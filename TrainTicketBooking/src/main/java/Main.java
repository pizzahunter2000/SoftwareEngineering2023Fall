import model.*;
import view.LoginMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List < Station > stationList = new ArrayList<>();
        List<Connection> connections = new ArrayList<>();
        Train train1 = new Train();
        Train train2 = new Train();
        Point coordA = new Point(1.0, 2.0);
        Point coordB = new Point(4.0, 2.0);
        Point coordC = new Point(4.0, 6.0);
        Station stationA = new Station(coordA, "A");
        stationList.add(stationA);
        Station stationB = new Station(coordB, "B");
        stationList.add(stationB);
        Station stationC = new Station(coordC, "C");
        stationList.add(stationC);
        connections.add(new Connection(train1, stationA, stationB, new Date(), new Date()));
        connections.add(new Connection(train1, stationB, stationC, new Date(), new Date()));
        connections.add(new Connection(train2, stationA, stationC, new Date(), new Date()));
        Graph graph = new Graph(connections);
        System.out.println("Testing translation to graph...");
        System.out.println(graph);

        System.out.println("Testing shortest path algorithm...");
        graph.shortestPath(stationA);

        LoginMenu lm = new LoginMenu();
    }
}