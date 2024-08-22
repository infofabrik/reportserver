package net.datenwerke.rs.grideditor.service.grideditor.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport;
import net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReportVariant;
import net.datenwerke.rs.grideditor.service.grideditor.locale.GridEditorMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsGridEditorProviderHooker implements UsageStatisticsReportEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String GRID_EDITOR = "GRID_EDITOR";
   private final static String GRID_EDITOR_VAR = "GRID_EDITOR_VAR";
   
   @Inject
   public UsageStatisticsGridEditorProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideReportCountValueEntry(GRID_EDITOR,
            GridEditorMessages.INSTANCE.gridEditorReports(), GridEditorReport.class, GRID_EDITOR_VAR,
            GridEditorMessages.INSTANCE.gridEditorReportVariants(), GridEditorReportVariant.class);
   }

}
