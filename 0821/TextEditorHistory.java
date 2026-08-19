import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private String content = "";
    private final Deque<String> undoStack = new ArrayDeque<>();
    private final Deque<String> redoStack = new ArrayDeque<>();

    public void type(String text) {
        undoStack.push(content);
        redoStack.clear(); // 新操作清空 redo
        content = content + text;
        printState("輸入 \"" + text + "\"");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("無法 Undo，歷史為空");
            return;
        }
        redoStack.push(content);
        content = undoStack.pop();
        printState("Undo");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("無法 Redo，Redo 為空");
            return;
        }
        undoStack.push(content);
        content = redoStack.pop();
        printState("Redo");
    }

    private void printState(String op) {
        System.out.println(op + " → 內容: \"" + content + "\" | undo=" + undoStack.size() + " redo=" + redoStack.size());
    }

    public static void main(String[] args) {
        System.out.println("===== 文字編輯 Undo/Redo =====");
        TextEditorHistory editor = new TextEditorHistory();

        editor.type("Hello");
        editor.type(" World");
        editor.type("!");
        editor.undo();
        editor.undo();
        editor.redo();
        editor.type(" Java");
        editor.undo();
        editor.undo();
        editor.undo(); // 空
        editor.redo();
        editor.redo();
    }
}
