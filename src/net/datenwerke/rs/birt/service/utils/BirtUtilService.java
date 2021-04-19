package net.datenwerke.rs.birt.service.utils;

import java.util.List;

import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile;

public interface BirtUtilService {

	List<BirtParameterProposal> extractParameters(BirtReportFile reportFile);

}
