public class EquipmentInventory {

    static class Equipment {
        private String id;
        private String name;
        private int availableCount;

        public Equipment(String id, String name, int availableCount) {
            this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
            this.availableCount = Math.max(availableCount, 0);
        }

        public boolean borrowOne() {
            if (availableCount > 0) {
                availableCount--;
                return true;
            }
            return false;
        }

        public void returnItems(int quantity) {
            if (quantity > 0) {
                availableCount += quantity;
            }
        }

        @Override
        public String toString() {
            return "設備編號: " + id + ", 名稱: " + name + ", 可借數量: " + availableCount;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 設備庫存物件 =====");

        Equipment projector = new Equipment("EQ001", "投影機", 3);
        Equipment laptop = new Equipment("", "筆記型電腦", -2); // 測試空白 id 與負數

        System.out.println("初始狀態:");
        System.out.println(projector);
        System.out.println(laptop);

        System.out.println("\n借用投影機一次（成功）: " + projector.borrowOne());
        System.out.println(projector);

        System.out.println("\n借用筆記型電腦（庫存 0，應失敗）: " + laptop.borrowOne());
        System.out.println(laptop);

        System.out.println("\n歸還 2 台筆記型電腦:");
        laptop.returnItems(2);
        System.out.println(laptop);

        System.out.println("\n再次借用筆記型電腦（成功）: " + laptop.borrowOne());
        System.out.println(laptop);

        System.out.println("\n嘗試歸還負數（應不改變）:");
        laptop.returnItems(-5);
        System.out.println(laptop);
    }
}
