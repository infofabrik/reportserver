package net.datenwerke.rs.teamspace.client.teamspace;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.teamspace.client.teamspace.dto.StrippedDownTeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.rpc.TeamSpaceRpcServiceAsync;

public class TeamSpaceDao extends Dao {

	private final TeamSpaceRpcServiceAsync rpcService;
	
	@Inject
	public TeamSpaceDao(
		TeamSpaceRpcServiceAsync rpcService	
		){
		
		/* store objects */
		this.rpcService = rpcService;
	}
	
	public void getPrimarySpace(AsyncCallback<TeamSpaceDto> callback){
		rpcService.getPrimarySpace(transformAndKeepCallback(callback));
	}
	
	public void getExplicitPrimarySpace(AsyncCallback<TeamSpaceDto> callback){
		rpcService.getExplicitPrimarySpace(transformAndKeepCallback(callback));
	}
	
	public void setPrimarySpace(TeamSpaceDto teamSpaceDto, AsyncCallback<Void> callback){
		rpcService.setPrimarySpace(teamSpaceDto, transformAndKeepCallback(callback));
	}

	
	public void reloadTeamSpace(TeamSpaceDto teamSpace,
			AsyncCallback<TeamSpaceDto> callback){
		rpcService.reloadTeamSpace(teamSpace, transformAndKeepCallback(callback));
	}

	
	public void createNewTeamSpace(TeamSpaceDto dummySpace,
			AsyncCallback<TeamSpaceDto> callback){
		rpcService.createNewTeamSpace(dummySpace, transformAndKeepCallback(callback));
	}
	
	public void loadTeamSpaces(AsyncCallback<ListLoadResult<TeamSpaceDto>> callback){
		rpcService.loadTeamSpaces(transformAndKeepCallback(callback));
	}
	
	public void removeTeamSpace(TeamSpaceDto teamSpace, AsyncCallback<Void> callback){
		rpcService.removeTeamSpace(teamSpace, transformAndKeepCallback(callback));
	}
	
	public void editTeamSpaceSettings(TeamSpaceDto space, AsyncCallback<TeamSpaceDto> callback){
		rpcService.editTeamSpaceSettings(space, transformAndKeepCallback(callback));
	}
	
	public void setMembers(TeamSpaceDto teamSpace,
			Collection<StrippedDownTeamSpaceMemberDto> members,
			AsyncCallback<TeamSpaceDto> callback){
		rpcService.setMembers(teamSpace, members, transformAndKeepCallback(callback));
	}
	
	public void loadAllTeamSpaces(AsyncCallback<ListLoadResult<TeamSpaceDto>> callback){
		rpcService.loadAllTeamSpaces(transformAndKeepCallback(callback));
	}
	
	public void reloadTeamSpaceForEdit(TeamSpaceDto teamSpace, AsyncCallback<TeamSpaceDto> callback) {
		rpcService.reloadTeamSpaceForEdit(teamSpace, transformAndKeepCallback(callback));
	}

}
