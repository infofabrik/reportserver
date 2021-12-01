package net.datenwerke.rs.teamspace.service.teamspace;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.teamspace.service.teamspace.eventhandler.UserNodeForceRemoveEventHandler;
import net.datenwerke.rs.teamspace.service.teamspace.eventhandler.UserNodeRemoveEventHandler;
import net.datenwerke.rs.teamspace.service.teamspace.eximport.TeamSpaceExporter;
import net.datenwerke.rs.teamspace.service.teamspace.eximport.TeamSpaceImporter;
import net.datenwerke.rs.teamspace.service.teamspace.eximport.hookers.ExportAllTeamspacesHooker;
import net.datenwerke.rs.teamspace.service.teamspace.eximport.hookers.ImportAllTeamspacesHooker;
import net.datenwerke.rs.teamspace.service.teamspace.genrights.TeamSpaceSecurityTarget;
import net.datenwerke.rs.teamspace.service.teamspace.hooks.TeamspaceModSubCommandHook;
import net.datenwerke.rs.teamspace.service.teamspace.security.TeamSpaceSecuree;
import net.datenwerke.rs.teamspace.service.teamspace.terminal.commands.AddMembersSubCommand;
import net.datenwerke.rs.teamspace.service.teamspace.terminal.commands.SetRoleSubCommand;
import net.datenwerke.rs.teamspace.service.teamspace.terminal.commands.TeamspaceModCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

/**
 * 
 *
 */
public class TeamSpaceStartup {

	@Inject
	public TeamSpaceStartup(
		HookHandlerService hookHandler,
		SecurityService securityService,
		EventBus eventBus,
		
		UserNodeRemoveEventHandler userNodeRemoveEventHandler,
		UserNodeForceRemoveEventHandler userNodeForceRemoveEventHandler,
		
		Provider<TeamspaceModCommand> teamspaceModProvider,
		
		Provider<TeamSpaceExporter> exporterProvider,
		Provider<TeamSpaceImporter> importerProvider,
		Provider<ExportAllTeamspacesHooker> exportAllTeamspaces,
		Provider<ImportAllTeamspacesHooker> importAllTeamspaces,
		
		Provider<AddMembersSubCommand> addMembersToTeamspaceProvider,
		Provider<SetRoleSubCommand> setRoleInTeamspaceProvider,
		final Provider<TeamSpaceService> teamSpaceServiceProvider
		){
		
		eventBus.attachObjectEventHandler(RemoveEntityEvent.class, AbstractUserManagerNode.class, userNodeRemoveEventHandler);
		eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, AbstractUserManagerNode.class, userNodeForceRemoveEventHandler);
		
		hookHandler.attachHooker(TerminalCommandHook.class, teamspaceModProvider);
		hookHandler.attachHooker(TeamspaceModSubCommandHook.class, addMembersToTeamspaceProvider);
		hookHandler.attachHooker(TeamspaceModSubCommandHook.class, setRoleInTeamspaceProvider);
		
		/* eximport */
		hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(exporterProvider));
		hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(importerProvider));
		hookHandler.attachHooker(ExportAllHook.class, exportAllTeamspaces);
		hookHandler.attachHooker(ImportAllHook.class, importAllTeamspaces);
		
		registerSecurityTargets(securityService);
		
	}
	
	private void registerSecurityTargets(SecurityService securityService) {
		/* register security target */
		securityService.registerSecurityTarget(TeamSpaceSecurityTarget.class, new TeamSpaceSecuree());
	}
}
