import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessGameGUI extends JFrame implements ActionListener {

    JLabel title, message, scoreLabel, attemptsLabel, historyLabel;
    JTextField guessField;
    JButton guessButton, newGameButton;
    JTextArea historyArea;
    JScrollPane scrollPane;

    Random random = new Random();

    int target;
    int score = 0;
    int attempts = 10;

    public NumberGuessGameGUI() {

        setTitle("🎮 Number Guessing Game");
        setSize(600, 500);
        setLayout(null);

        getContentPane().setBackground(new Color(30, 30, 60));

        title = new JLabel("NUMBER GUESSING GAME");
        title.setBounds(120, 20, 400, 40);
        title.setForeground(Color.YELLOW);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        message = new JLabel("Guess a number between 1 and 100");
        message.setBounds(120, 70, 350, 30);
        message.setForeground(Color.WHITE);
        message.setFont(new Font("Arial", Font.PLAIN, 16));

        guessField = new JTextField();
        guessField.setBounds(200, 110, 180, 35);
        guessField.setFont(new Font("Arial", Font.BOLD, 18));

        guessButton = new JButton("Guess");
        guessButton.setBounds(150, 160, 120, 40);
        guessButton.setBackground(Color.GREEN);
        guessButton.addActionListener(this);

        newGameButton = new JButton("New Game");
        newGameButton.setBounds(300, 160, 120, 40);
        newGameButton.setBackground(Color.CYAN);
        newGameButton.addActionListener(this);

        scoreLabel = new JLabel("Score : 0");
        scoreLabel.setBounds(120, 220, 150, 30);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        attemptsLabel = new JLabel("Attempts Left : 10");
        attemptsLabel.setBounds(300, 220, 180, 30);
        attemptsLabel.setForeground(Color.WHITE);
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        historyLabel = new JLabel("Previous Guesses");
        historyLabel.setBounds(220, 260, 200, 30);
        historyLabel.setForeground(Color.YELLOW);
        historyLabel.setFont(new Font("Arial", Font.BOLD, 18));

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        historyArea.setBackground(Color.BLACK);
        historyArea.setForeground(Color.GREEN);

        scrollPane = new JScrollPane(historyArea);
        scrollPane.setBounds(120, 300, 350, 120);

        add(title);
        add(message);
        add(guessField);
        add(guessButton);
        add(newGameButton);
        add(scoreLabel);
        add(attemptsLabel);
        add(historyLabel);
        add(scrollPane);

        startNewGame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void startNewGame() {

        target = random.nextInt(100) + 1;
        attempts = 10;

        attemptsLabel.setText("Attempts Left : 10");
        message.setText("Guess a number between 1 and 100");

        guessField.setText("");
        historyArea.setText("");

        // Uncomment to test
        // System.out.println(target);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == guessButton) {

            try {

                int guess = Integer.parseInt(guessField.getText());

                if (guess < 1 || guess > 100) {
                    message.setText("Enter a number between 1 and 100");
                    return;
                }

                attempts--;
                attemptsLabel.setText("Attempts Left : " + attempts);

                if (guess == target) {

                    historyArea.append(
                            guess + "  →  Correct ✅\n");

                    score += attempts * 10 + 10;

                    scoreLabel.setText("Score : " + score);

                    JOptionPane.showMessageDialog(
                            this,
                            "🎉 Congratulations!\nYou guessed correctly.\nNumber = "
                                    + target,
                            "Winner",
                            JOptionPane.INFORMATION_MESSAGE);

                    startNewGame();
                }

                else if (guess > target) {

                    historyArea.append(
                            guess + "  →  Too High 📈\n");

                    message.setText("📈 Too High! Try Smaller Number");
                }

                else {

                    historyArea.append(
                            guess + "  →  Too Low 📉\n");

                    message.setText("📉 Too Low! Try Bigger Number");
                }

                if (attempts == 0 && guess != target) {

                    JOptionPane.showMessageDialog(
                            this,
                            "😢 Game Over!\nCorrect Number was: "
                                    + target);

                    startNewGame();
                }

                guessField.setText("");

            } catch (NumberFormatException ex) {

                message.setText("❌ Enter Valid Number");
                guessField.setText("");
            }
        }

        if (e.getSource() == newGameButton) {
            startNewGame();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new NumberGuessGameGUI();
        });
    }
}