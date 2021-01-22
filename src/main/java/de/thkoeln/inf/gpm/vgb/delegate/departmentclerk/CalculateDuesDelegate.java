package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateDuesDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
         ProcessContext processContext = new ProcessContext(delegateExecution);

        // TODO: change names of the variables
        Integer age = processContext.getInternal().getInsurantAge();

        double riskSurcharge;

        try {
            riskSurcharge = processContext.getInternal().getInsurancePolicyRiskSurcharge();
        } catch (NullPointerException e) {
            riskSurcharge = 0;
        }

        // TODO: rename
        Double calc = calculateDue(age, riskSurcharge);

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
