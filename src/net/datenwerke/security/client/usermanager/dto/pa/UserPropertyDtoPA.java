package net.datenwerke.security.client.usermanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.usermanager.entities.UserProperty.class)
public interface UserPropertyDtoPA extends PropertyAccess<UserPropertyDto> {


	public static final UserPropertyDtoPA INSTANCE = GWT.create(UserPropertyDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<UserPropertyDto> dtoId();

	/* Properties */
	public ValueProvider<UserPropertyDto,Long> id();
	public ValueProvider<UserPropertyDto,String> key();
	public ValueProvider<UserPropertyDto,String> value();


}
