package net.datenwerke.rs.fileserver.client.fileserver.fileeditors.text;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.EditFileServerFileHook;
import net.datenwerke.rs.fileserver.shared.fileserver.FileServerFileHelper;

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
      
      return new FileServerFileHelper().isTextFile(file.getName(), file.getContentType());
   }

   @Override
   public MainPanelView getView(FileServerFileDto file) {
      return textFileViewProvider.get();
   }

}
