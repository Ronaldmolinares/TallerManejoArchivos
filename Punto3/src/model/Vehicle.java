package model;

public class Vehicle {
    private String brand;
    private String model;
    private String color;
    private int year;
    private String  plate ;
    private int id;


    public Vehicle(){

    }
    public Vehicle(int id, String brand, String model, String color, int year, String plate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.plate = plate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "Vehículo: " +
                "ID:' "+id+ '\'' +
                " Marca:'" + brand + '\'' +
                " Modelo:'" + model + '\'' +
                " Color:'" + color + '\'' +
                " Año:" + year +
                " Placa:'" + plate + '\'';
    }
}