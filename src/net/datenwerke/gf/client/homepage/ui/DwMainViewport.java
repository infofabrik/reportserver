package net.datenwerke.gf.client.homepage.ui;


import java.util.Collection;

import net.datenwerke.gf.client.homepage.modules.ClientMainModule;
import net.datenwerke.gf.client.homepage.modules.ClientTempModule;
import net.datenwerke.gf.client.homepage.modules.ui.ModuleManagerModuleSelector;
import net.datenwerke.gf.client.homepage.modules.ui.ModuleManagerPanel;
import net.datenwerke.gf.client.theme.ThemeUiService;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.statusbar.StatusBarUIService;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * This LayoutContainer divides the screen in two parts. 
 * Header and main
 * 
 *
 */
@Singleton
public class DwMainViewport extends Viewport {

	@CssClassConstant
	public static final String CSS_NAME = "rs-viewport";
	
	final private HeaderPanel header;
	final private ModuleManagerPanel mainPanel;
	final private StatusBarUIService statusBarService;
	final private ThemeUiService themeService;
	
	private final ModuleManagerModuleSelector moduleSelector;
	
	@Inject
	public DwMainViewport(
		HeaderPanel header, 
		ModuleManagerPanel mainPanel,
		StatusBarUIService statusBarService,
		ThemeUiService themeService,
		ModuleManagerModuleSelector moduleSelector
	) {

		this.header = header;
		this.mainPanel = mainPanel;
		this.statusBarService = statusBarService;
		this.themeService = themeService;
		this.moduleSelector = moduleSelector;

		moduleSelector.setViewport(this);
		
		initializeUI();
		
		getElement().addClassName(getCssName());
	}
		
	public String getCssName() {
		return CSS_NAME;
	}

	private void initializeUI() {
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		
		container.add(header, new VerticalLayoutData(1, themeService.getThemeConfig().getHeaderHeight()));
		container.add(mainPanel, new VerticalLayoutData(1, 1));
		container.add(statusBarService.getStatusBarWidget(), new VerticalLayoutData(1, -1));
		
		statusBarService.setContainer(this);
		
		add(container);
	}
	
	public Widget getHeader() {
		return header;
	}

	public void addTempModule(final ClientTempModule module){
		moduleSelector.addTempModule(module);
	}
	

	public void removeTempModule(ClientTempModule module){
		moduleSelector.removeTempModule(module);
	}
	
	public Collection<ClientTempModule> getTempModules() {
		return moduleSelector.getTempModules();
	}

	public void setLoadingMask(){
		mask(BaseMessages.INSTANCE.loadingMsg());
	}

	public void showModule(ClientMainModule module) {
		moduleSelector.showModule(module);
	}
}
