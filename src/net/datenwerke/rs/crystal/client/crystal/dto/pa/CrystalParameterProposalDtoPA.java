package net.datenwerke.rs.crystal.client.crystal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.crystal.service.crystal.CrystalParameterProposal.class)
public interface CrystalParameterProposalDtoPA extends PropertyAccess<CrystalParameterProposalDto> {


	public static final CrystalParameterProposalDtoPA INSTANCE = GWT.create(CrystalParameterProposalDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<CrystalParameterProposalDto> dtoId();

	/* Properties */
	public ValueProvider<CrystalParameterProposalDto,String> key();
	public ValueProvider<CrystalParameterProposalDto,String> name();
	public ValueProvider<CrystalParameterProposalDto,ParameterDefinitionDto> parameterProposal();
	public ValueProvider<CrystalParameterProposalDto,String> type();


}
