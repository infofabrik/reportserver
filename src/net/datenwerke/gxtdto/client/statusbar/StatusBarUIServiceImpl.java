package net.datenwerke.gxtdto.client.statusbar;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Viewport;

import net.datenwerke.gxtdto.client.statusbar.ui.StatusBarWidget;

/**
 * 
 *
 */
public class StatusBarUIServiceImpl implements StatusBarUIService {

	final private Provider<StatusBarWidget> statusBarWidgetProvider;
	
	@Inject
	public StatusBarUIServiceImpl(
		Provider<StatusBarWidget> statusBarWidgetProvider
		){
		
		this.statusBarWidgetProvider = statusBarWidgetProvider;
	}
	
	@Override
	public StatusBarWidget getStatusBarWidget(){
		return statusBarWidgetProvider.get();
	}
	
	@Override
	public void clearLeft() {
		getStatusBarWidget().clearLeft();
	}
	
	@Override
	public void clearRight() {
		getStatusBarWidget().clearRight();
	}
	
	@Override
	public void addRight(String text, String icon){
		getStatusBarWidget().addRight(text, icon);
	}
	
	@Override
	public void addLeft(String text, String icon){
		getStatusBarWidget().addLeft(text, icon);
	}
	
	@Override
	public void addRight(Widget comp){
		getStatusBarWidget().addRight(comp);
	}
	
	@Override
	public void addLeft(Widget comp){
		getStatusBarWidget().addLeft(comp);
	}
	
	@Override
	public void removeRight(Widget comp){
		getStatusBarWidget().removeRight(comp);
	}
	
	@Override
	public void removeLeft(Widget comp){
		getStatusBarWidget().removeLeft(comp);
	}

	@Override
	public void setContainer(Viewport container) {
		getStatusBarWidget().setContainer(container);
	}

}
