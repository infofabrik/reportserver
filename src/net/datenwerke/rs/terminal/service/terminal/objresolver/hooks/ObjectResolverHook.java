package net.datenwerke.rs.terminal.service.terminal.objresolver.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;

public interface ObjectResolverHook extends Hook {

   boolean consumes(String locationStr, TerminalSession terminalSession);

   Collection<Object> getObjects(String locationStr, TerminalSession terminalSession) throws ObjectResolverException;

}
