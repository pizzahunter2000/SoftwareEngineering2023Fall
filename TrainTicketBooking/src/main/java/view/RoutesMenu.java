package view;

import model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutesMenu extends JFrame {
    public RoutesMenu(String startingStation, String destinationStation, Graph graph) {
        setTitle("Routes Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Display selected options in a label
        JLabel label = new JLabel("Selected Routes: " + startingStation + " to " + destinationStation);
        mainPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        // Button to go back to the Login Menu
        JButton backToUserButton = new JButton("Back to Selecting Endpoints");
        backToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the logic to go back to the Login Menu
                // For now, just closing the AdminMenu
                dispose(); // Close the AdminMenu
                new UserMenu(graph); // Open the LoginMenu
            }
        });
        mainPanel.add(backToUserButton, gbc);

        getContentPane().add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
