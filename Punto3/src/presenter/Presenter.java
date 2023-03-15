package presenter;

import view.*;
import model.*;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Presenter {
    private  View view;
    private int id =1;

    public Presenter() {
        view = new View();
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:

                    break;
                default:
                    View.print("Salio Del Programa");
            }
        } while ((option=view.menu())!= 0);
    }
    private static void registerVehicle(Vehicle vehicle, String path){
        try(DataOutputStream stream = new DataOutputStream(new FileOutputStream(path))){
            stream.writeBytes(vehicle.getBrand());
            stream.writeBytes(vehicle.getModel());
            stream.writeInt(vehicle.getYear());
            stream.writeBytes(vehicle.getPlate());
            stream.writeBytes(vehicle.getColor());
        }catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }
    private void createFile(){
        String Brand = view.readStringLine("Dijete la Marca ");
        String Model = view.readStringLine("Dijete el modelo ");
        int Year = view.readIntegers("Dijete el año  ");
        String Plate = view.readStringLine("Dijete la Placa ");
        String Color = view.readStringLine("Dijete el color ");
        Vehicle vehicle = new Vehicle( id ,Brand,  Model,  Color,  Year,  Plate);
        System.out.println(vehicle);
        registerVehicle(vehicle, "src/resources/out/newVehicle"+id+".ddr");
       id+=1;
    }

}

