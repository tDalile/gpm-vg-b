package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class DeactivatePolicyDelegate implements JavaDelegate {

    private long policeID;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        ProcessContext processContext = new ProcessContext(delegateExecution);

        policeID = processContext.getInternal().getInsurancePolicyId();

        processContext.getInternal().setInsurancePolicyIsActive(false);

    }

    // TODO Change the active flag of the police in the DB

}
