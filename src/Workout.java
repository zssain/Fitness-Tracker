public class Workout {
    private String workoutName;
    private String date;
    private double duration;

    public Workout(String workoutName, String date, double duration) {
        this.workoutName = workoutName;
        this.date = date;
        this.duration = duration;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
