package net.datenwerke.rs.transport.client.transport.provider.treehooker;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.helper.menu.DeleteMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InfoMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.InsertMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.ReloadMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.icon.IconMapping;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.helper.menu.TerminalMenuItem;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.TransportTreeManagerDao;
import net.datenwerke.rs.transport.client.transport.TransportUIModule;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;
import net.datenwerke.rs.transport.client.transport.dto.decorator.TransportDtoDec;
import net.datenwerke.rs.transport.client.transport.helper.menu.DuplicateAsOpenMenuItem;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TransportManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final TransportTreeManagerDao treeHandler;
   private final Provider<TerminalUIService> terminalUIServiceProvider;
   private final Provider<SecurityUIService> securityServiceProvider;

   @Inject
   public TransportManagerTreeConfigurationHooker(
         TransportTreeManagerDao treeHandler, 
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SecurityUIService> securityServiceProvider
         ) {
      this.treeHandler = treeHandler;
      this.terminalUIServiceProvider = terminalUIServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
   }

   @Override
   public boolean consumes(ManagerHelperTree tree) {
      return TransportUIModule.class.equals(tree.getGuarantor());
   }

   @Override
   public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
      /* Folder */
      Menu folderMenu = menuProvider.createOrGetMenuFor(TransportFolderDto.class);
      MenuItem insertItem = generateInsertMenu();
      folderMenu.add(insertItem);
      folderMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class))
         folderMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
      folderMenu.add(new SeparatorMenuItem());
      folderMenu.add(new InfoMenuItem());
      folderMenu.add(new ReloadMenuItem());
      
      /* Transport */
      Menu transportMenu = menuProvider.createOrGetMenuFor(TransportDto.class);
      insertItem = generateInsertMenu();
      insertItem.disable();
      transportMenu.add(insertItem);
      transportMenu.add(new DuplicateAsOpenMenuItem(treeHandler));
      transportMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class))
         transportMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
      transportMenu.add(new SeparatorMenuItem());
      transportMenu.add(new InfoMenuItem());
      
   }
   
   private MenuItem generateInsertMenu() {
      final Menu insertMenu = new DwMenu();
      InsertMenuItem folderInsert = new InsertMenuItem(new TransportFolderDto(), BaseMessages.INSTANCE.folder(),
            treeHandler);
      folderInsert.setIcon(BaseIcon.FOLDER_O);
      insertMenu.add(folderInsert);
      
      insertMenu.add(new InsertMenuItem(new TransportDtoDec(), "Transport", treeHandler, BaseIcon.ARCHIVE));
      
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
      tree.addFolderTypes(TransportFolderDto.class);
   }

   @Override
   public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
      iconProvider.addMappings(new IconMapping(TransportDto.class, BaseIcon.ARCHIVE));
      
   }
}
