package de.thkoeln.inf.gpm.vgb.listener;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.ProcessVariableConstants;
import de.thkoeln.inf.gpm.vgb.model.external.Customer;
import de.thkoeln.inf.gpm.vgb.util.TestDataUtil;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.db.entitymanager.DbEntityManager;
import org.camunda.bpm.engine.impl.el.StartProcessVariableScope;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Map;


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
