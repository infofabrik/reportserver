package net.datenwerke.rs.core.client.urlview;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.homepage.hooks.ClientMainModuleProviderHook;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.urlview.hooker.ObjectInfoProvider;
import net.datenwerke.rs.core.client.urlview.hooker.ViewProviderHooker;
import net.datenwerke.rs.core.client.urlview.hooks.UrlViewClientHook;
import net.datenwerke.rs.core.client.urlview.module.UrlViewClientMainModule;
import net.datenwerke.security.client.security.SecurityUIService;

public class UrlViewUiStartup {

	@Inject
	public UrlViewUiStartup(
		final WaitOnEventUIService waitOnEventService,
		final SecurityUIService securityService,
		final HookHandlerService hookHandler,
		final Provider<LoginService> loginServiceProvider,
		final UrlViewDao dao
		) {
		

		waitOnEventService.permanentCallbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, new SynchronousCallbackOnEventTrigger(){
			public void execute(final WaitOnEventTicket ticket) {
				dao.loadViewConfiguration(new RsAsyncCallback<Map<String,Map<String, List<String[]>>>>(){
					public void onSuccess(Map<String,Map<String, List<String[]>>> result) {
						try{
							if(result.containsKey("adminviews"))
								hookHandler.attachHooker(MainPanelViewProviderHook.class, new ViewProviderHooker(hookHandler, loginServiceProvider, result.get("adminviews")), HookHandlerService.PRIORITY_LOWER);
							
							if(result.containsKey("objectinfo"))
								hookHandler.attachHooker(ObjectPreviewTabProviderHook.class, new ObjectInfoProvider(hookHandler, loginServiceProvider, result.get("objectinfo")), HookHandlerService.PRIORITY_LOWER);
							
							int module = 1;
							if(result.containsKey("module")){
								Map<String, List<String[]>> map = result.get("module");
								for(List<String[]> v : map.values()){
									if(v.isEmpty())
										continue;
									
									String[] values = v.get(0);
									if(values.length < 2)
										continue;
									
									int priority = HookHandlerService.PRIORITY_LOWER + 50 + module++;
									if(values.length > 2){
										try{
											priority = Integer.parseInt(values[2]);
										}catch(Exception e){}
									}
									
									String name = values[0];
									String url = values[1].replace("${username}", loginServiceProvider.get().getCurrentUser().getUsername());;
									
									hookHandler.attachHooker(ClientMainModuleProviderHook.class, new ClientMainModuleProviderHook(new UrlViewClientMainModule(hookHandler, name, url)), priority);
								}
							}
							
							for(UrlViewClientHook hooker : hookHandler.getHookers(UrlViewClientHook.class)){
								try{
									hooker.processUrlViewConfig(result);
								} catch(Exception e){}
							}
						} finally {
							waitOnEventService.signalProcessingDone(ticket);
						}
					};
					
					@Override
					public void onFailure(Throwable caught) {
						waitOnEventService.signalProcessingDone(ticket);
					}
				});
				
			}
		});
	}
}
