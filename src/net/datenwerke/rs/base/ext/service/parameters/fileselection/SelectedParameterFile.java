package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import com.google.inject.Injector;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.eximport.ex.annotations.ExportableField;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.treedb.actions.ReadAction;
import net.datenwerke.security.service.usermanager.entities.User;

@Entity
@Table(name="FILESEL_PARAM_SEL_FILE")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.ext.client.parameters.fileselection.dto"
)
public class SelectedParameterFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7661781931478148032L;

	@ExposeToClient
	@ManyToOne
	private AbstractFileServerNode fileServerFile;
	
	@ManyToOne
	@ExposeToClient
	private AbstractTsDiskNode teamSpaceFile;
	
	@ExposeToClient
	@EnclosedEntity
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private UploadedParameterFile uploadedFile;

	@ExposeToClient(view=DtoView.MINIMAL)
	@Column(length = 128)
	private String name;

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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractFileServerNode getFileServerFile() {
		return fileServerFile;
	}

	public void setFileServerFile(AbstractFileServerNode fileServerFile) {
		if((null != teamSpaceFile || null != uploadedFile) && null != fileServerFile)
			throw new IllegalArgumentException("Can only hold one reference");
		
		this.fileServerFile = fileServerFile;
	}

	public AbstractTsDiskNode getTeamSpaceFile() {
		return teamSpaceFile;
	}

	public void setTeamSpaceFile(AbstractTsDiskNode teamSpaceFile) {
		if((null != uploadedFile || null != fileServerFile) && null != teamSpaceFile)
			throw new IllegalArgumentException("Can only hold one reference");
		
		this.teamSpaceFile = teamSpaceFile;
	}

	public UploadedParameterFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedParameterFile uploadedFile) {
		if((null != teamSpaceFile || null != fileServerFile) && null != uploadedFile)
			throw new IllegalArgumentException("Can only hold one reference");
		
		this.uploadedFile = uploadedFile;
	}
	
	public Long getOldTransientId() {
		return oldTransientId;
	}
	
	public void setOldTransientId(Long oldTransientId) {
		this.oldTransientId = oldTransientId;
	}
	
	public byte[] getContent(){
		if(null != uploadedFile)
			return uploadedFile.getContent();
		else if(fileServerFile != null && fileServerFile instanceof FileServerFile)
			return ((FileServerFile)fileServerFile).getData();
		else if(teamSpaceFile != null)
			if(teamSpaceFile instanceof TsDiskGeneralReference)
				return ((TsDiskGeneralReference)teamSpaceFile).getData();
		return null;
	}
	
	public Object getSelectedFile(){
		if(null != uploadedFile)
			return uploadedFile;
		if(null != teamSpaceFile)
			return teamSpaceFile;
		if(null != fileServerFile)
			return fileServerFile;
		return null;
	}

	public boolean mayAccess(Injector injector) {
		AuthenticatorService authenticator = injector.getInstance(AuthenticatorService.class);
		return mayAccess(injector, authenticator.getCurrentUser());
	}
	
	public boolean mayAccess(Injector injector, User user) {
		if(null != teamSpaceFile){
			TsDiskService diskService = injector.getInstance(TsDiskService.class);
			TeamSpaceService teamSpaceService = injector.getInstance(TeamSpaceService.class);
			
			TeamSpace teamSpace = diskService.getTeamSpaceFor(teamSpaceFile);
			return teamSpaceService.mayAccess(user, teamSpace);
		} else if(null != fileServerFile){
			SecurityService securityService = injector.getInstance(SecurityService.class);
			return securityService.checkActions(user, fileServerFile, ReadAction.class);
		}
			 
		return true;
	}
	
}
