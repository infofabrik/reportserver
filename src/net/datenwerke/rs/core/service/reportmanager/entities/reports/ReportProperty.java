package net.datenwerke.rs.core.service.reportmanager.entities.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

import org.hibernate.envers.Audited;

@Entity
@Table(name="REPORT_PROPERTY")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.reportmanager.dto.reports"
)
public class ReportProperty {

	@ExposeToClient(
		view=DtoView.LIST,
		displayTitle=true
	)
	@Column(length=40,nullable= false)
	private String name = "unnamed";
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public ReportProperty(){
		//dummy
	}
	
	public ReportProperty(String name) {
		this.name = name;
	}

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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof ReportProperty))
			return false;
		
		ReportProperty prop = (ReportProperty) obj;
		
		if(null == getId())
			return false;
		
		return getId().equals(prop.getId());
	}

	public boolean isIdentical(ReportProperty toCompare) {
		return equals(toCompare);
	}
}
