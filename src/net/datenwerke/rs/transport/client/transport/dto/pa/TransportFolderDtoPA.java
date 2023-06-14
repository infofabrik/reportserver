package net.datenwerke.rs.transport.client.transport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;
import net.datenwerke.rs.transport.client.transport.dto.pa.AbstractTransportManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.transport.service.transport.entities.TransportFolder.class)
public interface TransportFolderDtoPA extends AbstractTransportManagerNodeDtoPA {


	public static final TransportFolderDtoPA INSTANCE = GWT.create(TransportFolderDtoPA.class);


	/* Properties */
	public ValueProvider<TransportFolderDto,String> description();
	public ValueProvider<TransportFolderDto,String> name();


}
