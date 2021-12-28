package net.datenwerke.rs.teamspace.client.teamspace.hookers;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTeamSpace;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceDao;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHookImpl;

/**
 * 
 *
 */
public class UserProfileTeamSpacePropertiesHooker extends
		UserProfileCardProviderHookImpl {

	private static final String DEFAULT_TEAMSPACE_KEY = "_defaultTeamSpace";

	private final TeamSpaceDao tsDao;

	private SimpleForm form;
	
	@Inject
	public UserProfileTeamSpacePropertiesHooker(
		TeamSpaceDao tsDao
		){
		
		/* store objects */
		this.tsDao = tsDao;
	}
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.GROUP.toImageResource(1);
	}

	@Override
	public Widget getCard() {
		form = SimpleForm.getInlineInstance();
		form.addField(
			TeamSpaceDto.class, DEFAULT_TEAMSPACE_KEY, TeamSpaceMessages.INSTANCE.defaultTeamSpaceLabel(),
			new SFFCTeamSpace(){
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
		tsDao.getExplicitPrimarySpace(new RsAsyncCallback<TeamSpaceDto>(){
			@Override
			public void onSuccess(TeamSpaceDto result) {
				if(null != result)
					form.setValue(DEFAULT_TEAMSPACE_KEY, result);
				form.unmask();
			}
		});
		
		return form;
	}
	
	@Override
	public void submitPressed(final SubmitTrackerToken token) {
		TeamSpaceDto teamSpace = (TeamSpaceDto) form.getValue(DEFAULT_TEAMSPACE_KEY);
		tsDao.setPrimarySpace(teamSpace, new RsAsyncCallback(){
			@Override
			public void onSuccess(Object result) {
				token.setCompleted();
			}
		});
	}

	@Override
	public String getName() {
		return TeamSpaceMessages.INSTANCE.clientModuleName();
	}

}
