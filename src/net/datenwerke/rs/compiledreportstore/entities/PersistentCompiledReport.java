package net.datenwerke.rs.compiledreportstore.entities;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@Entity
@Table(name="COMPILED_REPORT")
public class PersistentCompiledReport {

	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] serializedReport;
	
	@ManyToOne
	private Report report;
	
	private Date createdOn = new Date();
	
	@Version
	private Long version;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
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

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Report getReport() {
		return report;
	}

	public void setSerializedReport(byte[] serializedReport) {
		this.serializedReport = serializedReport;
	}

	public byte[] getSerializedReport() {
		return serializedReport;
	}

	public CompiledReport getCompiledReport() {
		if(null == serializedReport)
			return null;
		
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(serializedReport);
			ObjectInputStream oIn = new ObjectInputStream(in);
			
			return (CompiledReport) oIn.readObject();
		} catch (Exception e) {
		}
		
		return null;
	}
}
