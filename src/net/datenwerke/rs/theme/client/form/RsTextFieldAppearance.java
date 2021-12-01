package net.datenwerke.rs.theme.client.form;

import com.google.gwt.dom.client.Element;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.theme.neptune.client.base.field.Css3TextFieldAppearance;

public class RsTextFieldAppearance extends Css3TextFieldAppearance {

	public static final String CSS_FOCUS = "rs-focus";
	
	@Override
	public void onFocus(Element parent, boolean focus) {
		parent.<XElement>cast().setClassName(CSS_FOCUS, focus);
	}
}
