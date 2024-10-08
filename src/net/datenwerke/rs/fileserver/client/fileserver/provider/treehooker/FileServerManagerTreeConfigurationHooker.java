package net.datenwerke.rs.fileserver.client.fileserver.provider.treehooker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.client.Random;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.DeleteMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.DuplicateMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InfoMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InsertMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.MoveToFolderMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.ReloadMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TerminalNewWindowMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeFolders;
import net.datenwerke.rs.fileserver.client.fileserver.provider.helper.FileIconMapping;
import net.datenwerke.rs.fileserver.client.fileserver.provider.helper.menu.DownloadMenuItem;
import net.datenwerke.rs.fileserver.client.fileserver.provider.helper.menu.DownloadMenuItem.DownloadMenuUrlGenerator;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.terminal.client.terminal.TerminalDao;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.helper.menu.TerminalMenuItem;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.TransportDao;
import net.datenwerke.rs.transport.client.transport.provider.annotations.TransportTreeBasic;
import net.datenwerke.rs.transport.client.transport.ui.AddToTransportMenuItem;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class FileServerManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final FileServerTreeManagerDao treeHandler;
   private final HookHandlerService hookHandler;
   private final Provider<TerminalUIService> terminalUIServiceProvider; 
   private final Provider<SecurityUIService> securityServiceProvider;
   private final Provider<UITree> transportTreeProvider;
   private final TransportDao transportDao;
   private final Provider<UITree> fileServerManagerTreeProvider;
   private final Provider<UtilsUIService> utilsUIServiceProvider;
   private final TerminalDao terminalDao;

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
   public FileServerManagerTreeConfigurationHooker(
         FileServerTreeManagerDao treeHandler,
         HookHandlerService hookHandler, 
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SecurityUIService> securityServiceProvider,
         @TransportTreeBasic Provider<UITree> transportTreeProvider,
         TransportDao transportDao,
         @FileServerTreeFolders Provider<UITree> fileServerManagerTreeProvider,
         Provider<UtilsUIService> utilsUIServiceProvider, 
         TerminalDao terminalDao
         ) {

      /* store objects */
      this.treeHandler = treeHandler;
      this.hookHandler = hookHandler;
      this.terminalUIServiceProvider = terminalUIServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.transportTreeProvider = transportTreeProvider;
      this.transportDao = transportDao;
      this.fileServerManagerTreeProvider = fileServerManagerTreeProvider;
      this.utilsUIServiceProvider = utilsUIServiceProvider;
      this.terminalDao = terminalDao;
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
      folderMenu.add(new AddToTransportMenuItem(transportTreeProvider.get(), transportDao));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         folderMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
         folderMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));         
      }
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
      fileMenu.add(new MoveToFolderMenuItem(treeHandler, fileServerManagerTreeProvider));
      fileMenu.add(new DeleteMenuItem(treeHandler));
      fileMenu.add(new AddToTransportMenuItem(transportTreeProvider.get(), transportDao));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         fileMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
         fileMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));         
      }
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
