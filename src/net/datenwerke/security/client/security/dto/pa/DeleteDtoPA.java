package net.datenwerke.security.client.security.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.security.dto.DeleteDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.security.rights.Delete.class)
public interface DeleteDtoPA extends PropertyAccess<DeleteDto> {


	public static final DeleteDtoPA INSTANCE = GWT.create(DeleteDtoPA.class);


	/* Properties */
	public ValueProvider<DeleteDto,String> abbreviation();
	public ValueProvider<DeleteDto,Long> bitField();
	public ValueProvider<DeleteDto,String> description();


}
