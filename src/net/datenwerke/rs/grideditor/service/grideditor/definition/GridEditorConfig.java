package net.datenwerke.rs.grideditor.service.grideditor.definition;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false
)
public class GridEditorConfig {

	@ExposeToClient
	private boolean filterable = true;
	
	@ExposeToClient
	private boolean sortable = true;
	
	@ExposeToClient
	private boolean paging = true;
	
	@ExposeToClient
	private int maxPageSize = 1000;
	
	@ExposeToClient
	private int defaultPageSize = 100;
	
	@ExposeToClient
	private boolean hasForm = true;
	
	@ExposeToClient
	@EnclosedEntity
	private List<GridEditorColumnConfig> columnConfigs;

	@ExposeToClient
	private int totalNumberOfRecords;
	
	@ExposeToClient
	private boolean canAddRecords = true;
	
	@ExposeToClient
	private boolean canDuplicateRecords = true;
	
	@ExposeToClient
	private boolean canRemoveRecords = true;

	public GridEditorConfig(){
	}

	public boolean isPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

	public int getMaxPageSize() {
		return maxPageSize;
	}

	public void setMaxPageSize(int maxPageSize) {
		this.maxPageSize = maxPageSize;
	}
	
	public int getDefaultPageSize() {
		return defaultPageSize;
	}
	
	public void setDefaultPageSize(int defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public List<GridEditorColumnConfig> getColumnConfigs() {
		return columnConfigs;
	}

	public void setColumnConfigs(List<GridEditorColumnConfig> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}
	
	public void addColumnConfig(GridEditorColumnConfig config){
		if(null == columnConfigs)
			columnConfigs = new ArrayList<GridEditorColumnConfig>();
		columnConfigs.add(config);
	}
	
	public int getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(int totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}
	
	public boolean isFilterable() {
		return filterable;
	}
	
	public void setFilterable(boolean filterable) {
		this.filterable = filterable;
	}
	
	public boolean isSortable() {
		return sortable;
	}
	
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isHasForm() {
		return hasForm;
	}

	public void setHasForm(boolean hasForm) {
		this.hasForm = hasForm;
	}
	
	public boolean isCanAddRecords() {
		return canAddRecords;		
	}
	
	public void setCanAddRecords(boolean canAddRecords) {
		this.canAddRecords = canAddRecords;		
	}
	
	public boolean isCanDuplicateRecords() {
		return canDuplicateRecords;		
	}
	
	public void setCanDuplicateRecords(boolean canDuplicateRecords) {
		this.canDuplicateRecords = canDuplicateRecords;		
	}
	
	public boolean isCanRemoveRecords() {
		return canRemoveRecords;
	}

	public void setCanRemoveRecords(boolean canRemoveRecords) {
		this.canRemoveRecords = canRemoveRecords;
	}
}
