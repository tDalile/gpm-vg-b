package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Address;
import de.thkoeln.inf.gpm.vgb.model.external.Customer;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import de.thkoeln.inf.gpm.vgb.model.external.Location;
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

        ProcessContext processContext = new ProcessContext(delegateExecution);

        val insurant = saveCustomer(
                processContext.getInternal().getInsurantPassword(),
                processContext.getInternal().getInsurantName(),
                processContext.getInternal().getInsurantFirstName(),
                processContext.getInternal().getInsurantBirthday(),
                processContext.getInternal().getInsurantSex(),
                processContext.getInternal().getInsurantSize(),
                processContext.getInternal().getInsurantWeight(),
                processContext.getInternal().getInsurantZip(),
                processContext.getInternal().getInsurantCity(),
                processContext.getInternal().getInsurantStreet(),
                processContext.getInternal().getInsurantHousenumber()
        );

        processContext.getInternal().setCustomerId(insurant.getId());
        processContext.getInternal().setInsurantId(insurant.getCustomer().getId());

    }

    private Insurant saveCustomer(
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
        val customer = Customer.createOrUpdate(new Customer(LocalDate.now().toString()));
        val location = Location.createOrUpdate(new Location(zipCode, city));
        val address = Address.createOrUpdate(new Address(street, houseNumber, location));

        return Insurant.createOrUpdate(
                new Insurant(
                        name, firstName, birthday.toString(), sex.charAt(0), size, weight, address, customer
                )
        );
    }
}
