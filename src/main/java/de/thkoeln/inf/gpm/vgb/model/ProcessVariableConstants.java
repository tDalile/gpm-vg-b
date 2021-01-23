/**
 * Central definition of Keys for Variables here
 * Reference: https://blog.viadee.de/umgang-mit-variablen-in-camunda-prozessen
 */

package de.thkoeln.inf.gpm.vgb.model;

public final class ProcessVariableConstants {

    // No instantiation possible
    private ProcessVariableConstants() {
    }

    // --- External objects (here DB entities) ---
    public static final String EXT_CUSTOMER = "ext_customer";
    public static final String EXT_LOCATION = "ext_location";
    public static final String EXT_INSURANT = "ext_insurant";
    public static final String EXT_ADDRESS= "ext_address";
    public static final String EXT_CLAIM= "ext_claim";
    public static final String EXT_INSURANCE_POLICY= "ext_insurancePolicy";
    public static final String EXT_MEDICAL_HISTORY= "ext_medicalHistory";
    public static final String EXT_PRECONDITION= "ext_precondition";
    public static final String EXT_DISEASE= "ext_disease";

    // --- Internal objects (here Attributes) ---

    // Customer
    public static final String INT_CUSTOMER_ID = "int_customerId";
    public static final String INT_CUSTOMER_PASSWORD = "int_customerPassword";
    public static final String INT_CUSTOMER_ENTRY = "int_customerEntry";

    // Insurant
    public static final String INT_INSURANT_ID = "int_insurantId";
    public static final String INT_INSURANT_NAME = "int_insurantName";
    public static final String INT_INSURANT_FIRST_NAME = "int_insurantFirstName";
    public static final String INT_INSURANT_BIRTHDATE = "int_insurantBirthdate";
    public static final String INT_INSURANT_SEX = "int_insurantSex";
    public static final String INT_INSURANT_SIZE = "int_insurantSize";
    public static final String INT_INSURANT_WEIGHT = "int_insurantWeight";

    // Location
    public static final String INT_LOCATION_ID = "int_locationId";
    public static final String INT_LOCATION_ZIP = "int_locationZip";
    public static final String INT_LOCATION_NAME = "int_locationName";

    // Address
    public static final String INT_ADDRESS_ID = "int_addressId";
    public static final String INT_ADDRESS_STREET = "int_addressStreet";
    public static final String INT_ADDRESS_HOUSE_NUMBER = "int_addressHouseNumber";

    //Claim
    public static final String INT_CLAIM_ID = "int_claimId";
    public static final String INT_CLAIM_DATE = "int_claimDate";
    public static final String INT_CLAIM_DESIRED_START_DATE = "int_claimDesiredStartDate";
    public static final String INT_CLAIM_BMI = "int_claimBMI";
    public static final String INT_CLAIM_RISK_FACTOR_AGE = "int_claimRiskFactorAge";
    public static final String INT_CLAIM_RISK_FACTOR_BMI = "int_claimRiskFactorBMI";
    public static final String INT_CLAIM_RISK_CATEGORY_MEDICAL_HISTORY = "int_claimRiskCategoryMedicalHistory";
    public static final String INT_CLAIM_RISK_FACTOR_MEDICAL_FACTOR = "int_claimRiskFactorMedicalFactor";
    public static final String INT_CLAIM_IS_INSURABLE = "int_claimIsInsurable";
    public static final String INT_CLAIM_REJECTION_REASON = "int_claimRejectionReason";

    // InsurancePolicy
    public static final String INT_INSURANCE_POLICY_ID = "int_insurancePolicyId";
    public static final String INT_INSURANCE_POLICY_IS_NEW_CUSTOMER = "int_insurancePolicyIsNewCustomer";
    public static final String INT_INSURANCE_POLICY_RISK_SURCHARGE = "int_insurancePolicyRiskSurcharge";
    public static final String INT_INSURANCE_POLICY_RISK_SURCHARGE_REASON = "int_insurancePolicyRiskSurchargeReason";
    public static final String INT_INSURANCE_POLICY_MONTHLY_CONTRIBUTION = "int_insurancePolicyMonthlyContribution";
    public static final String INT_INSURANCE_POLICY_INITIAL_CONTRIBUTION = "int_insurancePolicyInitialContribution";
    public static final String INT_INSURANCE_POLICY_START_DATE_OF_CONTRACT = "int_insurancePolicyStartDateOfContract";
    public static final String INT_INSURANCE_POLICY_IS_PREMIUM_TARIFF = "int_insurancePolicyIsPremiumTariff";
    public static final String INT_INSURANCE_POLICY_SIGN_DATE_OF_CONTRACT = "int_insurancePolicySignDateOfContract";
    public static final String INT_INSURANCE_POLICY_IS_ACTIVE = "int_insurancePolicyIsActive";

    // Medical History
    public static final String INT_MEDICAL_HISTORY_ID = "int_medicalHistoryId";

    // Precondition
    public static final String INT_PRECONDITION_ID = "int_preconditionId";
    public static final String INT_PRECONDITION_LIST = "int_preconditionList";

    // Disease
    public static final String INT_DISEASE_LIST = "int_diseaseList";
    public static final String INT_DISEASE_ID = "int_diseaseId";
    public static final String INT_DISEASE_CATEGORY = "int_diseaseCategory";
    public static final String INT_DISEASE_DESCRIPTION = "int_diseaseDescription";


    // (here variables for sequence flows)
    public static final String INT_IS_NEW_CUSTOMER = "int_isNewCustomer";
    public static final String INT_IS_CLAIM_FOR_FAMILY_MEMBER = "int_isClaimForFamilyMember";
    public static final String INT_IS_PREMIUM_CLAIM = "int_isPremiumClaim";
    public static final String INT_IS_LOGIN_SUCCESSFUL = "int_isLoginSuccessful";
    public static final String INT_IS_INSURABLE = "int_isInsurable";
    public static final String INT_IS_ADD_SURCHARGE = "int_isAddSurcharge";

    // (here volatile variables)
    public static final String INT_INSURANT_AGE = "int_insurantAge";


    // (here messages)
    public static final String INT_INSTANTIATION_MESSAGE = "int_instantiationMessage";
    public static final String INT_RECEIVED_REVISION_MESSAGE = "int_receivedRevisionMessage";
    public static final String INT_RECEIVED_REJECTION_MESSAGE = "int_receivedRejectionMessage";
    public static final String INT_APPROVED_POLICY_MESSAGE = "int_approvedPolicyMessage";
    public static final String INT_OBJECTION_MESSAGE = "int_objectionMessage";

}
