import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DisplayerGUI extends JFrame {

    private JPanel mainPanel1;
    private JLabel roomLabel;
    private JLabel courseLabel;
    private JLabel currentTimeLabel;
    private JTextArea workStationIDTextArea;
    private JTextArea queuePositionTextArea;
    private JTextArea timeRequestMadeTextArea;
    private JTextArea estimatedWaitTimeTextArea;

    public DisplayerGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel1);
        this.pack();
        time();
    }

    public static void main(String[] args) {
        JFrame frame = new DisplayerGUI("Help Queue Display");
        frame.setVisible(true);

    }

    public void time(){
       // old way
    /*   Calendar cal = new GregorianCalendar();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        currentTimeLabel.setText(hour+":" + minute + ":" +second);
    */
        // New way, updates every second

        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String date = new java.text.SimpleDateFormat("HH:mm.ss").format(new java.util.Date(System.currentTimeMillis()));
                currentTimeLabel.setText(date);
            }
        };
        new Timer(delay, taskPerformer).start();
    }



}