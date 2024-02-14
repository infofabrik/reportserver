package net.datenwerke.rs.transport.client.transport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.Date;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.dto.decorator.TransportDtoDec;
import net.datenwerke.rs.transport.client.transport.dto.pa.AbstractTransportManagerNodeDtoPA;
import net.datenwerke.security.client.usermanager.dto.UserDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.transport.service.transport.entities.Transport.class)
public interface TransportDtoPA extends AbstractTransportManagerNodeDtoPA {


	public static final TransportDtoPA INSTANCE = GWT.create(TransportDtoPA.class);


	/* Properties */
	public ValueProvider<TransportDto,UserDto> appliedBy();
	public ValueProvider<TransportDto,Date> appliedOn();
	public ValueProvider<TransportDto,String> appliedProtocol();
	public ValueProvider<TransportDto,Boolean> closed();
	public ValueProvider<TransportDto,String> creatorEmail();
	public ValueProvider<TransportDto,String> creatorFirstname();
	public ValueProvider<TransportDto,String> creatorLastname();
	public ValueProvider<TransportDto,String> creatorUsername();
	public ValueProvider<TransportDto,String> description();
	public ValueProvider<TransportDto,UserDto> importedBy();
	public ValueProvider<TransportDto,Date> importedOn();
	public ValueProvider<TransportDto,String> key();
	public ValueProvider<TransportDto,String> rsSchemaVersion();
	public ValueProvider<TransportDto,String> rsVersion();
	public ValueProvider<TransportDto,String> serverId();
	public ValueProvider<TransportDto,String> status();
	public ValueProvider<TransportDto,String> xml();
	public ValueProvider<TransportDto,String> shortKey();
	public ValueProvider<TransportDto,String> createdOnStr();
	public ValueProvider<TransportDto,String> importedOnStr();
	public ValueProvider<TransportDto,String> appliedOnStr();
	public ValueProvider<TransportDto,String> importedByStr();
	public ValueProvider<TransportDto,String> appliedByStr();


}
