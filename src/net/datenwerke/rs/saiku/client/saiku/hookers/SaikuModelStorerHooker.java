package net.datenwerke.rs.saiku.client.saiku.hookers;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.hooks.PrepareReportModelForStorageOrExecutionHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public class SaikuModelStorerHooker implements PrepareReportModelForStorageOrExecutionHook {

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof SaikuReportDto || ((report instanceof TableReportDto) && ((TableReportDto)report).isCubeFlag());
	}

	@Override
	public void prepareForExecutionOrStorage(ReportDto report, String executeToken) {
		String json = getModel("rs-saiku-" + executeToken);
		
		if(report instanceof SaikuReportDto)
			((SaikuReportDto)report).setQueryXml(json);
		else 
			((TableReportDto)report).setCubeXml(json);
	}
	
	protected native String getModel(String name) /*-{
	    // get document
	    var f = $doc.getElementById(name);
	    
	    if (null === f)
	    	return "";
	    
	    d = (f.contentWindow || f.contentDocument);
	    var json = d.RsSaikuWorkspace.query.model;
	    return JSON.stringify(json);
	}-*/; 

}
