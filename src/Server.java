// A Java program for a Server

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    //initialize socket and input stream
    private ServerSocket    server   = null;

    // constructor with port
    public Server(int port) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
        }
        catch(IOException i){
            System.out.println(i);
        }
            while (true) {
                Thread t = new Thread() {
                    public void run() {
                        try {
                            Socket socket;
                            socket = server.accept();
                            // takes input from the client socket
                            DataInputStream in;
                            in = new DataInputStream(
                                    new BufferedInputStream(socket.getInputStream()));
                            System.out.println(in.readUTF());
                            String line = "";

                            // reads message from client until "Over" is sent
                            while (!line.equals("Client closed")) {
                                try {
                                    line = in.readUTF();
                                    System.out.println(line);
                                } catch (IOException i) {
                                    System.out.println(i);
                                }
                            }
                            System.out.println("Closing connection");

                            // close connection
                            socket.close();
                            in.close();
                        } catch (IOException e) { }
                    }
                };
                t.start();
            }
        }
    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}