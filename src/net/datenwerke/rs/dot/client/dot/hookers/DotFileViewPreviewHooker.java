package net.datenwerke.rs.dot.client.dot.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.dot.client.dot.DotUiModule;
import net.datenwerke.rs.dot.client.dot.hooks.DotFileViewHook;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.ui.panels.DotFilePreviewView;

public class DotFileViewPreviewHooker implements DotFileViewHook {
   @Inject
   private Provider<DotFilePreviewView> dotFilePreviewView;

   @Override
   public boolean consumes(FileServerFileDto file) {
      if (null == file)
         return false;
      if (DotUiModule.MIME_TYPE.equals(file.getContentType()))
         return true;
      return false;
   }

   @Override
   public MainPanelView getView(FileServerFileDto file) {
      return dotFilePreviewView.get();
   }

}
