package net.datenwerke.rs.grideditor.service.grideditor.definition;

import java.util.List;

import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport;
import net.datenwerke.security.service.usermanager.entities.User;

public interface GridEditorDefinition {

   public GridEditorData getData(GridEditorReport report, User user, ParameterSet parameters,
         ReportExecutionConfig[] configs) throws ReportExecutorException;

   public void commitChanges(GridEditorReport referenceReport, User user, ParameterSet ps,
         List<GridEditorRecordDto> modified, List<GridEditorRecordDto> modifiedOriginals,
         List<GridEditorRecordDto> deletedRecords, List<GridEditorRecordDto> newRecords);

}
