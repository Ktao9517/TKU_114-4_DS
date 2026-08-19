import java.util.ArrayDeque;
import java.util.Deque;

public class CounterWaitingQueue {

    static class Customer {
        private final String id;
        private final String name;

        public Customer(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + "-" + name;
        }
    }

    private final Deque<Customer> queue = new ArrayDeque<>();

    public void join(Customer c) {
        queue.offerLast(c);
        System.out.println("加入: " + c + "，目前等候: " + queue.size());
    }

    public Customer peekNext() {
        if (queue.isEmpty()) {
            System.out.println("目前無人等候");
            return null;
        }
        Customer next = queue.peekFirst();
        System.out.println("下一位: " + next);
        return next;
    }

    public Customer serveNext() {
        if (queue.isEmpty()) {
            System.out.println("空隊列，無法服務");
            return null;
        }
        Customer served = queue.pollFirst();
        System.out.println("服務: " + served + "，剩餘等候: " + queue.size());
        return served;
    }

    public int waitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        System.out.println("===== 櫃台等候 Queue =====");
        CounterWaitingQueue counter = new CounterWaitingQueue();

        counter.join(new Customer("C01", "王小明"));
        counter.join(new Customer("C02", "陳小美"));
        counter.join(new Customer("C03", "林大同"));

        counter.peekNext();
        counter.serveNext();
        counter.serveNext();
        System.out.println("目前等候數: " + counter.waitingCount());
        counter.serveNext();
        counter.serveNext(); // 空隊列
        counter.peekNext();  // 空隊列
    }
}
