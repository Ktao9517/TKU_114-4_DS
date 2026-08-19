public class ReportExporterFactory {

    interface ReportExporter {
        void export(String title, int[] values);
    }

    static class CsvExporter implements ReportExporter {
        @Override
        public void export(String title, int[] values) {
            System.out.println("=== CSV Export: " + title + " ===");
            if (values == null) {
                System.out.println("(no data)");
                return;
            }
            System.out.print("values,");
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i]);
                if (i < values.length - 1) System.out.print(",");
            }
            System.out.println();
        }
    }

    static class JsonExporter implements ReportExporter {
        @Override
        public void export(String title, int[] values) {
            System.out.println("=== JSON Export: " + title + " ===");
            System.out.print("{\"title\":\"" + title + "\",\"values\":[");
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    System.out.print(values[i]);
                    if (i < values.length - 1) System.out.print(",");
                }
            }
            System.out.println("]}");
        }
    }

    static class TextExporter implements ReportExporter {
        @Override
        public void export(String title, int[] values) {
            System.out.println("=== Text Export: " + title + " ===");
            if (values == null) {
                System.out.println("(no data)");
                return;
            }
            for (int i = 0; i < values.length; i++) {
                System.out.println("Item " + (i + 1) + ": " + values[i]);
            }
        }
    }

    public static ReportExporter createExporter(String format) {
        if (format == null) return new TextExporter();
        switch (format.trim().toLowerCase()) {
            case "csv":
                return new CsvExporter();
            case "json":
                return new JsonExporter();
            default:
                return new TextExporter(); 
        }
    }

    
    public static void exportReport(ReportExporter exporter, String title, int[] values) {
        if (exporter == null) {
            System.out.println("Exporter 為 null");
            return;
        }
        exporter.export(title, values);
    }

    public static void main(String[] args) {
        System.out.println("===== 報表輸出 Factory =====");

        int[] data = {120, 85, 200, 45, 160};

        ReportExporter csv = createExporter("csv");
        ReportExporter json = createExporter("json");
        ReportExporter text = createExporter("txt");
        ReportExporter unknown = createExporter("xml"); 

        exportReport(csv, "Sales Report", data);
        System.out.println();
        exportReport(json, "Sales Report", data);
        System.out.println();
        exportReport(text, "Sales Report", data);
        System.out.println();
        exportReport(unknown, "Sales Report", data);

        System.out.println("\n--- null values 測試 ---");
        exportReport(csv, "Empty Report", null);
    }
}
