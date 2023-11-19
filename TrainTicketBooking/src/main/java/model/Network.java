package model;

import java.util.List;

public class Network {
    private int nrOfStations;
    private List<Connection> connections;

    public Network(int nrOfStations, List<Connection> connections) {
        this.nrOfStations = nrOfStations;
        this.connections = connections;
    }

    public int getNrOfStations() {
        return nrOfStations;
    }

    public void setNrOfStations(int nrOfStations) {
        this.nrOfStations = nrOfStations;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
