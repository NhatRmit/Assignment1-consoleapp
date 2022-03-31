import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class EnrolmentManagerTest {

    private static EnrolmentManager enrolment = new EnrolmentManager();

    @BeforeAll
    static void beforeAll() throws FileNotFoundException {
        enrolment.readData("default");
        
    }

    @Test
    void testAdd() {
        System.out.println("Test Add");
        String studentID, courseID, semester;
        StudentEnrolment se;

        // Test Case: Enrolled Successfully
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";

        enrolment.add(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, !enrolment.getEnrolmentList().contains(se));


        //Test Case: Enrolled Already
    //     studentID = "s101312";
    //     courseID = "bus2232";
    //     semester = "2020C";
    //     enrolment.add(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
    //     se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
    //     Assertions.assertEquals(true, enrolment.getEnrolmentList().contains(se));
    }

    @Test
    void testUpdate() {
        System.out.println("Test Update");
        String studentID, courseID, semester, option;
        StudentEnrolment se;

        // Test Case: Updated Successfully 
        //(Add New)
        option = "1";
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";
        enrolment.update(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester, option);
        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, !enrolment.getEnrolmentList().contains(se));

        //(Delete)
        option = "2";
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";
        enrolment.update(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester, option);
        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, !enrolment.getEnrolmentList().contains(se));


        // Test Case: Updated Unsuccessfully
        //(Add New)
        option = "1";
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";
        enrolment.update(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester, option);
        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, enrolment.getEnrolmentList().contains(se));
    
        //(Delete)
        option = "2";
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";
        enrolment.update(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester, option);
        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, enrolment.getEnrolmentList().contains(se));


    }

    @Test
    void testDelete() {
        System.out.println("Test Delete");
        String studentID, courseID, semester;
        StudentEnrolment se;        
        
        // Test Case: Removed Successfully
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";

        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        enrolment.delete(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, !enrolment.getEnrolmentList().contains(se));

        //Test Case: Removed Unsuccessfully
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";
    
        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        enrolment.delete(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, enrolment.getEnrolmentList().contains(se));
    }

    @Test
    void testGetOne(){
        System.out.println("Test Get One");
        String studentID, courseID, semester;
        StudentEnrolment se;        

        //Test Case: Get One Successfully
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";

        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, !enrolment.getEnrolmentList().contains(se));

        //Test Case: Get One Unsuccessfully
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";

        se = enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        enrolment.getOne(enrolment.verifyStudent(studentID), enrolment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, enrolment.getEnrolmentList().contains(se));
    }

    @Test 
    void testGetAll(){
    }

}