import java.util.ArrayList;

public class UnorderedPairs {
    public ArrayList<Integer> sort(ArrayList<Integer> numbers) {
        if (numbers.size() == 0)
            return numbers;
        else if (numbers.size() % 2 != 0)
            numbers.remove(numbers.size() - 1);

        for (int i = 0; i < numbers.size() - 1; i += 2) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                numbers.remove(numbers.get(i));
                numbers.remove(numbers.get(i));
            }
        }
        return numbers;
    }
}