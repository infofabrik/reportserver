package net.datenwerke.rs.legacysaiku.service.saiku;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

import org.olap4j.metadata.Cube;
import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.SaikuMember;
import org.legacysaiku.olap.dto.SaikuTag;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.olap.query.IQuery;
import org.legacysaiku.olap.util.formatter.ICellSetFormatter;
import org.legacysaiku.service.util.KeyValue;
import org.legacysaiku.service.util.exception.SaikuServiceException;

public interface OlapQueryService {


	byte[] exportResultSetCsv(ResultSet rs);

	byte[] exportResultSetCsv(ResultSet rs, String delimiter, String enclosing, boolean printHeader, List<KeyValue<String, String>> additionalColumns);

	CellDataSet execute(IQuery query, String formatter);

	CellDataSet execute(IQuery query, ICellSetFormatter formatter);

	CellDataSet execute(IQuery query);

	IQuery qm2mdx(IQuery query);

	CellDataSet executeMdx(IQuery query, String mdx);

	CellDataSet executeMdx(IQuery query, String mdx, ICellSetFormatter formatter);

	byte[] getExport(IQuery query, String type, ICellSetFormatter formatter);

	List<SaikuDimensionSelection> getAxisSelection(IQuery query, String axis);

	List<SaikuMember> getResultMetadataMembers(IQuery query, boolean preferResult, String dimensionName, String hierarchyName, String levelName);

	ResultSet explain(IQuery query);

	ResultSet drillthrough(IQuery queryName, int maxrows, String returns);

	ResultSet drillthrough(IQuery query, List<Integer> cellPosition, Integer maxrows, String returns);

	IQuery swapAxes(IQuery query);

	void setCellValue(IQuery query, List<Integer> position, String value, String allocationPolicy);

	SaikuDimensionSelection getAxisDimensionSelections(IQuery query, String axis, String dimension);

	void moveDimension(IQuery query, String axisName, String dimensionName, int position);

	void removeDimension(IQuery query, String axisName, String dimensionName);

	boolean includeMember(IQuery query, String dimensionName, String uniqueMemberName, String selectionType, int memberposition);

	boolean removeMember(IQuery query, String dimensionName, String uniqueMemberName, String selectionType) throws SaikuServiceException;

	boolean includeLevel(IQuery queryName, String dimensionName, String uniqueHierarchyName, String uniqueLevelName);

	boolean removeLevel(IQuery query, String dimensionName, String uniqueHierarchyName, String uniqueLevelName);

	void sortAxis(IQuery query, String axisName, String sortLiteral, String sortOrder);

	boolean includeChildren(IQuery query, String dimensionName, String uniqueMemberName);

	void clearSort(IQuery query, String axisName);

	void limitAxis(IQuery query, String axisName, String limitFunction, String n, String sortLiteral);

	void clearLimit(IQuery query, String axisName);

	void filterAxis(IQuery query, String axisName, String filterCondition);

	void clearFilter(IQuery query, String axisName);

	SaikuTag createTag(IQuery query, String tagName, List<List<Integer>> cellPositions);

	IQuery createNewOlapQuery(String queryName, SaikuReport report, String connectionName, Cube cube) throws SQLException;

	IQuery createNewOlapQuery(String queryName, SaikuReport report, String connectionName, Cube cube, String xml);

	IQuery simulateTag(IQuery query, SaikuTag tag);

	byte[] getExport(IQuery query, String type, String formatter);

	byte[] exportResultSetXLS(ResultSet rs);

}
