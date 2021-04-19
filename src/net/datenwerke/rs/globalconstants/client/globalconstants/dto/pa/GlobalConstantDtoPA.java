package net.datenwerke.rs.globalconstants.client.globalconstants.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant.class)
public interface GlobalConstantDtoPA extends PropertyAccess<GlobalConstantDto> {


	public static final GlobalConstantDtoPA INSTANCE = GWT.create(GlobalConstantDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<GlobalConstantDto> dtoId();

	/* Properties */
	public ValueProvider<GlobalConstantDto,Long> id();
	public ValueProvider<GlobalConstantDto,String> name();
	public ValueProvider<GlobalConstantDto,String> value();


}
