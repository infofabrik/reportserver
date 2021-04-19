package net.datenwerke.security.client.security.dto;

import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.service.security.rights.Right;

/**
 * Dto for {@link Right}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public interface RightDto {

	public String getAbbreviation();

	public long getBitField();

	public String getDescription();


}
