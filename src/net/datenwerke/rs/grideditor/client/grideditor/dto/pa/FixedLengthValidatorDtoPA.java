package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.ValidatorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.FixedLengthValidator.class)
public interface FixedLengthValidatorDtoPA extends ValidatorDtoPA {


	public static final FixedLengthValidatorDtoPA INSTANCE = GWT.create(FixedLengthValidatorDtoPA.class);


	/* Properties */
	public ValueProvider<FixedLengthValidatorDto,Integer> length();


}
