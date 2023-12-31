import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JUnitTests {
    List<Connection> connections = new ArrayList<>();
    Train train1 = new Train();
    Train train2 = new Train();
    Station stationA;
    Station stationB;
    Station stationC;
    Station stationD;
    Graph network1;
    Graph network2;
    Point coordA, coordB, coordC, coordD;
    DiscountList discountList;

    @BeforeEach
    void setUp(){
        coordA = new Point(1.0, 2.0);
        coordB = new Point(4.0, 2.0);
        coordC = new Point(4.0, 6.0);
        coordD = new Point(7.0, 10.0);
        stationA = new Station(coordA, "A");
        stationB = new Station(coordB, "B");
        stationC = new Station(coordC, "C");
        stationD = new Station(coordD, "D");
        connections.add(new Connection(train1, stationA, stationB, new Date(), new Date()));
        connections.add(new Connection(train1, stationB, stationC, new Date(), new Date()));
        connections.add(new Connection(train2, stationA, stationC, new Date(), new Date()));
        connections.add(new Connection(train2, stationC, stationB, new Date(), new Date()));
        connections.add(new Connection(train2, stationB, stationA, new Date(), new Date()));
        connections.add(new Connection(train1, stationC, stationD, new Date(), new Date()));

        network1 = new Graph(new HashMap<>()); // empty graph
        network2 = new Graph(connections); // connected graph

        discountList = new DiscountList();
        discountList.addDiscount("Student", 20.0);
    }

    /**
     * Test cases for calculating the distance of a connection:
     *  - both coordinates increase
     *  - both decrease
     *  - one increases, one decreases
     *  - edge case: they are constant
     */

    @Test
    void testCalculateDistance1(){
        assert(connections.get(5).calculateDistance() == 5);
    }

    @Test
    void testCalculateDistance2(){
        assert(connections.get(3).calculateDistance() == 4);
    }


    @Test
    void testCalculateDistance3(){
        assert(connections.get(4).calculateDistance() == 3);
    }

    @Test
    void testAddingConnectionToEmptyGraph() {
        network1.addConnection(new Connection(train1, stationA, stationB, new Date(), new Date()));
        assert network1.getAdjList() != null;
        assert network1.getAdjList().get(stationA) != null;
        assert(network1.getAdjList().get(stationA).size() == 1);
    }

    /**
     * Try adding a connection between 2 stations that don't have a connection.
     */
    @Test
    void testAddingConnectionToConnectedGraph() {
        network2.addConnection(new Connection(train1, stationC, stationA, new Date(), new Date()));
        assert network2.getAdjList() != null;
        assert network2.getAdjList().get(stationC) != null;
        assert(network2.getAdjList().get(stationC).size() == 3);
    }

    /**
     * Try adding a connection between 2 stations that already have a connection.
     */
    @Test
    void testAddingExistingConnectionToConnectedGraph() {
        network2.addConnection(new Connection(train1, stationB, stationA, new Date(), new Date()));
        assert network2.getAdjList() != null;
        assert network2.getAdjList().get(stationB) != null;
        assert(network2.getAdjList().get(stationB).size() == 3);
    }

    @Test
    void testCalculatePriceWithoutDiscount(){
        assert(network2.calculatePrice(stationA, stationC, "", 1, discountList) == 5);
    }

    @Test
    void testCalculatePriceWithoutDiscount2(){
        assert(network2.calculatePrice(stationA, stationC, "", 2, discountList) == 10);
    }

    @Test
    void testCalculatePriceWithDiscount(){
        List<Station> stations = network2.shortestPathList(stationA, stationD);
        assert(network2.calculatePrice(stationA, stationC, "Student", 2, discountList) == 8);
    }
}
