public class DocumentCapabilityDemo {

    interface Exportable {
        void export(String path);
    }

    interface Compressible {
        void compress(String level);
    }

    static class BackupDocument implements Exportable, Compressible {
        private String name;

        public BackupDocument(String name) {
            this.name = (name == null || name.trim().isEmpty()) ? "Untitled" : name.trim();
        }

        @Override
        public void export(String path) {
            System.out.println("匯出文件「" + name + "」到 " + path);
        }

        @Override
        public void compress(String level) {
            System.out.println("壓縮文件「" + name + "」使用等級: " + level);
        }

        public void showInfo() {
            System.out.println("文件名稱: " + name);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 匯出與壓縮能力 =====");

        BackupDocument doc = new BackupDocument("期末報告.pdf");

        // 同一個物件，用不同 interface reference
        Exportable exporter = doc;
        Compressible compressor = doc;

        System.out.println("兩個 reference 指向同一物件: " + (exporter == compressor));
        System.out.println("exporter 可用 method: export()");
        System.out.println("compressor 可用 method: compress()");
        System.out.println("兩者都無法直接呼叫 showInfo()（需轉型回 BackupDocument）\n");

        exporter.export("/backup/report.pdf");
        compressor.compress("HIGH");

        // 轉回原型才能呼叫額外方法
        if (exporter instanceof BackupDocument) {
            ((BackupDocument) exporter).showInfo();
        }
    }
}
