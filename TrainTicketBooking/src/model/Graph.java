package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Station, List<Station>> adjList;

    public Graph(Map<Station, List<Station>> adjList) {
        this.adjList = adjList;
    }

    // In the Network class the railway system is in list and has
    // to be transformed into a graph for Dijkstra's algorithm to work.
    public Graph(List<Connection> connections){
        adjList = new HashMap<>();
        for(Connection connection : connections){
            if(adjList.containsKey(connection.getStation())){
                adjList.get(connection.getStation()).add(connection.getNextStation());
            } else {
                List<Station> stations = new ArrayList<>();
                stations.add(connection.getNextStation());
                adjList.put(connection.getStation(), stations);
            }
        }
    }

    public Map<Station, List<Station>> getAdjList() {
        return adjList;
    }

    public void setAdjList(Map<Station, List<Station>> adjList) {
        this.adjList = adjList;
    }

    @Override
    public String toString() {
        String text = "";
        for(Map.Entry<Station, List<Station>> stations : adjList.entrySet()){
            text = text.concat(stations.getKey().getName());
            text = text.concat(": ");
            for(Station station : stations.getValue()){
                text = text.concat(station.getName());
                text = text.concat(",");
            }
            text = text.concat("\n");
        }
        return text;
    }
}
