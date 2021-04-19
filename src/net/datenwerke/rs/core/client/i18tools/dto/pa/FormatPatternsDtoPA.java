package net.datenwerke.rs.core.client.i18tools.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.i18ntools.FormatPatterns.class)
public interface FormatPatternsDtoPA extends PropertyAccess<FormatPatternsDto> {


	public static final FormatPatternsDtoPA INSTANCE = GWT.create(FormatPatternsDtoPA.class);


	/* Properties */
	public ValueProvider<FormatPatternsDto,String> currencyPattern();
	public ValueProvider<FormatPatternsDto,String> integerPattern();
	public ValueProvider<FormatPatternsDto,String> longDatePattern();
	public ValueProvider<FormatPatternsDto,String> longDateTimePattern();
	public ValueProvider<FormatPatternsDto,String> longTimePattern();
	public ValueProvider<FormatPatternsDto,String> numberPattern();
	public ValueProvider<FormatPatternsDto,String> percentPattern();
	public ValueProvider<FormatPatternsDto,String> shortDatePattern();
	public ValueProvider<FormatPatternsDto,String> shortDateTimePattern();
	public ValueProvider<FormatPatternsDto,String> shortTimePattern();


}
