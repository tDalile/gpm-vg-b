package de.thkoeln.inf.gpm.vgb.util;

import de.thkoeln.inf.gpm.vgb.model.external.Customer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestDataUtil {

    private static final String[] LAST_NAMES = {"Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meier",
            "Wagner", "Becker", "Schulz", "Hoffmann"};

    private static final String[] FIRST_NAMES = {"Peter", "Michael", "Thomas", "Andreas", "Stefan", "Anna",
            "Andrea", "Karin", "Monika", "Maria"};

    private static final String[] STREETS = {"Hauptstraße", "Schillerstraße", "Rathausplatz", "Bahnhofstraße",
            "Schlossallee"};



    public static Customer createCustomer() {
        Long id = new Random().nextLong()*1000000;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();
        String entry = formatter.format(date);

        Long insurantId = new Random().nextLong()*1000000;

        return new Customer(id,entry, insurantId);
    }


    private static <T> T randomEntry(T[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }

    String formatDate(Date date) {

        String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

        return dateFormat.format(date);

    }


}
