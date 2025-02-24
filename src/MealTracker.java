public class MealTracker extends Tracker {
    private int calories;

    public MealTracker(String name, String date, int calories) {
        super(name, date); // Call the parent class constructor
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public void logDetails() {
        System.out.println("Meal: " + name + ", Date: " + date + ", Calories: " + calories);
    }
}
