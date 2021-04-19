package net.datenwerke.gxtdto.client.utilityservices.toolbar;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class DwToolBar extends ToolBar {
	
	@CssClassConstant
	public static final String CSS_NAME = "rs-tbar";
	
	public DwToolBar(){
		super();
		
		getElement().addClassName(getCssName());
	}

	public String getCssName() {
		return CSS_NAME;
	}

	public void addClassName(String className) {
		getElement().addClassName(className);
	}

}
