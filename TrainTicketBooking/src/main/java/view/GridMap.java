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


    public GridMap(Graph graph, Station start, Station end) {
        this.start = start;
        this.end =end;
        this.graph = graph;

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



//                // Draw a line between specified squares
//                g.setColor(Color.RED);
//                drawLine(g, lineStart, lineEnd, squareSize);

            }
        };

        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int squareSize = gridPanel.getWidth() / GRID_SIZE;
                int x = e.getX() / squareSize;
                int y = e.getY() / squareSize;

                if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
                    toggleColor(x, y);
                    gridPanel.repaint();
                }
            }
        });

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

    private void toggleColor(int x, int y) {
        if (gridColors[x][y].equals(Color.WHITE)) {
            gridColors[x][y] = Color.BLUE; // Change color to blue
        } else {
            gridColors[x][y] = Color.WHITE; // Change color back to white
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

    private void drawLine( Point start, Point end, int squareSize) {
        int startX = start.x * squareSize + squareSize / 2;
        int startY = start.y * squareSize + squareSize / 2;
        int endX = end.x * squareSize + squareSize / 2;
        int endY = end.y * squareSize + squareSize / 2;

        //g.setColor(Color.RED);
        //g.drawLine(startX, startY, endX, endY);
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

                g2d.setColor(Color.BLUE);


                g2d.drawLine(
                        sourceX ,
                        sourceY ,
                        destX ,
                        destY
                );
            }
        }
    }

}
