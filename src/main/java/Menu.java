import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Show console menu to user. There are five menu items: four for sorting and one for the exit.
 */

class Menu {

    /**
     * Scanner for adoption of the values in the console
     */
    private Scanner select = new Scanner(System.in);

    /**
     * Class object RecordTemplate
     */
    private RecordTemplate recordCriteria = new RecordTemplate();

    /*
     * Class object Logs to call method findAndWriteResults
     */
    private Logs logs = new Logs();
    /*
     * A special date format ("dd-MM-yyyy") for recording and reading
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Method which show Menu to User.
     * User must select one of the items and follow the instructions
     * @throws InputMismatchException print message
     */
    Menu(){
        System.out.println("1. Find the record by the name of the user.\n" +
                            "2. Find the records by the period of time.\n" +
                            "3. Find records by the message template.\n" +
                "4. Search of the records by several criteria.\n" +
                "5. Exit\n");
        String selection = select.nextLine();
        try {
            switch (selection) {
                case "1":
                    findRecordsFromName();
                    break;
                case "2":
                    findRecordsFromTime();
                    break;
                case "3":
                    findRecordFromTemplate();
                    break;
                case "4":
                    findRecordsFromSomeCriterias();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("You made the wrong choice! Try again!");
            }
        } catch (InputMismatchException e){
            System.out.println("You need to write numbers!");
        }
    }

    /**
     * A method that requires a username and seeks records on this parameter
     */
    private void findRecordsFromName(){
        //Entry user name
        System.out.println("Enter the user Name\n");
        recordCriteria.setUserName(select.nextLine());

        //start method for adding path and otput file name
        addPathsAndFindLogs();
    }

    /**
     * method that requests the initial date and end date and seeks records in this parameter
     * @throws DateTimeParseException print message
     */
    private void findRecordsFromTime(){
        try {
            //Entry initial date in format d-MM-yyyy
            System.out.println("Enter the initial date in the format of range d-MM-yyyy\n");
            recordCriteria.setDate(LocalDate.parse(select.nextLine(), formatter));
            //Entry initial date in format d-MM-yyyy
            System.out.println("Enter the final date in the format of range d-MM-yyyy");

            recordCriteria.setSecondDate(LocalDate.parse(select.nextLine(), formatter));
            addPathsAndFindLogs();
        } catch (DateTimeParseException e){
            System.out.println("You've written the incorrect date!");
        }
    }

    /**
     * A method that requires the template message and seeks records on this parameter
     */
    private void findRecordFromTemplate(){

        //Entry template message to sort

        System.out.println("Enter template message\n");
        recordCriteria.setRecordBody(select.nextLine());
        addPathsAndFindLogs();
    }

    /**
     *  This is default method that requires the path for input and output files and seeks records on this parameter
     */
    private void addPathsAndFindLogs(){

        String pathInputFiles;
        String pathOutputFiles;
        String fileName;

        //Entry path to input and output files.

        System.out.println("Enter path to input files");
        pathInputFiles = select.nextLine();
        System.out.println("Enter path to output files");
        pathOutputFiles = select.nextLine();

        // Entry file name
        System.out.println("Enter file name for output files");
        fileName = select.nextLine();

        //start method findAndWriteResults
        logs.findAndWriteResults(pathInputFiles, pathOutputFiles, fileName, recordCriteria);
    }

    /**
     * A method that requires a username and invoke method findRecordsFromTime
     */
    private void findRecordsFromSomeCriterias(){
        System.out.println("Enter the user name");
        recordCriteria.setUserName(select.nextLine());
        findRecordsFromTime();
    }
}
