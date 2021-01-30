package de.thkoeln.inf.gpm.vgb;


import de.thkoeln.inf.gpm.vgb.util.TestDataUtil;
import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import util.DbUtil;

import java.util.logging.Logger;


/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class InsuranceCompanyB extends ServletProcessApplication {

  private static final Logger LOGGER = Logger.getLogger(InsuranceCompanyB.class.getName());

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access
   * the processes the application has deployed.
   */
  @PostDeploy
  public void startFirstProcess(ProcessEngine processEngine) {
    LOGGER.info("Running DbUtil.");
    DbUtil.INSTANCE.getDbWithDocker(); // TODO change to getDb(); while not using docker
    createUsers(processEngine);

  }

  private void createUsers(ProcessEngine processEngine) {

    // create demo users
    new TestDataUtil().createUsers(processEngine);
  }

}
