package net.datenwerke.rs.core.client.reportexecutor.ui.preview.hookers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.userprofile.UserProfileViewContentHook;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;

public class PdfPreviewUserProfileViewContentHooker implements UserProfileViewContentHook {

	private ReportExecutorDao reportExecutorDao;
	private SimpleForm form;
	private String fKey;
	
	@Inject
	public PdfPreviewUserProfileViewContentHooker(ReportExecutorDao reportExecutorDao) {
		this.reportExecutorDao = reportExecutorDao;
	}

	@Override
	public void submitPressed(final SubmitTrackerToken token) {
		final RsAsyncCallback<Void> cb = new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				token.setCompleted();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				token.failure(caught);
			}
		};
		
		reportExecutorDao.setPreviewModeUserProperty(String.valueOf(form.getValue(fKey)), cb); 
	}

	@Override
	public Widget getComponent(UserDto user) {
		form = SimpleForm.getInlineInstance();

		form.setLabelWidth(150);

		fKey = form.addField(List.class, ReportexecutorMessages.INSTANCE.forceLegacyPdfPreview(), new SFFCStaticDropdownList<String>() {

			@Override
			public Map<String, String> getValues() {
				HashMap<String, String> res = new HashMap<String, String>();
				res.put("default", "default");
				res.put("jsviewer", "jsviewer");
				res.put("native", "native");
				res.put("image", "image");
				
				return res;
			}
		});

		String prop = ((UserDtoDec)user).getUserPropertyValue("preview:mode");
		if(null == prop)
			prop = "default";

		form.setValue(fKey, prop);

		form.loadFields();

		return form;
	}

}
