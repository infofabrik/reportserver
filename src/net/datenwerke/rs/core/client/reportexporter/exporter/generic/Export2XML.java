package net.datenwerke.rs.core.client.reportexporter.exporter.generic;

import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;

public abstract class Export2XML extends ReportExporterImpl {

	@Override
	public boolean consumesConfiguration(ReportDto report) {
		return true;
	}
	
	@Override
	public String getExportDescription() {
		return ReportExporterMessages.INSTANCE.export2XML();
	}

	@Override
	public String getExportTitle() {
		return "XML"; //$NON-NLS-1$
	}

	@Override
	public String getOutputFormat() {
		return "XML"; //$NON-NLS-1$
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.fromFileExtension("xml").toImageResource();
	}

	@Override
	public boolean hasConfiguration() {
		return false;
	}
}