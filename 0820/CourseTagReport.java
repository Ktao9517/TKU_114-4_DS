import java.util.*;

public class CourseTagReport {
    public static void main(String[] args) {
        System.out.println("===== 課程標籤統計 =====");

        String[] tags = {
            "Java", "資料結構", "演算法", "Java", "資料庫",
            "演算法", "網路", "Java", "作業系統", "資料結構"
        };

        List<String> originalOrder = new ArrayList<>();
        Set<String> uniqueTags = new LinkedHashSet<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (String tag : tags) {
            originalOrder.add(tag);
            uniqueTags.add(tag);
            countMap.put(tag, countMap.getOrDefault(tag, 0) + 1);
        }

        System.out.println("1. List（保存原始順序）:");
        System.out.println(originalOrder);
        System.out.println("   用途：保留輸入順序，允許重複，適合需要順序的操作。\n");

        System.out.println("2. Set（不重複標籤）:");
        System.out.println(uniqueTags);
        System.out.println("   用途：快速去重，適合判斷是否存在某個標籤。\n");

        System.out.println("3. Map（統計次數）:");
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println("   " + entry.getKey() + " → " + entry.getValue() + " 次");
        }
        System.out.println("   用途：鍵值對應，適合統計頻率與查詢出現次數。");
    }
}
