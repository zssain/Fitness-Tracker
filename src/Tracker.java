public abstract class Tracker {
    protected String name;
    protected String date;

    public Tracker(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    // Abstract method to be implemented by subclasses
    public abstract void logDetails();
}

