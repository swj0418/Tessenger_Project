package jeong.pro.messenger.client.gui.authentication;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;

public class AuthFrame extends JFrame {
    private AuthPanel authPanel = null;

    public AuthFrame() {
        BorderLayout borderLayout = new BorderLayout();

        setName("Tessenger Login Module");
        setSize(new Dimension(250, 150));

        setLayout(borderLayout);
        add(authPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AuthFrame(InetAddress serverAddress) {
        authPanel = new AuthPanel(serverAddress);
        BorderLayout borderLayout = new BorderLayout();

        setName("Tessenger Login Module");
        setSize(new Dimension(250, 150));

        setLayout(borderLayout);
        add(authPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
