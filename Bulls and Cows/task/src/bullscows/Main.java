package bullscows;

import java.util.*;

public class Main {
    private static final Random random = new Random();
    private static final char[] symbols = new char[26];
    private static String symbolsLength = "";
    private static final String PATTERN = "^[a-z0-9]+$";
    private static final String DIGIT_ONLY_PATTERN = "^[0-9]+$";
    public static void main(String[] args) {
        for (int i = 0; i< symbols.length; i++) {
            symbols[i] = (char) ('a' + i);
        }
        Scanner scanner = new Scanner(System.in);
        String secretCode;
        System.out.println("Please enter the secret code's length:");
        String sizeString = scanner.next();
        if (!sizeString.matches(DIGIT_ONLY_PATTERN)) {
            System.out.println("Error: " + sizeString + " isn't a valid number.");
            return;
        }
        int size = Integer.parseInt(sizeString);
        if (size < 1) {
            System.out.println("Error: can't generate a secret number with a length of 0.");
            return;
        }

        System.out.println("Please enter the secret code's length:");
        String symbolsLength = scanner.next();
        if (!symbolsLength.matches(PATTERN)) {
            System.out.println("Error: " + symbolsLength + " isn't a valid number.");
            return;
        }else if(Integer.parseInt(symbolsLength)> 36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }else if(Integer.parseInt(symbolsLength) < size){
            System.out.println("Error: it's not possible to generate a code with a length of " + size + " with " + symbolsLength + " unique symbols.");
            return;
        }
        if(Integer.parseInt(symbolsLength) > 10){
            System.out.println("The secret is prepared: " + "*".repeat(size) + " (0-9," +symbols[0] + "-" + symbols[Integer.parseInt(symbolsLength)-11]+").");
        }else{
            System.out.println("The secret is prepared: " + "*".repeat(size) + " (0-9).");
        }

        secretCode = generateSecretCode(size, symbolsLength);
        System.out.println("The random secret number is " + secretCode + ".");

        String guess;
        int count = 1;
        System.out.println("Okay, let's start a game!");
        while (true) {
            System.out.println("Turn " + count + ":");
            guess = scanner.next();

            if (guess.length() != size) {
                System.out.println("Error: Your SymbolsLength must be " + size + " digits long.");
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

    private static String generateSecretCode(int size, String symbolsLength) {
        StringBuilder secretCode = new StringBuilder(size);
        int symbolsLengthInt = Integer.parseInt(symbolsLength);

        while (secretCode.length() < size) {
            int nextDigit = random.nextInt(10 + symbolsLengthInt == 10 ? 0 : symbolsLengthInt);
            char nextChar;
            if (nextDigit < 10) {
                nextChar = (char) (nextDigit + '0');
            } else {
                nextChar = symbols[nextDigit - 10];
            }
            if (secretCode.indexOf(String.valueOf(nextChar)) == -1) {
                secretCode.append(nextChar);
            }
        }
        return secretCode.toString();
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
