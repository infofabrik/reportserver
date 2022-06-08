package net.datenwerke.rs.core.client.reportexporter.exporter.generic;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public abstract class Export2RTF extends ReportExporterImpl {

   @Override
   public boolean consumesConfiguration(ReportDto report) {
      return true;
   }

   @Override
   public String getExportDescription() {
      return ReportExporterMessages.INSTANCE.Export2RTF();
   }

   @Override
   public String getExportTitle() {
      return "RTF"; //$NON-NLS-1$
   }

   @Override
   public String getOutputFormat() {
      return "RTF"; //$NON-NLS-1$
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.fromFileExtension("rtf").toImageResource();
   }

   @Override
   public boolean hasConfiguration() {
      return false;
   }
   
}