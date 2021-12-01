package net.datenwerke.rs.teamspace.service.teamspace.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

/**
 * 
 *
 */
@Entity
@Table(name="TEAMSPACE_MEMBER")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.teamspace.client.teamspace.dto"
)
public class TeamSpaceMember {

	@ExposeToClient
	private TeamSpaceRole role = TeamSpaceRole.GUEST;
	
	@ExposeToClient(
		view=DtoView.LIST
	)
	@EntityClonerIgnore
	@ManyToOne
	private AbstractUserManagerNode folk;
	
	@EntityClonerIgnore
	@ManyToOne
	private TeamSpace teamSpace;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	public TeamSpaceMember() {
	}

	public TeamSpaceMember(AbstractUserManagerNode folk) {
		this.folk = folk;
	}
	
	public TeamSpaceMember(AbstractUserManagerNode folk, TeamSpaceRole role) {
		this.folk = folk;
		this.role = role;
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

	public void setRole(TeamSpaceRole role) {
		if(null == role)
			role = TeamSpaceRole.GUEST;
		else
			this.role = role;
	}

	public TeamSpaceRole getRole() {
		return role;
	}

	public void setFolk(AbstractUserManagerNode folk) {
		this.folk = folk;
	}
	
	public AbstractUserManagerNode getFolk() {
		return folk;
	}
	
	public void setTeamSpace(TeamSpace teamSpace) {
		this.teamSpace = teamSpace;
	}
	
	public TeamSpace getTeamSpace() {
		return teamSpace;
	}
}
