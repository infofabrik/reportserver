package net.datenwerke.gf.client.homepage.ui;

import java.util.List;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gf.client.homepage.hooks.HomepageHeaderContentHook;
import net.datenwerke.gf.client.homepage.modules.ui.ModuleManagerModuleSelector;
import net.datenwerke.gf.client.theme.ThemeUiService;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

/**
 * A panel divided into 3 columns
 * 
 *
 */
public class HeaderPanel extends DwContentPanel {

	@CssClassConstant
	public static final String CSS_NAME = "rs-header";
	
	@CssClassConstant
	public static final String CSS_HEADER_SEP = "rs-header-r-sep";
	
	@CssClassConstant
	public static final String CSS_HEADER_TEXT = "rs-header-r-text";
	
	@CssClassConstant
	public static final String CSS_LOGO_NAME = "rs-logo";
	
	private final HookHandlerService hookHandler;
	private final ModuleManagerModuleSelector moduleSelector;

	private ThemeUiService themeService;
	
	@Inject
	public HeaderPanel(
		HookHandlerService hookHandler,
		ModuleManagerModuleSelector moduleSelector,
		ThemeUiService themeService
		) {
		
		/* store objects */
		this.hookHandler = hookHandler;
		this.moduleSelector = moduleSelector;
		this.themeService = themeService;
		
		initializeUI();
	}
	
	@Override
	public String getCssName() {
		return super.getCssName() + " " + CSS_NAME;
	}
	
	public static String getCssLogoName() {
		return CSS_LOGO_NAME;
	}
	
	private void initializeUI() {
		setHeaderVisible(false);
		setBodyBorder(false);
		setBorders(false);
		
		HBoxLayoutContainer container = new HBoxLayoutContainer();
		container.setEnableOverflow(false);
		container.setPack(BoxLayoutPack.START);
		container.setPadding(new Padding(0, 15, 0, 15));
		container.setAllowTextSelection(false);
		container.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		add(container);
		
		HTML logo = themeService.getHeaderLogo();
		container.add(logo);
		
		/* fill ponels */ 
		List<HomepageHeaderContentHook> hookers = hookHandler.getHookers(HomepageHeaderContentHook.class);
		moduleSelector.addModules(container);
		
		container.add(new FillToolItem());

		boolean first = true;
		for(HomepageHeaderContentHook hooker : hookers){
			final DwMainViewportTopBarElement tbElement = hooker.homepageHeaderContentHook_addTopRight(container);
			if(null == tbElement)
				continue;
			
			if(first)
				first = false;
			else {
				Label sep = new Label("|");
				sep.setStyleName(CSS_HEADER_SEP);
				container.add(sep, new BoxLayoutData(new Margins(0, 5, 0, 5)));
			}
			
			ClickHandler elClickHandler = event -> tbElement.onClick();
			
			if(tbElement instanceof DwMainViewportTopBarWidget){
				container.add(((DwMainViewportTopBarWidget)tbElement).getComponent());
			} else {
				String textLabel = tbElement.getName();
				
				Label text = new Label(textLabel);
				text.addStyleName(CSS_HEADER_TEXT);
				text.getElement().getStyle().setCursor(Cursor.POINTER);
				text.addDomHandler(elClickHandler, ClickEvent.getType());
				container.add(text);
			}
		}
	}


}
