import java.util.*;

public class CollectionChoiceReport {
    public static void main(String[] args) {
        System.out.println("===== 集合選擇報告與實作 =====\n");

        // 1. 保留搜尋紀錄且允許重複
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java");
        searchHistory.add("資料結構");
        searchHistory.add("Java");
        System.out.println("1. 搜尋紀錄 (允許重複)");
        System.out.println("   Interface: List  Implementation: ArrayList");
        System.out.println("   結果: " + searchHistory);

        // 2. 保存不重複會員編號
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001"); // 重複無效
        System.out.println("\n2. 不重複會員編號");
        System.out.println("   Interface: Set  Implementation: HashSet");
        System.out.println("   結果: " + memberIds);

        // 3. 以學號查詢成績
        Map<String, Integer> scores = new HashMap<>();
        scores.put("S001", 92);
        scores.put("S002", 85);
        scores.put("S003", 78);
        System.out.println("\n3. 學號查詢成績");
        System.out.println("   Interface: Map  Implementation: HashMap");
        System.out.println("   S002 成績: " + scores.get("S002"));
        System.out.println("   全部: " + scores);

        // 4. 依到達順序處理列印工作
        Queue<String> printJobs = new ArrayDeque<>();
        printJobs.offer("文件A");
        printJobs.offer("文件B");
        printJobs.offer("文件C");
        System.out.println("\n4. 列印工作 FIFO");
        System.out.println("   Interface: Queue  Implementation: ArrayDeque");
        System.out.println("   處理: " + printJobs.poll() + "，剩餘: " + printJobs);

        // 5. 復原最近操作
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("操作1");
        undoStack.push("操作2");
        undoStack.push("操作3");
        System.out.println("\n5. 復原最近操作 (LIFO)");
        System.out.println("   Interface: Deque  Implementation: ArrayDeque (作為 Stack)");
        System.out.println("   Undo: " + undoStack.pop() + "，剩餘: " + undoStack);
    }
}
