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
   public CommandResult convertSimpleMapToCommandResult(List<String> headlines, String emptyTableMessage,
         Map<String, Object> map) {
      CommandResult result = new CommandResult()
      headlines.each { result.addResultLine it }
      if (!map.size()) {
         result.addResultLine(emptyTableMessage)
         return result
      }
      
      def model = convertSimpleMapToTableModel(map)
      result.addResultTable model
      
      return result
   }
   
   @Override
   public RSTableModel convertSimpleMapToTableModel(Map<String, Object> map) {
      RSTableModel model = new RSTableModel()
      TableDefinition tableDef = new TableDefinition(['Key', 'Value'], [String, String])
      tableDef.displaySizes = [180, 0]
      model.tableDefinition = tableDef
      
      map.each{key, val -> model.addDataRow(
         new RSStringTableRow(key, val instanceof List? join(val, true): val as String))}
      return model
   }
   
   @Override 
   public String join(List<String> list, boolean sort) {
      return  (sort? list?.toSorted{(it as String).toLowerCase(Locale.ROOT)} : list)?.join(', ')
   }

   @Override
   public CommandResult convertSimpleMapListToCommandResult(List<String> headlines, String emptyTableMessage,
         List<Map<String, String>> mapList, List<String> firstKeys, Map<String, String> keyToText) {
      CommandResult result = new CommandResult()
      headlines.each { result.addResultLine it }
      if (!mapList.size()) {
         result.addResultLine(emptyTableMessage)
         return result
      }
      
      // all maps must have the same size
      assert mapList*.size().toUnique().size() == 1
      // all maps must contain the given firstKeys
      def allContained = mapList.collect{ it.keySet().containsAll(firstKeys) }.toUnique()
      assert allContained.size() == 1 && allContained[0]
      
      RSTableModel model = new RSTableModel()
      def allTableTitles = firstKeys + (mapList[0]*.key-firstKeys)
      TableDefinition tableDef = new TableDefinition(allTableTitles.collect{keyToText[it]?:it}, mapList[0]*.key.collect { String })
      model.tableDefinition = tableDef
      result.addResultTable model
      mapList.each{ 
         def vals = []
         firstKeys.each { header -> vals << it[header] }
         it.findAll { key, val -> !(key in firstKeys ) }.each{ key, val -> vals << val }
         model.addDataRow(new RSStringTableRow(vals))
      }
      
      return result
   }

}
