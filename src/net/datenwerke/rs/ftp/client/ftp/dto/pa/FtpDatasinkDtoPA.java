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
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink.class)
public interface FtpDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final FtpDatasinkDtoPA INSTANCE = GWT.create(FtpDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<FtpDatasinkDto,String> folder();
	public ValueProvider<FtpDatasinkDto,String> ftpMode();
	public ValueProvider<FtpDatasinkDto,String> host();
	public ValueProvider<FtpDatasinkDto,String> password();
	public ValueProvider<FtpDatasinkDto,Integer> port();
	public ValueProvider<FtpDatasinkDto,String> username();
	public ValueProvider<FtpDatasinkDto,Boolean> hasPassword();


}
