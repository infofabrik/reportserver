package net.datenwerke.rs.scp.client.scp.dto.pa;

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
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink.class)
public interface ScpDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final ScpDatasinkDtoPA INSTANCE = GWT.create(ScpDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<ScpDatasinkDto,String> authenticationType();
	public ValueProvider<ScpDatasinkDto,String> folder();
	public ValueProvider<ScpDatasinkDto,String> host();
	public ValueProvider<ScpDatasinkDto,String> password();
	public ValueProvider<ScpDatasinkDto,Integer> port();
	public ValueProvider<ScpDatasinkDto,String> privateKeyPassphrase();
	public ValueProvider<ScpDatasinkDto,String> username();
	public ValueProvider<ScpDatasinkDto,Boolean> hasPassword();
	public ValueProvider<ScpDatasinkDto,Boolean> hasPrivateKeyPassphrase();


}
