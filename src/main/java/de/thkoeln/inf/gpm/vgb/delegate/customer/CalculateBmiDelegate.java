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

    /*
    //basic formula: weight/height(m)²
    Double bmi = gewicht / Math.pow(groesseMeter,2.0);
    //round to 2 digits
    bmi = Math.round(bmi * 100.0)/100.0;
    */

    /*
    private Integer calcAge(Date geburtstag) {
        //java.util.Date --> java.time.LocalDateTime
        LocalDateTime gLocDT = geburtstag.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        //java.time.LocalDateTime --> LocalDate
        LocalDate gLocD = gLocDT.toLocalDate();

        int alter;
        return alter = Period.between(gLocD, LocalDate.now()).getYears();
    }
    */
}
