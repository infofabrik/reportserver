package net.datenwerke.security.ext.client.usermanager.provider.treehookers;

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
import net.datenwerke.rs.terminal.client.terminal.TerminalDao;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.helper.menu.TerminalMenuItem;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeManagerDao;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIModule;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeFolders;
import net.datenwerke.security.ext.client.usermanager.utils.UserIconMapping;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class UserManagerTreeConfigurationHooker implements TreeConfiguratorHook {

   private final UserManagerTreeManagerDao treeHandler;
   private final Provider<TerminalUIService> terminalUIServiceProvider;
   private final Provider<SecurityUIService> securityServiceProvider;
   private final Provider<UITree> userManagerTreeProvider;
   private final Provider<UtilsUIService> utilsUIServiceProvider;
   private final TerminalDao terminalDao;

   @Inject
   public UserManagerTreeConfigurationHooker(
         UserManagerTreeManagerDao treeHandler, 
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SecurityUIService> securityServiceProvider,
         @UserManagerTreeFolders Provider<UITree> userManagerTreeProvider,
         Provider<UtilsUIService> utilsUIServiceProvider, 
         TerminalDao terminalDao
         ) {

      /* store objects */
      this.treeHandler = treeHandler;
      this.terminalUIServiceProvider = terminalUIServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.userManagerTreeProvider = userManagerTreeProvider;
      this.utilsUIServiceProvider = utilsUIServiceProvider;
      this.terminalDao = terminalDao;
   }

   @Override
   public boolean consumes(ManagerHelperTree tree) {
      return UserManagerUIModule.class.equals(tree.getGuarantor());
   }

   @Override
   public void configureTreeIcons(TreeDBUIIconProvider iconProvider) {
      iconProvider.addMappings(new UserIconMapping(), new IconMapping(GroupDto.class, BaseIcon.GROUP));
   }

   @Override
   public void configureTreeMenu(TreeDBUIMenuProvider menuProvider) {
      /* user */
      Menu userMenu = menuProvider.createOrGetMenuFor(UserDto.class);
      MenuItem inserItem = generateInsertMenu();
      inserItem.disable();
      userMenu.add(inserItem);
      userMenu.add(new MoveToFolderMenuItem(treeHandler, userManagerTreeProvider));
      userMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         userMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
         userMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));         
      }
      userMenu.add(new SeparatorMenuItem());
      userMenu.add(new InfoMenuItem());

      /* role */
      Menu groupMenu = menuProvider.createOrGetMenuFor(GroupDto.class);
      inserItem = generateInsertMenu();
      inserItem.disable();
      groupMenu.add(inserItem);
      groupMenu.add(new MoveToFolderMenuItem(treeHandler, userManagerTreeProvider));
      groupMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         groupMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
         groupMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));         
      }
      groupMenu.add(new SeparatorMenuItem());
      groupMenu.add(new InfoMenuItem());

      /* OU */
      Menu ouMenu = menuProvider.createOrGetMenuFor(OrganisationalUnitDto.class);
      inserItem = generateInsertMenu();
      ouMenu.add(inserItem);
      ouMenu.add(new MoveToFolderMenuItem(treeHandler, userManagerTreeProvider));
      ouMenu.add(new DeleteMenuItem(treeHandler));
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         ouMenu.add(new TerminalMenuItem(terminalUIServiceProvider));
         ouMenu.add(new TerminalNewWindowMenuItem(utilsUIServiceProvider, terminalDao));         
      }
      ouMenu.add(new SeparatorMenuItem());
      ouMenu.add(new InfoMenuItem());
      ouMenu.add(new ReloadMenuItem());
   }

   private MenuItem generateInsertMenu() {
      Menu insertMenu = new DwMenu();
      insertMenu.add(new InsertMenuItem(new OrganisationalUnitDto(), UsermanagerMessages.INSTANCE.ou(), treeHandler,
            BaseIcon.FOLDER_USER));
      insertMenu
            .add(new InsertMenuItem(new GroupDto(), UsermanagerMessages.INSTANCE.role(), treeHandler, BaseIcon.GROUP));
      insertMenu
            .add(new InsertMenuItem(new UserDtoDec(), UsermanagerMessages.INSTANCE.user(), treeHandler, BaseIcon.USER));

      MenuItem insertItem = new DwMenuItem(BaseMessages.INSTANCE.insert(), BaseIcon.FILE_O);
      insertItem.setSubMenu(insertMenu);

      return insertItem;
   }

   @Override
   public void onDoubleClick(AbstractNodeDto selectedItem, DoubleClickEvent event) {

   }

   @Override
   public void configureFolderTypes(ManagerHelperTree tree) {
      tree.addFolderTypes(OrganisationalUnitDto.class);
   }

}
