package net.datenwerke.rs.core.client.reportexecutor.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewFactory;

import com.google.inject.Provider;

/**
 * 
 *
 */
public class ReportViewHook extends ObjectHook<ReportViewFactory> {

	public ReportViewHook(ReportViewFactory factory) {
		super(factory);
	}
	
	public ReportViewHook(Provider<? extends ReportViewFactory> provider) {
		super(provider);
	}

	public ReportViewFactory getViewFactory(){
		return getObject();
	}
}
