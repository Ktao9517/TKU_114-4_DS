import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private final Deque<String> history = new ArrayDeque<>();
    private String current = null;

    public void visit(String url) {
        if (current != null) {
            history.push(current);
        }
        current = url;
        System.out.println("訪問: " + url + " | 目前頁面: " + current);
    }

    public void back() {
        if (history.isEmpty()) {
            System.out.println("無法返回，沒有上一頁");
            return;
        }
        current = history.pop();
        System.out.println("返回 → 目前頁面: " + current);
    }

    public String current() {
        return current;
    }

    public static void main(String[] args) {
        System.out.println("===== 瀏覽器返回功能 =====");
        BrowserBackStack browser = new BrowserBackStack();

        browser.visit("https://home.com");
        browser.visit("https://news.com");
        browser.visit("https://sports.com");
        browser.back();
        browser.back();
        browser.visit("https://tech.com");
        browser.back();
        browser.back(); // 空 stack 測試
        browser.back(); // 再次空 stack

        System.out.println("最終目前頁面: " + browser.current());
    }
}
