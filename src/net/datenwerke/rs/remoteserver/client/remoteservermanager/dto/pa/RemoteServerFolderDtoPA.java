package net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.AbstractRemoteServerManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder.class)
public interface RemoteServerFolderDtoPA extends AbstractRemoteServerManagerNodeDtoPA {


	public static final RemoteServerFolderDtoPA INSTANCE = GWT.create(RemoteServerFolderDtoPA.class);


	/* Properties */
	public ValueProvider<RemoteServerFolderDto,String> description();
	public ValueProvider<RemoteServerFolderDto,String> name();


}
