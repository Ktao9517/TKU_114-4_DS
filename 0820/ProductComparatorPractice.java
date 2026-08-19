import java.util.*;

public class ProductComparatorPractice {

    static class StoreProduct implements Comparable<StoreProduct> {
        private String id;
        private String name;
        private int price;
        private int stock;

        public StoreProduct(String id, String name, int price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public int getPrice() { return price; }
        public int getStock() { return stock; }

        @Override
        public int compareTo(StoreProduct other) {
            return this.id.compareTo(other.id); 
        }

        @Override
        public String toString() {
            return id + " | " + name + " | 價格:" + price + " | 庫存:" + stock;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 多規則商品排序 =====");

        List<StoreProduct> products = Arrays.asList(
            new StoreProduct("P03", "Monitor", 5200, 5),
            new StoreProduct("P01", "Keyboard", 890, 12),
            new StoreProduct("P05", "Headset", 1290, 8),
            new StoreProduct("P02", "Mouse", 490, 20),
            new StoreProduct("P04", "USB", 890, 8),   
            new StoreProduct("P06", "Webcam", 650, 8)  
        );

        System.out.println("原始順序:");
        products.forEach(System.out::println);

        
        List<StoreProduct> byId = new ArrayList<>(products);
        Collections.sort(byId);
        System.out.println("\nNatural order (id 升冪):");
        byId.forEach(System.out::println);

        
        List<StoreProduct> byPrice = new ArrayList<>(products);
        byPrice.sort(Comparator.comparingInt(StoreProduct::getPrice)
                .thenComparing(StoreProduct::getName));
        System.out.println("\n依 price 升冪，同價依 name:");
        byPrice.forEach(System.out::println);

        
        List<StoreProduct> byStock = new ArrayList<>(products);
        byStock.sort(Comparator.comparingInt(StoreProduct::getStock).reversed()
                .thenComparing(StoreProduct::getId));
        System.out.println("\n依 stock 降冪，同庫存依 id:");
        byStock.forEach(System.out::println);

        System.out.println("\n原始順序仍保持不變:");
        products.forEach(System.out::println);
    }
}
