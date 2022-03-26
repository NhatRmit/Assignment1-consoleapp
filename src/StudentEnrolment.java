public class StudentEnrolment {
    private String semester;
    private Student student;
    private Course course;

    public StudentEnrolment(){
        semester = "default";
        student = null;
        course = null;
    }
    
    public StudentEnrolment(Student student, Course course, String semester){
        this.semester = semester;
        this.student = student;
        this.course = course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString(){
        return student.toString() + " " + course.toString() + " " + semester;
    }

}