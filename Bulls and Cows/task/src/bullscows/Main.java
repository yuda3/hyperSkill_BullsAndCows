package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(10000);
        String secretNumberString = String.valueOf(secretNumber);
        char[] secret = secretNumberString.toCharArray();
        Scanner scanner = new Scanner(System.in);
        secretNumber = secretNumber < 1000 ? secretNumber + 1000 : secretNumber;
        String yourAnswer = scanner.next();
        char[] your = yourAnswer.toCharArray();
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < your.length; i++) {
            if (your[i] == secret[i]) {
                bulls++;
            } else {
                for (int j = 0; j < secret.length; j++) {
                    if (your[i] == secret[j]) {
                        cows++;
                    }
                }
            }
        }

        judgment(bulls, cows, secretNumber);
    }

    private static void judgment(int bulls, int cows, int secretNumber) {
        if(bulls > 0 && cows > 0) {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s). The secret code is " + secretNumber + ".");
        } else if (bulls > 0) {
            System.out.println("Grade: " + bulls + " bull(s). The secret code is " + secretNumber + ".");
        } else if (cows > 0) {
            System.out.println("Grade: " + cows + " cow(s). The secret code is " + secretNumber + ".");
        }else {
            System.out.println("Grade: None. The secret code is " + secretNumber + ".");
        }
    }
}
