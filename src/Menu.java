import java.util.Scanner;

public class Menu {
    final String OPTIONONE = "1";
    final String OPTIONTWO = "2";
    final String OPTIONTHREE = "3";
    final String OPTIONFOUR = "4";
    Scanner scanner = new Scanner(System.in);
    EnrolmentManager enrolmentManager = new EnrolmentManager();



    public void printMenu(){
        String option;
        enrolmentManager.readData();
        do{
            System.out.println( "What do you want to do? (1/2/3/4)?");
            System.out.println( "1. Add\n" + 
                                "2. Update\n" +
                                "3. Delete\n" +
                                "4. Exit"
            );
            option = scanner.nextLine();
            switch (option) {
                case OPTIONONE:
                    enrolmentManager.getInputStudent();
                    enrolmentManager.getInputCourse();
                    enrolmentManager.getInputSemester();
                    enrolmentManager.add();
                    break;
                case OPTIONTWO:
                    enrolmentManager.update();
                    break;
                case OPTIONTHREE:
                    enrolmentManager.delete();
                    break;
                case OPTIONFOUR:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Your choice is not appropriate!");
                    break;
            }
        } while(!option.equals(OPTIONONE) &&
                !option.equals(OPTIONTWO) &&
                !option.equals(OPTIONTHREE) &&
                !option.equals(OPTIONFOUR)
        );
    }
}
