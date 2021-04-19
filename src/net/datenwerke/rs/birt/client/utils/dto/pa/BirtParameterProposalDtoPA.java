package net.datenwerke.rs.birt.client.utils.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.birt.service.utils.BirtParameterProposal.class)
public interface BirtParameterProposalDtoPA extends PropertyAccess<BirtParameterProposalDto> {


	public static final BirtParameterProposalDtoPA INSTANCE = GWT.create(BirtParameterProposalDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<BirtParameterProposalDto> dtoId();

	/* Properties */
	public ValueProvider<BirtParameterProposalDto,String> key();
	public ValueProvider<BirtParameterProposalDto,String> name();
	public ValueProvider<BirtParameterProposalDto,ParameterDefinitionDto> parameterProposal();
	public ValueProvider<BirtParameterProposalDto,String> type();


}
