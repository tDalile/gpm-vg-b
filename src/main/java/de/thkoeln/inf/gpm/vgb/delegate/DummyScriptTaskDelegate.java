package de.thkoeln.inf.gpm.vgb.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class DummyScriptTaskDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> processVariables = new HashMap();


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Integer alter = Period.between(LocalDate.now(), (LocalDate) ((execution.getVariable("mgeburtsdatum")))).getYears();

        /**
        * TODO: Compute each variable
        * Integer bmi
        **/

        processVariables.put("alter", alter);


        execution.setVariables(processVariables);

    }
}
