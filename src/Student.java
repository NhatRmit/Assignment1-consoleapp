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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return id + " " + name + " " + birthdate;
    }

}
