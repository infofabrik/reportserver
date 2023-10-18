package net.datenwerke.rs.markdown.client.markdown.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.ui.panels.MarkdownFilePreviewView;
import net.datenwerke.rs.markdown.client.markdown.MarkdownUiModule;
import net.datenwerke.rs.markdown.client.markdown.hooks.MarkdownFileViewHook;

public class MarkdownFileViewPreviewHooker implements MarkdownFileViewHook {
   @Inject
   private Provider<MarkdownFilePreviewView> mdFilePreviewView;

   @Override
   public boolean consumes(FileServerFileDto file) {
      if (null == file)
         return false;
      if (MarkdownUiModule.MIME_TYPE.equals(file.getContentType()))
         return true;
      return false;
   }

   @Override
   public MainPanelView getView(FileServerFileDto file) {
      return mdFilePreviewView.get();
   }

}
