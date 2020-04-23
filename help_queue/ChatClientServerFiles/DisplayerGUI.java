import javax.swing.*;
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
    private JPanel mainPanel1;
    private JLabel roomLabel;
    private JLabel courseLabel;
    private JLabel currentTimeLabel;
    private JTextArea workStationIDTextArea;
    private JTextArea queuePositionTextArea;
    private JTextArea timeRequestMadeTextArea;
    private JTextArea estimatedWaitTimeTextArea;
    private JTextArea workStationIDValueTextArea;


    public DisplayerGUI(String title) throws IOException {
        super(title);
        Listener listener = new Listener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel1);
        this.pack();
        time();

    }


    public static void main(String[] args) throws IOException {

        JFrame frame = new DisplayerGUI("Help Queue Display");
        frame.setVisible(true);

    }

    public void time(){
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String date = new SimpleDateFormat("HH:mm.ss").format(new Date(System.currentTimeMillis()));
                currentTimeLabel.setText(date);
            }
        };
        new Timer(delay, taskPerformer).start();
    }


    private class Listener implements ActionListener {
        int counter = 0;
        long lastPressTime = System.currentTimeMillis();

        public void actionPerformed(ActionEvent e) {

                if (workStationIDTextArea.equals("test")) {

                    // getting host ip
                    InetAddress ip = null;
                    try {
                        ip = InetAddress.getByName(Host);
                    } catch (UnknownHostException unknownHostException) {
                        unknownHostException.printStackTrace();
                    }


                    // establish the connection
                    Socket s = null;
                    try {
                        s = new Socket(ip, ServerPort);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    // obtaining input and out streams
                    DataInputStream dis = null;
                    try {
                        dis = new DataInputStream(s.getInputStream());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    try {
                        workStationIDValueTextArea.setText(dis.readUTF());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                    //add dosUTF to setText


            }


        }
    }


}

