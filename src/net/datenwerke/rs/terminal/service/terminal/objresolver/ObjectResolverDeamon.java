package net.datenwerke.rs.terminal.service.terminal.objresolver;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionDeamonHook;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.objresolver.hooks.ObjectResolverHook;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.security.service.security.Securee;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Right;

public class ObjectResolverDeamon implements TerminalSessionDeamonHook {

   private final HookHandlerService hookHandler;
   private final SecurityService securityService;

   private TerminalSession terminalSession;

   @Inject
   public ObjectResolverDeamon(HookHandlerService hookHandler, SecurityService securityService) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.securityService = securityService;
   }

   @Override
   public void init(TerminalSession terminalSession) {
      this.terminalSession = terminalSession;

   }

   @Override
   public void autocomplete(AutocompleteHelper autoHelper) {
      // we do no autocompletion
   }
   
   private boolean checkRights(Object obj, Class<? extends Securee> securee,
         Class<? extends Right>... rights) {
      if (!(obj instanceof SecurityTarget)
            || securityService.checkRights((SecurityTarget) obj, securee, rights)) {
         return true;
      } else {
         return false;
      }
   }
   
   private Collection<Object> doGetObjects(final String locationStr, final Class<? extends Securee> securee,
         final Class<? extends Right>... rights) throws ObjectResolverException {
      return hookHandler.getHookers(ObjectResolverHook.class)
         .stream()
         .filter(objectResolver -> objectResolver.consumes(locationStr, terminalSession))
         .map(rethrowFunction(objectResolver -> objectResolver.getObjects(locationStr, terminalSession)))
         .filter(objects -> null != objects)
         .filter(objects -> !(objects instanceof Collection && objects.contains(null)))
         .flatMap(Collection::stream)
         .map(obj -> {
            if (obj instanceof HibernateProxy)
               return ((HibernateProxy) obj).getHibernateLazyInitializer().getImplementation();
            else
               return obj;
         })
         .filter(obj -> {
            if (rights.length > 0)
               return checkRights(obj, securee, rights);
            else
               return true;
         })
         .collect(toList());
   }
   
   public Collection<Object> getObjects(final Collection<String> locationStrings, final Class<? extends Securee> securee,
         final Class<? extends Right>... rights) throws ObjectResolverException {
      Collection<Object> res = locationStrings
            .stream()
            .map(rethrowFunction(locationStr -> doGetObjects(locationStr, securee, rights)))
            .flatMap(Collection::stream)
            .collect(toList());
      
      return res;
   }

   public Collection<Object> getObjects(Collection<String> locationStrings, Class<? extends Right>... rights)
         throws ObjectResolverException {
      return getObjects(locationStrings, SecurityServiceSecuree.class, rights);
   }

   public Collection<Object> getObjects(String locationStr, Class<? extends Right>... rights)
         throws ObjectResolverException {
      return getObjects(locationStr, SecurityServiceSecuree.class, rights);
   }

   public Collection<Object> getObjects(String locationStr, Class<? extends Securee> securee,
         Class<? extends Right>... rights) throws ObjectResolverException {
      return getObjects(Collections.singleton(locationStr), securee, rights);
   }

   public Object getObject(String locationStr, Class<? extends Right>... rights) throws ObjectResolverException {
      return getObject(locationStr, SecurityServiceSecuree.class, rights);
   }

   public Object getObject(String locationStr, Class<? extends Securee> securee, Class<? extends Right>... rights)
         throws ObjectResolverException {
      Collection<Object> col = getObjects(Collections.singleton(locationStr), securee, rights);
      if (col.isEmpty())
         return null;
      return col.iterator().next();
   }

   public Collection<VFSLocation> getLocations(Collection<String> locationStrings, Class<? extends Securee> securee,
         Class<? extends Right>... rights) throws ObjectResolverException {
      Collection<Object> objects = getObjects(locationStrings, securee, rights);

      final VirtualFileSystemDeamon vfs = terminalSession.getFileSystem();

      Collection<VFSLocation> locations = new HashSet<>();

      locations.addAll(objects.stream().map(object -> vfs.getLocationFor(object)).collect(toSet()));

      return locations;
   }

   public Collection<VFSLocation> getLocations(Collection<String> locationStrings, Class<? extends Right>... rights)
         throws ObjectResolverException {
      return getLocations(locationStrings, SecurityServiceSecuree.class, rights);
   }

   public Collection<VFSLocation> getLocations(String locationStr, Class<? extends Right>... rights)
         throws ObjectResolverException {
      return getLocations(locationStr, SecurityServiceSecuree.class, rights);
   }

   public Collection<VFSLocation> getLocations(String locationStr, Class<? extends Securee> securee,
         Class<? extends Right>... rights) throws ObjectResolverException {
      return getLocations(Collections.singleton(locationStr), securee, rights);
   }

}
