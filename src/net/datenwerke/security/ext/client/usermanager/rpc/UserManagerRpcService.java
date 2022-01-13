package net.datenwerke.security.ext.client.usermanager.rpc;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

@RemoteServiceRelativePath("usermanager")
public interface UserManagerRpcService extends RemoteService {

   ListLoadResult<StrippedDownUser> getStrippedDownUsers() throws ServerCallFailedException;

   ListLoadResult<StrippedDownGroup> getStrippedDownGroups() throws ServerCallFailedException;

   UserDto changeActiveUserData(UserDto userDto) throws ServerCallFailedException;

   List<StrippedDownUser> getStrippedDownUsers(Collection<Long> ids) throws ServerCallFailedException;

   GroupDto updateGroupMembership(GroupDto group, Set<Long> userIds, Set<Long> groupIds, Set<Long> ouIds)
         throws ServerCallFailedException;
   
   SafeHtml getUserDetailsAsHtml(UserDto userDto) throws ServerCallFailedException;

   SafeHtml getUserGroupDetailsAsHtml(UserDto userDto) throws ServerCallFailedException;
   
   SafeHtml getOrganisationalUnitDetailsAsHtml(UserDto userDto) throws ServerCallFailedException;
}
