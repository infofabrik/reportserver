package net.datenwerke.rs.transport.client.transport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;
import net.datenwerke.security.client.treedb.dto.pa.SecuredAbstractNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode.class)
public interface AbstractTransportManagerNodeDtoPA extends SecuredAbstractNodeDtoPA {


	public static final AbstractTransportManagerNodeDtoPA INSTANCE = GWT.create(AbstractTransportManagerNodeDtoPA.class);


	/* Properties */


}
