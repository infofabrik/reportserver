package net.datenwerke.rs.teamspace.client.teamspace;

import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;

import net.datenwerke.gxtdto.client.stores.LoadableListStore;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;


/**
 * 
 *
 */
public interface TeamSpaceUIService {

	public interface TeamSpaceOperationSuccessHandler{
		public void onSuccess(TeamSpaceDto teamSpace);
	}
	
	/**
	 * Tells if the current user is an admin
	 */
	public boolean isGlobalTsAdmin();

	public boolean hasTeamSpaceCreateRight();

	boolean isAdmin(TeamSpaceDto teamSpace);

	boolean isManager(TeamSpaceDto teamSpace);

	boolean isUser(TeamSpaceDto teamSpace);

	boolean isGuest(TeamSpaceDto teamSpace);

	TeamSpaceRoleDto getRole(TeamSpaceDto teamSpace);

	LoadableListStore<ListLoadConfig, TeamSpaceDto, ListLoadResult<TeamSpaceDto>> getAllTeamSpacesStore();

	LoadableListStore<ListLoadConfig, TeamSpaceDto, ListLoadResult<TeamSpaceDto>> getTeamSpacesStore();

	ListLoader<ListLoadConfig, ListLoadResult<TeamSpaceDto>> getTeamSpacesLoader();

	ListLoader<ListLoadConfig, ListLoadResult<TeamSpaceDto>> getAllTeamSpacesLoader();

	boolean hasTeamSpaceRemoveRight();
	
	public void gotoTeamSpace(TeamSpaceDto selectedItem);

	void displayAddSpaceDialog(TeamSpaceOperationSuccessHandler successHandler);

	public void notifyOfDeletion(TeamSpaceDto deleted);

	public void notifyOfAddition(TeamSpaceDto added);

	public void notifyOfUpdate(TeamSpaceDto updated);
}
