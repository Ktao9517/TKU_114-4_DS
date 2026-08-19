import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServiceCenterWorkflow {

    static class ServiceTicket {
        private final String id;
        private final String customerName;
        private boolean completed;

        public ServiceTicket(String id, String customerName) {
            this.id = id;
            this.customerName = customerName;
            this.completed = false;
        }

        public String getId() {
            return id;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public String toString() {
            return id + "-" + customerName + (completed ? "[完成]" : "[等待]");
        }
    }

    private final Map<String, ServiceTicket> byId = new HashMap<>();
    private final Deque<ServiceTicket> waiting = new ArrayDeque<>();
    private final Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private final Set<String> idSet = new HashSet<>();

    public boolean createTicket(String id, String name) {
        if (id == null || id.trim().isEmpty() || idSet.contains(id)) {
            System.out.println("重複或無效 id，無法建立: " + id);
            return false;
        }
        ServiceTicket ticket = new ServiceTicket(id, name);
        byId.put(id, ticket);
        idSet.add(id);
        waiting.offerLast(ticket);
        System.out.println("建立: " + ticket + "，等待數: " + waiting.size());
        return true;
    }

    public ServiceTicket processNext() {
        if (waiting.isEmpty()) {
            System.out.println("空 Queue，無法處理");
            return null;
        }
        ServiceTicket ticket = waiting.pollFirst();
        ticket.setCompleted(true);
        completedStack.push(ticket);
        System.out.println("處理: " + ticket);
        return ticket;
    }

    public boolean cancelWaiting(String id) {
        if (!idSet.contains(id)) {
            System.out.println("取消失敗，id 不存在: " + id);
            return false;
        }
        ServiceTicket ticket = byId.get(id);
        if (ticket.completed) {
            System.out.println("已完成的 ticket 無法取消: " + id);
            return false;
        }
        boolean removed = waiting.remove(ticket);
        if (removed) {
            System.out.println("取消等待中: " + ticket);
        } else {
            System.out.println("取消失敗: " + id);
        }
        return removed;
    }

    public ServiceTicket undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("沒有可 undo 的完成紀錄");
            return null;
        }
        ServiceTicket ticket = completedStack.pop();
        ticket.setCompleted(false);
        waiting.offerFirst(ticket); 
        System.out.println("Undo: " + ticket + " 回到等待前端");
        return ticket;
    }

    public ServiceTicket findById(String id) {
        return byId.get(id);
    }

    public void printSummary() {
        System.out.println("--- Summary ---");
        System.out.println("等待 Queue: " + waiting);
        System.out.println("完成 Stack: " + completedStack);
        System.out.println("註冊總數: " + byId.size());
    }

    public static void main(String[] args) {
        System.out.println("===== 服務中心排隊與取消 =====");
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();

        center.createTicket("T001", "王小明");
        center.createTicket("T002", "陳小美");
        center.createTicket("T003", "林大同");
        center.createTicket("T001", "重複"); 

        center.processNext();
        center.processNext();
        center.cancelWaiting("T003");
        center.cancelWaiting("T999"); 
        center.cancelWaiting("T001"); 

        center.undoLastCompletion();
        center.undoLastCompletion();
        center.undoLastCompletion(); 

        center.printSummary();
        System.out.println("find T002: " + center.findById("T002"));
    }
}
