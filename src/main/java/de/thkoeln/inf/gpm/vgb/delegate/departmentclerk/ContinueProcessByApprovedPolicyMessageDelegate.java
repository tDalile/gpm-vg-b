package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;


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
		
		Map<String, Object> processVariables = new HashMap();
		processVariables = execution.getVariables();
		processVariables.putIfAbsent("approved", true);

		String correlationId = (String) processVariables.get("correlationId");

		runtimeService
		.createMessageCorrelation("approvedPolicyMessage")
		.setVariables(processVariables)
		.processInstanceBusinessKey(correlationId)
		.correlate();

	  }
}
