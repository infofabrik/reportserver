package net.datenwerke.security.service.usermanager.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.FolderDto;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.security.service.usermanager.entities.post.OU2DtoPostProcessor;
import net.datenwerke.security.service.usermanager.locale.UserManagerMessages;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

/**
 * 
 *
 */
@Entity
@Table(name="ORGANISATIONAL_UNIT")
@Audited
@Indexed
@TreeDBAllowedChildren({
	Group.class,
	User.class,
	OrganisationalUnit.class
})
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.usermanager.dto",
	dtoImplementInterfaces=FolderDto.class,
	poso2DtoPostProcessors=OU2DtoPostProcessor.class,
	typeDescriptionMsg=net.datenwerke.security.client.locale.UserManagerMessages.class, typeDescriptionKey="ou",
	icon="folder",
	additionalFields = {
		@AdditionalField(name="isUserRoot",type=Boolean.class)
	}
)
@InstanceDescription(
	msgLocation=UserManagerMessages.class,
	objNameKey="ouTypeName",
	icon = "folder"
)
public class OrganisationalUnit extends AbstractUserManagerNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8497522954279869294L;
	
	@ExposeToClient(displayTitle=true)
	@Column(length = 64)
	@Title
	@Field
	private String name;
	
	@ExposeToClient(view=DtoView.MINIMAL)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@Description
	@Field
    private String description;
	
	public OrganisationalUnit(){
		
	}
	
	public OrganisationalUnit(String name) {
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
