public interface StudentEnrolmentManager{
    public void add(Student student, Course course, String semester);
    public void update(Student student, Course course, String semester, String option);
    public void delete(Student student, Course course, String semester);
    public StudentEnrolment getOne(Student student, Course course, String semester);
    public void getAll();
}