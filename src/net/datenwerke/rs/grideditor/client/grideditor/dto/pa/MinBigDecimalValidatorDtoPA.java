package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.math.BigDecimal;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinBigDecimalValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinBigDecimalValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.ValidatorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinBigDecimalValidator.class)
public interface MinBigDecimalValidatorDtoPA extends ValidatorDtoPA {


	public static final MinBigDecimalValidatorDtoPA INSTANCE = GWT.create(MinBigDecimalValidatorDtoPA.class);


	/* Properties */
	public ValueProvider<MinBigDecimalValidatorDto,BigDecimal> number();


}
