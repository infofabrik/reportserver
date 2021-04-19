package net.datenwerke.rs.base.client.jasperutils.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.jasper.util.JasperParameterProposal.class)
public interface JasperParameterProposalDtoPA extends PropertyAccess<JasperParameterProposalDto> {


	public static final JasperParameterProposalDtoPA INSTANCE = GWT.create(JasperParameterProposalDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<JasperParameterProposalDto> dtoId();

	/* Properties */
	public ValueProvider<JasperParameterProposalDto,String> defaultValueExpression();
	public ValueProvider<JasperParameterProposalDto,Boolean> forPromting();
	public ValueProvider<JasperParameterProposalDto,String> key();
	public ValueProvider<JasperParameterProposalDto,String> name();
	public ValueProvider<JasperParameterProposalDto,ParameterDefinitionDto> parameterProposal();
	public ValueProvider<JasperParameterProposalDto,String> type();


}
