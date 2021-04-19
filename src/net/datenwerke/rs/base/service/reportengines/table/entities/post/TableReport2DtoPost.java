package net.datenwerke.rs.base.service.reportengines.table.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;

public class TableReport2DtoPost implements Poso2DtoPostProcessor<TableReport, TableReportDto> {

	@Override
	public void dtoCreated(TableReport report, TableReportDto dto) {
		for(ColumnDto col : dto.getColumns()){
			if(col instanceof ColumnReferenceDto){
				for(AdditionalColumnSpecDto spec: dto.getAdditionalColumns()){
					if(spec.equals(((ColumnReferenceDto)col).getReference())){
						((ColumnReferenceDto)col).setReference(spec);
						break;
					}
				}
			}
		}
	}

	@Override
	public void dtoInstantiated(TableReport arg0, TableReportDto arg1) {
		
	}

}
