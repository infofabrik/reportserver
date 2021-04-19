package net.datenwerke.gf.client.managerhelper.hooks;

import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public abstract class TreePostSelectAsyncHook implements Hook {
	
	public abstract void postprocessNode(AbstractNodeDto selectedNode, List<TreePostSelectAsyncHook> next);
	
	public abstract boolean consumes(AbstractNodeDto node);
	
	protected void doNext(AbstractNodeDto selectedNode, List<TreePostSelectAsyncHook> next){
		if(null != next && !next.isEmpty()){
			next.get(0).postprocessNode(selectedNode, next.subList(1, next.size()));
		}
	}

}
