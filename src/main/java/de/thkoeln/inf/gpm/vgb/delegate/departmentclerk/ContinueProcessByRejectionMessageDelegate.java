package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class ContinueProcessByRejectionMessageDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

        Map<String, Object> processVariables;

        processVariables = execution.getVariables();
        processVariables.putIfAbsent("approved", true);

        // TODO: set up Variable Store
        String correlationId = (String) processVariables.get("correlationId");

        runtimeService
                .createMessageCorrelation(ProcessVariableConstants.INT_RECEIVED_REJECTION_MESSAGE)
                .setVariables(processVariables)
                .processInstanceBusinessKey(correlationId)
                .correlate();

    }
}
