public class AccountTransferService {

    static class Account {
        private String accountId;
        private String owner;
        private int balance;

        public Account(String accountId, String owner, int balance) {
            this.accountId = (accountId == null || accountId.trim().isEmpty()) ? "Unknown" : accountId.trim();
            this.owner = (owner == null || owner.trim().isEmpty()) ? "Unknown" : owner.trim();
            this.balance = Math.max(balance, 0);
        }

        public String getAccountId() { return accountId; }
        public String getOwner() { return owner; }
        public int getBalance() { return balance; }

        
        void deposit(int amount) {
            if (amount > 0) balance += amount;
        }

        boolean withdraw(int amount) {
            if (amount <= 0 || amount > balance) return false;
            balance -= amount;
            return true;
        }

        @Override
        public String toString() {
            return accountId + " (" + owner + ") 餘額: " + balance;
        }
    }

    static class TransferService {
        public boolean transfer(Account source, Account target, int amount) {
            
            if (source == null || target == null) {
                System.out.println("來源或目標帳戶為 null，轉帳失敗");
                return false;
            }
            
            if (source == target) {
                System.out.println("來源與目標為同一帳戶，轉帳失敗");
                return false;
            }
            
            if (amount <= 0) {
                System.out.println("轉帳金額必須大於 0");
                return false;
            }
            if (amount > source.getBalance()) {
                System.out.println("來源餘額不足，轉帳失敗");
                return false;
            }

            
            boolean withdrawn = source.withdraw(amount);
            if (!withdrawn) {
                return false; 
            }
            target.deposit(amount);
            System.out.println("轉帳成功: " + amount + " 從 " + source.getAccountId() + " 到 " + target.getAccountId());
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 跨帳戶轉帳服務 =====");

        Account a1 = new Account("A001", "王小明", 5000);
        Account a2 = new Account("A002", "陳小美", 2000);
        TransferService service = new TransferService();

        System.out.println("初始:");
        System.out.println(a1);
        System.out.println(a2);

        System.out.println("\n--- 成功轉帳 1500 ---");
        service.transfer(a1, a2, 1500);
        System.out.println(a1);
        System.out.println(a2);

        System.out.println("\n--- 餘額不足 ---");
        service.transfer(a1, a2, 10000);
        System.out.println(a1);
        System.out.println(a2);

        System.out.println("\n--- 同帳戶轉帳 ---");
        service.transfer(a1, a1, 100);
        System.out.println(a1);

        System.out.println("\n--- null 目標 ---");
        service.transfer(a1, null, 100);
        System.out.println(a1);

        System.out.println("\n最終狀態:");
        System.out.println(a1);
        System.out.println(a2);
    }
}
