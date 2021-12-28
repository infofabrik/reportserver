package net.datenwerke.gxtdto.client.utilityservices.toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gxtdto.client.utilityservices.ext.HookableContainer;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.hooks.ToolbarBaseHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class DwHookableToolbar extends DwToolBar implements HookableContainer {

	private String toolbarName;

	@Inject
	private HookHandlerService hookHandler;
	
	private HashMap<String, String> hookConfig = new HashMap<String, String>();
	
	public DwHookableToolbar(){
		super();
	}
	
	@Override
	public String getContainerName() {
		return toolbarName;
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
		this.hookConfig = hookConfig;
	}

	@Override
	public void setContainerName(String toolbarName) {
		this.toolbarName = toolbarName;
	}

	public void addBaseHookersLeft(){
		for(ToolbarBaseHook hooker : getBaseHookers())
			hooker.attachToLeft(this);
	}
	
	public void addBaseHookersRight(){
		for(ToolbarBaseHook hooker : getBaseHookers())
			hooker.attachToRight(this);
	}
	
	private List<ToolbarBaseHook> getBaseHookers() {
		List<ToolbarBaseHook> baseHookers = new ArrayList<ToolbarBaseHook>();
		for(ToolbarBaseHook hooker : hookHandler.getHookers(ToolbarBaseHook.class))
			if(hooker.consumes(this))
				baseHookers.add(hooker);
		
		return baseHookers;
	}

	public void addFillAndBaseHookersRight(){
		addFill();
		addBaseHookersRight();
	}
}
