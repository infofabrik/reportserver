package net.datenwerke.rs.base.service.reportengines.table.output.object;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	generateDto2Poso=false
)
public class RSStringTableRow extends RSTableRow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 121693743242964517L;
	
	@ExposeToClient(mergeDtoValueBack=false)
	protected List<String> stringRow = new ArrayList<String>();

	public RSStringTableRow(){
		super();
	}
	
	public RSStringTableRow(String... row){
		super();
		
		setRow(row);
	}
	
	public RSStringTableRow(List<String> row){
		super();
		
		setRow(row.toArray());
	}
	
	public RSStringTableRow(RSTableRow row) {
		for(Object cell : row.getRow())
			stringRow.add(null == cell ? null : cell.toString());
	}

	@Override
	public void setRow(Object[] row) {
		this.row = row;
		
		for(Object o : row){
			if(null != o)
				stringRow.add(o.toString());
		}
	}

	public List<String> getStringRow(){
		return stringRow;
	}
		
}
