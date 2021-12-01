package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

import org.hibernate.envers.Audited;

@Entity
@Table(name="COLUMN_FILTER")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator = true
)
public class ColumnFilter extends FilterSpec {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8779925486341041400L;
	
	@EnclosedEntity
	@ExposeToClient
	@OneToOne(cascade=CascadeType.ALL)
	private Column column;
	
	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	@Override
	public Collection<Column> getColumns() {
		List<Column> l = new ArrayList<Column>();
		if(null != column)
			l.add(column);
		return l;
	}
}
