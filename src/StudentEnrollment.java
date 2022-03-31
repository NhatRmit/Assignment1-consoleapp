public class StudentEnrollment {
    private String semester;
    private Student student;
    private Course course;

    public StudentEnrollment(){
        semester = "default";
        student = null;
        course = null;
    }
    
    public StudentEnrollment(Student student, Course course, String semester){
        this.semester = semester;
        this.student = student;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString(){
        return student.toString() + " " + course.toString() + " " + semester;
    }

}