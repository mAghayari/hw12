import java.util.ArrayList;
import java.util.Iterator;

public class UnorderedPairs {
    public ArrayList<Integer> sort(ArrayList<Integer> numbers) {
        if (numbers.size() == 0)
            return numbers;
        else if (numbers.size() % 2 != 0)
            numbers.remove(numbers.size() - 1);

        ArrayList<Integer> evenIndexes = new ArrayList<>();
        ArrayList<Integer> oddIndexes = new ArrayList<>();
        ArrayList<Integer> newList = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i += 2) {
            evenIndexes.add(numbers.get(i));
            oddIndexes.add(numbers.get(i + 1));
        }

        Iterator evenIterator = evenIndexes.iterator();
        Iterator oddIterator = oddIndexes.iterator();
        int oddIndex;
        int evenIndex;

        while (evenIterator.hasNext()) {
            evenIndex = (int) evenIterator.next();
            oddIndex = (int) oddIterator.next();
            if (evenIndex > oddIndex) {
                evenIterator.remove();
                oddIterator.remove();
            }
        }
        for (int i = 0; i < evenIndexes.size(); i++) {
            newList.add(evenIndexes.get(i));
            newList.add(oddIndexes.get(i));
        }
        return newList;
    }
}