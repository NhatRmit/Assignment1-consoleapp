import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

class EnrolmentManager implements StudentEnrolmentManager {
    final String ADDORDELETE = "addOrDelete";
    final String PRINTALLOPTION = "printAllOption";
    final String ADDSUCCESSFULLY = "addSuccessfully";
    final String REMOVESUCCESSFULLY = "removeSuccessfully";
    final String FILETYPE = ".csv";
    final String DIRECTORY = "src/";
    final String ACTIONDELETE = "delete";
    final String ACTIONADD = "add";
    final String OPTIONONE = "1";
    final String OPTIONTWO = "2";
    final String OPTIONTHREE = "3";

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

    public Student verifyStudent(String inputCheck) {
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
            case PRINTALLOPTION:
                System.out.println("What do you want to get? (1 or 2)\n" +
                                    "1. Print all courses for 1 student in 1 semester\n" +
                                    "2. Print all students of 1 course in 1 semester\n" +
                                    "3. Prints all courses offered in 1 semester"
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

    public boolean isExistInEnrolmentList(Student student, String semester){
        for(StudentEnrolment se : enrolmentList){
            if((se.getSemester().equalsIgnoreCase(semester)) && 
                (se.getStudent().getId().equalsIgnoreCase(student.getId()))) {
                    printAllCouOfStudInSem(student, semester);
                    return true;
            }
        }
        return false;
    }

    public void addDetail(Student student, Course course, String semester){
        if(getOne(student, course, semester) == null){
            enrolmentList.add(new StudentEnrolment(student, course, semester));
            printInfo(ADDSUCCESSFULLY);
        } else {
            System.out.println("The course has already been enrolled");
        }
    }

    public void removeDetail(StudentEnrolment getOne) {
        if(getOne != null) {
            enrolmentList.remove(getOne);
            printInfo(REMOVESUCCESSFULLY);
        } else {
            System.out.println("There is any course to remove");
        }
    }

    @Override
    public void add() {
        addDetail(getInputStudent(), getInputCourse(), getInputSemester());
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
                        addDetail(student, course, semester);
                        break;
                    case OPTIONTWO:
                        System.out.println("Which course you want to delete?");
                        course = getInputCourse();
                        removeDetail(getOne(student, course, semester));
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
        removeDetail(getOne(getInputStudent(), getInputCourse(), getInputSemester()));
    }
    
    @Override
    public StudentEnrolment getOne(Student student, Course course, String semester) {
        for(StudentEnrolment se : enrolmentList){
            if(se.getStudent().getId().equalsIgnoreCase(student.getId()) &&
                (se.getCourse().getId().equalsIgnoreCase(course.getId())) &&
                (se.getSemester().equalsIgnoreCase(semester))) {
                    return se;
                }
        }
        return null;
    }

    @Override
    public void getAll() {
        for(StudentEnrolment se : enrolmentList) {
            System.out.println(se);
        }
    }

    public void printAll() {
        String option;
        do {
            printInfo(PRINTALLOPTION);
            option = scanner.nextLine();
            switch (option) {
                case OPTIONONE:
                    printAllCouOfStudInSem(getInputStudent(), getInputSemester());
                    break;
                case OPTIONTWO:
                    printAllStudInCouInSem(getInputCourse(), getInputSemester());
                    break;
                case OPTIONTHREE:
                    printAllCouOfferedInSem(getInputSemester());
                    break;
                default:
                    break;
            }
        } while (!option.equals(OPTIONONE) &&
                !option.equals(OPTIONTWO) &&
                !option.equals(OPTIONTHREE));
    }

    
    public void convertAllStudInCouInSemCSV(ArrayList<Student> studentPrint, Course course, String semester){
        FileWriter file = null;
        try {
            file = new FileWriter("src/allStudInCouInSem.csv");
            Iterator<Student> it = studentPrint.iterator();
            file.append("All of the student of " + 
            course.getName() + 
            " in semester " + 
            semester);
            file.append("\n");

            while(it.hasNext())
            {
                Student s = (Student)it.next();
                file.append(s.getId());
                file.append(",");
                file.append(s.getName());
                file.append(",");
                file.append(s.getBirthdate());
                file.append(",");
                file.append("\n");
            }
            
            file.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printAllStudInCouInSem(Course course, String semester){
        System.out.println("All of the student of " + 
                            course.getName() + 
                            " in semester " + 
                            semester);
        ArrayList<Student> tempPrint = new ArrayList<>();
        for(StudentEnrolment se : enrolmentList){
            if(se.getCourse().getId().equalsIgnoreCase(course.getId()) && 
                se.getSemester().equalsIgnoreCase(semester)){
                System.out.println(se.getStudent());
                tempPrint.add(se.getStudent());
            }
        }   
        convertAllStudInCouInSemCSV(tempPrint, course, semester); 
    }

    public void convertAllCouOfStudInSemCSV(ArrayList<Course> coursePrint, Student student, String semester){
        FileWriter file = null;
        try {
            file = new FileWriter("src/allCouOfStudInSem.csv");
            Iterator<Course> it = coursePrint.iterator();
            file.append("All of the course of " + 
            student.getName() + 
            " in semester " + 
            semester);
            file.append("\n");
            while(it.hasNext())
            {
                Course c = (Course)it.next();
                file.append(c.getId());
                file.append(",");
                file.append(c.getName());
                file.append(",");
                file.append(String.valueOf(c.getCredits()));
                file.append(",");
                file.append("\n");
            }
            
            file.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printAllCouOfStudInSem(Student student, String semester){
        System.out.println("All of the course of " + 
                            student.getName() + 
                            " in semester " + 
                            semester);
        ArrayList<Course> tempPrint = new ArrayList<>();
        for(StudentEnrolment se : enrolmentList){
            if(se.getStudent().getId().equalsIgnoreCase(student.getId()) && 
                se.getSemester().equalsIgnoreCase(semester)){
                System.out.println(se.getCourse());
                tempPrint.add(se.getCourse());
            }
        }
        convertAllCouOfStudInSemCSV(tempPrint, student, semester);
    }    

    public void convertAllCouOfferedInSemCSV(ArrayList<Course> course, String semester){
        FileWriter file = null;
        try {
            file = new FileWriter("src/allCouOfferedInSem.csv");
            Iterator<Course> it = course.iterator();
            file.append("All of the course offered in semester " + semester);
            file.append("\n");
            while(it.hasNext())
            {
                Course c = (Course)it.next();
                file.append(c.getId());
                file.append(",");
                file.append(c.getName());
                file.append(",");
                file.append(String.valueOf(c.getCredits()));
                file.append(",");
                file.append("\n");
            }
            
            file.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void printAllCouOfferedInSem(String semester){
        ArrayList<Course> tempList = new ArrayList<>();
        System.out.println("All of the course offered in semester " + semester);

        for(StudentEnrolment se : enrolmentList){
            if(se.getSemester().equalsIgnoreCase(semester)) {
                Course c = new Course(se.getCourse().getId(), se.getCourse().getName(), se.getCourse().getCredits());
                if(isCourseNotDuplicated(tempList, c)){
                    tempList.add(c);
                    System.out.println(c);
                }
            }
        } 

        convertAllCouOfferedInSemCSV(tempList, semester);
    }

    public boolean isCourseNotDuplicated(ArrayList<Course> tempList, Course c){
        for(Course course : tempList) {
            if(course.getId().equalsIgnoreCase(c.getId())){
                return false;
            }
        }
        return true;
    }
}