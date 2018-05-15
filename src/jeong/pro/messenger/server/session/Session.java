package jeong.pro.messenger.server.session;

import java.net.Socket;

public interface Session extends Runnable{
    void setSocket(Socket socket);
    
}
