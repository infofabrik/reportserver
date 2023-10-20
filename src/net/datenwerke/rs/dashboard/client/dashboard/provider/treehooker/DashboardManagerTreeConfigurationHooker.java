package net.datenwerke.rs.dashboard.client.dashboard.provider.treehooker;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.helper.menu.DeleteMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.DuplicateMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InfoMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InsertMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.ReloadMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TerminalMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.icon.IconMapping;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeManagerDao;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiModule;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DashboardManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final DashboardTreeManagerDao treeHandler;
   private final Provider<TerminalUIService> terminalUIServiceProvider;
   private final Provider<SecurityUIService> securityServiceProvider;

   @Inject
   public DashboardManagerTreeConfigurationHooker(
         DashboardTreeManagerDao treeHandler, 
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SecurityUIService> securityServiceProvider
         ) {

      /* store objects */
      this.treeHandler = treeHandler;
      this.terminalUIServiceProvider = terminalUIServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
   }

   @Override
   public boolean consumes(ManagerHelperTree tree) {
      return DashboardUiModule.class.equals(tree.getGuarantor());
   }

   @Override
   public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
      iconProvider.addMappings(new IconMapping(DadgetNodeDto.class, BaseIcon.MAP_O),
            new IconMapping(DashboardNodeDto.class, BaseIcon.ITEMS_DETAIL));
   }

   @Override
   public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
      /* Folder */
      Menu folderMenu = menuProvider.createOrGetMenuFor(DashboardFolderDto.class);
      MenuItem insertItem = generateInsertMenu();
      folderMenu.add(insertItem);
      folderMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class))
         folderMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
      folderMenu.add(new SeparatorMenuItem());
      folderMenu.add(new InfoMenuItem());
      folderMenu.add(new ReloadMenuItem());

      /* File */
      Menu fileMenu = menuProvider.createOrGetMenuFor(DadgetNodeDto.class);
      insertItem = generateInsertMenu();
      insertItem.disable();
      fileMenu.add(insertItem);
      fileMenu.add(new DuplicateMenuItem(treeHandler));
      fileMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class))
         fileMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
      fileMenu.add(new SeparatorMenuItem());
      fileMenu.add(new InfoMenuItem());

      /* File */
      Menu dashboardMenu = menuProvider.createOrGetMenuFor(DashboardNodeDto.class);
      insertItem = generateInsertMenu();
      insertItem.disable();
      dashboardMenu.add(insertItem);
      dashboardMenu.add(new DuplicateMenuItem(treeHandler));
      dashboardMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class))
         dashboardMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
      dashboardMenu.add(new SeparatorMenuItem());
      dashboardMenu.add(new InfoMenuItem());
   }

   private MenuItem generateInsertMenu() {
      Menu insertMenu = new DwMenu();
      insertMenu.add(new InsertMenuItem(new DashboardFolderDto(), BaseMessages.INSTANCE.folder(), treeHandler,
            BaseIcon.FOLDER_O));
      insertMenu.add(
            new InsertMenuItem(new DadgetNodeDto(), DashboardMessages.INSTANCE.dadget(), treeHandler, BaseIcon.MAP_O));
      insertMenu.add(new InsertMenuItem(new DashboardNodeDto(), DashboardMessages.INSTANCE.dashboard(), treeHandler,
            BaseIcon.ITEMS_DETAIL));

      MenuItem insertItem = new DwMenuItem(DashboardMessages.INSTANCE.insert(), BaseIcon.FILE_O);
      insertItem.setSubMenu(insertMenu);

      return insertItem;
   }

   @Override
   public void onDoubleClick(AbstractNodeDto selectedItem, DoubleClickEvent event) {

   }

   @Override
   public void configureFolderTypes(ManagerHelperTree tree) {
      tree.addFolderTypes(DashboardFolderDto.class);
   }
}
