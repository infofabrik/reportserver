package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;

import org.hibernate.annotations.Type;

@Entity
@Table(name="DADGET_STATIC_HTML")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true
)
public class StaticHtmlDadget extends Dadget {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5302531684378157549L;
	
	@ExposeToClient
	private String title;
	
	@ExposeToClient(disableHtmlEncode=true)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String data;
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
