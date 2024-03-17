import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class GuessingGame implements ActionListener{
    private final int MAX_ATTEMPTS=10;
    private int secretNumber;
    private int attemptsLeft;
    private int score;
    private javax.swing.Timer timer;
    private JLabel attemptsLabel;
    private JLabel hintLabel;
    private JTextField guessField;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private int countdown=30;

    public GuessingGame(){
        JFrame f = new JFrame("Guessing Game");
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.CYAN);

        secretNumber= new Random().nextInt(100)+1;
        attemptsLeft=MAX_ATTEMPTS;
        score=0;

        JLabel titleLable = new JLabel("Guess the Number Game");
        titleLable.setBounds(50,10,200,20);
        titleLable.setForeground(Color.BLUE);
        f.add(titleLable);

        attemptsLabel = new JLabel("Attempts Left: "+attemptsLeft);
        attemptsLabel.setBounds(100, 60, 200, 20);
        f.add(attemptsLabel);

        hintLabel = new JLabel("Enter your guess between 1 to 100");
        hintLabel.setBounds(50, 90, 280, 20);
        f.add(hintLabel);

        guessField = new JTextField();
        guessField.setBounds(50, 120, 200, 20);
        f.add(guessField);

        scoreLabel = new JLabel("Score: "+score);
        scoreLabel.setBounds(210, 60, 200, 20);
        f.add(scoreLabel);

        timeLabel = new JLabel("Time Left: "+countdown);
        timeLabel.setBounds(10,60,200,20);
        f.add(timeLabel);

       timer = new javax.swing.Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e){
            countdown--;
            timeLabel.setText("Time Left: "+countdown);
            if(countdown<=0){
                timer.stop();
                JOptionPane.showMessageDialog(null, "Time's up! The number was " + secretNumber + ".", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                score=0;
                resetGame();
            }
        }
       });

       JButton guessButton= new JButton("Guess");
       guessButton.setBounds(100, 190, 80, 30);
       guessButton.setForeground(Color.BLUE);
       guessButton.addActionListener(this);
       f.add(guessButton);

       f.setLocationRelativeTo(null);
       f.setVisible(true);
       timer.start();
 }

public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("Guess")){
        try{
            int guess = Integer.parseInt(guessField.getText());
            checkGuess(guess);
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     }
     private void checkGuess(int guess){
        if(guess==secretNumber){
            JOptionPane.showMessageDialog(null, "Congratulations! Your guess is right", "Congratulation!", JOptionPane.INFORMATION_MESSAGE);
            score +=10;
            displayScore();
            continueGame();
        }
        else if(attemptsLeft==0){
            score=0;
            JOptionPane.showMessageDialog(null, "Sorry, you ran out of attempts. The number was " + secretNumber + ".", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            displayScore();
            resetGame();
        }
        else{
            attemptsLeft--;
            String hint = guess > secretNumber ? "Your guess is Higher" : "Your guess is Lower";
            hintLabel.setText(hint );
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
        }
     }

     private void resetGame(){
        score=0;
        secretNumber = new Random().nextInt(100)+1;
        attemptsLeft=MAX_ATTEMPTS;
        attemptsLabel.setText("Attempts Left: "+attemptsLeft);
        hintLabel.setText("Enter your guess: ");
        guessField.setText("");
        countdown=30;
        timer.restart();
     }

     private void continueGame(){
        secretNumber = new Random().nextInt(100)+1;
        hintLabel.setText("Enter your guess:");
        guessField.setText("");
        countdown = 30;
        timer.restart();
   }

   private void displayScore(){
    scoreLabel.setText("Score: "+score);
   }
        public static void main(String args[]){
            SwingUtilities.invokeLater(GuessingGame::new);
         }


    }
