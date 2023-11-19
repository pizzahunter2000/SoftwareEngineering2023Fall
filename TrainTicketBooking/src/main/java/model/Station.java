package model;

import java.util.List;

public class Station {
    private static int MAX_ID = 0;
    private int id;
    private List<Double> coordinates;
    private String name;

    public Station(List<Double> coordinates, String name) {
        this.id = ++MAX_ID;
        this.coordinates = coordinates;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX(){
        return coordinates.get(0);
    }

    public double getY(){
        return coordinates.get(1);
    }
}
