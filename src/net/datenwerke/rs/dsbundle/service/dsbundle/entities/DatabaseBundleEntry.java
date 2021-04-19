package net.datenwerke.rs.dsbundle.service.dsbundle.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;

import org.hibernate.envers.Audited;
import net.datenwerke.gf.base.service.annotations.Field;

@Entity
@Table(name="DATABASE_BUNDLE_ENTRY")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dsbundle.client.dsbundle.dto"
)
public class DatabaseBundleEntry implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6648113810813153773L;

	@ExposeToClient
	private String key;
	
	@ExposeToClient
	@ManyToOne
	private AbstractDatasourceManagerNode database;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Field(name="nodeId")
	private Long id;
	
	@Transient @TransientID
	private Long oldTransientId;
	
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
	
	public Long getOldTransientId() {
		return oldTransientId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public AbstractDatasourceManagerNode getDatabase() {
		return database;
	}

	public void setDatabase(AbstractDatasourceManagerNode database) {
		this.database = database;
	}
	
	
}
