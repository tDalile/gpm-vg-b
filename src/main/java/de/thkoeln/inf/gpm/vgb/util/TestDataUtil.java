package de.thkoeln.inf.gpm.vgb.util;

import de.thkoeln.inf.gpm.vgb.model.external.Customer;

import java.util.Date;
import java.util.Random;

public class TestDataUtil {

    private static final Long[] KUNDENNR = {1111L};
    private static final Date[] DATUM = {
            new Date(20200101)};
    private static final Long[] VERSICHERTER_ID = {1111L};

    public static Customer createCustomer() {
        Long kundennr = randomEntry(VERSICHERTER_ID);
        Date eintritt = randomEntry(DATUM);
        Long versicherter_id = randomEntry(VERSICHERTER_ID);
        return new Customer(kundennr,eintritt, versicherter_id);
    }

    private static <T> T randomEntry(T[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }

}
