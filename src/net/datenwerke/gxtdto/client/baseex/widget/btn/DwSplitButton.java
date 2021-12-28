package net.datenwerke.gxtdto.client.baseex.widget.btn;

import com.sencha.gxt.widget.core.client.button.SplitButton;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DwSplitButton extends SplitButton {

	@CssClassConstant
	public static final String CSS_NAME = "rs-btn rs-btn-splt";

	@CssClassConstant
	public static final String CSS_BODY_NAME = "rs-btn-body";
	
	public DwSplitButton() {
		super();
		initCss();
	}
	
	public DwSplitButton(String label) {
		super(label);
		initCss();
	}
	
	private void initCss() {
		getElement().addClassName(getCssName());
		getCell().getAppearance().getButtonElement(getElement()).addClassName(getCssBodyName());
	}

	public String getCssName() {
		return CSS_NAME;
	}
	
	public String getCssBodyName() {
		return CSS_BODY_NAME;
	}

	public void setIcon(BaseIcon icon) {
		super.setIcon(icon.toImageResource());
	}
	
}
