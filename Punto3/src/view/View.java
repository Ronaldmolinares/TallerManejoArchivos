package view;

import java.util.Scanner;

public class View {
    private Scanner reader;

    public View(){
        reader = new Scanner(System.in);
    }

    public int readIntegers(String mensaje){
        System.out.println(mensaje);
        return Integer.parseInt(reader.nextLine());
    }

    public String readStringLine(String mensaje){
        String exit;
        System.out.println(mensaje);
        return exit=reader.nextLine();
    }
    public String readString(String mensaje){
        String exit;
        System.out.println(mensaje);
        return exit=reader.next();
    }
    public void pause(String mensaje){
        System.out.println(mensaje);
        reader.nextLine();
        System.out.println("\n\n");
    }

    public static void print(String mensaje){
        System.out.println(mensaje);
    }
    public int menu(){
        print("\n_______________________________________________________________________________");
        print("\n\t\t\t     ...MENU PRINCIPAL...");
        print("\n_______________________________________________________________________________");
        int opcion = 0;
        return opcion = readIntegers("\nIngrese la opcion que desea relizar:"+
                "\n[1 ]Crear un nuevo vehículo"+
                "\n[2] Encuentra todos los vehículos "+
                "\n[3] Encuentra un vehículo por id"+
                "\n[0]Salir");

    }
}
