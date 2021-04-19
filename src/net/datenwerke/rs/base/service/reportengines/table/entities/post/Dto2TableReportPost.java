package net.datenwerke.rs.base.service.reportengines.table.entities.post;

import java.util.Collection;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoPostProcessor;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;

import com.google.inject.Inject;

public class Dto2TableReportPost implements Dto2PosoPostProcessor<TableReportDto, TableReport> {

	@Inject
	public Dto2TableReportPost() {
		super();
	}

	@Override
	public void posoCreated(TableReportDto arg0, TableReport arg1) {
		
	}

	@Override
	public void posoInstantiated(TableReport arg0) {
		
	}

	@Override
	public void posoLoaded(TableReportDto arg0, TableReport arg1) {
		
	}

	@Override
	public void posoMerged(TableReportDto arg0, TableReport arg1) {

	}

	@Override
	public void posoCreatedUnmanaged(TableReportDto arg0, TableReport report) {
		/* this is a rather crude hack, but the dto generator is not capable enough */
		for(AdditionalColumnSpec spec : report.getAdditionalColumns()){
			for(Column col : report.getColumns()){
				if(col instanceof ColumnReference){
					AdditionalColumnSpec reference = ((ColumnReference) col).getReference();
					if(null != reference.getTransientId() && reference.getTransientId().equals(spec.getTransientId()))
						 ((ColumnReference) col).setReference(spec);
				}
			}
		}
		
		/* validate */
		for(Column col : report.getColumns()){
			if(col instanceof ColumnReference){
				AdditionalColumnSpec spec = ((ColumnReference) col).getReference();
				if(! containsSpec(report.getAdditionalColumns(), spec))
					throw new IllegalStateException();
			}
		}
	}

	private boolean containsSpec(Collection<AdditionalColumnSpec> toCompare, AdditionalColumnSpec spec) {
		for(AdditionalColumnSpec toComp : toCompare)
			if(toComp == spec)
				return true;
		return false;
	}

}
