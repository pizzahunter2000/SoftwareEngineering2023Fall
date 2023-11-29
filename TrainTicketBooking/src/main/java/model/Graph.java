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

    public double calculateDistance(Station source, Station destination){
        List<Pair> pairs = adjList.get(source);
        Double distance;
        for(Pair pair : pairs){
            if(pair.getStation().equals(destination)){
                return pair.getDistance();
            }
        }
        return -1;
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

    public List<Connection> getConnections() {
        List<Connection> connections = new ArrayList<>();
        for(Map.Entry<Station, List<Pair>> entry : adjList.entrySet()){
            Station stationFrom = entry.getKey();
            Station stationTo = null;
            for(Pair pair : entry.getValue()){
                stationTo = pair.getStation();
            }
            Connection connection = new Connection(new Train(), stationFrom, stationTo,
                    new Date(), new Date());
            connections.add(connection);
        }
        return connections;
    }

    /**
     * Used by the admin to add a new connection.
     * @param connection - give connection to add to the network
     */
    public void addConnection(Connection connection) {
        if(adjList == null) {
            adjList = new HashMap<>();
            List<Pair> stationsPair = new ArrayList<>();
            stationsPair.add(new Pair(connection.getDistance(), connection.getNextStation()));
            adjList.put(connection.getStation(), stationsPair);
            return;
        }
        if(adjList.isEmpty()) {
            List<Pair> stationsPair = new ArrayList<>();
            stationsPair.add(new Pair(connection.getDistance(), connection.getNextStation()));
            adjList.put(connection.getStation(), stationsPair);
            return;
        }
        if(adjList.get(connection.getStation()) == null) {
            List<Pair> stationsPair = new ArrayList<>();
            stationsPair.add(new Pair(connection.getDistance(), connection.getNextStation()));
            adjList.put(connection.getStation(), stationsPair);
            return;
        }
        adjList.get(connection.getStation()).add(new Pair(connection.getDistance(),
                connection.getNextStation()));
    }

    // returns a map of station and a distance from the source
    // using the Dijkstra shortest path algorithm, where the
    // stations are the node and the connections are the edges.
    public Graph shortestPath(Station sourceStation){
        Set<Station> stations = getStations();
        Map<Station, Double> distances = new HashMap<>();
        Graph spanningTree = new Graph(new HashMap<>());
        for(Station station : stations){
            distances.put(station, Double.MAX_VALUE);
            if(station.equals(sourceStation)){
                distances.replace(station, 0.0);
            }
        }

        PriorityQueue<Pair> queue = new PriorityQueue<>(size, Comparator.comparingDouble(
                Pair::getDistance
        ));
        queue.add(new Pair(0, sourceStation));

        while(!queue.isEmpty()){
//            for (Pair pair : queue){
//                System.out.print(pair.getStation().getName());
//            }
//            System.out.println();
            Pair top = queue.poll();
            if(adjList.containsKey(top.getStation())){
                for(Pair pair : adjList.get(top.getStation())){
                    // just works, simply lovely
                    if(distances.get(pair.getStation()) > distances.get(top.getStation())
                            + pair.getDistance()){
                        double temp = distances.get(top.getStation()) + pair.getDistance();
                        distances.replace(pair.getStation(), temp);
//                        System.out.println(distances.get(top.getStation()) + " " + pair.getDistance());
//                        System.out.println(pair.getStation().getName() + temp);
                        spanningTree.addConnection(new Connection(new Train(), pair.getStation(),
                                top.getStation(), new Date(), new Date(), temp));
                        queue.add(new Pair(distances.get(pair.getStation()), pair.getStation()));
                    }
                }
            }
        }

//        System.out.println("Distance from source station");
//        for(Map.Entry<Station, Double> distance : distances.entrySet()){
//            System.out.println(distance.getKey().getName() + ": " + distance.getValue());
//        }
//        System.out.println("Spanning tree from station: " + sourceStation.getName());
//        System.out.println(spanningTree);
        return spanningTree;
    }

    public List<Station> shortestPathList(Station source, Station destination){
        Graph inverseSpanningTree = shortestPath(source);
        Station currentStation = destination;
        List<Station> stations = new ArrayList<>();
        while(!currentStation.equals(source)){
            stations.add(currentStation);
            currentStation = inverseSpanningTree.getAdjList().get(currentStation).get(0).getStation();
        }
        stations.add(currentStation);
        for(Station station : stations){
            System.out.println(station.getName());
        }
        return stations;
    }

    public double calculateTripDistance(Station source, Station destination) {
        Graph distances = shortestPath(source);
        return distances.calculateDistance(destination, source);
    }

    public double calculatePrice(Station source, Station destination,
                                 String discountName, double pricePerKm, DiscountList discountList){
        double distance = calculateTripDistance(source, destination);
        return distance * pricePerKm * (100 - discountList.getDiscount(discountName)) / 100;
    }

    @Override
    public String toString() {
        String text = "";
        for(Map.Entry<Station, List<Pair>> stations : adjList.entrySet()){
            text = text.concat(stations.getKey().getName());
            text = text.concat(": ");
            for(Pair pair : stations.getValue()){
                text = text.concat("(");
                text = text.concat(pair.getStation().getName());
                text = text.concat(",");
                text = text.concat(Double.toString(pair.getDistance()));
                text = text.concat(") ");
            }
            text = text.concat("\n");
        }
        return text;
    }
}
