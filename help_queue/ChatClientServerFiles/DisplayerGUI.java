import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DisplayerGUI extends JFrame {

    final static int ServerPort = 3050;
    final static String Host = "localhost";
    public JTextArea workStationIDValueTextArea;
    public JTextArea workStationIDTextArea;
    private DataInputStream dataInput;
    private JPanel mainPanel1;
    private JLabel roomLabel;
    private JLabel courseLabel;
    private JLabel currentTimeLabel;
    private JTextArea queuePositionTextArea;
    private JTextArea timeRequestMadeTextArea;
    private JTextArea estimatedWaitTimeTextArea;


    public DisplayerGUI(String title) {
        super(title);
        //  Listener listener = new Listener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel1);
        this.pack();
        time();
        //dataInput = startServer();
        //updatePanels(dataInput);


    }

    public static void main(String[] args) throws IOException {

        DisplayerGUI frame = new DisplayerGUI("Help Queue Display");
        frame.setVisible(true);


        // getting host ip
        InetAddress ip = InetAddress.getByName(Host);

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());


        System.out.println("Before");
        frame.workStationIDTextArea.append(dis.readUTF());
        System.out.println("After");


    }    // end - method main

    public static DataInputStream startServer() throws IOException {
        InetAddress ip = null;
        ip = InetAddress.getByName(Host);

        // establish the connection
        Socket s = null;
        s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = null;
        dis = new DataInputStream(s.getInputStream());

        return dis;

    }

    public void setWorkStationIDTextArea(String workStationText) {
        this.workStationIDTextArea.setText(workStationText);
    }

    public void time() {
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String date = new SimpleDateFormat("HH:mm.ss").format(new Date(System.currentTimeMillis()));
                currentTimeLabel.setText(date);
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    public void updatePanels(DataInputStream dataStream) throws IOException {

        Thread readMessage = new Thread(new Runnable() {
            private boolean exit = false;
            // private String name;   could new Thread(___, name) above;

            @Override
            public void run() {
                while (!exit) {

                    // write on the output stream
                    try {
                        String data = dataStream.readUTF();
                        workStationIDValueTextArea.append(data);
                        System.out.println("It works");

                    } catch (IOException e) {
                        System.out.println("Client, send message section - caught exception");
                        //e.printStackTrace();
                        exit = true;
                    }
                }    // end - while
                System.out.println("Client, thread sendMessage - run(), after while loop");
            }    // end - method run
        });    // end - thread sendMessage
    }


//    private class Listener implements ActionListener {
//        int counter = 0;
//        long lastPressTime = System.currentTimeMillis();
//
//        public void actionPerformed(ActionEvent e) {
//
//            try {
//                if (dataInput.readUTF() != null) {
//
//                    System.out.println("Action Performed");
//                    try {
//                        String testString = dataInput.readUTF();
//                        workStationIDTextArea.setText(testString);
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//
//
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//
//
//        }
//    }


}

