import java.util.*;

public class CourseCollectionManager {

    static class Enrollment {
        private String studentId;
        private String name;
        private String courseCode;
        private String tag;
        private int score;

        public Enrollment(String studentId, String name, String courseCode, String tag, int score) {
            this.studentId = studentId == null ? "" : studentId.trim();
            this.name = name == null ? "" : name.trim();
            this.courseCode = courseCode == null ? "" : courseCode.trim();
            this.tag = tag == null ? "" : tag.trim();
            this.score = Math.max(0, Math.min(100, score));
        }

        public String getStudentId() { return studentId; }
        public String getName() { return name; }
        public String getCourseCode() { return courseCode; }
        public String getTag() { return tag; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = Math.max(0, Math.min(100, score)); }

        public String getLevel() {
            if (score >= 90) return "A";
            if (score >= 80) return "B";
            if (score >= 70) return "C";
            if (score >= 60) return "D";
            return "F";
        }

        @Override
        public String toString() {
            return studentId + " | " + name + " | " + courseCode +
                   " | tag:" + tag + " | 分數:" + score + " (" + getLevel() + ")";
        }
    }

    private final List<Enrollment> list = new ArrayList<>();
    private final Set<String> studentIds = new HashSet<>();
    private final Map<String, Enrollment> byStudentId = new HashMap<>();

    public boolean add(Enrollment e) {
        if (e == null || e.getStudentId().isEmpty()) return false;
        if (byStudentId.containsKey(e.getStudentId())) {
            System.out.println("重複學號，略過: " + e.getStudentId());
            return false;
        }
        list.add(e);
        studentIds.add(e.getStudentId());
        byStudentId.put(e.getStudentId(), e);
        return true;
    }

    public boolean updateScore(String studentId, int score) {
        Enrollment e = byStudentId.get(studentId);
        if (e == null) {
            System.out.println("找不到學號: " + studentId);
            return false;
        }
        e.setScore(score);
        return true;
    }

    public List<Enrollment> findByTag(String tag) {
        List<Enrollment> result = new ArrayList<>();
        if (tag == null) return result;
        String t = tag.trim();
        for (Enrollment e : list) {
            if (e.getTag().equalsIgnoreCase(t)) {
                result.add(e);
            }
        }
        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> dist = new LinkedHashMap<>();
        dist.put("A", 0);
        dist.put("B", 0);
        dist.put("C", 0);
        dist.put("D", 0);
        dist.put("F", 0);
        for (Enrollment e : list) {
            String level = e.getLevel();
            dist.put(level, dist.get(level) + 1);
        }
        return dist;
    }

    public List<Enrollment> top(int count) {
        List<Enrollment> sorted = new ArrayList<>(list);
        sorted.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        if (count >= sorted.size()) return sorted;
        return sorted.subList(0, Math.max(0, count));
    }

    public int removeBelow(int minimum) {
        int removed = 0;
        Iterator<Enrollment> it = list.iterator();
        while (it.hasNext()) {
            Enrollment e = it.next();
            if (e.getScore() < minimum) {
                it.remove();
                studentIds.remove(e.getStudentId());
                byStudentId.remove(e.getStudentId());
                removed++;
            }
        }
        return removed;
    }

    public void printAll() {
        System.out.println("目前報名 (List size=" + list.size() +
                ", Set size=" + studentIds.size() +
                ", Map size=" + byStudentId.size() + "):");
        for (Enrollment e : list) {
            System.out.println("  " + e);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 課程管理集合系統 =====");

        CourseCollectionManager manager = new CourseCollectionManager();

        manager.add(new Enrollment("S001", "王小明", "CS101", "Java", 92));
        manager.add(new Enrollment("S002", "陳小美", "CS101", "Java", 85));
        manager.add(new Enrollment("S003", "林大同", "CS201", "資料結構", 78));
        manager.add(new Enrollment("S001", "重複學號", "CS301", "網路", 60)); // 重複
        manager.add(new Enrollment("S004", "張三", "CS201", "", 65));      // 空白 tag
        manager.add(new Enrollment("S005", "李四", "CS101", "Java", 55));
        manager.add(new Enrollment("S006", "黃五", "CS301", "資料結構", 88));
        manager.add(new Enrollment("S007", "趙六", "CS201", "演算法", 92)); // 同分

        manager.printAll();

        System.out.println("\n--- updateScore ---");
        manager.updateScore("S005", 72);
        manager.updateScore("S999", 100);

        System.out.println("\n--- findByTag(Java) ---");
        manager.findByTag("Java").forEach(System.out::println);

        System.out.println("\n--- scoreDistribution ---");
        System.out.println(manager.scoreDistribution());

        System.out.println("\n--- top(3) ---");
        manager.top(3).forEach(System.out::println);

        System.out.println("\n--- removeBelow(70) ---");
        int removed = manager.removeBelow(70);
        System.out.println("移除 " + removed + " 筆");
        manager.printAll();
    }
}
