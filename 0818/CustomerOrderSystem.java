public class CustomerOrderSystem {

    static class Customer {
        private String customerId;
        private String name;

        public Customer(String customerId, String name) {
            this.customerId = (customerId == null || customerId.trim().isEmpty()) ? "Unknown" : customerId.trim();
            this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
        }

        public String getCustomerId() { return customerId; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return customerId + " - " + name;
        }
    }

    static class OrderItem {
        private String productName;
        private int unitPrice;
        private int quantity;

        public OrderItem(String productName, int unitPrice, int quantity) {
            this.productName = (productName == null || productName.trim().isEmpty()) ? "Unknown" : productName.trim();
            this.unitPrice = Math.max(unitPrice, 0);
            this.quantity = Math.max(quantity, 0);
        }

        public int getSubtotal() {
            return unitPrice * quantity;
        }

        @Override
        public String toString() {
            return productName + " x" + quantity + " @" + unitPrice + " = " + getSubtotal();
        }
    }

    static class CustomerOrder {
        private String orderId;
        private Customer customer;         
        private OrderItem[] items;          

        public CustomerOrder(String orderId, Customer customer, OrderItem[] items) {
            this.orderId = (orderId == null || orderId.trim().isEmpty()) ? "Unknown" : orderId.trim();
            this.customer = customer;
            this.items = (items == null) ? new OrderItem[0] : items;
        }

        public int getTotalAmount() {
            int total = 0;
            for (OrderItem item : items) {
                if (item != null) {
                    total += item.getSubtotal();
                }
            }
            return total;
        }

        public int getItemCount() {
            int count = 0;
            for (OrderItem item : items) {
                if (item != null) count++;
            }
            return count;
        }

        public String summary() {
            StringBuilder sb = new StringBuilder();
            sb.append("訂單編號: ").append(orderId).append("\n");
            sb.append("顧客: ").append(customer == null ? "未指定" : customer.toString()).append("\n");
            sb.append("品項:\n");
            for (OrderItem item : items) {
                if (item != null) {
                    sb.append("  - ").append(item).append("\n");
                }
            }
            sb.append("品項數量: ").append(getItemCount()).append("\n");
            sb.append("訂單總額: ").append(getTotalAmount());
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 訂單與顧客管理 =====");

        Customer customer = new Customer("C001", "陳小美");

        OrderItem[] items = {
            new OrderItem("鍵盤", 890, 1),
            new OrderItem("滑鼠", 490, 2),
            new OrderItem("USB 線", 150, 3)
        };

        CustomerOrder order = new CustomerOrder("ORD-1001", customer, items);
        System.out.println(order.summary());
    }
}
