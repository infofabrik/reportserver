package net.datenwerke.rs.base.service.datasources.definitions;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.dbpool.config.ConnectionPoolConfigFactory;
import net.datenwerke.dbpool.config.ConnectionPoolConfigImpl;
import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.DatabaseDatasource2DtoPostProcessor;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.datasourcemanager.interfaces.ParameterAwareDatasource;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define data sources that can be used in ReportServer.
 * 
 * <p>
 * 
 * </p> 
 *  
 *
 */
@Entity
@Table(name="DATABASE_DATASOURCE")
@Audited
@GenerateDto(
		dtoPackage="net.datenwerke.rs.base.client.datasources.dto",
		poso2DtoPostProcessors=DatabaseDatasource2DtoPostProcessor.class,
		additionalFields = {
			@AdditionalField(name="hasPassword", type=Boolean.class),
		},
		icon="database"
		)
@InstanceDescription(
		msgLocation = DatasourcesMessages.class,
		objNameKey = "databaseDatasourceTypeName",

		icon = "database"
		)
@Indexed
public class DatabaseDatasource extends DatasourceDefinition implements ParameterAwareDatasource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5532424176260294397L;


	@Inject
	protected static Provider<PbeService> pbeServiceProvider;

	@Inject
	protected static Provider<ConnectionPoolConfigFactory> connectionConfigFactoryProvider;

	@Inject
	protected static Provider<DBHelperService> dbHelperServiceProvider;

	@ExposeToClient
	@Field
	@Column(length = 1024)
	private String url;

	@ExposeToClient
	@Field
	private String username;

	@ExposeToClient(
			exposeValueToClient=false,
			mergeDtoValueBack=true
			)
	private String password;
	
	@ExposeToClient(disableHtmlEncode = true)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String jdbcProperties;

	@ExposeToClient
	private String databaseDescriptor;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		if(null == password)
			return null;
		if("".equals(password))
			return "";

		EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
		String decrypted = new String(encryptionService.decryptFromHex(password));
		return decrypted;
	}

	public void setPassword(String password) {
		if(null == password)
			password = "";

		EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
		byte[] encrypted = encryptionService.encrypt(password);

		this.password = new String(Hex.encodeHex(encrypted));
	}
	
	public String getJdbcProperties() {
		return jdbcProperties;
	}
	public void setJdbcProperties(String jdbcProperties) {
		this.jdbcProperties = jdbcProperties;
	}

	public void setDatabaseDescriptor(String databaseDescriptor) {
		this.databaseDescriptor = databaseDescriptor;
	}

	public String getDatabaseDescriptor() {
		return databaseDescriptor;
	}

	@Override @Transient
	public String escapeString(Injector injector, String string){
		if(null == getDatabaseDescriptor())
			return string;

		DBHelperService dbHelperService = injector.getInstance(DBHelperService.class);
		DatabaseHelper dbHelper = dbHelperService.getDatabaseHelper(getDatabaseDescriptor());

		return dbHelper.escapeString(string);
	}

	@Override @Transient
	public DatasourceDefinitionConfig createConfigObject() {
		return new DatabaseDatasourceConfig();
	}

	public ConnectionPoolConfig getConnectionConfig() {
		ConnectionPoolConfigImpl config = connectionConfigFactoryProvider.get().create(getId());

		config.setUsername(getUsername());
		config.setPassword(getPassword());
		config.setJdbcUrl(getUrl());
		config.setDatasourceId(getId());
		config.setDatasourceName(getName());
		config.setDriver(dbHelperServiceProvider.get().getDatabaseHelper(getDatabaseDescriptor()).getDriver());
		config.setLastUpdated(getLastUpdated());
		config.setJdbcProperties(parseJdbcProperties());

		return config;
	}
	
	protected Properties parseJdbcProperties() {
		if(null != jdbcProperties && ! "".equals(jdbcProperties.trim())){
			Properties props = new Properties();
			try {
				props.load(new StringReader(jdbcProperties));
			} catch (IOException e) {
				throw new IllegalStateException("Could not parse jdbc jdbcProperties.", e);
			}
			return props;
		}
		return null;
	}

	@Override
	public boolean usesParameter(DatasourceDefinitionConfig config, String key) {
		if(null == config)
			return false;

		String query = ((DatabaseDatasourceConfig)config).getQuery();
		if(null == query)
			return false;

		return query.contains(key);
	}

}
