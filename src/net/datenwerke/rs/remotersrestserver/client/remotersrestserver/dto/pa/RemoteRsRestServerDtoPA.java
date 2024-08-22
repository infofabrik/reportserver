package net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer.class)
public interface RemoteRsRestServerDtoPA extends RemoteServerDefinitionDtoPA {


	public static final RemoteRsRestServerDtoPA INSTANCE = GWT.create(RemoteRsRestServerDtoPA.class);


	/* Properties */
	public ValueProvider<RemoteRsRestServerDto,String> apikey();
	public ValueProvider<RemoteRsRestServerDto,String> url();
	public ValueProvider<RemoteRsRestServerDto,String> username();
	public ValueProvider<RemoteRsRestServerDto,Boolean> hasApikey();


}
