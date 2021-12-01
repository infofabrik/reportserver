package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator;

import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

/**
 * Dto Decorator for {@link TsDiskReportReferenceDto}
 *
 */
public class TsDiskReportReferenceDtoDec extends TsDiskReportReferenceDto implements ReportContainerDto {


	private static final long serialVersionUID = 1L;

	public TsDiskReportReferenceDtoDec() {
		super();
	}
	
	@Override
	public String toTypeDescription() {
		if(null == getReport())
			return super.toTypeDescription();
		return getReport().toTypeDescription();
	}
	
	@Override
	public BaseIcon toIcon() {
		if(null == getReport())
			return super.toIcon();
		return getReport().toIcon();
	}

}
