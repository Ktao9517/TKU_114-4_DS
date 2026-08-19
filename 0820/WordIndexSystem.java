import java.util.*;

public class WordIndexSystem {
    public static void main(String[] args) {
        System.out.println("===== 文字索引系統 =====");

        String[] sentences = {
            "Java is a powerful language.",
            "Learning Java and data structures is fun.",
            "Data structures and algorithms are important.",
            "Java collections are useful for many tasks."
        };

        Map<String, Integer> wordCount = new HashMap<>();
        Set<String> uniqueWords = new TreeSet<>();

        for (String sentence : sentences) {
            String cleaned = sentence.toLowerCase()
                    .replace(".", "")
                    .replace(",", "");
            String[] words = cleaned.split("\\s+");
            for (String word : words) {
                if (word.isEmpty()) continue;
                uniqueWords.add(word);
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        System.out.println("不重複單字 (Set):");
        System.out.println(uniqueWords);

        System.out.println("\n單字出現次數 (Map):");
        for (Map.Entry<String, Integer> e : wordCount.entrySet()) {
            System.out.println("  " + e.getKey() + " → " + e.getValue());
        }

        System.out.println("\n出現至少兩次的單字:");
        for (Map.Entry<String, Integer> e : wordCount.entrySet()) {
            if (e.getValue() >= 2) {
                System.out.println("  " + e.getKey() + " (" + e.getValue() + " 次)");
            }
        }
    }
}
