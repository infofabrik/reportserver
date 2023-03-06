package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;

public interface ExporterHelper {

   List<Column> getExportedColumns(TableReport report, TableDefinition td);

   ImmutablePair<String[], Boolean[]> getNullFormats(List<Column> columns, CellFormatter[] cellFormatters,
         TableDefinition td);
}
