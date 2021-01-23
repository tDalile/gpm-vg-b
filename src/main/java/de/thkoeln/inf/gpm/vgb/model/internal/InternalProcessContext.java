/**
 * All Runtime-Variables which should not exist outside of process-instance
 * Reference: https://blog.viadee.de/umgang-mit-variablen-in-camunda-prozessen
 */

package de.thkoeln.inf.gpm.vgb.model.internal;

import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import org.camunda.bpm.engine.delegate.VariableScope;

import java.util.Date;

public class InternalProcessContext {
    private final VariableScope execution;


    public InternalProcessContext(final VariableScope execution) {
        this.execution = execution;
    }


    // Customer
    public Long getCustomerId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_CUSTOMER_ID);
    }

    public void setCustomerId(Long customerId) {
        execution.setVariable(ProcessVariableConstants.INT_CUSTOMER_ID, customerId);
    }

    public String getCustomerPassword() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_CUSTOMER_PASSWORD);
    }

    public void setCustomerPassword(String customerPassword) {
        execution.setVariable(ProcessVariableConstants.INT_CUSTOMER_PASSWORD, customerPassword);
    }

    public String getCustomerEntry() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_CUSTOMER_ENTRY);
    }

    public void setCustomerEntry(String customerEntry) {
        execution.setVariable(ProcessVariableConstants.INT_CUSTOMER_ENTRY, customerEntry);
    }


    // Insurant
    public Long getInsurantId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_INSURANT_ID);
    }

    public void setInsurantId(Long insurantId) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_ID, insurantId);
    }

    public String getInsurantName() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_NAME);
    }

    public void setInsurantName(String insurantName) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_NAME, insurantName);
    }

    public String getInsurantFirstName() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_FIRST_NAME);
    }

    public void setInsurantFirstName(String insurantFirstName) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_FIRST_NAME, insurantFirstName);
    }

    public Date getInsurantBirthdate() {
        return (Date) execution.getVariable(ProcessVariableConstants.INT_INSURANT_BIRTHDATE);
    }

    public void setInsurantBirthday(Date insurantBirthdate) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_BIRTHDATE, insurantBirthdate);
    }

    public String getInsurantSex() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_SEX);
    }

    public void setInsurantSex(String insurantSex) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_SEX, insurantSex);
    }

    public Double getInsurantSize() {
        return (Double) execution.getVariable(ProcessVariableConstants.INT_INSURANT_SIZE);
    }

    public void setInsurantSize(Double insurantSize) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_SIZE, insurantSize);
    }

    public Double getInsurantWeight() {
        return (Double) execution.getVariable(ProcessVariableConstants.INT_INSURANT_WEIGHT);
    }

    public void setInsurantWeight(Double insurantWeight) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_WEIGHT, insurantWeight);
    }


    // Location
    public Long getLocationId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_LOCATION_ID);
    }

    public void setLocationId(Long locationId) {
        execution.setVariable(ProcessVariableConstants.INT_LOCATION_ID, locationId);
    }

    public String getLocationZip() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_LOCATION_ZIP);
    }

    public void setLocationZip(String locationZip) {
        execution.setVariable(ProcessVariableConstants.INT_LOCATION_ZIP, locationZip);
    }

    public String getLocationName() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_LOCATION_NAME);
    }

    public void setLocationName(String locationName) {
        execution.setVariable(ProcessVariableConstants.INT_LOCATION_NAME, locationName);
    }


    // Address
    public Long getAddressId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_ADDRESS_ID);
    }

    public void setAddressId(Long addressId) {
        execution.setVariable(ProcessVariableConstants.INT_ADDRESS_ID, addressId);
    }
    public String getAddressStreet() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_ADDRESS_STREET);
    }

    public void setAddressStreet(String addressStreet) {
        execution.setVariable(ProcessVariableConstants.INT_ADDRESS_STREET, addressStreet);
    }

    public String getAddressHousenumber() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_ADDRESS_HOUSE_NUMBER);
    }

    public void setAddressHousenumber(String addressHousenumber) {
        execution.setVariable(ProcessVariableConstants.INT_ADDRESS_HOUSE_NUMBER, addressHousenumber);
    }


    //Claim
    public Long getClaimId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_CLAIM_ID);
    }

    public void setClaimId(Long claimId) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_ID, claimId);
    }

    public String getClaimDate() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_CLAIM_DATE);
    }

    public void setClaimDate(String claimDate) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_DATE, claimDate);
    }

    public Date getClaimDesiredStartDate() {
        return (Date) execution.getVariable(ProcessVariableConstants.INT_CLAIM_DESIRED_START_DATE);
    }

    public void setClaimDesiredStartDate(Date desiredStartDate) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_DESIRED_START_DATE, desiredStartDate);
    }

    public Double getClaimBMI() {
        return (Double) execution.getVariable(ProcessVariableConstants.INT_CLAIM_BMI);
    }

    public void setClaimBMI(Double claimBMI) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_BMI, claimBMI);
    }

    public Long getClaimRiskFactorAge() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_CLAIM_RISK_FACTOR_AGE);
    }

    public void setClaimRiskFactorAge(Long riskFactorAge) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_RISK_FACTOR_AGE, riskFactorAge);
    }

    public Long getClaimRiskFactorBMI() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_CLAIM_RISK_FACTOR_BMI);
    }

    public void setClaimRiskFactorBMI(Long riskFactorBMI) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_RISK_FACTOR_BMI, riskFactorBMI);
    }

    public Long getClaimRiskCategoryMedicalHistory() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_CLAIM_RISK_CATEGORY_MEDICAL_HISTORY);
    }

    public void setClaimRiskCategoryMedicalHistory(Long riskCategoryMedicalHistory) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_RISK_CATEGORY_MEDICAL_HISTORY, riskCategoryMedicalHistory);
    }

    public Long getClaimRiskFactorMedicalFactor() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_CLAIM_RISK_FACTOR_MEDICAL_FACTOR);
    }

    public void setClaimRiskFactorMedicalFactor(Long riskFactorMedicalFactor) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_RISK_FACTOR_MEDICAL_FACTOR, riskFactorMedicalFactor);
    }

    public Boolean getClaimIsInsurable() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_CLAIM_IS_INSURABLE);
    }

    public void setClaimIsInsurable(Boolean claimIsInsurable) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_IS_INSURABLE, claimIsInsurable);
    }

    public String getClaimRejectionReason() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_CLAIM_REJECTION_REASON);
    }

    public void setClaimRejectionReason(String rejectionReason) {
        execution.setVariable(ProcessVariableConstants.INT_CLAIM_REJECTION_REASON, rejectionReason);
    }


    // InsurancePolicy
    public Long getInsurancePolicyId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_ID);
    }

    public void setInsurancePolicyId(Long insurancePolicyId) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_ID, insurancePolicyId);
    }

    public Boolean getInsurancePolicyIsNewCustomer() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_IS_NEW_CUSTOMER);
    }

    public void setInsurancePolicyIsNewCustomer(Boolean insurancePolicyIsNewCustomer) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_IS_NEW_CUSTOMER, insurancePolicyIsNewCustomer);
    }

    public Double getInsurancePolicyRiskSurcharge() {
        return (Double) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_RISK_SURCHARGE);
    }

    public void setInsurancePolicyRiskSurcharge(Double insurancePolicyRiskSurcharge) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_RISK_SURCHARGE, insurancePolicyRiskSurcharge);
    }

    public String getInsurancePolicyRiskSurchargeReason() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_RISK_SURCHARGE_REASON);
    }

    public void setInsurancePolicyRiskSurchargeReason(String insurancePolicyRiskSurchargeReason) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_RISK_SURCHARGE_REASON, insurancePolicyRiskSurchargeReason);
    }

    public Double getInsurancePolicyMonthlyContribution() {
        return (Double) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_MONTHLY_CONTRIBUTION);
    }

    public void setInsurancePolicyMonthlyContribution(Double insurancePolicyMonthlyContribution) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_MONTHLY_CONTRIBUTION, insurancePolicyMonthlyContribution);
    }

    public Double getInsurancePolicyInitialContribution() {
        return (Double) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_INITIAL_CONTRIBUTION);
    }

    public void setInsurancePolicyInitialContribution(Double insurancePolicyInitialContribution) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_INITIAL_CONTRIBUTION, insurancePolicyInitialContribution);
    }

    public String getInsurancePolicyStartDateOfContract() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_START_DATE_OF_CONTRACT);
    }

    public void setInsurancePolicyStartDateOfContract(Double insurancePolicyStartDateOfContract) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_START_DATE_OF_CONTRACT, insurancePolicyStartDateOfContract);
    }

    public Boolean getInsurancePolicyIsPremiumTariff() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_IS_PREMIUM_TARIFF);
    }

    public void setInsurancePolicyIsPremiumTariff(Boolean insurancePolicyIsPremiumTariff) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_IS_PREMIUM_TARIFF, insurancePolicyIsPremiumTariff);
    }

    public String getInsurancePolicySignDateOfContract() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_SIGN_DATE_OF_CONTRACT);
    }

    public void setInsurancePolicySignDateOfContract(String insurancePolicySignDateOfContract) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_SIGN_DATE_OF_CONTRACT, insurancePolicySignDateOfContract);
    }

    public Boolean getInsurancePolicyIsActive() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_IS_ACTIVE);
    }

    public void setInsurancePolicyIsActive(Boolean isActive) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANCE_POLICY_IS_ACTIVE, isActive);
    }


    // Medical History
    public Long getMedicalHistoryId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_MEDICAL_HISTORY_ID);
    }

    public void setMedicalHistoryId(Long medicalHistoryId) {
        execution.setVariable(ProcessVariableConstants.INT_MEDICAL_HISTORY_ID, medicalHistoryId);
    }


    // Precondition
    public Long getPreconditionId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_PRECONDITION_ID);
    }

    public void setPreconditionId(Long preconditionId) {
        execution.setVariable(ProcessVariableConstants.INT_PRECONDITION_ID, preconditionId);
    }

    public String getPreconditionList() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_PRECONDITION_LIST);
    }

    public void setPreconditionList(String preconditionList) {
        execution.setVariable(ProcessVariableConstants.INT_PRECONDITION_LIST, preconditionList);
    }

    // Disease
    public String getDiseaseList() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_DISEASE_LIST);
    }

    public void setDiseaseList(String list) {
        execution.setVariable(ProcessVariableConstants.INT_DISEASE_LIST, list);
    }

    public Long getDiseaseId() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_DISEASE_ID);
    }

    public void setDiseaseId(Long diseaseId) {
        execution.setVariable(ProcessVariableConstants.INT_DISEASE_ID, diseaseId);
    }

    public Long getDiseaseCategory() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_DISEASE_CATEGORY);
    }

    public void setDiseaseCategory(Long diseaseCategory) {
        execution.setVariable(ProcessVariableConstants.INT_DISEASE_CATEGORY, diseaseCategory);
    }

    public String getDiseaseDescription() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_DISEASE_DESCRIPTION);
    }

    public void setDiseaseDescription(String diseaseDescription) {
        execution.setVariable(ProcessVariableConstants.INT_DISEASE_DESCRIPTION, diseaseDescription);
    }


    // (here variables for sequence flows)
    public Boolean getIsNewCustomer() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_IS_NEW_CUSTOMER);
    }

    public void setIsNewCustomer(Boolean isNewCustomer) {
        execution.setVariable(ProcessVariableConstants.INT_IS_NEW_CUSTOMER, isNewCustomer);
    }

    public Boolean getIsClaimForFamilyMember() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_IS_CLAIM_FOR_FAMILY_MEMBER);
    }

    public void setIsClaimForFamilyMember(Boolean isClaimForFamilyMember) {
        execution.setVariable(ProcessVariableConstants.INT_IS_CLAIM_FOR_FAMILY_MEMBER, isClaimForFamilyMember);
    }

    public Boolean getIsPremiumClaim() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_IS_PREMIUM_CLAIM);
    }

    public void setIsPremiumClaim(Boolean isPremiumClaim) {
        execution.setVariable(ProcessVariableConstants.INT_IS_PREMIUM_CLAIM, isPremiumClaim);
    }

    public Boolean getIsLoginSuccessful() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_IS_LOGIN_SUCCESSFUL);
    }

    public void setIsLoginSuccessful(Boolean isLoginSuccessful) {
        execution.setVariable(ProcessVariableConstants.INT_IS_LOGIN_SUCCESSFUL, isLoginSuccessful);
    }

    public Boolean getIsInsurable() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_IS_INSURABLE);
    }

    public void setIsInsurable(Boolean isInsurable) {
        execution.setVariable(ProcessVariableConstants.INT_IS_INSURABLE, isInsurable);
    }

    public Boolean getIsAddSurcharge() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_IS_ADD_SURCHARGE);
    }

    public void setIsAddSurcharge(Boolean isAddSurcharge) {
        execution.setVariable(ProcessVariableConstants.INT_IS_ADD_SURCHARGE, isAddSurcharge);
    }


    // (here volatile variables)
    public Integer getInsurantAge() {
        return (Integer) execution.getVariable(ProcessVariableConstants.INT_INSURANT_AGE);
    }

    public void setInsurantAge(Integer insurantAge) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_AGE, insurantAge);
    }


    // (here messages)
    public String getInstantiationMessage() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSTANTIATION_MESSAGE);
    }

    public void setInstantiationMessage(String instantiationMessage) {
        execution.setVariable(ProcessVariableConstants.INT_INSTANTIATION_MESSAGE, instantiationMessage);
    }

    public String getReceivedRevisionMessage() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_RECEIVED_REVISION_MESSAGE);
    }

    public void setReceivedRevisionMessage(String receivedRevisionMessage) {
        execution.setVariable(ProcessVariableConstants.INT_RECEIVED_REVISION_MESSAGE, receivedRevisionMessage);
    }

    public String getReceivedRejectionMessage() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_RECEIVED_REJECTION_MESSAGE);
    }

    public void setReceivedRejectionMessage(String receivedRejectionMessage) {
        execution.setVariable(ProcessVariableConstants.INT_RECEIVED_REJECTION_MESSAGE, receivedRejectionMessage);
    }

    public String getApprovedPolicyMessage() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_APPROVED_POLICY_MESSAGE);
    }

    public void setApprovedPolicyMessage(String approvedPolicyMessage) {
        execution.setVariable(ProcessVariableConstants.INT_APPROVED_POLICY_MESSAGE, approvedPolicyMessage);
    }

    public String getObjectionMessage() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_OBJECTION_MESSAGE);
    }

    public void setObjectionMessage(String objectionMessage) {
        execution.setVariable(ProcessVariableConstants.INT_OBJECTION_MESSAGE, objectionMessage);
    }

}
