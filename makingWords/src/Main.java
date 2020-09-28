import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Word word = new Word();
        GetInputs getInputs = new GetInputs();
        HashMap<String, List<String>> wordsMap = new HashMap<>();
        ArrayList<String> userWords;
        ArrayList<String> madeWords = new ArrayList<>();
        int numberOfWords;
        numberOfWords = getInputs.getNumberOfWords();
        userWords = getInputs.getUserWords(numberOfWords);
        for (String w : userWords) {
            word.makeWords(w, w.length(), "", madeWords);
            ArrayList<String> words = new ArrayList<>(madeWords);
            if (Word.isDuplicate(wordsMap, madeWords))
                System.out.println("failed");
            else {
                wordsMap.put(w, words);
                System.out.println("pass");
            }
            madeWords.clear();
        }
        for (String w : wordsMap.keySet()) {
            System.out.print(w + " :");
            System.out.println(wordsMap.get(w).toString());
        }
    }
}