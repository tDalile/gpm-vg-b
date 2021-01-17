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

    public Integer getCustomerId() {
        return (Integer) execution.getVariable(ProcessVariableConstants.INT_CUSTOMER_ID);
    }

    public void setCustomerId(Integer customerId) {
        execution.setVariable(ProcessVariableConstants.INT_CUSTOMER_ID, customerId);
    }

    public String getCustomerPassword() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_CUSTOMER_PASSWORD);
    }

    public void setCustomerPassword(String customerPassword) {
        execution.setVariable(ProcessVariableConstants.INT_CUSTOMER_PASSWORD, customerPassword);
    }

    public Double getInsurantBMI() {
        return (Double) execution.getVariable(ProcessVariableConstants.INT_INSURANT_BMI);
    }

    public void setInsurantBMI(Double insurantBMI) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_BMI, insurantBMI);
    }

    public Boolean getIsPremiumClaim() {
        return (Boolean) execution.getVariable(ProcessVariableConstants.INT_IS_PREMIUM_CLAIM);
    }

    public void setIsPremiumClaim(Integer isPremiumClaim) {
        execution.setVariable(ProcessVariableConstants.INT_IS_PREMIUM_CLAIM, isPremiumClaim);
    }

    public Integer getInsurantId() {
        return (Integer) execution.getVariable(ProcessVariableConstants.INT_INSURANT_ID);
    }

    public void setInsurantId(Integer insurantId) {
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

    public Date getInsurantBirthday() {
        return (Date) execution.getVariable(ProcessVariableConstants.INT_INSURANT_BIRTHDAY);
    }

    public void setInsurantBirthday(Date insurantBirthday) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_BIRTHDAY, insurantBirthday);
    }

    public String getInsurantSex() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_SEX);
    }

    public void setInsurantSex(String insurantSex) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_SEX, insurantSex);
    }

    public Long getInsurantSize() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_INSURANT_SIZE);
    }

    public void setInsurantSize(Long insurantSize) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_SIZE, insurantSize);
    }

    public Long getInsurantWeight() {
        return (Long) execution.getVariable(ProcessVariableConstants.INT_INSURANT_WEIGHT);
    }

    public void setInsurantWeight(Long insurantWeight) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_WEIGHT, insurantWeight);
    }

    public String getInsurantZip() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_ZIP);
    }

    public void setInsurantZip(String insurantZip) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_ZIP, insurantZip);
    }

    public String getInsurantCity() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_CITY);
    }

    public void setInsurantCity(String insurantCity) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_CITY, insurantCity);
    }

    public String getInsurantStreet() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_STREET);
    }

    public void setInsurantStreet(String insurantStreet) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_STREET, insurantStreet);
    }

    public String getInsurantHousenumber() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_HOUSENUMBER);
    }

    public void setInsurantHousenumber(String insurantHousenumber) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_HOUSENUMBER, insurantHousenumber);
    }

    public String getInsurantPassword() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_INSURANT_PASSWORD);
    }

    public void setInsurantPassword(String insurantPassword) {
        execution.setVariable(ProcessVariableConstants.INT_INSURANT_PASSWORD, insurantPassword);
    }

    public String getPrecondition1() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_PRECONDITION1);
    }

    public void setPrecondition1(String precondition1) {
        execution.setVariable(ProcessVariableConstants.INT_PRECONDITION1, precondition1);
    }

    public String getPrecondition2() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_PRECONDITION2);
    }

    public void setPrecondition2(String precondition2) {
        execution.setVariable(ProcessVariableConstants.INT_PRECONDITION2, precondition2);
    }

    public String getPrecondition3() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_PRECONDITION3);
    }

    public void setPrecondition3(String precondition3) {
        execution.setVariable(ProcessVariableConstants.INT_PRECONDITION3, precondition3);
    }

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

}
