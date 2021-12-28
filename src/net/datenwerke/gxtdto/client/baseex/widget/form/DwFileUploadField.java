package net.datenwerke.gxtdto.client.baseex.widget.form;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.FileUploadField;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;

public class DwFileUploadField extends FileUploadField {

	public DwFileUploadField(){
		super();
		TextButton button = getInputButton(this);
		
		button.getElement().addClassName(DwTextButton.CSS_NAME);
		button.getCell().getAppearance().getButtonElement(getElement()).addClassName(DwTextButton.CSS_BODY_NAME);
	}
	
	/* resort to violator pattern */
	protected native TextButton getInputButton(FileUploadField field) /*-{
	  return field.@com.sencha.gxt.widget.core.client.form.FileUploadField::button;
	}-*/;
}
