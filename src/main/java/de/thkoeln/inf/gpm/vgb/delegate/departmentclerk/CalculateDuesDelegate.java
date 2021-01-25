package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateDuesDelegate implements JavaDelegate {

    private static final long MIN_CONTRIBUTION = 110;
    private static final long CONTRIBUTION_MULTIPLIER = 10;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        double riskSurcharge;

        try {
            riskSurcharge = processContext.getInternal().getInsurancePolicyRiskSurcharge();
        } catch (NullPointerException e) {
            riskSurcharge = 0;
        }

        Integer age = processContext.getInternal().getInsurantAge();
        Double contribution = calculateContribution(age, riskSurcharge);

        processContext.getInternal().setInsurancePolicyMonthlyContribution(contribution);
        processContext.getInternal().setInsurancePolicyInitialContribution(contribution);
    }

    double calculateContribution(Integer age, Double riskSurcharge) {
        if (age == 0) age = 1;

        long monthlyContribution = CONTRIBUTION_MULTIPLIER * age;

        if (monthlyContribution < MIN_CONTRIBUTION) monthlyContribution = MIN_CONTRIBUTION;

        return monthlyContribution + riskSurcharge.intValue();
    }
}
