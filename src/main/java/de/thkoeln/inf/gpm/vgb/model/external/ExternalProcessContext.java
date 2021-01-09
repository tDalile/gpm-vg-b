package de.thkoeln.inf.gpm.vgb.model.external;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import org.camunda.bpm.engine.delegate.VariableScope;

public class ExternalProcessContext {

    private final VariableScope execution;

    public ExternalProcessContext(final VariableScope execution) {
        this.execution = execution;
    }

    /*public void setCustomer(Customer customer) {
        execution.setVariable(EXT_CUSTOMER, customer);
    }
    */
    public void setCustomer (Customer customer) {
        execution.setVariable(ProcessVariableConstants.EXT_CUSTOMER, customer);
    }

    public Customer getCustomer() {
        return (Customer) execution.getVariable(ProcessVariableConstants.EXT_CUSTOMER);
    }

    /*
    public void setPreDiseases(PreDiseases preDiseases) {
        execution.setVariable(ProcessVariableConstants.EXT_CAR, preDiseases);
    }

    public String getPolicyNumber() {
        return (String) execution.getVariable(ProcessVariableConstants.EXT_POLICY_NUMBER);
    }

    public void setPolicyNumber(String policyNumber) {
        execution.setVariable(ProcessVariableConstants.EXT_POLICY_NUMBER, policyNumber);
    }*/
}
