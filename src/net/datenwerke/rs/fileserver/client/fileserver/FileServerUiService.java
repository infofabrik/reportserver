package net.datenwerke.rs.fileserver.client.fileserver;

import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

public interface FileServerUiService {

   public final static int DEFAULT_FILE_SEND_TO_WINDOW_HEIGHT = 360;

   void editFileDirectly(FileServerFileDto file, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd);

   void editFileDirectly(FileServerFileDto file, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd, String data);

   void editFileDirectly(String filename, String data, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd, Optional<FileServerFileDto> file);

   void displayFileSendToDatasinkDialog(final Class<? extends DatasinkDefinitionDto> datasinkType, String filename,
         Provider<UITree> treeProvider, Provider<? extends HasDefaultDatasink> datasinkDaoProvider,
         final AbstractFileServerNodeDto toExport, AsyncCallback<Map<String, Object>> onSelectHandler);

}
