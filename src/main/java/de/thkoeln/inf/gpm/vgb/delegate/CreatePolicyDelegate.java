package de.thkoeln.inf.gpm.vgb.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class CreatePolicyDelegate implements JavaDelegate {
    private long policeId;
    private long customerId;
    private String name;
    private String firstname;
    private boolean isPremium;
    private double initialDue;
    private double monthlyDue;
    private double riskDue;
    private String riskDueDescription;
    private Date policeDate;

    // TODO: Precondtions müsste eigentlich ein Array sein oder?
    // private String precondition[];
    private int precondtios;

    private boolean approved = false;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        customerId = (long) processVariables.get("customerId");
        name = (String) processVariables.get("customerName");
        firstname = (String) processVariables.get("customerFirstname");
        isPremium = (boolean) processVariables.get("isPremium");

        // TODO: change the names of the variables
        initialDue = (double) processVariables.get("initialDue");
        monthlyDue = (double) processVariables.get("monthlyDue");
        riskDue = (double) processVariables.get("riskDue");
        riskDueDescription = (String) processVariables.get("riskDueDescription");
        precondtios = (int) processVariables.get("preconditions");

        // TODO: Ist der Vertragsbeginn das Datum der erstellung oder 14 Tage später?
        processVariables.put("creationDate", setDate());

        delegateExecution.setVariables(processVariables);

    }

    private Date setDate(){

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate local = LocalDate.now();
        policeDate = Date.from(local.atStartOfDay(defaultZoneId).toInstant());

        return policeDate;
    }

    // TODO: add new Database Object with type Police
    // TODO: generate ID & fill the intermediate table
}
