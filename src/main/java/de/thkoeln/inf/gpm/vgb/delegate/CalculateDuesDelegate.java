package de.thkoeln.inf.gpm.vgb.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class CalculateDuesDelegate implements JavaDelegate {

//    private boolean isPremium;
    private double riskDue = 0.0;
    private int age;

//    CalculateDuesDelegate(int age){
//        this.age = age;
//    }
//
//    public CalculateDuesDelegate(int age, double riskDue){
//        this.age = age;
//        this.riskDue = riskDue;
//    }

    double calculateDue(){

        double initialDue = 110.0;
        double multiplier = 10.0;


        double monthlyDue = multiplier * age + riskDue;

        if (monthlyDue > initialDue) monthlyDue = initialDue + riskDue;

        return monthlyDue;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        // TODO: change names of the variables
        age = (int) processVariables.get("age");
        riskDue = (double) processVariables.get("riskDue");

        processVariables.put("monthlyDue", calculateDue());

        delegateExecution.setVariables(processVariables);
    }
}
