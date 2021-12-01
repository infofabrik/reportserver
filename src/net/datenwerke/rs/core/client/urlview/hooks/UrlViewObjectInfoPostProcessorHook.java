package net.datenwerke.rs.core.client.urlview.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface UrlViewObjectInfoPostProcessorHook extends Hook {

	String postProcess(String[] conf, String url, Object object);

}
