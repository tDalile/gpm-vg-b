package de.thkoeln.inf.gpm.vgb.listener;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;


public class ProcessStartEventListener implements ExecutionListener{

    /*
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    */

    // needs Definition of Listener in xml of process!
    @Override
    public void notify(DelegateExecution execution) {


        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

        ProcessContext context = new ProcessContext(execution);

        //Customer customer = TestDataUtil.createCustomer();
        //context.getExternal().setCustomer(customer);

        //runtimeService.setVariable(execution.getId(), "ext_customer", customer);


        /*
        session.save(customer);
        transaction.commit();
        session.clear();
        factory.close();
        */
    }


}
