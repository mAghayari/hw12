import java.util.*;

public class GetInputs {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInteger() {
        while (true) {
            String input = scanner.next();
            if (input.matches("[0-9]+")) {
                return Integer.parseInt(input);
            } else
                System.out.println("❌ Mismatched input...\njust numbers are allowed:");
        }
    }

    public static void addToArrayList(ArrayList<Integer> numbers) {
        numbers.add(getInteger());
    }

    public static void fillArrayList(int length, ArrayList<Integer> numbers) {
        for (int i = 0; i < length; i++) {
            addToArrayList(numbers);
        }
    }
}