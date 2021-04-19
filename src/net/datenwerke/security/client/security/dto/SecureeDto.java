package net.datenwerke.security.client.security.dto;

import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.service.security.Securee;

/**
 * Dto for {@link Securee}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public interface SecureeDto {

	public String getName();

	public List<RightDto> getRights();

	public String getSecureeId();


}
