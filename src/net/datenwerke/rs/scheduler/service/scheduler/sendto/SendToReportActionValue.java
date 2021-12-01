package net.datenwerke.rs.scheduler.service.scheduler.sendto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;

import org.hibernate.annotations.Type;

@Entity
@Table(name="SCHED_ACTION_SEND_TO_V")
public class SendToReportActionValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4819351779360554765L;

	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String valueId;
	
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String theValue;

	public SendToReportActionValue(){}
	
	public SendToReportActionValue(String key, String value) {
		this.valueId = key;
		this.theValue = value;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	public String getTheValue() {
		return theValue;
	}

	public void setTheValue(String theValue) {
		this.theValue = theValue;
	}

	
	
}
