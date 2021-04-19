package net.datenwerke.rs.ftp.service.ftp.definitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.FtpDatasink2DtoPostProcessor;
import net.datenwerke.rs.ftp.service.ftp.locale.FtpMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define ftp datasinks that can be used in ReportServer.
 * 
 * <p>
 * 
 * </p> 
 *  
 *
 */
@Entity
@Table(name="FTP_DATASINK")
@Audited
@GenerateDto(
		dtoPackage="net.datenwerke.rs.ftp.client.ftp.dto",
		poso2DtoPostProcessors=FtpDatasink2DtoPostProcessor.class,
		additionalFields = {
			@AdditionalField(name="hasPassword", type=Boolean.class),
		},
		icon="upload"
		)
@InstanceDescription(
		msgLocation = FtpMessages.class,
		objNameKey = "ftpDatasinkTypeName",

		icon = "upload"
		)
@Indexed
public class FtpDatasink extends DatasinkDefinition implements BasicDatasink {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5532424176260294397L;


	@Inject
	protected static Provider<PbeService> pbeServiceProvider;

	@ExposeToClient
	@Field
	@Column(length = 1024)
	private String host = "ftp://ftp.host.net";
	
	@ExposeToClient
	@Field
	private int port = 21;

	@ExposeToClient
	@Field
	private String username;

	@ExposeToClient(
			exposeValueToClient=false,
			mergeDtoValueBack=true
			)
	private String password;
	
	@ExposeToClient
	@Field
	@Column(length = 1024)
	private String folder = "./";
	
	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
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
	

}
