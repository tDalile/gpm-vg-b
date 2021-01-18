package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
                processContext.getInternal().getClaimBMI()
        );

        processContext.getInternal().setInsurantAge(calcAge(processContext.getInternal().getInsurantBirthdate()));

    }

    private void saveClaim(long customerId, long insurantId, boolean isPremiumApplication, double bmi) {
        // TODO save claim in db
        // TODO set claimId processVariable
    }

    private Integer calcAge(Date geburtstag) {
        //java.util.Date --> java.time.LocalDateTime
        LocalDateTime gLocDT = geburtstag.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        //java.time.LocalDateTime --> LocalDate
        LocalDate gLocD = gLocDT.toLocalDate();

        int alter;
        return alter = Period.between(gLocD, LocalDate.now()).getYears();
    }
}
