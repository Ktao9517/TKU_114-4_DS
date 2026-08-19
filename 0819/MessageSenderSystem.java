public class MessageSenderSystem {

    interface MessageSender {
        boolean send(String receiver, String message);
    }

    static class EmailSender implements MessageSender {
        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
                System.out.println("[Email] 接收者或訊息為空白，發送失敗");
                return false;
            }
            System.out.println("[Email] 發送給 " + receiver + ": " + message);
            return true;
        }
    }

    static class SmsSender implements MessageSender {
        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
                System.out.println("[SMS] 接收者或訊息為空白，發送失敗");
                return false;
            }
            System.out.println("[SMS] 發送給 " + receiver + ": " + message);
            return true;
        }
    }

    static class ConsoleSender implements MessageSender {
        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
                System.out.println("[Console] 接收者或訊息為空白，發送失敗");
                return false;
            }
            System.out.println("[Console] 通知 " + receiver + " → " + message);
            return true;
        }
    }

    // 只依賴 MessageSender interface，新增 sender 不需修改此方法
    public static void notify(MessageSender sender, String receiver, String message) {
        if (sender == null) {
            System.out.println("Sender 為 null，無法通知");
            return;
        }
        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        System.out.println("===== 訊息發送 Interface =====");

        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "user@example.com", "您的訂單已出貨");
        notify(sms, "0912345678", "驗證碼 123456");
        notify(console, "Admin", "系統維護通知");

        System.out.println("\n--- 空白測試 ---");
        notify(email, "", "測試");
        notify(sms, "0912", null);
        notify(console, "   ", "  ");
    }
}
