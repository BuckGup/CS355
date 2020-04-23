import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayerGUI extends JFrame {
    private JPanel mainPanel1;
    private JLabel roomLabel;
    private JTextField courseLabel;
    private JTextField currentTimeLabel;
    private JTextArea workStationIDTextArea;
    private JTextArea queuePositionTextArea;
    private JTextArea timeRequestMadeTextArea;
    private JTextArea estimatedWaitTimeTextArea;
    private JTextArea workStationIDValueTextArea;

    public DisplayerGUI(String title) {
        super(title);
        Listener listener = new Listener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel1);
        this.pack();


    }

    private class Listener implements ActionListener {
        int counter = 0;
        long lastPressTime = System.currentTimeMillis();

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == workStationIDValueTextArea.getText()){


            }


        }
    }


    public static void main(String[] args){

        JFrame frame = new DisplayerGUI("Help Queue Display");
        frame.setVisible(true);



    }


}

