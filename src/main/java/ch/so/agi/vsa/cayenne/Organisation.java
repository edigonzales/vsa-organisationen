package ch.so.agi.vsa.cayenne;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ch.so.agi.vsa.cayenne.auto._Organisation;

public class Organisation extends _Organisation {

    private static final long serialVersionUID = 1L;

    private LocalDate letzteAenderungDate;
    
    public LocalDate getLetzteAenderungDate() {
        beforePropertyRead("letzteAenderung");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(super.letzteAenderung, formatter);
    }

}
