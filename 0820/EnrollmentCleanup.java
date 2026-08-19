import java.util.*;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        System.out.println("===== 安全清理名單 =====");

        List<String> names = new ArrayList<>(Arrays.asList(
            "王小明", "陳小美", null, "  ", "林大同",
            "王小明", "張三", "", "陳小美", "李四", null, "王小明"
        ));

        System.out.println("清理前: " + names);

        
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name == null || name.trim().isEmpty()) {
                it.remove();
            }
        }

        System.out.println("清理後: " + names);

        
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new LinkedHashSet<>();
        for (String name : names) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("重複姓名報告: " + duplicates);
    }
}
