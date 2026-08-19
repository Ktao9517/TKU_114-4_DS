public class EmployeeConstructorChain {

    abstract static class EmployeeBase {
        protected String id;
        protected String name;

        public EmployeeBase(String id, String name) {
            System.out.println("EmployeeBase constructor 執行");
            this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
        }

        public abstract int calculatePay();

        @Override
        public String toString() {
            return id + " - " + name;
        }
    }

    static class FullTimeEmployee extends EmployeeBase {
        private int monthlySalary;

        public FullTimeEmployee(String id, String name, int monthlySalary) {
            super(id, name);
            System.out.println("FullTimeEmployee constructor 執行");
            this.monthlySalary = Math.max(monthlySalary, 0);
        }

        @Override
        public int calculatePay() {
            return monthlySalary;
        }
    }

    static class PartTimeEmployee extends EmployeeBase {
        private int hourlyRate;
        private int hours;

        public PartTimeEmployee(String id, String name, int hourlyRate, int hours) {
            super(id, name);
            System.out.println("PartTimeEmployee constructor 執行");
            this.hourlyRate = Math.max(hourlyRate, 0);
            this.hours = Math.max(hours, 0);
        }

        @Override
        public int calculatePay() {
            return hourlyRate * hours;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 建構鏈與員工類型 =====\n");

        System.out.println("--- 建立 FullTimeEmployee ---");
        FullTimeEmployee full = new FullTimeEmployee("E001", "王小明", 45000);
        System.out.println("薪資: " + full.calculatePay());
        System.out.println("物件: " + full);

        System.out.println("\n--- 建立 PartTimeEmployee ---");
        PartTimeEmployee part = new PartTimeEmployee("E002", "陳小美", 200, 80);
        System.out.println("薪資: " + part.calculatePay());
        System.out.println("物件: " + part);

        System.out.println("\n實際 constructor 執行順序：");
        System.out.println("1. 先執行 EmployeeBase constructor");
        System.out.println("2. 再執行 subclass constructor（FullTime / PartTime）");
        System.out.println("因為 subclass 必須先呼叫 super(...)");
    }
}
