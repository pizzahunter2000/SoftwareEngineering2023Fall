package model;

import java.util.*;

public class Graph {
    private int size;
    private Map<Station, List<Pair>> adjList;

    public Graph(Map<Station, List<Pair>> adjList) {
        this.adjList = adjList;
    }

    // In the Network class the railway system is in list and has
    // to be transformed into a graph for Dijkstra's algorithm to work.
    public Graph(List<Connection> connections){
        adjList = new HashMap<>();
        for(Connection connection : connections){
            if(adjList.containsKey(connection.getStation())){
                adjList.get(connection.getStation())
                        .add(new Pair(connection.getDistance(), connection.getNextStation()));
            } else {
                List<Pair> stations = new ArrayList<>();
                stations.add(new Pair(connection.getDistance(), connection.getNextStation()));
                adjList.put(connection.getStation(), stations);
            }
        }
        size = adjList.size();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Station, List<Pair>> getAdjList() {
        return adjList;
    }

    public void setAdjList(Map<Station, List<Pair>> adjList) {
        this.adjList = adjList;
    }

    public List<Station> getStations(List<Connection> connections){
        List<Station> stations = new ArrayList<>();
        for(Connection connection : connections){
            if(!stations.contains(connection.getStation())){
                stations.add(connection.getStation());
            }
            if(!stations.contains(connection.getNextStation())){
                stations.add(connection.getNextStation());
            }
        }
        return stations;
    }

    public Set<Station> getStations(){
        Set<Station> stations = new HashSet<>();
        for(Map.Entry<Station, List<Pair>> entry : adjList.entrySet()){
            stations.add(entry.getKey());
            for(Pair pair : entry.getValue()){
                stations.add(pair.getStation());
            }
        }
        return stations;
    }

    // returns a map of station and a distance from the source
    // using the Dijkstra shortest path algorithm, where the
    // stations are the node and the connections are the edges.
    public Map<Station, Double> shortestPath(Station sourceStation){
        Set<Station> stations = getStations();
        Map<Station, Double> spanningTree = new HashMap<>();
        for(Station station : stations){
            spanningTree.put(station, Double.MAX_VALUE);
            if(station.equals(sourceStation)){
                spanningTree.replace(station, 0.0);
            }
        }

        PriorityQueue<Pair> queue = new PriorityQueue<>(size, Comparator.comparingDouble(
                Pair::getDistance
        ));
        queue.add(new Pair(0, sourceStation));

        while(!queue.isEmpty()){
            Pair top = queue.poll();
            if(adjList.containsKey(top.getStation())){
                for(Pair pair : adjList.get(top.getStation())){
                    // just works, simply lovely
                    if(spanningTree.get(pair.getStation()) > spanningTree.get(top.getStation())
                            + pair.getDistance()){
                        spanningTree.replace(pair.getStation(), spanningTree.get(top.getStation())
                                + pair.getDistance());
                        queue.add(new Pair(spanningTree.get(pair.getStation()),
                                pair.getStation()));
                    }
                }
            }
        }

        System.out.println("Distance from source station");
        for(Map.Entry<Station, Double> distances : spanningTree.entrySet()){
            System.out.println(distances.getKey().getName() + ": " + distances.getValue());
        }

        return spanningTree;
    }

    @Override
    public String toString() {
        String text = "";
        for(Map.Entry<Station, List<Pair>> stations : adjList.entrySet()){
            text = text.concat(stations.getKey().getName());
            text = text.concat(": ");
            for(Pair pair : stations.getValue()){
                text = text.concat(pair.getStation().getName());
                text = text.concat(",");
            }
            text = text.concat("\n");
        }
        return text;
    }
}
