package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.Customer;
import de.thkoeln.inf.gpm.vgb.model.InsurancePolicy;
import de.thkoeln.inf.gpm.vgb.model.Insurant;
import de.thkoeln.inf.gpm.vgb.model.MedicalHistory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;
import java.util.Map;

public class CreatePolicyDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        // TODO: change the names of the variables
        long insurantId = 1; // (long) processVariables.get("mversichertennr");
        long medicalId = 1; // (long) processVariables.get("mvorerkrankungen");

        boolean isPremium = false; //(boolean) processVariables.get("mtarif");
        boolean isNewCustomer = (boolean) processVariables.get("isNewCustomer");
        boolean isActive = false;

        double initialContribution = (double) processVariables.get("initialContribution");
        double monthlyContribution = (double) processVariables.get("monthlyContribution");

        double riskSurcharge = 0.0;// (double) processVariables.get("riskDue");
        String riskSurchargeDescription = ""; // (String) processVariables.get("riskDueDescription");
        Date wishedDate = (Date) processVariables.get("");

        Insurant insurant = Insurant.findById(insurantId);
        MedicalHistory medicalHistory = MedicalHistory.findById(medicalId);

        InsurancePolicy insurancePolicy =
                new InsurancePolicy(isNewCustomer, 0.0, riskSurchargeDescription, monthlyContribution,
                        initialContribution, wishedDate.toString(), isPremium, isActive, insurant, medicalHistory);
        insurancePolicy = InsurancePolicy.createOrUpdate(insurancePolicy);

        processVariables.put("active", isActive);
        processVariables.put("policeID", insurancePolicy.getId());

        delegateExecution.setVariables(processVariables);

    }
}
