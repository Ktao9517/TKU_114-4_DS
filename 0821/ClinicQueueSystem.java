import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class ClinicQueueSystem {

    static class Patient {
        private final String medicalId;
        private final String name;

        public Patient(String medicalId, String name) {
            this.medicalId = medicalId;
            this.name = name;
        }

        public String getMedicalId() {
            return medicalId;
        }

        @Override
        public String toString() {
            return medicalId + "-" + name;
        }
    }

    private final Deque<Patient> waiting = new ArrayDeque<>();
    private final List<Patient> completed = new ArrayList<>();

    public void register(Patient p) {
        waiting.offerLast(p);
        System.out.println("掛號: " + p + "，等候人數: " + waiting.size());
    }

    public boolean cancel(String medicalId) {
        Iterator<Patient> it = waiting.iterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p.getMedicalId().equals(medicalId)) {
                it.remove();
                System.out.println("取消: " + p + "，剩餘等候: " + waiting.size());
                return true;
            }
        }
        System.out.println("找不到病歷號: " + medicalId);
        return false;
    }

    public Patient callNext() {
        if (waiting.isEmpty()) {
            System.out.println("目前無人等候");
            return null;
        }
        Patient p = waiting.pollFirst();
        completed.add(p);
        System.out.println("叫號: " + p + "，剩餘等候: " + waiting.size());
        return p;
    }

    public Patient peekNext() {
        if (waiting.isEmpty()) {
            System.out.println("目前無人等候");
            return null;
        }
        return waiting.peekFirst();
    }

    public void printCompleted() {
        System.out.println("當日完成清單: " + completed);
    }

    public static void main(String[] args) {
        System.out.println("===== 診所掛號系統 =====");
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.register(new Patient("M001", "王小明"));
        clinic.register(new Patient("M002", "陳小美"));
        clinic.register(new Patient("M003", "林大同"));
        clinic.register(new Patient("M004", "張三"));

        System.out.println("下一位: " + clinic.peekNext());
        clinic.callNext();
        clinic.cancel("M003");
        clinic.callNext();
        clinic.cancel("M999"); 
        clinic.callNext();
        clinic.callNext();
        clinic.callNext(); 
        clinic.printCompleted();
    }
}
