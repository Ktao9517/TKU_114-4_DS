public class LinkedTaskListSystem {

    static class Task {
        private final String id;
        private final String description;

        public Task(String id, String description) {
            this.id = id;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + ": " + description;
        }
    }

    static class TaskNode {
        Task task;
        TaskNode next;

        TaskNode(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    static class TaskLinkedList {
        private TaskNode head;
        private int size;

        public boolean addFirst(Task task) {
            if (task == null || containsId(task.getId())) {
                System.out.println("無法新增（null 或重複 id）: " + (task == null ? "null" : task.getId()));
                return false;
            }
            TaskNode node = new TaskNode(task);
            node.next = head;
            head = node;
            size++;
            return true;
        }

        public boolean addLast(Task task) {
            if (task == null || containsId(task.getId())) {
                System.out.println("無法新增（null 或重複 id）: " + (task == null ? "null" : task.getId()));
                return false;
            }
            TaskNode node = new TaskNode(task);
            if (head == null) {
                head = node;
            } else {
                TaskNode curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = node;
            }
            size++;
            return true;
        }

        public Task findById(String id) {
            TaskNode curr = head;
            while (curr != null) {
                if (curr.task.getId().equals(id)) {
                    return curr.task;
                }
                curr = curr.next;
            }
            return null;
        }

        public boolean removeById(String id) {
            if (head == null) {
                System.out.println("空 list，無法刪除");
                return false;
            }
            if (head.task.getId().equals(id)) {
                head = head.next;
                size--;
                System.out.println("刪除 head: " + id);
                return true;
            }
            TaskNode prev = head;
            TaskNode curr = head.next;
            while (curr != null) {
                if (curr.task.getId().equals(id)) {
                    prev.next = curr.next;
                    size--;
                    System.out.println("刪除: " + id);
                    return true;
                }
                prev = curr;
                curr = curr.next;
            }
            System.out.println("找不到 id: " + id);
            return false;
        }

        public boolean insertAfter(String existingId, Task task) {
            if (task == null || containsId(task.getId())) {
                System.out.println("無法插入（null 或重複 id）");
                return false;
            }
            TaskNode curr = head;
            while (curr != null) {
                if (curr.task.getId().equals(existingId)) {
                    TaskNode node = new TaskNode(task);
                    node.next = curr.next;
                    curr.next = node;
                    size++;
                    return true;
                }
                curr = curr.next;
            }
            System.out.println("找不到 existingId: " + existingId);
            return false;
        }

        public int size() {
            return size;
        }

        public void printAll() {
            System.out.print("List (size=" + size + "): ");
            TaskNode curr = head;
            while (curr != null) {
                System.out.print(curr.task + " -> ");
                curr = curr.next;
            }
            System.out.println("null");
        }

        private boolean containsId(String id) {
            return findById(id) != null;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 單向鏈結清單 =====");
        TaskLinkedList list = new TaskLinkedList();

        list.printAll(); // 空
        list.addLast(new Task("T1", "寫報告"));
        list.addLast(new Task("T2", "開會"));
        list.addFirst(new Task("T0", "緊急"));
        list.addLast(new Task("T1", "重複測試")); // 重複
        list.printAll();

        System.out.println("find T2: " + list.findById("T2"));
        list.insertAfter("T1", new Task("T1.5", "中間任務"));
        list.printAll();

        list.removeById("T0"); // 刪 head
        list.printAll();
        list.removeById("T1.5"); // 刪 middle
        list.printAll();
        list.removeById("T2"); // 刪 tail
        list.printAll();
        list.removeById("T999"); // 找不到
        list.removeById("T1");
        list.printAll();
        list.removeById("任何"); // 空 list
    }
}
