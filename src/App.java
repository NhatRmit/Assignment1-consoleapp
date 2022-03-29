import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        EnrolmentManager em = new EnrolmentManager();
        em.readData();        
        // em.add();
        // System.out.println("------------");
        // for(StudentEnrolment se : em.getEnrolmentList()){
        //     System.out.println(se);
        
        // em.delete();
        em.printAll();
        // em.update();
        //  System.out.println("------------");
        // for(StudentEnrolment se : em.getEnrolmentList()){
        //     System.out.println(se);
        // }
            // em.test();
        // em.getOne();
    }
}