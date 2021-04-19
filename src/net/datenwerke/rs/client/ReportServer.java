package net.datenwerke.rs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.GXT;

import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ReportServer implements EntryPoint {

	private final RSGinjector ginjector = GWT.create(RSGinjector.class);
	
	public void onModuleLoad() {
		GXT.setUseShims(true);
		
		/* ext exception swallow problem */
		/* http://code.google.com/p/gwt-ext/issues/detail?id=424 */
		if (!GWT.isScript()) 
		      GWT.setUncaughtExceptionHandler(e -> new DetailErrorDialog(e));
		
		ginjector.getDispatcherService().dispatch();
	}
}
