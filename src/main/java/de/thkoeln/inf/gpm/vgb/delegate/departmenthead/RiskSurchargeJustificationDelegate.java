package de.thkoeln.inf.gpm.vgb.delegate.departmenthead;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RiskSurchargeJustificationDelegate implements JavaDelegate {
  Logger log = LoggerFactory.getLogger("AttachRiskSurchargeDelegate");

  public void execute(DelegateExecution execution) throws Exception {
    log.info("Risikozuschlagsbegründung hinzugefügt");
  }
}
