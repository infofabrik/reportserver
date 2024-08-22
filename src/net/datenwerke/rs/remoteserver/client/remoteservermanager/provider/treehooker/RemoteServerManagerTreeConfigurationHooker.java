package net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.treehooker;

import com.google.gwt.event.dom.client.DoubleClickEvent;
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
import net.datenwerke.gf.client.treedb.icon.IconMapping;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerTreeManagerDao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerUIModule;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks.RemoteServerDefinitionConfigProviderHook;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.annotations.RemoteServerTreeFolders;
import net.datenwerke.rs.terminal.client.terminal.TerminalDao;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.helper.menu.TerminalMenuItem;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class RemoteServerManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final HookHandlerService hookHandler;
   private final RemoteServerTreeManagerDao treeHandler;
   private final Provider<TerminalUIService> terminalUIServiceProvider;
   private final Provider<SecurityUIService> securityServiceProvider;
   private Provider<UITree> remoteServerManagerTreeProvider;
   private final Provider<UtilsUIService> utilsUIServiceProvider;
   private final TerminalDao terminalDao;

   @Inject
   public RemoteServerManagerTreeConfigurationHooker(
         HookHandlerService hookHandler, 
         RemoteServerTreeManagerDao treeHandler, 
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SecurityUIService> securityServiceProvider,
         @RemoteServerTreeFolders Provider<UITree> remoteServerManagerTreeProvider,
         Provider<UtilsUIService> utilsUIServiceProvider, 
         TerminalDao terminalDao
         ) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.treeHandler = treeHandler;
      this.terminalUIServiceProvider = terminalUIServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.remoteServerManagerTreeProvider = remoteServerManagerTreeProvider;
      this.utilsUIServiceProvider = utilsUIServiceProvider;
      this.terminalDao = terminalDao;
   }

   @Override
   public boolean consumes(ManagerHelperTree tree) {
      return RemoteServerUIModule.class.equals(tree.getGuarantor());
   }

   @Override
   public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
      /* Folder */
      Menu folderMenu = menuProvider.createOrGetMenuFor(RemoteServerFolderDto.class);
      MenuItem insertItem = generateInsertMenu();
      folderMenu.add(insertItem);
      folderMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         folderMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
         folderMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));         
      }
      folderMenu.add(new SeparatorMenuItem());
      folderMenu.add(new InfoMenuItem());
      folderMenu.add(new ReloadMenuItem());
      
      /* Specific remote servers */
      for (RemoteServerDefinitionConfigProviderHook config : hookHandler
            .getHookers(RemoteServerDefinitionConfigProviderHook.class)) {
         Menu remoteServerMenu = menuProvider.createOrGetMenuFor(config.getRemoteServerClass());
         insertItem = generateInsertMenu();
         insertItem.disable();
         remoteServerMenu.add(insertItem);
         remoteServerMenu.add(new DuplicateMenuItem(treeHandler));
         remoteServerMenu.add(new MoveToFolderMenuItem(treeHandler, remoteServerManagerTreeProvider));
         remoteServerMenu.add(new DeleteMenuItem(treeHandler));
         if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
            remoteServerMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
            remoteServerMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));            
         }
         remoteServerMenu.add(new SeparatorMenuItem());
         remoteServerMenu.add(new InfoMenuItem());
      }
   }

   private MenuItem generateInsertMenu() {
      final Menu insertMenu = new DwMenu();
      InsertMenuItem folderInsert = new InsertMenuItem(new RemoteServerFolderDto(), BaseMessages.INSTANCE.folder(),
            treeHandler);
      folderInsert.setIcon(BaseIcon.FOLDER_O);
      insertMenu.add(folderInsert);
      
      /* Specific remote servers */
      hookHandler.getHookers(RemoteServerDefinitionConfigProviderHook.class).forEach(config -> {
         InsertMenuItem item = new InsertMenuItem(config.instantiateRemoteServer(), config.getRemoteServerName(), treeHandler);
         item.setIcon(config.getRemoteServerIcon());
         insertMenu.add(item);
      });
      

      MenuItem insertItem = new DwMenuItem(BaseMessages.INSTANCE.insert(), BaseIcon.FILE_O);
      insertItem.setSubMenu(insertMenu);

      return insertItem;
   }

   @Override
   public void onDoubleClick(AbstractNodeDto selectedItem, DoubleClickEvent event) {
      // ignore double clicks
   }

   @Override
   public void configureFolderTypes(ManagerHelperTree tree) {
      tree.addFolderTypes(RemoteServerFolderDto.class);
   }

   @Override
   public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
      iconProvider.addMappings(hookHandler.getHookers(RemoteServerDefinitionConfigProviderHook.class)
            .stream()
            .map(config -> new IconMapping(config.getRemoteServerClass(), config.getRemoteServerIcon()))
            .toArray(IconMapping[]::new));
      
   }
}
