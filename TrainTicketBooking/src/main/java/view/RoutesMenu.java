package view;

import javax.swing.*;
import java.awt.*;

public class RoutesMenu extends JFrame {
    public RoutesMenu(String startingStation, String destinationStation) {
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

        getContentPane().add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
