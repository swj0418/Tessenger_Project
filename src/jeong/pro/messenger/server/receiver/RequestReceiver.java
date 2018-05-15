package jeong.pro.messenger.server.receiver;
import jeong.pro.messenger.server.authentication.Authenticator;
import jeong.pro.messenger.server.data.ServerInfo;

import java.io.*;
import java.net.*;

public class RequestReceiver implements Runnable{
    /**
     * Request Receiver:
     *
     * Responsible for receiving incoming socket connection requests.
     * Connection will be redirected to "Authentication"
     * Accepted socket connection requests will be passed onto a "Session"
     */

    private ServerSocket receiverServerSocket = null;

    private void constructServerSocket() {
        System.out.println("Receiver Socket InetAddress : " + ServerInfo.getServerInfo().getServerAddress().getHostAddress());
        System.out.println("Starting Socket Server");
        try {
            // Binding done altogether
            receiverServerSocket = new ServerSocket(20000,10, ServerInfo.getServerInfo().getServerAddress());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        constructServerSocket();

        while(true) { // Must be broken down into smaller methods
            try {
                Socket authSocket = receiverServerSocket.accept(); // This authSocket will only live through one iteration.
                if(authSocket.isConnected()) {
                    char[] message = new char[1024];
                    InputStreamReader reader = new InputStreamReader(authSocket.getInputStream());
                    reader.read(message, 0, 1024);

                    String[] userInfo = extractUserInfo(message);

                    printIncomingUserInfo(authSocket, userInfo);
                    createAuthenticationSession(userInfo);
                    giveAuthorization(authSocket, userInfo);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printIncomingUserInfo(Socket authSocket, String[] userInfo) {
        System.out.println("Incoming request from " + authSocket.getLocalAddress() + " at port "
                + authSocket.getPort() + " User ID : " + userInfo[0] + " With password : " + userInfo[1]);
    }

    private void giveAuthorization(Socket authSocket, String[] userInfo) {
        try {
            if(Authenticator.authorizedUsers.get(userInfo[0])) {
                // If authorized, Shoot authorized message and redirect user to a Session. User Information
                // transfer will be conducted in that session
                try {
                    OutputStream outputStream  = authSocket.getOutputStream();
                    outputStream.write("101".getBytes()); // 101 for Acception

                    // Create a LiveSession

                } catch(IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    OutputStream outputStream  = authSocket.getOutputStream();
                    outputStream.write("201".getBytes()); // 201 for Decline
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        } catch(NullPointerException e) {

        }
    }

    private String[] extractUserInfo(char[] rawMessage) {
        StringBuilder strBuilder = new StringBuilder();
        for(int msgIdx = 0; msgIdx < rawMessage.length; msgIdx++) {
            strBuilder.append(rawMessage[msgIdx]);
        }
        String userInfoString = strBuilder.toString();

        // Separate by ','
        return userInfoString.trim().split(",");
    }

    private void createAuthenticationSession(String[] userInfo) {
        Thread authenticatorThread = new Thread(new Authenticator(userInfo));
        authenticatorThread.start();
    }
}