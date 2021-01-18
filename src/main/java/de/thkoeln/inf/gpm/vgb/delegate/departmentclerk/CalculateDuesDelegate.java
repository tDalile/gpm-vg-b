package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CalculateDuesDelegate implements JavaDelegate {

    long calculateDue(long age, long riskDue){



    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        ProcessContext processContext = new ProcessContext(delegateExecution);

        // TODO: change names of the variables
        age = processContext.getInternal().getInsurantAge();
        riskDue = 0.0; // (double) processVariables.get("riskDue");

        // TODO: rename
        Double calc = calculateDue();

        processContext.getInternal().setInsurancePolicyMonthlyContribution(calc);
        processContext.getInternal().setInsurancePolicyInitialContribution(calc);
    }

    double calculateDue(){
        long initialDue = 110;
        long multiplier = 10;
        long monthlyDue;

        if(age == 0) monthlyDue = multiplier + riskDue;
        else monthlyDue = multiplier * age + riskDue;

        if (monthlyDue > initialDue) monthlyDue = initialDue + riskDue;

        return monthlyDue;
    }




}
