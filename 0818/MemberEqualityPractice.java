import java.util.Objects;

public class MemberEqualityPractice {

    static class LibraryMember {
        private String memberId;
        private String name;
        private String email;

        public LibraryMember(String memberId, String name, String email) {
            this.memberId = (memberId == null || memberId.trim().isEmpty()) ? "Unknown" : memberId.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
            this.email = (email == null) ? "" : email.trim();
        }

        public String getMemberId() {
            return memberId;
        }

        @Override
        public String toString() {
            return "會員編號: " + memberId + ", 姓名: " + name + ", Email: " + email;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            LibraryMember other = (LibraryMember) obj;
            return Objects.equals(this.memberId, other.memberId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(memberId);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 會員身分比較 =====");

        LibraryMember m1 = new LibraryMember("M1001", "王小明", "wang@example.com");
        LibraryMember m2 = new LibraryMember("M1001", "王小明", "wang.other@example.com"); // 同 id 不同 email
        LibraryMember m3 = new LibraryMember("M1002", "陳小美", "chen@example.com");

        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);
        System.out.println("m3: " + m3);

        System.out.println("\nm1 == m2 : " + (m1 == m2));
        System.out.println("m1.equals(m2) : " + m1.equals(m2));

        System.out.println("\nm1 == m3 : " + (m1 == m3));
        System.out.println("m1.equals(m3) : " + m1.equals(m3));

        System.out.println("\nm1.equals(null) : " + m1.equals(null));
        System.out.println("m1.hashCode() == m2.hashCode() : " + (m1.hashCode() == m2.hashCode()));
    }
}
