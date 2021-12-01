package net.datenwerke.rs.legacysaiku.service.saiku;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import mondrian3.rolap.RolapConnection;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.base.service.reportengines.table.utils.RSTableToXLS;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ExcelExportException;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.olap4j.AllocationPolicy;
import org.olap4j.Axis;
import org.olap4j.CellSet;
import org.olap4j.CellSetAxis;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.Position;
import org.olap4j.Scenario;
import org.olap4j.impl.IdentifierParser;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.mdx.ParseTreeWriter;
import org.olap4j.mdx.SelectNode;
import org.olap4j.mdx.parser.impl.DefaultMdxParserImpl;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Level.Type;
import org.olap4j.metadata.Member;
import org.olap4j.query.LimitFunction;
import org.olap4j.query.Query;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;
import org.olap4j.query.Selection;
import org.olap4j.query.Selection.Operator;
import org.olap4j.query.SortOrder;
import org.legacysaiku.olap.dto.SaikuCube;
import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.SaikuMember;
import org.legacysaiku.olap.dto.SaikuSelection;
import org.legacysaiku.olap.dto.SaikuTag;
import org.legacysaiku.olap.dto.SaikuTuple;
import org.legacysaiku.olap.dto.SaikuTupleDimension;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.olap.query.IQuery;
import org.legacysaiku.olap.query.IQuery.QueryType;
import org.legacysaiku.olap.query.MdxQuery;
import org.legacysaiku.olap.query.OlapQuery;
import org.legacysaiku.olap.query.QueryDeserializer;
import org.legacysaiku.olap.util.ObjectUtil;
import org.legacysaiku.olap.util.OlapResultSetUtil;
import org.legacysaiku.olap.util.SaikuUniqueNameComparator;
import org.legacysaiku.olap.util.exception.SaikuOlapException;
import org.legacysaiku.olap.util.formatter.CellSetFormatter;
import org.legacysaiku.olap.util.formatter.FlattenedCellSetFormatter;
import org.legacysaiku.olap.util.formatter.HierarchicalCellSetFormatter;
import org.legacysaiku.olap.util.formatter.ICellSetFormatter;
import org.legacysaiku.service.util.KeyValue;
import org.legacysaiku.service.util.exception.SaikuServiceException;
import org.legacysaiku.service.util.export.CsvExporter;
import org.legacysaiku.service.util.export.ExcelExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OlapQueryServiceImpl implements OlapQueryService {

	private static final AtomicLong ID_GENERATOR = new AtomicLong();
	private static final Logger log = LoggerFactory.getLogger(OlapQueryServiceImpl.class);

	private OlapUtilService olapUtilService;


	@Inject
	public OlapQueryServiceImpl(
			OlapUtilService olapUtilService
			) {
		this.olapUtilService = olapUtilService;
	}


	@Override
	public IQuery createNewOlapQuery(String queryName, SaikuReport report, String connectionName, Cube cube) throws SQLException {
		OlapConnection con = getOlapConnection(cube);
		SaikuCube saikuCube = new SaikuCube(connectionName, cube.getUniqueName(), cube.getName(), cube.getCaption(), cube.getSchema().getCatalog().getName(), cube.getSchema().getName());

		return new OlapQuery(new Query(queryName, cube), con, saikuCube);
	}

	@Override
	public IQuery createNewOlapQuery(String queryName, SaikuReport report, String connectionName, Cube cube, String xml) {
		try {
			OlapConnection con = getOlapConnection(cube);
			QueryDeserializer qd = new QueryDeserializer();
			
			return qd.unparse(xml, con);
		} catch (Exception e) {
			throw new SaikuServiceException("Error creating query from xml",e);
		}
	}

	private OlapConnection getOlapConnection(Cube cube) {
		return cube.getSchema().getCatalog().getDatabase().getOlapConnection();
	}
	
	@Override
	public byte[] getExport(IQuery query, String type, String formatter) {
		formatter = formatter == null ? "" : formatter.toLowerCase();
		if (formatter.equals("flat")) {
			return getExport(query, type, new CellSetFormatter());			
		}else if (formatter.equals("flattened")) {
			return getExport(query, type, new FlattenedCellSetFormatter());
		} else if (formatter.equals("hierarchical")) {
			return getExport(query, type, new HierarchicalCellSetFormatter());
		}

		return getExport(query, type, new FlattenedCellSetFormatter());
	}

	@Override
	public byte[] getExport(IQuery query, String type, ICellSetFormatter formatter) {
		if (type != null) {
			
			CellSet rs = query.getCellset();
			List<SaikuDimensionSelection> filters = new ArrayList<SaikuDimensionSelection>();
			
			if (query.getType().equals(QueryType.QM)) {
				filters = getAxisSelection(query, "FILTER");
			}
			if (type.toLowerCase().equals("xls")) {
				return ExcelExporter.exportExcel(rs, formatter, filters);
			}
			if (type.toLowerCase().equals("csv")) {
				return CsvExporter.exportCsv(rs,",","\"", formatter);
			}
		}
		return new byte[0];
	}
	
	@Override
	public List<SaikuDimensionSelection> getAxisSelection(IQuery query, String axis) {
		List<SaikuDimensionSelection> dimsel = new ArrayList<SaikuDimensionSelection>();
		try {
			QueryAxis qaxis = query.getAxis(axis);
			if (qaxis != null) {
				for (QueryDimension dim : qaxis.getDimensions()) {
					dimsel.add(ObjectUtil.convertDimensionSelection(dim));
				}
			}
		} catch (SaikuOlapException e) {
			throw new SaikuServiceException("Cannot get dimension selections",e);
		}
		return dimsel;
	}
	
	@Override
	public CellDataSet execute(IQuery query) {
		return execute(query ,new HierarchicalCellSetFormatter());
	}

	
	@Override	
	public CellDataSet execute(IQuery query, String formatter) {
		formatter = formatter == null ? "" : formatter.toLowerCase(); 
		if(formatter.equals("flat")) {
			return execute(query, new CellSetFormatter());
		}
		else if (formatter.equals("hierarchical")) {
			return execute(query, new HierarchicalCellSetFormatter());
		}
		else if (formatter.equals("flattened")) {
			return execute(query, new FlattenedCellSetFormatter());
		}
		return execute(query, new FlattenedCellSetFormatter());
	}

	@Override
	public CellDataSet execute(IQuery query, ICellSetFormatter formatter) {
		String runId = "runId:" + ID_GENERATOR.getAndIncrement();
		try {
//			System.out.println("Execute: ID " + Thread.currentThread().getId() + " Name: " + Thread.currentThread().getName());
			
			OlapConnection con = query.getCube().getSchema().getCatalog().getDatabase().getOlapConnection();
			
			
			Long start = (new Date()).getTime();
			if (query.getScenario() != null) {
				log.debug(runId + "\tQuery: " + query.getName() + " Setting scenario:" + query.getScenario().getId());
				con.setScenario(query.getScenario());
			}

			if (query.getTag() != null) {
				query = applyTag(query, con, query.getTag());
			}
			
			String mdx = query.getMdx();
	        log.debug(runId + "\tType:" + query.getType() + ":\n" + mdx);
			
			CellSet cellSet =  query.execute();
			Long exec = (new Date()).getTime();

			if (query.getScenario() != null) {
				log.debug("Query (" + query.getName() + ") removing scenario:" + query.getScenario().getId());
				con.setScenario(null);
			}

			CellDataSet result = OlapResultSetUtil.cellSet2Matrix(cellSet,formatter);
			Long format = (new Date()).getTime();
			log.debug(runId + "\tSize: " + result.getWidth() + "/" + result.getHeight() + "\tExecute:\t" + (exec - start)
					+ "ms\tFormat:\t" + (format - exec) + "ms\t Total: " + (format - start) + "ms");
			result.setRuntime(new Double(format - start).intValue());
			query.storeCellset(cellSet);
			return result;
		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				String error = ExceptionUtils.getRootCauseMessage(e);
				log.info(runId + "\tException: " + error); 
			}
			throw new SaikuServiceException(runId + "\tCan't execute query: " + query.getName(),e);
		} catch (Error e) {
			if (log.isInfoEnabled()) {
				String error = ExceptionUtils.getRootCauseMessage(e);
				log.info(runId + "\tError: " + error); 
			}
			throw new SaikuServiceException(runId + "\tCan't execute query: " + query.getName(),e);
		}
	}
	
	private IQuery applyTag(IQuery query, OlapConnection con, SaikuTag t) throws Exception {
		String xml = query.toXml();
		QueryDeserializer qd = new QueryDeserializer();
		query = qd.unparse(xml, con);
		
		List<SaikuTupleDimension> doneDimension = new ArrayList<SaikuTupleDimension>();
		Map<String,QueryDimension> dimensionMap = new HashMap<String,QueryDimension>();
		if (t.getSaikuTupleDimensions() != null) {
			for (SaikuTupleDimension st : t.getSaikuTupleDimensions()) {
				if (!doneDimension.contains(st)) {
					QueryDimension dim = query.getDimension(st.getName());
					dimensionMap.put(st.getUniqueName(), dim);
					dim.clearExclusions();
					dim.clearInclusions();
					query.moveDimension(dim, null);
					doneDimension.add(st);
				}
			}
			if (t.getSaikuTupleDimensions().size() > 0) {
				SaikuTupleDimension rootDim = t.getSaikuTupleDimensions().get(0);
				QueryDimension dim = query.getDimension(rootDim.getName());
				query.moveDimension(dim, Axis.COLUMNS);

				for (SaikuTuple tuple : t.getSaikuTuples()) {
					SaikuMember m = tuple.getSaikuMember(rootDim.getUniqueName());
					List<SaikuMember> others = tuple.getOtherSaikuMembers(rootDim.getUniqueName());
					Selection sel = dim.createSelection(IdentifierParser.parseIdentifier(m.getUniqueName()));
					for (SaikuMember context : others) {
						QueryDimension otherDim = dimensionMap.get(context.getDimensionUniqueName());
						query.moveDimension(otherDim, Axis.COLUMNS);
						Selection ctxSel = otherDim.createSelection(IdentifierParser.parseIdentifier(context.getUniqueName()));
						sel.addContext(ctxSel);
					}
					dim.getInclusions().add(sel);
				}
			}
		}
		if (t.getSaikuDimensionSelections() != null) {
			for (SaikuDimensionSelection dimsel : t.getSaikuDimensionSelections()) {
				if (!dimsel.getName().equals("Measures")) {
					QueryDimension filterDim = query.getDimension(dimsel.getName());
					query.moveDimension(filterDim, Axis.FILTER);
					filterDim.clearInclusions();
					for (SaikuSelection ss : dimsel.getSelections()) {
						if (ss.getType() == SaikuSelection.Type.MEMBER) {
							Selection sel = filterDim.createSelection(IdentifierParser.parseIdentifier(ss.getUniqueName()));
							if (!filterDim.getInclusions().contains(sel)) {
								filterDim.getInclusions().add(sel);
							}
						}
					}
					// TODO: Move it to columns since drilling through with 2 filter items of the same dimension doesn't work
//					if (filterDim.getInclusions().size() > 1) {
//						query.moveDimension(filterDim, Axis.COLUMNS);
//					}
				}
			}
		}
		
		return query;
	}

	@Override
	public MdxQuery qm2mdx(IQuery query) {
		OlapConnection con = query.getCube().getSchema().getCatalog().getDatabase().getOlapConnection();
		return new MdxQuery(con, query.getSaikuCube(), query.getName(), query.getMdx());
	}
	
	@Override
	public CellDataSet executeMdx(IQuery query, String mdx) {
		query = qm2mdx(query);
		query.setMdx(mdx);
		return execute(query, new HierarchicalCellSetFormatter());
	}

	@Override
	public CellDataSet executeMdx(IQuery query, String mdx, ICellSetFormatter formatter) {
		query = qm2mdx(query);
		query.setMdx(mdx);
		return execute(query, formatter);
	}

	@Override
	public List<SaikuMember> getResultMetadataMembers(IQuery query, boolean preferResult, String dimensionName, String hierarchyName, String levelName) {
		CellSet cs = query.getCellset();
		List<SaikuMember> members = new ArrayList<SaikuMember>();
		Set<Level> levels = new HashSet<Level>();
		
		if (cs != null && preferResult) {
			for (CellSetAxis axis : cs.getAxes()) {
				int posIndex = 0;
				for (Hierarchy h : axis.getAxisMetaData().getHierarchies()) {
					if (h.getUniqueName().equals(hierarchyName)) {
						log.debug("Found hierarchy in the result: " + hierarchyName);
						if (h.getLevels().size() == 1) {
							break;
						}
						Set<Member> mset = new HashSet<Member>();
						for (Position pos : axis.getPositions()) {
							Member m = pos.getMembers().get(posIndex);
							if (!m.getLevel().getLevelType().equals(Type.ALL)) {
								levels.add(m.getLevel());
							}
							if (m.getLevel().getUniqueName().equals(levelName)) {
								mset.add(m);
							}
						}
						
						members = ObjectUtil.convertMembers(mset);
						Collections.sort(members, new SaikuUniqueNameComparator());
						
						break;
					}
					posIndex++;
				}
			}
			log.debug("Found members in the result: " + members.size());
			
		}
		if (cs == null || !preferResult || members.size() == 0 || levels.size() == 1) {
			try {
				members = ObjectUtil.convertMembers(olapUtilService.getAllMembers(query.getCube(), dimensionName, hierarchyName, levelName));
			} catch (OlapException e) {
				e.printStackTrace();
			}
		}
		
		return members;
	}
	
	@Override
	public ResultSet explain(IQuery query) {
		OlapStatement stmt = null;
		try {
			final OlapConnection con = getOlapConnection(query.getCube());
			if (!con.isWrapperFor(RolapConnection.class))
				throw new IllegalArgumentException("Cannot only get explain plan for Mondrian connections");

			stmt = con.createStatement();
			String mdx = query.getMdx();
			mdx = "EXPLAIN PLAN FOR \n" + mdx;
			ResultSet rs = stmt.executeQuery(mdx);
			return rs;

		} catch (Exception e) {
			throw new SaikuServiceException("Error EXPLAIN: " + query.getName(),e);
		} finally {
			try {
				if (stmt != null)  stmt.close();
			} catch (Exception e) {}
		}
	}
	
	@Override
	public ResultSet drillthrough(IQuery query, int maxrows, String returns) {
		OlapStatement stmt = null;
		try {
			Cube cube = query.getCube();
			final OlapConnection con = getOlapConnection(cube); 
			stmt = con.createStatement();
			String mdx = query.getMdx();
			if (maxrows > 0) {
				mdx = "DRILLTHROUGH MAXROWS " + maxrows + " " + mdx;
			}
			else {
				mdx = "DRILLTHROUGH " + mdx;
			}
			if (StringUtils.isNotBlank(returns)) {
				mdx += "\r\n RETURN " + returns;
			}
			ResultSet rs = stmt.executeQuery(mdx);
			return rs;
		} catch (SQLException e) {
			throw new SaikuServiceException("Error DRILLTHROUGH: " + query.getName(), e);
		} finally {
			try {
				if (stmt != null)  stmt.close();
			} catch (Exception e) {}
		}
	}

	@Override
	public ResultSet drillthrough(IQuery query, List<Integer> cellPosition, Integer maxrows, String returns) {
		OlapStatement stmt = null;
		try {
			CellSet cs = query.getCellset();
			SaikuCube cube = query.getSaikuCube();
			final OlapConnection con = getOlapConnection(query.getCube()); 
			stmt = con.createStatement();

			String select = null;
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT (");
			for (int i = 0; i < cellPosition.size(); i++) {
				List<Member> members = cs.getAxes().get(i).getPositions().get(cellPosition.get(i)).getMembers();
				for (int k = 0; k < members.size(); k++) {
					Member m = members.get(k);
					if (k > 0 || i > 0) {
						buf.append(", ");
					}
					buf.append(m.getUniqueName());
				}
			}
			buf.append(") ON COLUMNS \r\n");
			buf.append("FROM " + cube.getCubeName() + "\r\n");
			
			SelectNode sn = (new DefaultMdxParserImpl().parseSelect(query.getMdx())); 
			final Writer writer = new StringWriter();
			sn.getFilterAxis().unparse(new ParseTreeWriter(new PrintWriter(writer)));
			if (StringUtils.isNotBlank(writer.toString())) {
				buf.append("WHERE " + writer.toString());
			}
			select = buf.toString(); 
			if (maxrows > 0) {
				select = "DRILLTHROUGH MAXROWS " + maxrows + " " + select + "\r\n";
			}
			else {
				select = "DRILLTHROUGH " + select + "\r\n";
			}
			if (StringUtils.isNotBlank(returns)) {
				select += "\r\n RETURN " + returns;
			}

			log.debug("Drill Through for query (" + query.getName() + ") : \r\n" + select);
			ResultSet rs = stmt.executeQuery(select);
			return rs;
		} catch (Exception e) {
			throw new SaikuServiceException("Error DRILLTHROUGH: " + query.getName(),e);
		} finally {
			try {
				if (stmt != null)  stmt.close();
			} catch (Exception e) {}
		}
	}
	
	@Override
	public byte[] exportResultSetCsv(ResultSet rs) {
		return CsvExporter.exportCsv(rs);
	}

	@Override
	public byte[] exportResultSetXLS(ResultSet rs) {
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			TableDefinition tableDefinition = TableDefinition.fromResultSetMetaData(metaData);
			RSTableModel tableModel = new RSTableModel(tableDefinition);
			
			while(rs.next()){
				Object[] rowdata = new Object[tableDefinition.getColumnCount()];
				for(int i = 0; i < tableDefinition.getColumnCount(); i++){
					rowdata[i] = rs.getObject(i + 1);
				}
				tableModel.addDataRow(rowdata);
			}

			SXSSFWorkbook workbook = new SXSSFWorkbook(1000); 
			workbook.setCompressTempFiles(true);
	        Sheet sheet = workbook.createSheet();
	        
			RSTableToXLS tableToxls = new RSTableToXLS();
			tableToxls.exportToExcel(tableModel, workbook, sheet, true);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			workbook.write(os);
			os.close();
			
			return os.toByteArray();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ExcelExportException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] exportResultSetCsv(ResultSet rs, String delimiter, String enclosing, boolean printHeader, List<KeyValue<String,String>> additionalColumns) {
		return CsvExporter.exportCsv(rs, delimiter, enclosing, printHeader, additionalColumns);
	}
	
	@Override
	public IQuery swapAxes(IQuery query) {
		if (QueryType.QM.equals(query.getType())) {
			query.swapAxes();
		}		
		return query;
	}
	
	@Override
	public void setCellValue(IQuery query, List<Integer> position, String value, String allocationPolicy) {
		try {

			OlapConnection con = getOlapConnection(query.getCube());

			Scenario s;
			if (query.getScenario() == null) {
				s = con.createScenario();
				query.setScenario(s);
				con.setScenario(s);
//				System.out.println("Created scenario:" + s + " : cell:" + position + " value" + value);
			} else {
				s = query.getScenario();
				con.setScenario(s);
//				System.out.println("Using scenario:" + s + " : cell:" + position + " value" + value);

			}


			CellSet cs1 = query.execute();
			query.storeCellset(cs1);

			Object v = null;
			try {
				v = Integer.parseInt(value);
			} catch (Exception e) {
				v = Double.parseDouble(value);
			}
			if (v == null) {
				throw new SaikuServiceException("Error setting value of query " + query.getName() + " to:" + v);
			}

			allocationPolicy = AllocationPolicy.EQUAL_ALLOCATION.toString();

			AllocationPolicy ap = AllocationPolicy.valueOf(allocationPolicy);
			CellSet cs = query.getCellset();
			cs.getCell(position).setValue(v, ap);
			con.setScenario(null);
		} catch (Exception e) {
			throw new SaikuServiceException("Error setting value: " + query.getName(),e);
		}
	}
	
	@Override
	public SaikuDimensionSelection getAxisDimensionSelections(IQuery query, String axis, String dimension) {
		try {
			QueryAxis qaxis = query.getAxis(axis);
			if (qaxis != null) {
				QueryDimension dim = query.getDimension(dimension);
				if (dim != null) {
					return ObjectUtil.convertDimensionSelection(dim);
				}
				else
				{
					throw new SaikuOlapException("Cannot find dimension with name:" + dimension);
				}
			}
			else {
				throw new SaikuOlapException("Cannot find axis with name:" + axis);
			}
		} catch (SaikuOlapException e) {
			throw new SaikuServiceException("Cannot get dimension selections",e);
		}
	}
	
	@Override
	public void moveDimension(IQuery query, String axisName, String dimensionName, int position) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("move query: " + query.getName() + " dimension " + dimensionName + " to axis " + axisName + "  position" + position);
			}
			QueryDimension dimension = query.getDimension(dimensionName);
			Axis newAxis = axisName != null ? ( "UNUSED".equals(axisName) ? null : Axis.Standard.valueOf(axisName)) : null;
			if(position==-1){
				query.moveDimension(dimension, newAxis);
			}
			else{
				query.moveDimension(dimension, newAxis, position);
			}
		}
		catch (Exception e) {
			throw new SaikuServiceException("Cannot move dimension:" + dimensionName + " to axis: "+axisName,e);
		}
    }
	
	@Override
	public void removeDimension(IQuery query, String axisName, String dimensionName) {
		moveDimension(query, "UNUSED" , dimensionName, -1);
		query.getDimension(dimensionName).getExclusions().clear();
		query.getDimension(dimensionName).getInclusions().clear();
	}
	
	public boolean removeAllChildren(IQuery query, String dimensionName) {
		QueryDimension dimension = query.getDimension(dimensionName);
		List<Selection> children = new ArrayList<Selection>();
		try {
			for (Selection sel : dimension.getInclusions()) {
				if (sel.getOperator().equals(Operator.CHILDREN)) {
					children.add(sel);
				}
			}
			dimension.getInclusions().removeAll(children);
			return true;
		} catch (Exception e) {
			throw new SaikuServiceException("Cannot remove all children  for query ("+query.getName()+") dimension (" + dimensionName + ")",e);
		}
	}
	
	@Override
	public boolean includeMember(IQuery query, String dimensionName, String uniqueMemberName, String selectionType, int memberposition){
		
		List<IdentifierSegment> memberList = IdentifierNode.parseIdentifier(uniqueMemberName).getSegmentList();
		QueryDimension dimension = query.getDimension(dimensionName);
		final Selection.Operator selectionMode = Selection.Operator.valueOf(selectionType);
		try {
			removeAllChildren(query, dimensionName);
			Selection sel = dimension.createSelection(selectionMode, memberList);
			if (dimension.getInclusions().contains(sel)) {
				dimension.getInclusions().remove(sel);
			}
			if (memberposition < 0) {
				memberposition = dimension.getInclusions().size();
			}
			dimension.getInclusions().add(memberposition, sel);
			return true;
		} catch (OlapException e) {
			throw new SaikuServiceException("Cannot include member query ("+query.getName()+") dimension (" + dimensionName + ") member ("+
					uniqueMemberName+") operator (" + selectionType + ") position " + memberposition,e);
		}
	}

	@Override
	public boolean removeMember(IQuery query, String dimensionName, String uniqueMemberName, String selectionType) throws SaikuServiceException{
		removeAllChildren(query, dimensionName);
		List<IdentifierSegment> memberList = IdentifierNode.parseIdentifier(uniqueMemberName).getSegmentList();
		QueryDimension dimension = query.getDimension(dimensionName);
		final Selection.Operator selectionMode = Selection.Operator.valueOf(selectionType);
		try {
			if (log.isDebugEnabled()) {
				log.debug("query: "+query.getName()+" remove:" + selectionMode.toString() + " " + memberList.size());
			}
			Selection selection = dimension.createSelection(selectionMode, memberList);
			dimension.getInclusions().remove(selection);
			return true;
		} catch (OlapException e) {
			throw new SaikuServiceException("Error removing member (" + uniqueMemberName + ") of dimension (" +dimensionName+")",e);
		}
	}

	@Override
	public boolean includeLevel(IQuery query, String dimensionName, String uniqueHierarchyName, String uniqueLevelName) {
		removeAllChildren(query, dimensionName);
		QueryDimension dimension = query.getDimension(dimensionName);
		for (Hierarchy hierarchy : dimension.getDimension().getHierarchies()) {
			if (hierarchy.getUniqueName().equals(uniqueHierarchyName)) {
				for (Level level : hierarchy.getLevels()) {
					if (level.getUniqueName().equals(uniqueLevelName)) {
						Selection sel = dimension.createSelection(level);
						if (!dimension.getInclusions().contains(sel)) {
							dimension.include(level);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeLevel(IQuery query, String dimensionName, String uniqueHierarchyName, String uniqueLevelName) {
		removeAllChildren(query, dimensionName);
		QueryDimension dimension = query.getDimension(dimensionName);
		try {
			for (Hierarchy hierarchy : dimension.getDimension().getHierarchies()) {		
				if (hierarchy.getUniqueName().equals(uniqueHierarchyName)) {
					for (Level level : hierarchy.getLevels()) {
						if (level.getUniqueName().equals(uniqueLevelName)) {
							Selection inclusion = dimension.createSelection(level);
							dimension.getInclusions().remove(inclusion);
							ArrayList<Selection> removals = new ArrayList<Selection>();
							for (Selection sel :dimension.getInclusions()) {
								if ((sel.getRootElement() instanceof Member)) {
									if (((Member) sel.getRootElement()).getLevel().equals(level)) {
										if (dimension.getInclusions().contains(sel)) {
											removals.add(sel);
										}
									}
								}
							}
							dimension.getInclusions().removeAll(removals);
                        }
					}
				}
			}
		} catch (Exception e) {
			throw new SaikuServiceException("Cannot remove level" + uniqueLevelName + "from dimension " + dimensionName,e);
		}
		return true;
	}

	@Override
	public boolean includeChildren(IQuery query, String dimensionName, String uniqueMemberName) {
		List<IdentifierSegment> memberList = IdentifierNode.parseIdentifier(uniqueMemberName).getSegmentList();
		QueryDimension dimension = query.getDimension(dimensionName);
		try {
			Selection sel = dimension.createSelection(Operator.CHILDREN, memberList);
			
			/* dw-change */
			if(dimension.getInclusions().contains(sel))
				dimension.getInclusions().remove(sel);
			/* dw-change:end */
			
			dimension.getInclusions().add(sel);
			return true;
		} catch (OlapException e) {
			throw new SaikuServiceException("Cannot include children query ("+query.getName()+") dimension (" + dimensionName + ") member ("+
					uniqueMemberName +")" ,e);
		}
	}
	
	@Override
	public void sortAxis(IQuery query, String axisName, String sortLiteral, String sortOrder) {
		if (Axis.Standard.valueOf(axisName) != null) {
			QueryAxis qAxis = query.getAxis(Axis.Standard.valueOf(axisName));
			SortOrder so = SortOrder.valueOf(sortOrder);
			qAxis.sort(so, sortLiteral);
		}
	}
	
	@Override
	public void clearSort(IQuery query, String axisName) {
		if (Axis.Standard.valueOf(axisName) != null) {
			QueryAxis qAxis = query.getAxis(Axis.Standard.valueOf(axisName));
			qAxis.clearSort();
		}
	}
	
	@Override
	public void limitAxis(IQuery query, String axisName, String limitFunction, String n, String sortLiteral) {
		if (Axis.Standard.valueOf(axisName) != null) {
			QueryAxis qAxis = query.getAxis(Axis.Standard.valueOf(axisName));
			LimitFunction lf = LimitFunction.valueOf(limitFunction);
			BigDecimal bn = new BigDecimal(n);
			qAxis.limit(lf, bn, sortLiteral);
		}
	}
	
	@Override
	public void clearLimit(IQuery query, String axisName) {
		if (Axis.Standard.valueOf(axisName) != null) {
			QueryAxis qAxis = query.getAxis(Axis.Standard.valueOf(axisName));
			qAxis.clearLimitFunction();
		}
	}
	
	@Override
	public void filterAxis(IQuery query, String axisName, String filterCondition) {
		if (Axis.Standard.valueOf(axisName) != null) {
			QueryAxis qAxis = query.getAxis(Axis.Standard.valueOf(axisName));
			qAxis.filter(filterCondition);
		}
	}
	
	@Override
	public void clearFilter(IQuery query, String axisName) {
		if (Axis.Standard.valueOf(axisName) != null) {
			QueryAxis qAxis = query.getAxis(Axis.Standard.valueOf(axisName));
			qAxis.clearFilter();
		}
	}
	
	@Override
	public SaikuTag createTag(IQuery query, String tagName, List<List<Integer>> cellPositions) {
		try {
			CellSet cs = query.getCellset();
			List<SaikuTuple> tuples = new ArrayList<SaikuTuple>();
			List<SaikuTupleDimension> dimensions = new ArrayList<SaikuTupleDimension>();
			for(List<Integer> cellPosition : cellPositions) {
				List<Member> members = new ArrayList<Member>();
				for (int i = 0; i < cellPosition.size(); i++) {
					members.addAll(cs.getAxes().get(i).getPositions().get(cellPosition.get(i)).getMembers());
				}
				List <SaikuMember> sm = ObjectUtil.convertMembers(members);
				SaikuTuple tuple = new SaikuTuple(sm);
				tuples.add(tuple);
				
				if (dimensions.size() == 0) {
					for (Member m : members) {
						SaikuTupleDimension sd = 
							new SaikuTupleDimension(
								m.getDimension().getName(),
								m.getDimension().getUniqueName(),
								m.getDimension().getCaption());
						if (!dimensions.contains(sd)) {
							dimensions.add(sd);
						}
					}
				}
			}
			List<SaikuDimensionSelection> filterSelections = getAxisSelection(query, "FILTER");
			SaikuTag t = new SaikuTag(tagName, dimensions, tuples, filterSelections);
			return t;
			
		} catch (Exception e) {
			throw new SaikuServiceException("Error addTag:" + tagName + " for query: " + query.getName(),e);
		}
	}
	
	@Override
	public IQuery simulateTag(IQuery query, SaikuTag tag) {
		try {
			OlapConnection con = getOlapConnection(query.getCube());
			return applyTag(query, con, tag);
		} catch (Exception e) {
			throw new SaikuServiceException("Can't apply tag: " + tag + " to query "+ query.getName(),e);
		}
	}
	
}
