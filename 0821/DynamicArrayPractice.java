public class DynamicArrayPractice {

    static class DynamicArray<T> {
        private Object[] data;
        private int size;

        public DynamicArray() {
            data = new Object[2];
            size = 0;
        }

        public void add(T value) {
            ensureCapacity(size + 1);
            data[size++] = value;
        }

        public void add(int index, T value) {
            if (index < 0 || index > size) {
                System.out.println("不合法 index: " + index);
                return;
            }
            ensureCapacity(size + 1);
            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }
            data[index] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            if (index < 0 || index >= size) {
                System.out.println("不合法 index: " + index);
                return null;
            }
            return (T) data[index];
        }

        @SuppressWarnings("unchecked")
        public T set(int index, T value) {
            if (index < 0 || index >= size) {
                System.out.println("不合法 index: " + index);
                return null;
            }
            T old = (T) data[index];
            data[index] = value;
            return old;
        }

        @SuppressWarnings("unchecked")
        public T remove(int index) {
            if (index < 0 || index >= size) {
                System.out.println("不合法 index 或空結構刪除: " + index);
                return null;
            }
            T removed = (T) data[index];
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            data[--size] = null; // 最後一個無效格設為 null
            return removed;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return data.length;
        }

        private void ensureCapacity(int minCapacity) {
            if (minCapacity > data.length) {
                int newCap = data.length * 2;
                Object[] newData = new Object[newCap];
                for (int i = 0; i < size; i++) {
                    newData[i] = data[i];
                }
                data = newData;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                if (i > 0) sb.append(", ");
                sb.append(data[i]);
            }
            sb.append("] size=").append(size).append(" capacity=").append(capacity());
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Dynamic array 插入與刪除 =====");

        DynamicArray<String> strArr = new DynamicArray<>();
        strArr.add("A");
        strArr.add("B");
        strArr.add("C"); // 觸發擴充
        System.out.println(strArr);
        strArr.add(1, "X");
        System.out.println("插入後: " + strArr);
        System.out.println("get(2): " + strArr.get(2));
        strArr.set(0, "Z");
        System.out.println("set 後: " + strArr);
        System.out.println("remove(1): " + strArr.remove(1));
        System.out.println("移除後: " + strArr);

        System.out.println("\n--- 邊界測試 ---");
        strArr.get(-1);
        strArr.remove(strArr.size());
        DynamicArray<Integer> empty = new DynamicArray<>();
        empty.remove(0);

        System.out.println("\n--- Integer 測試 ---");
        DynamicArray<Integer> intArr = new DynamicArray<>();
        intArr.add(10);
        intArr.add(20);
        intArr.add(0, 5);
        System.out.println(intArr);
        System.out.println("remove(1): " + intArr.remove(1));
        System.out.println(intArr);
    }
}
