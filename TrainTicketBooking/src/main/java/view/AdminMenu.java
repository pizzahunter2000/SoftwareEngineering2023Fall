package view;
import model.Graph;
import model.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class AdminMenu extends JFrame{

        //private Map<String, List<Pair>> adjList; // Assuming this is your adjacency list
            Graph graph;
            public AdminMenu(Graph graph) {

                this.graph= graph;

                setTitle("Admin Menu");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel mainPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(10, 10, 10, 10);

                // Dropdown menus for choosing the new connection
                JComboBox<String> startStationComboBox = new JComboBox<>(getStationNames(graph.getStations()));
                JComboBox<String> endStationComboBox = new JComboBox<>(getStationNames(graph.getStations()));
                mainPanel.add(new JLabel("Start Station:"), gbc);

                gbc.gridx = 1;
                mainPanel.add(startStationComboBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                mainPanel.add(new JLabel("End Station:"), gbc);

                gbc.gridx = 1;
                mainPanel.add(endStationComboBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                // Button to add connection
                JButton addConnectionButton = new JButton("Add Connection");
                addConnectionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle the logic to add a new connection
                        String startStation = (String) startStationComboBox.getSelectedItem();
                        String endStation = (String) endStationComboBox.getSelectedItem();
                        // Add connection logic
                        JOptionPane.showMessageDialog(null, "Connection Added Successfully");
                    }
                });
                mainPanel.add(addConnectionButton, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                mainPanel.add(new JLabel("List of Discounts:"), gbc);

                // Dropdown menu for a list of discounts
                JComboBox<String> discountComboBox = new JComboBox<>(getDiscountOptions());
                gbc.gridx = 1;
                mainPanel.add(discountComboBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                mainPanel.add(new JLabel("New Discount:"), gbc);

                // Textfield for setting a new discount
                JTextField newDiscountTextField = new JTextField();
                newDiscountTextField.setPreferredSize(new Dimension(100, 25)); // Set preferred size
                gbc.gridx = 1;
                mainPanel.add(newDiscountTextField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 5;
                // Button to modify discount
                JButton modifyDiscountButton = new JButton("Modify Discount");
                modifyDiscountButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle the logic to modify the discount
                        String selectedDiscount = (String) discountComboBox.getSelectedItem();
                        String newDiscount = newDiscountTextField.getText();
                        // Modify discount logic
                        JOptionPane.showMessageDialog(null, "Discount Modified Successfully");
                    }
                });
                mainPanel.add(modifyDiscountButton, gbc);

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
                setSize(500, 600);
                setLocationRelativeTo(null);
                setVisible(true);
        }


    private String[] getStationNames(Set<Station> stationList) {
        String[] names = new String[stationList.size()];
        int i=0;
        for (Station iter : stationList) {
            names[i] = iter.getName();
            i++;
        }
        return names;
    }

    private String[] getDiscountOptions() {
        // Provide logic to get discount options
        // For now, using dummy data
        return new String[]{"Discount 1", "Discount 2", "Discount 3"};
    }
}
