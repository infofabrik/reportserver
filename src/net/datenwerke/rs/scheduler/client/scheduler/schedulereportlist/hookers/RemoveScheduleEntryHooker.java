package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hookers;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.SchedulerAdminModule;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListDetailToolbarHook;
import net.datenwerke.rs.scheduler.client.scheduler.security.SchedulingAdminViewGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class RemoveScheduleEntryHooker implements ScheduledReportListDetailToolbarHook {
	
	private final SchedulerDao schedulerDao;
	private final LoginService loginService;
	private final ToolbarService toolbarService;
	private final SecurityUIService securityService;

	@Inject
	public RemoveScheduleEntryHooker(
		SchedulerDao schedulerDao,
		LoginService loginService,
		SecurityUIService securityService,
		ToolbarService toolbarService
		) {
		
		/* store objects */
		this.schedulerDao = schedulerDao;
		this.loginService = loginService;
		this.securityService = securityService;
		this.toolbarService = toolbarService;
	}

	@Override
	public void statusBarToolbarHook_addLeft(ToolBar toolbar,
			final ReportScheduleJobListInformation info,
			ReportScheduleJobInformation detailInfo,
			final ScheduledReportListPanel reportListPanel) {
		
	}

	@Override
	public void statusBarToolbarHook_addRight(ToolBar toolbar,
		final	ReportScheduleJobListInformation info,
			ReportScheduleJobInformation detailInfo,
			final ScheduledReportListPanel reportListPanel) {

		/* only for selected user */
		UserDto user = loginService.getCurrentUser();
		if(! detailInfo.isOwner(user) && ! user.isSuperUser()) {
			/* If we are in the admin-panel: we check for scheduling-admin rights. */
			if (reportListPanel.getName().equals(SchedulerAdminModule.ADMIN_FILTER_PANEL)) {
				if(! securityService.hasRight(SchedulingAdminViewGenericTargetIdentifier.class, ExecuteDto.class)) {
					return;
				}
			} else {
				/* We are not in the admin panel. */
				return;
			}
		}
		
		TextButton removeBtn;
		if(securityService.hasRight(SchedulingAdminViewGenericTargetIdentifier.class, ExecuteDto.class)){
			removeBtn = new DwSplitButton(SchedulerMessages.INSTANCE.archiveScheduledJobLabel());
			((DwSplitButton)removeBtn).setIcon(BaseIcon.ARCHIVE);
			Menu menu = new DwMenu();
			removeBtn.setMenu(menu);
			
			MenuItem deleteItem = new DwMenuItem(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
			menu.add(deleteItem);
			deleteItem.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					schedulerDao.remove(info.getJobId(), new RsAsyncCallback<Boolean>(){
						@Override
						public void onSuccess(Boolean result) {
							if(result)
								reportListPanel.reload();
							else 
								new MessageBox(SchedulerMessages.INSTANCE.error(), SchedulerMessages.INSTANCE.couldNotRemoveJob()).show();
						}
					});
				}
			});
		} else 
			removeBtn = toolbarService.createSmallButtonLeft(SchedulerMessages.INSTANCE.archiveScheduledJobLabel(), BaseIcon.ARCHIVE);
		
		
		removeBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				schedulerDao.unschedule(info.getJobId(), new RsAsyncCallback<Boolean>(){
					@Override
					public void onSuccess(Boolean result) {
						if(result)
							reportListPanel.reload();
						else 
							new MessageBox(SchedulerMessages.INSTANCE.error(), SchedulerMessages.INSTANCE.couldNotRemoveJob()).show();
					}
				});
			}
		});
		
		
		
		toolbar.add(removeBtn);
		
	}

}
