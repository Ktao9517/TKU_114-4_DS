import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EnrollmentSetSystem {

    static class Enrollment {
        private final String studentId;
        private final String courseCode;

        public Enrollment(String studentId, String courseCode) {
            this.studentId = studentId == null ? "" : studentId.trim();
            this.courseCode = courseCode == null ? "" : courseCode.trim();
        }

        public String getStudentId() { return studentId; }
        public String getCourseCode() { return courseCode; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Enrollment that = (Enrollment) o;
            return Objects.equals(studentId, that.studentId) &&
                   Objects.equals(courseCode, that.courseCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseCode);
        }

        @Override
        public String toString() {
            return studentId + " → " + courseCode;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 課程報名身分集合 =====");

        Set<Enrollment> enrollments = new HashSet<>();

        Enrollment e1 = new Enrollment("S001", "CS101");
        Enrollment e2 = new Enrollment("S001", "CS201"); // 同一人不同課程
        Enrollment e3 = new Enrollment("S002", "CS101");
        Enrollment e4 = new Enrollment("S001", "CS101"); // 同一人同一課程（重複）

        System.out.println("新增 e1 (S001, CS101): " + enrollments.add(e1));
        System.out.println("新增 e2 (S001, CS201): " + enrollments.add(e2));
        System.out.println("新增 e3 (S002, CS101): " + enrollments.add(e3));
        System.out.println("新增 e4 (S001, CS101 重複): " + enrollments.add(e4));

        System.out.println("\n目前報名: " + enrollments);

        // 以新建立但身分相同的 object 測試
        Enrollment sameAsE1 = new Enrollment("S001", "CS101");
        System.out.println("\ncontains(相同身分新物件): " + enrollments.contains(sameAsE1));
        System.out.println("remove(相同身分新物件): " + enrollments.remove(sameAsE1));
        System.out.println("移除後: " + enrollments);
        System.out.println("再次 remove: " + enrollments.remove(sameAsE1));
    }
}
