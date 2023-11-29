import model.*;
import view.GridMap;
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
        Point pA = new Point(1.0,2.0);
        Point pB = new Point(3.0,2.0);
        Point pC = new Point(3.0,1.0);
        Station stationA = new Station(pA, "A");
        stationList.add(stationA);
        Station stationB = new Station(pB, "B");
        stationList.add(stationB);
        Station stationC = new Station(pC, "C");
        stationList.add(stationC);
        connections.add(new Connection(train1, stationA, stationB, new Date(), new Date()));
        connections.add(new Connection(train1, stationB, stationC, new Date(), new Date()));
        connections.add(new Connection(train2, stationA, stationC, new Date(), new Date()));


        // Hardcoded additional stations and connections
        Station stationD = new Station(new Point(6, 4), "D");
        stationList.add(stationD);
        connections.add(new Connection(train2, stationC, stationD, new Date(), new Date()));

        Station stationE = new Station(new Point(5, 5), "E");
        stationList.add(stationE);
        connections.add(new Connection(train1, stationA, stationE, new Date(), new Date()));

        Station stationF = new Station(new Point(2, 3), "F");
        stationList.add(stationF);
        connections.add(new Connection(train2, stationD, stationF, new Date(), new Date()));

        Station stationG = new Station(new Point(9, 8), "G");
        stationList.add(stationG);
        connections.add(new Connection(train1, stationE, stationG, new Date(), new Date()));




        Graph graph = new Graph(connections);
        System.out.println("Testing translation to graph...");
        System.out.println(graph);

        System.out.println("Testing shortest path algorithm...");
        graph.shortestPath(stationA);

        LoginMenu lm = new LoginMenu(graph);

        //
        //
        // GridMap map = new GridMap(graph);

    }
}