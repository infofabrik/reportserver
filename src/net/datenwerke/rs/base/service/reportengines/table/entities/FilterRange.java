package net.datenwerke.rs.base.service.reportengines.table.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

@Entity
@Table(name="FILTER_RANGE")
@Audited

@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator = true
)
public class FilterRange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2007551622712011559L;

	@ExposeToClient(disableHtmlEncode=true)
	private String rangeFrom;
	
	@ExposeToClient(disableHtmlEncode=true)
	private String rangeTo;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public FilterRange(){
	}
	
	public FilterRange(String from, String to) {
		rangeFrom = from;
		rangeTo = to;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public void setRangeFrom(String rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public String getRangeFrom() {
		return rangeFrom;
	}
	
	public void setRangeTo(String rangeTo) {
		this.rangeTo = rangeTo;
	}

	public String getRangeTo() {
		return rangeTo;
	}

}
