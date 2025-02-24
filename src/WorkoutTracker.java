public class WorkoutTracker extends Tracker {
    private int duration; // Duration in minutes

    public WorkoutTracker(String name, String date, int duration) {
        super(name, date); // Call the parent class constructor
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void logDetails() {
        System.out.println("Workout: " + name + ", Date: " + date + ", Duration: " + duration + " minutes");
    }
}
