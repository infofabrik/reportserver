package net.datenwerke.rs.dashboard.client.dashboard.dadgets;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeLoaderDao;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiService;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.i.LibrarySpeficDrawer;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.LibraryDadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardTreeDadgets;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class LibraryDadgetProcessor implements DadgetProcessorHook {

   private final UITree dashboardTree;
   private final HookHandlerService hookHandler;
   private final DashboardTreeLoaderDao dashboardTreeLoader;
   private final DashboardUiService dashboardService;

   @Inject
   public LibraryDadgetProcessor(@DashboardTreeDadgets UITree dashboardTree, DashboardTreeLoaderDao dashboardTreeLoader,
         HookHandlerService hookHandler, DashboardUiService dashboardService) {
      this.dashboardTree = dashboardTree;
      this.dashboardTreeLoader = dashboardTreeLoader;
      this.hookHandler = hookHandler;
      this.dashboardService = dashboardService;
   }

   @Override
   public BaseIcon getIcon() {
      return BaseIcon.BOOK;
   }

   @Override
   public boolean isRedrawOnMove() {
      return false;
   }

   @Override
   public String getTitle() {
      return DashboardMessages.INSTANCE.libraryDadgetTitle();
   }

   @Override
   public String getDescription() {
      return DashboardMessages.INSTANCE.libraryDadgetDescription();
   }

   @Override
   public boolean consumes(DadgetDto dadget) {
      return dadget instanceof LibraryDadgetDto;
   }

   @Override
   public DadgetDto instantiateDadget() {
      return new LibraryDadgetDtoDec();
   }

   @Override
   public void draw(DadgetDto dadget, DadgetPanel panel) {
      DadgetNodeDto dadgetNode = ((LibraryDadgetDto) dadget).getDadgetNode();
      if (null != dadgetNode) {
         DadgetDto d = dadgetNode.getDadget();
         if (null != d) {
            for (final DadgetProcessorHook processor : hookHandler.getHookers(DadgetProcessorHook.class)) {
               if (processor.consumes(d) && processor.hasConfigDialog()) {
                  if (processor instanceof LibrarySpeficDrawer)
                     ((LibrarySpeficDrawer) processor).drawForLibrary((LibraryDadgetDto) dadget, d, panel);
                  else
                     processor.draw(d, panel);
                  panel.setHeading(dadgetNode.getName());
                  return;
               }
            }
         }
      }
   }

   @Override
   public void displayConfigDialog(final DadgetDto dadget, final DadgetConfigureCallback dadgetConfigureCallback) {
      final DwWindow window = new DwWindow();
      window.setHeading(DashboardMessages.INSTANCE.configDadgetTitle());
      window.setHeaderIcon(BaseIcon.COG);
      window.setSize("450px", "240px");

      SimpleForm form = SimpleForm.getInlineInstance();
      form.setFieldWidth(400);
      form.setLabelWidth(90);

      form.addField(DadgetNodeDto.class, LibraryDadgetDtoPA.INSTANCE.dadgetNode(), DashboardMessages.INSTANCE.dadget(),
            new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return dashboardTree;
               }
            });

      form.addField(Long.class, LibraryDadgetDtoPA.INSTANCE.reloadInterval(),
            DashboardMessages.INSTANCE.reloadIntervalLabel());
      form.addField(Integer.class, LibraryDadgetDtoPA.INSTANCE.height(), DashboardMessages.INSTANCE.heightLabel());

      form.bind(dadget);

      window.add(form);

      DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
      window.addButton(cancelBtn);
      cancelBtn.addSelectHandler(event -> {
         window.hide();
         dadgetConfigureCallback.cancelled();
      });

      DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
      window.addButton(submitBtn);
      submitBtn.addSelectHandler(event -> {
         window.hide();

         dashboardTreeLoader.loadNode(((LibraryDadgetDto) dadget).getDadgetNode(),
               new RsAsyncCallback<AbstractNodeDto>() {
                  public void onSuccess(AbstractNodeDto result) {
                     ((LibraryDadgetDto) dadget).setDadgetNode((DadgetNodeDto) result);

                     dadgetConfigureCallback.configuringDone();
                  };
               });
      });

      window.show();
   }

   @Override
   public Widget getAdminConfigDialog(DadgetDto dadget, SimpleForm wrappingForm) {
      return null;
   }

   @Override
   public boolean supportsDadgetLibrary() {
      return false;
   }

   @Override
   public boolean readyToDisplayParameters(DadgetPanel dadgetPanel) {
      LibraryDadgetDto dadget = (LibraryDadgetDto) dadgetPanel.getDadget();
      DadgetNodeDto dadgetNode = dadget.getDadgetNode();
      if (null != dadgetNode) {
         DadgetDto d = dadgetNode.getDadget();
         if (d instanceof ReportDadgetDto) {
            ReportDto report = ((ReportDadgetDto) d).getReport();
            return null != report && !report.getParameterInstances().isEmpty();
         }
      }
      return false;
   }

   @Override
   public boolean hasConfigDialog() {
      return true;
   }

   @Override
   public void addTools(DadgetPanel dadgetPanel) {
      dashboardService.addParameterToolButtonTo(dadgetPanel, this);
   }
}
