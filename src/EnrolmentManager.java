import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;



class EnrolmentManager implements StudentEnrolmentManager {
    final String ADDORDELETE = "addOrDelete";
    final String GETALLSTUDORCOUINSEM = "getAllStudOrCouInSem";
    final String ADDSUCCESSFULLY = "addSuccessfully";
    final String REMOVESUCCESSFULLY = "removeSuccessfully";
    final String FILETYPE = ".csv";
    final String DIRECTORY = "src/";
    final String ACTIONDELETE = "delete";
    final String ACTIONADD = "add";
    final String OPTIONONE = "1";
    final String OPTIONTWO = "2";

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

            System.out.println("Enter your csv file: ");
            String filecsv = scanner.nextLine();
            String line = "";
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader(DIRECTORY + filecsv + FILETYPE));
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

    public Student verifyStudent(String inputCheck){
        for(Student student : studentList) {
            if((student.getId()).equalsIgnoreCase(inputCheck)){
                return student;
            }
        }
        return null;
    }

    public Course verifyCourse(String inputCheck) {
        for(Course course : courseList){
            if(!(inputCheck.contains(" "))){
                if(course.getId().equalsIgnoreCase(inputCheck)){
                    return course;
                }
            } else {
                if(course.getName().equalsIgnoreCase(inputCheck)){
                    return course;
                }
            }
        }
        return null;
    }  

    public Student getInputStudent(){
        String studentID;
        Student student;
        do{
            System.out.println("Enter studentID: ");
            studentID = scanner.nextLine();
            student = verifyStudent(studentID);
        } while (student == null);
        return student;
    }

    public Course getInputCourse(){
        String courseID;
        Course course;
        do{
            System.out.println("Enter courseID: ");
            courseID = scanner.nextLine();
            course = verifyCourse(courseID);
        } while (course == null);
        return course;
    }

    public String getInputSemester(){
        String semester;
        do {
            System.out.println("Enter semester");
            semester = scanner.nextLine();
        } while (semester == null);
        return semester;
    }

    public void printInfo(String option){
        switch (option) {
            case ADDORDELETE:
                System.out.println("What do you want to do? (1 or 2)\n" +
                                    "1. Add new course \n" +
                                    "2. Delete course"
                );
                break;
            case GETALLSTUDORCOUINSEM:
                System.out.println("What do you want to get? (1 or 2)\n" +
                                    "1. Get 1 student with his/her course list\n" +
                                    "2. Get 1 course with enrolled student list"
                );
                break;
            case ADDSUCCESSFULLY:
                System.out.println("Add successfully");
                break;
            case REMOVESUCCESSFULLY:
                System.out.println("Remove successfully");
                break;
            default:
                break;
        }
    }

    public boolean hasCourse(Student student, Course course, String semester){
        for(StudentEnrolment se : enrolmentList){
            if(((se.getCourse()).getId()).equalsIgnoreCase(course.getId()) && 
                (se.getStudent().getId().equalsIgnoreCase(student.getId())) &&
                (se.getSemester().equalsIgnoreCase(semester))){
                    return true;
            }
        }
        return false;
    }

    public boolean isExistInEnrolmentList(Student student, String semester){
        for(StudentEnrolment se : enrolmentList){
            if((se.getSemester().equalsIgnoreCase(semester)) && 
                (se.getStudent().getId().equalsIgnoreCase(student.getId()))) {
                    printAllCouOfStudOfSem(student, semester);
                    return true;
            }
        }
        return false;
    }

    public void addNewCourse(Student student, Course course, String semester){
        if(!hasCourse(student, course, semester)){
            enrolmentList.add(new StudentEnrolment(student, course, semester));
            printInfo(ADDSUCCESSFULLY);
        } else {
            System.out.println("The course has already been enrolled");
        }
    }

    public void deleteCourse(Student student, Course course, String semester){
        if(hasCourse(student, course, semester)) {
            for(StudentEnrolment se : enrolmentList){
                if(((se.getCourse()).getId()).equalsIgnoreCase(course.getId()) && 
                    (se.getStudent().getId().equalsIgnoreCase(student.getId())) &&
                    (se.getSemester().equalsIgnoreCase(semester))){
                        enrolmentList.remove(se);
                        printInfo(REMOVESUCCESSFULLY);
                }
            }
        } else {
            System.out.println("There is any course to remove");
        }
    }

    @Override
    public void add() {
        addNewCourse(getInputStudent(), getInputCourse(), getInputSemester());
    }

    @Override
    public void update() {
        String option;
        Course course;
        Student student = getInputStudent();
        String semester = getInputSemester();
        if(isExistInEnrolmentList(student, semester)){
            do {
                printInfo(ADDORDELETE);

                option = scanner.nextLine();
                switch (option) {
                    case OPTIONONE:
                        System.out.println("Which course you want to add?");
                        course = getInputCourse();
                        addNewCourse(student, course, semester);
                        break;
                    case OPTIONTWO:
                        System.out.println("Which course you want to delete?");
                        course = getInputCourse();
                        deleteCourse(student, course, semester);
                        break;
                    default:
                        break;
                }
                
            } while (!option.equals(OPTIONONE) &&
                     !option.equals(OPTIONTWO));
        } else {
            System.out.println("Student " + 
                                student.getId() + 
                                " has not enrolled anything. Cannot update!");
            return;
        } 
    }

    @Override
    public void delete() {
        Student student = getInputStudent();
        String semester = getInputSemester();
        printAllCouOfStudOfSem(student, semester);

        System.out.println("Which course you want to delete?");
        Course course = getInputCourse();
        deleteCourse(student, course, semester);
    }
    
    public void printAllStudInCouOfSem(Course course, String semester){
        System.out.println("All of the student of " + 
                            course.getName() + 
                            " in semester " + 
                            semester);

        for(StudentEnrolment se : enrolmentList){
            if(se.getCourse().getId().equalsIgnoreCase(course.getId()) && 
                se.getSemester().equalsIgnoreCase(semester)){
                System.out.println(se.getStudent());
            }
        }    
    }

    public void printAllCouOfStudOfSem(Student student, String semester){
        System.out.println("All of the course of " + 
                            student.getName() + 
                            " in semester " + 
                            semester);

        for(StudentEnrolment se : enrolmentList){
            if(se.getStudent().getId().equalsIgnoreCase(student.getId()) && 
                se.getSemester().equalsIgnoreCase(semester)){
                System.out.println(se.getCourse());
            }
        }
    }


    @Override
    public void getOne() {
        String option;
        do {
            printInfo(GETALLSTUDORCOUINSEM);
            option = scanner.nextLine();
            switch (option) {
                case OPTIONONE:
                    printAllCouOfStudOfSem(getInputStudent(), getInputSemester());
                    break;
                case OPTIONTWO:
                    printAllStudInCouOfSem(getInputCourse(), getInputSemester());
                    break;
                default:
                    break;
            }

        } while (!option.equals(OPTIONONE) &&
                !option.equals(OPTIONTWO));
    }

    public void printOfferedCouOfSem(String semester){
        
    }

    @Override
    public void getAll() {
        System.out.println("Which semester you want to get all offered courses?");
        getInputSemester();

    }

}