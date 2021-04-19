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
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink.class)
public interface SftpDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final SftpDatasinkDtoPA INSTANCE = GWT.create(SftpDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<SftpDatasinkDto,String> folder();
	public ValueProvider<SftpDatasinkDto,String> host();
	public ValueProvider<SftpDatasinkDto,String> password();
	public ValueProvider<SftpDatasinkDto,Integer> port();
	public ValueProvider<SftpDatasinkDto,String> username();
	public ValueProvider<SftpDatasinkDto,Boolean> hasPassword();


}
