package net.datenwerke.gxtdto.client.objectinformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelAppearance;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelBottomAppearance;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwTabPanel;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabKeyInfoProvider;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

/**
 * Creates a component with information about a given object.
 * 
 *
 */
public class ObjectPreviewTabPanel extends DwContentPanel {

	private final HookHandlerService hookHandler;

	private DwTabPanel tabPanel;
	
	private boolean bottomTabs = false;

	private Map<TabItemConfig, ObjectPreviewTabProviderHook.PreviewComponent> infoMap;
	private Map<TabItemConfig, Object> objMap;
	
	@Inject
	public ObjectPreviewTabPanel(
		HookHandlerService hookHandler	
		){
	
		/* store objects */
		this.hookHandler = hookHandler;
		this.setHeaderVisible(false);
	}
	
	public void setBottomTabs(boolean bottomTabs) {
		this.bottomTabs = bottomTabs;
	}

	public void displayInformationOn(final Object object){
		/* clear */
		clear();
		
		if(bottomTabs) {
			tabPanel = new DwTabPanel(GWT.<TabPanelAppearance> create(TabPanelBottomAppearance.class));
		} else
			tabPanel = new DwTabPanel();
		setWidget(tabPanel);
		
		infoMap = new HashMap<TabItemConfig, ObjectPreviewTabProviderHook.PreviewComponent>();
		objMap = new HashMap<TabItemConfig, Object>();
		
		
		tabPanel.addSelectionHandler(new SelectionHandler<Widget>() {
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				DwContentPanel widget = (DwContentPanel) event.getSelectedItem();
				TabItemConfig config = tabPanel.getConfig(widget);
				
				if(widget.getWidgetCount() == 0 && infoMap.containsKey(config) && objMap.containsKey(config)){
					widget.add(infoMap.get(config).getInfoComponent(objMap.get(config)));
					forceLayout();
				}
			}
		});
		
		/* get primary info */
		final ObjectPreviewTabKeyInfoProvider primaryInfo = getPrimaryInfo(object);
		if(null != primaryInfo){
			List<ObjectPreviewTabProviderHook.PreviewComponent> infoComponents = primaryInfo.getInfoComponents(object);
			if(null != infoComponents)
				for(ObjectPreviewTabProviderHook.PreviewComponent infoComp : infoComponents)
					addInfo(object, infoComp);
		}
		
		/* create list of objects */
		List<Object> objectList = new ArrayList<Object>();
		objectList.add(object);
		if(null != primaryInfo){
			Collection<?> subObjects = primaryInfo.getSubtypes(object);
			if(null != subObjects)
				objectList.addAll(subObjects);
		}
		
		/* add others */
		for(Object currentObejct : objectList){
			for(ObjectPreviewTabProviderHook infoProvider : hookHandler.getHookers(ObjectPreviewTabProviderHook.class)){
				if(! (infoProvider instanceof ObjectPreviewTabKeyInfoProvider) && infoProvider.consumes(currentObejct)){
					List<ObjectPreviewTabProviderHook.PreviewComponent> infoComponents = infoProvider.getInfoComponents(object);
					if(null != infoComponents)
						for(ObjectPreviewTabProviderHook.PreviewComponent infoComp : infoComponents)
							addInfo(object, infoComp);
				}
			}
		}
		
		/* set panel to primary info provider */
		if(null != primaryInfo)
			primaryInfo.setInfoPanel(ObjectPreviewTabPanel.this, object);
	}
	
	public void addInfoFor(Object object){
		for(ObjectPreviewTabProviderHook infoProvider : hookHandler.getHookers(ObjectPreviewTabProviderHook.class)){
			if(! (infoProvider instanceof ObjectPreviewTabKeyInfoProvider) && infoProvider.consumes(object)){
				List<ObjectPreviewTabProviderHook.PreviewComponent> infoComponents = infoProvider.getInfoComponents(object);
				if(null != infoComponents)
					for(ObjectPreviewTabProviderHook.PreviewComponent infoComp : infoComponents)
						addInfo(object, infoComp);
			}
		}
	}

	private void addInfo(final Object object, final ObjectPreviewTabProviderHook.PreviewComponent infoComp) {
		TabItemConfig config = new TabItemConfig();
		config.setText(infoComp.getInfoName());
		
		DwContentPanel wrapper = DwContentPanel.newInlineInstance();
		tabPanel.add(wrapper, config);
		infoMap.put(config, infoComp);
		objMap.put(config, object);
		
		if(tabPanel.getWidgetCount() == 1)
			wrapper.setWidget(infoComp.getInfoComponent(object));

	}

	private ObjectPreviewTabKeyInfoProvider getPrimaryInfo(Object object) {
		for(ObjectPreviewTabProviderHook infoProvider : hookHandler.getHookers(ObjectPreviewTabProviderHook.class))
			if(infoProvider instanceof ObjectPreviewTabKeyInfoProvider)
				if(infoProvider.consumes(object))
					return (ObjectPreviewTabKeyInfoProvider) infoProvider;
 		return null;
	}
	
	public TabItemConfig getConfig(Widget widget){
		return tabPanel.getConfig(widget);
	}

	public boolean hasItems(){
		return tabPanel.getWidgetCount() > 0;
	}
}
