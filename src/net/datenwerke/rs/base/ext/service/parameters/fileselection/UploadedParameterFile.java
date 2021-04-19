package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.eximport.ex.annotations.ExportableField;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;

import org.hibernate.envers.Audited;

@Entity
@Table(name="FILESEL_PARAM_UP_FILE")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.ext.client.parameters.fileselection.dto"
)
public class UploadedParameterFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8547958671841889543L;

	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] content;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ExportableField(exportField=false)
	@Transient 
	@TransientID
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
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public Long getOldTransientId() {
		return oldTransientId;
	}
	
	public void setOldTransientId(Long oldTransientId) {
		this.oldTransientId = oldTransientId;
	}
}
