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
        long customerId = 1; // (long) processVariables.get("mkundennr");
        long insurantId = 1; // (long) processVariables.get("mversichertennr");
        long medicalId = 1; // (long) processVariables.get("mvorerkrankungen");

        boolean isPremium = (boolean) processVariables.get("mtarif");
        boolean isNewCustomer = (Boolean) processVariables.get("isNewCustomer");
        boolean isActive = false;

        double initialContribution = 110.0; // (double) processVariables.get("initialDue");
        double monthlyContribution = (double) processVariables.get("monthlyDue");

        long riskSurcharge = 0; // (long) processVariables.get("riskDue");
        String riskSurchargeDescription = ""; // (String) processVariables.get("riskDueDescription");
        Date wishedDate = (Date) processVariables.get("");


        Customer customer = Customer.findById((int) customerId);
        Insurant insurant = Insurant.findById((int) insurantId);
        MedicalHistory medicalHistory = MedicalHistory.findById((int) medicalId);

        InsurancePolicy insurancePolicy =
                new InsurancePolicy(isNewCustomer, 0.0, riskSurchargeDescription, monthlyContribution,
                        initialContribution, wishedDate.toString(), isPremium, customer, medicalHistory);

        processVariables.put("active", false);
        processVariables.put("policeID", insurancePolicy.getId());

        delegateExecution.setVariables(processVariables);

    }

}
