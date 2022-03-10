package net.datenwerke.rs.terminal.service.terminal;

import java.util.Collection;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.exceptions.SessionNotFoundException;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;
import net.datenwerke.security.service.security.rights.Right;

/**
 * 
 *
 */
public class TerminalServiceImpl implements TerminalService {

   private final Provider<TerminalSession> terminalSessionProvider;
   private final Provider<TerminalSessionMap> terminalSessionMap;

   @Inject
   public TerminalServiceImpl(Provider<TerminalSession> terminalSessionProvider,
         Provider<TerminalSessionMap> terminalSessionMap) {

      /* store objects */
      this.terminalSessionProvider = terminalSessionProvider;
      this.terminalSessionMap = terminalSessionMap;
   }

   @Override
   public TerminalSession getUnscopedTerminalSession() {
      TerminalSession session = terminalSessionProvider.get();
      return session;
   }

   @Override
   public TerminalSession initTerminalSession() {
      TerminalSession session = terminalSessionProvider.get();
      terminalSessionMap.get().put(session.getSessionId(), session);
      return session;
   }

   @Override
   public TerminalSession getTerminalSession(String sessionId) throws SessionNotFoundException {
      if (!terminalSessionMap.get().containsKey(sessionId))
         throw new SessionNotFoundException();
      return terminalSessionMap.get().get(sessionId);
   }

   @Override
   public void closeTerminalSession(String sessionId) {
      terminalSessionMap.get().remove(sessionId);
   }

   @Override
   public Object getObjectByLocation(Class<? extends VirtualFileSystemManagerHook> vfsManager, String location)
         throws VFSException {
      return getObjectByLocation(vfsManager, location, true);
   }

   @Override
   public Object getObjectByLocation(Class<? extends VirtualFileSystemManagerHook> vfsManager, String location,
         boolean checkRights) throws VFSException {
      TerminalSession session = terminalSessionProvider.get();

      if (!checkRights)
         session.setCheckRights(checkRights);

      VirtualFileSystemDeamon vfs = session.getFileSystem();
      VFSLocation root = vfs.getFileSystemRoot(vfsManager);
      try {
         VFSLocation objLocation = vfs.getLocation(root, location);

         if (null != objLocation)
            return objLocation.getObject();
      } catch (VFSException e) {
      }

      return null;
   }

   @Override
   public Collection<Object> getObjectsByLocation(String location) throws ObjectResolverException {
      return getObjectsByLocation(location, true);
   }

   @Override
   public Collection<Object> getObjectsByLocation(String location, boolean checkRights) throws ObjectResolverException {
      TerminalSession session = terminalSessionProvider.get();
      if (!checkRights)
         session.setCheckRights(checkRights);

      return session.getObjectResolver().getObjects(location);
   }

   @Override
   public Object getObjectByLocation(String location) throws ObjectResolverException {
      return getObjectByLocation(location, true);
   }

   @Override
   public Object getObjectByLocation(String location, boolean checkRights) throws ObjectResolverException {
      Collection<Object> objects = getObjectsByLocation(location, checkRights);
      if (null != objects && objects.size() > 0) {
         Object obj = objects.iterator().next();
         if (obj instanceof HibernateProxy)
            obj = ((HibernateProxy) obj).getHibernateLazyInitializer().getImplementation();
         return obj;
      }
      return null;
   }

   @Override
   public <T> T getSingleObjectOfTypeByQuery(Class<T> type, String query, TerminalSession session,
         Class<? extends Right>... rights) throws ObjectResolverException {
      Collection<Object> resolvedDatasource = session.getObjectResolver().getObjects(query, rights);
      if (1 != resolvedDatasource.size())
         throw new IllegalArgumentException("Query must be resolved to exactly one object: \"" + query + "\"");
      Object asObject = resolvedDatasource.iterator().next();
      if (!(type.isInstance(asObject)))
         throw new IllegalArgumentException(
               "Incorrect type found. Required type: \"" + type.getSimpleName() + ", " + query + "\"");
      return (T) asObject;
   }

}
