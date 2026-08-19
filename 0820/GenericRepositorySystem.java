import java.util.ArrayList;
import java.util.List;

public class GenericRepositorySystem {

    static class Product {
        private String id;
        private String name;
        private int price;

        public Product(String id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return id + " - " + name + " ($" + price + ")";
        }
    }

    static class Repository<T> {
        private final List<T> items = new ArrayList<>();

        public void add(T item) {
            if (item != null) {
                items.add(item);
            }
        }

        public T get(int index) {
            if (index < 0 || index >= items.size()) {
                return null;
            }
            return items.get(index);
        }

        public boolean remove(int index) {
            if (index < 0 || index >= items.size()) {
                return false;
            }
            items.remove(index);
            return true;
        }

        public int size() {
            return items.size();
        }

        public void printAll() {
            System.out.println("Repository size=" + size());
            for (int i = 0; i < items.size(); i++) {
                System.out.println("  [" + i + "] " + items.get(i));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Generic Repository =====");

        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Java");
        stringRepo.add("Python");
        stringRepo.add("C++");
        stringRepo.printAll();
        System.out.println("get(1) = " + stringRepo.get(1));
        stringRepo.remove(1);
        stringRepo.printAll();

        System.out.println();
        Repository<Product> productRepo = new Repository<>();
        productRepo.add(new Product("P01", "Keyboard", 890));
        productRepo.add(new Product("P02", "Mouse", 490));
        productRepo.add(new Product("P03", "Monitor", 5200));
        productRepo.printAll();
        System.out.println("get(0) = " + productRepo.get(0));
    }
}
