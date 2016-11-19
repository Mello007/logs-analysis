import java.time.LocalDate;

/**
 * @author Artem
 *  Record contains userName, date and recordBody.
 *  Also there are getters and setters
 */

class Record {

    private String userName;
    private LocalDate date;
    private String recordBody;

    String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    LocalDate getDate() {
        return date;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }

    String getRecordBody() {
        return recordBody;
    }

    void setRecordBody(String recordBody) {
        this.recordBody = recordBody;
    }
}
