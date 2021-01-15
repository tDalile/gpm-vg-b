package de.thkoeln.inf.gpm.vgb.delegate.customer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CreateClaimDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        saveClaim(
                (long) processVariables.get("customerId"),
                (long) processVariables.get("insurantId"),
                (boolean) processVariables.get("isPremiumClaim"),
                (double) processVariables.get("insurantBmi")
        );
    }

    private void saveClaim(long customerId, long insurantId, boolean isPremiumApplication, double bmi) {
        // TODO save claim in db
        // TODO set claimId processVariable
    }
}
