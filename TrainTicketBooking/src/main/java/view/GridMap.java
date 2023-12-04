package view;

import model.Graph;
import model.Station;
import model.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class GridMap extends JFrame {

    private Graph graph;

    public Set stationList;

    private static final int GRID_SIZE = 20;
    private Color[][] gridColors;

    private Station start;
    private Station end;

    private List<Station> path;


    public GridMap(Graph graph, Station start, Station end) {
        this.start = start;
        this.end =end;
        this.graph = graph;
        this.path = graph.shortestPathList(start,end);
        this.stationList = graph.getStations();

        setTitle("Grid Coloring Example with Line");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridColors = new Color[GRID_SIZE][GRID_SIZE];
        initializeGridColors();
        colorStations(stationList);



        JPanel gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int squareSize = getWidth() / GRID_SIZE;

                // Draw the grid squares
                for (int i = 0; i < GRID_SIZE; i++) {
                    for (int j = 0; j < GRID_SIZE; j++) {
                        g.setColor(gridColors[i][j]);
                        g.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
                        g.setColor(Color.BLACK); // color of the lines in the Grid
                        g.drawRect(i * squareSize, j * squareSize, squareSize, squareSize);
                    }
                }

                drawConnections(g, squareSize);
                colorPath (g,path, start, end);



//                // Draw a line between specified squares
//                g.setColor(Color.RED);
//                drawLine(g, lineStart, lineEnd, squareSize);

            }
        };


        // Button to return to RoutesMenu
        JButton backToRoutesButton = new JButton("Back to Routes Menu");
        backToRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the logic to return to the RoutesMenu
                dispose(); // Close the current window
                new RoutesMenu(graph, start, end); // Open the RoutesMenu
            }
        });

        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gridPanel, BorderLayout.CENTER);
        getContentPane().add(backToRoutesButton, BorderLayout.SOUTH);


        getContentPane().add(gridPanel);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeGridColors() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridColors[i][j] = Color.WHITE; // Initialize all squares to white
            }
        }
    }



    private void colorStations(Set<Station> stationList) {
        int squareSize = getWidth() / GRID_SIZE;

        for (Station station : stationList) {
            int gridX = 2*(int) Math.floor(station.getX());
            int gridY = 2*(int) Math.floor(station.getY());

            if (gridX >= 0 && gridX < GRID_SIZE && gridY >= 0 && gridY < GRID_SIZE) {
                gridColors[gridX][gridY] = Color.BLACK;
            }
        }
    }

    private void colorPath (Graphics g, List<Station> path, Station start, Station end)
    {


        for (int i = 0; i < path.size() - 1; i++) {
            Station currentStation = path.get(i);
            Station nextStation = path.get(i + 1);

            int currentX = 2 * (int) Math.floor(currentStation.getX());
            int currentY = 2 * (int) Math.floor(currentStation.getY());

            int nextX = 2 * (int) Math.floor(nextStation.getX());
            int nextY = 2 * (int) Math.floor(nextStation.getY());

            // Color the connection between consecutive stations in yellow
            colorConnection(g,currentX, currentY, nextX, nextY, Color.BLUE);

            if(i>0 || i != path.size()-2)
            {
                colorStation(g,currentStation.getX(),currentStation.getY(),Color.BLUE);
            }
        }
        // Color the start and end stations in green
        colorStation(g,start.getX(), start.getY(), Color.GREEN);
        colorStation(g,end.getX(), end.getY(), Color.RED);
    }

    private void drawConnections(Graphics g, int squareSize) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3)); // Set the stroke thickness to 3 pixels


        Map<Station,List<Pair>> adjList = graph.getAdjList();

        for (Map.Entry<Station, List<Pair>> entry : adjList.entrySet()) {
            Station source = entry.getKey();
            int sourceX = 2* (int) Math.floor(source.getX() * GRID_SIZE);
            int sourceY = 2* (int) Math.floor(source.getY() * GRID_SIZE);

            for (Pair pair : entry.getValue()) {
                Station destination = pair.getStation();
                int destX = 2* (int) Math.floor(destination.getX() * GRID_SIZE);
                int destY = 2* (int) Math.floor(destination.getY() * GRID_SIZE);

                Point start = new Point(sourceX,sourceY);
                Point end = new Point(destX,destY);

                g2d.setColor(Color.BLACK);


                g2d.drawLine(
                        sourceX ,
                        sourceY ,
                        destX ,
                        destY
                );
            }
        }
    }

    private void colorConnection(Graphics g, int startX, int startY, int endX, int endY, Color color) {
        int squareSize = getWidth() / GRID_SIZE;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3)); // Set the stroke thickness to 3 pixels
        g2d.setColor(color);

        g2d.drawLine(
                startX * squareSize ,
                startY * squareSize ,
                endX * squareSize ,
                endY * squareSize
        );
    }

    private void colorStation(Graphics g, double x, double y, Color color) {
        int gridX = 2 * (int) Math.floor(x);
        int gridY = 2 * (int) Math.floor(y);

        if (gridX >= 0 && gridX < GRID_SIZE && gridY >= 0 && gridY < GRID_SIZE) {
            gridColors[gridX][gridY] = color;
        }
        repaint();
    }

}
