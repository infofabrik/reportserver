package net.datenwerke.rs.dashboard.service.dashboard.entities;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@Audited
@Entity
@Table(name="DASHBOARD_CONTAINER")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true
)
public class DashboardContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4401146517117953733L;

	@JoinTable(name="DASHBOARD_CONT_2_DASHBRD")
	@EnclosedEntity
	@ExposeToClient(mergeDtoValueBack=false, view=DtoView.MINIMAL)
	@OneToMany(cascade=CascadeType.ALL)
	@OrderBy("n")
	private List<Dashboard> dashboards = new ArrayList<Dashboard>();
	
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

	public void setDashboards(List<Dashboard> dashboards) {
		this.dashboards = dashboards;
	}

	public List<Dashboard> getDashboards() {
		return dashboards;
	}
	
	public void addDashboard(Dashboard dashboard){
		dashboards.add(dashboard);
	}
	
	public boolean removeDashboard(Dashboard dashboard){
		return dashboards.remove(dashboard);
	}

	public boolean contains(Dashboard dashboard) {
		return dashboards.contains(dashboard);
	}
}
