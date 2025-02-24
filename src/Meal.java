public class Meal {
    private String mealName;
    private String date;
    private double calories;

    public Meal(String mealName, String date, double calories) {
        this.mealName = mealName;
        this.date = date;
        this.calories = calories;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
