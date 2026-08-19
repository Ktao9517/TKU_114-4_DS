public class BookArrayReport {

    static class Book {
        private String id;
        private String title;
        private int price;
        private int stock;

        public Book(String id, String title, int price, int stock) {
            this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
            this.title = (title == null || title.trim().isEmpty()) ? "Unknown" : title.trim();
            this.price = Math.max(price, 0);
            this.stock = Math.max(stock, 0);
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public int getPrice() { return price; }
        public int getStock() { return stock; }

        public long getInventoryValue() {
            return (long) price * stock;
        }

        @Override
        public String toString() {
            return id + " | " + title + " | 價格: " + price + " | 庫存: " + stock;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 物件陣列統計 =====");

        Book[] books = {
            new Book("B001", "Java 基礎", 450, 12),
            new Book("B002", "資料結構", 520, 3),
            new Book("B003", "演算法導論", 680, 2),
            new Book("B004", "作業系統", 590, 8),
            new Book("B005", "網路概論", 380, 1)
        };

        
        System.out.println("所有書籍:");
        for (Book b : books) {
            System.out.println(b);
        }

        
        long totalValue = 0;
        for (Book b : books) {
            totalValue += b.getInventoryValue();
        }
        System.out.println("\n庫存總價值: " + totalValue);

        
        Book highest = books[0];
        for (int i = 1; i < books.length; i++) {
            if (books[i].getPrice() > highest.getPrice()) {
                highest = books[i];
            }
        }
        System.out.println("價格最高的書: " + highest);

        
        System.out.println("\n庫存 ≤ 3 的書:");
        for (Book b : books) {
            if (b.getStock() <= 3) {
                System.out.println(b);
            }
        }
    }
}
