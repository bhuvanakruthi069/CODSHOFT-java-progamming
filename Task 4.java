//TASK 4//
import java.util.*;
import java.util.concurrent.TimeUnit;

public class QuizApp {

    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public boolean checkAnswer(int userAnswerIndex) {
            return userAnswerIndex == correctAnswerIndex;
        }
    }

    static class Quiz {
        List<Question> questions;
        int score;
        int totalQuestions;

        public Quiz(List<Question> questions) {
            this.questions = questions;
            this.score = 0;
            this.totalQuestions = questions.size();
        }

        public void startQuiz() {
            Scanner scanner = new Scanner(System.in);
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                System.out.println("Question " + (i + 1) + ": " + question.questionText);
                for (int j = 0; j < question.options.length; j++) {
                    System.out.println((j + 1) + ". " + question.options[j]);
                }

                System.out.println("You have 10 seconds to answer...");

                long elapsedTime = 0;
                int userAnswerIndex = -1;

                while (elapsedTime < 10000) {
                    if (scanner.hasNextInt()) {
                        userAnswerIndex = scanner.nextInt() - 1;
                        break;
                    }
                    elapsedTime = System.currentTimeMillis() - startTime;
                }

                if (userAnswerIndex >= 0 && question.checkAnswer(userAnswerIndex)) {
                    score++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect! The correct answer was: " + question.options[question.correctAnswerIndex]);
                }

                if (elapsedTime >= 10000) {
                    System.out.println("Time's up!");
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            showResult();
        }

        private void showResult() {
            System.out.println("\nQuiz Completed!");
            System.out.println("Your score: " + score + "/" + totalQuestions);
            System.out.println(score == totalQuestions ? "Excellent! You got all the answers right!" : "Better luck next time!");
        }
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();

        String[] q1Options = {"Paris", "London", "Berlin", "Madrid"};
        questions.add(new Question("What is the capital of France?", q1Options, 0));

        String[] q2Options = {"10", "20", "30", "40"};
        questions.add(new Question("How many days are in a week?", q2Options, 1));

        String[] q3Options = {"Java", "C++", "Python", "Ruby"};
        questions.add(new Question("Which programming language is this quiz written in?", q3Options, 0));

        String[] q4Options = {"Apple", "Banana", "Cherry", "Grapes"};
        questions.add(new Question("Which fruit is yellow?", q4Options, 1));

        String[] q5Options = {"Mount Everest", "K2", "Mount Fuji", "Kangchenjunga"};
        questions.add(new Question("What is the highest mountain in the world?", q5Options, 0));

        Quiz quiz = new Quiz(questions);

        quiz.startQuiz();
    }
}
