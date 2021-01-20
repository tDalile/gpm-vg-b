package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CalculateDuesDelegate implements JavaDelegate {




    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        ProcessContext processContext = new ProcessContext(delegateExecution);

        // TODO: change names of the variables
        Integer age = processContext.getInternal().getInsurantAge();

        double riskDue = 0;

        try {
            riskDue = processContext.getInternal().getInsurancePolicyRiskSurcharge();
        } catch (NullPointerException e) {
            // No risk surchage attached
        }

        // TODO: rename
        Double calc = calculateDue(age, riskDue);

        processContext.getInternal().setInsurancePolicyMonthlyContribution(calc);
        processContext.getInternal().setInsurancePolicyInitialContribution(calc);
    }

    double calculateDue(Integer age, Double riskDue){
        long initialDue = 110;
        long multiplier = 10;
        long monthlyDue;

        // TODO: CHANGE emergency solution
        if(age == 0) monthlyDue = multiplier + riskDue.intValue();
        else monthlyDue = multiplier * age + riskDue.intValue();

        if (monthlyDue > initialDue) monthlyDue = initialDue + riskDue.intValue();

        return monthlyDue;
    }




}
