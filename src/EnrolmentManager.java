import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class EnrolmentManager implements StudentEnrolmentManager {
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<StudentEnrolment> enrolmentList = new ArrayList<>();

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<StudentEnrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public void addStudent(Student s){
        studentList.add(s);
    }

    public void addCourse(Course c) {
        courseList.add(c);
    }

    public void addEnrolmentList(StudentEnrolment se){
        enrolmentList.add(se);
    }

    public void readData() {
        try {
            String line = "";
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader("src/default.csv"));
            while ((line = br.readLine()) != null) {
                String[] tempStudent = line.split(splitBy);
                addStudent(new Student(tempStudent[0], tempStudent[1], tempStudent[2]));
                addCourse(new Course(tempStudent[3], tempStudent[4], tempStudent[5]));
                addEnrolmentList(new StudentEnrolment(new Student(tempStudent[0], tempStudent[1], tempStudent[2]), new Course(tempStudent[3], tempStudent[4], tempStudent[5]), new String(tempStudent[6])));
            }
        } catch (Exception e) {
            System.out.println("Error Function");
            e.printStackTrace();
        }
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

    public Object[] getCourseList() {
        return null;
    }

}