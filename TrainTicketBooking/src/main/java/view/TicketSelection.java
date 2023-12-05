package view;

import model.DiscountList;
import model.Graph;
import model.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketSelection extends JFrame {

    private JTextField ticketsTextField;

    private JLabel priceLabel; // New JLabel for displaying the price of one normal ticket
    private JComboBox<String> discountDropdown;
    private JButton addToOrderButton;
    private JTextField totalTextField;
    private JButton payButton;

    private Graph graph;
    private Station start;
    private Station end;
    private double total;

    private double pricePerKM ;

    public TicketSelection(Graph graph, Station source, Station destination) {
        total = 0.0;
        pricePerKM =  1.0;
        this.start = source;
        this.end = destination;
        this.graph = graph;
        setTitle("Ticket Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Components
        ticketsTextField = new JTextField();
        discountDropdown = new JComboBox<>(graph.getDiscountList().getDiscounts().keySet().toArray(new String[0]));
        discountDropdown.setRenderer(new DiscountListCellRenderer(graph.getDiscountList()));
        addToOrderButton = new JButton("Add to Order");
        totalTextField = new JTextField();
        payButton = new JButton("Pay");

        // Layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth=2;
        gbc.insets = new Insets(10, 10, 10, 10);

        priceLabel = new JLabel(String.format("Price of one normal ticket for the selected trip: $%.2f", pricePerKM * graph.calculateTripDistance(start, end))); // Initialize the label
        // Add the price label at the top
        mainPanel.add(priceLabel, gbc);

        gbc.gridwidth=1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        mainPanel.add(new JLabel("Number of Tickets:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Make the text field take available horizontal space
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the available horizontal space
        mainPanel.add(ticketsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // Reset weightx for other components
        gbc.fill = GridBagConstraints.NONE; // Reset fill for other components
        mainPanel.add(new JLabel("Discount Type:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Make the combo box take available horizontal space
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the available horizontal space
        mainPanel.add(discountDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(addToOrderButton, gbc);
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDicount = (String) discountDropdown.getSelectedItem();
                String amountString = ticketsTextField.getText();
                int amount = Integer.parseInt(amountString);
                addToOrder(amount, selectedDicount, start, end);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Total:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Make the text field take available horizontal space
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the available horizontal space
        mainPanel.add(totalTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
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

    private void addToOrder(int amount, String discount, Station start, Station end) {
        // Add logic to handle adding tickets to the order
        // You can retrieve the values from ticketsTextField and discountDropdown
        // Update the totalTextField accordingly
        total += amount * graph.calculatePrice(start, end,  discount, pricePerKM,  graph.getDiscountList());
        totalTextField.setText(String.format("%.2f", total));
    }

    private void payAndExit() {
        // Add logic to handle the payment
        // Display a message and exit the application
        JOptionPane.showMessageDialog(this, "Payment successful! Exiting application.");
        System.exit(0);
    }

    class DiscountListCellRenderer extends DefaultListCellRenderer {
        private DiscountList discountList;

        public DiscountListCellRenderer(DiscountList discountList) {
            this.discountList = discountList;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof String) {
                String discountName = (String) value;
                Double discountAmount = discountList.getDiscount(discountName);

                // Display both name and amount in the dropdown
                value = discountName + " - " + discountAmount + " %";
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }


}
