package net.datenwerke.rs.base.service.reportengines.table.entities.format;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

@Entity
@Table(name="COLUMN_FORMAT")
@Inheritance(strategy=InheritanceType.JOINED)
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true,
	abstractDto=true
)
public abstract class ColumnFormat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1787362070179868777L;

	@ExposeToClient(
		id=true
	)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public abstract String format(Object value);

}
