public class Student {
    private int id;
    private String name;
    private int age;
    private double score;

    // Constructor
    public Student(int id, String name, int age, double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    // Getter & Setter
    public int getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public double getScore() { return score; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name +
                ", Age: " + age + ", Score: " + score;
    }
}
