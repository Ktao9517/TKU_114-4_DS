public class CourseComposition {

    static class Instructor {
        private String id;
        private String name;

        public Instructor(String id, String name) {
            this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return id + " - " + name;
        }
    }

    static class Course {
        private String courseCode;
        private String title;
        private Instructor instructor; 

        public Course(String courseCode, String title, Instructor instructor) {
            this.courseCode = (courseCode == null || courseCode.trim().isEmpty()) ? "Unknown" : courseCode.trim();
            this.title = (title == null || title.trim().isEmpty()) ? "Unknown" : title.trim();
            this.instructor = instructor; 
        }

        public String summary() {
            String instructorInfo = (instructor == null) ? "未指定授課者" : instructor.toString();
            return "課程代碼: " + courseCode + ", 名稱: " + title + ", 授課者: " + instructorInfo;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 課程與授課者 Composition =====");

        Instructor teacherWang = new Instructor("T001", "王老師");
        Instructor teacherLee = new Instructor("T002", "李老師");

        Course javaCourse = new Course("CS101", "Java 程式設計", teacherWang);
        Course dataStructure = new Course("CS201", "資料結構", teacherWang); 
        Course database = new Course("CS301", "資料庫系統", teacherLee);

        System.out.println(javaCourse.summary());
        System.out.println(dataStructure.summary());
        System.out.println(database.summary());

        System.out.println("\n確認兩門課共用同一 Instructor 物件: " +
                (javaCourse.summary().contains("王老師") && dataStructure.summary().contains("王老師")));
    }
}
