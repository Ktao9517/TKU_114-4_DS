import java.util.Arrays;

public class InventorySnapshotPractice {

    static class InventorySnapshot {
        private final String warehouseId;
        private final int[] quantities; 

        public InventorySnapshot(String warehouseId, int[] quantities) {
            this.warehouseId = (warehouseId == null || warehouseId.trim().isEmpty())
                    ? "Unknown" : warehouseId.trim();
            if (quantities == null) {
                this.quantities = new int[0];
            } else {
                this.quantities = Arrays.copyOf(quantities, quantities.length);
            }
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public int[] getQuantities() {
            return Arrays.copyOf(quantities, quantities.length); // defensive copy
        }

        public int totalQuantity() {
            int sum = 0;
            for (int q : quantities) {
                sum += q;
            }
            return sum;
        }

        public int outOfStockCount() {
            int count = 0;
            for (int q : quantities) {
                if (q == 0) count++;
            }
            return count;
        }

        @Override
        public String toString() {
            return "倉庫: " + warehouseId + ", 數量: " + Arrays.toString(quantities);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Immutable 庫存快照 =====");

        int[] data = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-A", data);

        System.out.println(snapshot);
        System.out.println("總數量: " + snapshot.totalQuantity());       
        System.out.println("缺貨品項數: " + snapshot.outOfStockCount()); 

        
        InventorySnapshot emptySnap = new InventorySnapshot("WH-B", null);
        System.out.println("\nnull 陣列測試: " + emptySnap);
        System.out.println("總數量: " + emptySnap.totalQuantity());
        System.out.println("缺貨品項數: " + emptySnap.outOfStockCount());

        
        data[0] = 999;
        System.out.println("\n修改原陣列後 snapshot 仍為: " + Arrays.toString(snapshot.getQuantities()));
    }
}
