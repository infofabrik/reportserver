package net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen.EmailDatasink2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for EmailDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class EmailDatasink2DtoGenerator implements Poso2DtoGenerator<EmailDatasink,EmailDatasinkDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen.EmailDatasink2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public EmailDatasink2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen.EmailDatasink2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public EmailDatasinkDto instantiateDto(EmailDatasink poso)  {
		EmailDatasinkDto dto = new EmailDatasinkDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public EmailDatasinkDto createDto(EmailDatasink poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final EmailDatasinkDto dto = new EmailDatasinkDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set position */
			dto.setPosition(poso.getPosition() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set createdOn */
			dto.setCreatedOn(poso.getCreatedOn() );

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set encryptionPolicy */
			dto.setEncryptionPolicy(StringEscapeUtils.escapeXml(StringUtils.left(poso.getEncryptionPolicy(),8192)));

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set forceSender */
			dto.setForceSender(poso.isForceSender() );

			/*  set host */
			dto.setHost(StringEscapeUtils.escapeXml(StringUtils.left(poso.getHost(),8192)));

			/*  set port */
			dto.setPort(poso.getPort() );

			/*  set sender */
			dto.setSender(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSender(),8192)));

			/*  set senderName */
			dto.setSenderName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSenderName(),8192)));

			/*  set sslEnable */
			dto.setSslEnable(poso.isSslEnable() );

			/*  set tlsEnable */
			dto.setTlsEnable(poso.isTlsEnable() );

			/*  set tlsRequire */
			dto.setTlsRequire(poso.isTlsRequire() );

			/*  set username */
			dto.setUsername(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUsername(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
