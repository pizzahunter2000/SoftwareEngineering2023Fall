package model;

import java.util.List;

public class Station {
    private static int MAX_ID = 0;
    private int id;
    private Point coordinates;
    private String name;

    public Station(Point coordinates, String name) {
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

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX(){
        return coordinates.getX();
    }

    public double getY(){
        return coordinates.getY();
    }
}
