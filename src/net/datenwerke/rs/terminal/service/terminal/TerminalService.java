package net.datenwerke.rs.terminal.service.terminal;

import java.util.Collection;

import net.datenwerke.rs.terminal.service.terminal.exceptions.SessionNotFoundException;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public interface TerminalService {

   /**
    * Initializes a new {@link TerminalSession}.
    * 
    * @return The newly initialized {@link TerminalSession}
    */
   public TerminalSession initTerminalSession();

   /**
    * Returns the {@link TerminalSession} identified by the given <i>sessionId</i>
    * 
    * @param sessionId The ID of the desired {@link TerminalSession}
    * @return The {@link TerminalSession}
    * @throws SessionNotFoundException
    */
   public TerminalSession getTerminalSession(String sessionId) throws SessionNotFoundException;

   /**
    * Closes the {@link TerminalSession} identified by the given <i>sessionId</i>
    * 
    * @param sessionId The ID of the {@link TerminalSession}
    */
   public void closeTerminalSession(String sessionId);

   public Object getObjectByLocation(Class<? extends VirtualFileSystemManagerHook> vfs, String location)
         throws VFSException;

   public Object getObjectByLocation(String location) throws ObjectResolverException;

   public Object getObjectByLocation(String location, boolean checkRights) throws ObjectResolverException;

   Object getObjectByLocation(Class<? extends VirtualFileSystemManagerHook> vfsManager, String location,
         boolean checkRights) throws VFSException;

   public Collection<Object> getObjectsByLocation(String location, boolean checkRights) throws ObjectResolverException;

   public Collection<Object> getObjectsByLocation(String location) throws ObjectResolverException;

   TerminalSession getUnscopedTerminalSession();

}
