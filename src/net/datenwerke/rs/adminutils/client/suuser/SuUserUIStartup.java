package net.datenwerke.rs.adminutils.client.suuser;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow.OnButtonClickHandler;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.suuser.locale.SuMessages;
import net.datenwerke.rs.adminutils.client.suuser.security.SuGenericTargetIdentifier;
import net.datenwerke.rs.adminutils.client.suuser.security.SuViewSecurityTargetDomainHooker;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.usermanager.helper.simpleform.SFFCUserSelector;

public class SuUserUIStartup {

	private String userKey;
	
	@Inject
	public SuUserUIStartup(
			final HookHandlerService hookHandler,
			final SecurityUIService securityService,
			final WaitOnEventUIService waitOnEventService,

			SuViewSecurityTargetDomainHooker securityTargetDomain,
			
			final SuUserDao suUserDao,
			final UtilsUIService utilsService
			){
			
			/* attach security target domains */
			hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
			hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

		
			waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED, new SynchronousCallbackOnEventTrigger(){
				public void execute(final WaitOnEventTicket ticket) {
					if(securityService.hasRight(SuGenericTargetIdentifier.class, ExecuteDto.class))
						attachSu(suUserDao, utilsService);

					waitOnEventService.signalProcessingDone(ticket);
				}
			});
		}

	protected void attachSu(final SuUserDao suUserDao, final UtilsUIService utilsService) {
		Event.addNativePreviewHandler(new NativePreviewHandler(){
			@Override
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				if(event.getTypeInt() == Event.ONKEYUP){
					boolean ctrlKey = event.getNativeEvent().getCtrlKey();
					if(ctrlKey){
						int keyCode = event.getNativeEvent().getKeyCode();
						if(ctrlKey && (keyCode == 'L' || keyCode == 'l')){
							DwWindow window = new DwWindow();
							window.setModal(true);
							window.setHeaderIcon(BaseIcon.USER_SECRET);
							window.setSize(300, 180);
							window.setResizable(false);
							window.setHeading(SuMessages.INSTANCE.suPromptHeader());
							
							final SimpleForm form = SimpleForm.getInlineInstance();
							
							userKey = form.addField(UserDto.class, SuMessages.INSTANCE.userLabel(), new SFFCUserSelector() {
								@Override
								public UserDto getUser() {
									return (UserDto) form.getValue(userKey);
								}
							});
							
							form.loadFields();
							window.add(form, new MarginData(10));
							
							window.addCancelButton();
							window.addSubmitButton(new OnButtonClickHandler() {
								@Override
								public void onClick() {
									UserDto user = (UserDto) form.getValue(userKey);
									suUserDao.su(user.getId(), new RsAsyncCallback<Void>(){
										public void onSuccess(Void result) {
											utilsService.reloadPageWithoutAsking();
										};
									});
								}
							});
							
							window.show();
						}
					}
				}
			}
			
		}); 
	}
}
