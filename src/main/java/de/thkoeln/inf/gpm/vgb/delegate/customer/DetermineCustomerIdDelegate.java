package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Customer;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


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
        }
        else {
            processContext.getInternal().setIsLoginSuccessful(false);
        }
    }

    private boolean verifyCredentials(Customer customer, long id, String password) {
        return customer.getId().equals(id) && customer.getPassword().equals(password);
    }
}
