import java.util.Arrays;
import java.util.List;

public class SecretCode {
    public static void main(String[] args) {
        generateSecretCode("Where are you? Meet me near the clock tower");

    }

    public static void generateSecretCode(String sentence) {
        String[] words = sentence.split(" ");
        String[] vowel = {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U"};
        List vowels = Arrays.asList(vowel);
        String output = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int wordNumber = (i + 1) % 3;
            String temp = word;
            if (wordNumber == 1) {
                temp = temp.replaceAll("a", "%");
                temp = temp.replaceAll("e", "%");
                temp = temp.replaceAll("i", "%");
                temp = temp.replaceAll("o", "%");
                temp = temp.replaceAll("u", "%");
                temp = temp.replaceAll("A", "%");
                temp = temp.replaceAll("E", "%");
                temp = temp.replaceAll("I", "%");
                temp = temp.replaceAll("O", "%");
                temp = temp.replaceAll("U", "%");
                output += temp;
            } else if (wordNumber == 2) {
                for (int j = 0; j < word.length(); j++) {
                    if (!vowels.contains(String.valueOf(word.charAt(j)))) {
                        temp = temp.replaceAll(String.valueOf(word.charAt(j)), "#");
                    }
                }
                output += temp;
            } else {
                output += word.toUpperCase() + " ";
            }
        }
        System.out.println(output);
    }
}
