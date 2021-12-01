package net.datenwerke.rs.core.client.reportmanager;

import java.util.List;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public interface ReportManagerUIService {

	public abstract List<ColumnConfig<ReportDto,?>> getColumnConfigForReportGrid();

	public List<ColumnConfig<ReportDto,?>> getColumnConfigForReportGrid(boolean includeVariants, boolean icon);
	
	public ImageResource getIconFor(ReportDto report);
	
	public ImageResource getLinkIconFor(ReportDto report);
	
	public ImageResource getLargeIconFor(ReportDto report);
	
	public ImageResource getLargeLinkIconFor(ReportDto report);

	boolean supportsVariants(ReportDto report);
}
