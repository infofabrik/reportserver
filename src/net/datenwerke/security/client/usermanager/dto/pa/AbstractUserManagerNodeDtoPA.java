package net.datenwerke.security.client.usermanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.treedb.dto.pa.SecuredAbstractNodeDtoPA;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode.class)
public interface AbstractUserManagerNodeDtoPA extends SecuredAbstractNodeDtoPA {


	public static final AbstractUserManagerNodeDtoPA INSTANCE = GWT.create(AbstractUserManagerNodeDtoPA.class);


	/* Properties */
	public ValueProvider<AbstractUserManagerNodeDto,String> guid();
	public ValueProvider<AbstractUserManagerNodeDto,String> origin();


}
