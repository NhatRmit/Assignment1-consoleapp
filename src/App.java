import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        EnrolmentManager em = new EnrolmentManager();
        em.readData();
        for(Student s : em.getStudentList()){
            System.out.println(s);
        }
        System.out.println("------------");
        for(Course c : em.getCourseList()){
            System.out.println(c);
        }
        System.out.println("------------");
        for(StudentEnrolment se : em.getEnrolmentList()){
            System.out.println(se);
        }

    }
}