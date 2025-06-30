import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManager {
    static ArrayList<Student> students = new ArrayList<>();
    static int nextId = 1; // id tự tăng từ 1
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    showStudents();
                    break;
                case 3:
                    findStudentByName();
                    break;
                case 4:
                    findHighestScore();
                    break;
                case 5:
                    sortByScoreDesc();
                    break;
                case 6:
                    calcAverageScore();
                    break;
                case 7:
                    calcFactorialOfFirstStudent();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Thêm sinh viên mới");
        System.out.println("2. Hiển thị danh sách");
        System.out.println("3. Tìm sinh viên theo tên");
        System.out.println("4. Tìm sinh viên có điểm cao nhất");
        System.out.println("5. Sắp xếp theo điểm giảm dần");
        System.out.println("6. Tính điểm trung bình");
        System.out.println("7. Tính giai thừa tuổi sinh viên đầu tiên");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng: ");
    }
    public static void addStudent() {
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();

        System.out.print("Nhập tuổi: ");
        int age = sc.nextInt();
        if (age <= 0) {
            System.out.println("Tuổi phải lớn hơn 0!");
            return;
        }

        System.out.print("Nhập điểm: ");
        double score = sc.nextDouble();
        if (score < 0 || score > 10) {
            System.out.println("Điểm phải từ 0 đến 10!");
            return;
        }

        Student s = new Student(nextId, name, age, score);
        students.add(s);
        nextId++;
        System.out.println("Thêm thành công!");
    }
    public static void showStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sách rỗng!");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }
    public static void findStudentByName() {
        System.out.print("Nhập tên cần tìm: ");
        String keyword = sc.nextLine();
        boolean found = false;

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(s);
                found = true;
            }
        }

        if (!found) System.out.println("Không tìm thấy!");
    }
    public static void findHighestScore() {
        if (students.isEmpty()) {
            System.out.println("Danh sách rỗng!");
            return;
        }
        Student top = students.get(0);
        for (Student s : students) {
            if (s.getScore() > top.getScore()) {
                top = s;
            }
        }
        System.out.println("Sinh viên điểm cao nhất: " + top);
    }
    public static void sortByScoreDesc() {
        students.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        System.out.println("Đã sắp xếp theo điểm giảm dần.");
    }
    public static void calcAverageScore() {
        if (students.isEmpty()) {
            System.out.println("Danh sách rỗng!");
            return;
        }
        double sum = 0;
        for (Student s : students) sum += s.getScore();
        double avg = sum / students.size();
        System.out.printf("Điểm trung bình: %.2f\n", avg);
    }
    public static void calcFactorialOfFirstStudent() {
        if (students.isEmpty()) {
            System.out.println("Danh sách rỗng!");
            return;
        }
        int age = students.get(0).getAge();
        long result = factorial(age);
        System.out.println("Giai thừa tuổi (" + age + ") là: " + result);
    }

    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

}
