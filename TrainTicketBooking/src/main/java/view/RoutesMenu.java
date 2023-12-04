package view;

import model.Graph;
import model.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
public class RoutesMenu extends JFrame {

    private Station start;
    private Station end;

    private List <Station> path;

    private Graph graph;
    public RoutesMenu( Graph graph, Station source, Station destination ){
        this.start = source;
        this.end = destination;
        this.graph = graph;
        graph.shortestPath(start);
        this.path = graph.shortestPathList(start, end);


        setTitle("Routes Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        int n= path.size();

        StringBuilder pathStringBuilder = new StringBuilder(path.get(n-1).getName());
        for (int i = n-2; i >= 0; i--) {
            pathStringBuilder.append("->").append(path.get(i).getName());
        }

        String pathString = pathStringBuilder.toString();

        JLabel routeLabel = new JLabel("Selected Route: From " + start.getName() + " to " + end.getName());
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(routeLabel, gbc);

        // Display the path
        JLabel pathLabel = new JLabel("Path: " + pathString);
        gbc.gridy = 1;
        mainPanel.add(pathLabel, gbc);

        // See Map Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton seeMapButton = new JButton("See Map");
        seeMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the logic to create a GridMap window
                dispose(); // Close the current window
                new GridMap(graph, start, end); // Open the GridMap window
            }
        });
        mainPanel.add(seeMapButton, gbc);

        // Order Button
        gbc.gridx = 1;
        JButton orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the logic to create a TicketSelection window
                dispose(); // Close the current window
                // Calculate Price needs source and destination, so I have to send them as parameters to TicketSelection
                new TicketSelection(graph, start,  end); // Open the TicketSelection window
            }
        });
        mainPanel.add(orderButton, gbc);

        // Back to User Button
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        JButton backToUserButton = new JButton("Back to Selecting Endpoints");
        backToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the logic to go back to the Login Menu
                dispose(); // Close the current window
                new UserMenu(graph); // Open the UserMenu
            }
        });
        mainPanel.add(backToUserButton, gbc);

        getContentPane().add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
