import adminGUI.DataAccessObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientViewerGUI extends JFrame {

    final static int ServerPort = 3050;
    final static String Host = "localhost";
    String PCName = "Unknown";
    DataAccessObject DAO = new DataAccessObject();
    private JPanel mainPanel;
    private JButton helpButton;
    private JButton cancelButton;
    private JButton lastButtonPressed;
    private JTextArea errorMessagesTextArea;
    private JTextArea questionMessage;

    public ClientViewerGUI(String title) throws IOException {
        super(title);
        Listener listener = new Listener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        helpButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
        DAO.connect();

    }

    public static void main(String args[]) throws IOException {
        ClientViewerGUI frame = new ClientViewerGUI("Help Queue Client System");
        frame.setVisible(true);
        String question = "";

    }

    private class Listener implements ActionListener {
        int counter = 0;
        long lastPressTime = System.currentTimeMillis();

        // getting host ip
        InetAddress ip = InetAddress.getByName(Host);

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        private Listener() throws IOException {
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == helpButton) {
                try {
                    InetAddress addr;
                    addr = InetAddress.getLocalHost();
                    PCName = addr.getHostName();

                    dos.writeUTF(PCName);

                    int ID = (int) (Math.random() * 10000);

                    //Database connection
                    //DAO.connect();
                    DAO.startTransaction();
                    DAO.setAutoCommit(true);


                    String currentTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

                    String Insert = "INSERT INTO HELPQUEUE VALUES ('" + PCName + "'," + ID + ", 'HELP', 'CLIENT', 'CS355', 01, TO_DATE('" + currentTime + "', 'YYYY-MM-DD HH:MI:SS'),  NULL)";
                    //System.out.println(Insert);
                    DAO.executeSQLQuery(Insert);

                    //inserts into table but shows error s

                    DAO.commit();

                    //Database End


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            } else if (e.getSource() == cancelButton) {
                try {
                    InetAddress addr;
                    addr = InetAddress.getLocalHost();
                    PCName = addr.getHostName();
                    dos.writeUTF(PCName + "#delete");

                    DAO.startTransaction();
                    DAO.setAutoCommit(true);

                    int ID = (int) (Math.random() * 10000);
                    double waitTime = (int) ((Math.random() * 10));


                    String currentTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

                    String Insert = "INSERT INTO HELPQUEUE VALUES ('" + PCName + "'," + ID + ", 'CANCEL', 'CLIENT', 'CS355', 01, TO_DATE('" + currentTime + "', 'YYYY-MM-DD HH:MI:SS'), " + waitTime + ")";

                    String Delete = "DELETE FROM HELPQUEUE WHERE PCName = '" + PCName + "'";

                    DAO.executeSQLQuery(Delete);
                    DAO.executeSQLQuery(Insert);
                    DAO.commit();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

            //add to DisplayerGUI and database
            if (lastButtonPressed == helpButton) {
                questionMessage.append("Help button already pressed: Please wait patiently\n ");
                lastButtonPressed = helpButton;
            } else if (e.getSource() == cancelButton) {

                //delete from database and remove from DisplayerGUI
                if (lastButtonPressed == cancelButton) {
                    questionMessage.append("Nothing to cancel \n ");
                }
                lastButtonPressed = cancelButton;

            }

        }


    }
}



