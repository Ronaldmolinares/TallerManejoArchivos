package presenter;

import model.Vehicle;
import view.View;

import java.io.*;
import java.util.Arrays;


public class Presenter {
    private  View view;
    private static final String PATH ="src/resources/outAndinp";
    private int id =1;

    public Presenter() {
        view = new View();
        id = readId();
    }

    /**
     * Nos permite Separar los datos del nombre del archivo y buscar el numero de este
     * @return
     */
    private int readId() {
      File file = new File(PATH);
      String [] files = file.list();
      try {
          return Integer.parseInt(files [files.length-1].split("-")[1].replace(".ddr", ""))+1;
      }catch (ArrayIndexOutOfBoundsException e){
          return 1;
      }
    }


    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.run();
    }

    public void run() {
        int option = 0;
        do {
            View.print("\n\t\t\t...BIENVENIDO...");
            switch (option) {
                case 1:
                    createFile();
                    break;
                case 2:
                    printVehicles();
                    break;
                case 3:
                    searchVehicle();
                    break;

                default:
                    View.print("Salio Del Programa");
            }
        } while ((option=view.menu())!= 0);
    }


    private static void registerVehicle(Vehicle vehicle, String path){
        try(DataOutputStream stream = new DataOutputStream(new FileOutputStream(path))){
            stream.writeUTF(vehicle.getBrand());
            stream.writeUTF(vehicle.getModel());
            stream.writeInt(vehicle.getYear());
            stream.writeUTF(vehicle.getPlate());
            stream.writeUTF(vehicle.getColor());
        }catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }
    private void createFile(){
        String Brand = view.readStringLine("Dijete la Marca ");
        String Model = view.readStringLine("Dijete el modelo ");
        int Year = view.readIntegers("Dijete el año  ");
        String Plate = view.readStringLine("Dijete la Placa AAA123 ");
        String Color = view.readStringLine("Dijete el color ");
        Vehicle vehicle = new Vehicle( id ,Brand,  Model,  Color,  Year,  Plate);
        System.out.println(vehicle);
        registerVehicle(vehicle, PATH+"/newVehicle-"+id+".ddr");
       id+=1;
    }
    private static Vehicle readVehicle(String path) {
        Vehicle vehicle = new Vehicle();
        try  {
            DataInputStream stream = new DataInputStream(new FileInputStream(path));
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
            int id= Integer.parseInt(file.split("-")[1].replace(".ddr", ""));
            if (id == idRead){
                return file;
            }
        }
        return null;
    }
    private void printVehicles() {
        File directory = new File(PATH);
        String [] files = directory.list();
        System.out.println(Arrays.toString(files));
    }

}

