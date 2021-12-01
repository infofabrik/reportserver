package net.datenwerke.rs.utils.eventlogger.entities;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@Table(name="AUDIT_LOG_ENTRY")
@Entity
public class AuditLogEntry {

	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="LOG_ENTRY_ID")
	private Set<LogProperty> logProperties = new HashSet<LogProperty>();
	
	@Id @GeneratedValue
	private long id;
	
	@Version
	private Long version;

	private Long userId;

	private Date date = new GregorianCalendar().getTime();
	
	@Column(length=64)
	private String action;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setLogProperties(Set<LogProperty> logProperties) {
		this.logProperties = logProperties;
	}

	public Set<LogProperty> getLogProperties() {
		return logProperties;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	
}
