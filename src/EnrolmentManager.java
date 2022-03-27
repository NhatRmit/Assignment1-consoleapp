import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

class EnrolmentManager implements StudentEnrolmentManager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<StudentEnrolment> enrolmentList = new ArrayList<>();
    private StudentEnrolment studentEnrolment;

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<StudentEnrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public boolean isNotDuplicated(Object object) {
        if(object.getClass().equals(Student.class)){
            for(Student student : studentList) {
                if(student.getId().equals(((Student) object).getId())){
                    return false;
                }
            }
        } else {
            for(Course course : courseList) {
                if(course.getId().equals(((Course) object).getId())){
                    return false;
                } 
            }
        }
        return true;
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
                Student tempNewStudent = new Student(tempData[0], tempData[1], tempData[2]);
                Course tempNewCourse = new Course(tempData[3], tempData[4], Integer.parseInt(tempData[5]));
                StudentEnrolment tempStudentEnrolment = new StudentEnrolment(tempNewStudent, tempNewCourse, tempData[6]);
                if(isNotDuplicated(tempNewStudent)){
                    studentList.add(tempNewStudent);
                }
                if(isNotDuplicated(tempNewCourse)){
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

    public boolean isDigits(String str) {
        for(int i = 0; i < str.length();) {
            if (str.charAt(i) >= '0'
                && str.charAt(i) <= '9') {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean isStudentValid(String inputCheck){
        for(Student student : studentList) {
            if(student.getId().equalsIgnoreCase(inputCheck)){
                studentEnrolment.setStudent(student);
                return true;
            }
        }
        return false;
    }

    public boolean isCourseValid(String inputCheck){
        for(Course course : courseList){
            if(!(inputCheck.contains(" "))){
                if(course.getId().equalsIgnoreCase(inputCheck)){
                    studentEnrolment.setCourse(course);
                    return true;
                }
            } else {
                if(course.getName().equalsIgnoreCase(inputCheck)){
                    studentEnrolment.setCourse(course);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isNotValid(String inputCheck) {
        if(inputCheck.startsWith("S") && isDigits(inputCheck)) {
            isStudentValid(inputCheck);
        } else {
            isCourseValid(inputCheck);
        }   
        return false;
    }

    @Override
    public void add() {
        String studentID;
        String courseID;
        String semester;

        do{
            System.out.println("Enter studentID: ");
            studentID = scanner.nextLine();
            
        } while (isNotValid(studentID));

        do{
            System.out.println("Enter courseID: ");
            courseID = scanner.nextLine();
            
        } while (isNotValid(courseID));

        System.out.println("Enter semester");
        semester = scanner.nextLine();

        studentEnrolment.setSemester(semester);
        enrolmentList.add(studentEnrolment);


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