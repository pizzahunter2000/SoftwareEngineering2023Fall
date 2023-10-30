package model;

import java.util.List;

public class Network {
    private int nrOfStations;
    private List<List<Connection>> connections;

    public Network(int nrOfStations, List<List<Connection>> connections) {
        this.nrOfStations = nrOfStations;
        this.connections = connections;
    }

    public int getNrOfStations() {
        return nrOfStations;
    }

    public void setNrOfStations(int nrOfStations) {
        this.nrOfStations = nrOfStations;
    }

    public List<List<Connection>> getConnections() {
        return connections;
    }

    public void setConnections(List<List<Connection>> connections) {
        this.connections = connections;
    }
}
