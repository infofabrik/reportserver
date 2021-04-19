package net.datenwerke.gxtdto.client.baseex.widget.layout;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;

import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class DwBorderContainer extends BorderLayoutContainer {

	@CssClassConstant
	public static final String CSS_NAME = "rs-blc";
	
	public DwBorderContainer(){
		super();
		
		getElement().addClassName(getCssName());
	}

	public String getCssName() {
		return CSS_NAME;
	}
	
	public void addClassName(String name){
		getElement().addClassName(name);
	}
	
	@Override
	public void doLayout() {
		super.doLayout();
	}
}
