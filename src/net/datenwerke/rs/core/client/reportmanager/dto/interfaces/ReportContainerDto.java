package net.datenwerke.rs.core.client.reportmanager.dto.interfaces;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportContainerDto {

   public ReportDto getReport();

   public String toDisplayTitle();
}
