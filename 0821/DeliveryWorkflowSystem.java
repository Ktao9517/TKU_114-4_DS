import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DeliveryWorkflowSystem {

    static class DeliveryTask {
        private final String id;
        private final String address;

        public DeliveryTask(String id, String address) {
            this.id = id;
            this.address = address;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " → " + address;
        }
    }

    private final Map<String, DeliveryTask> byId = new HashMap<>();
    private final Deque<DeliveryTask> waiting = new ArrayDeque<>();
    private final Deque<DeliveryTask> completed = new ArrayDeque<>();

    public boolean add(DeliveryTask task) {
        if (task == null || byId.containsKey(task.getId())) {
            System.out.println("重複或無效 id，無法新增: " + (task == null ? "null" : task.getId()));
            return false;
        }
        byId.put(task.getId(), task);
        waiting.offerLast(task);
        System.out.println("新增: " + task + "，等待: " + waiting.size());
        return true;
    }

    public DeliveryTask processNext() {
        if (waiting.isEmpty()) {
            System.out.println("沒有待配送工作");
            return null;
        }
        DeliveryTask task = waiting.pollFirst();
        completed.push(task);
        System.out.println("處理完成: " + task);
        return task;
    }

    public DeliveryTask undo() {
        if (completed.isEmpty()) {
            System.out.println("沒有可復原的完成紀錄");
            return null;
        }
        DeliveryTask task = completed.pop();
        waiting.offerFirst(task);
        System.out.println("復原: " + task + " 回到等待前端");
        return task;
    }

    public DeliveryTask find(String id) {
        return byId.get(id);
    }

    public void printStats() {
        System.out.println("統計 → 等待: " + waiting.size() + "，完成: " + completed.size() + "，總註冊: " + byId.size());
    }

    public static void main(String[] args) {
        System.out.println("===== 物流工作流程 =====");
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();

        system.add(new DeliveryTask("D001", "台北市"));
        system.add(new DeliveryTask("D002", "新北市"));
        system.add(new DeliveryTask("D001", "重複測試")); 
        system.add(new DeliveryTask("D003", "桃園市"));

        system.processNext();
        system.processNext();
        system.undo();
        System.out.println("查詢 D002: " + system.find("D002"));
        system.printStats();
        system.processNext();
        system.printStats();
    }
}
