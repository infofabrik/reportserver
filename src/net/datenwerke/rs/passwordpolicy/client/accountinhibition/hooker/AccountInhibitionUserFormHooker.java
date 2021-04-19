package net.datenwerke.rs.passwordpolicy.client.accountinhibition.hooker;

import java.util.Date;

import net.datenwerke.gf.client.managerhelper.hooks.SimpleFormViewHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDate;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionConfiguration;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionServiceDao;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.pa.AccountInhibitionConfigurationPa;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;
import net.datenwerke.security.ext.client.usermanager.ui.forms.UserForm;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

public class AccountInhibitionUserFormHooker extends SimpleFormViewHook{

	private AccountInhibitionServiceDao accountInhibitionDao;
	private FormatUiHelper formatUiHelper;
	
	@Inject
	public AccountInhibitionUserFormHooker(
		AccountInhibitionServiceDao accountInhibitionDao, 
		FormatUiHelper formatUiHelper
		) {
		super(UserForm.class);

		/* store objects */
		this.accountInhibitionDao = accountInhibitionDao;
		this.formatUiHelper = formatUiHelper;
	}
	
	@Override
	public void configureSimpleForm(final SimpleForm inhibitForm, final AbstractNodeDto selectedNode) {
		inhibitForm.setHeight(100);

		inhibitForm.setFieldWidth(300);
		inhibitForm.setLabelAlign(LabelAlign.LEFT);
		inhibitForm.setLabelWidth(150);
		
		final String isInhibitedKey = inhibitForm.addField(Boolean.class, AccountInhibitionConfigurationPa.INSTANCE.inhibitionState(), SecurityMessages.INSTANCE.accountInhibitionLabel());
		
		final String expirationKey = inhibitForm.addField(Date.class, AccountInhibitionConfigurationPa.INSTANCE.expirationDate(), SecurityMessages.INSTANCE.accountExpirationDate(), new SFFCDate(){

			@Override
			public Mode getMode() {
				return Mode.Date;
			}

			@Override
			public String getDatePattern() {
				return formatUiHelper.getShortDateFormat().getPattern();
			}

			@Override
			public String getTimePattern() {
				return formatUiHelper.getShortTimeFormat().getPattern();
			}
			
		});
		
		final SeparatorTextLabel noticeLabel = SeparatorTextLabel.createSmallText("");
		inhibitForm.addField(CustomComponent.class, new SFFCCustomComponent(){
			@Override
			public Widget getComponent() {
				return noticeLabel;
			}
			
		});

		
		inhibitForm.addSubmissionCallback(new SimpleFormSubmissionCallback(inhibitForm) {
			
			@Override
			public void formSubmitted() {
				AccountInhibitionConfiguration config = new AccountInhibitionConfiguration();

				config.setUserId(selectedNode.getId());
				config.setExpirationDate((Date) inhibitForm.getValue(expirationKey));
				config.setInhibitionState(Boolean.TRUE.equals(inhibitForm.getValue(isInhibitedKey)));
				
				accountInhibitionDao.applyAccountInhibitionConfiguration(
						config, 
						new RsAsyncCallback<Void>(){
							@Override
							public void onSuccess(Void result) {
								cbSuccess();
							}
							
							@Override
							public void onFailure(Throwable caught) {
								cbFailure(caught);
							}
						});
			}
		});
		
		if(selectedNode instanceof UserDto){
			accountInhibitionDao.getAccountInhibitionConfiguration((UserDto)selectedNode, new RsAsyncCallback<AccountInhibitionConfiguration>(){
				@Override
				public void onSuccess(AccountInhibitionConfiguration result) {
					if(Boolean.TRUE.equals(result.isBlockedTemporarily())){
						noticeLabel.setText(SecurityMessages.INSTANCE.blockedTemporarily());
					}
					inhibitForm.setValue(expirationKey, result.getExpirationDate());
					inhibitForm.setValue(isInhibitedKey, Boolean.TRUE.equals(result.isInhibitionState()));
				}
			});
		}
		
		inhibitForm.loadFields();
	}

}
