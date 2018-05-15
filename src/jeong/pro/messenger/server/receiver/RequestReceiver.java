package jeong.pro.messenger.server.receiver;
import jeong.pro.messenger.server.authentication.AuthenticationSessionContainer;
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
    private SocketAddress socketAddress = null;
    private ServerSocket receiverServerSocket = null;
    private InetAddress receiverInetAddress = null;
    private boolean receiverOn = false;

    public RequestReceiver() {

    }

    private void findServerLocalAddress() {
        try {
            receiverInetAddress = InetAddress.getLocalHost();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void constructServerSocket() {
        System.out.println("Receiver Socket InetAddress : " + receiverInetAddress.getHostAddress());
        System.out.println("Starting Socket Server");
        try {
            // Binding done altogether
            receiverServerSocket = new ServerSocket(20000,10, receiverInetAddress);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        findServerLocalAddress();
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
                    authorizeUser(authSocket, userInfo);
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

    private void authorizeUser(Socket authSocket, String[] userInfo) {
        if(AuthenticationSessionContainer.getAuthenticationSessionContainerInstance().getAuthorized(userInfo[0])) {
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