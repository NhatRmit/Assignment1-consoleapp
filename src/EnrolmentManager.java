import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

class EnrolmentManager implements StudentEnrolmentManager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<StudentEnrolment> enrolmentList = new ArrayList<>();
    private StudentEnrolment studentEnrolment = new StudentEnrolment();

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

    public boolean isDigit(String str) {
        for(int i = 1; i < str.length();) {
            if (str.charAt(i) >= '1'
                && str.charAt(i) <= '9') {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean isNotValid(String inputCheck) {
        if((inputCheck.startsWith("S") || inputCheck.startsWith("s")) && isDigit(inputCheck)) {
            for(Student student : studentList) {
                if((student.getId()).equalsIgnoreCase(inputCheck)){
                    studentEnrolment.setStudent(student);
                    return true;
                } else {
                    System.out.println("aaaa");
                }
            }
            return false;
        } else {
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
    }

    @Override
    public void add() {
        String studentID, courseID, semester;

        do{
            System.out.println("Enter studentID: ");
            studentID = scanner.nextLine();
        } while (!isNotValid(studentID));

        do{
            System.out.println("Enter courseID: ");
            courseID = scanner.nextLine();
        } while (!isNotValid(courseID));

        do {
            System.out.println("Enter semester");
            semester = scanner.nextLine();
            studentEnrolment.setSemester(semester);
        } while (false);

        enrolmentList.add(studentEnrolment);
    }

    @Override
    public void update() {
        //cu the 1 đứa trong 1 semester
    }

    @Override
    public void delete() {
        String studentID, courseID;
        ArrayList<StudentEnrolment> tempList = new ArrayList<>();

        for(StudentEnrolment se : enrolmentList){
            System.out.println(se);
        }

        do{
            System.out.println("Enter studentID: ");
            studentID = scanner.nextLine();
        } while (!isNotValid(studentID));

        for(StudentEnrolment se : enrolmentList){
            if(((se.getStudent()).getId()).equalsIgnoreCase(studentID)){
                tempList.add(se);
                System.out.println(se);
            }
        }

        do{
            System.out.println("Enter courseID: ");
            courseID = scanner.nextLine();
        } while (!isNotValid(courseID));

        for(StudentEnrolment se : tempList){
            if(((se.getCourse()).getId()).equalsIgnoreCase(courseID)){
                enrolmentList.remove(se);
            }
        }

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {
        // TODO Auto-generated method stub

    }

}