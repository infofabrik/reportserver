package net.datenwerke.rs.core.client.toolsmenu.hookers;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.widget.core.client.WidgetComponent;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.homepage.hooks.HomepageHeaderContentHook;
import net.datenwerke.gf.client.homepage.ui.DwMainViewportTopBarElement;
import net.datenwerke.gf.client.homepage.ui.DwMainViewportTopBarWidget;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.adminutils.client.suuser.SuUserUiService;
import net.datenwerke.rs.adminutils.client.suuser.locale.SuMessages;
import net.datenwerke.rs.adminutils.client.suuser.security.SuGenericTargetIdentifier;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.search.client.search.SearchUiService;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIModule;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;

public class ToolsMenuHooker implements HomepageHeaderContentHook {

   @CssClassConstant
   public static final String CSS_NAME_COMBO = "rs-search-box";

   @CssClassConstant
   public static final String CSS_NAME_ICON = "rs-search-box-icon";

   private final Provider<TerminalUIService> terminalUIServiceProvider;
   private final Provider<SecurityUIService> securityServiceProvider;
   private final Provider<UtilsUIService> utilsUIServiceProvider;
   private final Provider<SearchUiService> searchUiServiceProvider;
   private final Provider<SuUserUiService> suUserUiServiceProvider;
   private final Provider<ToolbarService> toolbarServiceProvider;

   @Inject
   public ToolsMenuHooker(
         Provider<SecurityUIService> securityServiceProvider,
         Provider<UtilsUIService> utilsUIServiceProvider,
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SuUserUiService> suUserUiServiceProvider,
         Provider<SearchUiService> searchUiServiceProvider,
         Provider<ToolbarService> toolbarServiceProvider
         ) {
      this.terminalUIServiceProvider = terminalUIServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.utilsUIServiceProvider = utilsUIServiceProvider;
      this.suUserUiServiceProvider = suUserUiServiceProvider;
      this.searchUiServiceProvider = searchUiServiceProvider;
      this.toolbarServiceProvider = toolbarServiceProvider;
   }

   @Override
   public DwMainViewportTopBarElement homepageHeaderContentHook_addTopRight(final HBoxLayoutContainer container) {
      return new DwMainViewportTopBarWidget() {

         @Override
         public Widget getComponent() {
            return createWidget();
         }

         @Override
         public int getSize() {
            return 50;
         }
      };
   }
   
   private Widget createWidget() {
      Menu menu = new DwMenu();
      
      String openTerminalLabel = SystemConsoleMessages.INSTANCE.openTerminal();
      String openTerminalInNewWindowLabel = SystemConsoleMessages.INSTANCE.openTerminalInNewWindow();
      String userLabel = SuMessages.INSTANCE.userLabel();
      String globalSearchLabel = SystemConsoleMessages.INSTANCE.globalSearch();
      
      /* open menu button */
      TextButton toolsBtn = toolbarServiceProvider.get().createSmallButtonLeft(BaseMessages.INSTANCE.tools(), BaseIcon.COG);
      toolsBtn.addStyleName("rs-tools-menu-panel-btn");
      toolsBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
      toolsBtn.setMenu(menu);
      
      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         /* open terminal button */
         MenuItem openConsoleBtn = new MenuItem(openTerminalLabel, BaseIcon.TERMINAL.toImageResource());
         openConsoleBtn.addSelectionHandler(event -> terminalUIServiceProvider.get().displayTerminalWindow());
         menu.add(openConsoleBtn);
         
         /* open terminal in new window button */         
         String url = "#" + TerminalUIModule.TERMINAL_ID;
         MenuItem openConsoleNewWindowBtn = new MenuItem(openTerminalInNewWindowLabel, BaseIcon.TERMINAL.toImageResource());
         openConsoleNewWindowBtn.addSelectionHandler(event -> utilsUIServiceProvider.get().redirectInPopup(url));
         menu.add(openConsoleNewWindowBtn);
      }
      
      /* change user button */
      if (securityServiceProvider.get().hasRight(SuGenericTargetIdentifier.class, ExecuteDto.class)) {
         MenuItem changeUserBtn = new MenuItem(userLabel, BaseIcon.USER_SECRET.toImageResource());
         changeUserBtn.addSelectionHandler(event -> suUserUiServiceProvider.get().openSuWindow());
         menu.add(changeUserBtn);
      }
      
      /* global search button */
      MenuItem searchBtn = new MenuItem(globalSearchLabel, BaseIcon.SEARCH.toImageResource());
      searchBtn.addSelectionHandler(event -> searchUiServiceProvider.get().displaySearchModule());    
      menu.add(searchBtn);
      
      return new WidgetComponent(toolsBtn);
   }

}
