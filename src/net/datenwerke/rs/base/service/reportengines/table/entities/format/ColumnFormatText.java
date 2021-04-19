package net.datenwerke.rs.base.service.reportengines.table.entities.format;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

@Entity
@Table(name="COLUMN_FORMAT_TEXT")
@Inheritance(strategy=InheritanceType.JOINED)
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true
)
public class ColumnFormatText extends ColumnFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7426511593850193265L;

	@Override
	public String format(Object value) {
		return String.valueOf(value);
	}
}
