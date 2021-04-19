package net.datenwerke.rs.base.service.reportengines.table.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

@Entity
@Table(name="ADD_COLUMN_SPEC")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true
)
public class AdditionalColumnSpec extends Column {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6279783353593597698L;

	public boolean hasSameName(AdditionalColumnSpec obj) {
		if(null == obj || null == getName())
			return false;
		return getName().equals(obj.getName());
	}

}
