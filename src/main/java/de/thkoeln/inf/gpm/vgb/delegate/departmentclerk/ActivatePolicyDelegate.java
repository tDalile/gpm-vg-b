package de.thkoeln.inf.gpm.vgb.delegate.departmentclerk;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class ActivatePolicyDelegate implements JavaDelegate {

    private long policeID;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        ProcessContext processContext = new ProcessContext(delegateExecution);

        processContext.getInternal().getInsurancePolicyId();
        processContext.getInternal().setInsurancePolicyIsActive(true);


    }
}
