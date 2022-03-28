public interface StudentEnrolmentManager{
    public void add();
    public void update();
    public void delete();
    public StudentEnrolment getOne(Student student, Course course, String semester);
    public void getAll();
}