package model;

import java.util.Date;

public class Connection {
    private static int MAX_ID;
    private int id;
    private Train train;
    private Station station;
    private Station nextStation;
    private double distance;
    private Date departureDate;
    private Date arrivalDate;

    public Connection(Train train, Station station, Station nextStation,
                      Date departureDate, Date arrivalDate) {
        this.id = ++MAX_ID;
        this.train = train;
        this.station = station;
        this.nextStation = nextStation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.distance = calculateDistance();
    }

    public Connection(Train train, Station station, Station nextStation,
                      Date departureDate, Date arrivalDate, Double distance) {
        this.id = ++MAX_ID;
        this.train = train;
        this.station = station;
        this.nextStation = nextStation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Station getNextStation() {
        return nextStation;
    }

    public void setNextStation(Station nextStation) {
        this.nextStation = nextStation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double calculateDistance(){
        return Math.sqrt(
                Math.pow(station.getX() - nextStation.getX(), 2.0)
                + Math.pow(station.getY() - nextStation.getY(), 2.0)
        );
    }
}
