
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuessGUI extends JFrame {
    public final GuessAPI guessAPI = new GuessAPI();
    public JTextField inputGuessField, bestScoreTextField, guessTextField, t4;
    public JLabel displayMessageField;
    public GuessButtonListener guessButtonListener;
    public GiveUpButtonListener giveUpButtonListener;
    public PlayAgainButtonListener playAgainButtonListener;

    //setting random number in rand variable
    public GuessGUI() {
        //Get the container
        Container contentPane = getContentPane();

        //Set absolute layout
        contentPane.setLayout(null);

        //Set Background Color
        contentPane.setBackground(Color.WHITE);

        //Creating image
        JLabel imageLable = new JLabel("");
        imageLable.setIcon(new ImageIcon("images.png"));
        imageLable.setBounds(0, 0, 500, 350);

        //Creating label GuessGUI my number text
        JLabel guessMyNumberTextLabel = new JLabel("Guess my number game");
        guessMyNumberTextLabel.setForeground(Color.RED);
        guessMyNumberTextLabel.setFont(new Font("tunga", Font.BOLD, 24));
        guessMyNumberTextLabel.setSize(270, 20);
        guessMyNumberTextLabel.setLocation(300, 35);

        //Creating label Enter a number.....
        JLabel enterNumberLabel = new JLabel("Enter a number b/w 1-100");
        enterNumberLabel.setFont(new Font("tunga", Font.PLAIN, 17));
        enterNumberLabel.setSize(270, 20);
        enterNumberLabel.setLocation(300, 60);

        //Creating TextField for input guess
        inputGuessField = new JTextField(10);
        inputGuessField.setSize(50, 30);
        inputGuessField.setLocation(350, 80);

        //Creating Label for Display message
        displayMessageField = new JLabel("Try and guess my number");
        displayMessageField.setFont(new Font("tunga", Font.PLAIN, 17));
        displayMessageField.setSize(270, 20);
        displayMessageField.setLocation(290, 130);

        //Creating Text field for best score
        bestScoreTextField = new JTextField(10);
        bestScoreTextField.setSize(40, 20);
        bestScoreTextField.setLocation(10, 10);

        //Creating Label for best score
        JLabel bestScoteLabel = new JLabel("Best Score");
        bestScoteLabel.setFont(new Font("tunga", Font.PLAIN, 17));
        bestScoteLabel.setSize(270, 20);
        bestScoteLabel.setLocation(60, 10);

        //Creating guess text field
        guessTextField = new JTextField(10);
        guessTextField.setSize(40, 20);
        guessTextField.setLocation(160, 10);

        //Creating guess Label
        JLabel guessLabel = new JLabel("Guesses");
        guessLabel.setFont(new Font("tunga", Font.PLAIN, 17));
        guessLabel.setSize(270, 20);
        guessLabel.setLocation(210, 10);

        //creating 3 buttons
        JButton guessButton = new JButton("Guess");
        guessButton.setSize(150, 40);
        guessButton.setLocation(260, 250);
        guessButtonListener = new GuessButtonListener();
        guessButton.addActionListener(guessButtonListener);


        JButton giveUpButton = new JButton("Give up!");
        giveUpButton.setSize(100, 30);
        giveUpButton.setLocation(180, 200);
        giveUpButtonListener = new GiveUpButtonListener();
        giveUpButton.addActionListener(giveUpButtonListener);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setSize(120, 30);
        playAgainButton.setLocation(330, 200);
        playAgainButtonListener = new PlayAgainButtonListener();
        playAgainButton.addActionListener(playAgainButtonListener);


        //Place the components in the pane
        contentPane.add(bestScoteLabel);
        contentPane.add(displayMessageField);
        contentPane.add(imageLable);
        contentPane.add(guessMyNumberTextLabel);
        contentPane.add(enterNumberLabel);
        contentPane.add(inputGuessField);
        contentPane.add(bestScoreTextField);
        contentPane.add(guessTextField);
        contentPane.add(guessButton);
        contentPane.add(giveUpButton);
        contentPane.add(playAgainButton);
        contentPane.add(guessLabel);

        //Changing TextFields to UnEditable
        bestScoreTextField.setEditable(false);
        guessTextField.setEditable(false);

        //Set the title of the window
        setTitle("GUESS MY NUMBER");

        //Set the size of the window and display it
        setSize(550, 350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Integer.valueOf(inputGuessField.getText());
            } catch (NumberFormatException ex) {
                displayMessageField.setText("Enter a number!");
                return;
            }
            int inputValue = Integer.valueOf(inputGuessField.getText());
            int result = guessAPI.guessNumber(inputGuessField.getText());

            if (result == -1) {
                displayMessageField.setText(inputValue + " is too low. Try again!");
            }
            if (result == 1) {
                displayMessageField.setText(inputValue + " is too high. Try again!");
            }
            if(result == 2){
                displayMessageField.setText(inputValue + " is invalid. Guess number between 1..100");
            }
            if (result == 0) {
                displayMessageField.setText("Correct! You won!");
                inputGuessField.setEditable(false);
                if (guessAPI.count < guessAPI.bestScore) {
                    guessAPI.bestScore = guessAPI.count;
                    bestScoreTextField.setText(String.valueOf(guessAPI.bestScore));
                } else {
                    bestScoreTextField.setText(String.valueOf(guessAPI.bestScore));
                }
            }
            //setting focus to input guess text field
            inputGuessField.requestFocus();
            inputGuessField.selectAll();

            guessTextField.setText(String.valueOf(guessAPI.count));
        }
    }

    private class GiveUpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            guessTextField.setText("");
            displayMessageField.setText(guessAPI.random + " is the answer!");
            inputGuessField.setText("");
            inputGuessField.setEditable(false);
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            guessAPI.random = (int) (Math.random() * 100);
            inputGuessField.setText("");
            guessTextField.setText("");
            displayMessageField.setText("Try and guess my number");
            guessTextField.setText("");
            guessAPI.count = 0;
            inputGuessField.setEditable(true);
            inputGuessField.requestFocus();
        }
    }
}
