package presenter;

import model.Vehicle;
import view.View;

import java.io.*;


public class Presenter {
    private  View view;
    private static final String PATH ="src\\resources\\output";
    private static final String PATHID ="src\\resources\\input\\vehicle_sequence.ddr";

    public Presenter() {
        view = new View();
    }


    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.run();
    }

    public void run() {
        try {

            View.print("\n\t\t\t...BIENVENIDO...");
            int option = 0;
            do {
                switch (option) {
                    case 1:
                        createFile(readId());
                        break;
                    case 2:
                        printVehicles();
                        break;
                    case 3:
                        searchVehicle();
                        break;
                    case 4:
                        View.print("Hasta Luego");
                        break;
                    default:
                }
            } while ((option=view.menu())!= 4);
        } catch (NumberFormatException e) {
            View.print("Solo puede ingresar valores númericos");
        }
    }


    private int readId() {
        int id = 0;
        try {
            File file =new File(PATHID);
            FileReader fr =new FileReader(file);
            BufferedReader br =new BufferedReader(fr);
            id=Integer.parseInt(br.readLine());
            fr.close();
            id++;
            FileWriter fw =new FileWriter(file,false);
            fw.write(Integer.toString(id));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println("Excepción al abrir archivo "+e);
        }
        return id;
    }
    private void createFile(int id) {
        String Brand = view.readStringLine("Dijete la Marca ");
        String Model = view.readStringLine("Dijete el modelo ");
        int Year = view.readIntegers("Dijete el año  ");
        String Plate = view.readStringLine("Dijete la Placa AAA123 ");
        String Color = view.readStringLine("Dijete el color ");
        Vehicle vehicle = new Vehicle( id ,Brand,  Model,  Color,  Year,  Plate);
        System.out.println(vehicle);
        registerVehicle(vehicle, PATH+"/vehicle_"+id+".veh");
    }
    private static void registerVehicle(Vehicle vehicle, String path){
        try(DataOutputStream stream = new DataOutputStream(new FileOutputStream(path))){
            stream.writeInt(vehicle.getId());
            stream.writeUTF(vehicle.getBrand());
            stream.writeUTF(vehicle.getModel());
            stream.writeInt(vehicle.getYear());
            stream.writeUTF(vehicle.getPlate());
            stream.writeUTF(vehicle.getColor());
        }catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    private static Vehicle readVehicle(String path) {
        Vehicle vehicle = new Vehicle();
        try  {
            DataInputStream stream = new DataInputStream(new FileInputStream(path));
            vehicle.setId(stream.readInt());
            vehicle.setBrand(stream.readUTF());
            vehicle.setModel(stream.readUTF());
            vehicle.setYear(stream.readInt());
            vehicle.setPlate(stream.readUTF());
            vehicle.setColor(stream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  vehicle;
    }
    private void searchVehicle(){
        int idRead = view.readIntegers("Dijite el id a buscar");
        String file = searchBytId(idRead);
        if ( file != null ){
            System.out.println(readVehicle(PATH+"/"+file));
        }else {
            System.out.println("Esta ide no esta registrada ");
        }
    }
    private String searchBytId(int idRead) {
        File directory = new File(PATH);
        String [] files = directory.list();
        for (String file:files) {
            int id= Integer.parseInt(file.split("_")[1].replace(".veh", ""));
            if (id == idRead){
                return file;
            }
        }
        return null;
    }
    private void printVehicles() {
        try {
            File directory = new File(PATH);
            String[] files = directory.list();
            String salida="----------\n";
            for (int i = 0; i < files.length; i++) {
                salida+=readVehicle(PATH+"//"+files[i])+"\n";
            }
            System.out.println(salida);
        } catch (Exception e) {
            System.err.println("Excepción al crear el archivo " + e);
        }
    }

}

