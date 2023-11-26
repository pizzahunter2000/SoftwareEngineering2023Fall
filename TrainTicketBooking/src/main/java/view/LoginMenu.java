package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame {

    private JButton userButton, adminButton;
    private JTextField passwordField;

    public LoginMenu() {
        // Set up the frame
        setTitle("Login Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create components
        JLabel titleLabel = new JLabel("Login Menu", SwingConstants.CENTER);
        userButton = new JButton("User");
        adminButton = new JButton("Admin");
        passwordField = new JTextField();

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(titleLabel, BorderLayout.NORTH);
        add(userButton, BorderLayout.WEST);
        add(adminButton, BorderLayout.EAST);
        add(passwordField, BorderLayout.SOUTH);

        // Add action listeners to buttons
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login("user");
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login("admin");
            }
        });
    }

    private void login(String userType) {
        String enteredPassword = passwordField.getText();
        String correctPassword = "admin";

        if (enteredPassword.equals(correctPassword)) {
            JOptionPane.showMessageDialog(this, "Login successful as " + userType);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect password. Please try again.");
        }
    }

}
