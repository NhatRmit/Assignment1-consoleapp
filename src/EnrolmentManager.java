import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class EnrolmentManager implements StudentEnrolmentManager {
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<StudentEnrolment> enrolmentList;

    public ArrayList<Student> readStudentData() {
        ArrayList<Student> studentData = new ArrayList<Student>();
        try {
            String line = "";
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader("src/studentData.csv"));
            while ((line = br.readLine()) != null) {
                String[] tempStudent = line.split(splitBy);
                studentData.add(new Student(tempStudent[0], tempStudent[1], tempStudent[2]));
            }
        } catch (Exception e) {
            System.out.println("Error Function");
            e.printStackTrace();
        }

        return studentData;
    }

    public ArrayList<Course> readCourseData() {
        ArrayList<Course> courseData = new ArrayList<Course>();
        try {
            String line = "";
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader("src/studentData.csv"));
            while ((line = br.readLine()) != null) {
                String[] tempCourse = line.split(splitBy);
                courseData.add(new Course(tempCourse[0], tempCourse[1], tempCourse[2]));
            }
        } catch (Exception e) {
            System.out.println("Error Function");
            e.printStackTrace();
        }

        return courseData;
    }

    public void printTest() {
        for (Student student : readStudentData()) {
            System.out.println(student.toString());
        }
    }

    @Override
    public void add() {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public void getOne() {
        // TODO Auto-generated method stub

    }

    @Override
    public void getAll() {
        // TODO Auto-generated method stub

    }

}