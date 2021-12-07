package net.datenwerke.usermanager.ext.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.localization.hooks.LocaleChangedNotificationHook;
import net.datenwerke.security.service.usermanager.hooks.GroupModSubCommandHook;
import net.datenwerke.usermanager.ext.service.eximport.HttpUserManagerImportConfigurationHooker;
import net.datenwerke.usermanager.ext.service.eximport.UserManagerExporter;
import net.datenwerke.usermanager.ext.service.eximport.UserManagerImporter;
import net.datenwerke.usermanager.ext.service.eximport.hooker.ExportAllUsersHooker;
import net.datenwerke.usermanager.ext.service.eximport.hooker.ImportAllUsersHooker;
import net.datenwerke.usermanager.ext.service.history.UserManagerHistoryUrlBuilderHooker;
import net.datenwerke.usermanager.ext.service.hookers.UpdateUserLocalHooker;
import net.datenwerke.usermanager.ext.service.hooks.UserModSubCommandHook;
import net.datenwerke.usermanager.ext.service.terminal.commands.AddMembersSubCommand;
import net.datenwerke.usermanager.ext.service.terminal.commands.GroupModCommand;
import net.datenwerke.usermanager.ext.service.terminal.commands.IdCommand;
import net.datenwerke.usermanager.ext.service.terminal.commands.SetUserPropertySubCommand;
import net.datenwerke.usermanager.ext.service.terminal.commands.UserModCommand;


public class UserManagerExtStartup {

	@Inject
	public UserManagerExtStartup(
		HookHandlerService hookHandler,
		Provider<UserManagerExporter> exporterProvider,
		Provider<UserManagerImporter> importerProvider,
		Provider<HttpUserManagerImportConfigurationHooker> httpImportConfigHookerProvider,
		Provider<ExportAllUsersHooker> exportAllUsers,
		Provider<ImportAllUsersHooker> importAllUsers,
		
		Provider<UpdateUserLocalHooker> updateUserLocaleHooker,
		
		Provider<UserManagerHistoryUrlBuilderHooker> userManagerUrlBuilder,
		
		Provider<UserModCommand> userModCommandProvider,
		Provider<SetUserPropertySubCommand> setUserPropertyCommandProvider,
		Provider<GroupModCommand> groupModProvider,
		Provider<AddMembersSubCommand> addMembersToGroupProvider,
		Provider<IdCommand> idCommandProvider
		){
		
		hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(exporterProvider));
		hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(importerProvider));
		hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, httpImportConfigHookerProvider);
		hookHandler.attachHooker(ExportAllHook.class, exportAllUsers);
		hookHandler.attachHooker(ImportAllHook.class, importAllUsers);
		
		hookHandler.attachHooker(LocaleChangedNotificationHook.class, updateUserLocaleHooker);
		
		hookHandler.attachHooker(TerminalCommandHook.class, userModCommandProvider);
		hookHandler.attachHooker(UserModSubCommandHook.class, setUserPropertyCommandProvider);
		hookHandler.attachHooker(TerminalCommandHook.class, groupModProvider);
		hookHandler.attachHooker(GroupModSubCommandHook.class, addMembersToGroupProvider);
		hookHandler.attachHooker(TerminalCommandHook.class, idCommandProvider);
		
		hookHandler.attachHooker(HistoryUrlBuilderHook.class, userManagerUrlBuilder);
	}
}
