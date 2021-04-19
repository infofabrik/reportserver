package net.datenwerke.gf.service.history.hooks;

import java.util.List;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface HistoryUrlBuilderHook extends Hook {

	public List<HistoryLink> buildLinksFor(Object o);
	
	public boolean consumes(Object o);
}
