public class DeviceInspectionSystem {

    static class Device {
        protected String id;
        protected String name;

        public Device(String id, String name) {
            this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
        }

        public void runDiagnostic() {
            System.out.println("[" + id + "] " + name + " 執行基本診斷...");
        }
    }

    static class Laptop extends Device {
        public Laptop(String id, String name) {
            super(id, name);
        }

        @Override
        public void runDiagnostic() {
            System.out.println("[" + id + "] " + name + " 筆電診斷：CPU、記憶體、硬碟檢查完成");
        }
    }

    static class Printer extends Device {
        public Printer(String id, String name) {
            super(id, name);
        }

        @Override
        public void runDiagnostic() {
            System.out.println("[" + id + "] " + name + " 印表機診斷：噴頭、紙匣、連線檢查完成");
        }

        public void cleanPrintHead() {
            System.out.println("[" + id + "] " + name + " 正在清潔噴頭...");
        }
    }

    static class Router extends Device {
        public Router(String id, String name) {
            super(id, name);
        }

        @Override
        public void runDiagnostic() {
            System.out.println("[" + id + "] " + name + " 路由器診斷：訊號、連線數、韌體檢查完成");
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 安全型態判斷 =====");

        Device[] devices = {
            new Laptop("L001", "ThinkPad X1"),
            new Printer("P001", "HP LaserJet"),
            new Router("R001", "ASUS RT-AX"),
            new Printer("P002", "Epson EcoTank"),
            new Laptop("L002", "MacBook Pro")
        };

        System.out.println("--- 多型執行診斷 ---");
        for (Device d : devices) {
            d.runDiagnostic(); // polymorphism
        }

        System.out.println("\n--- 只對 Printer 執行清潔（pattern matching instanceof）---");
        for (Device d : devices) {
            if (d instanceof Printer printer) { // pattern matching
                printer.cleanPrintHead();
            }
        }
    }
}
