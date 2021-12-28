package net.datenwerke.rs.fileserver.client.fileserver;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.fileserver.client.fileserver.ui.FileServerManagerPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class FileServerAdminModule implements AdminModule {

	final private Provider<FileServerManagerPanel> managerPanelanelProvider;
	
	@Inject
	public FileServerAdminModule(
		Provider<FileServerManagerPanel> managerPanelanelProvider
		){
		
		/* store objects */
		this.managerPanelanelProvider = managerPanelanelProvider;
	}
	
	@Override
	public ImageResource getNavigationIcon(){
		return BaseIcon.FLOPPY_O.toImageResource();
	}
	
	@Override
	public String getNavigationText() {
		return FileServerMessages.INSTANCE.adminLabel();
	}

	@Override
	public Widget getMainWidget() {
		return managerPanelanelProvider.get();
	}
	
	@Override
	public void notifyOfSelection() {
		
	}
}
