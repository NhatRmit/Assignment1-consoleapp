import java.util.Scanner;

public class Menu {
    final String OPTIONONE = "1";
    final String OPTIONTWO = "2";
    final String OPTIONTHREE = "3";
    final String OPTIONFOUR = "4";
    final String OPTIONFIVE = "5";
    final String OPTIONSIX = "6";
    final String OPTIONSEVEN = "7";

    Scanner scanner = new Scanner(System.in);
    EnrollmentManager enrolmentManager = new EnrollmentManager();
    String option, optionData, semester;
    Student student;
    Course course;
    
    /**
     * @desc a menu when starts the program (a menu has 7 options)
     * @param none
     * @return none
     * @author Nguyen Hoang Minh Nhat - s3765963
     */
    public void printMenu(){
        System.out.println("Enter file csv");
        enrolmentManager.readData(scanner.nextLine());
        
        do{
            enrolmentManager.printInfo(enrolmentManager.MENUOPTION);
            option = scanner.nextLine();
            switch (option) {
                case OPTIONONE:
                    enrolmentManager
                        .add(
                            enrolmentManager.getInputStudent(), 
                            enrolmentManager.getInputCourse(), 
                            enrolmentManager.getInputSemester()
                        );
                    break;
                case OPTIONTWO:
                    student = enrolmentManager.getInputStudent();
                    semester = enrolmentManager.getInputSemester();
                    if(enrolmentManager.isExistInEnrollmentList(student, semester)){
                        enrolmentManager.printInfo(enrolmentManager.ADDORDELETE);
                        String opt;
                        do{
                            System.out.println("Enter option");
                            opt = scanner.nextLine();
                            switch(opt){
                                case OPTIONONE:
                                    System.out.println("Which course you want to add?");
                                    course = enrolmentManager.getInputCourse();
                                    enrolmentManager.update(student, course, semester, opt);
                                    break;
                                case OPTIONTWO:
                                    System.out.println("Which course you want to delete?");
                                    course = enrolmentManager.getInputCourse();
                                    enrolmentManager.update(student, course, semester, opt);
                                    break;
                                default:
                                    break;
                            }
                        } while (!opt.equals(OPTIONONE) &&
                                !opt.equals(OPTIONTWO));
                    } else {
                        enrolmentManager.printInfo(enrolmentManager.INVALIDDATA);
                    }
                    break;
                case OPTIONTHREE:
                    enrolmentManager
                        .delete(
                            enrolmentManager.getInputStudent(), 
                            enrolmentManager.getInputCourse(), 
                            enrolmentManager.getInputSemester()
                        );
                    break;
                case OPTIONFOUR:
                    enrolmentManager
                        .getOne(
                            enrolmentManager.getInputStudent(), 
                            enrolmentManager.getInputCourse(),
                            enrolmentManager.getInputSemester()
                        );
                    break;
                case OPTIONFIVE:
                    enrolmentManager.getAll();
                    break;
                case OPTIONSIX:
                    enrolmentManager.printInfo(enrolmentManager.PRINTALLOPTION);
                    optionData = scanner.nextLine();
                    do{
                        switch (optionData) {
                            case OPTIONONE:
                                enrolmentManager
                                    .printAllCouOfStudInSem(
                                        enrolmentManager.getInputStudent(),
                                        enrolmentManager.getInputSemester()
                                    );
                                break;
                            case OPTIONTWO:
                                enrolmentManager
                                    .printAllStudInCouInSem(
                                        enrolmentManager.getInputCourse(), 
                                        enrolmentManager.getInputSemester()
                                    );
                                break;
                            case OPTIONTHREE:
                                enrolmentManager
                                    .printAllCouOfferedInSem(
                                        enrolmentManager.getInputSemester()
                                    );
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
                    enrolmentManager.printInfo(enrolmentManager.INVALIDDATA);
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
