package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Customer;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class DetermineCustomerIdDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables = delegateExecution.getVariables();
        ProcessContext processContext = new ProcessContext(delegateExecution);

        Long customerId = processContext.getInternal().getCustomerId();
        String customerPassword = processContext.getInternal().getCustomerPassword();

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
        // TODO: CHANGE!!
        return true; //customer.getId().equals((Long) id);
    }
}
