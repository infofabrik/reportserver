package net.datenwerke.rs.base.service.reportengines.table.utils;

import java.util.Collection;
import java.util.Map;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.security.service.usermanager.entities.User;

public interface TableReportColumnMetadataService {

	void augmentWithMetadata(TableReport report, User user) throws NonFatalException;
	
	void augmentWithMetadata(Collection<Column> columns, TableReport report, User user) throws NonFatalException;

	Map<String, ColumnMetadata> createColumnMetadataMap(TableReport realReport, User user) throws NonFatalException;

}
