import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class works with logs. It can find, read and write logs by the parameters
 */

class Logs {

    /**
     * USER_NAME_IN_LINE - user name in the file line
     */
    private static final Integer USER_NAME_IN_LINE = 0;
    /**
     * DATE_IN_LINE - date in the file line
     */
    private static final Integer DATE_IN_LINE = 1;
    /**
     * RECORD_BODY_IN_LINE - record body in the file line
     */
    private static final Integer RECORD_BODY_IN_LINE = 2;


    /**
     * This method checks whether the date is between two other dates.
     * For comparison used method compareTo, it return number, which showed different between dateForCheck and other dates.
     *
     * @param dateForCheck  The date which compares with other dates.
     * @param firstDate  Initial date for comparison.
     * @param secondDate  The latest date for comparison.
     * @return true if dateForCheck is between firstDate and secondDate, else false;
     */
    private boolean dateBetweenDates(LocalDate dateForCheck, LocalDate firstDate, LocalDate secondDate){
        if (dateForCheck.compareTo(firstDate) >= 0 && dateForCheck.compareTo(secondDate)<=0){
            return true;
        } else return false;
    }

    /**
     * This method checks each field in recordTemplate on null
     * and if field not null, method compare field with the field of recordOfCheck and return false if
     * fields does not similar
     * @param recordForCheck Record that compare with recordForCheck and find similar fields
     * @param recordTemplate Template record that created user to find records
     * @return true, if the record fields coincide with the completed template record fields
     */

    private boolean isNeededRecord(Record recordForCheck, RecordTemplate recordTemplate){
        //Check name on null
        if (recordTemplate.getUserName() != null){
            if (!recordForCheck.getUserName().equals(recordTemplate.getUserName())){
                return false;
            }
        }
        //Check date on null
        if (recordTemplate.getDate() != null && recordTemplate.getSecondDate() != null){
            if (!dateBetweenDates(recordForCheck.getDate(), recordTemplate.getDate(), recordTemplate.getSecondDate())){
                return false;
            }
        }
        //Check message on null
        if (recordTemplate.getRecordBody() != null){
            if (!recordForCheck.getRecordBody().equals(recordTemplate.getRecordBody())){
                return false;
            }
        }
        // If all the fields of the two records are similar then return true
        return true;
    }

    /** This method creates a record of the line which places in the file
     * @param line which is used to create a record
     * @throws ArrayIndexOutOfBoundsException print message
     * @return record that created from line
     */

    private Record createRecordFromLine(String line){
        Record record = new Record();
        //  Format date dd-MM-yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> components = Arrays.asList(line.split("\\|"));
        try {
            LocalDate localDate = LocalDate.parse(components.get(DATE_IN_LINE), formatter);
            record.setDate(localDate);
            record.setUserName(components.get(USER_NAME_IN_LINE).replace(" ", ""));
            record.setRecordBody(components.get(RECORD_BODY_IN_LINE));
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("txt file is wrong!\n");
        }
        return record;
    }

    /** This method finds the records in the parameters
     *
     * @param fileName Search for the file in the folder
     * @param recordTemplate Template record that created user to find records
     * @param pathToFolder add to file and creates path for file
     * @throws RuntimeException print message
     * @return list with found records.
     */
    private List<Record> takeRecordsFromFile(String fileName, RecordTemplate recordTemplate, String pathToFolder){
        //Create new file (address = pathToFolder + fileName
        File file = new File(pathToFolder+fileName);
        List<Record> records = new ArrayList<>();
        try {
            BufferedReader linesInTxtFile = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String lineIfTxtFile;
                while ((lineIfTxtFile = linesInTxtFile.readLine()) != null) {
                    Record record = createRecordFromLine(lineIfTxtFile);
                    // Check record, if it not null and it approach in the parameters
                    if (record != null && isNeededRecord(record, recordTemplate)) {
                        //add record to List
                            records.add(record);
                    }
                }
            } finally {
                //necessarily close file
                linesInTxtFile.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    /** This method finds the .txt files among other files
     *
     * @param filesNames - its List with fileNames
     * @return file names in txt format
     */
    private List<String> findTxtFilesNames(List<String> filesNames){
        // Create new List with txt file names
        List<String> txtFilesNames = new ArrayList<>();
        // ForEach to search txt files among the others
        filesNames.forEach(nameOfTxtFile -> {

            //method "contains" defines files in txt format
            if (nameOfTxtFile.contains(".txt")){
                txtFilesNames.add(nameOfTxtFile);
            }
        });
        return txtFilesNames;
    }

    /** This method looks for matching records for the user.
     *
     * @param pathToFolder which is entered by the user to search for files
     * @param recordTemplate which is entered by the user to search for files
     * @return List with found records
     */
    private List<Record> findNeededRecords(String pathToFolder, RecordTemplate recordTemplate) {
        //Create List files with different files
        File listOfFiles = new File(pathToFolder);
        //Create list with records
        List<Record> records = new ArrayList<>();
        //Get massive of files
        String[] exportFiles = listOfFiles.list();
        //Check if files not null
        if (exportFiles != null) {
            //Convert massive in List and find txt files among others
            List<String> txtFilesInFolder = findTxtFilesNames(Arrays.asList(exportFiles));
            // ForEach to search for relevant records in txt files
            txtFilesInFolder.forEach(nameOfFile -> {
                records.addAll(takeRecordsFromFile(nameOfFile, recordTemplate, pathToFolder));
            });
        }
        return records;
    }

    /** This method records the results by the found criteria of recording into file.
     * @param path to folder where the records recording
     * @param records which are to be written
     * @param filename for file
     */
    private void write(String path, List<Record> records, String filename) {
        //Check empty file or not
        if (!records.isEmpty()) {
            File file = new File(path + filename);
            try {
                //If the file does not exist then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                try {
                    //Writing to a file using the ForEach
                    records.forEach(record -> {
                        // first - User Name + |
                        // next - date + |
                        // and last - message + \n (new line)
                        out.print(record.getUserName() + "|" + record.getDate() + "|" + record.getRecordBody()+"\n");
                    });

                } finally {
                    //Close the file
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // print message if records exist
            System.out.println("File successfully written!\n Number of records: " + records.size() + "\n");
        } else {
            //print message if records doesn't exist
            System.out.println("Records to these parameters is not found\n");
        }
    }

    /** Public method, which is called from Menu.
     * There are two parts:
     * first part - method that finds records by Template Record
     * second part - write result records in file
     *
     * @param pathToInputFiles for search input files
     * @param pathToOutputFiles for write output files
     * @param fileName for output file
     * @param recordTemplate to compare with records
     */
    void findAndWriteResults(String pathToInputFiles, String pathToOutputFiles, String fileName,
                             RecordTemplate recordTemplate){
        //Create list with the found records of the entered parameters
        List<Record> records = findNeededRecords(pathToInputFiles, recordTemplate);
        //invoke method write
        write(pathToOutputFiles, records, fileName);
    }
}
