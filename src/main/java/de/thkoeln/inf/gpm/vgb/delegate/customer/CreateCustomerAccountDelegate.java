package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Address;
import de.thkoeln.inf.gpm.vgb.model.external.Customer;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import de.thkoeln.inf.gpm.vgb.model.external.Location;
import de.thkoeln.inf.gpm.vgb.util.DateUtil;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.util.Date;

public class CreateCustomerAccountDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val insurant = saveCustomer(
                processContext.getInternal().getCustomerPassword(),
                processContext.getInternal().getInsurantName(),
                processContext.getInternal().getInsurantFirstName(),
                processContext.getInternal().getInsurantBirthdate(),
                processContext.getInternal().getInsurantSex(),
                processContext.getInternal().getInsurantSize(),
                processContext.getInternal().getInsurantWeight(),
                processContext.getInternal().getLocationZip(),
                processContext.getInternal().getLocationName(),
                processContext.getInternal().getAddressStreet(),
                processContext.getInternal().getAddressHousenumber()
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
            Double size,
            Double weight,
            String zipCode,
            String city,
            String street,
            String houseNumber
    ) {
        val customer = Customer.createOrUpdate(new Customer(DateUtil.toString(LocalDate.now()), password));
        val location = Location.createOrUpdate(new Location(zipCode, city));
        val address = Address.createOrUpdate(new Address(street, houseNumber, location));

        return Insurant.createOrUpdate(
                new Insurant(
                        name, firstName, DateUtil.toString(birthday), sex, size, weight, address, customer
                )
        );
    }
}
