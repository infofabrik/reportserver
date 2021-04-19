package net.datenwerke.security.ext.client.password.ui;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

public class ChangePasswordDialog extends DwWindow {

	private PasswordField oldPassword;
	private PasswordField newPassword;
	private PasswordField repeatPassword;
	
	private DwTextButton submitBtn;
	private DwTextButton cancelBtn;
	
	public ChangePasswordDialog(boolean canCancel) {
		setClosable(false);
		if(canCancel)
			cancelBtn = addCancelButton();
		
		submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		addButton(submitBtn);
		
		setHeading(SecurityMessages.INSTANCE.changePasswordTitle());
		setModal(true);
		setWidth(320);
		setHeight(170);
		
		VerticalLayoutContainer fieldWrapper = new VerticalLayoutContainer();
		add(fieldWrapper, new MarginData(10));
		
		oldPassword = new PasswordField();
		oldPassword.setWidth(150);
		
		newPassword = new PasswordField();
		newPassword.setWidth(150);
		
		repeatPassword = new PasswordField();
		repeatPassword.setWidth(150);
		repeatPassword.setAutoValidate(true);
		repeatPassword.addValidator(new Validator<String>() {
			
			@Override
			public List<EditorError> validate(Editor<String> editor, String value) {
				if(value.equals(newPassword.getValue())){
					return null;
				}

				List<EditorError> errors = new ArrayList<EditorError>();
				errors.add(new DefaultEditorError(editor, SecurityMessages.INSTANCE.passwordsDontMatch(), value));
				return errors;
			}
		});
		
		fieldWrapper.add(new FieldLabel(oldPassword, SecurityMessages.INSTANCE.oldPassword()));
		fieldWrapper.add(new FieldLabel(newPassword, SecurityMessages.INSTANCE.newPassword()));
		fieldWrapper.add(new FieldLabel(repeatPassword, SecurityMessages.INSTANCE.repeatPassword()));
		
		forceLayout();
	}

	private String noNull(String s){
		return (null==s)?"":s;
	}
	
	public String getOldPassword() {
		return noNull(oldPassword.getValue());
	}


	public String getNewPassword() {
		return noNull(newPassword.getValue());
	}

	public String getRepeatPassword() {
		return noNull(repeatPassword.getValue());
	}
	
	public void addSubmitHandler(final SelectHandler handler) {
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(repeatPassword.isValid())
					handler.onSelect(event);
			}
		});
	}
	
	public void addCancelHandler(SelectHandler handler) {
		if(null != cancelBtn)
			cancelBtn.addSelectHandler(handler);
	}
}
