package net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerContainer.class)
public interface RemoteServerContainerDtoPA extends PropertyAccess<RemoteServerContainerDto> {


	public static final RemoteServerContainerDtoPA INSTANCE = GWT.create(RemoteServerContainerDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<RemoteServerContainerDto> dtoId();

	/* Properties */
	public ValueProvider<RemoteServerContainerDto,Long> id();
	public ValueProvider<RemoteServerContainerDto,RemoteServerDefinitionDto> remoteServer();


}
