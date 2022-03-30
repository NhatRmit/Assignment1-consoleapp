import java.util.Scanner;

public class Menu {
    final String OPTIONONE = "1";
    final String OPTIONTWO = "2";
    final String OPTIONTHREE = "3";
    final String OPTIONFOUR = "4";
    final String OPTIONFIVE = "5";
    final String OPTIONSIX = "6";
    final String OPTIONSEVEN = "7";
    final String PRINTALLOPTION = "printAllOption";
    final String INVALIDDATA = "invalidData";
    final String MENUOPTION = "menuOption";

    Scanner scanner = new Scanner(System.in);
    EnrolmentManager enrolmentManager = new EnrolmentManager();

    public void printMenu(){
        String option, optionData;
        Student student;
        String semester;
        Course course;
        enrolmentManager.readData();
        do{
            enrolmentManager.printInfo(MENUOPTION);
            option = scanner.nextLine();
            switch (option) {
                case OPTIONONE:
                    student = enrolmentManager.getInputStudent();
                    semester = enrolmentManager.getInputSemester();
                    course = enrolmentManager.getInputCourse();
                    enrolmentManager.add(student, semester, course);
                    break;
                case OPTIONTWO:
                    student = enrolmentManager.getInputStudent();
                    semester = enrolmentManager.getInputSemester();
                    enrolmentManager.update(student, semester);
                    break;
                case OPTIONTHREE:
                    student = enrolmentManager.getInputStudent();
                    semester = enrolmentManager.getInputSemester();
                    
                    enrolmentManager.delete(student, semester);    
                    break;
                case OPTIONFOUR:
                    student = enrolmentManager.getInputStudent();
                    semester = enrolmentManager.getInputSemester();
                    course = enrolmentManager.getInputCourse();
                    enrolmentManager.getOne(student, course, semester);
                    break;
                case OPTIONFIVE:
                    enrolmentManager.getAll();
                    break;
                case OPTIONSIX:
                    enrolmentManager.printInfo(PRINTALLOPTION);
                    optionData = scanner.nextLine();
                    do{
                        switch (optionData) {
                            case OPTIONONE:
                                student = enrolmentManager.getInputStudent();
                                semester = enrolmentManager.getInputSemester();
                                enrolmentManager.printAllCouOfStudInSem(student, semester);
                                break;
                            case OPTIONTWO:
                                course = enrolmentManager.getInputCourse();
                                semester = enrolmentManager.getInputSemester();
                                enrolmentManager.printAllStudInCouInSem(course, semester);
                                break;
                            case OPTIONTHREE:
                                semester = enrolmentManager.getInputSemester();
                                enrolmentManager.printAllCouOfferedInSem(semester);
                                break;
                            default:
                                break;
                        }
                    } while(!option.equals(OPTIONONE) &&
                            !option.equals(OPTIONTWO) &&
                            !option.equals(OPTIONTHREE)
                    );
                    break;
                case OPTIONSEVEN:
                    System.exit(0);
                    break;
                default:
                    enrolmentManager.printInfo(INVALIDDATA);
                    break;
            }
        } while(!option.equals(OPTIONONE) &&
                !option.equals(OPTIONTWO) &&
                !option.equals(OPTIONTHREE) &&
                !option.equals(OPTIONFOUR) &&
                !option.equals(OPTIONFIVE) &&
                !option.equals(OPTIONSIX) &&
                !option.equals(OPTIONSEVEN)
        );
    }
}
