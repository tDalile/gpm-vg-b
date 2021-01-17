/**
 * Central definition of Keys for Variables here
 * Reference: https://blog.viadee.de/umgang-mit-variablen-in-camunda-prozessen
 */

package de.thkoeln.inf.gpm.vgb.model;

public final class ProcessVariableConstants {

    //TODO: add other variables

    public static final String EXT_CUSTOMER = "ext_customer";


    public static final String INT_NEW_CUSTOMER = "int_newCustomer";
    public static final String INT_CUSTOMER_ID = "int_customerId";
    public static final String INT_CUSTOMER_PASSWORD = "int_customerPassword";


    public static final String INT_INSURANT_ID = "int_insurantId";
    public static final String INT_INSURANT_NAME = "int_insurantName";
    public static final String INT_INSURANT_FIRST_NAME = "int_insurantFirstName";
    public static final String INT_INSURANT_BIRTHDAY = "int_insurantBirthday";
    public static final String INT_INSURANT_SEX = "int_insurantSex";
    public static final String INT_INSURANT_SIZE = "int_insurantSize";
    public static final String INT_INSURANT_WEIGHT = "int_insurantWeight";
    public static final String INT_INSURANT_ZIP = "int_insurantZip";
    public static final String INT_INSURANT_CITY = "int_insurantCity";
    public static final String INT_INSURANT_STREET = "int_insurantStreet";
    public static final String INT_INSURANT_HOUSENUMBER = "int_insurantHouseNumber";
    public static final String INT_INSURANT_PASSWORD = "int_insurantPassword";

    public static final String INT_INSURANT_BMI = "int_insurantBMI";


    public static final String INT_CLAIM_FOR_FAMILY_MEMBER = "int_claimForFamilyMember";
    public static final String INT_IS_PREMIUM_CLAIM = "int_isPremiumClaim";

    //TODO(tbd): provide only one or several?
    public static final String INT_PRECONDITION1 = "int_precondition1";
    public static final String INT_PRECONDITION2 = "int_precondition2";
    public static final String INT_PRECONDITION3 = "int_precondition3";

    public static final String INT_INSTANTIATION_MESSAGE = "int_instantiationMessage";
    public static final String INT_RECEIVED_REVISION_MESSAGE = "int_receivedRevisionMessage";










    // No instantiation possible
    private ProcessVariableConstants() {
    }
}
