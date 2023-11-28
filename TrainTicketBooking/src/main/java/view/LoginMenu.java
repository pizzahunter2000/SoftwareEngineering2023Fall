package view;

import model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame {

    private JButton userButton;
    private JButton adminButton;
    private JPasswordField passwordField1;
    private JTextField passwordTextField;
    private JLabel titleLabel ;

    private JPanel mainPanel;

    private Graph graph;

    public LoginMenu (Graph graph) {
        this.graph=graph;
        setTitle ("Login Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,400);
        setVisible(true);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;

        JLabel titleLabel = new JLabel("Login Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing around buttons

        JButton userButton = new JButton("User");
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                dispose();
                // Create a new UserMenu window
                UserMenu userWindow = new UserMenu(graph);
            }
        });
        mainPanel.add(userButton, gbc);

        gbc.gridx = 1;
        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt for password
                String password = JOptionPane.showInputDialog(LoginMenu.this, "Enter Admin Password:");

                // Check if the password is "admin"
                if (password != null && password.equals("admin")) {
                    // Close the current window
                    dispose();
                    // Create a new AdminMenu window
                    AdminMenu adminWindow = new AdminMenu(graph);
                } else {
                    // Display an error message
                    JOptionPane.showMessageDialog(LoginMenu.this, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(adminButton, gbc);



        this.getContentPane().add(mainPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Center the frame
        this.setVisible(true);
    }

}
