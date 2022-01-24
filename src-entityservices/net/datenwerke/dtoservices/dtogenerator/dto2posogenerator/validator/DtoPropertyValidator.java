package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;

public interface DtoPropertyValidator {

   public void validateProperty(Object property, Object poso, Object dto) throws ValidationFailedException;
}
