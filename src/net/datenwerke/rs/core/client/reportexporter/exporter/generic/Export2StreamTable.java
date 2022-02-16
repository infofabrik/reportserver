package net.datenwerke.rs.core.client.reportexporter.exporter.generic;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public abstract class Export2StreamTable extends ReportExporterImpl {

   @Override
   public boolean consumesConfiguration(ReportDto report) {
      return true;
   }

   @Override
   public String getExportDescription() {
      return "Stream table";
   }

   @Override
   public String getExportTitle() {
      return "Stream table"; // $NON-NLS-1$
   }

   @Override
   public String getOutputFormat() {
      return "RS_STREAM_TABLE"; //$NON-NLS-1$
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.TABLE.toImageResource();
   }

   @Override
   public boolean hasConfiguration() {
      return false;
   }
   
   @Override
   public boolean supportsDatasink(Class<? extends DatasinkDefinitionDto> datasinkType) {
      return datasinkType.equals(TableDatasinkDto.class);
   }
}