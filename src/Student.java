public class Student {
    private String id;
    private String name;
    private String birthdate;

    public Student() {
        id = "0000";
        name = "default";
        birthdate = "######";
    }

    public Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + birthdate;
    }

}
