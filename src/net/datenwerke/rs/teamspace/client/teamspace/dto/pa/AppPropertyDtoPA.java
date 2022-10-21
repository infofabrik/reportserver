package net.datenwerke.rs.teamspace.client.teamspace.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.teamspace.service.teamspace.entities.AppProperty.class)
public interface AppPropertyDtoPA extends PropertyAccess<AppPropertyDto> {


	public static final AppPropertyDtoPA INSTANCE = GWT.create(AppPropertyDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<AppPropertyDto> dtoId();

	/* Properties */
	public ValueProvider<AppPropertyDto,Long> id();
	public ValueProvider<AppPropertyDto,String> name();
	public ValueProvider<AppPropertyDto,String> value();


}
