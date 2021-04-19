package net.datenwerke.rs.teamspace.client.teamspace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.homepage.DwMainViewportUiService;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPlaceHolder;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.stores.LoadableListStore;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec;
import net.datenwerke.rs.teamspace.client.teamspace.dto.pa.TeamSpaceDtoPA;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceGenericTargetIdentifier;
import net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto;
import net.datenwerke.rs.teamspace.client.teamspace.ui.TeamSpaceMainComponent;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.client.security.dto.WriteDto;

/**
 * 
 *
 */
public class TeamSpaceUIServiceImpl implements TeamSpaceUIService {

	private static TeamSpaceDtoPA tsPA = GWT.create(TeamSpaceDtoPA.class);
	
	private final SecurityUIService securityService;
	private final TeamSpaceDao tsDao;
	private final DwMainViewportUiService viewportService;
	private final Provider<TeamSpaceClientMainModule> teamSpaceClientModuleProvider;
	
	@Inject
	public TeamSpaceUIServiceImpl(
		SecurityUIService securityService,
		TeamSpaceDao tsDao,
		DwMainViewportUiService viewportService,
		Provider<TeamSpaceClientMainModule> teamSpaceClientModuleProvider
		){
		
		/* store objects */
		this.securityService = securityService;
		this.tsDao = tsDao;
		this.viewportService = viewportService;
		this.teamSpaceClientModuleProvider = teamSpaceClientModuleProvider;
	}
	
	@Override
	public boolean isGlobalTsAdmin() {
		return securityService.hasRight(TeamSpaceGenericTargetIdentifier.class, TeamSpaceAdministratorDto.class);
	}

	@Override
	public boolean hasTeamSpaceCreateRight() {
		return securityService.hasRight(TeamSpaceGenericTargetIdentifier.class, WriteDto.class);
	}
	
	@Override
	public boolean hasTeamSpaceRemoveRight() {
		return securityService.hasRight(TeamSpaceGenericTargetIdentifier.class, DeleteDto.class);
	}

	@Override
	public boolean isAdmin(TeamSpaceDto teamSpace) {
		return hasRole(teamSpace, TeamSpaceRoleDto.ADMIN);
	}

	@Override
	public boolean isManager(TeamSpaceDto teamSpace) {
		return hasRole(teamSpace, TeamSpaceRoleDto.MANAGER);
	}

	@Override
	public boolean isUser(TeamSpaceDto teamSpace) {
		return hasRole(teamSpace, TeamSpaceRoleDto.USER);
	}

	@Override
	public boolean isGuest(TeamSpaceDto teamSpace) {
		return hasRole(teamSpace, TeamSpaceRoleDto.GUEST);
	}

	private boolean hasRole(TeamSpaceDto teamSpace, TeamSpaceRoleDto roleToHave) {
		if(isGlobalTsAdmin() || isOwner(teamSpace))
			return true;
		TeamSpaceRoleDto role = getRole(teamSpace);
		if(null == role)
			return false;
		return roleToHave.compareTo(role) >= 0;
	}
	
	private boolean isOwner(TeamSpaceDto teamSpace) {
		if(null == teamSpace)
			return false;
		return ((TeamSpaceDtoDec)teamSpace).isTeamSpaceOwner();
	}

	@Override
	public TeamSpaceRoleDto getRole(TeamSpaceDto teamSpace){
		return ((TeamSpaceDtoDec)teamSpace).getRole();
	}
	
	@Override
	public ListLoader<ListLoadConfig, ListLoadResult<TeamSpaceDto>> getTeamSpacesLoader(){
		/* create store */
		RpcProxy<ListLoadConfig, ListLoadResult<TeamSpaceDto>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<TeamSpaceDto>>() {
			@Override
			public void load(ListLoadConfig loadConfig,
					AsyncCallback<ListLoadResult<TeamSpaceDto>> callback) {
				tsDao.loadTeamSpaces(callback);
			}
		};

		return new ListLoader<ListLoadConfig, ListLoadResult<TeamSpaceDto>>(proxy);
	}
	
	@Override
	public LoadableListStore<ListLoadConfig, TeamSpaceDto, ListLoadResult<TeamSpaceDto>> getTeamSpacesStore(){
		return new LoadableListStore<ListLoadConfig, TeamSpaceDto, ListLoadResult<TeamSpaceDto>>(tsPA.dtoId(), getTeamSpacesLoader());
	}
	
	@Override
	public ListLoader<ListLoadConfig, ListLoadResult<TeamSpaceDto>> getAllTeamSpacesLoader(){
		/* create store */
		RpcProxy<ListLoadConfig, ListLoadResult<TeamSpaceDto>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<TeamSpaceDto>>() {
			
			@Override
			public void load(ListLoadConfig loadConfig,
					AsyncCallback<ListLoadResult<TeamSpaceDto>> callback) {
				tsDao.loadAllTeamSpaces(callback);
			}
		};
		return new ListLoader<ListLoadConfig, ListLoadResult<TeamSpaceDto>>(proxy);
	}
	
	@Override
	public LoadableListStore<ListLoadConfig, TeamSpaceDto, ListLoadResult<TeamSpaceDto>> getAllTeamSpacesStore(){
		return new LoadableListStore<ListLoadConfig, TeamSpaceDto, ListLoadResult<TeamSpaceDto>>(tsPA.dtoId(), getAllTeamSpacesLoader());
	}
	
	@Override
	public void gotoTeamSpace(TeamSpaceDto teamSpace) {
		if(null != teamSpace){
			TeamSpaceClientMainModule tsModule = teamSpaceClientModuleProvider.get();
			
			tsModule.getMainWidget().loadSpace(teamSpace);
			viewportService.showModule(tsModule);
			
			tsModule.getMainWidget().delayedForceComponentLayout();
		}
	}
	
	@Override
	public void displayAddSpaceDialog(final TeamSpaceOperationSuccessHandler successHandler) {
		final DwWindow window = new DwWindow();
		window.setSize(430, 340);
		window.setHeading(TeamSpaceMessages.INSTANCE.newTeamSpaceHeading());
		window.setHeaderIcon(BaseIcon.GROUP_ADD);
		
		/* create form */
		final SimpleForm form = SimpleForm.getInlineInstance();
		window.add(form, new MarginData(10));
		form.setLabelAlign(LabelAlign.LEFT);
		
		form.addField(String.class, 
				TeamSpaceDtoPA.INSTANCE.name(), 
				BaseMessages.INSTANCE.name(),
				new SFFCAllowBlank() {
					@Override
					public boolean allowBlank() {
						return false;
					}
				},
				new SFFCPlaceHolder() {
					@Override
					public String getPlaceholder() {
						return BaseMessages.INSTANCE.name();
					}
				});
		
		form.addField(String.class, 
				TeamSpaceDtoPA.INSTANCE.description(), 
				TeamSpaceMessages.INSTANCE.newTeamSpaceDescriptionLabel(),
				new SFFCTextAreaImpl(80));
		
		/* create dummy teamspace and bind it to form */
		final TeamSpaceDto dummy = new TeamSpaceDtoDec();
		form.bind(dummy);
		
		/* create buttons */
		window.addCancelButton();
		
		DwTextButton submitBtn = new DwTextButton(TeamSpaceMessages.INSTANCE.submitCreateTeamSpace());
		window.addButton(submitBtn);
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(! form.isValid())
					return;
				window.hide();
				tsDao.createNewTeamSpace(dummy, new NotamCallback<TeamSpaceDto>(TeamSpaceMessages.INSTANCE.newTeamSpaceCreatedMessage()){
					@Override
					public void doOnSuccess(TeamSpaceDto space) {
						successHandler.onSuccess(space);
					}
				});
			}
		});
		
		window.show();
	}

	@Override
	public void notifyOfDeletion(TeamSpaceDto deleted) {
		TeamSpaceClientMainModule tsModule = teamSpaceClientModuleProvider.get();
		
		TeamSpaceMainComponent teamSpaceMainComponent = tsModule.getMainWidget();
		teamSpaceMainComponent.notifyOfDeletion(deleted);
	}

	@Override
	public void notifyOfAddition(TeamSpaceDto added) {
		TeamSpaceClientMainModule tsModule = teamSpaceClientModuleProvider.get();
		
		TeamSpaceMainComponent teamSpaceMainComponent = tsModule.getMainWidget();
		teamSpaceMainComponent.notifyOfAddition(added);		
	}

	@Override
	public void notifyOfUpdate(TeamSpaceDto updated) {
TeamSpaceClientMainModule tsModule = teamSpaceClientModuleProvider.get();
		
		TeamSpaceMainComponent teamSpaceMainComponent = tsModule.getMainWidget();
		teamSpaceMainComponent.notifyOfUpdate(updated);		
	}

}
