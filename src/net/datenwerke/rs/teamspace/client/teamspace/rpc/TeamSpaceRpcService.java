package net.datenwerke.rs.teamspace.client.teamspace.rpc;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.teamspace.client.teamspace.dto.StrippedDownTeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

@RemoteServiceRelativePath("teamspace")
public interface TeamSpaceRpcService extends RemoteService {

	public TeamSpaceDto getPrimarySpace() throws ServerCallFailedException;
	
	public TeamSpaceDto getExplicitPrimarySpace() throws ServerCallFailedException;
	
	public void setPrimarySpace(TeamSpaceDto teamSpaceDto) throws ServerCallFailedException;

	public TeamSpaceDto reloadTeamSpace(TeamSpaceDto teamSpace) throws ServerCallFailedException;
	
	public TeamSpaceDto createNewTeamSpace(TeamSpaceDto dummySpace) throws ServerCallFailedException;

	public ListLoadResult<TeamSpaceDto> loadTeamSpaces() throws ServerCallFailedException;
	
	public void removeTeamSpace(TeamSpaceDto teamSpace) throws ServerCallFailedException;
	
	public TeamSpaceDto editTeamSpaceSettings(TeamSpaceDto space) throws ServerCallFailedException;
	
	public TeamSpaceDto setMembers(TeamSpaceDto teamSpace, Collection<StrippedDownTeamSpaceMemberDto> members) throws ServerCallFailedException;
	
	public ListLoadResult<TeamSpaceDto> loadAllTeamSpaces() throws ServerCallFailedException;
	
	public TeamSpaceDto reloadTeamSpaceForEdit(TeamSpaceDto teamSpaceDto) throws ServerCallFailedException;
	
}
