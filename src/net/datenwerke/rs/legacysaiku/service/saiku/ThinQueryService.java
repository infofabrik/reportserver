package net.datenwerke.rs.legacysaiku.service.saiku;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.olap4j.CellSet;
import org.olap4j.metadata.Cube;
import org.saiku.olap.dto.SimpleCubeElement;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.query2.ThinQuery;
import org.saiku.olap.util.formatter.ICellSetFormatter;
import org.saiku.service.util.KeyValue;
import org.saiku.service.util.QueryContext;

import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.security.service.usermanager.entities.User;

public interface ThinQueryService extends Serializable {

   boolean isOldQuery(String filecontent);

   ThinQuery convertQuery(String xml, Cube cube, SaikuReport report);

   ThinQuery createQuery(ThinQuery tq, Cube cube);

   boolean isMdxDrillthrough(ThinQuery query, SaikuReport report);

   ResultSet drillthrough(ThinQuery tq, SaikuReport report);

   CellDataSet execute(String username, ThinQuery tq, SaikuReport report, Cube cube);

   QueryContext getContext(String name);

   void cancel(String queryName) throws SQLException;

   ThinQuery updateQuery(ThinQuery old, Cube cube, SaikuReport report);

   List<SimpleCubeElement> getResultMetadataMembers(String queryName, Cube cube, boolean preferResult,
         String hierarchyName, String levelName, String searchString, int searchLimit);

   byte[] getExport(String queryName, Cube cube, String type);

   byte[] getExport(String queryName, Cube cube, String type, String formatter);

   ThinQuery zoomIn(String queryName, Cube cub, SaikuReport report, List<List<Integer>> realPositions);

   ResultSet drillthrough(String queryName, SaikuReport report, int maxrows, String returns);

   ResultSet drillthrough(String queryName, SaikuReport report, List<Integer> cellPosition, Integer maxrows,
         String returns);

   byte[] exportResultSetCsv(ResultSet rs);

   byte[] exportResultSetCsv(ResultSet rs, String delimiter, String enclosing, boolean printHeader,
         List<KeyValue<String, String>> additionalColumns);

   CellDataSet getFormattedResult(String query, Cube cube, String format) throws Exception;

   CellDataSet execute(String username, ThinQuery tq, SaikuReport report, Cube cube, String formatter);

   ThinQuery drillacross(String queryName, Cube cub, SaikuReport report, List<Integer> cellPosition,
         Map<String, List<String>> levels);

   String toJSONString(ThinQuery query);

   CellSet executeInternalQuery(String username, ThinQuery query, SaikuReport report, Cube cube) throws Exception;

   CellSet executeInternalQuery(User user, ParameterSet parameterSet, ThinQuery query, SaikuReport report, Cube cube)
         throws Exception;

   void calculateTotals(ThinQuery tq, Cube cub, CellDataSet result, CellSet cellSet, ICellSetFormatter formatter)
         throws Exception;

}