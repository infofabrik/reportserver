package net.datenwerke.rs.grideditor.service.grideditor.definition;

import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator =true
)
public class GridEditorRecord {

	@ExposeToClient()
	@EnclosedEntity
	private List<GridEditorRecordEntry> data;

	@ExposeToClient()
	private int id;

	public GridEditorRecord(){
	}
	
	public GridEditorRecord(int i, List<GridEditorRecordEntry> data) {
		this.id = i;
		this.data = data;
	}

	public List<GridEditorRecordEntry> getData() {
		return data;
	}
	
	public void setData(List<GridEditorRecordEntry> data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
