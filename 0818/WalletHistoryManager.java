public class WalletHistoryManager {

    static class Transaction {
        private final int sequence;
        private final String type;   // DEPOSIT, PAY, REFUND, TRANSFER_OUT, TRANSFER_IN
        private final int amount;
        private final String note;

        public Transaction(int sequence, String type, int amount, String note) {
            this.sequence = sequence;
            this.type = type;
            this.amount = amount;
            this.note = note == null ? "" : note;
        }

        public int getSequence() { return sequence; }
        public String getType() { return type; }
        public int getAmount() { return amount; }

        @Override
        public String toString() {
            return "#" + sequence + " [" + type + "] " + amount + "  " + note;
        }
    }

    static class DigitalWallet {
        private final String walletId;
        private final String owner;
        private int balance;
        private final Transaction[] transactions;
        private int transactionCount;
        private int nextSequence;

        public DigitalWallet(String walletId, String owner, int initialBalance, int capacity) {
            this.walletId = (walletId == null || walletId.trim().isEmpty()) ? "Unknown" : walletId.trim();
            this.owner = (owner == null || owner.trim().isEmpty()) ? "Unknown" : owner.trim();
            this.balance = Math.max(initialBalance, 0);
            this.transactions = new Transaction[Math.max(capacity, 1)];
            this.transactionCount = 0;
            this.nextSequence = 1;
        }

        private boolean isFull() {
            return transactionCount >= transactions.length;
        }

        private boolean record(String type, int amount, String note) {
            if (isFull()) {
                System.out.println("交易紀錄已滿，無法記錄（也不修改餘額）");
                return false;
            }
            transactions[transactionCount++] = new Transaction(nextSequence++, type, amount, note);
            return true;
        }

        public boolean deposit(int amount) {
            if (amount <= 0) {
                System.out.println("儲值金額必須 > 0");
                return false;
            }
            if (isFull()) {
                System.out.println("交易陣列已滿，儲值失敗（餘額不變）");
                return false;
            }
            balance += amount;
            record("DEPOSIT", amount, "儲值");
            return true;
        }

        public boolean pay(int amount) {
            if (amount <= 0) {
                System.out.println("付款金額必須 > 0");
                return false;
            }
            if (amount > balance) {
                System.out.println("餘額不足");
                return false;
            }
            if (isFull()) {
                System.out.println("交易陣列已滿，付款失敗（餘額不變）");
                return false;
            }
            balance -= amount;
            record("PAY", amount, "付款");
            return true;
        }

        public boolean refund(int amount) {
            if (amount <= 0) {
                System.out.println("退款金額必須 > 0");
                return false;
            }
            if (isFull()) {
                System.out.println("交易陣列已滿，退款失敗（餘額不變）");
                return false;
            }
            balance += amount;
            record("REFUND", amount, "退款");
            return true;
        }

        
        public boolean transferTo(DigitalWallet target, int amount) {
            if (target == null) {
                System.out.println("目標錢包為 null");
                return false;
            }
            if (this == target) {
                System.out.println("不可轉給自己");
                return false;
            }
            if (amount <= 0) {
                System.out.println("轉帳金額必須 > 0");
                return false;
            }
            if (amount > this.balance) {
                System.out.println("來源餘額不足");
                return false;
            }
            if (this.isFull() || target.isFull()) {
                System.out.println("交易陣列已滿，轉帳失敗（兩邊餘額不變）");
                return false;
            }

            
            this.balance -= amount;
            this.record("TRANSFER_OUT", amount, "轉出至 " + target.walletId);

            target.balance += amount;
            target.record("TRANSFER_IN", amount, "轉入自 " + this.walletId);

            System.out.println("轉帳成功 " + amount + " 從 " + this.walletId + " 到 " + target.walletId);
            return true;
        }

        public Transaction findTransaction(int sequence) {
            for (int i = 0; i < transactionCount; i++) {
                if (transactions[i].getSequence() == sequence) {
                    return transactions[i];
                }
            }
            return null;
        }

        public int totalByType(String type) {
            if (type == null) return 0;
            int sum = 0;
            for (int i = 0; i < transactionCount; i++) {
                if (transactions[i].getType().equalsIgnoreCase(type)) {
                    sum += transactions[i].getAmount();
                }
            }
            return sum;
        }

        public void printStatement() {
            System.out.println("===== 錢包對帳單: " + walletId + " (" + owner + ") =====");
            System.out.println("目前餘額: " + balance);
            System.out.println("交易筆數: " + transactionCount + " / " + transactions.length);
            for (int i = 0; i < transactionCount; i++) {
                System.out.println(transactions[i]);
            }
            System.out.println("----------------------------------------");
        }

        public int getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 電子錢包交易系統擴充 =====");

        DigitalWallet w1 = new DigitalWallet("W001", "王小明", 2000, 10);
        DigitalWallet w2 = new DigitalWallet("W002", "陳小美", 1000, 10);

        w1.deposit(500);
        w1.pay(300);
        w1.refund(100);
        w1.transferTo(w2, 400);

        System.out.println("\n尋找交易 #2: " + w1.findTransaction(2));
        System.out.println("DEPOSIT 總額: " + w1.totalByType("DEPOSIT"));
        System.out.println("TRANSFER_OUT 總額: " + w1.totalByType("TRANSFER_OUT"));

        System.out.println();
        w1.printStatement();
        w2.printStatement();

        
        System.out.println("\n--- 填滿交易陣列測試 ---");
        DigitalWallet small = new DigitalWallet("W003", "測試", 100, 2);
        small.deposit(50);
        small.deposit(30);
        small.deposit(20); // 應失敗，餘額不變
        System.out.println("small 餘額應仍為 180: " + small.getBalance());
        small.printStatement();
    }
}
