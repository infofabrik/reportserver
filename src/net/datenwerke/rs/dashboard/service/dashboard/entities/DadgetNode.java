package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;

@Entity
@Table(name="DASHBOARD_DADGET_NODE")
@Audited
@Indexed
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto"
)
public class DadgetNode extends AbstractDashboardManagerNode  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8596090281743976223L;

	@ExposeToClient(
			view=DtoView.MINIMAL,
			displayTitle=true
			)
	@Column(length = 128)
	@Field
	@Title
	private String name;

	@ExposeToClient(view=DtoView.MINIMAL)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@Field
	@Description
	private String description;
	
	@ExposeToClient
	@EnclosedEntity
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    private Dadget dadget;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Dadget getDadget() {
		return dadget;
	}
	
	public void setDadget(Dadget dadget) {
		this.dadget = dadget;
	}
	
	@Override
	public boolean hasChildren() {
		return false;
	}
}
