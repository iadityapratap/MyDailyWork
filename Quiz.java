import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class Quiz {
    private static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3),
        new Question("What is 2 + 2?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 2),
        new Question("Who wrote 'Hamlet'?", new String[]{"1. Charles Dickens", "2. J.K. Rowling", "3. William Shakespeare", "4. Mark Twain"}, 3)
    };
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static boolean answered = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            displayQuestion(question);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime's up!");
                        nextQuestion();
                    }
                }
            }, 10000); // 10 seconds timer

            int answer = scanner.nextInt();
            answered = true;
            timer.cancel();

            if (answer == question.correctAnswer) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }

            nextQuestion();
        }

        displayResults();
        scanner.close();
    }

    private static void displayQuestion(Question question) {
        System.out.println(question.question);
        for (String option : question.options) {
            System.out.println(option);
        }
        System.out.print("Your answer (1-4): ");
        answered = false;
    }

    private static void nextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            System.out.println("\nNext question:");
        }
    }

    private static void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.length);
        System.out.println("Summary of correct/incorrect answers:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println((i + 1) + ". " + questions[i].question + " - " + (i < score ? "Correct" : "Incorrect"));
        }
    }
}
