package net.datenwerke.rs.fileserver.client.fileserver;

import java.util.Optional;

import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;

public interface FileServerUiService {

   public final static int DEFAULT_FILE_SEND_TO_WINDOW_HEIGHT = 370;

   void editFileDirectly(FileServerFileDto file, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd);

   void editFileDirectly(FileServerFileDto file, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd, String data);

   void editFileDirectly(String filename, String data, boolean editable, boolean refreshable, boolean emailable,
         boolean scrollToEnd, Optional<FileServerFileDto> file);

}
