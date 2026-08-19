public class CircularQueuePractice {

    static class CircularQueue<T> {
        private final Object[] data;
        private int front;
        private int rear;
        private int size;
        private final int capacity;

        public CircularQueue(int capacity) {
            this.capacity = capacity;
            this.data = new Object[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        public boolean enqueue(T value) {
            if (isFull()) {
                System.out.println("Queue 已滿，無法 enqueue " + value);
                return false;
            }
            rear = (rear + 1) % capacity;
            data[rear] = value;
            size++;
            printState("enqueue " + value);
            return true;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (isEmpty()) {
                System.out.println("Queue 為空，無法 dequeue");
                return null;
            }
            T value = (T) data[front];
            data[front] = null;
            front = (front + 1) % capacity;
            size--;
            printState("dequeue " + value);
            return value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public int size() {
            return size;
        }

        private void printState(String op) {
            System.out.print(op + " → array=[");
            for (int i = 0; i < capacity; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(data[i] == null ? "_" : data[i]);
            }
            System.out.println("] front=" + front + " rear=" + rear + " size=" + size);
        }

        public void drainAll() {
            System.out.println("\n依 FIFO 取出所有元素:");
            while (!isEmpty()) {
                System.out.println("  " + dequeue());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Circular queue 狀態追蹤 (容量 4) =====");
        CircularQueue<String> q = new CircularQueue<>(4);

        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        q.dequeue();
        q.dequeue();
        q.enqueue("D");
        q.enqueue("E");
        q.enqueue("F");
        q.dequeue();
        q.enqueue("G");

        q.drainAll();
    }
}
