package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="TS_DISK_ROOT")
@Audited
@TreeDBAllowedChildren({
	TsDiskFolder.class,
	TsDiskReportReference.class,
	TsDiskGeneralReference.class
})
@GenerateDto(
	dtoPackage="net.datenwerke.rs.tsreportarea.client.tsreportarea.dto"
)
public class TsDiskRoot extends AbstractTsDiskNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5537404632434918329L;
	
	@ExposeToClient(
		view=DtoView.MINIMAL,
		displayTitle=true
	)
	@Column(length = 128)
	private String name = "Root";

	@ExposeToClient(view=DtoView.MINIMAL)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
    private String description;
	
	@ExposeToClient
	@OneToOne
	private TeamSpace teamSpace;

	public void setTeamSpace(TeamSpace teamSpace) {
		this.teamSpace = teamSpace;
	}

	public TeamSpace getTeamSpace() {
		return teamSpace;
	}
	
	
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

}
