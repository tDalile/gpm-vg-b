package de.thkoeln.inf.gpm.vgb.delegate.customer;


import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class ContinueProcessByObjectionMessageDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) {
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		ProcessContext processContext = new ProcessContext(execution);
		
		// fill the reply message with this instance process variables
		Map<String, Object> processVariables = new HashMap();
		processVariables = execution.getVariables();
		// put, if not by user task
		processVariables.putIfAbsent("approved", true);			

		// set the correlation id to identify the waiting process
		// TODO: add to Variable Store
		String correlationId = (String) processVariables.get("correlationId");

		runtimeService
				.createMessageCorrelation("processContext.getInternal().getObjectionMessage()")
				.setVariables(processVariables)
				// set the correlation id as processInstanceBusinessKey of the waiting process
				.processInstanceBusinessKey(correlationId)
				.correlate();

	  }
}
