package net.datenwerke.security.ext.server.usermanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
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

/**
 * 
 *
 */
@Singleton
public class UserManagerRpcServiceImpl extends SecuredRemoteServiceServlet
		implements UserManagerRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8317348655238744366L;

	private final Provider<AuthenticatorService> authenticatorServiceProvider;
	private final DtoService dtoService;
	private final SecurityService securityService;
	private final UserManagerService userService;
	
	@Inject
	public UserManagerRpcServiceImpl(
		Provider<AuthenticatorService> authenticatorServiceProvider,
		DtoService dtoService,
		SecurityService securityService,
		UserManagerService userService
		){
		
		/* store objects */
		this.authenticatorServiceProvider = authenticatorServiceProvider;
		this.dtoService = dtoService;
		this.securityService = securityService;
		this.userService = userService;
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public ListLoadResult<StrippedDownGroup> getStrippedDownGroups()
			throws ServerCallFailedException {
		List<StrippedDownGroup> list = new ArrayList<StrippedDownGroup>();
		
		for(Group group : userService.getAllGroups()){
			if(! securityService.checkRights(group, SecurityServiceSecuree.class, Read.class))
				continue;
			
			StrippedDownGroup sGroup = new StrippedDownGroup();
			sGroup.setName(group.getName());
			sGroup.setId(group.getId());
			
			AbstractUserManagerNode node = group.getParent();
			if(node instanceof HibernateProxy)
				node = (AbstractUserManagerNode) ((HibernateProxy)node).getHibernateLazyInitializer().getImplementation();
			
			if(node instanceof OrganisationalUnit)
				sGroup.setParentOu(((OrganisationalUnit)node).getName());
			else
				sGroup.setParentOu("unknown");
			
			list.add(sGroup);
		}
		
		return new ListLoadResultBean<StrippedDownGroup>(list);
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public ListLoadResult<StrippedDownUser> getStrippedDownUsers()
			throws ServerCallFailedException {
		List<StrippedDownUser> list = new ArrayList<StrippedDownUser>();
		
		for(User user : userService.getAllUsers()){
			if(! securityService.checkRights(user, SecurityServiceSecuree.class, Read.class))
				continue;
			
			StrippedDownUser sUser = new StrippedDownUser();
			sUser.setFirstname(user.getFirstname());
			sUser.setLastname(user.getLastname());
			sUser.setId(user.getId());
			
			AbstractUserManagerNode node = user.getParent();
			if(node instanceof HibernateProxy)
				node = (AbstractUserManagerNode) ((HibernateProxy)node).getHibernateLazyInitializer().getImplementation();
			
			if(node instanceof OrganisationalUnit)
				sUser.setParentOu(((OrganisationalUnit)node).getName());
			else
				sUser.setParentOu("unknown");
			
			list.add(sUser);
		}
		
		return new ListLoadResultBean<StrippedDownUser>(list);
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public UserDto changeActiveUserData(UserDto userDto)
			throws ServerCallFailedException {
		User currentUser = authenticatorServiceProvider.get().getCurrentUser();
		
		/* do the users have the same id ? */
		if(null == currentUser || null == userDto || ! currentUser.getId().equals(userDto.getId()))
			throw new ServerCallFailedException("Could not change user data.");
		
		if(! securityService.checkRights(currentUser, SecurityServiceSecuree.class, Write.class))
			throw new ServerCallFailedException("Not authorized to change user details.");
		
		User user = (User)dtoService.loadPoso(userDto);
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setSex((Sex)dtoService.loadPoso(userDto.getSex()));

		userService.merge(user);
		
		return (UserDto) dtoService.createDto(user);
	}

	@Override
	public List<StrippedDownUser> getStrippedDownUsers(Collection<Long> ids) throws ServerCallFailedException {
		List<StrippedDownUser> list = new ArrayList<StrippedDownUser>();
		
		for(Long id : ids){
			try{
				User user  = (User) userService.getNodeById(id);
				
				if(! securityService.checkRights(user, SecurityServiceSecuree.class, Read.class))
					continue;
				
				StrippedDownUser sUser = new StrippedDownUser();
				sUser.setFirstname(user.getFirstname());
				sUser.setLastname(user.getLastname());
				sUser.setId(user.getId());
				
				AbstractUserManagerNode node = user.getParent();
				if(node instanceof HibernateProxy)
					node = (AbstractUserManagerNode) ((HibernateProxy)node).getHibernateLazyInitializer().getImplementation();
				
				if(node instanceof OrganisationalUnit)
					sUser.setParentOu(((OrganisationalUnit)node).getName());
				else
					sUser.setParentOu("unknown");
				
				list.add(sUser);
			} catch(NoResultException e){}
		}
		
		return list;
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public GroupDto updateGroupMembership(GroupDto groupDto, Set<Long> userIds, Set<Long> groupIds, Set<Long> ouIds){
		Group group = (Group) userService.getNodeById(groupDto.getId());
		
		if(null != userIds){
			Set<User> users = userService.getUsers(userIds);
			group.setUsers(users);
		}
		
		if(null != groupIds){
			Set<Group> groups = userService.getGroups(groupIds);
			group.setReferencedGroups(groups);
		}
		
		if(null != ouIds){
			Set<OrganisationalUnit> ous = userService.getOUs(ouIds);
			group.setOus(ous);
		}

		userService.merge(group);
		
		return (GroupDto) dtoService.createDto(group);
	}


}
