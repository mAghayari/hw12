import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInputs {
    private static Scanner scanner = new Scanner(System.in);

    public int getNumberOfWords() {
        System.out.println("enter number of Words you want to test:");
        while (true) {
            String input = scanner.next();
            if (input.matches("[1-9]+")) {
                try {
                    return Integer.parseInt(input);
                } catch (InputMismatchException e) {
                    System.out.println("❌ Mismatched input...\njust alphabet are allowed:");
                }
            } else
                System.out.println("❌ Mismatched input...\njust alphabet are allowed:");
        }
    }

    public ArrayList<String> getUserWords(int numberOfWords) {
        System.out.println("enter words :");
        ArrayList<String> userWordsList = new ArrayList<>();
        for (int i = 0; i < numberOfWords; i++) {
            while (true) {
                String input = scanner.next();
                if (input.matches("[a-zA-Z]+")) {
                    userWordsList.add(input);
                    break;
                } else
                    System.out.println("❌ Mismatched input...\njust alphabet are allowed:");
            }
        }
        return userWordsList;
    }
}