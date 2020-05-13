import adminGUI.DataAccessObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayerGUI extends JFrame {

    final static int ServerPort = 3050;
    final static String Host = "localhost";
    private JTextArea workStationIDValueTextArea;
    private JTextArea workStationIDTextArea;
    private DataInputStream dataInput;
    private JPanel mainPanel1;
    private JLabel roomLabel;
    private JLabel courseLabel;
    private JLabel currentTimeLabel;
    private JTextArea queuePositionTextArea;
    private JTextArea estimatedWaitTimeTextArea;
    private JTextArea queuePosArea;
    private JTextArea timeRequestArea;


    public DisplayerGUI(String title) {
        super(title);
        //Listener listener = new Listener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel1);
        this.pack();
        time();
    }

    public static void main(String[] args) throws IOException {
        DataAccessObject DAO = new DataAccessObject();

        DAO.connect();


        DisplayerGUI frame = new DisplayerGUI("Help Queue Display");
        frame.setVisible(true);

        // getting host ip
        InetAddress ip = InetAddress.getByName(Host);

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());

        //need to format what's being streamed across the network and break it into pieces to fill
        //the appropriate display GUI fields
        int counterTwo = 0;
        int counter = 1;
        int Min = 1;
        int Max = 10;
        String NA = null;
        while (true) {

            //todo swap over to a document based display to support better updating and parsing
            //working on creating a live update for the wait time
//            StringTokenizer st = new StringTokenizer(frame.estimatedWaitTimeTextArea.getText()," ");
//            //frame.estimatedWaitTimeTextArea.setText(null);
//            while (st.hasMoreTokens()) {
//
//                int waitTimeUpdate = Integer.parseInt(st.nextToken());
//                waitTimeUpdate = waitTimeUpdate + counterTwo;
//                frame.estimatedWaitTimeTextArea.append(Integer.toString(waitTimeUpdate));
//                counterTwo++;
//            }

            Min = counter;
            String PCName = dis.readUTF();

            if (PCName.contains("#delete")) {

                //frame.workStationIDValueTextArea.replaceRange(NA, 0, PCName.length());

                //we know computer name will be 11 characters long plus a space making them 12
                //delete doesn't work for all rows so I have disabled it
                counter--;
            } else {
                frame.workStationIDValueTextArea.append(PCName + "\n");
                String counterString = Integer.toString(counter);
                frame.queuePosArea.append(counterString + "\n");

                String currentTime = new SimpleDateFormat("HH:mm.ss").format(new Date(System.currentTimeMillis()));
                frame.timeRequestArea.append(currentTime + "\n");

                double waitTime = ((Math.random() * ((counter * 2) - counter)) + counter);

                waitTime = Math.round(waitTime * 1000) / 1000;

                String waitTimeInt = Double.toString(waitTime);

                frame.estimatedWaitTimeTextArea.append(waitTimeInt + " minutes" + "\n");

                counter++;
            }
        }


    }    // end - method main

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
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//
//        }
//    }


}

