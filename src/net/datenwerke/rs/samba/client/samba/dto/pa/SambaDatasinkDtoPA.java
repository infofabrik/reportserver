package net.datenwerke.rs.samba.client.samba.dto.pa;

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
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink.class)
public interface SambaDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final SambaDatasinkDtoPA INSTANCE = GWT.create(SambaDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<SambaDatasinkDto,String> domain();
	public ValueProvider<SambaDatasinkDto,String> folder();
	public ValueProvider<SambaDatasinkDto,String> host();
	public ValueProvider<SambaDatasinkDto,String> password();
	public ValueProvider<SambaDatasinkDto,Integer> port();
	public ValueProvider<SambaDatasinkDto,String> username();
	public ValueProvider<SambaDatasinkDto,Boolean> hasPassword();


}
