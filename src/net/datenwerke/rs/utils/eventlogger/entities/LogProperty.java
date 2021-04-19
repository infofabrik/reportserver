package net.datenwerke.rs.utils.eventlogger.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

@Table(name="AUDIT_LOG_PROPERTY")
@Entity
public class LogProperty {

	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String value;
	
	@Column(length=64,nullable=false)
	private String key;
	
	@Id @GeneratedValue
	private long id;
	
	@Version
	private Long version;

	public LogProperty() {
	}
	
	public LogProperty(String key, String value) {
		setKey(key);
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
