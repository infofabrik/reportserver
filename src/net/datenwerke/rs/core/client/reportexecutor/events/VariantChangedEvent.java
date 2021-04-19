package net.datenwerke.rs.core.client.reportexecutor.events;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;


public class VariantChangedEvent implements ReportExecutorEvent {


	private ReportDto variant;

	public void setVariant(ReportDto variant) {
		this.variant = variant;
	}

	public ReportDto getVariant() {
		return variant;
	}
	
}
