package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import net.datenwerke.rs.core.client.urlview.hooks.UrlViewObjectInfoPostProcessorHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

public class TsUrlViewObjectInfoPostProcessor implements
		UrlViewObjectInfoPostProcessorHook {

	@Override
	public String postProcess(String[] conf, String url, Object object) {
		if(object instanceof AbstractTsDiskNodeDto)
			url = url.replace("${tsObjectId}", String.valueOf(((AbstractTsDiskNodeDto)object).getId()));
		
		if(object instanceof TsDiskReportReferenceDto)
			return url.replace("${reportId}", String.valueOf(((TsDiskReportReferenceDto)object).getReport().getId()));

		return url;
	}

}
