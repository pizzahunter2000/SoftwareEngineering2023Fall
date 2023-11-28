package view;

import model.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class GridMap extends JFrame {

    public Set stationList;

    private static final int GRID_SIZE = 20;
    private Color[][] gridColors;

    private Point lineStart = new Point(1, 5);
    private Point lineEnd = new Point(10, 2);

    public GridMap(Set stationList) {

        this.stationList = stationList;

        setTitle("Grid Coloring Example with Line");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridColors = new Color[GRID_SIZE][GRID_SIZE];
        initializeGridColors();

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

                // Draw a line between specified squares
                g.setColor(Color.RED);
                drawLine(g, lineStart, lineEnd, squareSize);
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

    private void drawLine(Graphics g, Point start, Point end, int squareSize) {
        int startX = start.x * squareSize + squareSize / 2;
        int startY = start.y * squareSize + squareSize / 2;
        int endX = end.x * squareSize + squareSize / 2;
        int endY = end.y * squareSize + squareSize / 2;

        g.drawLine(startX, startY, endX, endY);
    }

}
