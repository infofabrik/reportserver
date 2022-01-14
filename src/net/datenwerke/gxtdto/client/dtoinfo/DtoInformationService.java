package net.datenwerke.gxtdto.client.dtoinfo;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;

/**
 * 
 *
 */
public interface DtoInformationService {

   public Object getDtoId(Dto dto);

   public boolean isProxyableDto(Dto dto);

   public <X extends Dto> X createInstance(Class<X> dtoClass);

   public <X extends Dto> X createInstance(String dtoClassName);

   public Class<? extends Dto2PosoMapper> lookupPosoMapper(Class<? extends RsDto> dtoType);

   public boolean isAuthorityForClass(Class<?> clazz);

   public boolean isAuthorityFor(Object object);

   public boolean isAuthorityForClassName(String dtoClassName);
}
