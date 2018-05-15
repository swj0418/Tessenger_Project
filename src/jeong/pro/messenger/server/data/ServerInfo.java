package jeong.pro.messenger.server.data;

import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class ServerInfo {
    private InetAddress serverAddress = null;
    private int port = 20000;

    private static ServerInfo serverInfo = new ServerInfo();
    private ServerInfo() {
        try {
            this.serverAddress = InetAddress.getLocalHost();
            //this.serverAddress = InetAddress.getByName("localhost"); // Testing environment
        } catch(UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static ServerInfo getServerInfo() {
        return serverInfo;
    }

    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return port;
    }
}
