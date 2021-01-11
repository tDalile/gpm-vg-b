package de.thkoeln.inf.gpm.vgb.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DummyScriptTaskDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> processVariables = new HashMap();

        Date geburtstag = (Date) execution.getVariable("mgeburtsdatum");


        Double groesseMeter = (Double)execution.getVariable("mgroesse") / 100;
        Double gewicht = (Double)execution.getVariable("mgewicht");

        //basic formula: weight/height(m)²
        Double bmi = gewicht / Math.pow(groesseMeter,2.0);
        //round to 2 digits
        bmi = Math.round(bmi * 100.0)/100.0;


        /**
        * TODO: Compute each variable
        * Integer bmi
        **/

        processVariables.put("alter", calcAge(geburtstag));
        processVariables.put("bmi", bmi);


        execution.setVariables(processVariables);

    }

    private Integer calcAge(Date geburtstag) {
        //java.util.Date --> java.time.LocalDateTime
        LocalDateTime gLocDT = geburtstag.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        //java.time.LocalDateTime --> LocalDate
        LocalDate gLocD = gLocDT.toLocalDate();

        int alter;
        return alter = Period.between(gLocD, LocalDate.now()).getYears();
    }
}
