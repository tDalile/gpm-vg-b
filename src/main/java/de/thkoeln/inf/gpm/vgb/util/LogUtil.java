package de.thkoeln.inf.gpm.vgb.util;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
public class LogUtil {
    public static void logExecution(Logger logger, DelegateExecution execution) {
        logger.debug(
                "Delegate invoked by processDefinitionId={}, processInstanceId={}, activityId={}, activityName='{}', businessKey={}, executionId={}",
                execution.getProcessDefinitionId(),
                execution.getProcessInstanceId(),
                execution.getCurrentActivityId(),
                execution.getCurrentActivityName(),
                execution.getProcessBusinessKey(),
                execution.getId());
    }
}
