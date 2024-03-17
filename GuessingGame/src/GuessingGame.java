import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GuessingGame implements ActionListener{
    private final int MAX_ATTEMPTS=5;
    private int secretNumber;
    private int attemptsLeft;
    private int score;
    private Timer timer;
    private JLabel attemptsLabel;
    private JLabel hintLabel;
    private JTextField guessField;
    private JLabel scoreLabe;
    private JLabel timeLabel;
    private int countdown=30;

    public GuessingGame(){
        JFrame f = new JFrame("Guessing Game");
        f.setSize(300, 380);
        
    }
}