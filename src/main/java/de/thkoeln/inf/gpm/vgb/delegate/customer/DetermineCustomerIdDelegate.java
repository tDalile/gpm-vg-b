package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Customer;
import de.thkoeln.inf.gpm.vgb.model.external.Insurant;
import de.thkoeln.inf.gpm.vgb.util.DateUtil;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.ParseException;


public class DetermineCustomerIdDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val customerId = processContext.getInternal().getCustomerId();
        val customerPassword = processContext.getInternal().getCustomerPassword();

        val customer = Customer.findById(customerId);

        if (verifyCredentials(customer, customerId, customerPassword)) {
            processContext.getInternal().setIsLoginSuccessful(true);
            processContext.getInternal().setCustomerId(customer.getId());
            processContext.getInternal().setInsurantId(customer.getInsurantId());
            loadInsurantDataToProcessInstance(processContext, customer.getInsurantId());
        }
        else {
            processContext.getInternal().setIsLoginSuccessful(false);
        }
    }

    private boolean verifyCredentials(Customer customer, long id, String password) {
        return customer.getId().equals(id) && customer.getPassword().equals(password);
    }

    private void loadInsurantDataToProcessInstance(ProcessContext processContext, Long insurantId) throws ParseException {
        val insurant = Insurant.findById(insurantId);
        processContext.getInternal().setInsurantName(insurant.getName());
        processContext.getInternal().setInsurantFirstName(insurant.getFirstName());
        processContext.getInternal().setInsurantBirthday(DateUtil.parseDate(insurant.getBirthdate()));
        processContext.getInternal().setInsurantSex(insurant.getSex());
        processContext.getInternal().setInsurantSize(insurant.getSize());
        processContext.getInternal().setInsurantWeight(insurant.getWeight());
        processContext.getInternal().setLocationZip(insurant.getAddress().getLocation().getZipCode());
        processContext.getInternal().setLocationName(insurant.getAddress().getLocation().getName());
        processContext.getInternal().setAddressStreet(insurant.getAddress().getStreet());
        processContext.getInternal().setAddressHousenumber(insurant.getAddress().getHouseNumber());
    }
}
