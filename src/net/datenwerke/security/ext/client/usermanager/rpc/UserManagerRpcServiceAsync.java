package net.datenwerke.security.ext.client.usermanager.rpc;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public interface UserManagerRpcServiceAsync {

   void getStrippedDownUsers(AsyncCallback<ListLoadResult<StrippedDownUser>> callback);

   void getStrippedDownGroups(AsyncCallback<ListLoadResult<StrippedDownGroup>> callback);

   void getStrippedDownUsers(Collection<Long> ids, AsyncCallback<List<StrippedDownUser>> callback);

   void changeActiveUserData(UserDto userDto, AsyncCallback<UserDto> callback);

   void updateGroupMembership(GroupDto group, Set<Long> userIds, Set<Long> groupIds, Set<Long> ouIds,
         AsyncCallback<GroupDto> callback);

   void getUserDetailsAsHtml(UserDto userDto, AsyncCallback<SafeHtml> callback);
   
   void getUserGroupDetailsAsHtml(UserDto userDto, AsyncCallback<SafeHtml> callback);
   
   void getOrganisationalUnitDetailsAsHtml(UserDto userDto, AsyncCallback<SafeHtml> callback);
}
