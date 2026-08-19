import java.util.Objects;

public class GenericArrayTools {

    public static <T> int countMatches(T[] data, T target) {
        if (data == null) return 0;
        int count = 0;
        for (T item : data) {
            if (Objects.equals(item, target)) {
                count++;
            }
        }
        return count;
    }

    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    public static <T> void swap(T[] data, int first, int second) {
        if (data == null) return;
        if (first < 0 || second < 0 || first >= data.length || second >= data.length) {
            System.out.println("不合法 index，不進行交換");
            return;
        }
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        System.out.println("===== Generic 陣列工具 =====");

        String[] names = {"Amy", "Ben", "Cara", "Ben", "Dan"};
        Integer[] numbers = {10, 20, 30, 20, 40};

        System.out.println("countMatches(names, \"Ben\") = " + countMatches(names, "Ben"));
        System.out.println("countMatches(numbers, 20) = " + countMatches(numbers, 20));
        System.out.println("countMatches(null, 1) = " + countMatches(null, 1));

        System.out.println("last(names) = " + last(names));
        System.out.println("last(empty) = " + last(new String[0]));
        System.out.println("last(null) = " + last(null));

        System.out.println("\n交換前: " + java.util.Arrays.toString(names));
        swap(names, 0, 2);
        System.out.println("交換後: " + java.util.Arrays.toString(names));
        swap(names, -1, 0); // 不合法
        swap(names, 0, 99);  // 不合法
    }
}
