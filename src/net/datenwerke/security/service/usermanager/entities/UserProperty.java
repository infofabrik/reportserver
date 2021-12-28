package net.datenwerke.security.service.usermanager.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * 
 *
 */
@Entity
@Table(name="USER_PROPERTY")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.usermanager.dto"
)
public class UserProperty implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8546508757889644967L;

	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ExposeToClient
	@Column(length=64,nullable=false)
	private String key;
	
	@ExposeToClient
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String value;

	/**
	 * Default constructor
	 */
	public UserProperty(){
		
	}
	
	public UserProperty(String key, String value){
		setKey(key);
		setValue(value);
	}
	
	public UserProperty(String key, boolean value) {
		setKey(key);
		setValue(value);
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		if(key.length() > 64)
			throw new IllegalArgumentException("Keys cannot exceed 64 characters.");
		this.key = key;
	}

	public String getValue() {
		return value;
	}
	
	public boolean getValueAsBoolean(){
		if(null == value)
			return false;
		return "true".equals(value);
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void setValue(Boolean value) {
		if(value)
			this.value = "true";
		else
			this.value = "false";
	}

	public void setValue(Long l) {
		setValue(String.valueOf(l));
	}
	
	
}
