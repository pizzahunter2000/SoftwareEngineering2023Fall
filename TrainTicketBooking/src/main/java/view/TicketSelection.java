package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketSelection extends JFrame {

    private JTextField ticketsTextField;
    private JComboBox<String> discountDropdown;
    private JButton addToOrderButton;
    private JTextField totalTextField;
    private JButton payButton;

    public TicketSelection() {
        setTitle("Ticket Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // Components
        ticketsTextField = new JTextField();
        discountDropdown = new JComboBox<>(new String[]{"No Discount", "Student Discount", "Senior Discount"});
        addToOrderButton = new JButton("Add to Order");
        totalTextField = new JTextField();
        payButton = new JButton("Pay");

        // Layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Number of Tickets:"));
        add(ticketsTextField);

        add(new JLabel("Discount Type:"));
        add(discountDropdown);

        add(addToOrderButton);
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrder();
            }
        });

        add(new JLabel("Total:"));
        add(totalTextField);

        add(payButton);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payAndExit();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addToOrder() {
        // Add logic to handle adding tickets to the order
        // You can retrieve the values from ticketsTextField and discountDropdown
        // Update the totalTextField accordingly
    }

    private void payAndExit() {
        // Add logic to handle the payment
        // Display a message and exit the application
        JOptionPane.showMessageDialog(this, "Payment successful! Exiting application.");
        System.exit(0);
    }

}
