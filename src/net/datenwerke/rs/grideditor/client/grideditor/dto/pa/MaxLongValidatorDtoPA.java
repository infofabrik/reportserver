package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.ValidatorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxLongValidator.class)
public interface MaxLongValidatorDtoPA extends ValidatorDtoPA {


	public static final MaxLongValidatorDtoPA INSTANCE = GWT.create(MaxLongValidatorDtoPA.class);


	/* Properties */
	public ValueProvider<MaxLongValidatorDto,Long> number();


}
