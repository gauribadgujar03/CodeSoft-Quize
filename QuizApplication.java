import java.util.Scanner;

public class QuizApplication {
    private static final int QUIZ_TIME_SECONDS = 20;
    private static final int NUM_QUESTIONS = 5;
    private static final String[] QUESTIONS = {
        "What is the capital of France?",
        "Who wrote 'Romeo and Juliet'?",
        "What is the chemical symbol for water?",
        "What year did World War II end?",
        "What is the largest planet in our solar system?"
    };
    private static final String[][] OPTIONS = {
        {"A) Paris", "B) Rome", "C) Berlin", "D) London"},
        {"A) William Shakespeare", "B) Charles Dickens", "C) Jane Austen", "D) Mark Twain"},
        {"A) H2O", "B) CO2", "C) NaCl", "D) O2"},
        {"A) 1943", "B) 1944", "C) 1945", "D) 1946"},
        {"A) Mars", "B) Saturn", "C) Jupiter", "D) Earth"}
    };
    private static final String[] ANSWERS = {
        "A",
        "A",
        "A",
        "C",
        "C"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        int correctAnswers = 0;
        int incorrectAnswers = 0;

        System.out.println("Welcome to the Quiz! Each question has " + QUIZ_TIME_SECONDS + " seconds to answer.");

        for (int i = 0; i < NUM_QUESTIONS; i++) {
            final int questionNumber = i;
            System.out.println("\nQuestion " + (questionNumber + 1) + ": " + QUESTIONS[questionNumber]);
            for (String option : OPTIONS[questionNumber]) {
                System.out.println(option);
            }

            // Start timer for the current question
            Thread timerThread = new Thread(() -> {
                try {
                    Thread.sleep(QUIZ_TIME_SECONDS * 1000);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("\nTime's up for question " + (questionNumber + 1) + "!");
                System.out.println("Moving to the next question...");
                System.out.println("Press Enter to continue.");
            });
            timerThread.start();

            // Get user's answer
            System.out.print("Enter your answer: ");
            String answer = scanner.nextLine().trim().toUpperCase();

            // Stop timer
            timerThread.interrupt();

            // Check answer
            if (answer.equals(ANSWERS[questionNumber])) {
                System.out.println("Correct!");
                totalScore++;
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + ANSWERS[questionNumber]);
                incorrectAnswers++;
            }
        }

        // Display final score and summary
        System.out.println("\nQuiz finished!");
        System.out.println("Your score is: " + totalScore + "/" + NUM_QUESTIONS);
        System.out.println("Summary:");
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Incorrect answers: " + incorrectAnswers);

        scanner.close();
    }
}
