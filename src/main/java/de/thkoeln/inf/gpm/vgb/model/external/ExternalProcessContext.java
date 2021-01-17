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



}
