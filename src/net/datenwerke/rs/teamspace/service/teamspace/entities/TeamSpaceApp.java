package net.datenwerke.rs.teamspace.service.teamspace.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

import org.hibernate.envers.Audited;

@Entity
@Table(name="TEAMSPACE_APP")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.teamspace.client.teamspace.dto"
)
public class TeamSpaceApp {

	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinTable(name="TEAMSPACE_APP_2_PROPERTY")
	@ExposeToClient
	@EnclosedEntity
    @OneToMany(cascade={CascadeType.ALL})
    private Set<AppProperty> appProperties = new HashSet<AppProperty>();

	@ExposeToClient
	@Column(length=32)
	private String type;
	
	@ExposeToClient
	private Boolean installed = false;

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

	public Set<AppProperty> getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(Set<AppProperty> appProperties) {
		this.appProperties = appProperties;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setInstalled(Boolean installed) {
		if(null == installed)
			installed = false;
		this.installed = installed;
	}

	public Boolean isInstalled() {
		return installed;
	}
	
}
