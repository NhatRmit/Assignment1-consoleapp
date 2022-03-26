import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        EnrolmentManager em = new EnrolmentManager();
        em.readData();
        for(StudentEnrolment s : em.getEnrolmentList()){
            System.out.println(s);
        }
    }
}