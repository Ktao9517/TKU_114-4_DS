public class DigitalWalletSystem {

    static class DigitalWallet {
        private String walletId;
        private String owner;
        private int balance;
        private int transactionCount;

        public DigitalWallet(String walletId, String owner, int initialBalance) {
            this.walletId = (walletId == null || walletId.trim().isEmpty()) ? "Unknown" : walletId.trim();
            this.owner = (owner == null || owner.trim().isEmpty()) ? "Unknown" : owner.trim();
            this.balance = Math.max(initialBalance, 0);
            this.transactionCount = 0;
        }

        public boolean deposit(int amount) {
            if (amount <= 0) {
                System.out.println("儲值金額必須大於 0");
                return false;
            }
            balance += amount;
            transactionCount++;
            System.out.println("儲值成功 +" + amount + "，目前餘額: " + balance);
            return true;
        }

        public boolean pay(int amount) {
            if (amount <= 0) {
                System.out.println("付款金額必須大於 0");
                return false;
            }
            if (amount > balance) {
                System.out.println("餘額不足，付款失敗");
                return false;
            }
            balance -= amount;
            transactionCount++;
            System.out.println("付款成功 -" + amount + "，目前餘額: " + balance);
            return true;
        }

        public boolean refund(int amount) {
            if (amount <= 0) {
                System.out.println("退款金額必須大於 0");
                return false;
            }
            balance += amount;
            transactionCount++;
            System.out.println("退款成功 +" + amount + "，目前餘額: " + balance);
            return true;
        }

        public int getBalance() {
            return balance;
        }

        public int getTransactionCount() {
            return transactionCount;
        }

        @Override
        public String toString() {
            return "錢包ID: " + walletId + ", 持有人: " + owner +
                   ", 餘額: " + balance + ", 交易次數: " + transactionCount;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 封裝式電子錢包 =====");

        DigitalWallet wallet = new DigitalWallet("W001", "王小明", 1000);
        System.out.println("初始: " + wallet);

        System.out.println("\n--- 正常儲值 ---");
        wallet.deposit(500);

        System.out.println("\n--- 正常付款 ---");
        wallet.pay(300);

        System.out.println("\n--- 餘額不足 ---");
        wallet.pay(2000);

        System.out.println("\n--- 負數金額 ---");
        wallet.deposit(-100);
        wallet.pay(-50);

        System.out.println("\n--- 退款 ---");
        wallet.refund(200);

        System.out.println("\n最終狀態: " + wallet);
    }
}
