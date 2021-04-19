package net.datenwerke.rs.core.service.reportmanager.entities.reports;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

@Entity
@Table(name="REPORT_STRING_PROPERTY")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.reportmanager.dto.reports"
)
public class ReportStringProperty extends ReportProperty {

	@ExposeToClient
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String strValue;
	
	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public boolean toBoolean() {
		if(null == strValue || "".equals(strValue.trim()))
			return false;
		return "true".equals(strValue.trim().toLowerCase());
	}
	
	public Integer toInteger() {
		if(null == strValue || "".equals(strValue.trim()))
			return null;
		return Integer.valueOf(strValue.trim().toLowerCase());
	}

}
