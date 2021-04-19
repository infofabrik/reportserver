package net.datenwerke.rs.teamspace.client.teamspace.rpc;

import java.util.Collection;

import net.datenwerke.rs.teamspace.client.teamspace.dto.StrippedDownTeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

public interface TeamSpaceRpcServiceAsync {

	void getPrimarySpace(AsyncCallback<TeamSpaceDto> callback);
	
	void getExplicitPrimarySpace(AsyncCallback<TeamSpaceDto> callback);

	void setPrimarySpace(TeamSpaceDto teamSpaceDto, AsyncCallback<Void> callback);
	
	void reloadTeamSpace(TeamSpaceDto teamSpace,
			AsyncCallback<TeamSpaceDto> callback);
	
	void createNewTeamSpace(TeamSpaceDto dummySpace,
			AsyncCallback<TeamSpaceDto> callback);

	void loadTeamSpaces(AsyncCallback<ListLoadResult<TeamSpaceDto>> callback);

	void removeTeamSpace(TeamSpaceDto teamSpace, AsyncCallback<Void> callback);

	void editTeamSpaceSettings(TeamSpaceDto space,
			AsyncCallback<TeamSpaceDto> callback);

	void setMembers(TeamSpaceDto teamSpace,
			Collection<StrippedDownTeamSpaceMemberDto> members,
			AsyncCallback<TeamSpaceDto> callback);

	void loadAllTeamSpaces(AsyncCallback<ListLoadResult<TeamSpaceDto>> callback);

	void reloadTeamSpaceForEdit(TeamSpaceDto teamSpaceDto, AsyncCallback<TeamSpaceDto> callback);
	
}
