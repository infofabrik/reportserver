package net.datenwerke.rs.core.client.reportmanager.hookers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportVariantDtoDec;
import net.datenwerke.rs.core.client.parameters.backend.ParameterView;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.BasicReportVariantForm;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.FolderForm;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.SaikuReportVariantForm;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.TableReportVariantForm;
import net.datenwerke.rs.core.client.reportvariants.ui.ReportVariantsView;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec;
import net.datenwerke.security.ext.client.security.ui.SecurityView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker implements MainPanelViewProviderHook {

   private final HookHandlerService hookHandler;

   private final Provider<BasicReportVariantForm> basicReportVariantFormProvider;
   private Provider<TableReportVariantForm> tableReportVariantFormProvider;
   private final Provider<SaikuReportVariantForm> saikuReportVariantFormProvider;
   private final Provider<FolderForm> folderFormProvider;
   private final Provider<ParameterView> parameterTabItemProvider;
   private final Provider<ReportVariantsView> varientsViewProvider;
   private final Provider<SecurityView> securityViewProvider;

   @Inject
   public MainPanelViewProviderHooker(HookHandlerService hookHandler,
         Provider<SaikuReportVariantForm> saikuReportVariantFormProvider,
         Provider<TableReportVariantForm> tableReportVariantFormProvider,
         Provider<BasicReportVariantForm> basicReportVariantFormProvider, Provider<FolderForm> folderFormProvider,
         Provider<ParameterView> parameterTabItemProvider, Provider<ReportVariantsView> varientsViewProvider,
         Provider<SecurityView> securityViewProvider) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.saikuReportVariantFormProvider = saikuReportVariantFormProvider;
      this.tableReportVariantFormProvider = tableReportVariantFormProvider;
      this.basicReportVariantFormProvider = basicReportVariantFormProvider;
      this.folderFormProvider = folderFormProvider;
      this.parameterTabItemProvider = parameterTabItemProvider;
      this.varientsViewProvider = varientsViewProvider;
      this.securityViewProvider = securityViewProvider;
   }

   public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
      if (node instanceof ReportFolderDto)
         return getViewForReportFolder();
      if (node instanceof SaikuReportVariantDtoDec)
         return getViewForSaikuReportVariant();
      if (node instanceof TableReportVariantDtoDec && ((TableReportVariantDtoDec) node).isCubeFlag())
         return getViewForTableReportVariant();
      if (node instanceof ReportVariantDto)
         return getViewForReportVariant();
      if (node instanceof ReportDto)
         return getViewForReport((ReportDto) node);
      return null;
   }

   private List<MainPanelView> getViewForReport(ReportDto report) {
      List<MainPanelView> views = new ArrayList<MainPanelView>();
      for (ReportTypeConfigHook config : hookHandler.getHookers(ReportTypeConfigHook.class))
         if (config.consumes(report))
            views.addAll(config.getAdminViews(report));

      views.addAll(
            Arrays.asList(parameterTabItemProvider.get(), varientsViewProvider.get(), securityViewProvider.get()));
      return views;

   }

   private List<MainPanelView> getViewForSaikuReportVariant() {
      return Arrays.asList(new MainPanelView[] { saikuReportVariantFormProvider.get() });
   }

   private List<MainPanelView> getViewForTableReportVariant() {
      return Arrays.asList(new MainPanelView[] { tableReportVariantFormProvider.get() });
   }

   private List<MainPanelView> getViewForReportVariant() {
      return Arrays.asList(new MainPanelView[] { basicReportVariantFormProvider.get() });
   }

   private List<MainPanelView> getViewForReportFolder() {
      return Arrays.asList(folderFormProvider.get(), securityViewProvider.get());
   }

}
