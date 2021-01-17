package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.Customer;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class DetermineCustomerIdDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables = delegateExecution.getVariables();

        long customerId = (long) processVariables.get("customerId");
        String customerPassword = (String) processVariables.get("customerPassword");

        val customer = Customer.findById((int) customerId);
        if (verifyCredentials(customer, customerId, customerPassword)) {
            delegateExecution.setVariable("loginIsSuccessful", true);
            delegateExecution.setVariable("customerId", (long) customer.getId());
            delegateExecution.setVariable("insurantId", (long) customer.getInsurantId());
        }
        else {
            delegateExecution.setVariable("loginIsSuccessful", false);
        }
    }

    private boolean verifyCredentials(Customer customer, long id, String password) {
        return customer.getId().equals((int) id);
    }
}
