package jeong.pro.messenger.server.data;

import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerInfo {
    private static final String SERVER_IP = "175.192.128.143";
    private static final String SERVER_PORT = "20000";
    private static final char[] SERVER_IP_CHAR = new char[] {'1', '7', '5', '1', '9', '2', '1', '2', '8', '1', '4', '3'};

    public static char[] getSERVER_IP_CHAR() {
        return SERVER_IP_CHAR;
    }

    public static String getServerIp() {
        return SERVER_IP;
    }

    public static String getServerPort() {
        return SERVER_PORT;
    }
}
