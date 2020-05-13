package adminGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class adminsGUI extends JFrame {
    final static int ServerPort = 3050;
    final static String Host = "localhost";
    DataAccessObject DAO = new DataAccessObject();
    private JButton adminCancelButton;
    private JPanel adminPanel;
    private JButton reinitializeQueueButton;
    private JTextField wsToDelete;
    private JLabel classLabel;



    public adminsGUI(String title) throws IOException {

        super(title);
        Listener listener = new Listener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(adminPanel);
        this.pack();
        adminCancelButton.addActionListener(listener);
        reinitializeQueueButton.addActionListener(listener);
        DAO.connect();


    }

    public static void main(String args[]) throws IOException {

        adminsGUI frame = new adminsGUI("Help Queue Administrator System");
        frame.setVisible(true);

        // getting host ip
        InetAddress ip = InetAddress.getByName(Host);

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());


        // getting time
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        char hourOne = date.charAt(11);
        char hourTwo = date.charAt(12);
        String realHour = String.valueOf(hourOne);
        realHour = realHour + hourTwo;
        int finalTime = Integer.parseInt(realHour);


        if (finalTime <= 14 && finalTime >= 12) {
            frame.classLabel.setText("CS315-01");
        } else if (finalTime <= 16 && finalTime >= 15) {
            frame.classLabel.setText("CS315-02");
        } else {
            frame.classLabel.setText("No Current Course");
        }

    }

    public class Listener implements ActionListener {
        private Listener() throws IOException {
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == reinitializeQueueButton) {

                //todo implement this
                //take all current help requests and write to the database
                //need to parse all requests and send

            } else if (e.getSource() == adminCancelButton) {

                String delete = wsToDelete.getText();
                //delete statement
                String wsDelete = "DELETE FROM HELPQUEUE WHERE PCName = '"+delete+"'";
                DAO.startTransaction();
                DAO.setAutoCommit(true);
                DAO.executeSQLQuery(wsDelete);
                DAO.commit();

            }


        }
    }


}

