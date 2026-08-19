public class DeliveryStrategySystem {

    interface DeliveryMethod {
        int calculateFee(int orderAmount);
        String estimate();
    }

    static class HomeDelivery implements DeliveryMethod {
        @Override
        public int calculateFee(int orderAmount) {
            if (orderAmount >= 1000) return 0; 
            return 80;
        }

        @Override
        public String estimate() {
            return "宅配約 2-3 個工作天";
        }
    }

    static class ConvenienceStorePickup implements DeliveryMethod {
        @Override
        public int calculateFee(int orderAmount) {
            return 60;
        }

        @Override
        public String estimate() {
            return "超商取貨約 3-5 個工作天";
        }
    }

    static class SelfPickup implements DeliveryMethod {
        @Override
        public int calculateFee(int orderAmount) {
            return 0;
        }

        @Override
        public String estimate() {
            return "自取，當日可取";
        }
    }

    static class OrderService {
        private DeliveryMethod deliveryMethod; 

        public OrderService(DeliveryMethod deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
        }

        public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
        }

        public void processOrder(int orderAmount) {
            if (deliveryMethod == null) {
                System.out.println("尚未設定配送方式");
                return;
            }
            int fee = deliveryMethod.calculateFee(orderAmount);
            System.out.println("訂單金額: " + orderAmount +
                    "，運費: " + fee +
                    "，預估: " + deliveryMethod.estimate() +
                    "，應付: " + (orderAmount + fee));
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 多方式配送系統 =====");

        OrderService service = new OrderService(new HomeDelivery());
        service.processOrder(800);
        service.processOrder(1500);

        System.out.println();
        service.setDeliveryMethod(new ConvenienceStorePickup());
        service.processOrder(600);

        System.out.println();
        service.setDeliveryMethod(new SelfPickup());
        service.processOrder(300);
    }
}
