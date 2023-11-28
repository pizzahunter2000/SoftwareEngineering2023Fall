package view;
import model.Connection;
import model.Point;
import model.Station;
import model.Train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserMenu extends JFrame{

    public UserMenu () {

        java.util.List< Station > stationList = new ArrayList<>();
        java.util.List<Connection> connections = new ArrayList<>();
        Train train1 = new Train();
        Train train2 = new Train();
        Point coordA = new model.Point(1.0, 2.0);
        Point coordB = new model.Point(4.0, 2.0);
        Point coordC = new Point(4.0, 6.0);
        Station stationA = new Station(coordA, "A");
        stationList.add(stationA);
        Station stationB = new Station(coordB, "B");
        stationList.add(stationB);
        Station stationC = new Station(coordC, "C");
        stationList.add(stationC);


        setTitle("User Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create JComboBox for starting station
        JComboBox<String> startingStationComboBox = new JComboBox<>(getStationNames(stationList));
        mainPanel.add(new JLabel("Select Starting Station:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(startingStationComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        // Create JComboBox for destination station
        JComboBox<String> destinationStationComboBox = new JComboBox<>(getStationNames(stationList));
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
                String startingStation = (String) startingStationComboBox.getSelectedItem();
                String destinationStation = (String) destinationStationComboBox.getSelectedItem();

                // Create RoutesMenu window with the selected options
                new RoutesMenu(startingStation, destinationStation);
            }
        });
        mainPanel.add(findRoutesButton, gbc);

        getContentPane().add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private String[] getStationNames(List<Station> stationList) {
        String[] names = new String[stationList.size()];
        for (int i = 0; i < stationList.size(); i++) {
            names[i] = stationList.get(i).getName();
        }
        return names;
    }

}
