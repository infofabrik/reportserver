package net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.pa;

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
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink.class)
public interface EmailDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final EmailDatasinkDtoPA INSTANCE = GWT.create(EmailDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<EmailDatasinkDto,String> encryptionPolicy();
	public ValueProvider<EmailDatasinkDto,Boolean> forceSender();
	public ValueProvider<EmailDatasinkDto,String> host();
	public ValueProvider<EmailDatasinkDto,String> password();
	public ValueProvider<EmailDatasinkDto,Integer> port();
	public ValueProvider<EmailDatasinkDto,String> sender();
	public ValueProvider<EmailDatasinkDto,String> senderName();
	public ValueProvider<EmailDatasinkDto,Boolean> sslEnable();
	public ValueProvider<EmailDatasinkDto,Boolean> tlsEnable();
	public ValueProvider<EmailDatasinkDto,Boolean> tlsRequire();
	public ValueProvider<EmailDatasinkDto,String> username();
	public ValueProvider<EmailDatasinkDto,Boolean> hasPassword();


}
