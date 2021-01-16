package de.thkoeln.inf.gpm.vgb;


import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import util.DbSetup;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class InsuranceCompanyB extends ServletProcessApplication {

  private static final String PROCESS_DEFINITION_KEY = "vgb";

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {
    DbSetup.INSTANCE.getDbWithDocker(); // TODO change to getDb(); while not using docker
    // start an initial process instance
//    Map<String, Object> variables = new HashMap<String, Object>();
//    variables.put("name", "John");
//    
//    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
  }

}
