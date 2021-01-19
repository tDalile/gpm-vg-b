package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import de.thkoeln.inf.gpm.vgb.util.DateUtil;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreateClaimDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        saveClaim(
                processContext.getInternal().getCustomerId(),
                processContext.getInternal().getInsurantId(),
                processContext.getInternal().getIsPremiumClaim(),
                processContext.getInternal().getClaimBMI()
        );

        val insurant = Insurant.findById(processContext.getInternal().getInsurantId());
        val age = DateUtil.calcAge(insurant.getBirthdate());

        processContext.getInternal().setInsurantAge(age);
    }

    private void saveClaim(long customerId, long insurantId, boolean isPremiumApplication, double bmi) {
        // TODO save claim in db
        // TODO set claimId processVariable
    }
}
