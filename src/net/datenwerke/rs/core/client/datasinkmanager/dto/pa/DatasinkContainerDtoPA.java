package net.datenwerke.rs.core.client.datasinkmanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer.class)
public interface DatasinkContainerDtoPA extends PropertyAccess<DatasinkContainerDto> {


	public static final DatasinkContainerDtoPA INSTANCE = GWT.create(DatasinkContainerDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DatasinkContainerDto> dtoId();

	/* Properties */
	public ValueProvider<DatasinkContainerDto,DatasinkDefinitionDto> datasink();
	public ValueProvider<DatasinkContainerDto,Long> id();


}
