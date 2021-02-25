package de.thkoeln.inf.gpm.vgb.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import java.util.Random;

public class ProcessStartEventListener implements ExecutionListener {

    // TODO: needs Definition of Listener in xml of process!
    // https://github.com/camunda/camunda-docs-manual/blob/master/content/user-guide/process-engine/delegation-code.md#execution-listener
    @Override
    public void notify(DelegateExecution execution) {

        // TODO: resolve Connection refused
        /*
         Deploy Schema at first
         String[] arguments = new String[] {""};
         DeploySchema.main(arguments);
        */

        // TODO: set BusinessKey
        // TODO: better identifier
        Random random = new Random();
        execution.setProcessBusinessKey(String.valueOf(random.nextInt(100000)));

    }


}
