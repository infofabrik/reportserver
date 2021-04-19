package net.datenwerke.rs.base.service.reportengines.table.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

@Entity
@Table(name="COLUMN_REFERENCE")
@Inheritance(strategy=InheritanceType.JOINED)
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true
)
public class ColumnReference extends Column {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8283001475354861348L;

	@ExposeToClient
	@ManyToOne
	private AdditionalColumnSpec reference;

	public void setReference(AdditionalColumnSpec reference) {
		this.reference = reference;
	}

	public AdditionalColumnSpec getReference() {
		return reference;
	}
	
	@Override
	public String getName() {
		if(null == getReference())
			return "";
		return getReference().getName();
	}
	
	@Override
	public void setName(String name) {
	}
	
	@Override
	public String getDescription() {
		if(null == getReference())
			return "";
		return getReference().getDescription();
	}
	
	@Override
	public void setDescription(String description) {
	}
	
	@Override
	public Integer getType() {
		if(null == getReference())
			return null;
		return getReference().getType();
	}
	
	@Override
	public void setType(Integer type) {
	}
}
