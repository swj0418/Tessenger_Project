package jeong.pro.messenger.client.gui.authentication;

import javafx.stage.PopupWindow;
import jeong.pro.messenger.client.gui.messenger.MessengerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;

public class AuthPanel extends JPanel {
    private static boolean userIsValid;
    private static Socket socket = null;

    private GridLayout gridLayout = new GridLayout();
    private JLabel idLabel = new JLabel("   Identification");
    private JLabel passLabel = new JLabel("   Password");

    private JTextField idField = new JTextField();
    private JTextField passField = new JTextField();

    private JTextArea nullArea = new JTextArea();
    private JButton loginButton = new JButton(" Login ");

    public AuthPanel() {
        gridLayout.setColumns(2);
        gridLayout.setRows(3);

        setName("Tessenger Login Module");
        setLayout(gridLayout);

        add(idLabel);
        add(idField);
        add(passLabel);
        add(passField);

        nullArea.setEditable(false);
        add(nullArea);

        loginButton.addActionListener(loginActionListener);
        add(loginButton);
    }

    public static boolean getUserIsValid() {
        return userIsValid;
    }

    public static Socket getSocket() {
        return socket;
    }

    private ActionListener loginActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Read ID and Password
            String id = idField.getText();
            String password = passField.getText();

            InetAddress inetAddress = null;
            socket = null;

            try {
                inetAddress = InetAddress.getLocalHost();
            } catch(UnknownHostException e1) {
                e1.printStackTrace();
            }

            try {
                socket =  new Socket(inetAddress, 20000);
                if(socket.isConnected()) {
                    // Send Authorization Request Packet
                    char[] userInfoOutput = new char[1024];
                    (id + password).getChars(0, 0, userInfoOutput, 0);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write((id + "," +password).getBytes());
                }
            } catch(IOException e2) {
                e2.printStackTrace();
            }

            // Listen to authentication
            try {
                char[] acceptMsg = new char[] {'1', '0', '1'};
                char[] deniedMsg = new char[] {'2', '0', '1'};
                char[] authMessage = new char[3];
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                inputStreamReader.read(authMessage);

                if(convertCharArrayToString(authMessage).equals("101")) {
                    // Authorized
                    JDesktopPane desktopPane = new JDesktopPane();
                    JOptionPane.showMessageDialog(desktopPane.getParent(), "Authorized");
                    System.out.println("Validated");
                    userIsValid = true;
                } else if(convertCharArrayToString(authMessage).equals("201")) {
                    // Unauthorized
                    JDesktopPane desktopPane = new JDesktopPane();
                    JOptionPane.showMessageDialog(desktopPane.getParent(), "Denied");
                }

                //Exit Authpanel and enter the main messenger


            } catch(IOException e3) {
                e3.printStackTrace();
            }


        }
    };

    private String convertCharArrayToString(char[] toConvert) {
        StringBuilder ret = new StringBuilder();
        for(int idx = 0; idx < toConvert.length; idx++) {
            ret.append(toConvert[idx]);
        }
        return ret.toString();
    }
}
