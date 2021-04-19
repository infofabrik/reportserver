package net.datenwerke.gxtdto.client.baseex.widget.btn;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.sencha.gxt.cell.core.client.form.ToggleButtonCell;
import com.sencha.gxt.widget.core.client.button.ToggleButton;

public class DwToggleButton extends ToggleButton {

	@CssClassConstant
	public static final String CSS_NAME = "rs-btn";

	@CssClassConstant
	public static final String CSS_BODY_NAME = "rs-btn-body";
	
	public DwToggleButton() {
		super();
		initCss();
	}
	
	public DwToggleButton(String label) {
		super(label);
		initCss();
	}

	public DwToggleButton(ToggleButtonCell cell) {
		super(cell);
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
