package net.datenwerke.rs.condition.client.condition.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.condition.service.condition.entity.ReportCondition.class)
public interface ReportConditionDtoPA extends PropertyAccess<ReportConditionDto> {


	public static final ReportConditionDtoPA INSTANCE = GWT.create(ReportConditionDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<ReportConditionDto> dtoId();

	/* Properties */
	public ValueProvider<ReportConditionDto,String> description();
	public ValueProvider<ReportConditionDto,Long> id();
	public ValueProvider<ReportConditionDto,String> key();
	public ValueProvider<ReportConditionDto,String> name();
	public ValueProvider<ReportConditionDto,TableReportDto> report();


}
