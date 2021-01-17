/**
 *  Accessor-Pattern: Class uses current VariableScope of Process-Instance
 *  Enables type-safe access to variables
 *  Reference: https://blog.viadee.de/umgang-mit-variablen-in-camunda-prozessen
 */
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
