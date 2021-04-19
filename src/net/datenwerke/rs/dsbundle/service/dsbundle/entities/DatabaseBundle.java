package net.datenwerke.rs.dsbundle.service.dsbundle.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.dsbundle.service.dsbundle.locale.DatasourceBundleMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

import org.hibernate.envers.Audited;
import net.datenwerke.gf.base.service.annotations.Indexed;

@Entity
@Table(name="DB_BUNDLE_DATASOURCE")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dsbundle.client.dsbundle.dto"
)
@InstanceDescription(
	msgLocation = DatasourceBundleMessages.class,
	objNameKey = "databaseBundleTypeName",
	icon = "database"
)
@Indexed
public class DatabaseBundle extends DatabaseDatasource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5705061680418219522L;

	@ExposeToClient
	@OneToMany(cascade=CascadeType.ALL)
	@EnclosedEntity
	@JoinTable(name="DB_BUNDLE_2_ENTRY")
	private Set<DatabaseBundleEntry> bundleEntries = new HashSet<DatabaseBundleEntry>();
	
	@ExposeToClient
	private String keySource;
	
	@ExposeToClient
	private String keySourceParamName;
	
	@ExposeToClient
	private String mappingSource;

	public Set<DatabaseBundleEntry> getBundleEntries() {
		return bundleEntries;
	}

	public void setBundleEntries(Set<DatabaseBundleEntry> bundleEntries) {
		this.bundleEntries = bundleEntries;
	}

	public String getKeySource() {
		return keySource;
	}

	public void setKeySource(String keySource) {
		this.keySource = keySource;
	}

	public String getMappingSource() {
		return mappingSource;
	}

	public void setMappingSource(String mappingSource) {
		this.mappingSource = mappingSource;
	}

	public String getKeySourceParamName() {
		return keySourceParamName;
	}

	public void setKeySourceParamName(String keySourceParamName) {
		this.keySourceParamName = keySourceParamName;
	}
	
}