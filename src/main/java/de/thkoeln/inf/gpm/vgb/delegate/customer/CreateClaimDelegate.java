package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Claim;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import de.thkoeln.inf.gpm.vgb.util.DateUtil;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreateClaimDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val insurant = Insurant.findById(processContext.getInternal().getInsurantId());
        val age = DateUtil.calcAge(insurant.getBirthdate());

        val claimId = saveClaim(
                insurant,
                processContext.getInternal().getIsPremiumClaim(),
                processContext.getInternal().getClaimBMI()
        );

        processContext.getInternal().setInsurantAge(age);
        processContext.getInternal().setClaimId(claimId);
    }

    /**
     * @return claimId
     */
    private long saveClaim(Insurant insurant, boolean isPremiumApplication, double bmi) {
        val claim = Claim.createOrUpdate(
                new Claim(DateUtil.nowAsString(),
                        "",
                        bmi,
                        null,
                        null,
                        null,
                        null,
                        null,
                        insurant,
                        null,
                        null)
        );
        return claim.getId();
    }
}
