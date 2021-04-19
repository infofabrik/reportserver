package net.datenwerke.gf.client.homepage.modules.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.WidgetComponent;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;

import net.datenwerke.gf.client.history.ParentAwareSubmoduleHook;
import net.datenwerke.gf.client.history.ParentModule;
import net.datenwerke.gf.client.homepage.hooks.ClientMainModuleProviderHook;
import net.datenwerke.gf.client.homepage.modules.ClientMainModule;
import net.datenwerke.gf.client.homepage.modules.ClientTempModule;
import net.datenwerke.gf.client.homepage.ui.DwMainViewport;
import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;
import net.datenwerke.gxtdto.client.eventbus.handlers.SubmoduleDisplayRequestHandler;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

/**
 * 
 *
 */
@Singleton
public class ModuleManagerModuleSelector implements ClientModuleSelector, ParentModule {

	private static final String CSS_MODULE_TEXT = "rs-header-mod";
	private static final String CSS_FIRST_TEMP_MODULE = "rs-header-tmod-f";
	private static final String CSS_TEMP_MODULE_TEXT = "rs-header-tmod";
	private static final String CSS_TEMP_MODULE_ACTIVE = "rs-header-tmod-a";
	private static final String CSS_MODULE_ACTIVE = "rs-header-mod-a";
	private final ModuleManagerPanel mainPanel;
	private final HookHandlerService hookHandler;

	private List<ClientMainModule> modules;
	
	private Map<ClientMainModule, WidgetComponent> wrapperMap = new HashMap<ClientMainModule, WidgetComponent>();
	private Map<ClientMainModule, HTML> textMap = new HashMap<ClientMainModule, HTML>();

	final private Map<ClientTempModule, WidgetComponent> tempComponents = new HashMap<ClientTempModule, WidgetComponent>();
	
	private DwMainViewport viewport;

	private ClientMainModule lastModule;
	private HBoxLayoutContainer main;
	
	private int tempCnt = 0;
	private int tempPos;
	
	@Inject
	public ModuleManagerModuleSelector(
		ModuleManagerPanel mainPanel,
		HookHandlerService hookHandler, 
		EventBus eventBus
		){
		this.mainPanel = mainPanel;
		this.hookHandler = hookHandler;
		
		eventBus.addHandler(SubmoduleDisplayRequest.TYPE, new SubmoduleDisplayRequestHandler() {
			
			@Override
			public void onSubmoduleDisplayRequest(SubmoduleDisplayRequest event) {
				if(null == modules)
					return;
				for(ClientMainModule module : modules){
					if(module.getMainWidget() == event.getSubmodule()){
						selectModule(module);	
						return;
					}
				}
			}
		});
	}
	
	public void setViewport(DwMainViewport viewport) {
		this.viewport = viewport;
	}
	
	@Override
	public void moduleTextUpdated(ClientMainModule module, String text){
		HTML html = textMap.get(module);
		if(null != html)
			html.setHTML(text);
	}
	
	@Override
	public void moduleTooltipUpdated(ClientMainModule module, String text){
		WidgetComponent wrapper = wrapperMap.get(module);
		if(null != wrapper)
			if(null == text)
				wrapper.removeToolTip();
			else
				wrapper.setToolTip(text);
	}


	public void addModules(HBoxLayoutContainer main) {
		/* load menus */
		modules = new ArrayList<ClientMainModule>();
		
		/* add space */
		main.add(new Label(""), new BoxLayoutData(new Margins(0, 25, 0, 0)));

		/* load modules */
		for(ClientMainModuleProviderHook hook : hookHandler.getHookers(ClientMainModuleProviderHook.class)){
			modules.add(hook.getObject());
			if(hook instanceof ParentAwareSubmoduleHook){
				((ParentAwareSubmoduleHook)hook).registerParent(this);
			}
		}
		
		/* add modules */
		boolean bFirst = true;
		for(final ClientMainModule module : modules){
			module.setClientModuleSelector(this);
			
			HTML text = new HTML(module.getModuleName());
			textMap.put(module, text);
			text.addStyleName(CSS_MODULE_TEXT);
			
			WidgetComponent wrapper = new WidgetComponent(text);
			wrapperMap.put(module, wrapper);
			
			main.add(wrapper, new BoxLayoutData(new Margins(0, 15, 0, 0)));
			
			text.addDomHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					selectModule(module);					
				}
			}, ClickEvent.getType());
			
			if(bFirst){
				bFirst = false;
				
				/* select first module */
				selectModule(module);
			}
		}
		
		this.tempPos = main.getWidgetCount();
		this.main = main;
	}
	
	
	public void addTempModule(final ClientTempModule module) {
		if(tempComponents.containsKey(module)){
			selectTempModule(module);
			return;
		}
		
		module.setViewport(viewport);
		
		HTML text = new HTML(module.getModuleName());
		text.addStyleName(CSS_TEMP_MODULE_TEXT);
		
		if(tempCnt == 0)
			text.addStyleName(CSS_FIRST_TEMP_MODULE);

		WidgetComponent wrapper = new WidgetComponent(text);
		tempComponents.put(module, wrapper);

		text.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				selectTempModule(module);
			}
		}, ClickEvent.getType());
		
		text.addDomHandler(new MouseOverHandler(){
			@Override
			public void onMouseOver(MouseOverEvent event) {
				module.onMouseOver(event);
			}
		}, MouseOverEvent.getType());
		
		selectTempModule(module);
		
		tempCnt++;
		
		main.insert(wrapper, tempPos + tempCnt - 1, new BoxLayoutData(new Margins(0,15,0,0)));
		main.forceLayout();
	}
	

	private void selectTempModule(ClientTempModule module) {
		deselectAll();
		mainPanel.displayModule(module);
		
		tempComponents.get(module).addStyleName(CSS_TEMP_MODULE_ACTIVE);
	}
	
	public void removeTempModule(ClientTempModule module) {
		if(tempComponents.containsKey(module)){
			boolean success = main.remove(tempComponents.remove(module));
			if(success)
				tempCnt--;
		}

		mainPanel.removeModule(module);
		selectLastModule();
	}
	

	public Collection<ClientTempModule> getTempModules() {
		return tempComponents.keySet();
	}

	public void deselectAll() {
		for(WidgetComponent w : wrapperMap.values())
			w.removeStyleName(CSS_MODULE_ACTIVE);
		for(WidgetComponent w : tempComponents.values())
			w.removeStyleName(CSS_TEMP_MODULE_ACTIVE);
	}
	
	private void selectModule(ClientMainModule module){
		lastModule = module;
		
		deselectAll();
		wrapperMap.get(module).addStyleName(CSS_MODULE_ACTIVE);
		mainPanel.displayModule(module);
		module.selected();
	}
	
	
	public void selectModule(int i){
		selectModule(modules.get(i));
	}
	
	public void selectLastModule(){
		if(null == lastModule)
			selectModule(0);
		else
			selectModule(lastModule);
	}
	
	@Override
	public void showSubmodule(Object o) {
		if(o instanceof ClientMainModule && modules.contains(o)){
			selectModule((ClientMainModule) o);
		}
	}

	public void showModule(ClientMainModule module) {
		for(int i = 0; i < modules.size(); i++)
			if(module == modules.get(i)){
				selectModule(modules.get(i));
				break;
			}
				
	}

}
