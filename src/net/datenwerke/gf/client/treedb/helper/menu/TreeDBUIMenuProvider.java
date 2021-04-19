package net.datenwerke.gf.client.treedb.helper.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.utilityservices.menu.DwHookableMenu;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class TreeDBUIMenuProvider {

	final private Map<Class<?>, Menu> menuMapping = new HashMap<Class<?>, Menu>();
	
	private String menuName;
	
	private final Provider<DwHookableMenu> dwMenuProvider;
	
	@Inject
	public TreeDBUIMenuProvider(
		Provider<DwHookableMenu> dwMenuProvider
		){
		
		/* store objects */
		this.dwMenuProvider = dwMenuProvider;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public Menu createOrGetMenuFor(Class<?> nodeType){
		Menu menu = menuMapping.get(nodeType);
		if(null != menu)
			return menu;
		
		DwHookableMenu newMenu = dwMenuProvider.get();
		newMenu.setContainerName(menuName);
		addMenu(nodeType, newMenu);
		return newMenu;
	}
	
	public void addMenu(Class<?> nodeType, Menu menu){
		menuMapping.put(nodeType, menu);
	}
	
	public Menu getMenuFor(Class<?> nodeType){
		while(null != nodeType){
			if(null != menuMapping.get(nodeType))
				return menuMapping.get(nodeType);
			nodeType = nodeType.getSuperclass();
		}
		
		return null;
	}
	
	public void initializeMenus(UITree tree){
		for(Entry<Class<?>,Menu> e : menuMapping.entrySet())
			initializeMenu(e.getKey(), e.getValue(), tree);
	}

	private void initializeMenu(Class<?> type, Menu menu, UITree tree) {
		for(Widget item : menu){
			if(item instanceof TreeMenuItem)
				((TreeMenuItem)item).setTree(tree);
			if(item instanceof MenuItem && null != ((MenuItem)item).getSubMenu() )
				initializeMenu(type, ((MenuItem)item).getSubMenu(), tree);
		}
		
		if(menu instanceof DwHookableMenu){
			((DwHookableMenu)menu).getHookConfig().put("classname", type.getName());
			((DwHookableMenu)menu).addSeparatorAndBaseHookers();
		}
	}
}
