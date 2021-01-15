package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class ActivatePolicyDelegate implements JavaDelegate {

    private long policeID;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        long policeID = (long) processVariables.get("policeID");

        processVariables.put("active", true);

        delegateExecution.setVariables(processVariables);

    }

    // TODO: Status der Police mit der gegebenen ID ändern



}
