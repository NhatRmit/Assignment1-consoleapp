import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class EnrollmentManagerTest {
    private static EnrollmentManager enrollment = new EnrollmentManager();
    
    /**
     * @desc 
     * @param 
     * @param 
     * @return 
     * @author Nguyen Hoang Minh Nhat - s3765963
     */
    @BeforeAll
    static void beforeAll() throws FileNotFoundException {
        enrollment.readData("default");
    }
    
    /**
     * @desc 
     * @param 
     * @param 
     * @return 
     * @author Nguyen Hoang Minh Nhat - s3765963
     */
    @Test
    void testAdd() {
        System.out.println("Test Add");
        String studentID, courseID, semester;
        StudentEnrollment se;

        // Test Case: Enrolled Successfully
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";

        enrollment.add(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, enrollment.getEnrollmentList().contains(se));

        // Test Case: Enrolled Already
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";

        enrollment.add(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, !enrollment.getEnrollmentList().contains(se));
    }
    
    /**
     * @desc 
     * @param 
     * @param 
     * @return 
     * @author Nguyen Hoang Minh Nhat - s3765963
     */
    @Test
    void testUpdate() {
        System.out.println("Test Update");
        String studentID, courseID, semester, option;
        StudentEnrollment se;

        // Test Case: Updated Successfully
        // (Add New)
        option = "1";
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";
        enrollment.update(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester, option);
        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, !enrollment.getEnrollmentList().contains(se));

        // (Delete)
        option = "2";
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";
        enrollment.update(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester, option);
        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, !enrollment.getEnrollmentList().contains(se));

        // Test Case: Updated Unsuccessfully
        // (Add New)
        option = "1";
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";
        enrollment.update(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester, option);
        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, enrollment.getEnrollmentList().contains(se));

        // (Delete)
        option = "2";
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";
        enrollment.update(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester, option);
        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, enrollment.getEnrollmentList().contains(se));

    }
    
    /**
     * @desc 
     * @param 
     * @param 
     * @return 
     * @author Nguyen Hoang Minh Nhat - s3765963
     */
    @Test
    void testDelete() {
        System.out.println("Test Delete");
        String studentID, courseID, semester;
        StudentEnrollment se;

        // Test Case: Removed Successfully
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";

        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        enrollment.delete(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, !enrollment.getEnrollmentList().contains(se));

        // Test Case: Removed Unsuccessfully
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";

        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        enrollment.delete(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, enrollment.getEnrollmentList().contains(se));
    }
    
    /**
     * @desc 
     * @param 
     * @param 
     * @return 
     * @author Nguyen Hoang Minh Nhat - s3765963
     */
    @Test
    void testGetOne() {
        System.out.println("Test Get One");
        String studentID, courseID, semester;
        StudentEnrollment se;

        // Test Case: Get One Successfully
        studentID = "s101312";
        courseID = "bus2232";
        semester = "2020C";

        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(true, !enrollment.getEnrollmentList().contains(se));

        // Test Case: Get One Unsuccessfully
        studentID = "s101312";
        courseID = "cosc3321";
        semester = "2020C";

        se = enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        enrollment.getOne(enrollment.verifyStudent(studentID), enrollment.verifyCourse(courseID), semester);
        Assertions.assertEquals(false, enrollment.getEnrollmentList().contains(se));
    }
    
    /**
     * @desc 
     * @param 
     * @param 
     * @return 
     * @author Nguyen Hoang Minh Nhat - s3765963
     */
    @Test
    void testGetAll() {
    }
}