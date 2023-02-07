package net.datenwerke.rs.terminal.service.terminal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
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
   TerminalSession initTerminalSession();

   /**
    * Returns the {@link TerminalSession} identified by the given <i>sessionId</i>
    * 
    * @param sessionId The ID of the desired {@link TerminalSession}
    * @return The {@link TerminalSession}
    * @throws SessionNotFoundException
    */
   TerminalSession getTerminalSession(String sessionId) throws SessionNotFoundException;

   /**
    * Closes the {@link TerminalSession} identified by the given <i>sessionId</i>
    * 
    * @param sessionId The ID of the {@link TerminalSession}
    */
   void closeTerminalSession(String sessionId);

   Object getObjectByQuery(Class<? extends VirtualFileSystemManagerHook> vfs, String location) throws VFSException;

   /**
    * Fetches a single object by an object resolver query. Permissions are checked.
    * 
    * @param location the object resolver query
    * @return the object found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   Object getObjectByQuery(String location) throws ObjectResolverException;

   /**
    * Fetches a single object by an object resolver query.
    * 
    * @param location    the object resolver query
    * @param checkRights if permissions should be checked
    * @return the object found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   Object getObjectByQuery(String location, boolean checkRights) throws ObjectResolverException;

   Object getObjectByQuery(Class<? extends VirtualFileSystemManagerHook> vfsManager, String location,
         boolean checkRights) throws VFSException;

   /**
    * Fetches objects by an object resolver query.
    * 
    * @param location    the object resolver query
    * @param checkRights if permissions should be checked
    * @return the objects found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   Collection<Object> getObjectsByQuery(String location, boolean checkRights) throws ObjectResolverException;

   /**
    * Fetch objects by an object resolver query. Permissions are checked.
    * 
    * @param location the object resolver query
    * @return the objects found
    * @throws ObjectResolverException if the query is not valid or something
    *                                 happens during query execution
    */
   Collection<Object> getObjectsByQuery(String location) throws ObjectResolverException;

   TerminalSession getUnscopedTerminalSession();

   <T> T getSingleObjectOfTypeByQuery(Class<T> type, String query, TerminalSession session,
         Class<? extends Right>... rights) throws ObjectResolverException;

   CommandResult convertResultSetToCommandResult(ResultSet rs) throws SQLException;

   /**
    * Creates a {@link CommandResult} that contains a table created from the given
    * map. The table contains the map keys as the first table column and their
    * corresponding values as the second table column.
    * 
    * @param headlines         headlines of the {@link CommandResult}. Each
    *                          headline is printed in a new row. Note that these
    *                          are NOT the table headers.
    * @param emptyTableMessage message to show if the map is empty.
    * @param map               the map. Its keys map to the first table column of
    *                          the result, their corresponding values to its second
    *                          table column.
    * @return a {@link CommandResult} containing the given map as a table.
    */
   CommandResult convertSimpleMapToCommandResult(List<String> headlines, String emptyTableMessage,
         Map<String, Object> map);
   
   RSTableModel convertSimpleMapToTableModel(Map<String, Object> map);
   
   RSTableModel convertSimpleListToTableModel(String headline, List<String> list);

   /**
    * Creates a {@link CommandResult} that contains a table created from the given
    * list of maps. The map keys map to the table headers, their corresponding
    * values map to the table values. All maps must have the same size. The order
    * of the headers is not defined. If the firstHeaders attribute is given, the
    * table headers contain the given firstHeaders in first place and their order
    * is preserved. All firstHeaders must be contained in all maps.
    * 
    * @param headlines         headlines of the {@link CommandResult}. Each
    *                          headline is printed in a new row. Note that these
    *                          are NOT the table headers.
    * @param emptyTableMessage message to shwo if the list of maps is empty.
    * @param mapList           the list of maps.
    * @param firstKeys         the ordered first map keys to appear in the table
    * @param keyToDisplayText  maps the map keys to a display text
    * @return a {@link CommandResult} containing the given list of maps as a table.
    */
   CommandResult convertSimpleMapListToCommandResult(List<String> headlines, String emptyTableMessage,
         List<Map<String, String>> mapList, List<String> firstKeys, Map<String, String> keyToText);

   /**
    * Joins the list with ", ". If sort is true, sorts the given list
    * alphabetically
    * 
    * @param list the list
    * @param sort if false, just joins without sorting
    * @return the converted string
    */
   String join(List<String> list, boolean sort);

}
