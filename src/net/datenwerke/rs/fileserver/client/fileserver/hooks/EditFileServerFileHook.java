package net.datenwerke.rs.fileserver.client.fileserver.hooks;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

public interface EditFileServerFileHook extends Hook {

	boolean consumes(FileServerFileDto file);

	MainPanelView getView(FileServerFileDto file);

}
