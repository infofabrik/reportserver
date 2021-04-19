package net.datenwerke.gf.service.history;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;

public class HistoryServiceImpl implements HistoryService{

	private final HookHandlerService  hookHandler;
	
	@Inject
	public HistoryServiceImpl(
			HookHandlerService hookHandler
	) {
		
		this.hookHandler = hookHandler;
		
	}
	
	@Override
	public List<HistoryLink> buildLinksFor(Object o) {
		ArrayList<HistoryLink> links = new ArrayList<HistoryLink>();
		
		for(HistoryUrlBuilderHook hooker : hookHandler.getHookers(HistoryUrlBuilderHook.class)){
			if(hooker.consumes(o)){
				links.addAll(hooker.buildLinksFor(o));
			}
		}
		
		return links;
	}

}
