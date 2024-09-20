import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine().chars().forEach(c -> sb.append((char) c).append((char) c));
        System.out.println(sb);
    }
}