package net.datenwerke.rs.globalconstants.service.globalconstants.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="GLOBAL_CONSTANT")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.globalconstants.client.globalconstants.dto"
)
public class GlobalConstant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1420273313390610869L;

	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ExposeToClient
	private String name = "undefined";
	
	@ExposeToClient
	private String value = "undefined";

	public GlobalConstant(){
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
