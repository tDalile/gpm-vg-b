package de.thkoeln.inf.gpm.vgb.listener;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import java.util.Random;


public class ProcessStartEventListener implements ExecutionListener{

    // needs Definition of Listener in xml of process!
    // TODO: https://github.com/camunda/camunda-docs-manual/blob/master/content/user-guide/process-engine/delegation-code.md#execution-listener
    @Override
    public void notify(DelegateExecution execution) {


        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

        ProcessContext context = new ProcessContext(execution);


        /*
        // TODO: resolve Connection refused
        // Deploy Schema at first
        String[] arguments = new String[] {""};
        DeploySchema.main(arguments);
        */

        // TODO: set BusinessKey
        //TODO: better identifier
        Random random = new Random();
        execution.setProcessBusinessKey(String.valueOf(random.nextInt(100000)));

    }



}
