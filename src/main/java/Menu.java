import java.util.Scanner;

/**
 * Created by artem on 14.11.16.
 */
public class Menu {

    private Scanner select = new Scanner(System.in);
    private Record recordCriteria = new Record();

    void menu(){
        System.out.println("1. Найти записи по имени пользователя\n" +
                            "2. Найти записи по временному периоду\n" +
                            "3. Найти записи по шаблону сообщения\n" +
                "4. Поиск записей по нескольким критериям");
        Integer selection = select.nextInt();
        do {
            switch (selection) {
                case 1:
                    findRecordsFromName();
                    break;
                case 2:
                    findRecordsFromTime();
                    break;
                case 3:
                    findRecordFromTemplate();
                    break;
                case 4:
                    findRecordsFromSomeCriterias();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Вы сделали неправильный выбор! Попробуйте еще раз!");
            }
        } while (selection != 5);
    }

    private void findRecordsFromName(){
        System.out.println("Введите имя пользователя\n");
        String userName = select.next();
    }

    private void findRecordsFromTime(){

        System.out.println("Введите время\n");
        String time = select.next();
    }

    private void findRecordFromTemplate(){
        System.out.println("Введите текст сообщения для шаблона\n");
        String textOfMessage = select.next();
    }

    private String isPassedCriteria(String select){
        String criteria;
        if (select.equals("*")){
            criteria = select;
        } else criteria = null;
        return criteria;
    }

    private void findRecordsFromSomeCriterias(){

        System.out.println("Введите имя пользователя или '*' чтобы пропустить этот пункт");
        recordCriteria.setUserName(isPassedCriteria(select.next()));

        System.out.println("Введите дату или '*' чтобы пропустить этот пункт");
        recordCriteria.setDate(isPassedCriteria(select.next()));

        System.out.println("Введите шаблон сообщения или '*' чтобы пропустить этот пункт");
        recordCriteria.setRecordBody(isPassedCriteria(select.next()));
    }

}
