package model;

public class Pair {
    private double distance;
    private Station station;

    public Pair(double distance, Station station) {
        this.distance = distance;
        this.station = station;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
