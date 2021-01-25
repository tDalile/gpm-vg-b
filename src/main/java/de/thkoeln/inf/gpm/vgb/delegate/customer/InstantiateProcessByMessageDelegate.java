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
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> processVariables = execution.getVariables();


        //TODO: setup serialization for objects?
		 /*
		 processVariables.put("mkundennr", execution.getVariable("kundennr"));
		 processVariables.put("meintritt", execution.getVariable("eintritt"));
		 processVariables.put("mversicherter_id", execution.getVariable("versicherter_id"));
		 processVariables.put("mnachname", execution.getVariable("nachname"));
		 processVariables.put("mvorname", execution.getVariable("vorname"));
		 processVariables.put("mgeburtsdatum", execution.getVariable("geburtsdatum"));
		 processVariables.put("mgeschlecht", execution.getVariable("geschlecht"));
		 processVariables.put("mgroesse", execution.getVariable("groesse"));
		 processVariables.put("mgewicht", execution.getVariable("gewicht"));
		 processVariables.put("mtarif", execution.getVariable("tarif"));
		 processVariables.put("mvorerkrankung", execution.getVariable("vorerkrankung"));
		 */


        // TODO: Set up Variable Store
        // set the correlation id to identify this in receiving process
        String correlationId = execution.getBusinessKey();
        if (correlationId == null) {            // if not set at process start
            correlationId = execution.getProcessInstanceId();
            execution.setProcessBusinessKey(correlationId);
        }
        processVariables.put("correlationId", correlationId);

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        // correlate process with message name
        runtimeService.startProcessInstanceByMessage(
                ProcessVariableConstants.INT_INSTANTIATION_MESSAGE,
                processVariables
        );
    }
}
