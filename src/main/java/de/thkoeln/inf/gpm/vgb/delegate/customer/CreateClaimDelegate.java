package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CreateClaimDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();
        ProcessContext processContext = new ProcessContext(delegateExecution);

        saveClaim(
                processContext.getInternal().getCustomerId(),
                processContext.getInternal().getInsurantId(),
                processContext.getInternal().getIsPremiumClaim(),
                processContext.getInternal().getInsurantBMI()
        );
    }

    private void saveClaim(long customerId, long insurantId, boolean isPremiumApplication, double bmi) {
        // TODO save claim in db
        // TODO set claimId processVariable
    }
}
