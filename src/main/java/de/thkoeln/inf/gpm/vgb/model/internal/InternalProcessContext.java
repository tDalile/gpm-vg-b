package de.thkoeln.inf.gpm.vgb.model.internal;

import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import de.thkoeln.inf.gpm.vgb.model.external.ExternalProcessContext;
import de.thkoeln.inf.gpm.vgb.model.internal.InternalProcessContext;
import org.camunda.bpm.engine.delegate.VariableScope;

public class InternalProcessContext {
    private final VariableScope execution;

    public InternalProcessContext(final VariableScope execution) {
        this.execution = execution;
    }

    //get Policy, Claim
    /*public String getPolicyNumber() {
        return (String) execution.getVariable(ProcessVariableConstants.INT_POLICY_NUMBER);
    }

    public void setPolicy(Policy policy) {
        execution.setVariable(ProcessVariableConstants.INT_POLICY, policy);
    }*/

}
