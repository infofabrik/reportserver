package net.datenwerke.rs.core.client.reportexporter.exporter.generic;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public abstract class Export2JSON extends ReportExporterImpl {

   @Override
   public boolean consumesConfiguration(ReportDto report) {
      return true;
   }

   @Override
   public String getExportDescription() {
      return ReportExporterMessages.INSTANCE.export2JSON();
   }

   @Override
   public String getExportTitle() {
      return "JSON"; //$NON-NLS-1$
   }

   @Override
   public String getOutputFormat() {
      return "JSON"; //$NON-NLS-1$
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.fromFileExtension("json").toImageResource();
   }

   @Override
   public boolean hasConfiguration() {
      return false;
   }
   
}