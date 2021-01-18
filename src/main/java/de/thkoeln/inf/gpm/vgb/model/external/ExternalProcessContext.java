/**
 * External Variables which are persistent outside of process-instance
 * Reference: https://blog.viadee.de/umgang-mit-variablen-in-camunda-prozessen
 */

package de.thkoeln.inf.gpm.vgb.model.external;

import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import org.camunda.bpm.engine.delegate.VariableScope;

public class ExternalProcessContext {

    private final VariableScope execution;

    public ExternalProcessContext(final VariableScope execution) {
        this.execution = execution;
    }

    public Customer getCustomer() {
        return (Customer) execution.getVariable(ProcessVariableConstants.EXT_CUSTOMER);
    }

    public void setCustomer(Customer customer) {
        execution.setVariable(ProcessVariableConstants.EXT_CUSTOMER, customer);
    }

    public Location getLocation() {
        return (Location) execution.getVariable(ProcessVariableConstants.EXT_LOCATION);
    }

    public void setLocation(Location location) {
        execution.setVariable(ProcessVariableConstants.EXT_LOCATION, location);
    }

    public void setInsurant(Insurant insurant) {
        execution.setVariable(ProcessVariableConstants.EXT_INSURANT, insurant);
    }

    public Insurant getInsurant() {
        return (Insurant) execution.getVariable(ProcessVariableConstants.EXT_INSURANT);
    }

    public void setAddress(Address address) {
        execution.setVariable(ProcessVariableConstants.EXT_ADDRESS, address);
    }

    public Address getAddress() {
        return (Address) execution.getVariable(ProcessVariableConstants.EXT_ADDRESS);
    }

    public void setClaim(Claim claim) {
        execution.setVariable(ProcessVariableConstants.EXT_CLAIM, claim);
    }

    public Claim getClaim() {
        return (Claim) execution.getVariable(ProcessVariableConstants.EXT_CLAIM);
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        execution.setVariable(ProcessVariableConstants.EXT_INSURANCE_POLICY, insurancePolicy);
    }

    public InsurancePolicy getInsurancePolicy() {
        return (InsurancePolicy) execution.getVariable(ProcessVariableConstants.EXT_INSURANCE_POLICY);
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        execution.setVariable(ProcessVariableConstants.EXT_MEDICAL_HISTORY, medicalHistory);
    }

    public MedicalHistory getMedicalHistory() {
        return (MedicalHistory) execution.getVariable(ProcessVariableConstants.EXT_MEDICAL_HISTORY);
    }

    public void setPrecondition(Precondition precondition) {
        execution.setVariable(ProcessVariableConstants.EXT_PRECONDITION, precondition);
    }

    public Precondition getPrecondition() {
        return (Precondition) execution.getVariable(ProcessVariableConstants.EXT_PRECONDITION);
    }

    public void setDisease(Disease disease) {
        execution.setVariable(ProcessVariableConstants.EXT_DISEASE, disease);
    }

    public Disease getDisease() {
        return (Disease) execution.getVariable(ProcessVariableConstants.EXT_DISEASE);
    }

}
