package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int CODE_LENGTH = 4;
    private static final int MIN_NUMBER = 1000;
    private static final int MAX_NUMBER = 9999;

    public static void main(String[] args) {
        int secretNumber = generateSecretNumber();
        String secretCode = String.format("%04d", secretNumber);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your guess (a 4-digit number):");
        String guess = scanner.nextLine();

        if (isValidGuess(guess)) {
            Result result = calculateResult(secretCode, guess);
            printResult(result, secretNumber);
        } else {
            System.out.println("Invalid input. Please enter a 4-digit number.");
        }

        scanner.close();
    }

    private static int generateSecretNumber() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }

    private static boolean isValidGuess(String guess) {
        return guess.matches("\\d{4}");
    }

    private static Result calculateResult(String secretCode, String guess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < CODE_LENGTH; i++) {
            char secretDigit = secretCode.charAt(i);
            char guessDigit = guess.charAt(i);

            if (secretDigit == guessDigit) {
                bulls++;
            } else if (secretCode.contains(String.valueOf(guessDigit))) {
                cows++;
            }
        }

        return new Result(bulls, cows);
    }

    private static void printResult(Result result, int secretNumber) {
        if (result.bulls > 0 && result.cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %d.%n",
                    result.bulls, result.cows, secretNumber);
        } else if (result.bulls > 0) {
            System.out.printf("Grade: %d bull(s). The secret code is %d.%n",
                    result.bulls, secretNumber);
        } else if (result.cows > 0) {
            System.out.printf("Grade: %d cow(s). The secret code is %d.%n",
                    result.cows, secretNumber);
        } else {
            System.out.printf("Grade: None. The secret code is %d.%n", secretNumber);
        }
    }

    private static class Result {
        int bulls;
        int cows;

        Result(int bulls, int cows) {
            this.bulls = bulls;
            this.cows = cows;
        }
    }
}
