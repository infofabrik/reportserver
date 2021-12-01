package net.datenwerke.rs.incubator.service.jaspertotable.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

import org.hibernate.envers.Audited;

@Entity
@Table(name="JASPER_TO_TABLE_CONFIG")
@Audited
@Inheritance(strategy=InheritanceType.JOINED)
@GenerateDto(
	dtoImplementInterfaces=DatasourceContainerProviderDto.class,
	dtoPackage="net.datenwerke.rs.incubator.client.jaspertotable.dto",
	createDecorator=true,
	additionalFields = {
		@AdditionalField(name="active", type=Boolean.class)
	}
)
public class JasperToTableConfig extends ReportProperty implements DatasourceContainerProvider  {

	@ExposeToClient(
		view=DtoView.ALL,
		mergeDtoValueBack=false
	)
	@EnclosedEntity
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    private DatasourceContainer datasourceContainer;

	@Override
	public DatasourceContainer getDatasourceContainer() {
		return datasourceContainer;
	}

	@Override
	public void setDatasourceContainer(DatasourceContainer datasourceContainer) {
		this.datasourceContainer = datasourceContainer;
	}
    
}
