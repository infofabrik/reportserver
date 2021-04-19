package net.datenwerke.security.service.security.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.security.service.security.SecurityTarget;

import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="GEN_SECURITY_TGT_ENTITY")
@Audited
public class GenericSecurityTargetEntity implements SecurityTarget {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8719357798010108612L;

	@OneToOne(cascade=CascadeType.ALL)
	@EnclosedEntity
	private Acl acl;
	
	@Column(length=128, unique=true)
	private String targetIdentifier;
	
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

	public Acl getAcl() {
		return acl;
	}
	
	public void setAcl(Acl acl){
		this.acl = acl;
	}

	public void setTargetIdentifier(String targetIdentifier) {
		this.targetIdentifier = targetIdentifier;
	}

	public String getTargetIdentifier() {
		return targetIdentifier;
	}
}
