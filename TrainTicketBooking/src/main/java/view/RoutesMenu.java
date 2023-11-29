package view;

import model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutesMenu extends JFrame {
    public RoutesMenu( Graph graph) {
        setTitle("Routes Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Display selected options in a label
        JLabel label = new JLabel("Selected Routes: ");
        mainPanel.add(label, gbc);

        // See Map Button
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton seeMapButton = new JButton("See Map");
        seeMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the logic to create a GridMap window
                dispose(); // Close the current window
                new GridMap(graph); // Open the GridMap window
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
                new TicketSelection(); // Open the TicketSelection window
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
