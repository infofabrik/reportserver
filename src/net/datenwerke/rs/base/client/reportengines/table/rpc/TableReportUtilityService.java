package net.datenwerke.rs.base.client.reportengines.table.rpc;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.inject.name.Named;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.FilterType;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.SelectorPanelLoadConfig;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

@RemoteServiceRelativePath("tablereportutility")
public interface TableReportUtilityService extends RemoteService{

	public ListLoadResult<ColumnDto> getReturnedColumns(TableReportDto tableReport, String executeToken) throws ServerCallFailedException, NonFatalException;

	public TableReportInformation getReportInformation(TableReportDto tableReport,  String executeToken) throws ServerCallFailedException, NonFatalException;

	public Map<String, List<String>> getSpecialParameter(TableReportDto tableReportDto, String executeToken) throws ServerCallFailedException, NonFatalException;
	
	public PagingLoadResult<StringBaseModel> getDistinctValuesPaged(
			SelectorPanelLoadConfig pagingLoadConfig, TableReportDto report,
			ColumnDto column, FilterType type, boolean useFilters, boolean countResults, String executeToken) throws ServerCallFailedException;

	List<ColumnDto> loadColumnDefinition(ReportDto report, DatasourceContainerDto containerDto, String query, String executeToken) throws ServerCallFailedException, NonFatalException;

	PagingLoadResult<ListStringBaseModel> loadData(ReportDto report, DatasourceContainerDto containerDto,
			PagingLoadConfig loadConfig, String query) throws ServerCallFailedException, NonFatalException;

}
