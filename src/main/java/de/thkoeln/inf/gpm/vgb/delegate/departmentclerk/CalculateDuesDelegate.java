package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CalculateDuesDelegate implements JavaDelegate {

    long calculateDue(long age, long riskDue){

        long initialDue = 110;
        long multiplier = 10;
        long monthlyDue;

        if(age == 0) monthlyDue = multiplier + riskDue;
        else monthlyDue = multiplier * age + riskDue;

        if (monthlyDue > initialDue) monthlyDue = initialDue + riskDue;

        return monthlyDue;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        long age = (long) processVariables.get("alter");
        long riskDue = 0; // (double) processVariables.get("riskDue");

        long monthlyContribution = calculateDue(age, riskDue);

        processVariables.put("initialContribution", monthlyContribution);
        processVariables.put("monthlyContribution", monthlyContribution);

        delegateExecution.setVariables(processVariables);
    }
}
