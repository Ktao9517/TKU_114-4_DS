public class GenericResultDemo {

    static class Result<T> {
        private final boolean success;
        private final String message;
        private final T data;

        public Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message == null ? "" : message;
            this.data = data;
        }

        public static <T> Result<T> ok(T data) {
            return new Result<>(true, "OK", data);
        }

        public static <T> Result<T> fail(String message) {
            return new Result<>(false, message, null);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Result{success=" + success + ", message='" + message + "', data=" + data + "}";
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Generic Result =====");

        Result<String> nameResult = Result.ok("王小明");
        Result<Integer> scoreResult = Result.ok(95);
        Result<String> failResult = Result.fail("查無資料");

        System.out.println(nameResult);
        System.out.println(scoreResult);
        System.out.println(failResult);

        
        String name = nameResult.getData();
        Integer score = scoreResult.getData();
        System.out.println("姓名: " + name);
        System.out.println("分數: " + score);
        System.out.println("失敗資料: " + failResult.getData());
    }
}
