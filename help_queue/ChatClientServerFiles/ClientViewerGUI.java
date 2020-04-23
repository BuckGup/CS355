import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientViewerGUI extends JFrame {

    private JPanel mainPanel;
    private JButton helpButton;
    private JButton cancelButton;
    private JTextField errorMessagesTextField;
    private JTextField questionMessage;
    final static int ServerPort = 3050;
    final static String Host = "localhost";
    String question = "";

    public ClientViewerGUI(String title) {

        super(title);
        Listener listener = new Listener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        helpButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
    }

    public static void main(String args[]) throws IOException {
        JFrame frame = new ClientViewerGUI("Help Queue Client System");
        frame.setVisible(true);
        String question = "";
        serverStart();


    }



    public static void serverStart()throws UnknownHostException, IOException{
        Scanner scn = new Scanner(System.in);

        // getting host ip
        InetAddress ip = InetAddress.getByName(Host);

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        // sendMessage thread
        Thread sendMessage = new Thread(new Runnable() {
            private boolean exit = false;
            // private String name;   could new Thread(___, name) above;

            @Override
            public void run() {
                while (!exit) {

                    // read the message to deliver.
                    String msg = scn.nextLine();

                    // write on the output stream
                    try {
                        dos.writeUTF(msg);
                    } catch (IOException e) {
                        System.out.println("Client, send message section - caught exception");
                        //e.printStackTrace();
                        exit = true;
                    }
                }    // end - while
                System.out.println("Client, thread sendMessage - run(), after while loop");
            }    // end - method run
        });    // end - thread sendMessage


        // readMessage thread
        Thread readMessage = new Thread(new Runnable() {
            private boolean exit = false;
            // private String name;   could new Thread(___, name) above;

            @Override
            public void run() {
                while (!exit) {
                    try {
                        // read the message sent to this client
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        //System.out.println("Client, read message section - caught exception");
                        //e.printStackTrace();
                        exit = true;
                    }
                }    // end - while true
                System.out.println("Client, thread readMessage - run(), after while loop");
            }    // end - method run
        });    // end - thread readMessage

        sendMessage.start();
        readMessage.start();

    }    // end - method main



    private class Listener implements ActionListener {
        int counter = 0;
        long lastPressTime = System.currentTimeMillis();

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == helpButton){

               question = questionMessage.getText();


               //add to DisplayerGUI and database

            }

            else if(e.getSource() == cancelButton){

                //delete from database and remove from DisplayerGUI

            }

        }
    }

}
