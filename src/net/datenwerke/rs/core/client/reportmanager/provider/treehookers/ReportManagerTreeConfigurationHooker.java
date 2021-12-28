package net.datenwerke.rs.core.client.reportmanager.provider.treehookers;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.AvailabilityCallback;
import net.datenwerke.gf.client.treedb.helper.menu.DeleteMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.DuplicateMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InfoMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InsertMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.ReloadMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.helper.menu.TreeMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeMenuSelectionEvent;
import net.datenwerke.gf.client.treedb.icon.IconMapping;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerUIModule;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ReportManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final HookHandlerService hookHandler;
   private final ReportManagerTreeManagerDao treeHandler;
   private final ReportExecutorUIService reportExecutorService;

   @Inject
   public ReportManagerTreeConfigurationHooker(HookHandlerService hookHandler, ReportManagerTreeManagerDao treeHandler,
         ReportExecutorUIService reportExecutorService) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.treeHandler = treeHandler;
      this.reportExecutorService = reportExecutorService;
   }

   @Override
   public boolean consumes(ManagerHelperTree tree) {
      return ReportManagerUIModule.class.equals(tree.getGuarantor());
   }

   @Override
   public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
      for (ReportTypeConfigHook config : hookHandler.getHookers(ReportTypeConfigHook.class))
         iconProvider.addMappings(new IconMapping(config.getReportClass(), config.getReportIcon()),
               new IconMapping(config.getReportVariantClass(), config.getReportVariantIcon()));

   }

   @Override
   public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
      /* Folder */
      Menu folderMenu = menuProvider.createOrGetMenuFor(ReportFolderDto.class);
      MenuItem insertItem = generateInsertMenu();
      folderMenu.add(insertItem);
      folderMenu.add(new DeleteMenuItem(treeHandler));
      folderMenu.add(new SeparatorMenuItem());
      folderMenu.add(new InfoMenuItem());
      folderMenu.add(new ReloadMenuItem());

      /* Reports */
      for (ReportTypeConfigHook config : hookHandler.getHookers(ReportTypeConfigHook.class)) {
         Menu reportMenu = menuProvider.createOrGetMenuFor(config.getReportClass());
         insertItem = generateInsertMenu();
         insertItem.disable();

         reportMenu.add(generateExecuteReportItem());
         reportMenu.add(new SeparatorMenuItem());
         reportMenu.add(insertItem);
         reportMenu.add(new DuplicateMenuItem(treeHandler));
         reportMenu.add(new DeleteMenuItem(treeHandler));
         reportMenu.add(new SeparatorMenuItem());
         reportMenu.add(new InfoMenuItem());
      }

   }

   private MenuItem generateExecuteReportItem() {
      TreeMenuItem executeReportItem = new TreeMenuItem();
      executeReportItem.setText(ReportmanagerMessages.INSTANCE.execute());
      executeReportItem.setIcon(BaseIcon.EXECUTE.toImageResource());

      executeReportItem.addMenuSelectionListener(new TreeMenuSelectionEvent() {

         @Override
         public void menuItemSelected(UITree tree, AbstractNodeDto node) {
            if (node instanceof ReportDto) {
               reportExecutorService.executeReport((ReportDto) node);
            }
         }
      });

      return executeReportItem;
   }

   private MenuItem generateInsertMenu() {
      Menu insertMenu = new DwMenu();
      insertMenu.add(new InsertMenuItem(new ReportFolderDto(), ReportmanagerMessages.INSTANCE.folder(), treeHandler,
            BaseIcon.FOLDER_O));

      for (final ReportTypeConfigHook config : hookHandler.getHookers(ReportTypeConfigHook.class)) {
         InsertMenuItem item = new InsertMenuItem(config.instantiateReport(), config.getReportName(), treeHandler,
               config.getReportIcon());
         insertMenu.add(item);

         item.setAvailableCallback(new AvailabilityCallback() {
            @Override
            public boolean isAvailable() {
               return config.isAvailable();
            }
         });
      }

      MenuItem insertItem = new DwMenuItem(ReportmanagerMessages.INSTANCE.insert(), BaseIcon.FILE_O);
      insertItem.setSubMenu(insertMenu);

      return insertItem;
   }

   @Override
   public void onDoubleClick(AbstractNodeDto selectedItem, DoubleClickEvent event) {
      if (selectedItem instanceof ReportDto) {
         reportExecutorService.executeReport((ReportDto) selectedItem);
         event.stopPropagation();
      }
   }

   @Override
   public void configureFolderTypes(ManagerHelperTree tree) {
      tree.addFolderTypes(ReportFolderDto.class);
   }

}
