// A Java program for a Client

import java.net.*;
import java.io.*;
public class Client
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    public Client(String name, String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input  = new DataInputStream(System.in);

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Client " + name + " connected");
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (true) {
            try {
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    //close the connection
                    try
                    {
                        out.writeUTF("Client closed");
                        socket.close();
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                }));
                line = input.readLine();
                out.writeUTF(name + ":" + line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }
    }
    public Socket getSocket() {
        return this.socket;
    }

    public static void main(String[] args)
    {
        String clientName = args[0];
        Client client = new Client(clientName, "127.0.0.1", 5000);
    }
}
