public class CourseGradeManager {

    static class CourseGrade {
        private String studentId;
        private String name;
        private int daily;      
        private int midterm;    
        private int finalExam;  
        private int attendance; 

        public CourseGrade(String studentId, String name, int daily, int midterm, int finalExam, int attendance) {
            this.studentId = (studentId == null || studentId.trim().isEmpty()) ? "Unknown" : studentId.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
            this.daily = clamp(daily);
            this.midterm = clamp(midterm);
            this.finalExam = clamp(finalExam);
            this.attendance = clamp(attendance);
        }

        private int clamp(int score) {
            if (score < 0) return 0;
            if (score > 100) return 100;
            return score;
        }

        public double calculateFinalScore() {
            
            return daily * 0.5 + midterm * 0.2 + finalExam * 0.2 + attendance * 0.1;
        }

        public String getLevel() {
            double score = calculateFinalScore();
            if (score >= 90) return "A";
            if (score >= 80) return "B";
            if (score >= 70) return "C";
            if (score >= 60) return "D";
            return "F";
        }

        @Override
        public String toString() {
            return studentId + " | " + name +
                   " | 平時:" + daily + " 期中:" + midterm + " 期末:" + finalExam + " 出席:" + attendance +
                   " | 總分:" + String.format("%.1f", calculateFinalScore()) + " | 等級:" + getLevel();
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 課程成績物件系統 =====");

        CourseGrade[] grades = {
            new CourseGrade("S001", "王小明", 85, 78, 90, 95),
            new CourseGrade("S002", "陳小美", 92, 88, 85, 100),
            new CourseGrade("S003", "林大同", 55, 60, 50, 70),
            new CourseGrade("S004", "張三", 70, 65, 72, 80),
            new CourseGrade("S005", "李四", 45, 40, 55, 60)
        };

        System.out.println("所有成績:");
        for (CourseGrade g : grades) {
            System.out.println(g);
        }

        
        double sum = 0;
        for (CourseGrade g : grades) {
            sum += g.calculateFinalScore();
        }
        System.out.println("\n平均總分: " + String.format("%.2f", sum / grades.length));

        
        CourseGrade highest = grades[0];
        for (int i = 1; i < grades.length; i++) {
            if (grades[i].calculateFinalScore() > highest.calculateFinalScore()) {
                highest = grades[i];
            }
        }
        System.out.println("最高分: " + highest);

        
        System.out.println("\n不及格名單:");
        boolean hasFail = false;
        for (CourseGrade g : grades) {
            if (g.getLevel().equals("F")) {
                System.out.println(g);
                hasFail = true;
            }
        }
        if (!hasFail) {
            System.out.println("無");
        }
    }
}
