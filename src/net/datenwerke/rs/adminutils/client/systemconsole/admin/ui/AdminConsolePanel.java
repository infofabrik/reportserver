package net.datenwerke.rs.adminutils.client.systemconsole.admin.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.adminutils.client.suuser.SuUserUiService;
import net.datenwerke.rs.adminutils.client.suuser.locale.SuMessages;
import net.datenwerke.rs.adminutils.client.suuser.security.SuGenericTargetIdentifier;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.security.SystemConsoleGenericTargetIdentifier;
import net.datenwerke.rs.search.client.search.SearchUiService;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIModule;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.dto.ReadDto;

/**
 * 
 *
 */
@Singleton
public class AdminConsolePanel extends DwContentPanel {

   private VerticalLayoutContainer wrapper;
   HorizontalLayoutContainer buttonsLayout = new HorizontalLayoutContainer();

   private final Provider<TerminalUIService> terminalUIServiceProvider;
   private final Provider<SuUserUiService> suUserUiServiceProvider;
   private final Provider<SecurityUIService> securityServiceProvider;
   private final Provider<UtilsUIService> utilsUIServiceProvider;
   private final Provider<SearchUiService> searchUiServiceProvider;

   @Inject
   public AdminConsolePanel(
         Provider<TerminalUIService> terminalUIServiceProvider,
         Provider<SuUserUiService> suUserUiServiceProvider,
         Provider<SecurityUIService> securityServiceProvider,
         Provider<UtilsUIService> utilsUIServiceProvider,
         Provider<SearchUiService> searchUiServiceProvider
         ) {

      this.terminalUIServiceProvider = terminalUIServiceProvider;     
      this.suUserUiServiceProvider = suUserUiServiceProvider;   
      this.securityServiceProvider = securityServiceProvider;
      this.utilsUIServiceProvider = utilsUIServiceProvider;
      this.searchUiServiceProvider = searchUiServiceProvider;

      initializeUI();
   }

   private void initializeUI() {
      setHeading(SystemConsoleMessages.INSTANCE.adminPanel());
      addStyleName("rs-adminconsole");

      wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      add(wrapper);

      init();
   }

   protected void init() {
      wrapper.add(buttonsLayout);
      final SimpleForm form = SimpleForm.getNewInstance();
      form.setHeading(SystemConsoleMessages.INSTANCE.adminPanel());
      wrapper.add(form, new VerticalLayoutData(1, -1, new Margins(50)));
      form.clearButtonBar();
      
      if (!securityServiceProvider.get().hasRight(SystemConsoleGenericTargetIdentifier.class, ReadDto.class))
         return;

      if (securityServiceProvider.get().hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
         /* open terminal button */
         DwTextButton openConsoleBtn = new DwTextButton(SystemConsoleMessages.INSTANCE.openTerminal());
         openConsoleBtn.setIcon(BaseIcon.TERMINAL);
         openConsoleBtn.addSelectHandler(event -> terminalUIServiceProvider.get().displayTerminalWindow());
         form.addButton(openConsoleBtn);
         
         /* open terminal in new window button */
         DwTextButton openConsoleNewWindowBtn = new DwTextButton(SystemConsoleMessages.INSTANCE.openTerminalInNewWindow());
         openConsoleNewWindowBtn.setIcon(BaseIcon.TERMINAL);
         String url = "#" + TerminalUIModule.TERMINAL_ID;
         openConsoleNewWindowBtn.addSelectHandler(event -> utilsUIServiceProvider.get().redirectInPopup(url));
         form.addButton(openConsoleNewWindowBtn);
      }
      
      if (securityServiceProvider.get().hasRight(SuGenericTargetIdentifier.class, ExecuteDto.class)) {
         /* change user button */
         DwTextButton changeUserBtn = new DwTextButton(SuMessages.INSTANCE.userLabel());
         changeUserBtn.setIcon(BaseIcon.USER_SECRET);
         changeUserBtn.addSelectHandler(event -> suUserUiServiceProvider.get().openSuWindow());
         form.addButton(changeUserBtn);
      }
      
      /* global search button */
      DwTextButton searchBtn = new DwTextButton(SystemConsoleMessages.INSTANCE.globalSearch());
      searchBtn.setIcon(BaseIcon.SEARCH);
      searchBtn.addSelectHandler(event -> searchUiServiceProvider.get().displaySearchModule());
      form.addButton(searchBtn);
      
      form.getButtonBar().setPack(BoxLayoutPack.START);
      buttonsLayout.add(form, new HorizontalLayoutData(1000, 20, new Margins(10)));      
   }
   
}