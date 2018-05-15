package jeong.pro.messenger.client.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionTest {
    ConnectionTest(){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        }

        Socket socket;
        try {
            socket = new Socket(inetAddress, 20000);
            socket.getOutputStream().write("admin".getBytes());
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] ar) {
        new ConnectionTest();
    }
}
