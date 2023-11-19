import model.Connection;
import model.Station;
import model.Train;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JUnitTests {
    List<Connection> connections = new ArrayList<>();
    Train train1 = new Train();
    Train train2 = new Train();
    Station stationA;
    Station stationB;
    Station stationC;

    @BeforeEach
    void setUp(){
        List<Double> coordA = new ArrayList<>();
        coordA.add(1.0);
        coordA.add(2.0);
        List<Double> coordB = new ArrayList<>();
        coordB.add(4.0);
        coordB.add(2.0);
        List<Double> coordC = new ArrayList<>();
        coordC.add(4.0);
        coordC.add(6.0);
        stationA = new Station(coordA, "A");
        stationB = new Station(coordB, "B");
        stationC = new Station(coordC, "C");
        connections.add(new Connection(train1, stationA, stationB, new Date(), new Date()));
        connections.add(new Connection(train1, stationB, stationC, new Date(), new Date()));
        connections.add(new Connection(train2, stationA, stationC, new Date(), new Date()));
        connections.add(new Connection(train2, stationC, stationB, new Date(), new Date()));
        connections.add(new Connection(train2, stationB, stationA, new Date(), new Date()));
        connections.add(new Connection(train1, stationC, stationA, new Date(), new Date()));
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
        assert(connections.get(2).calculateDistance() == 5);
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
    void testCalculateDistance4(){
        assert(connections.get(5).calculateDistance() == 5);
    }
}
