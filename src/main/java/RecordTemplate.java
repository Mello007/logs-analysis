import java.time.LocalDate;


/**
 *  RecordTemplate contains secondDate. This class extends from Record.
 *  Also there are getters and setters
 */
class RecordTemplate extends Record{
    private LocalDate SecondDate;

    LocalDate getSecondDate() {
        return SecondDate;
    }

    void setSecondDate(LocalDate secondDate) {
        SecondDate = secondDate;
    }
}
