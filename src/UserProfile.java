public class UserProfile {
    private String name;
    private int age;
    private double weight;
    private double height;
    private String goal;

    // Constructor
    public UserProfile(String name, int age, double weight, double height, String goal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
