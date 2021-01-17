package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.InsurancePolicy;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class ActivatePolicyDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        long policeID = (long) processVariables.get("policeID");
        InsurancePolicy insurancePolicy = InsurancePolicy.findById(policeID);

        insurancePolicy.setActive(true);
        InsurancePolicy.createOrUpdate(insurancePolicy);

        processVariables.put("active", true);

        delegateExecution.setVariables(processVariables);
    }
}
