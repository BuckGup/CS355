import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientViewerGUI extends JFrame {

    final static int ServerPort = 3050;
    final static String Host = "localhost";
    String question = "";
    private JPanel mainPanel;
    private JButton helpButton;
    private JButton cancelButton;
    private JTextField errorMessagesTextField;
    private JTextField questionMessage;

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

    }


    private class Listener implements ActionListener {
        int counter = 0;
        long lastPressTime = System.currentTimeMillis();

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == helpButton) {

                question = questionMessage.getText();

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

                try {
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    dos.writeUTF(question);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                //add to DisplayerGUI and database

            } else if (e.getSource() == cancelButton) {

                //delete from database and remove from DisplayerGUI

            }

        }
    }


}
