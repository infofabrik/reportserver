package net.datenwerke.rs.core.service.datasourcemanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.gf.base.service.annotations.Field;

import com.google.inject.Injector;

/**
 * Used to define data sources that can be used in ReportServer.
 * 
 * <p>
 * 
 * </p> 
 *  
 *
 */
@Entity
@Table(name="DATASOURCE_DEFINITION")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.datasourcemanager.dto",
	abstractDto=true,
	typeDescriptionMsg=DatasourcesMessages.class,
	typeDescriptionKey="dataSource"
)
abstract public class DatasourceDefinition extends AbstractDatasourceManagerNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5049862067210491425L;

	@ExposeToClient(displayTitle=true)
	@Field
	@Column(length = 128)
	@Title
	private String name;
	
	@ExposeToClient(view=DtoView.MINIMAL)
	@Lob
	@Field
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@Description
    private String description;

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


	@Transient
	public String escapeString(Injector injector, String string){
		return string;
	}
    
    @Transient
    public abstract DatasourceDefinitionConfig createConfigObject();

    @Override
	public boolean hasChildren() {
		return false;
	}
}
