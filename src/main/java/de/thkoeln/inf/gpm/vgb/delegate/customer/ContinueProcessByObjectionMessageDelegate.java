package de.thkoeln.inf.gpm.vgb.delegate.customer;


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
public class ContinueProcessByObjectionMessageDelegate implements JavaDelegate {

	 /**
	 *
	 */
	public void execute(DelegateExecution execution) {
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		
		// fill the reply message with this instance process variables
		Map<String, Object> processVariables = new HashMap();
		processVariables = execution.getVariables();
		// put, if not by user task
		processVariables.putIfAbsent("approved", true);			

		// set the correlation id to identify the waiting process
		String correlationId = (String) processVariables.get("correlationId");

		runtimeService
				.createMessageCorrelation("objectionMessage")
				.setVariables(processVariables)
				// set the correlation id as processInstanceBusinessKey of the waiting process
				.processInstanceBusinessKey(correlationId)
				.correlate();

		// set the correlation id to identify the waiting process


		/*
		processVariables.put("kundennr", execution.getVariable("mkundennr"));
		processVariables.put("eintritt", execution.getVariable("meintritt"));
		processVariables.put("versicherter_id", execution.getVariable("mversicherter_id"));
		processVariables.put("nachname", execution.getVariable("mnachname"));
		processVariables.put("vorname", execution.getVariable("mvorname"));
		processVariables.put("geburtsdatum", execution.getVariable("mgeburtsdatum"));
		processVariables.put("geschlecht", execution.getVariable("mgeschlecht"));
		processVariables.put("groesse", execution.getVariable("mgroesse"));
		processVariables.put("gewicht", execution.getVariable("mgewicht"));

		 */
	  }
}
