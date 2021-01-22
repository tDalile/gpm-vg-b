package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.InsurancePolicy;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import de.thkoeln.inf.gpm.vgb.model.external.MedicalHistory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreatePolicyDelegate implements JavaDelegate {
    //private Date approvedStartDate; TODO: überpfrüter Vertagsbeginn
    //private Date creatiionDate; TODO: Datum der Unterschrift (nach 14 Tagen widerruf)
    //private Date wishedDate; TODO: gewünschtes Datum vom Kunden (aus Antrag entnehmen)

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        ProcessContext processContext = new ProcessContext(delegateExecution);

        Insurant insurant = Insurant.findById(processContext.getInternal().getInsurantId());
        MedicalHistory medicalHistory = MedicalHistory.findById(processContext.getInternal().getMedicalHistoryId());

        InsurancePolicy insurePolicy = savePolicy(
                processContext.getInternal().getIsNewCustomer(),
                processContext.getInternal().getInsurancePolicyRiskSurcharge(),
                processContext.getInternal().getInsurancePolicyRiskSurchargeReason(),
                processContext.getInternal().getInsurancePolicyMonthlyContribution(),
                processContext.getInternal().getInsurancePolicyInitialContribution(),
                processContext.getInternal().getClaimDesiredStartDate(),
                processContext.getInternal().getIsPremiumClaim(),
                processContext.getInternal().getInsurancePolicySignDateOfContract(),
                processContext.getInternal().getInsurancePolicyIsActive(),
                insurant,
                medicalHistory
        );

        processContext.getInternal().setInsurancePolicyId(insurePolicy.getId());
    }
/*
    private Date setDate() {

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate local = LocalDate.now();
        approvedStartDate = Date.from(local.atStartOfDay(defaultZoneId).toInstant());

        return approvedStartDate;
    }
*/
    private InsurancePolicy savePolicy(
            Boolean isNewCustomer,
            Double riskSurcharge,
            String riskSurchargeReason,
            Double monthlyContribution,
            Double initialContribution,
            String startOfContract,
            Boolean isPremiumTarif,
            String signDate,
            Boolean isActive,
            Insurant insurant,
            MedicalHistory medicalHistory
    ) {
        return InsurancePolicy.createOrUpdate(
                new InsurancePolicy(
                        isNewCustomer,
                        riskSurcharge,
                        riskSurchargeReason,
                        monthlyContribution,
                        initialContribution,
                        startOfContract,
                        isPremiumTarif,
                        signDate,
                        isActive,
                        insurant,
                        medicalHistory)
        );
    }


}
