package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CalculateBmiDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val insurantId = (Long) processVariables.get("insurantId");
        val insurant = Insurant.findById(insurantId);

        double bmi = calcBmi(insurant.getWeight(), insurant.getSize());

        processContext.getInternal().setClaimBMI(bmi);

    }
    
    // BMI = weight (in kg) divided by size² (in m)
    private double calcBmi(double weight, double size) {
        return weight / (1.0 * size * size); // *1.0 for simple double cast
    }
}
