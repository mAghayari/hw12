
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.println("how many numbers do you wanna add to list?");
        int length = GetInputs.getInteger();
        System.out.println("enter numbers:");
        GetInputs.fillArrayList(length, numbers);
       UnorderedPairs unorderedPairs= new UnorderedPairs();
        numbers = unorderedPairs.sort(numbers);
        System.out.println(numbers);
    }
}
