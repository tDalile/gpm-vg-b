package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;


import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

/**
 * This is an easy adapter implementation 
 * illustrating how a Java Delegate can be used 
 * from within a BPMN 2.0 Send Task to reply to another process
 */
public class ContinueProcessByApprovedPolicyMessageDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) {
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

		ProcessContext processContext = new ProcessContext(execution);
		
		// fill the reply message with this instance process variables
		Map<String, Object> processVariables = new HashMap();
		processVariables = execution.getVariables();
		// put, if not by user task
		processVariables.putIfAbsent("approved", true);			

		// set the correlation id to identify the waiting process
		// TODO: set up Variable Store
		String correlationId = (String) processVariables.get("correlationId");

		runtimeService
		.createMessageCorrelation(ProcessVariableConstants.INT_APPROVED_POLICY_MESSAGE)
		.setVariables(processVariables)
		// set the correlation id as processInstanceBusinessKey of the waiting process
		.processInstanceBusinessKey(correlationId)
		.correlate();

	  }
}
