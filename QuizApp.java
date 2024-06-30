import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizApp {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private Scanner scanner;

    public QuizApp() {
        this.questions = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
        this.scanner = new Scanner(System.in);
    }

    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    public void startQuiz() {
        System.out.println("Welcome to the Chemistry Quiz!\n");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Quiz ended.");
                displayResult();
                System.exit(0);
            }
        }, 60000); // 60 seconds timer

        for (QuizQuestion question : questions) {
            presentQuestion(question);
        }

        displayResult();
    }

    private void presentQuestion(QuizQuestion question) {
        System.out.println("Question: " + question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        System.out.print("Enter your choice (1-" + options.size() + "): ");
        int userChoice = scanner.nextInt();

        if (userChoice > 0 && userChoice <= options.size()) {
            if (userChoice - 1 == question.getCorrectAnswerIndex()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }
        } else {
            System.out.println("Invalid choice. Skipping question.");
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz Summary:");
        System.out.println("Total Questions: " + questions.size());
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.size() - score));
    }

    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();

        // Sample quiz questions on chemistry
        QuizQuestion question1 = new QuizQuestion("Which of the following is a chemical change?",
                List.of("Melting of ice", "Burning of paper", "Cutting of wood", "Dissolving salt in water"), 1);
        QuizQuestion question2 = new QuizQuestion("What is the chemical formula of water?",
                List.of("H2O", "CO2", "NaCl", "C6H12O6"), 0);
        QuizQuestion question3 = new QuizQuestion("What happens when iron is exposed to moist air for a long time?",
                List.of("It melts", "It tarnishes", "It evaporates", "It dissolves"), 1);
        QuizQuestion question4 = new QuizQuestion("Which of the following is a non-metal?",
                List.of("Sodium (Na)", "Carbon (C)", "Iron (Fe)", "Calcium (Ca)"), 1);
        QuizQuestion question5 = new QuizQuestion("What is the process called when a solid changes directly into a gas without passing through the liquid state?",
                List.of("Sublimation", "Condensation", "Evaporation", "Fusion"), 0);

        // Adding questions to quiz
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        quiz.addQuestion(question3);
        quiz.addQuestion(question4);
        quiz.addQuestion(question5);

        // Starting the quiz
        quiz.startQuiz();
    }
}
