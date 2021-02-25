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

public class CreateNewInsurantDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val location = new Location(
            processContext.getInternal().getLocationZip(),
            processContext.getInternal().getLocationName()
        );

        val address = new Address(
            processContext.getInternal().getAddressStreet(),
            processContext.getInternal().getAddressHousenumber(),
            location
        );

        val customer = Customer.findById(processContext.getInternal().getCustomerId());

        val insurant = new Insurant(
            processContext.getInternal().getInsurantName(),
            processContext.getInternal().getInsurantFirstName(),
            DateUtil.toString(processContext.getInternal().getInsurantBirthdate()),
            processContext.getInternal().getInsurantSex(),
            processContext.getInternal().getInsurantSize(),
            processContext.getInternal().getInsurantWeight(),
            address,
            customer
        );

        val createdInsurant = Insurant.createOrUpdate(insurant);

        processContext.getInternal().setInsurantId(createdInsurant.getId());
    }

}
