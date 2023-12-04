package view;
import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserMenu extends JFrame{

    Graph graph;

    public UserMenu (Graph graph) {

        this.graph= graph;

        java.util.Set< Station > stationList = graph.getStations();
        java.util.List<Connection> connections = graph.getConnections();


        setTitle("User Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Update the JComboBox declaration to use Station objects
        JComboBox<Station> startingStationComboBox = new JComboBox<>(getStationArray(stationList));
        startingStationComboBox.setRenderer(new StationListCellRenderer());
        mainPanel.add(new JLabel("Select Starting Station:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(startingStationComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        // Update the JComboBox declaration for destination station
        JComboBox<Station> destinationStationComboBox = new JComboBox<>(getStationArray(stationList));
        destinationStationComboBox.setRenderer(new StationListCellRenderer());
        mainPanel.add(new JLabel("Select Destination Station:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(destinationStationComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);

        // Create "Find Routes" button
        JButton findRoutesButton = new JButton("Find Routes");
        findRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected options from the dropdown menus
                Station startingStation = (Station) startingStationComboBox.getSelectedItem();
                Station destinationStation = (Station) destinationStationComboBox.getSelectedItem();
                dispose();

                // Create RoutesMenu window with the selected options
                System.out.println("We got " + startingStation.getName() + " and " + destinationStation.getName());
                new RoutesMenu( graph, startingStation, destinationStation);
            }
        });
        mainPanel.add(findRoutesButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        // Button to go back to the Login Menu
        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the logic to go back to the Login Menu
                // For now, just closing the AdminMenu
                dispose(); // Close the AdminMenu
                new LoginMenu(graph); // Open the LoginMenu
            }
        });
        mainPanel.add(backToLoginButton, gbc);

        getContentPane().add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // Custom renderer class so that
    // It the JComboBox displays only the name of the Station
    // But the selection is of class Station
    class StationListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Station) {
                value = ((Station) value).getName(); // Display only the name
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }

    // Modify the getStationNames method to return an array of Station objects
    private Station[] getStationArray(Set<Station> stationList) {
        return stationList.toArray(new Station[0]);
    }

}
