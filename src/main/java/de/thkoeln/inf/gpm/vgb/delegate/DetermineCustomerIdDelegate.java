package de.thkoeln.inf.gpm.vgb.delegate;

import de.thkoeln.inf.gpm.vgb.model.util.DbSetup;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class DetermineCustomerIdDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> processVariables = delegateExecution.getVariables();

        long customerId = (long) processVariables.get("customerId");
        String customerPassword = (String) processVariables.get("customerPassword");

        if (verifyCredentials(customerId, customerPassword)) {
            delegateExecution.setVariable("loginIsSuccessful", true);
            delegateExecution.setVariable("customerId", 1L);
            delegateExecution.setVariable("insurantId", 2L);
        }
        else {
            delegateExecution.setVariable("loginIsSuccessful", false);
        }
    }

    private boolean verifyCredentials(long id, String password) {
        // TODO check credentials in db

        DbSetup.INSTANCE.insertDummyData();
        return id == 12345L && password.equals("demo");
    }
}
