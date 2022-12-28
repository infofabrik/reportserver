package net.datenwerke.rs.terminal.service.terminal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.terminal.service.terminal.exceptions.SessionNotFoundException;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;
import net.datenwerke.security.service.security.rights.Right;

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

   public Object getObjectByQuery(Class<? extends VirtualFileSystemManagerHook> vfs, String location)
         throws VFSException;

   /**
    * Fetch a single object by an object resolver query. Permissions are checked.
    * 
    * @param location the object resolver query
    * @return the object found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   public Object getObjectByQuery(String location) throws ObjectResolverException;

   /**
    * Fetch a single object by an object resolver query.
    * 
    * @param location    the object resolver query
    * @param checkRights if permissions should be checked
    * @return the object found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   public Object getObjectByQuery(String location, boolean checkRights) throws ObjectResolverException;

   Object getObjectByQuery(Class<? extends VirtualFileSystemManagerHook> vfsManager, String location,
         boolean checkRights) throws VFSException;

   /**
    * Fetch objects by an object resolver query.
    * 
    * @param location    the object resolver query
    * @param checkRights if permissions should be checked
    * @return the objects found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   public Collection<Object> getObjectsByQuery(String location, boolean checkRights) throws ObjectResolverException;

   /**
    * Fetch objects by an object resolver query. Permissions are checked.
    * 
    * @param location the object resolver query
    * @return the objects found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   public Collection<Object> getObjectsByQuery(String location) throws ObjectResolverException;

   TerminalSession getUnscopedTerminalSession();

   public <T> T getSingleObjectOfTypeByQuery(Class<T> type, String query, TerminalSession session,
         Class<? extends Right>... rights) throws ObjectResolverException;

   public CommandResult convertResultSetToCommandResult(ResultSet rs) throws SQLException;
   
   CommandResult convertSimpleTableToCommandResult(String title, String emptyTableMessage, Map<String, Object> table);
   
   String sortAndJoin(List<String> list);
   
}
