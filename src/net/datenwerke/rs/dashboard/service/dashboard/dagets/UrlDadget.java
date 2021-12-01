package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;

@Entity
@Table(name="DADGET_URL")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true
)
public class UrlDadget extends Dadget {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4559913190633451378L;
	
	@ExposeToClient
	private String title;
	
	@ExposeToClient
	private String url;
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

}