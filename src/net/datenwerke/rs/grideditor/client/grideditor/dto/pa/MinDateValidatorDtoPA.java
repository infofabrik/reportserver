package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.Date;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.ValidatorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinDateValidator.class)
public interface MinDateValidatorDtoPA extends ValidatorDtoPA {


	public static final MinDateValidatorDtoPA INSTANCE = GWT.create(MinDateValidatorDtoPA.class);


	/* Properties */
	public ValueProvider<MinDateValidatorDto,Date> minDate();


}
