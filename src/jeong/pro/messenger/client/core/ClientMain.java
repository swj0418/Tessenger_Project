package jeong.pro.messenger.client.core;

import jeong.pro.messenger.client.gui.authentication.AuthFrame;
import jeong.pro.messenger.client.gui.authentication.AuthPanel;
import jeong.pro.messenger.client.gui.messenger.MessengerFrame;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientMain {
    public static AuthFrame authFrame = null;
    public static MessengerFrame messengerFrame = null;
    private String serverExternalIP = "";
    private InetAddress serverAddress = null;

    public ClientMain() {
        System.out.println("=================== Tessenger Client =====================");
        System.out.println("Version : 0.0.1");
        System.out.println("Author : Sangwon Jeong");

        invokeAuthentication();
        listenToauthorization();
    }

    ClientMain(String serverExternalIP) {
        this.serverExternalIP = serverExternalIP;

        System.out.println("=================== Tessenger Client =====================");
        System.out.println("Version : 0.0.1");
        System.out.println("Author : Sangwon Jeong");

        try {
            this.serverAddress = InetAddress.getByName(this.serverExternalIP);
        } catch(UnknownHostException e) {
            e.printStackTrace();
        }

        invokeAuthentication();
        listenToauthorization();
    }

    private void invokeAuthentication() {
        authFrame = new AuthFrame(this.serverAddress);
    }
    private void listenToauthorization() {
        Thread authorizationListner = new Thread(new AuthorizationListener());
        try {
            authorizationListner.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] ar) {
        new ClientMain("175.192.128.143");
    }
}

class AuthorizationListener implements Runnable {
    boolean authorized = false;
    @Override
    public void run() {
        boolean isLive = true;
        while(isLive) {
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            if(AuthPanel.getUserIsValid()) {
                authorized = true;
                ClientMain.messengerFrame = new MessengerFrame(AuthPanel.getSocket());
                isLive = false;
            }
        }
    }
}
