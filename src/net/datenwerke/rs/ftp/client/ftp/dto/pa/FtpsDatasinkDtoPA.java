package net.datenwerke.rs.ftp.client.ftp.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink.class)
public interface FtpsDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final FtpsDatasinkDtoPA INSTANCE = GWT.create(FtpsDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<FtpsDatasinkDto,String> authenticationType();
	public ValueProvider<FtpsDatasinkDto,String> dataChannelProtectionLevel();
	public ValueProvider<FtpsDatasinkDto,String> folder();
	public ValueProvider<FtpsDatasinkDto,String> ftpMode();
	public ValueProvider<FtpsDatasinkDto,String> host();
	public ValueProvider<FtpsDatasinkDto,String> password();
	public ValueProvider<FtpsDatasinkDto,Integer> port();
	public ValueProvider<FtpsDatasinkDto,String> username();
	public ValueProvider<FtpsDatasinkDto,Boolean> hasPassword();
	public ValueProvider<FtpsDatasinkDto,Boolean> hasPrivateKeyPassphrase();


}
