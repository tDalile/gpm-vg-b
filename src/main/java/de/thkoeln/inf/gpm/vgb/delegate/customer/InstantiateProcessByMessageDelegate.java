package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import model.Customer;
import de.thkoeln.inf.gpm.vgb.util.LogUtil;
import de.thkoeln.inf.gpm.vgb.util.TestDataUtil;
import model.Customer;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import lombok.extern.slf4j.Slf4j;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This is an easy adapter implementation 
 * illustrating how a Java Delegate can be used 
 * from within a BPMN 2.0 Send Task to start another process by mesaage
 * 
 */
@Slf4j
public class InstantiateProcessByMessageDelegate implements JavaDelegate {
	 public void execute(DelegateExecution execution) throws Exception{
		 ProcessContext processContext = new ProcessContext(execution);

		 Map<String, Object> processVariables = new HashMap();
		 // fill the message;
		 // Additional Layer: external constants to provide single point of truth
		 // Reference: https://blog.viadee.de/umgang-mit-variablen-in-camunda-prozessen
		 /**Customer customer = new Customer(
				 (Long)execution.getVariable("kundennr"),
				 (Date)execution.getVariable("eintritt"),
				 (Long)execution.getVariable("versicherter_id")
		 );
**/
		 //processContext.getExternal().setCustomer(customer);

		 //setup serialization for objects
		 //processVariables.put("mCustomer", processContext.getExternal().getCustomer());
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

		 //TODO: Implement constant approach
		 //processVariables.put("mversicherter_id", execution.getVariable(ProcessVariableConstants.VERSICHERTER_ID));


		 //TODO: better identifier
		  execution.setProcessBusinessKey("1");

		 // set the correlation id to identify this in receiving process
		 String correlationId = execution.getBusinessKey();
		if (correlationId == null) {			// if not set at process start
			correlationId = execution.getProcessInstanceId();
			execution.setProcessBusinessKey(correlationId);
		}
		 processVariables.put("correlationId", correlationId);

		 RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		 // correlate process with message name
		 runtimeService.startProcessInstanceByMessage("instantiationMessage", processVariables);


		 //DelegateExecution processInstance = execution.getProcessInstance();


		//TODO: proper logging



	  }
}
