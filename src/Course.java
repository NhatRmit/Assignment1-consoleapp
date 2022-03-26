public class Course {
    private String id;
    private String name;
    private int credits;

    public Course(){
        id = "0000";
        name = "default";
        credits = 0;
    }

    public Course(String id, String name, int credits){
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString(){
        return id + " " + name + " " + credits;
    }
}
