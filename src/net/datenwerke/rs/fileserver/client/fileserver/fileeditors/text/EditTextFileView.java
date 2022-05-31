package net.datenwerke.rs.fileserver.client.fileserver.fileeditors.text;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.EditFileServerFileHook;

public class EditTextFileView implements EditFileServerFileHook {

   @Inject
   private Provider<TextFileView> textFileViewProvider;

   @Override
   public boolean consumes(FileServerFileDto file) {
      if (null == file)
         return false;

      if (null == file.getContentType()) {
         if (((FileServerFileDtoDec) file).getSize() < 1024 * 10)
            return true;
         return false;
      }

      if (null != file.getName() && file.getName().endsWith("rs"))
         return true;

      return (file.getContentType().startsWith("text") || file.getContentType().endsWith("xml")
            || file.getContentType().contains("groovy"));
   }

   @Override
   public MainPanelView getView(FileServerFileDto file) {
      return textFileViewProvider.get();
   }

}
