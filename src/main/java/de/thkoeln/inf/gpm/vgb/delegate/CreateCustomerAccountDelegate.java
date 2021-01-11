package de.thkoeln.inf.gpm.vgb.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;
import java.util.Map;

public class CreateCustomerAccountDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        saveCustomer(
                (String) processVariables.get("insurantPassword"),
                (String) processVariables.get("insurantName"),
                (String) processVariables.get("insurantFirstName"),
                (Date) processVariables.get("insurantBirthday"),
                (String) processVariables.get("insurantSex"),
                (Long) processVariables.get("insurantSize"),
                (Long) processVariables.get("insurantWeight"),
                (String) processVariables.get("insurantZip"),
                (String) processVariables.get("insurantCity"),
                (String) processVariables.get("insurantStreet"),
                (String) processVariables.get("insurantHouseNumber")
        );

        // TODO get ids from db
        delegateExecution.setVariable("customerId", 1L);
        delegateExecution.setVariable("insurantId", 2L);
    }

    private void saveCustomer(
            String password,
            String name,
            String firstName,
            Date birthday,
            String sex,
            Long size,
            Long weight,
            String zipCode,
            String city,
            String street,
            String houseNumber
    ) {
        // TODO save customer in db
        // TODO add custom db exceptions
        // TODO set insurantId processVariable
    }
}
