package net.datenwerke.rs.core.client.datasinkmanager.provider.treehooker;

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
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.datasinkmanager.provider.annotations.DatasinkTreeFolders;
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

public class DatasinkManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final HookHandlerService hookHandler;
   private final DatasinkTreeManagerDao treeHandler;
   private final Provider<TerminalUIService> terminalUIServiceProvider;  
   private final Provider<SecurityUIService> securityServiceProvider;
   private final Provider<UtilsUIService> utilsUIServiceProvider;
   private final Provider<UITree> transportTreeProvider;
   private final TransportDao transportDao;
   private final Provider<UITree> datasinkManagerTreeProvider;
   private final TerminalDao terminalDao;

   @Inject
   public DatasinkManagerTreeConfigurationHooker(
         HookHandlerService hookHandler, 
         DatasinkTreeManagerDao treeHandler, 
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SecurityUIService> securityServiceProvider,
         @TransportTreeBasic Provider<UITree> transportTreeProvider,
         Provider<UtilsUIService> utilsUIServiceProvider,
         TransportDao transportDao,
         @DatasinkTreeFolders Provider<UITree> datasinkManagerTreeProvider,
         TerminalDao terminalDao
         ) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.treeHandler = treeHandler;
      this.terminalUIServiceProvider = terminalUIServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.utilsUIServiceProvider = utilsUIServiceProvider;
      this.transportTreeProvider = transportTreeProvider;
      this.transportDao = transportDao;
      this.datasinkManagerTreeProvider = datasinkManagerTreeProvider;
      this.terminalDao = terminalDao;
   }

   @Override
   public boolean consumes(ManagerHelperTree tree) {
      return DatasinkUIModule.class.equals(tree.getGuarantor());
   }

   @Override
   public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
      iconProvider.addMappings(hookHandler.getHookers(DatasinkDefinitionConfigProviderHook.class)
            .stream()
            .map(config -> new IconMapping(config.getDatasinkClass(), config.getDatasinkIcon()))
            .toArray(IconMapping[]::new));
   }

   @Override
   public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
      /* Folder */
      Menu folderMenu = menuProvider.createOrGetMenuFor(DatasinkFolderDto.class);
      MenuItem insertItem = generateInsertMenu();
      folderMenu.add(insertItem);
      folderMenu.add(new DeleteMenuItem(treeHandler));
      folderMenu.add(new AddToTransportMenuItem(transportTreeProvider.get(), transportDao));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         folderMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
         folderMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));         
      }
      folderMenu.add(new SeparatorMenuItem());
      folderMenu.add(new InfoMenuItem());
      folderMenu.add(new ReloadMenuItem());

      /* Specific datasinks */
      for (DatasinkDefinitionConfigProviderHook config : hookHandler
            .getHookers(DatasinkDefinitionConfigProviderHook.class)) {
         Menu dsMenu = menuProvider.createOrGetMenuFor(config.getDatasinkClass());
         insertItem = generateInsertMenu();
         insertItem.disable();
         dsMenu.add(insertItem);
         dsMenu.add(new DuplicateMenuItem(treeHandler));
         dsMenu.add(new MoveToFolderMenuItem(treeHandler, datasinkManagerTreeProvider));
         dsMenu.add(new DeleteMenuItem(treeHandler));
         dsMenu.add(new AddToTransportMenuItem(transportTreeProvider.get(), transportDao));
         if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
            dsMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
            dsMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));            
         }
         dsMenu.add(new SeparatorMenuItem());
         dsMenu.add(new InfoMenuItem());
      }
   }

   private MenuItem generateInsertMenu() {
      final Menu insertMenu = new DwMenu();
      InsertMenuItem folderInsert = new InsertMenuItem(new DatasinkFolderDto(), BaseMessages.INSTANCE.folder(),
            treeHandler);
      folderInsert.setIcon(BaseIcon.FOLDER_O);
      insertMenu.add(folderInsert);

      /* Specific datasinks */
      hookHandler.getHookers(DatasinkDefinitionConfigProviderHook.class).forEach(config -> {
         InsertMenuItem item = new InsertMenuItem(config.instantiateDatasink(), config.getDatasinkName(), treeHandler);
         item.setIcon(config.getDatasinkIcon());
         insertMenu.add(item);

         item.setAvailableCallback(config::isAvailable);
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
      tree.addFolderTypes(DatasinkFolderDto.class);
   }
}
