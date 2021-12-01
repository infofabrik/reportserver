package net.datenwerke.rs.dsbundle.client.dsbundle;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.dsbundle.client.dsbundle.hooker.AuthenticatorWindowExtraOptionHooker;
import net.datenwerke.rs.dsbundle.client.dsbundle.hooker.DatabaseBundleConfigProviderHooker;
import net.datenwerke.rs.dsbundle.client.dsbundle.hooker.DatasourceBundleTesterToolbarConfigurator;
import net.datenwerke.rs.enterprise.client.EnterpriseCheckUiModule;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.security.client.login.hooks.AuthenticatorWindowExtraOptionHook;

public class DatasourceBundleUiStartup {

	@Inject
	public DatasourceBundleUiStartup(
		final WaitOnEventUIService waitOnEventService,
		final HookHandlerService hookHandler,
		Provider<DatabaseBundleConfigProviderHooker> configProvider,
		final AuthenticatorWindowExtraOptionHooker authenticatorWindowExtraOptionHooker,
		
		final DatasourceBundleDao dsBundleDao,
		final DatasourceBundleUiService dsBundleService,
		
		final EnterpriseUiService enterpriseService,
		
		final DatasourceBundleTesterToolbarConfigurator bundleTesterConfigurator
		
		){
		
		hookHandler.attachHooker(DatasourceDefinitionConfigProviderHook.class, configProvider, 60);
		
		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, bundleTesterConfigurator);
		
		waitOnEventService.callbackOnEvent(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_BEFORE_LOGIN, new SynchronousCallbackOnEventTrigger() {
			@Override
			public void execute(final WaitOnEventTicket ticket) {
				hookHandler.detachHooker(AuthenticatorWindowExtraOptionHook.class, authenticatorWindowExtraOptionHooker);
				
				if(! enterpriseService.isEnterprise())
					waitOnEventService.signalProcessingDone(ticket);
				else {
					dsBundleDao.getAvailableBundleKeys(new RsAsyncCallback<List<String>>(){
						public void onSuccess(List<String> result) {
							dsBundleService.setAvailableBundleKeys(result);
							
							if(null != result && ! result.isEmpty())
								hookHandler.attachHooker(AuthenticatorWindowExtraOptionHook.class, authenticatorWindowExtraOptionHooker);
							
							waitOnEventService.signalProcessingDone(ticket);
						};
						@Override
						public void onFailure(Throwable caught) {
							waitOnEventService.signalProcessingDone(ticket);
						}
					});
				}
								
			}
		});
	}
}
