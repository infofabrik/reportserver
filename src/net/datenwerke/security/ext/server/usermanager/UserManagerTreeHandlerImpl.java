package net.datenwerke.security.ext.server.usermanager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.usermanager.rpc.UserManagerTreeLoader;
import net.datenwerke.security.ext.client.usermanager.rpc.UserManagerTreeManager;
import net.datenwerke.security.ext.server.locale.DwSecurityMessages;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.hooks.PasswordManualSetHook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.EntireTreeDTO;

/**
 * 
 *
 */
@Singleton
public class UserManagerTreeHandlerImpl extends TreeDBManagerTreeHandler<AbstractUserManagerNode>
      implements UserManagerTreeLoader, UserManagerTreeManager {

   /**
    * 
    */
   private static final long serialVersionUID = -7239838179341589474L;

   private final UserManagerService userManager;
   private final HookHandlerService hookHandlerService;

   @Inject
   public UserManagerTreeHandlerImpl(
         UserManagerService userManager, 
         DtoService dtoGenerator,
         SecurityService securityService, 
         EntityClonerService entityClonerService,
         HookHandlerService hookHandlerService,
         KeyNameGeneratorService keyGeneratorService
         ) {

      super(userManager, 
            dtoGenerator, 
            securityService, 
            entityClonerService,
            keyGeneratorService);

      this.userManager = userManager;
      this.hookHandlerService = hookHandlerService;
   }

   @Override
   protected void doSetInitialProperties(AbstractUserManagerNode inserted) {
      if (inserted instanceof User) {
         ((User) inserted).setFirstname(DwSecurityMessages.INSTANCE.firstname());
         ((User) inserted).setLastname(DwSecurityMessages.INSTANCE.lastname());
         /* getNextFreeUsername queries db -> we will get not-null hibernate error if username is not set */
         ((User) inserted).setUsername(keyGeneratorService.generateDefaultKey());
         String username = getNextFreeUserName(DwSecurityMessages.INSTANCE.username());
         ((User) inserted).setUsername(username);
      } else if (inserted instanceof Group) {
         ((Group) inserted).setName(DwSecurityMessages.INSTANCE.unnamed());
      } else if (inserted instanceof OrganisationalUnit) {
         ((OrganisationalUnit) inserted).setName(DwSecurityMessages.INSTANCE.unnamed());
      }
   }

   @Override
   protected void doUpdateNode(AbstractNodeDto node, AbstractUserManagerNode realNode) throws ExpectedException {

      /* if user, set password by hand */
      if (realNode instanceof User) {
         // TODO : add more sophisticated password criteria!
         if (null != ((UserDto) node).getPassword()) {
            boolean createdPassword = false;
            if (null == ((User) realNode).getPassword())
               createdPassword = true; // this must come before userManager.setPassword()

            userManager.setPassword((User) realNode, ((UserDto) node).getPassword());
            for (PasswordManualSetHook h : hookHandlerService.getHookers(PasswordManualSetHook.class))
               h.passwordWasManuallySet((User) realNode, createdPassword);
         }

      }
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Write.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(@Named("node") AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      /* if node is user, test that username has not been used already */
      if (node instanceof UserDto) {
         UserDto user = (UserDto) node;
         if (null != user.getUsername()) {
            try {
               User realUser = userManager.getUserByName(user.getUsername());
               if (null != realUser && !realUser.getId().equals(node.getId()))
                  throw new ExpectedException("Username already exists.");
            } catch (NoResultException ex) {
            }
         }
      }

      return super.updateNode(node, state);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public EntireTreeDTO loadAll(Dto state, Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters)
         throws ServerCallFailedException {
      boolean filter = (null != wlFilters && !wlFilters.isEmpty()) || (null != blFilters && !blFilters.isEmpty());
      if (filter && null == wlFilters)
         wlFilters = new HashSet<Dto2PosoMapper>();
      if (filter && null == blFilters)
         blFilters = new HashSet<Dto2PosoMapper>();

      EntireTreeDTO treeDto = new EntireTreeDTO();

      int nodeCounter = 0;

      Collection<AbstractUserManagerNode> roots = getRoots();

      Set<Class<?>> wlFilterList = new HashSet<Class<?>>();
      Set<Class<?>> blFilterList = new HashSet<Class<?>>();
      if (filter) {
         for (Dto2PosoMapper filterDtoMapper : wlFilters)
            wlFilterList.add(dtoService.getPosoFromDtoMapper(filterDtoMapper));
         for (Dto2PosoMapper filterDtoMapper : blFilters)
            blFilterList.add(dtoService.getPosoFromDtoMapper(filterDtoMapper));
      }

      for (AbstractUserManagerNode root : roots) {
         if (root instanceof HibernateProxy)
            root = (AbstractUserManagerNode) ((HibernateProxy) root).getHibernateLazyInitializer().getImplementation();

         if (!securityService.checkRights((SecurityTarget) root, SecurityServiceSecuree.class, Read.class))
            continue;
         AbstractNodeDto rootDto = (AbstractNodeDto) dtoService.createListDto(root);
         treeDto.addRoot(rootDto);

         nodeCounter++;

      }

      /* Load all OUs in the tree. */
      nodeCounter = addAllOus(nodeCounter, treeDto);

      if (!filter) {
         /* Load all elements */
         nodeCounter = addAllUsers(nodeCounter, treeDto);
         nodeCounter = addAllGroups(nodeCounter, treeDto);
      } else {
         boolean addUsers = false;
         for (Class<?> wlFilter : wlFilterList) {
            if (wlFilter.isAssignableFrom(User.class)) {
               addUsers = true;
               break;
            }
         }
         if (addUsers)
            nodeCounter = addAllUsers(nodeCounter, treeDto);

         boolean addGroups = false;
         for (Class<?> wlFilter : wlFilterList) {
            if (wlFilter.isAssignableFrom(Group.class)) {
               addGroups = true;
               break;
            }
         }

         if (addGroups)
            nodeCounter = addAllGroups(nodeCounter, treeDto);
      }

      return treeDto;
   }

   private int addAllUsers(int nodeCounter, EntireTreeDTO treeDto) {
      Collection<User> allUsers = userManager.getAllUsers();
      return addAllNodes(nodeCounter, treeDto, allUsers);
   }

   private int addAllGroups(int nodeCounter, EntireTreeDTO treeDto) {
      Collection<Group> allGroups = userManager.getAllGroups();
      return addAllNodes(nodeCounter, treeDto, allGroups);
   }

   private int addAllOus(int nodeCounter, EntireTreeDTO treeDto) {
      Collection<OrganisationalUnit> allOus = userManager.getAllOUs();
      return addAllNodes(nodeCounter, treeDto, allOus);
   }

   private int addAllNodes(int nodeCounter, EntireTreeDTO treeDto,
         Collection<? extends AbstractUserManagerNode> nodes) {
      int addedNodes = 0;

      for (AbstractUserManagerNode child : nodes) {
         if (child instanceof HibernateProxy)
            child = (AbstractUserManagerNode) ((HibernateProxy) child).getHibernateLazyInitializer()
                  .getImplementation();

         if (!securityService.checkRights((SecurityTarget) child, SecurityServiceSecuree.class, Read.class))
            continue;

         AbstractUserManagerNode parent = child.getParent();

         if (parent instanceof HibernateProxy)
            parent = (AbstractUserManagerNode) ((HibernateProxy) parent).getHibernateLazyInitializer()
                  .getImplementation();

         if (!child.isRoot())
            if (!securityService.checkRights((SecurityTarget) parent, SecurityServiceSecuree.class, Read.class))
               continue;

         addedNodes++;

         AbstractNodeDto childDto = (AbstractNodeDto) dtoService.createListDto(child);
         AbstractNodeDto parentDto = (AbstractNodeDto) dtoService.createListDto(parent);

         treeDto.addChild(parentDto, childDto);
      }

      return nodeCounter + addedNodes;
   }
   
   private String getNextFreeUserName(String username) {
      if(userManager.getUserOrNull(username) == null)
         return username;
      int counter = 1;
      boolean freeUsernameFound = false;
      String nextUsername = "";
      while(!freeUsernameFound) {
         nextUsername = username + counter;
         if(userManager.getUserOrNull(nextUsername) == null)
            freeUsernameFound = true;
         counter++;
      }
      return nextUsername;
   }
}
