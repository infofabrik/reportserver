package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.FolderDto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.dashboard.service.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBDeniedChildren;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;

/**
 * Entity that can be used to organize reports and reportgroups hierarchically.
 * 
 *
 */
@Entity
@Table(name="DASHBOARD_FOLDER")
@Audited
@Indexed
@TreeDBAllowedChildren({
	DadgetNode.class,
	DashboardNode.class,
	
	DashboardFolder.class
})
@TreeDBDeniedChildren(ReportVariant.class)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	dtoImplementInterfaces=FolderDto.class,
	typeDescriptionMsg=BaseMessages.class, typeDescriptionKey="folder",
	icon="folder"
)
@InstanceDescription(
	msgLocation=DashboardMessages.class,
	objNameKey="folderTypeName",		
	icon = "folder"
)
public class DashboardFolder extends AbstractDashboardManagerNode  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3139118358223220435L;

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
	
	public DashboardFolder(){
		
	}

	public DashboardFolder(String name){
		setName(name);
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
    
	@Override
	public boolean isFolder() {
		return true;
	}

}
