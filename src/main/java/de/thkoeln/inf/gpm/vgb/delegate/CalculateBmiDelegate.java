package de.thkoeln.inf.gpm.vgb.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CalculateBmiDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        double bmi = calcBmi(
                (long) processVariables.get("insurantWeight"),
                (long) processVariables.get("insurantSize")
        );

        delegateExecution.setVariable("insurantBmi", bmi);
    }

    // BMI = weight (in kg) divided by size² (in m)
    private double calcBmi(long weight, long size) {
        return weight / (1.0 * size * size); // *1.0 for simple double cast
    }
}
