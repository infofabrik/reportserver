package net.datenwerke.rs.fileserver.service.fileserver.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

@Entity
@Table(name="FILE_SERVER_FILE_DATA")
@Audited
@Cacheable(false)
@Cache(usage=CacheConcurrencyStrategy.NONE)
public class FileServerFileData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4963004254625766627L;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] data;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

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

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}

	public long getSize() {
		if(null == data)
			return 0;
		return data.length;
	}
	
	
}
