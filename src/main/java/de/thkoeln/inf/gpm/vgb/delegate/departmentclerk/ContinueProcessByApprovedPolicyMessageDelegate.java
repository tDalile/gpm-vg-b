package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class ContinueProcessByApprovedPolicyMessageDelegate implements JavaDelegate {

    public void execute(DelegateExecution delegateExecution) {
        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();

        Map<String, Object> processVariables;

        processVariables = delegateExecution.getVariables();
        processVariables.putIfAbsent("approved", true);

        // TODO: set up Variable Store
        String correlationId = (String) processVariables.get("correlationId");

		runtimeService
                .createMessageCorrelation(ProcessVariableConstants.INT_APPROVED_POLICY_MESSAGE)
                .setVariables(processVariables)
                .processInstanceBusinessKey(correlationId)
                .correlate();

    }
}
