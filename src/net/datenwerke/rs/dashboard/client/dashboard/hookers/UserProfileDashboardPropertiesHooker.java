package net.datenwerke.rs.dashboard.client.dashboard.hookers;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDashboard;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardDao;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHookImpl;

/**
 * 
 *
 */
public class UserProfileDashboardPropertiesHooker extends
		UserProfileCardProviderHookImpl {

	private static final String DEFAULT_DASHBOARD_KEY = "_defaultDashboard";

	private final DashboardDao dashboardDao;

	private SimpleForm form;
	
	@Inject
	public UserProfileDashboardPropertiesHooker(
		DashboardDao dashboardDao
		){
		
		/* store objects */
		this.dashboardDao = dashboardDao;
	}
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.DASHBOARD.toImageResource(1);
	}

	@Override
	public Widget getCard() {
		form = SimpleForm.getInlineInstance();
		form.addField(
			DashboardDto.class, DEFAULT_DASHBOARD_KEY, DashboardMessages.INSTANCE.defaultDashboardLabel(),
			new SFFCDashboard(){
				@Override
				public boolean isMulti() {
					return false;
				}

				@Override
				public boolean isLoadAll() {
					return false;
				}
			});
			
		form.loadFields();
		
		form.mask(BaseMessages.INSTANCE.loadingMsg());
		dashboardDao.getExplicitPrimaryDashboard(new RsAsyncCallback<DashboardDto>(){
			@Override
			public void onSuccess(DashboardDto result) {
				if(null != result)
					form.setValue(DEFAULT_DASHBOARD_KEY, result);
				form.unmask();
			}
		});
		
		return form;
	}
	
	@Override
	public void submitPressed(final SubmitTrackerToken token) {
		DashboardDto dashboard = (DashboardDto) form.getValue(DEFAULT_DASHBOARD_KEY);
		dashboardDao.setPrimaryDashboard(dashboard, new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				token.setCompleted();
			}
		});
	}

	@Override
	public String getName() {
		return DashboardMessages.INSTANCE.clientModuleName();
	}

}
