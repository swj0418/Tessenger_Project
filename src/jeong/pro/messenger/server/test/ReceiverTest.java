package jeong.pro.messenger.server.test;

import jeong.pro.messenger.server.receiver.RequestReceiver;

public class ReceiverTest {
    public ReceiverTest() {
        RequestReceiver receiver = new RequestReceiver();

        System.out.println("Hello Receiver");
    }

    public static void main(String[] ar) {
        new ReceiverTest();
    }
}
