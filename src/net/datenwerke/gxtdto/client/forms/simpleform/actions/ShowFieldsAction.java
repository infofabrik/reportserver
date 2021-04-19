package net.datenwerke.gxtdto.client.forms.simpleform.actions;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

import com.google.gwt.user.client.ui.Widget;

public class ShowFieldsAction implements SimpleFormAction {

	
	private final String[] toHide;
	private final String[] toShow;

	public ShowFieldsAction(String[] toHide, String[] toShow){
		this.toHide = toHide;
		this.toShow = toShow;
	}


	public void onSuccess(SimpleForm form) {
		hideFields(form, toHide);
		showFields(form, toShow);
	}
	
	public void onFailure(SimpleForm form) {
	}

	protected void hideFields(SimpleForm form, String... allFields) {
		for(String key : allFields){
			Widget field = form.getDisplayedField(key);
			if(null == field)
				continue;
			
			field.setVisible(false);
		}
		
		form.updateFormLayout();
	}
	
	protected void showFields(SimpleForm form, String... allFields) {
		for(String key : allFields){
			Widget field = form.getDisplayedField(key);
			if(null == field)
				continue;

			field.setVisible(true);
		}
		
		form.updateFormLayout();
	}
	

}
