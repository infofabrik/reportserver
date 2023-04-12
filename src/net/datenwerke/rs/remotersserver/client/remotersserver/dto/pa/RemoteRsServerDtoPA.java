package net.datenwerke.rs.remotersserver.client.remotersserver.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.remotersserver.service.remotersserver.entities.RemoteRsServer.class)
public interface RemoteRsServerDtoPA extends RemoteServerDefinitionDtoPA {


	public static final RemoteRsServerDtoPA INSTANCE = GWT.create(RemoteRsServerDtoPA.class);


	/* Properties */
	public ValueProvider<RemoteRsServerDto,String> apikey();
	public ValueProvider<RemoteRsServerDto,String> url();
	public ValueProvider<RemoteRsServerDto,String> username();
	public ValueProvider<RemoteRsServerDto,Boolean> hasApikey();


}
