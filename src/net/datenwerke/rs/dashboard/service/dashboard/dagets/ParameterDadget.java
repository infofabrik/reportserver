package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;

@Entity
@Table(name = "DADGET_PARAMETER")
@GenerateDto(dtoPackage = "net.datenwerke.rs.dashboard.client.dashboard.dto", createDecorator = true)
public class ParameterDadget extends Dadget {

   private static final long serialVersionUID = 1L;

}
