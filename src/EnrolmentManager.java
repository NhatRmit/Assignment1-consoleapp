import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

class EnrolmentManager implements StudentEnrolmentManager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<StudentEnrolment> enrolmentList = new ArrayList<>();

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<StudentEnrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public boolean isDuplicated(Object object) {
        if(object.getClass().equals(Student.class)){
            for(Student student : studentList) {
                if(student.getId().equals(((Student) object).getId())){
                    return true;
                }
            }
        } else {
            for(Course course : courseList) {
                if(course.getId().equals(((Course) object).getId())){
                    return true;
                } 
            }
        }
        return false;
    }

    public void readData() {
        try {
            final String fileType = ".csv";
            final String direct = "src/";
            System.out.println("Enter your csv file: ");
            String filecsv = scanner.nextLine();
            String line = "";
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader(direct + filecsv + fileType));
            while ((line = br.readLine()) != null) {
                String[] tempData = line.split(splitBy);
                // addStudent(new Student(tempStudent[0], tempStudent[1], tempStudent[2]));
                Student tempNewStudent = new Student(tempData[0], tempData[1], tempData[2]);
                Course tempNewCourse = new Course(tempData[3], tempData[4], Integer.parseInt(tempData[5]));
                StudentEnrolment tempStudentEnrolment = new StudentEnrolment(tempNewStudent, tempNewCourse, tempData[6]);
                if(!isDuplicated(tempNewStudent)){
                    studentList.add(tempNewStudent);
                }
                if(!isDuplicated(tempNewCourse)){
                    courseList.add(tempNewCourse);
                }
                enrolmentList.add(tempStudentEnrolment);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error Function");
            e.printStackTrace();
        }
    }

    public boolean isStudentIdValid(Student stud){
        for(Student student : studentList){
            if(student.getId().equals(stud.getId())){
                return true;
            }
        }
        return true;
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