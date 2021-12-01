package net.datenwerke.rs.base.client.reportengines.table.rpc;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.FilterType;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.SelectorPanelLoadConfig;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface TableReportUtilityServiceAsync {

	Request getReturnedColumns(TableReportDto tableReport, String executeToken, 
			AsyncCallback<ListLoadResult<ColumnDto>> callback);

	Request getSpecialParameter(TableReportDto tableReport, String executeToken, AsyncCallback<Map<String, List<String>>> callback);

	Request getReportInformation(TableReportDto tableReport, String executeToken,
			AsyncCallback<TableReportInformation> callback);

	Request getDistinctValuesPaged(SelectorPanelLoadConfig pagingLoadConfig,
			TableReportDto report, ColumnDto column, FilterType type,
			boolean useFilters, boolean countResults, 
			String executeToken, 
			AsyncCallback<PagingLoadResult<StringBaseModel>> callback);

	void loadColumnDefinition(ReportDto report, DatasourceContainerDto containerDto, String query, String executeToken, 
			AsyncCallback<List<ColumnDto>> transformAndKeepCallback);

	void loadData(
			ReportDto report,
			DatasourceContainerDto containerDto,
			PagingLoadConfig loadConfig,
			String query,
			AsyncCallback<PagingLoadResult<ListStringBaseModel>> transformAndKeepCallback);

}
