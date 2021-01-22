package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.InsurancePolicy;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.hibernate.query.criteria.internal.predicate.InPredicate;

import java.util.Map;

public class DeactivatePolicyDelegate implements JavaDelegate {

    private long policeID;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        InsurancePolicy insurancePolicy = InsurancePolicy.findById(processContext.getInternal().getInsurancePolicyId());

        insurancePolicy.setActive(false);
        InsurancePolicy.createOrUpdate(insurancePolicy);

        processContext.getInternal().setInsurancePolicyIsActive(false);

    }
}
