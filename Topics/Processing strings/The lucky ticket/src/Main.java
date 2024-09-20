import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        String ticket = new Scanner(System.in).next();

        calculateLucky(ticket);
    }

    private static void calculateLucky(String ticket) {
        char[] charArray = ticket.toCharArray();
        int first = 0;
        int last = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (i < 3) {
                last += Character.getNumericValue(charArray[i]);
            } else {
                first += Character.getNumericValue(charArray[i]);
            }
        }

        System.out.println(first == last ? "Lucky" : "Regular");
    }
}