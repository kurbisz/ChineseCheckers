package client.menu;

import javax.swing.*;
import java.awt.*;

public class ConnectMenu extends Menu {

    JPanel formPanel;

    JTextField addressField, portField;
    JButton connectButton;

    public ConnectMenu(JFrame frame) {
        super(frame);
    }

    @Override
    public void generate() {

        formPanel = new JPanel();
        formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        formPanel.setBackground(Color.CYAN);

        SpringLayout springLayout = new SpringLayout();

        addressField = new JTextField("", 15);
        formPanel.add(addressField);

        portField = new JTextField("", 15);
        formPanel.add(portField);

        formPanel.setLayout(springLayout);

        formPanel.setMaximumSize(new Dimension(600, 400));

        jFrame.add(formPanel);
        jFrame.pack();
    }

    @Override
    public void clear() {
        jFrame.remove(formPanel);
    }
}
