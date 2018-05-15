package jeong.pro.messenger.client.gui.authentication;

import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {
    private AuthPanel authPanel = new AuthPanel();
    public AuthFrame() {
        BorderLayout borderLayout = new BorderLayout();

        setName("Tessenger Login Module");
        setSize(new Dimension(250, 150));

        setLayout(borderLayout);
        add(authPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
