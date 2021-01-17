package de.thkoeln.inf.gpm.vgb.delegate.customer;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class ContinueProcessByObjectionMessageDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) {
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		
		// fill the reply message with this instance process variables
		Map<String, Object> processVariables = new HashMap();
		processVariables = execution.getVariables();
		// put, if not by user task
		processVariables.putIfAbsent("approved", true);			

		// ProcessInstance startProcessInstanceByMessage(String messageName, Map<String, Object> processVariables);

		// set the correlation id to identify the waiting process
		String correlationId = (String) processVariables.get("correlationId");


		runtimeService
				.createMessageCorrelation("objectionMessage")
				.setVariables(processVariables)
				.processInstanceBusinessKey(correlationId)
				.correlate();
	  }
}
