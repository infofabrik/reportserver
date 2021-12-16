package net.datenwerke.rs.fileserver.client.fileserver;

import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public interface FileServerUiService {

   void editFileDirectly(FileServerFileDto file, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd);

   void editFileDirectly(FileServerFileDto file, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd, String data);

   void editFileDirectly(String filename, String data, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd, Optional<FileServerFileDto> file);

   void displayFileSendToDatasinkDialog(BaseIcon icon, String title, String filename, Provider<UITree> treeProvider,
         Provider<? extends HasDefaultDatasink> datasinkDaoProvider, final AbstractFileServerNodeDto toExport, AsyncCallback<Map<String,Object>> onSelectHandler);

}
