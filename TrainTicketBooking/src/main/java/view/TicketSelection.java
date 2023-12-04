package view;

import model.Graph;
import model.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketSelection extends JFrame {

    private JTextField ticketsTextField;
    private JComboBox<String> discountDropdown;
    private JButton addToOrderButton;
    private JTextField totalTextField;
    private JButton payButton;

    private Graph graph;
    private Station start;
    private Station end;
    private double total;

    public TicketSelection(Graph graph, Station source, Station destination) {
        total = 0.0;
        this.start = source;
        this.end = destination;
        this.graph = graph;
        setTitle("Ticket Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Components
        ticketsTextField = new JTextField();
        discountDropdown = new JComboBox<>(new String[]{"No Discount", "Student Discount", "Senior Discount"});
        addToOrderButton = new JButton("Add to Order");
        totalTextField = new JTextField();
        payButton = new JButton("Pay");

        // Layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(new JLabel("Number of Tickets:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Make the text field take available horizontal space
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the available horizontal space
        mainPanel.add(ticketsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // Reset weightx for other components
        gbc.fill = GridBagConstraints.NONE; // Reset fill for other components
        mainPanel.add(new JLabel("Discount Type:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Make the combo box take available horizontal space
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the available horizontal space
        mainPanel.add(discountDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(addToOrderButton, gbc);
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrder();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Total:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Make the text field take available horizontal space
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the available horizontal space
        mainPanel.add(totalTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(payButton, gbc);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payAndExit();
            }
        });

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

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addToOrder() {
        // Add logic to handle adding tickets to the order
        // You can retrieve the values from ticketsTextField and discountDropdown
        // Update the totalTextField accordingly
        total += 2.5;
        totalTextField.setText(String.format("%.2f", total));
    }

    private void payAndExit() {
        // Add logic to handle the payment
        // Display a message and exit the application
        JOptionPane.showMessageDialog(this, "Payment successful! Exiting application.");
        System.exit(0);
    }


}
