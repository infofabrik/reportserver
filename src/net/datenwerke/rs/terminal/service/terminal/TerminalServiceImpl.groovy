package net.datenwerke.rs.terminal.service.terminal

import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.util.ArrayList
import java.util.Arrays
import java.util.Collection
import java.util.List
import java.util.Map

import org.hibernate.proxy.HibernateProxy

import com.google.inject.Inject
import com.google.inject.Provider

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition
import net.datenwerke.rs.terminal.service.terminal.exceptions.SessionNotFoundException
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook
import net.datenwerke.security.service.security.rights.Right

public class TerminalServiceImpl implements TerminalService {

   private final Provider<TerminalSession> terminalSessionProvider
   private final Provider<TerminalSessionMap> terminalSessionMap

   @Inject
   public TerminalServiceImpl(
      Provider<TerminalSession> terminalSessionProvider,
      Provider<TerminalSessionMap> terminalSessionMap
      ) {

      this.terminalSessionProvider = terminalSessionProvider
      this.terminalSessionMap = terminalSessionMap
   }

   @Override
   public TerminalSession getUnscopedTerminalSession() {
      TerminalSession session = terminalSessionProvider.get()
      return session
   }

   @Override
   public TerminalSession initTerminalSession() {
      TerminalSession session = terminalSessionProvider.get()
      terminalSessionMap.get().put(session.sessionId, session)
      return session
   }

   @Override
   public TerminalSession getTerminalSession(String sessionId) throws SessionNotFoundException {
      if (!terminalSessionMap.get().containsKey(sessionId))
         throw new SessionNotFoundException()
      return terminalSessionMap.get().get(sessionId)
   }

   @Override
   public void closeTerminalSession(String sessionId) {
      terminalSessionMap.get().remove(sessionId)
   }

   @Override
   public Object getObjectByQuery(Class<? extends VirtualFileSystemManagerHook> vfsManager, String location)
         throws VFSException {
      return getObjectByQuery(vfsManager, location, true)
   }

   @Override
   public Object getObjectByQuery(Class<? extends VirtualFileSystemManagerHook> vfsManager, String location,
         boolean checkRights) throws VFSException {
      TerminalSession session = terminalSessionProvider.get()

      if (!checkRights)
         session.setCheckRights(checkRights)

      VirtualFileSystemDeamon vfs = session.fileSystem
      VFSLocation root = vfs.getFileSystemRoot(vfsManager)
      try {
         VFSLocation objLocation = vfs.getLocation(root, location)

         if (null != objLocation)
            return objLocation.object
      } catch (VFSException e) {
      }

      return null;
   }

   @Override
   public Collection<Object> getObjectsByQuery(String location) throws ObjectResolverException {
      return getObjectsByQuery(location, true)
   }

   @Override
   public Collection<Object> getObjectsByQuery(String location, boolean checkRights) throws ObjectResolverException {
      TerminalSession session = terminalSessionProvider.get()
      if (!checkRights)
         session.checkRights = checkRights

      return session.objectResolver.getObjects(location)
   }

   @Override
   public Object getObjectByQuery(String location) throws ObjectResolverException {
      return getObjectByQuery(location, true)
   }

   @Override
   public Object getObjectByQuery(String location, boolean checkRights) throws ObjectResolverException {
      Collection<Object> objects = getObjectsByQuery(location, checkRights)
      if (null != objects && objects.size() > 0) {
         Object obj = objects.iterator().next()
         if (obj instanceof HibernateProxy)
            obj = obj.hibernateLazyInitializer.implementation
         return obj
      }
      return null
   }

   @Override
   public <T> T getSingleObjectOfTypeByQuery(Class<T> type, String query, TerminalSession session,
         Class<? extends Right>... rights) throws ObjectResolverException {
      Collection<Object> resolvedDatasource = session.objectResolver.getObjects(query, rights)
      if (1 != resolvedDatasource.size())
         throw new IllegalArgumentException("Query must be resolved to exactly one object. Query: '$query'")
      Object asObject = resolvedDatasource.iterator().next()
      if (!(type.isInstance(asObject)))
         throw new IllegalArgumentException(
               "Incorrect type found. Required type: '${type.simpleName}'. Query: '$query'")
      return (T) asObject
   }

   @Override
   public CommandResult convertResultSetToCommandResult(ResultSet rs) throws SQLException {
      ResultSetMetaData metaData = rs.metaData
      int columnCount = metaData.columnCount
      def columnNames = []
      def types = []
      for (int i = 0; i < columnCount; i++) {
         columnNames << metaData.getColumnName(i + 1)
         types << String
      }
      TableDefinition td = new TableDefinition(columnNames, types)
      RSTableModel table = new RSTableModel(td)
      while (rs.next()) {
         def values = []
         for (int i = 0; i < columnCount; i++) {
            try {
               values << rs.getObject(i + 1).toString()
            } catch (Exception e) {
               values << 'null'
            }
         }
         table.addDataRow(new RSStringTableRow(values))
      }
      return new CommandResult(table)
   }

   @Override
   public CommandResult convertSimpleTableToCommandResult(String title, String emptyTableMessage,
         Map<String, Object> table) {
      CommandResult result = new CommandResult()
      result.addResultLine title
      if (!table.size()) {
         result.addResultLine(emptyTableMessage)
         return result
      }
      
      RSTableModel model = new RSTableModel()
      TableDefinition tableDef = new TableDefinition(['Key', 'Value'], [String, String])
      tableDef.displaySizes = [180, 0]
      model.tableDefinition = tableDef
      result.addResultTable model
      table.each{key, val -> model.addDataRow(
         new RSStringTableRow(key, val instanceof List? sortAndJoin(val): val as String))}

      return result
   }
   
   @Override 
   public String sortAndJoin(List<String> list) {
      return list?.sort(false, {(it as String).toLowerCase(Locale.ROOT)})?.join(', ')
   }

}
