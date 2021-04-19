package net.datenwerke.rs.computedcolumns.service.computedcolumns.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

@Entity
@Table(name="COMPUTED_COLUMN")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.computedcolumns.client.computedcolumns.dto"
)
public class ComputedColumn extends AdditionalColumnSpec {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6766016497691991076L;

	@ExposeToClient(disableHtmlEncode=true)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String expression;
	
	/* @ExposeToClient -> is exposed by Column */
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String description;
	
	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
