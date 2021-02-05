package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class CalculateBmiDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val insurant = Insurant.findById(processContext.getInternal().getInsurantId());

        double bmi = calcBmi(insurant.getWeight(), insurant.getSize());

        processContext.getInternal().setClaimBMI(bmi);
    }
    
    // BMI = weight (in kg) divided by size² (in m)
    private double calcBmi(double weight, double size) {
        return Math.round((weight / (1.0 * size * size)) * 100.0) / 100.0; // *1.0 for simple double cast
    }
}
