package net.datenwerke.gxtdto.client.utilityservices.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.datenwerke.gxtdto.client.utilityservices.ext.HookableContainer;
import net.datenwerke.gxtdto.client.utilityservices.menu.hooks.MenuBaseHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

public class DwHookableMenu extends Menu implements HookableContainer {
	
	private String menuName;

	@Inject
	private HookHandlerService hookHandler;
	
	private HashMap<String, String> hookConfig = new HashMap<String, String>();
	
	@Override
	public String getContainerName() {
		return menuName;
	}
	
	public void addFill(){
		add(new FillToolItem());
	}
	
	@Override
	public HashMap<String, String> getHookConfig() {
		return hookConfig;
	}

	@Override
	public void setHookConfig(HashMap<String, String> hookConfig) {
		if(null == hookConfig)
			hookConfig = new HashMap<String, String>();
		this.hookConfig = hookConfig;
	}

	@Override
	public void setContainerName(String name) {
		this.menuName = name;
	}

	public void addSeparatorAndBaseHookers(){
		addBaseHookers(true);
	}
	
	public void addBaseHookers(boolean addSeparator){
		for(MenuBaseHook hooker : getBaseHookers())
			addSeparator = hooker.attachTo(this, addSeparator);
	}
	
	private List<MenuBaseHook> getBaseHookers() {
		List<MenuBaseHook> baseHookers = new ArrayList<MenuBaseHook>();
		for(MenuBaseHook hooker : hookHandler.getHookers(MenuBaseHook.class))
			if(hooker.consumes(this))
				baseHookers.add(hooker);
		
		return baseHookers;
	}
}
