public interface StudentEnrolmentManager{
    public void add(Student student, String semester, Course course);
    public void update(Student student, String semester);
    public void delete(Student student, String semester);
    public StudentEnrolment getOne(Student student, Course course, String semester);
    public void getAll();
}