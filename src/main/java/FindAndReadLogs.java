import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindAndReadLogs {

    private void findNeededRecord(String fileName){
        File file = new File(fileName);
        try {
            StringBuilder sb = new StringBuilder();
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private String read(List<String> filesNames, Record templateRecord) throws FileNotFoundException {
//        List<Record> foundRecords = new ArrayList<>();
//        filesNames.forEach(fileName -> {
//
//        });
//    }

    private String readFiles(String fileName) throws FileNotFoundException {




        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();


        File file = new File(fileName);
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {

                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    private List<String> findTxtFilesNames(List<String> filesNames){
        List<String> txtFilesNames = new ArrayList<>();
        filesNames.forEach(nameOfTxtFile -> {
            if (nameOfTxtFile.contains(".txt")){
                txtFilesNames.add(nameOfTxtFile);
            }
        });
        return txtFilesNames;
    }

    void findFilesInFolder() throws FileNotFoundException{
        File listOfFiles = new File("/Users/artem/IdeaProjects/logs-analysis");
        String[] exportFiles = listOfFiles.list();

        List<String> txtFilesInFolder = findTxtFilesNames(Arrays.asList(exportFiles));
        readFiles(txtFilesInFolder.get(0));
    }

    public void addCriteria(String... criteria){

    }

    private Record addRecord(String recordInLog){


        return null;
    }

}
