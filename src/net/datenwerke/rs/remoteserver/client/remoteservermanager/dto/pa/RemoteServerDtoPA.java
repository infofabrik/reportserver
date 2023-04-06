package net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServer.class)
public interface RemoteServerDtoPA extends RemoteServerDefinitionDtoPA {


	public static final RemoteServerDtoPA INSTANCE = GWT.create(RemoteServerDtoPA.class);


	/* Properties */
	public ValueProvider<RemoteServerDto,String> apikey();
	public ValueProvider<RemoteServerDto,String> key();
	public ValueProvider<RemoteServerDto,String> url();
	public ValueProvider<RemoteServerDto,String> username();
	public ValueProvider<RemoteServerDto,Boolean> hasApikey();


}
