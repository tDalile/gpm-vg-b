package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Claim;
import de.thkoeln.inf.gpm.vgb.model.external.Disease;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import de.thkoeln.inf.gpm.vgb.util.DateUtil;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;

public class CreateClaimDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val insurant = Insurant.findById(processContext.getInternal().getInsurantId());
        val age = DateUtil.calcAge(insurant.getBirthdate());

        val claimId = saveClaim(
                insurant,
                processContext.getInternal().getIsPremiumClaim(),
                processContext.getInternal().getClaimBMI(),
                processContext.getInternal().getClaimDesiredStartDate()
        );

        processContext.getInternal().setInsurantAge(age);
        processContext.getInternal().setClaimId(claimId);

        // get all available diseases
        val diseases = Disease.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Disease d: diseases) stringBuilder.append(d.getDescription()).append(";");

        processContext.getInternal().setInsurantBirthday(DateUtil.parseDate(insurant.getBirthdate()));
        processContext.getInternal().setCustomerEntry(insurant.getCustomer().getEntry());
        processContext.getInternal().setDiseaseList(stringBuilder.toString());
    }

    /**
     * @return claimId
     */
    private long saveClaim(Insurant insurant, boolean isPremiumApplication, double bmi, Date desiredStartDate) {
        val claim = Claim.createOrUpdate(
                new Claim(DateUtil.nowAsString(),
                        DateUtil.toString(desiredStartDate),
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
