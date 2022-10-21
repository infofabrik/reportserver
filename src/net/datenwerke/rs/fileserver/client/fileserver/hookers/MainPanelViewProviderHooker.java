package net.datenwerke.rs.fileserver.client.fileserver.hookers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.EditFileServerFileHook;
import net.datenwerke.rs.fileserver.client.fileserver.ui.forms.FileForm;
import net.datenwerke.rs.fileserver.client.fileserver.ui.forms.FolderForm;
import net.datenwerke.security.ext.client.security.ui.SecurityView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker implements MainPanelViewProviderHook {

   private final HookHandlerService hookHandler;
   private final Provider<FolderForm> folderFormProvider;

   private final Provider<SecurityView> securityViewProvider;
   private final Provider<FileForm> fileFormProvider;

   @Inject
   public MainPanelViewProviderHooker(HookHandlerService hookHandler, Provider<FolderForm> folderFormProvider,
         Provider<FileForm> fileFormProvider,

         Provider<SecurityView> securityViewProvider) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.folderFormProvider = folderFormProvider;
      this.fileFormProvider = fileFormProvider;
      this.securityViewProvider = securityViewProvider;
   }

   public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
      if (node instanceof FileServerFolderDto)
         return getViewForFolder();
      if (node instanceof FileServerFileDto)
         return getViewForFiles((FileServerFileDto) node);
      return null;
   }

   private List<MainPanelView> getViewForFiles(FileServerFileDto file) {
      List<MainPanelView> views = new ArrayList<MainPanelView>();

      views.add(fileFormProvider.get());

      for (EditFileServerFileHook hooker : hookHandler.getHookers(EditFileServerFileHook.class)) {
         if (hooker.consumes(file)) {
            views.add(hooker.getView(file));
            break;
         }
      }

      views.add(securityViewProvider.get());

      return views;
   }

   private List<MainPanelView> getViewForFolder() {
      return Arrays.asList(folderFormProvider.get(), securityViewProvider.get());
   }

}
