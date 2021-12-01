package net.datenwerke.rs.scripting.client.scripting.hookers;

import static net.datenwerke.rs.utils.stream.shared.StreamUtil.streamOfNullable;

import java.util.HashMap;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.widget.core.client.button.ButtonGroup;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.statusbar.StatusBarUIService;
import net.datenwerke.gxtdto.client.utilityservices.ext.HookableContainer;
import net.datenwerke.gxtdto.client.utilityservices.menu.DwHookableMenu;
import net.datenwerke.gxtdto.client.utilityservices.menu.hooks.MenuBaseHook;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.hooks.ToolbarBaseHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scripting.client.scripting.ScriptingUiService;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScriptingCommandResultProcessorHooker implements CommandResultProcessorHook {

   private final HookHandlerService hookHandlerService;
   private final ToolbarService toolbarService;
   private final ScriptingUiService scriptingService;
   private final StatusBarUIService statusBarService;

   interface ConfigGetter {
      HashMap<String, String> getConfig();
   }

   @Inject
   public ScriptingCommandResultProcessorHooker(
         HookHandlerService hookHandlerService,
         ScriptingUiService scriptingService, 
         ToolbarService toolbarService, 
         StatusBarUIService statusBarService
         ) {

      /* store objects */
      this.hookHandlerService = hookHandlerService;
      this.scriptingService = scriptingService;
      this.toolbarService = toolbarService;
      this.statusBarService = statusBarService;
   }

   @Override
   public void process(CommandResultDto result) {
      for (CommandResultExtensionDto ext : result.getExtensions()) {
         if (ext instanceof AddMenuEntryExtensionDto)
            addMenuEntry((AddMenuEntryExtensionDto) ext);
         else if (ext instanceof AddMenuSeparatorEntryExtensionDto)
            addMenuSeparator((AddMenuSeparatorEntryExtensionDto) ext);
         else if (ext instanceof AddToolbarEntryExtensionDto)
            addToolbarEntry((AddToolbarEntryExtensionDto) ext);
         else if (ext instanceof AddStatusbBarLabelExtensionDto)
            addStatusBarEntry((AddStatusbBarLabelExtensionDto) ext);
         else if (ext instanceof AddReportExportFormatProviderDto)
            addRepotExportFormat((AddReportExportFormatProviderDto) ext);
      }

   }

   protected void addStatusBarEntry(AddStatusbBarLabelExtensionDto ext) {
      if (ext.isLeft()) {
         if (ext.isClear())
            statusBarService.clearLeft();
         statusBarService.addLeft(ext.getLabel(), ext.getIcon());
      } else {
         if (ext.isClear())
            statusBarService.clearRight();
         statusBarService.addRight(ext.getLabel(), ext.getIcon());
      }
   }

   protected void addToolbarEntry(final AddToolbarEntryExtensionDto ext) {
      hookHandlerService.attachHooker(ToolbarBaseHook.class, new ToolbarBaseHook() {

         @Override
         public boolean consumes(DwHookableToolbar toolbar) {
            if (null == ext.getToolbarName())
               return false;
            if (!ext.getToolbarName().equals(toolbar.getContainerName()))
               return false;

            if (!conditionsHold(ext, toolbar))
               return false;

            return true;
         }

         @Override
         public void attachToRight(DwHookableToolbar dwToolbar) {
            if (!ext.isLeft())
               attach(dwToolbar, ext);
         }

         @Override
         public void attachToLeft(DwHookableToolbar dwToolbar) {
            if (ext.isLeft())
               attach(dwToolbar, ext);
         }
      });
   }

   protected boolean conditionsHold(final AddToolbarEntryExtensionDto ext, final HookableContainer container) {
      return !streamOfNullable(ext.getDisplayConditions())
         .filter(cond -> !conditionHolds(cond, container))
         .findAny().isPresent();
   }

   protected boolean conditionsHold(final AddMenuEntryExtensionDto ext, final HookableContainer container) {
      return !streamOfNullable(ext.getDisplayConditions())
         .filter(cond -> !conditionHolds(cond, container))
         .findAny().isPresent();
   }

   protected boolean conditionHolds(DisplayConditionDto cond, HookableContainer container) {
      if (null == cond.getValue() || null == cond.getPropertyName())
         return true;

      if (null == container.getHookConfig())
         return true;

      switch (cond.getType()) {
      case EQUALS:
         String name = cond.getPropertyName();
         String value = String.valueOf(container.getHookConfig().get(name));

         return cond.getValue().equals(value);
      }

      return false;
   }

   protected void addMenuSeparator(final AddMenuSeparatorEntryExtensionDto ext) {
      hookHandlerService.attachHooker(MenuBaseHook.class, new MenuBaseHook() {

         @Override
         public boolean consumes(DwHookableMenu dwMenu) {
            if (null == ext.getMenuName())
               return false;
            return ext.getMenuName().equals(dwMenu.getContainerName());
         }

         @Override
         public boolean attachTo(DwHookableMenu dwMenu, boolean addSeparator) {
            dwMenu.add(new SeparatorMenuItem());
            return false;
         }
      });
   }

   protected void addMenuEntry(final AddMenuEntryExtensionDto ext) {
      hookHandlerService.attachHooker(MenuBaseHook.class, new MenuBaseHook() {

         @Override
         public boolean consumes(DwHookableMenu dwMenu) {
            if (null == ext.getMenuName())
               return false;

            if (!conditionsHold(ext, dwMenu))
               return false;

            return ext.getMenuName().equals(dwMenu.getContainerName());
         }

         @Override
         public boolean attachTo(final DwHookableMenu dwMenu, boolean addSeparator) {
            if (addSeparator)
               dwMenu.add(new SeparatorMenuItem());
            MenuItem menuItem = createMenuItem(ext, () -> dwMenu.getHookConfig());
            dwMenu.add(menuItem);
            return false;
         }
      });
   }

   protected void attach(final DwHookableToolbar dwToolbar, final AddToolbarEntryExtensionDto ext) {
      if (null != ext.getGroupEntries() && 0 < ext.getGroupEntries().size()) {
//			ButtonGroup group = new ButtonGroup(ext.getColumns());
         final ButtonGroup group = new ButtonGroup();
         group.setHeading(ext.getLabel());
         
         ext.getGroupEntries()
            .stream()
            .map(groupEntry -> createToolbarButton(dwToolbar, groupEntry))
            .forEach(group::add);
         
         dwToolbar.add(group);
      } else
         dwToolbar.add(createToolbarButton(dwToolbar, ext));

   }

   private DwTextButton createToolbarButton(final DwHookableToolbar dwToolbar, final AddToolbarEntryExtensionDto ext) {

      DwTextButton btn = null;
      if (ext.isLarge())
         btn = toolbarService.createLargeButtonTop(ext.getLabel(), BaseIcon.from(ext.getIcon()));
      else
         btn = toolbarService.createSmallButtonLeft(ext.getLabel(), BaseIcon.from(ext.getIcon()));

      btn.addSelectHandler(event -> {
         executeJavaScript(ext.getJavaScript(), ext.getArguments(), dwToolbar.getHookConfig());
         executeScript(ext.getScriptLocation(), ext.getArguments(), dwToolbar.getHookConfig());
      });

      if (null != ext.getSubMenuEntries() && 0 < ext.getSubMenuEntries().size()) {
         final Menu menu = new DwMenu();
         btn.setMenu(menu);
         if (ext.isLarge())
            btn.setArrowAlign(ButtonArrowAlign.BOTTOM);
         else
            btn.setArrowAlign(ButtonArrowAlign.RIGHT);

         ext.getSubMenuEntries()
            .forEach(entry -> menu.add(createMenuItem(entry, () -> dwToolbar.getHookConfig())));
      }

      return btn;
   }

   protected MenuItem createMenuItem(final AddMenuEntryExtensionDto ext, final ConfigGetter configGetter) {
      MenuItem item;
      if (null != ext.getIcon())
         item = new DwMenuItem(ext.getLabel(), BaseIcon.from(ext.getIcon()));
      else
         item = new DwMenuItem(ext.getLabel());

      if (null != ext.getScriptLocation()) {
         item.addSelectionHandler(event -> {
            executeJavaScript(ext.getJavaScript(), ext.getArguments(), configGetter.getConfig());
            executeScript(ext.getScriptLocation(), ext.getArguments(), configGetter.getConfig());
         });
      } else if (null != ext.getSubMenuEntries() && 0 < ext.getSubMenuEntries().size()) {
         final Menu submenu = new DwMenu();
         item.setSubMenu(submenu);

         ext.getSubMenuEntries()
            .stream()
            .map(subEntry -> createMenuItem(subEntry, configGetter))
            .forEach(submenu::add);
      }

      return item;
   }

   protected void executeJavaScript(String script, String args, HashMap<String, String> config) {
      try {
         if (null != script && !"".equals(script.trim()))
            doEval(script, args, config);
      } catch (Exception e) {
         new SimpleErrorDialog(BaseMessages.INSTANCE.error(), e.getMessage()).show();
      }
   }

   protected native void doEval(String javascript, String args, HashMap<String, String> config) /*-{
		var f = eval(javascript);
		// is function?
		var getType = {};
		if (f && getType.toString.call(f) === '[object Function]') {
			f(args, config);
		}
   }-*/;

   protected void executeScript(String scriptLocation, String args, HashMap<String, String> config) {
      if (null != scriptLocation && !"".equals(scriptLocation.trim()))
         scriptingService.executeScript(scriptLocation, args, config);
   }

   protected void addRepotExportFormat(final AddReportExportFormatProviderDto ext) {
      ReportExporterImpl exporter = new ReportExporterImpl() {

         @Override
         public boolean hasConfiguration() {
            return false;
         }

         @Override
         public String getOutputFormat() {
            return ext.getOutputFormat();
         }

         @Override
         public ImageResource getIcon() {
            if (null == ext.getIcon() || "".equals(ext.getIcon()))
               return null;
            return IconHelper.getImageResource(UriUtils.fromString(ext.getIcon()), 16, 16);
         }

         @Override
         public String getExportTitle() {
            return ext.getTitle();
         }

         @Override
         public String getExportDescription() {
            return ext.getDescription();
         }

         @Override
         public boolean consumesConfiguration(ReportDto report) {
            return true;
         }

         @Override
         public boolean consumes(ReportDto report) {
            if (null == report || null == ext.getReportType())
               return false;
            Class<?> type = report.getClass();
            while (null != type) {
               if (type.equals(ext.getReportType().getClass()))
                  break;
               type = type.getSuperclass();
            }
            if (null == type)
               return false;

            return true;
         }

         @Override
         public boolean isSkipDownload() {
            return ext.isSkipDownload();
         }
      };

      hookHandlerService.attachHooker(ReportExporterExportReportHook.class,
            new ReportExporterExportReportHook(exporter), HookHandlerService.PRIORITY_HIGH);
   }
}
