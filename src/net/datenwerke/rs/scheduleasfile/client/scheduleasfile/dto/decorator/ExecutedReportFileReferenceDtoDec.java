package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto Decorator for {@link ExecutedReportFileReferenceDto}
 *
 */
public class ExecutedReportFileReferenceDtoDec extends ExecutedReportFileReferenceDto {

	private static final long serialVersionUID = 1L;

	public ExecutedReportFileReferenceDtoDec() {
		super();
	}
	
	@Override
	public BaseIcon toIcon() {
		return BaseIcon.from(getIconStr());
	}
	
	@Override
	public String toTypeDescription() {
		return getTypeStr();
	}

}
