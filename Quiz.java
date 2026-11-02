
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz extends JFrame implements ActionListener {

    JLabel questionLabel, resultLabel;
    JRadioButton option1, option2, option3, option4;
    JButton nextButton;

    ButtonGroup optionsGroup;

    int questionNumber = 0;
    int score = 0;

    String[] questions = {
        "Which language are we using?",
        "What is 2 + 2?",
        "Which planet is known as the Red Planet?",
        "Which keyword creates a class in Java?",
        "Which company developed Java?"
    };

    String[][] options = {
        {"Python", "Java", "C++", "HTML"},
        {"3", "5", "4", "6"},
        {"Earth", "Mars", "Venus", "Jupiter"},
        {"class", "create", "newclass", "define"},
        {"Microsoft", "Apple", "Sun Microsystems", "Google"}
    };

    int[] answers = {1, 2, 1, 0, 2};

    public Quiz() {
        setTitle("Online Quiz Application");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 10, 10)
        );

        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5));

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        resultLabel = new JLabel("Question 1");
        nextButton = new JButton("Next");

        nextButton.addActionListener(this);

        bottomPanel.add(resultLabel);
        bottomPanel.add(nextButton);

        add(bottomPanel, BorderLayout.SOUTH);

        showQuestion();

        setVisible(true);
    }

    public void showQuestion() {
        questionLabel.setText(
            "Q" + (questionNumber + 1) + ". "
            + questions[questionNumber]
        );

        option1.setText(options[questionNumber][0]);
        option2.setText(options[questionNumber][1]);
        option3.setText(options[questionNumber][2]);
        option4.setText(options[questionNumber][3]);

        optionsGroup.clearSelection();

        resultLabel.setText(
            "Question " + (questionNumber + 1)
            + " of " + questions.length
        );
    }

    public void actionPerformed(ActionEvent e) {

        int selected = -1;

        if (option1.isSelected()) selected = 0;
        if (option2.isSelected()) selected = 1;
        if (option3.isSelected()) selected = 2;
        if (option4.isSelected()) selected = 3;

        if (selected == -1) {
            JOptionPane.showMessageDialog(
                this, "Please select an answer!"
            );
            return;
        }

        if (selected == answers[questionNumber]) {
            score++;
        }

        questionNumber++;

        if (questionNumber < questions.length) {
            showQuestion();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Quiz Completed!\nYour Score: "
                + score + "/" + questions.length
            );

            questionLabel.setText("Quiz Completed!");
            resultLabel.setText("Final Score: " + score);
            nextButton.setEnabled(false);

            option1.setVisible(false);
            option2.setVisible(false);
            option3.setVisible(false);
            option4.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Quiz();
    }
}
