package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame{
    public AdminMenu () {
        setTitle("Admin Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton myButton = new JButton("Click Me");
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminMenu.this, "Button Clicked!");
            }
        });
        mainPanel.add(myButton, gbc);

        getContentPane().add(mainPanel);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
