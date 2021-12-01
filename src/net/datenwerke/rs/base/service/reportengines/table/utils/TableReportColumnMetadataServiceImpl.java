package net.datenwerke.rs.base.service.reportengines.table.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProviderImpl;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.security.service.usermanager.entities.User;

@Singleton
public class TableReportColumnMetadataServiceImpl implements TableReportColumnMetadataService {

	private final SimpleDataSupplier simpleDataSupplyer;

	@Inject
	public TableReportColumnMetadataServiceImpl(
			SimpleDataSupplier simpleDataSupplyer 
	) {

		this.simpleDataSupplyer = simpleDataSupplyer;
	}

	@Override
	public void augmentWithMetadata(TableReport report, User user)
			throws NonFatalException {
		augmentWithMetadata(report.getColumns(), report, user);
	}


	public void augmentWithMetadata(Collection<Column> columns, TableReport report, User user) throws NonFatalException{
		Map<String, ColumnMetadata> columnMetadataMap = createColumnMetadataMap((TableReport) report, user);

		for(Column col : columns){
			if(columnMetadataMap.containsKey(col.getName())){
				ColumnMetadata metadata = columnMetadataMap.get(col.getName());
				col.setDefaultAlias(metadata.getDefaultAlias());
				col.setDescription(metadata.getDescription());
				col.setDefaultPreviewWidth(metadata.getDefaultPreviewWidth());
				col.setSemanticType(metadata.getSemanticType());
				col.setIndexColumn(metadata.isIndexColumn());
			}
		}
	}



	public Map<String, ColumnMetadata> createColumnMetadataMap(TableReport report, User user) throws NonFatalException{
		Map<String, ColumnMetadata> columnMetadataMap = new HashMap<String, ColumnMetadata>();

		if(null == report.getMetadataDatasourceContainer() || null == report.getMetadataDatasourceContainer().getDatasource())
			return columnMetadataMap;

		try {
			RSTableModel res = simpleDataSupplyer.getData(createMetadataDataSourceContainer(report), report, user);
			if(res.getColumnCount() < 3)
				throw new IllegalArgumentException("Expected Metadata Query to return at least 3 columns.");
			
			boolean hasPreviewWidth = res.getColumnCount() > 3;
			boolean hasSemanticType = res.getColumnCount() > 4;
			boolean hasIsIndex = res.getColumnCount() > 5;
			for(RSTableRow s : res.getData()){
				if(null == s.getAt(0))
					continue;
				
				String alias = null == s.getAt(1) ? "" : s.getAt(1).toString();
				String description = null == s.getAt(2) ? "" : s.getAt(2).toString();
				String previewWidth = hasPreviewWidth ? (null == s.getAt(3) ? null : s.getAt(3).toString()) : null;
				String semanticType = hasSemanticType ? (null == s.getAt(4) ? null : s.getAt(4).toString()) : null;
				boolean hasIndex = hasIsIndex ? (null == s.getAt(5) ? false  :  ! "".equals(s.getAt(5))) : false;
				
				ColumnMetadata metadata = new ColumnMetadata(alias, description, previewWidth);
				metadata.setSemanticType(semanticType);
				metadata.setIndexColumn(hasIndex);
				
				columnMetadataMap.put(s.getAt(0).toString(), metadata);
			}
		} catch (ReportExecutorException e) {
			throw new NonFatalException("Could not load Metadata", e);
		}

		return columnMetadataMap;
	}


	private DatasourceContainerProvider createMetadataDataSourceContainer(final TableReport report){
		return new DatasourceContainerProviderImpl(report.getMetadataDatasourceContainer());
	}



}
