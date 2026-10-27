package fr.efrei.transfret;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Dates {

    private static final String FORMAT_DATE = "dd/MM/yyyy";

    private Dates() {
    }

    public static String formater(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(FORMAT_DATE).format(date);
    }
}
