package net.datenwerke.gf.client.upload;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Hidden;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FileUploadField;

public class HtmlUploadFieldContainer extends HtmlLayoutContainer {

	private FileUploadField baseField;
	private Hidden nameField;

	public HtmlUploadFieldContainer(SafeHtml html) {
		super(html);
	}

	public void setNameField(Hidden xhrNameField) {
		this.nameField=xhrNameField;
	}

	public void setBaseField(FileUploadField field) {
		this.baseField = field;
	}
	
	public String getUploadFileName(){
		String name = nameField.getValue();
		if(null == name || "".equals(name.trim()))
			name = baseField.getValue();
		return null != name && ! "".equals(name.trim()) ? name : null;
	}
	

}
