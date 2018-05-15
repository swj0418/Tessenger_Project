package jeong.pro.messenger.server.core;

import jeong.pro.messenger.server.receiver.RequestReceiver;

public class MessengerServer {
    /**
     * Messenger Server Core.
     *
     * Messenger server is split into 6 components
     *
     *  1. Core : Serves as the starting point and the end point of the server
     *  2. ServerInfo : This is a small component where I will store server-related meta information
     *  3. Receiver : This component, which is an independent thread, listens to connection requests.
     *  4. Session : In a messenger service, clients from other computers must enter a session of their own to
     *               exchange information. These session instances serves for that purpose.
     *  5. Data : Every session has a state "Live & Dead". Live means that one of several clients those are
     *            in the session has activated the connection, or has sent a message. Since this messenger will be
     *            working asymmetrically. Data storage for a session is required.
     *  6. Authentication : User-based session storing seems to be easier and reasonable than ip-based. Of course.
     *                      This component will check if the userinfodata is valid, and if valid, it will send (sync)
     *                      all the user data and previous session data to display in his/her client
     *
     *  @Author: Sangwon Jeong
     */

    public MessengerServer() {
        Thread requestReceiverThread = new Thread(new RequestReceiver());

        requestReceiverThread.run();


        try {
            requestReceiverThread.join();
        } catch(InterruptedException e) {

        }

    }

    public static void main(String[] ar) {
        System.out.println("=========================== TESSENGER ===========================");
        System.out.println("Author : Sangwon Jeong");
        System.out.println("Version : 0.0.1");

        MessengerServer messengerServer = new MessengerServer();
    }
}
