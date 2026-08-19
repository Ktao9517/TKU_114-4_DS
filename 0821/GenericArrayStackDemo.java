public class GenericArrayStackDemo {

    static class ArrayStack<T> {
        private final Object[] elements;
        private int size;
        private final int capacity;

        public ArrayStack(int capacity) {
            this.capacity = Math.max(capacity, 1);
            this.elements = new Object[this.capacity];
            this.size = 0;
        }

        public boolean push(T value) {
            if (isFull()) {
                System.out.println("Stack 已滿，無法 push");
                return false;
            }
            elements[size++] = value;
            return true;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) {
                System.out.println("Stack 為空，無法 pop");
                return null;
            }
            T value = (T) elements[--size];
            elements[size] = null;
            return value;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                System.out.println("Stack 為空，無法 peek");
                return null;
            }
            return (T) elements[size - 1];
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                if (i > 0) sb.append(", ");
                sb.append(elements[i]);
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 固定容量 Generic Stack =====");

        ArrayStack<String> stringStack = new ArrayStack<>(3);
        stringStack.push("A");
        stringStack.push("B");
        stringStack.push("C");
        stringStack.push("D"); 
        System.out.println("String Stack: " + stringStack + " size=" + stringStack.size());
        System.out.println("peek: " + stringStack.peek());
        System.out.println("pop: " + stringStack.pop());
        System.out.println("pop 後: " + stringStack);

        System.out.println();
        ArrayStack<Integer> intStack = new ArrayStack<>(2);
        intStack.push(10);
        intStack.push(20);
        System.out.println("Integer Stack: " + intStack);
        System.out.println("isFull: " + intStack.isFull());
        System.out.println("pop: " + intStack.pop());
        System.out.println("pop: " + intStack.pop());
        System.out.println("pop (空): " + intStack.pop());
        System.out.println("isEmpty: " + intStack.isEmpty());
    }
}
