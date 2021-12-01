package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@Audited
@Entity
@Table(name="DASHBOARD_REFERENCE")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true
)
public class DashboardReference extends Dashboard{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3667835383035261556L;
	
	@ExposeToClient(inheritDtoView=true)
	@ManyToOne
	private DashboardNode dashboardNode;

	public DashboardNode getDashboardNode() {
		return dashboardNode;
	}

	public void setDashboardNode(DashboardNode dashboardNode) {
		this.dashboardNode = dashboardNode;
	}

	
}
