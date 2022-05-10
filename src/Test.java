import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Test {
    Client clients;

    void setUp(String name, String address, int port) {
        clients = new Client(name, address, port);
    }

    public void checkConnection(String name) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5000);
        Thread t = new Thread() {
            public void run() {
                setUp(name, "127.0.0.1", 5000);
            }
        };
        t.start();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Test test = new Test();
        Thread t = new Thread() {
            public void run() {
                System.out.println("Checking server\n");
                Server server = new Server(5000);
            }
        };
        t.start();
        Thread.sleep(1000);
        System.out.println("Testing 3 clients connected simultaneously\n");
        test.checkConnection("Eduard");
        test.checkConnection("Georgi");
        test.checkConnection("Guci");
    }
}
