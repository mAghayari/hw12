import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Word {
    public void makeWords(String originWord, int originLength, String newWord, ArrayList<String> madeWords) {
        if (newWord.length() == originLength)
            madeWords.add(newWord);
        else {
            for (int i = 0; i < originWord.length(); i++) {
                ArrayList<Character> originWordChars = new ArrayList<>();
                for (char ch : originWord.toCharArray())
                    originWordChars.add(ch);
                newWord += originWordChars.get(i);
                originWordChars.remove(i);
                String copyOriginWord = originWordChars.stream().map(String::valueOf).collect(Collectors.joining());
                makeWords(copyOriginWord, originLength, newWord, madeWords);
                newWord = (newWord.length() == 1) ? "" : newWord.substring(0, newWord.length() - 1);
            }
        }
    }
    public static boolean isDuplicate(HashMap<String, List<String>> wordsMap, ArrayList<String> madeWords) {
        return wordsMap.values().stream().anyMatch(words -> madeWords.stream().anyMatch(words::contains));
    }
}
