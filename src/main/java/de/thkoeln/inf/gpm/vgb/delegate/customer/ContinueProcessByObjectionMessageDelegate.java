package de.thkoeln.inf.gpm.vgb.delegate.customer;


import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class ContinueProcessByObjectionMessageDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        Map<String, Object> processVariables = execution.getVariables();

        processVariables.putIfAbsent("approved", true);

        // set the correlation id to identify the waiting process
        // TODO: add to Variable Store
        String correlationId = (String) processVariables.get("correlationId");
        System.out.println("correlationID: " + correlationId);

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
//		runtimeService
//				.createMessageCorrelation(ProcessVariableConstants.INT_OBJECTION_MESSAGE)
//				.setVariables(processVariables)
//				// set the correlation id as processInstanceBusinessKey of the waiting process
//				.processInstanceBusinessKey(correlationId)
//				.correlate();

        runtimeService.startProcessInstanceByMessage(
                ProcessVariableConstants.INT_OBJECTION_MESSAGE,
                processVariables
        );
    }
}
