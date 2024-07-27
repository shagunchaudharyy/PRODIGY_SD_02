import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame implements ActionListener {

    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private int randomNumber;
    private int attempts;

    public GuessingGame() {
        super("Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        // Initialize components
        instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(5);
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts: 0");

        // Add components to the frame
        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(feedbackLabel);
        add(attemptsLabel);

        // Add ActionListener to the guessButton
        guessButton.addActionListener(this);

        // Generate a random number
        generateRandomNumber();

        // Make the frame visible
        setVisible(true);
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;
        attemptsLabel.setText("Attempts: 0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                attemptsLabel.setText("Attempts: " + attempts);
                if (guess < randomNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (guess > randomNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("You got it! The number was " + randomNumber);
                    guessButton.setEnabled(false);
                    guessField.setEditable(false);
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Invalid input. Please enter a number.");
            }
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}