package net.datenwerke.rs.grideditor.service.grideditor.definition;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	createDecorator=true,
	generateDto2Poso=false
)
public class GridEditorData {

	@ExposeToClient()
	@EnclosedEntity
	private GridEditorConfig config;
	
	@ExposeToClient()
	@EnclosedEntity
	private List<GridEditorRecord> dataRecords;
	
	@Transient
	private RSTableModel rsTableModel;
	
	
	public GridEditorData(){
	}
	

	public GridEditorData(RSTableModel model, GridEditorConfig config) {
		this.rsTableModel = model;
		
		dataRecords = new ArrayList<GridEditorRecord>();
		
		TableDefinition td = model.getTableDefinition();
		int size = td.getColumnCount();
		
		int id = 0;
		for(RSTableRow row : model.getData()){
			List<GridEditorRecordEntry> entryList = new ArrayList<GridEditorRecordEntry>();
			Object[] rData = row.getRow();
			for(int i = 0; i < rData.length ;i++)
				entryList.add(new GridEditorRecordEntry(td.getSqlColumnType(i), rData[i]));
			
			dataRecords.add(new GridEditorRecord(id++, entryList));
		}
		
		this.config = config;
	}

	
	public GridEditorConfig getConfig() {
		return config;
	}
	
	public void setConfig(GridEditorConfig config) {
		this.config = config;
	}
	
	public List<GridEditorRecord> getDataRecords() {
		return dataRecords;
	}
	
	public void setDataRecords(List<GridEditorRecord> dataRecords) {
		this.dataRecords = dataRecords;
	}
	
	public RSTableModel getRsTableModel() {
		return rsTableModel;
	}
	
}
