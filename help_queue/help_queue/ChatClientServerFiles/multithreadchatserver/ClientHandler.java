/*
 * ClientHandler - Java implementation of ClientHandler class under Server class for multi-threaded chat server
 *
 * from https://www.geeksforgeeks.org/multi-threaded-chat-application-set-1/
 */
package multithreadchatserver;

import java.io.*;
import java.util.*;
import java.net.*;

//ClientHandler class 
class ClientHandler implements Runnable {
    final DataInputStream dis;                        // input stream for this client
    final DataOutputStream dos;                    // output stream for this client
    // data
    //Scanner scn = new Scanner(System.in); 		// ???
    //private String name; 							// client name
    String name;                                    // client name
    Socket s;                                        // socket for this client
    boolean isloggedin;                            // flag, whether client is currently connected

    // constructor
    public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin = true;

    }    // end - constructor

    // run method - called when thread starts
    @Override
    public void run() {
        String received;
        while (true) {
            try {
                // receive the string
                received = dis.readUTF();
                System.out.println(received);

                // TODO: need to fully handle thread disconnecting; remove from server list
                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this.s.close();
                    break;
                }

                // search for the recipient in the connected devices list from global Server class.
                // ar is the vector storing client of active users
                // TODO: this search could be slow if large number of clients - could use HashMap

                for (ClientHandler mc : Server.ar) {
                    mc.dos.writeUTF(received);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }    // end - while true

        // closing resources
        try {
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            System.out.println("ClientHandler, closing resources section");
            e.printStackTrace();
        }
    }    // end - method run

}    // end - class ClientHandler
