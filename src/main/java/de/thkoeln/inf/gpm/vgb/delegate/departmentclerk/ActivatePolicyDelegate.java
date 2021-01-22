package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.InsurancePolicy;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class ActivatePolicyDelegate implements JavaDelegate {

    private long policeID;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        InsurancePolicy insurancePolicy = InsurancePolicy.findById(processContext.getInternal().getInsurancePolicyId());

        insurancePolicy.setActive(false);
        InsurancePolicy.createOrUpdate(insurancePolicy);

        processContext.getInternal().setInsurancePolicyIsActive(true);
    }
}
