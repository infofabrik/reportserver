package net.datenwerke.gxtdto.client.utilityservices.toolbar;

import com.sencha.gxt.widget.core.client.button.ButtonBar;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;

public class DwButtonBar extends ButtonBar {
	
	@CssClassConstant
	public static final String CSS_NAME = "rs-bbar";
	
	public DwButtonBar(){
		super();
		
		getElement().addClassName(getCssName());
	}

	public String getCssName() {
		return CSS_NAME;
	}

}
