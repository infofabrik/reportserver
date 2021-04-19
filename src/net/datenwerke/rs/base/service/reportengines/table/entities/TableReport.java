package net.datenwerke.rs.base.service.reportengines.table.entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.base.client.reportengines.table.locale.TableMessages;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.post.Dto2TableReportPost;
import net.datenwerke.rs.base.service.reportengines.table.entities.post.TableReport2DtoPost;
import net.datenwerke.rs.base.service.reportengines.table.entities.supervisor.Dto2TableReportSupervisor;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.datasourcemanager.interfaces.ParameterAwareDatasource;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuide;
import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuides;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

/**
 * 
 *
 */
@Entity
@Table(name="TABLE_REPORT")
@Audited
@Indexed
@TreeDBAllowedChildren({
	TableReportVariant.class
})
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator = true,
	dto2PosoPostProcessors = Dto2TableReportPost.class,
	dto2PosoSupervisor = Dto2TableReportSupervisor.class,
	poso2DtoPostProcessors = TableReport2DtoPost.class,
	typeDescriptionMsg=TableMessages.class,
	typeDescriptionKey="reportTypeName",
	icon="table"
)
@InstanceDescription(
	msgLocation=ReportEnginesMessages.class,
	objNameKey="tableReportTypeName",
	icon = "table"
)
@EntityDiffGuides(guides={
	@EntityDiffGuide(
		name=Report.ENTITY_DIFF_IDENTITCAL_FOR_EXECUTION, ignoreId = true, ignoreVersion = true,
		whitelist = {"parameterDefinitions", "parameterInstances", "datasourceContainer", "additionalColumns", "enableSubtotals", "columns", "selectAllColumns", "metadataDatasourceContainer", "distinctFlag", "preFilter", "parameterDefinitions", "parameterInstances", "datasourceContainer"}
	)
})
public class TableReport extends Report {

	@Inject
	protected static Provider<TableReportUtils> tableReportUtils;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6376821821815787759L;

	@JoinTable(name="TABLE_REPORT_2_ADD_COLUMN")
	@EnclosedEntity
	@ExposeToClient
	@OneToMany(cascade=CascadeType.ALL)
	private List<AdditionalColumnSpec> additionalColumns = new ArrayList<AdditionalColumnSpec>();
	
	@JoinTable(name="TABLE_REPORT_2_COLUMN")
	@EnclosedEntity
	@ExposeToClient
	@OneToMany(cascade=CascadeType.ALL)
	@OrderBy("position")
	private List<Column> columns = new ArrayList<Column>();
	
	/**
	 * Flag to tell to select all columns
	 */
	@Transient
	private boolean selectAllColumns = false;

	@Transient
	private boolean ignoreAdditionalColumns = false;
	
	@ExposeToClient
	private boolean enableSubtotals = false;
	
	@ExposeToClient
	@EnclosedEntity
	@OneToOne(cascade=CascadeType.ALL)
	private DatasourceContainer metadataDatasourceContainer = new DatasourceContainer();

	@ExposeToClient
	private Boolean distinctFlag = false;
	
	@EnclosedEntity
	@ExposeToClient
	@OneToOne(cascade=CascadeType.ALL)
	private PreFilter preFilter = new PreFilter();
	
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@ExposeToClient(allowArbitraryLobSize=true,disableHtmlEncode=true,exposeValueToClient=false)
	private String cubeXml;
	
	@ExposeToClient(view=DtoView.FTO)
	private boolean cubeFlag = false;

	@ExposeToClient
	private boolean allowCubification = true;
	
	@ExposeToClient
	@Deprecated
	private boolean hideParents = true;
	
	@ExposeToClient(view=DtoView.FTO)
	private boolean allowMdx = true;
	
	@Override
	protected void doPrePersist() {
		super.doPrePersist();
		
		// update positions
		if(null != columns){
			int n = 0;
			for(Column c : columns){
				c.setPosition(n++);
			}
		}
		
		// make sure additional column names are unique
		Set<String> nameSet = new HashSet<String>();
		for(AdditionalColumnSpec col : getAdditionalColumns()){
			if(null == col.getName())
				continue;
			if(nameSet.contains(col.getName()))
				throw new IllegalStateException("Additional columns must have unique names.");
			nameSet.add(col.getName());
		}
	}
	
	@Override
	protected Report createVariant(Report adjustedReport) {
		if(! (adjustedReport instanceof TableReport))
			throw new IllegalArgumentException("Expected TableReport"); //$NON-NLS-1$
		
		TableReportVariant variant = new TableReportVariant();
		
		/* copy parameter instances */
		initVariant(variant, adjustedReport);
		
		/* copy add columns */
		Map<Integer, Column> columnRefMap = new HashMap<Integer, Column>();
		List<AdditionalColumnSpec> clonedColumnSpecs = new ArrayList<AdditionalColumnSpec>();
		
		for(AdditionalColumnSpec columnSpec : ((TableReport)adjustedReport).getAdditionalColumns()){
			AdditionalColumnSpec clonedColumnSpec = entityCloner.get().cloneEntity(columnSpec);
			int idx = 0;
			for(Column col : ((TableReport)adjustedReport).getColumns()){
				if(col instanceof ColumnReference){
					if(columnSpec.equals(((ColumnReference) col).getReference())){
						/* clone and set correct reference */
						Column clonedColumn = entityCloner.get().cloneEntity(col);
						((ColumnReference) clonedColumn).setReference(clonedColumnSpec);
						columnRefMap.put(idx, clonedColumn);
					}
				}
				idx++;
			}
			clonedColumnSpecs.add(clonedColumnSpec);
		}
		variant.setAdditionalColumns(clonedColumnSpecs);
		
		/* copy columns */
		List<Column> clonedColumns = new ArrayList<Column>();
		int idx = 0;
		for(Column column : ((TableReport)adjustedReport).getColumns()){
			if(! (column instanceof ColumnReference)){
				Column clonedColumn = entityCloner.get().cloneEntity(column);
				clonedColumns.add(clonedColumn);
			} else if(columnRefMap.containsKey(idx)) {
				clonedColumns.add(columnRefMap.get(idx));
			} else 
				throw new IllegalStateException();
			idx++;
		}
		variant.setColumns(clonedColumns);
		
		variant.setSelectAllColumns(((TableReport)adjustedReport).isSelectAllColumns());
		
		variant.setDistinctFlag(((TableReport)adjustedReport).isDistinctFlag());
		
		variant.setPreFilter(entityCloner.get().cloneEntity((((TableReport)adjustedReport).getPreFilter())));
		repairFilter(variant.getPreFilter().getRootBlock(), (TableReport) variant);
		
		variant.setEnableSubtotals(((TableReport)adjustedReport).isEnableSubtotals());
		
		/* cubeFlag */
		variant.setCubeFlag(((TableReport) adjustedReport).isCubeFlag());
		if (((TableReport) adjustedReport).isCubeFlag())
			variant.setCubeXml(((TableReport)adjustedReport).getCubeXml());
		variant.setHideParents(((TableReport) adjustedReport).isHideParents());
		
		return variant;
	}

	/**
	 * We need to repair refernces in prefilters after creating the variant, as the cloning cannot take care of this.
	 * 
	 * @see TableReport#createVariant(Report)
	 * @param rootBlock
	 * @param variant
	 */
	private void repairFilter(FilterBlock rootBlock, TableReport variant) {
		for(FilterBlock childBlock : rootBlock.getChildBlocks())
			repairFilter(childBlock, variant);
		
		for(FilterSpec spec : rootBlock.getFilters()){
			for(Column col : spec.getColumns()){
				if(col instanceof ColumnReference){
					AdditionalColumnSpec ref = ((ColumnReference)col).getReference();
					if(null != ref){
						for(AdditionalColumnSpec orgSpec : variant.getAdditionalColumns()){
							if(orgSpec.hasSameName(ref)){
								((ColumnReference)col).setReference(orgSpec);
								break;
							}
						}
					}
				}
			}
		}
	}
	
	@Deprecated
	public void setHideParents(boolean hideParents) {
		this.hideParents = hideParents;
	}
	@Deprecated
	public boolean isHideParents() {
		return hideParents;
	}

	public List<Column> getColumns() {
		if(null == columns)
			columns = new LinkedList<Column>();
		
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public void addColumn(Column col){
		if(null == columns)
			columns = new ArrayList<Column>();
		columns.add(col);
	}
	
	public DatasourceContainer getMetadataDatasourceContainer() {
		return metadataDatasourceContainer;
	}

	public void setMetadataDatasourceContainer(DatasourceContainer datasourceContainer) {
		this.metadataDatasourceContainer = datasourceContainer;
	}
	
	@Override
	public void replaceWith(Report aReport, Injector injector){
		if(! (aReport instanceof TableReport))
			throw new IllegalArgumentException("Expected TableReport"); //$NON-NLS-1$
		super.replaceWith(aReport, injector);
		
		DatasourceService datasourceService = injector.getInstance(DatasourceService.class);
		
		TableReport report = (TableReport) aReport;
		
		/* remove stuff */
		for(Column col : new ArrayList<Column>(columns))
			tableReportUtils.get().remove(col);
		for(AdditionalColumnSpec col : new ArrayList<AdditionalColumnSpec>(additionalColumns))
			tableReportUtils.get().remove(col);
		if(null != preFilter)
			tableReportUtils.get().remove(preFilter);
		
		/* datasource */
		if(null != metadataDatasourceContainer){
			DatasourceDefinitionConfig oldConfig = metadataDatasourceContainer.getDatasourceConfig();
			datasourceService.remove(oldConfig);
		} else 
			metadataDatasourceContainer = new DatasourceContainer();

		if(null != report.getMetadataDatasourceContainer()){
			metadataDatasourceContainer.setDatasource(report.getMetadataDatasourceContainer().getDatasource());
			metadataDatasourceContainer.setDatasourceConfig(report.getMetadataDatasourceContainer().getDatasourceConfig());
		}
		
		/* add stuff */
		for(Column col : report.getColumns())
			addColumn(col);
		for(AdditionalColumnSpec col : report.getAdditionalColumns())
			addColumn(col);
		setPreFilter(report.getPreFilter());
		setDistinctFlag(report.isDistinctFlag());
		setSelectAllColumns(report.isSelectAllColumns());
	}

	public Boolean isDistinctFlag() {
		return distinctFlag;
	}
	
	public void setDistinctFlag(Boolean distinct) {
		if(null == distinct)
			distinct = false;
		this.distinctFlag = distinct;
	}

	public boolean isCubeFlag() {
		return cubeFlag;
	}
	
	public void setCubeFlag(boolean cube) {
		this.cubeFlag = cube;
	}
	
	public String getCubeXml() {
		String connection = getUuid();
		if(null == connection || null == cubeXml)
			return cubeXml;

		String adapted = cubeXml.replaceFirst("connection=\"[^\"]+\"","connection=\"" + connection + "\"");
		return adapted;
	}
	
	public void setCubeXml(String cubeXml) {
		this.cubeXml = cubeXml;
	}
	
	public void setSelectAllColumns(Boolean selectAllColumns) {
		this.selectAllColumns = selectAllColumns;
	}

	public Boolean isSelectAllColumns() {
		return selectAllColumns;
	}

	public void setPreFilter(PreFilter preFilter) {
		this.preFilter = null == preFilter ? new PreFilter() : preFilter;
	}

	public PreFilter getPreFilter() {
		return preFilter;
	}

	public void setAdditionalColumns(List<AdditionalColumnSpec> additionalColumns) {
		this.additionalColumns = additionalColumns;
	}

	public List<AdditionalColumnSpec> getAdditionalColumns() {
		return additionalColumns;
	}
	
	public boolean isAllowCubification() {
		return allowCubification;
	}
	
	public void setAllowCubification(boolean allowCubification) {
		this.allowCubification = allowCubification;
	}
	
	@Override
	public boolean usesParameter(String key) {
		if(null == getDatasourceContainer())
			return false;
		
		DatasourceDefinition datasource = getDatasourceContainer().getDatasource();
		DatasourceDefinitionConfig config = getDatasourceContainer().getDatasourceConfig();
		if(null == datasource)
			return false;
		
		if(datasource instanceof ParameterAwareDatasource){
			return ((ParameterAwareDatasource)datasource).usesParameter(config, key);
		}
		
		return super.usesParameter(key);
	}

	public void setIgnoreAnyColumnConfiguration(boolean ignoreAnyColumnConfiguration) {
		this.ignoreAdditionalColumns = ignoreAnyColumnConfiguration;
	}

	public boolean isIgnoreAdditionalColumns() {
		return ignoreAdditionalColumns;
	}

	public CellFormatter[] getCellFormatter(User user) {
		List<CellFormatter> formatter = new ArrayList<Column.CellFormatter>();
		for(Column col : getVisibleColumns())
			formatter.add(col.getCellFormatter(user));
		return formatter.toArray(new CellFormatter[formatter.size()]);
	}
	

	public CellFormatter[] getCellFormatterForGroupRow(User user) {
		List<CellFormatter> formatter = new ArrayList<Column.CellFormatter>();
		for(Column col : getVisibleColumns())
			formatter.add(col.getCellFormatterForGroupRow(user));
		return formatter.toArray(new CellFormatter[formatter.size()]);
	}
	

	public boolean isColumnFormatted(int i) {
		return getVisibleColumns().get(i).isFormatted();
	}
	

	public int getNrOfVisibleColumns() {
		return getVisibleColumns().size();
	}
	
	public List<Column> getVisibleColumns() {
		List<Column> cols = new ArrayList<Column>();
		for(Column col : getColumns())
			if(! col.isHidden())
				cols.add(col);
		return cols;
	}

	public void setEnableSubtotals(boolean enableSubtotals) {
		this.enableSubtotals = enableSubtotals;
	}

	public boolean isEnableSubtotals() {
		return enableSubtotals;
	}

	public int getNumberOfSubtotalGroupColumns() {
		int cnt = 0;
		for(Column col : getVisibleColumns())
			if(col.isSubtotalGroup())
				cnt++;
		return cnt;
	}

	public int getNumberOfAggregateColumns() {
		int cnt = 0;
		for(Column col : getVisibleColumns())
			if(null != col.getAggregateFunction())
				cnt++;
		return cnt;
	}
	
	public int[] getAggregateColumnIndices() {
		int[] ind = new int[getNumberOfAggregateColumns()];
		Iterator<Column> cols = getVisibleColumns().iterator();
		int index = 0;
		for(int i = 0; i < ind.length; i++){
			Column current = cols.next();
			while(null == current.getAggregateFunction() ){
				current = cols.next();
				index++;
			}
			ind[i] = index;
			index++;
		}
		return ind;
	}
	
	public Column getVisibleColumnByPos(int pos) {
		int vis = 0;
		for(int i = 0; i < getColumns().size(); i++){
			Column col = getColumns().get(i);
			if(null == col.isHidden() || !col.isHidden()){
				if(vis == pos)
					return col;
				vis++;
			}
		}
		return null;
	}


	public int[] getSubtotalGroupColumnIndices() {
		int[] ind = new int[getNumberOfSubtotalGroupColumns()];
		Iterator<Column> cols = getVisibleColumns().iterator();
		int index = 0;
		for(int i = 0; i < ind.length; i++){
			Column current = cols.next();
			while(! current.isSubtotalGroup()){
				current = cols.next();
				index++;
			}
			ind[i] = index;
			index++;
		}
		return ind;
	}

	public boolean isAllowMdx() {
		return allowMdx;
	}

	public void setAllowMdx(boolean allowMdx) {
		this.allowMdx = allowMdx;
	}
}
