public class PayrollPolymorphismSystem {

    abstract static class Employee {
        protected String id;
        protected String name;

        public Employee(String id, String name) {
            this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
        }

        public abstract int calculatePay();

        @Override
        public String toString() {
            return id + " - " + name + " → 薪資: " + calculatePay();
        }
    }

    static class MonthlyEmployee extends Employee {
        private int monthlySalary;

        public MonthlyEmployee(String id, String name, int monthlySalary) {
            super(id, name);
            this.monthlySalary = Math.max(monthlySalary, 0);
        }

        @Override
        public int calculatePay() {
            return monthlySalary;
        }
    }

    static class HourlyEmployee extends Employee {
        private int hourlyRate;
        private int hours;

        public HourlyEmployee(String id, String name, int hourlyRate, int hours) {
            super(id, name);
            this.hourlyRate = Math.max(hourlyRate, 0);
            this.hours = Math.max(hours, 0);
        }

        @Override
        public int calculatePay() {
            return hourlyRate * hours;
        }
    }

    static class SalesEmployee extends Employee {
        private int baseSalary;
        private int salesAmount;
        private double commissionRate;

        public SalesEmployee(String id, String name, int baseSalary, int salesAmount, double commissionRate) {
            super(id, name);
            this.baseSalary = Math.max(baseSalary, 0);
            this.salesAmount = Math.max(salesAmount, 0);
            this.commissionRate = Math.max(commissionRate, 0);
        }

        @Override
        public int calculatePay() {
            return baseSalary + (int) (salesAmount * commissionRate);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 員工薪資與獎金系統 =====");

        Employee[] employees = {
            new MonthlyEmployee("E001", "王小明", 48000),
            new HourlyEmployee("E002", "陳小美", 250, 120),
            new SalesEmployee("E003", "林大同", 30000, 200000, 0.05),
            new MonthlyEmployee("E004", "張三", 52000),
            new SalesEmployee("E005", "李四", 25000, 350000, 0.08)
        };

        int total = 0;
        Employee highest = employees[0];

        for (Employee e : employees) {
            System.out.println(e);
            int pay = e.calculatePay();
            total += pay;
            if (pay > highest.calculatePay()) {
                highest = e;
            }
        }

        System.out.println("\n薪資總額: " + total);
        System.out.println("最高薪資: " + highest);
    }
}
