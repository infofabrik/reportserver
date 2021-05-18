package net.datenwerke.rs.teamspace.client.teamspace;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gf.client.homepage.hooks.ClientMainModuleProviderHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;
import net.datenwerke.gxtdto.client.eventbus.handlers.SubmoduleDisplayRequestHandler;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec;
import net.datenwerke.rs.teamspace.client.teamspace.helpers.simpleform.TeamSpaceProvider;
import net.datenwerke.rs.teamspace.client.teamspace.hookers.EditTeamSpaceBasicSettingsHooker;
import net.datenwerke.rs.teamspace.client.teamspace.hookers.EditTeamSpaceMembersHooker;
import net.datenwerke.rs.teamspace.client.teamspace.hookers.UserProfileTeamSpacePropertiesHooker;
import net.datenwerke.rs.teamspace.client.teamspace.hooks.TeamSpaceEditDialogHook;
import net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceGenericTargetIdentifier;
import net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecurityTargetDomainHooker;
import net.datenwerke.rs.teamspace.client.teamspace.ui.TeamSpaceMainComponent;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHook;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

/**
 * 
 *
 */
public class TeamSpaceUIStartup {

	@Inject
	public TeamSpaceUIStartup(
		final WaitOnEventUIService waitOnEventService,
		final TeamSpaceDao rpcService,
			
		final HookHandlerService hookHandler,
		
		final Provider<TeamSpaceClientMainModule> mainModuleProvider,
		
		final Provider<EditTeamSpaceBasicSettingsHooker> editTeamSpaceBasicSettingsProvider,
		final Provider<EditTeamSpaceMembersHooker> editTeamSpaceMembersProvider,
		
		final TeamSpaceSecurityTargetDomainHooker securityTargetDomain,
		
		final UserProfileTeamSpacePropertiesHooker userProfileProvider,
		
		final SecurityUIService securityService,
		
		Provider<TeamSpaceProvider> teamSpaceProvider,
		
		final EventBus eventBus,
		final Provider<TeamSpaceMainComponent> teamSpaceMainComponentProvider,
		
		final Provider<TeamSpaceUIService> teamSpaceServiceProvider,
		final Provider<TeamSpaceAdminModule> teamSpaceAdminModuleProvider,
		
		final TeamSpaceDao teamSpaceDao
		){
		
		/* create module provider */
		final ClientMainModuleProviderHook mainModule = new ClientMainModuleProviderHook(mainModuleProvider);
		
		/* attach to load rights */
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		
		/* request callback after login and check for rights */
		waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED, new SynchronousCallbackOnEventTrigger() {
			
			public void execute(final WaitOnEventTicket ticket) {
				if(securityService.hasRight(TeamSpaceGenericTargetIdentifier.class, ReadDto.class)){
					/* attach hooker */
					hookHandler.attachHooker(ClientMainModuleProviderHook.class, mainModule, HookHandlerService.PRIORITY_HIGH + 10);
					
					hookHandler.attachHooker(TeamSpaceEditDialogHook.class, editTeamSpaceBasicSettingsProvider, HookHandlerService.PRIORITY_HIGH);
					hookHandler.attachHooker(TeamSpaceEditDialogHook.class, editTeamSpaceMembersProvider);
					
					/* attach security target domains */
					hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);
					
					/* user profile */
					hookHandler.attachHooker(UserProfileCardProviderHook.class, userProfileProvider);
				} 
						
				waitOnEventService.signalProcessingDone(ticket);
			}
		});
		
		/* simpleform */
		hookHandler.attachHooker(FormFieldProviderHook.class, teamSpaceProvider, HookHandlerService.PRIORITY_LOW);
		
		/* test if user has rights to see datasource manager admin view */
		waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, new SynchronousCallbackOnEventTrigger(){
			public void execute(final WaitOnEventTicket ticket) {
				if(teamSpaceServiceProvider.get().isGlobalTsAdmin())
					hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(teamSpaceAdminModuleProvider.get()),  HookHandlerService.PRIORITY_HIGH + 60);
				waitOnEventService.signalProcessingDone(ticket);
			}
		});
		
		
		/* history */
		/* attach to eventbus */
		eventBus.addHandler(SubmoduleDisplayRequest.TYPE, new SubmoduleDisplayRequestHandler() {
			@Override
			public void onSubmoduleDisplayRequest(final SubmoduleDisplayRequest event) {
				if(TeamSpaceUIModule.TEAMSPACE_PANEL_ID.equals(event.getParentId())){
					final TeamSpaceMainComponent mainComponent = teamSpaceMainComponentProvider.get();
					eventBus.fireEvent(new SubmoduleDisplayRequest(mainComponent, null));
					
					Long id = (Long) event.getParameters().get(TeamSpaceUIModule.TEAMSPACE_ID_KEY);
					TeamSpaceDto teamSpace = new TeamSpaceDtoDec();
					teamSpace.setId(id);
					
					teamSpaceDao.reloadTeamSpace(teamSpace, new RsAsyncCallback<TeamSpaceDto>(){
						public void onSuccess(TeamSpaceDto result) {
							mainComponent.loadSpace(result);
							
							event.notifyCallback(mainComponent.getCurrentApps());						
						};
					});
				}
			}
		});
	}
}