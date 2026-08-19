public class FlexibleCheckoutSystem {

    interface PricingPolicy {
        int calculateFinalPrice(int originalPrice);
        String getDescription();
    }

    static class OriginalPricePolicy implements PricingPolicy {
        @Override
        public int calculateFinalPrice(int originalPrice) {
            return Math.max(originalPrice, 0);
        }

        @Override
        public String getDescription() {
            return "原價";
        }
    }

    static class VipDiscountPolicy implements PricingPolicy {
        @Override
        public int calculateFinalPrice(int originalPrice) {
            return (int) (Math.max(originalPrice, 0) * 0.85);
        }

        @Override
        public String getDescription() {
            return "VIP 八五折";
        }
    }

    static class FullAmountDiscountPolicy implements PricingPolicy {
        @Override
        public int calculateFinalPrice(int originalPrice) {
            int price = Math.max(originalPrice, 0);
            if (price >= 2000) {
                return price - 300;
            }
            return price;
        }

        @Override
        public String getDescription() {
            return "滿 2000 折 300";
        }
    }

    interface NotificationChannel {
        boolean notify(String message);
        String getChannelName();
    }

    static class EmailChannel implements NotificationChannel {
        @Override
        public boolean notify(String message) {
            System.out.println("[Email] " + message);
            return true;
        }

        @Override
        public String getChannelName() {
            return "Email";
        }
    }

    static class SmsChannel implements NotificationChannel {
        @Override
        public boolean notify(String message) {
            System.out.println("[SMS] " + message);
            return true;
        }

        @Override
        public String getChannelName() {
            return "SMS";
        }
    }

    static class ConsoleChannel implements NotificationChannel {
        @Override
        public boolean notify(String message) {
            System.out.println("[Console] " + message);
            return true;
        }

        @Override
        public String getChannelName() {
            return "Console";
        }
    }

    static class CheckoutResult {
        private final String orderId;
        private final int originalPrice;
        private final int finalPrice;
        private final boolean notificationSuccess;
        private final String policyName;
        private final String channelName;

        public CheckoutResult(String orderId, int originalPrice, int finalPrice,
                              boolean notificationSuccess, String policyName, String channelName) {
            this.orderId = orderId;
            this.originalPrice = originalPrice;
            this.finalPrice = finalPrice;
            this.notificationSuccess = notificationSuccess;
            this.policyName = policyName;
            this.channelName = channelName;
        }

        @Override
        public String toString() {
            return "CheckoutResult{orderId='" + orderId +
                    "', original=" + originalPrice +
                    ", final=" + finalPrice +
                    ", policy=" + policyName +
                    ", channel=" + channelName +
                    ", notified=" + notificationSuccess + "}";
        }
    }

    static class CheckoutService {
        private PricingPolicy pricingPolicy;
        private NotificationChannel notificationChannel;

        public CheckoutService(PricingPolicy pricingPolicy, NotificationChannel notificationChannel) {
            this.pricingPolicy = pricingPolicy;
            this.notificationChannel = notificationChannel;
        }

        public CheckoutResult checkout(String orderId, int originalPrice) {
            if (pricingPolicy == null || notificationChannel == null) {
                return new CheckoutResult(orderId, originalPrice, originalPrice, false, "None", "None");
            }

            int finalPrice = pricingPolicy.calculateFinalPrice(originalPrice);
            String message = "訂單 " + orderId + " 結帳完成，原價 " + originalPrice +
                    "，實付 " + finalPrice + "（" + pricingPolicy.getDescription() + "）";
            boolean notified = notificationChannel.notify(message);

            return new CheckoutResult(orderId, originalPrice, finalPrice, notified,
                    pricingPolicy.getDescription(), notificationChannel.getChannelName());
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 通知與費用系統擴充 =====\n");

        PricingPolicy[] policies = {
            new OriginalPricePolicy(),
            new VipDiscountPolicy(),
            new FullAmountDiscountPolicy()
        };

        NotificationChannel[] channels = {
            new EmailChannel(),
            new SmsChannel(),
            new ConsoleChannel()
        };

        
        String[] orderIds = {"ORD-001", "ORD-002", "ORD-003", "ORD-004", "ORD-005", "ORD-006"};
        int[] prices = {1500, 2500, 1800, 3000, 900, 2200};

        int idx = 0;
        for (int i = 0; i < policies.length; i++) {
            for (int j = 0; j < channels.length; j++) {
                if (idx >= 6) break;
                CheckoutService service = new CheckoutService(policies[i], channels[j]);
                CheckoutResult result = service.checkout(orderIds[idx], prices[idx]);
                System.out.println(result);
                System.out.println();
                idx++;
            }
            if (idx >= 6) break;
        }
    }
}
