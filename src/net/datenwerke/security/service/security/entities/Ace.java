package net.datenwerke.security.service.security.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="ACE")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.security.dto",
	proxyableDto=false,
	createDecorator=true
)
public class Ace implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9147318022533394506L;

	@ExposeToClient(id=true)
	@Id @GeneratedValue
	private Long id;
    
	@Version
	private Integer version;

	/**
	 * The ACE's access control list
	 */
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(nullable=false)
	private Acl acl;
	
	/**
	 * Describes the ACE's position in the acl
	 */
	@ExposeToClient
	private int n;
	
	/**
	 * Describes who this Ace is for
	 */
	@ExposeToClient
	@ManyToOne
	private AbstractUserManagerNode folk;
	
	/**
	 * Describes whether this Ace is a allow or deny ACE
	 */
	@ExposeToClient
	@Column(nullable=false)
	private AccessType accesstype = AccessType.ALLOW;
	
	@JoinTable(name="ACE_2_ACCESS_MAPS")
	@ExposeToClient
	@EnclosedEntity
	@OneToMany(cascade={CascadeType.ALL})
	private Set<AceAccessMap> accessMaps = new HashSet<AceAccessMap>();
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Integer getVersion() {
        return version;
    }

    @SuppressWarnings("unused")
	private void setVersion(Integer version) {
        this.version = version;
    }

	public Acl getAcl() {
		return acl;
	}

	public void setAcl(Acl acl) {
		this.acl = acl;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public AbstractUserManagerNode getFolk() {
		return folk;
	}

	public void setFolk(AbstractUserManagerNode folk) {
		this.folk = folk;
	}

	public AccessType getAccesstype() {
		return accesstype;
	}

	public void setAccesstype(AccessType accesstype) {
		if(null == accesstype)
			accesstype = accesstype.DENY;
		this.accesstype = accesstype;
	}
	
    public Set<AceAccessMap> getAccessMaps() {
		return accessMaps;
	}

	public void setAccessMaps(Set<AceAccessMap> accessMaps) {
		this.accessMaps = accessMaps;
	}
	
	public void addAccessMap(AceAccessMap accessMap){
		if(null == this.accessMaps)
			this.accessMaps = new HashSet<AceAccessMap>();
		this.accessMaps.add(accessMap);
	}
	
	@Transient
	public AceAccessMap getAccessMap(String secureeId){
		for(AceAccessMap map : getAccessMaps())
			if(map.getSecuree().equals(secureeId))
				return map;
		return null;
	}
    
	@Override
	public boolean equals(Object obj) {
		/* returns true if objects are in the same tree and have the same id */
    	if(! (obj instanceof Ace))
    		return false;
    	
    	/* cast object */
    	Ace ace = null;
    	try {
    		ace = (Ace) obj;
    	} catch(ClassCastException e){
    		return false;
    	}
    	
    	/* test id */
    	if(null == getId() && null != ace.getId())
    		return false;
    	if(null != getId() && ! getId().equals(ace.getId()))
    		return false;
    	
    	return true;
	}
	
    @Override
    public int hashCode() {
    	if(null != getId())
    		return getId().hashCode();
    	
    	return super.hashCode();
    }

    

}
