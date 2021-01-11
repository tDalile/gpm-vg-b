package de.thkoeln.inf.gpm.vgb.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class CreatePolicyDelegate implements JavaDelegate {
    private long policeId = 10000;
    private long customerId;
    private String name;
    private String firstname;
    private String isPremium;
    private long initialDue;
    private long monthlyDue;
    private long riskDue;
    private String riskDueDescription;
    private Date approvedStartDate; // TODO: überrpfüter Vertagsbeginn
    private Date creatiionDate; // TODO: Datum der Unterschrift (nach 14 Tagen wdiderruf)
    private Date wishedDate; // TODO: gewünschtes Datum vom Kunden (aus Antrag entnehmen)

    // TODO: Precondtions müsste eigentlich ein Array sein oder?
    // private String precondition[];
    private String precondtios;

    private boolean approved = false;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        customerId = (long) processVariables.get("mkundennr");
        name = (String) processVariables.get("mnachname");
        firstname = (String) processVariables.get("mvorname");
        isPremium = (String) processVariables.get("mtarif");

        // TODO: change the names of the variables
        initialDue = 110; // (double) processVariables.get("initialDue");
        monthlyDue = (long) processVariables.get("monthlyDue");
        riskDue = 0; // (long) processVariables.get("riskDue");
        riskDueDescription = ""; // (String) processVariables.get("riskDueDescription");

        // TODO: Datenbank Erkrankungskategorie mappen
        precondtios = (String) processVariables.get("mvorerkrankung");

        // processVariables.put("creationDate", setDate());
        processVariables.put("policeID", policeId);

        delegateExecution.setVariables(processVariables);

    }

    private Date setDate(){

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate local = LocalDate.now();
        approvedStartDate = Date.from(local.atStartOfDay(defaultZoneId).toInstant());

        return approvedStartDate;
    }

    // TODO: add new Database Object with type Police
    // TODO: generate ID & fill the intermediate table
}
