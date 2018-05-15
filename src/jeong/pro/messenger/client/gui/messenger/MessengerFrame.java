package jeong.pro.messenger.client.gui.messenger;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.HashMap;

public class MessengerFrame extends JFrame {
    HashMap<String, ChatPanel> chatPanelHashMap = new HashMap<>();
    SessionPanel sessionPanel = new SessionPanel();

    BorderLayout borderLayout = new BorderLayout();

    public MessengerFrame() {

    }

    public MessengerFrame(Socket socket) {
        setSize(new Dimension(1600, 800));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(1920, 1200));
        setLayout(borderLayout);

        add(sessionPanel, BorderLayout.WEST);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
