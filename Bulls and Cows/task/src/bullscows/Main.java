package bullscows;

import java.util.*;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String secretCode;
        System.out.println("Please enter the secret code's length:");
        int size = scanner.nextInt();
        if (size > 10) {
            System.out.println("Error: can't generate a secret number with a length of " + size + " because there aren't enough unique digits.");
            return;
        } else if (size < 1) {
            System.out.println("Error: can't generate a secret number with a length of 0.");
            return;
        } else {
            secretCode = generateSecretCode(size);
            System.out.println("The random secret number is " + secretCode + ".");
        }

        int count = 1;
        System.out.println("Okay, let's start a game!");
        while (true) {
            System.out.println("Turn " + count + ":");
            String guess = scanner.next();

            if (guess.length() != size) {
                System.out.println("Error: Your guess must be " + size + " digits long.");
                continue;
            }

            int bulls = 0;
            int cows = 0;

            for (int i = 0; i < size; i++) {
                char secretDigit = secretCode.charAt(i);
                char guessDigit = guess.charAt(i);

                if (secretDigit == guessDigit) {
                    bulls++;
                } else if (secretCode.contains(String.valueOf(guessDigit))) {
                    cows++;
                }
            }

            count++;
            if (bulls == size) {
                System.out.println("Grade: " + bulls + " bulls");
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            } else {
                System.out.println("Grade: " + bulls + " bulls and " + cows + " cows");
            }
        }
    }

    private static String generateSecretCode(int size) {
        StringBuilder secretCode = new StringBuilder(size);
        while (secretCode.length() < size) {
            int nextDigit = random.nextInt(10);
            char nextChar = (char) (nextDigit + '0');
            if (secretCode.indexOf(String.valueOf(nextChar)) == -1) {
                secretCode.append(nextChar);
            }
        }
        return secretCode.toString();
    }

    private static boolean isValidGuess(String guess) {
        return guess.matches("\\d{4}");
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
