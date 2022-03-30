import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

class EnrolmentManager implements StudentEnrolmentManager {
    final String MENUOPTION = "menuOption";
    final String ADDORDELETE = "addOrDelete";
    final String PRINTALLOPTION = "printAllOption";
    final String ADDSUCCESSFULLY = "addSuccessfully";
    final String REMOVESUCCESSFULLY = "removeSuccessfully";
    final String INVALIDDATA = "invalidData";
    final String FILETYPE = ".csv";
    final String DIRECTORY = "src/";
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

    public void printInfo(String option){
        switch (option) {
            case MENUOPTION:
                System.out.println( "What do you want to do? (1/2/3/4)?\n" +
                            "1. Add\n" + 
                            "2. Update\n" +
                            "3. Delete\n" +
                            "4. Get One\n" +
                            "5. Get All\n" +
                            "6. Print Info\n" +
                            "7. Exit"
                );
                break;
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
            case INVALIDDATA:
                System.out.println("Invalid Data");
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

    public Student getInputStudent(){
        String studentID;
        Student student = null;
        do{
            System.out.println("Enter studentID: ");
            studentID = scanner.nextLine();
            for(Student s : studentList){
                if(s.getId().equalsIgnoreCase(studentID)){
                    return student = s;
                }
            }
        } while (student == null);
        return null;
    }

    public Course getInputCourse(){
        String courseID;
        Course course = null;
        do{
            System.out.println("Enter courseID: ");
            courseID = scanner.nextLine();
            for(Course c : courseList){
                if(c.getId().equalsIgnoreCase(courseID)){
                    return course = c;
                }
            }
        } while (course == null);
        return null;
    }

    public String getInputSemester(){
        String semester;
        do {
            System.out.println("Enter semester");
            semester = scanner.nextLine();
        } while (semester == null);
        return semester;
    }

    @Override
    public void add(Student student, String semester) {
        Course course = getInputCourse();
        if(getOne(student, course, semester) == null){
            enrolmentList.add(new StudentEnrolment(student, course, semester));
            printInfo(ADDSUCCESSFULLY);
        } else {
            System.out.println("Already been enrolled");
        }
    }

    @Override
    public void update(Student student, String semester) {
        String option;

        if(isExistInEnrolmentList(student, semester)){
            do {
                printInfo(ADDORDELETE);
                option = scanner.nextLine();
                switch (option) {
                    case OPTIONONE:
                        System.out.println("Which course you want to add?");
                        add(student, semester);
                        break;
                    case OPTIONTWO:
                        System.out.println("Which course you want to delete?");
                        delete(student, semester);
                        break;
                    default:
                        break;
                }
            } while (!option.equals(OPTIONONE) &&
                     !option.equals(OPTIONTWO));
        } else {
            printInfo(INVALIDDATA);
            return;
        }
    }

    @Override
    public void delete(Student student, String semester) {
        Course course = getInputCourse();
        StudentEnrolment getOne = getOne(student, course, semester);
        if(getOne != null) {
            enrolmentList.remove(getOne);
            printInfo(REMOVESUCCESSFULLY);
        } else {
            System.out.println("There is not any course to remove");
        }
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

    public void printAllStudInCouInSem(Course course, String semester){
        System.out.println("All of the student of " + course.getName() + " in semester " + semester);
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

    public void printAllCouOfStudInSem(Student student, String semester){
        System.out.println("All of the course of " + student.getName() + " in semester " + semester);
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
    
    public void printAllCouOfferedInSem(String semester){
        Set<Course> tempList = new HashSet<>();
        System.out.println("All of the course offered in semester " + semester);
        for(StudentEnrolment se : enrolmentList){
            if(se.getSemester().equalsIgnoreCase(semester)){
                tempList.add(se.getCourse());
            }
        } 
        for(Course c : tempList) {
            System.out.println(c);
        }
        convertAllCouOfferedInSemCSV(tempList, semester);
    }

    public void convertAllStudInCouInSemCSV(ArrayList<Student> studentPrint, Course course, String semester){
        FileWriter file = null;
        try {
            file = new FileWriter("src/allStudInCouInSem.csv");
            file.append("\n");
            Iterator<Student> it = studentPrint.iterator();
            file.append("All of the student of " + course.getName() + " in semester " + semester);
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

    public void convertAllCouOfStudInSemCSV(ArrayList<Course> coursePrint, Student student, String semester){
        FileWriter file = null;
        try {
            file = new FileWriter("src/allCouOfStudInSem.csv");
            file.append("\n");
            Iterator<Course> it = coursePrint.iterator();
            file.append("All of the course of " + 
            student.getName() + 
            " in semester " + 
            semester);
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

    public void convertAllCouOfferedInSemCSV(Set<Course> course, String semester){
        FileWriter file = null;
        try {
            file = new FileWriter("src/allCouOfferedInSem.csv");
            file.append("\n");
            Iterator<Course> it = course.iterator();
            file.append("All of the course offered in semester " + semester);
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
}