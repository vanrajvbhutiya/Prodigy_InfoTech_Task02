import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int randomNumber;
    private JTextField textField;
    private JTextArea textArea;

    public GuessingGame() {
        setTitle("Guessing Game");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Generate random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        // Create GUI components
        textField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);

        // Add action listener to the guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter your guess: "));
        topPanel.add(textField);
        topPanel.add(guessButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void checkGuess() {
        String guessText = textField.getText();
        int guess = 0;
        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (guess < 1 || guess > 100) {
            JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (guess < randomNumber) {
            textArea.append(guess + " is too low!\n");
        } else if (guess > randomNumber) {
            textArea.append(guess + " is too high!\n");
        } else {
            textArea.append("Congratulations! " + guess + " is correct!\n");
            textField.setEditable(false);
        }
        textField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessingGame();
            }
        });
    }
}
