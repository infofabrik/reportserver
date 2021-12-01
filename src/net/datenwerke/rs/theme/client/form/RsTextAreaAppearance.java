package net.datenwerke.rs.theme.client.form;

import com.google.gwt.dom.client.Element;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.theme.neptune.client.base.field.Css3TextAreaAppearance;

public class RsTextAreaAppearance extends Css3TextAreaAppearance {

	@Override
	public void onFocus(Element parent, boolean focus) {
		parent.<XElement>cast().setClassName(RsTextFieldAppearance.CSS_FOCUS, focus);
	}
}
