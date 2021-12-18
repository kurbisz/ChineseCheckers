package client.graphics.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InformationPanel extends Panel {

    JLabel message;

    /**
     * Responsible for information rectangle and text
     * in left bottom corner (mainly handles and
     * shows information from server).
     * @param frame
     */
    public InformationPanel(JFrame frame) {
        super(frame);
    }

    /**
     * Create text 'Information' and rectangle for
     * incoming information. Sets graphic properties.
     */
    @Override
    public void initialize() {

        this.setLayout(new GridLayout(0, 1));

        JLabel infoLabel = new JLabel("Information");
        infoLabel.setAlignmentY(BOTTOM_ALIGNMENT);
        infoLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        infoLabel.setForeground(Color.RED);
        this.add(infoLabel);

        message = new JLabel("");
        LineBorder border = new LineBorder(Color.RED, 3, true);
        message.setBorder(border);
        this.add(message);

        this.setBounds((int) (0.05*jFrame.getWidth()), (int) (0.75*jFrame.getHeight()),
                (int) (0.15*jFrame.getWidth()), (int) (0.1*jFrame.getHeight()));

    }

    /**
     * Sets message which has to be shown in this box.
     * @param infoMsg message which has to be displayed
     */
    public void setMessage(String infoMsg) {
        message.setText(infoMsg);
    }

}
