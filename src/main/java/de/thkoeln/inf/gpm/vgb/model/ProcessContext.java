package de.thkoeln.inf.gpm.vgb.model;

import de.thkoeln.inf.gpm.vgb.model.external.ExternalProcessContext;
import de.thkoeln.inf.gpm.vgb.model.internal.InternalProcessContext;
import org.camunda.bpm.engine.delegate.VariableScope;

public class ProcessContext {

    private final InternalProcessContext internalProcessContext;

    private final ExternalProcessContext externalProcessContext;

    public ProcessContext(VariableScope execution) {
        internalProcessContext = new InternalProcessContext(execution);
        externalProcessContext = new ExternalProcessContext(execution);
    }

    public InternalProcessContext getInternal() {
        return internalProcessContext;
    }

    public ExternalProcessContext getExternal() {
        return externalProcessContext;
    }
}
