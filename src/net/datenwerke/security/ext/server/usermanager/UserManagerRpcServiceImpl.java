package net.datenwerke.security.ext.server.usermanager;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.proxy.HibernateProxy;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyUserMetadata;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.security.ext.client.usermanager.rpc.UserManagerRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.Sex;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.usermanager.ext.service.locale.UserManagerMessages;

/**
 * 
 *
 */
@Singleton
public class UserManagerRpcServiceImpl extends SecuredRemoteServiceServlet implements UserManagerRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 8317348655238744366L;

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final DtoService dtoService;
   private final SecurityService securityService;
   private final UserManagerService userService;
   private final BsiPasswordPolicyService bsiPasswordPolicyService;
   private final HistoryService historyService;

   @Inject
   public UserManagerRpcServiceImpl(
         Provider<AuthenticatorService> authenticatorServiceProvider, 
         DtoService dtoService,
         SecurityService securityService, 
         UserManagerService userService,
         BsiPasswordPolicyService bsiPasswordPolicyService,
         HistoryService historyService
         ) {

      /* store objects */
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.dtoService = dtoService;
      this.securityService = securityService;
      this.userService = userService;
      this.bsiPasswordPolicyService = bsiPasswordPolicyService;
      this.historyService = historyService;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public ListLoadResult<StrippedDownGroup> getStrippedDownGroups() throws ServerCallFailedException {
      List<StrippedDownGroup> list = new ArrayList<StrippedDownGroup>();

      for (Group group : userService.getAllGroups()) {
         if (!securityService.checkRights(group, SecurityServiceSecuree.class, Read.class))
            continue;

         StrippedDownGroup sGroup = new StrippedDownGroup();
         sGroup.setName(group.getName());
         sGroup.setId(group.getId());

         AbstractUserManagerNode node = group.getParent();
         if (node instanceof HibernateProxy)
            node = (AbstractUserManagerNode) ((HibernateProxy) node).getHibernateLazyInitializer().getImplementation();

         if (node instanceof OrganisationalUnit)
            sGroup.setParentOu(((OrganisationalUnit) node).getName());
         else
            sGroup.setParentOu("unknown");

         list.add(sGroup);
      }

      return new ListLoadResultBean<StrippedDownGroup>(list);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public ListLoadResult<StrippedDownUser> getStrippedDownUsers() throws ServerCallFailedException {
      List<StrippedDownUser> list = new ArrayList<StrippedDownUser>();

      for (User user : userService.getAllUsers()) {
         if (!securityService.checkRights(user, SecurityServiceSecuree.class, Read.class))
            continue;

         StrippedDownUser sUser = new StrippedDownUser();
         sUser.setFirstname(user.getFirstname());
         sUser.setLastname(user.getLastname());
         sUser.setId(user.getId());

         AbstractUserManagerNode node = user.getParent();
         if (node instanceof HibernateProxy)
            node = (AbstractUserManagerNode) ((HibernateProxy) node).getHibernateLazyInitializer().getImplementation();

         if (node instanceof OrganisationalUnit)
            sUser.setParentOu(((OrganisationalUnit) node).getName());
         else
            sUser.setParentOu("unknown");

         list.add(sUser);
      }

      return new ListLoadResultBean<StrippedDownUser>(list);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public UserDto changeActiveUserData(UserDto userDto) throws ServerCallFailedException {
      User currentUser = authenticatorServiceProvider.get().getCurrentUser();

      /* do the users have the same id ? */
      if (null == currentUser || null == userDto || !currentUser.getId().equals(userDto.getId()))
         throw new ServerCallFailedException("Could not change user data.");

      if (!securityService.checkRights(currentUser, SecurityServiceSecuree.class, Write.class))
         throw new ServerCallFailedException("Not authorized to change user details.");

      User user = (User) dtoService.loadPoso(userDto);
      user.setFirstname(userDto.getFirstname());
      user.setLastname(userDto.getLastname());
      user.setEmail(userDto.getEmail());
      user.setSex((Sex) dtoService.loadPoso(userDto.getSex()));

      userService.merge(user);

      return (UserDto) dtoService.createDto(user);
   }

   @Override
   public List<StrippedDownUser> getStrippedDownUsers(Collection<Long> ids) throws ServerCallFailedException {
      List<StrippedDownUser> list = new ArrayList<StrippedDownUser>();

      for (Long id : ids) {
         try {
            User user = (User) userService.getNodeById(id);

            if (!securityService.checkRights(user, SecurityServiceSecuree.class, Read.class))
               continue;

            StrippedDownUser sUser = new StrippedDownUser();
            sUser.setFirstname(user.getFirstname());
            sUser.setLastname(user.getLastname());
            sUser.setId(user.getId());

            AbstractUserManagerNode node = user.getParent();
            if (node instanceof HibernateProxy)
               node = (AbstractUserManagerNode) ((HibernateProxy) node).getHibernateLazyInitializer()
                     .getImplementation();

            if (node instanceof OrganisationalUnit)
               sUser.setParentOu(((OrganisationalUnit) node).getName());
            else
               sUser.setParentOu("unknown");

            list.add(sUser);
         } catch (NoResultException e) {
         }
      }

      return list;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public GroupDto updateGroupMembership(GroupDto groupDto, Set<Long> userIds, Set<Long> groupIds, Set<Long> ouIds) {
      Group group = (Group) userService.getNodeById(groupDto.getId());

      if (null != userIds) {
         Set<User> users = userService.getUsers(userIds);
         group.setUsers(users);
      }

      if (null != groupIds) {
         Set<Group> groups = userService.getGroups(groupIds);
         group.setReferencedGroups(groups);
      }

      if (null != ouIds) {
         Set<OrganisationalUnit> ous = userService.getOUs(ouIds);
         group.setOus(ous);
      }

      userService.merge(group);

      return (GroupDto) dtoService.createDto(group);
   }
   
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public SafeHtml getUserDetailsAsHtml(UserDto userDto) throws ServerCallFailedException {
      User user = (User) dtoService.loadPoso(userDto);
      SafeHtmlBuilder builder = new SafeHtmlBuilder();
      builder.appendEscaped("Title: " + (Sex.Male.equals(user.getSex()) ? UserManagerMessages.INSTANCE.genderMale()
            : Sex.Female.equals(user.getSex()) ? UserManagerMessages.INSTANCE.genderFemale() : ""));
      builder.appendHtmlConstant("<br/>");
      builder.appendEscaped("ID: " + user.getId());
      builder.appendHtmlConstant("<br/>");
      builder.appendEscaped("User name: " + user.getUsername());
      builder.appendHtmlConstant("<br/>");
      builder.appendEscaped("First name: " + (null != user.getFirstname() ? user.getFirstname() : ""));
      builder.appendHtmlConstant("<br/>");
      builder.appendEscaped("Last name: " + (null != user.getLastname() ? user.getLastname() : ""));
      builder.appendHtmlConstant("<br/>");
      builder.appendEscaped("E-mail: " + (null != user.getEmail() ? user.getEmail() : ""));
      builder.appendHtmlConstant("<br/>");
      BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata(user);

      if (null != metadata.getAccountInhibited()) {
         builder.appendEscaped("Account Inhibition: " + String.valueOf(metadata.getAccountInhibited()));
      } else {
         builder.appendEscaped("Account Inhibition: " + "false");
      }

      builder.appendHtmlConstant("<br/>");

      if (null != metadata.getAccountExpirationDate()) {
         builder.appendEscaped(
               "Account Expires: " + (new SimpleDateFormat("dd.MM.yyyy").format(metadata.getAccountExpirationDate())));
      } else {
         builder.appendEscaped("Account Expires : " + " ");
      }
      return builder.toSafeHtml();
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public SafeHtml getUserGroupDetailsAsHtml(UserDto userDto) throws ServerCallFailedException {
      User user = (User) dtoService.loadPoso(userDto);
      Collection<Group> groups = userService.getReferencedGroups(user);
      if (groups.isEmpty())
         return null;
      List<Group> sortedGroups = groups
            .stream()
            .sorted(comparing(Group::getName))
            .collect(toList());
      final SafeHtmlBuilder builder = new SafeHtmlBuilder();
      builder.appendHtmlConstant("<ul>");
      sortedGroups.forEach(group -> builder.appendHtmlConstant("<li>")
            .appendEscaped(group.getName() + " (" + group.getId() + ")").appendHtmlConstant("</li>"));
      builder.appendHtmlConstant("</ul>");
      return builder.toSafeHtml();
   }
   
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public SafeHtml getOrganisationalUnitDetailsAsHtml(UserDto userDto) throws ServerCallFailedException {
      User user = (User) dtoService.loadPoso(userDto);
      OrganisationalUnit ou = ((OrganisationalUnit) user.getParent());
      List<HistoryLink> ouPath = historyService.buildLinksFor(ou);

      final SafeHtmlBuilder builder = new SafeHtmlBuilder();
      builder.appendHtmlConstant("<ul>");
      ouPath.forEach(link -> builder.appendHtmlConstant("<li>")
            .appendEscaped(null != link.getObjectCaption() ? link.getObjectCaption() + "/" + user.getUsername() : "")
            .appendHtmlConstant("</li>"));
      builder.appendHtmlConstant("</ul>");

      return builder.toSafeHtml();
   }

}
