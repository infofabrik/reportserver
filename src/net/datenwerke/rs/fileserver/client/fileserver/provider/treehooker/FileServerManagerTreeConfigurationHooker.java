package net.datenwerke.rs.fileserver.client.fileserver.provider.treehooker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.client.Random;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.helper.menu.DeleteMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.DownloadMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.DownloadMenuItem.DownloadMenuUrlGenerator;
import net.datenwerke.gf.client.treedb.helper.menu.DuplicateMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InfoMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InsertMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.ReloadMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.fileserver.client.fileserver.provider.helper.FileIconMapping;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class FileServerManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final FileServerTreeManagerDao treeHandler;
   private final HookHandlerService hookHandler;

   class DownloadHelper implements DownloadMenuUrlGenerator {

      @Override
      public String getUrl(AbstractNodeDto node) {
         int nonce = Random.nextInt();
         String url = GWT.getModuleBaseURL() + FileServerUiModule.FILE_ACCESS_SERVLET + "?nonce=" + nonce + "&id=" //$NON-NLS-1$
               + node.getId() + "&download=true";
         if (node instanceof FileServerFolderDto)
            url += "&folder=true";
         return url;
      }

   }

   @Inject
   public FileServerManagerTreeConfigurationHooker(FileServerTreeManagerDao treeHandler,
         HookHandlerService hookHandler) {

      /* store objects */
      this.treeHandler = treeHandler;
      this.hookHandler = hookHandler;
   }

   @Override
   public boolean consumes(ManagerHelperTree tree) {
      return FileServerUiModule.class.equals(tree.getGuarantor());
   }

   @Override
   public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
      iconProvider.addMappings(new FileIconMapping());
   }

   @Override
   public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
      /* Folder */
      Menu folderMenu = menuProvider.createOrGetMenuFor(FileServerFolderDto.class);
      MenuItem insertItem = generateInsertMenu();
      folderMenu.add(insertItem);
      folderMenu.add(new DeleteMenuItem(treeHandler));
      folderMenu.add(new SeparatorMenuItem());
      folderMenu.add(new ReloadMenuItem());
      folderMenu.add(new SeparatorMenuItem());
      folderMenu.add(new InfoMenuItem());
      folderMenu.add(new DownloadMenuItem(new DownloadHelper()));
      MenuItem folderSendToItem = generateSendToMenu();
      folderMenu.add(folderSendToItem);

      /* File */
      Menu fileMenu = menuProvider.createOrGetMenuFor(FileServerFileDto.class);
      insertItem = generateInsertMenu();
      insertItem.disable();
      fileMenu.add(insertItem);
      fileMenu.add(new DuplicateMenuItem(treeHandler));
      fileMenu.add(new DeleteMenuItem(treeHandler));
      fileMenu.add(new SeparatorMenuItem());
      fileMenu.add(new InfoMenuItem());
      fileMenu.add(new DownloadMenuItem(new DownloadHelper()));
      MenuItem fileSendToItem = generateSendToMenu();
      fileMenu.add(fileSendToItem);

   }

   private MenuItem generateSendToMenu() {
      final Menu sendToMenu = new DwMenu();

      /* Specific datasinks */
      hookHandler.getHookers(FileExportExternalEntryProviderHook.class)
            .forEach(config -> config.createMenuEntry(sendToMenu, treeHandler));

      MenuItem sendToItem = new DwMenuItem(ReportExporterMessages.INSTANCE.sendToLabel(), BaseIcon.SERVER);
      sendToItem.setSubMenu(sendToMenu);

      return sendToItem;
   }

   private MenuItem generateInsertMenu() {
      Menu insertMenu = new DwMenu();
      insertMenu.add(new InsertMenuItem(new FileServerFolderDto(), FileServerMessages.INSTANCE.folder(), treeHandler,
            BaseIcon.FOLDER_O));
      insertMenu.add(new InsertMenuItem(new FileServerFileDtoDec(), FileServerMessages.INSTANCE.file(), treeHandler,
            BaseIcon.FILE_O));

      MenuItem insertItem = new DwMenuItem(FileServerMessages.INSTANCE.insert(), BaseIcon.FILE_O);
      insertItem.setSubMenu(insertMenu);

      return insertItem;
   }

   @Override
   public void onDoubleClick(AbstractNodeDto selectedItem, DoubleClickEvent event) {

   }

   @Override
   public void configureFolderTypes(ManagerHelperTree tree) {
      tree.addFolderTypes(FileServerFolderDto.class);
   }
}
