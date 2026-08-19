import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardNumberTools {

    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Number n : values) {
            sum += n.doubleValue();
        }
        return sum / values.size();
    }

    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        double max = values.get(0).doubleValue();
        for (Number n : values) {
            double v = n.doubleValue();
            if (v > max) max = v;
        }
        return max;
    }

    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Wildcard 數值工具 =====");

        List<Integer> ints = Arrays.asList(10, 20, 30, 40);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);

        System.out.println("Integer average: " + average(ints));
        System.out.println("Double average: " + average(doubles));
        System.out.println("Empty average: " + average(new ArrayList<>()));

        System.out.println("Integer maximum: " + maximum(ints));
        System.out.println("Double maximum: " + maximum(doubles));
        System.out.println("Empty maximum: " + maximum(new ArrayList<>()));

        List<Number> numbers = new ArrayList<>();
        addRange(numbers, 1, 5);
        System.out.println("addRange 1~5: " + numbers);

        List<Object> objects = new ArrayList<>();
        addRange(objects, 10, 12);
        System.out.println("addRange 10~12: " + objects);

        addRange(numbers, 5, 3); // start > end，不加入
        System.out.println("start > end 後: " + numbers);
    }
}
