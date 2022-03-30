import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class EnrolmentManagerTest {

    private static EnrolmentManager enrolment = new EnrolmentManager();

    @BeforeAll
    static void beforeAll() throws FileNotFoundException {
        //Open Default File
        enrolment.readData();
    }

    @Test
    void testAdd() {

        // System.out.println("Test Add");

        // Test Case: Enrolment Available
        Student sID = new Student();
        Course cID = new Course();
        // StudentEnrolment se = new StudentEnrolment(enrolment.isStudentValid(sID), enrolment.isCourseValid(cID), "2020C");
        // Assertions.assertEquals(false, enrolment.add(se));

        // Test Case: Enrolment Not Available
        // sID = "S101312";
        // cID = "COSC3321";
        // se = new StudentEnrolment(enrolment.isStudentValid(sID), enrolment.isCourseValid(cID), "2020C");
        // Assertions.assertEquals(true, enrolment.add(se));
        System.out.println("Test Add");
        // Test Case: Enrolment Available
        ArrayList<StudentEnrolment> listTest =  enrolment.getEnrolmentList();
        int sizeListBefore;
        sizeListBefore = listTest.size() + 1;

        enrolment.add(sID, "2020C", cID);
        Assertions.assertEquals(sizeListBefore, listTest.size());
    }

    // @Test
    // void testUpdate() {

    //     System.out.println("Test Update");

    //     // Test Case: Update Successfully
    //     String sID = "S101312";
    //     String cID = "COSC3321";
    //     Assertions.assertEquals(true, enrolment.update(enrolment.isStudentValid(sID), enrolment.isCourseValid(cID), "2020C"));

    //     // Test Case: Update Unsuccessfully
    //     sID = "S1010312";
    //     cID = "COSC4030";
    //     Assertions.assertEquals(false, enrolment.update(enrolment.isStudentValid(sID), enrolment.isCourseValid(cID), "2020C"));

    // }

    // @Test
    // void delete() {
    // }

}