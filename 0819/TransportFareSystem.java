public class TransportFareSystem {

    abstract static class Transport {
        protected String routeName;

        public Transport(String routeName) {
            this.routeName = (routeName == null || routeName.trim().isEmpty()) ? "Unknown" : routeName.trim();
        }

        public String getRouteName() {
            return routeName;
        }

        public abstract int calculateFare(int distance);
    }

    static class Bus extends Transport {
        public Bus(String routeName) {
            super(routeName);
        }

        @Override
        public int calculateFare(int distance) {
            // 公車：基本 15 元 + 每公里 2 元
            if (distance <= 0) return 15;
            return 15 + distance * 2;
        }
    }

    static class Taxi extends Transport {
        public Taxi(String routeName) {
            super(routeName);
        }

        @Override
        public int calculateFare(int distance) {
            // 計程車：起程 70 元 + 每公里 5 元
            if (distance <= 0) return 70;
            return 70 + distance * 5;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 交通票價多型系統 =====");

        Transport[] transports = {
            new Bus("台北-板橋"),
            new Bus("台北-新店"),
            new Taxi("機場接送"),
            new Taxi("市區短程")
        };

        int[] distances = {10, 15, 30, 5};

        for (int i = 0; i < transports.length; i++) {
            Transport t = transports[i];
            int fare = t.calculateFare(distances[i]); // 多型呼叫
            System.out.println(t.getRouteName() + " (" + t.getClass().getSimpleName() +
                    ") 距離 " + distances[i] + " km → 票價: " + fare + " 元");
        }
    }
}
