package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * This is an easy adapter implementation
 * illustrating how a Java Delegate can be used
 * from within a BPMN 2.0 Send Task to start another process by mesaage
 */
@Slf4j
public class InstantiateProcessByMessageDelegate implements JavaDelegate {
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();

        Map<String, Object> processVariables = delegateExecution.getVariables();

        // TODO: Set up Variable Store
        // set the correlation id to identify this in receiving process
        String correlationId = delegateExecution.getBusinessKey();
        if (correlationId == null) {
            correlationId = delegateExecution.getProcessInstanceId();
            delegateExecution.setProcessBusinessKey(correlationId);
        }
        processVariables.put("correlationId", correlationId);

        // correlate process with message name
        runtimeService.startProcessInstanceByMessage(
                ProcessVariableConstants.INT_INSTANTIATION_MESSAGE,
                processVariables
        );
    }
}
