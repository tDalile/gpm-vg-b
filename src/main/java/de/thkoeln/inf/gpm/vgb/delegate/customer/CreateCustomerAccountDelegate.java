package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.Address;
import de.thkoeln.inf.gpm.vgb.model.Customer;
import de.thkoeln.inf.gpm.vgb.model.Insurant;
import de.thkoeln.inf.gpm.vgb.model.Location;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class CreateCustomerAccountDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables;
        processVariables = delegateExecution.getVariables();

        val insurant = saveCustomer(
                (String) processVariables.get("insurantPassword"),
                (String) processVariables.get("insurantName"),
                (String) processVariables.get("insurantFirstName"),
                (Date) processVariables.get("insurantBirthday"),
                (String) processVariables.get("insurantSex"),
                (Double) processVariables.get("insurantSize"),
                (Double) processVariables.get("insurantWeight"),
                (String) processVariables.get("insurantZip"),
                (String) processVariables.get("insurantCity"),
                (String) processVariables.get("insurantStreet"),
                (String) processVariables.get("insurantHouseNumber")
        );

        delegateExecution.setVariable("customerId", insurant.getId());
        delegateExecution.setVariable("insurantId", insurant.getCustomer().getId());
    }

    private Insurant saveCustomer(
            String password,
            String name,
            String firstName,
            Date birthday,
            String sex,
            Double size,
            Double weight,
            String zipCode,
            String city,
            String street,
            String houseNumber
    ) {
        val customer = Customer.createOrUpdate(new Customer(LocalDate.now().toString(), password));
        val location = Location.createOrUpdate(new Location(zipCode, city));
        val address = Address.createOrUpdate(new Address(street, houseNumber, location));

        return Insurant.createOrUpdate(
                new Insurant(
                        name, firstName, birthday.toString(), sex, size, weight, address, customer
                )
        );
    }
}
