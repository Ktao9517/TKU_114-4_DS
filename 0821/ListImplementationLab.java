import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    public static void processList(List<Integer> list) {
        System.out.println("初始: " + list);

        
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("尾端新增後: " + list);

        
        list.add(1, 15);
        System.out.println("在 index 1 插入 15 後: " + list);

        
        int index = list.indexOf(20);
        System.out.println("搜尋 20 的位置: " + index);

        
        list.remove(Integer.valueOf(15));
        System.out.println("刪除 15 後: " + list);

        
        int sum = 0;
        for (int n : list) {
            sum += n;
        }
        System.out.println("總和: " + sum);
        System.out.println("最終內容: " + list);
    }

    public static void main(String[] args) {
        System.out.println("===== List Implementation 比較 =====\n");

        System.out.println("--- ArrayList ---");
        processList(new ArrayList<>());

        System.out.println("\n--- LinkedList ---");
        processList(new LinkedList<>());

        System.out.println("\n===== 內部成本差異說明 =====");
        System.out.println("1. 尾端新增 (add): ArrayList 攤銷 O(1)，LinkedList O(1)。");
        System.out.println("2. 中間插入 (add at index): ArrayList 需搬移元素 O(n)，LinkedList 找到位置後 O(1)。");
        System.out.println("3. 搜尋 (indexOf): 兩者皆為 O(n)。");
        System.out.println("4. 刪除中間元素: ArrayList 需搬移 O(n)，LinkedList 找到後 O(1)。");
        System.out.println("5. 隨機存取 (get): ArrayList O(1)，LinkedList O(n)。");
        System.out.println("結論: 頻繁中間插入/刪除適合 LinkedList；大量隨機讀取與尾端操作適合 ArrayList。");
    }
}
