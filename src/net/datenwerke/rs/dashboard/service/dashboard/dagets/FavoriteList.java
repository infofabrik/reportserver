package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.security.service.usermanager.entities.User;

@Entity
@Table(name="FAVORITE_LIST")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true
)
public class FavoriteList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3678095411599149460L;

	@JoinTable(name="FAVORITE_LIST_2_ENTRY")
	@ExposeToClient
	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@OrderBy("position")
	private List<FavoriteListEntry> referenceEntries = new ArrayList<FavoriteListEntry>();
	
	@OneToOne
	private User user;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
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

	public List<FavoriteListEntry> getReferenceEntries() {
		return referenceEntries;
	}

	public void setReferenceEntries(List<FavoriteListEntry> referenceEntries) {
		if(null == referenceEntries)
			referenceEntries = new ArrayList<FavoriteListEntry>();
		this.referenceEntries = referenceEntries;
	}
	
	public boolean containsEntry(AbstractTsDiskNode node) {
		return null != getEntry(node);
	}

	public FavoriteListEntry getEntry(AbstractTsDiskNode node) {
		if(null == node)
			return null;
		
		for(FavoriteListEntry e : referenceEntries)
			if(node.equals(e.getReferenceEntry()))
				return e;
		return null;
	}
	
	public void addEntry(FavoriteListEntry entry) {
		this.referenceEntries.add(entry);
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	
	 @PrePersist @PreUpdate
	 protected void prePersist(){
		 int i = 0;
		 for(FavoriteListEntry refEntry : referenceEntries){
			 refEntry.setPosition(i);
		 }
	 }


}