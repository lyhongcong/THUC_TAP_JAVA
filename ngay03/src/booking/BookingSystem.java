package booking;

import java.util.*;

public class BookingSystem {
    private static ArrayList<Event> events = new ArrayList<>();
    private static LinkedList<Booking> bookings = new LinkedList<>();
    private static HashMap<String, Integer> bookingStats = new HashMap<>();
    private static HashSet<String> bookedSeats = new HashSet<>();

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== HỆ THỐNG ĐẶT VÉ SỰ KIỆN ===");
            System.out.println("1. Thêm sự kiện");
            System.out.println("2. Danh sách sự kiện");
            System.out.println("3. Tìm kiếm sự kiện");
            System.out.println("4. Sắp xếp sự kiện theo tên");
            System.out.println("5. Đặt vé");
            System.out.println("6. Hiển thị tất cả vé đã đặt");
            System.out.println("7. Thống kê lượt đặt vé");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> addEvent();
                case 2 -> listEvents();
                case 3 -> searchEvent();
                case 4 -> sortEvents();
                case 5 -> bookSeat();
                case 6 -> listBookings();
                case 7 -> showStats();
                case 0 -> {
                    running = false;
                    System.out.println("Thoát chương trình.");
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addEvent() {
        System.out.print("Nhập mã sự kiện: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên sự kiện: ");
        String name = sc.nextLine();
        System.out.print("Nhập địa điểm: ");
        String loc = sc.nextLine();
        System.out.print("Nhập ngày tổ chức (dd/MM/yyyy): ");
        String date = sc.nextLine();
        System.out.print("Nhập số chỗ tối đa: ");
        int maxSeats = Integer.parseInt(sc.nextLine());

        events.add(new Event(id, name, loc, date, maxSeats));
        System.out.println(" Đã thêm sự kiện!");
    }

    private static void listEvents() {
        if (events.isEmpty()) {
            System.out.println(" Chưa có sự kiện nào.");
            return;
        }
        System.out.println("\n Danh sách sự kiện:");
        for (Event e : events) {
            System.out.println(e);
        }
    }

    private static void searchEvent() {
        System.out.print("Nhập từ khoá tên sự kiện: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Event e : events) {
            if (e.getEventName().toLowerCase().contains(keyword)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println(" Không tìm thấy sự kiện nào phù hợp.");
        }
    }

    private static void sortEvents() {
        events.sort(Comparator.comparing(Event::getEventName));
        System.out.println(" Đã sắp xếp sự kiện theo tên:");
        listEvents();
    }

    private static void bookSeat() {
        System.out.print("Nhập email người đặt: ");
        String email = sc.nextLine();
        System.out.print("Nhập mã sự kiện: ");
        String eventId = sc.nextLine();
        System.out.print("Nhập số ghế: ");
        int seatNum = Integer.parseInt(sc.nextLine());

        String seatKey = eventId + "-" + seatNum;

        if (bookedSeats.contains(seatKey)) {
            System.out.println(" Chỗ ngồi đã được đặt trước!");
            return;
        }

        bookedSeats.add(seatKey);
        bookings.add(new Booking(email, eventId, seatNum));
        bookingStats.put(eventId, bookingStats.getOrDefault(eventId, 0) + 1);

        System.out.println(" Đặt vé thành công!");
    }

    private static void listBookings() {
        if (bookings.isEmpty()) {
            System.out.println(" Chưa có vé nào được đặt.");
            return;
        }
        System.out.println("\n Danh sách vé đã đặt (theo thứ tự đặt):");
        Iterator<Booking> itr = bookings.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    private static void showStats() {
        if (bookingStats.isEmpty()) {
            System.out.println(" Chưa có dữ liệu thống kê.");
            return;
        }
        System.out.println("\n Thống kê lượt đặt vé theo sự kiện:");
        for (String eventId : bookingStats.keySet()) {
            System.out.println("Mã sự kiện: " + eventId + " → " + bookingStats.get(eventId) + " lượt đặt");
        }
    }
}
