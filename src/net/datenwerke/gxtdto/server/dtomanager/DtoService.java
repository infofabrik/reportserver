package net.datenwerke.gxtdto.server.dtomanager;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;

public interface DtoService {

   public Object createDto(Object poso);

   public Object createDto(Object poso, DtoView here, DtoView referenced);

   public Object createListDto(Object poso);

   public Object createDtoFullAccess(Object poso);

   public Object instantiateDto(Object poso);

   public Object instantiateDto(Class<?> posoType);

   /**
    * Given a {@link Dto}, loads the corresponding POSO. The POSO has to be casted
    * to the object's type.
    * 
    * @param dto the dto send e.g. by the client
    * @return the corresponding POSO object
    */
   public Object loadPoso(Object dto);

   public Object instantiatePoso(Class<?> dtoClass);

   public Object createPoso(Object dto) throws ExpectedException;

   public Object createUnmanagedPoso(Object dto) throws ExpectedException;

   public void mergePoso(Object dto, Object poso) throws ExpectedException;

   public void mergeUnmanagedPoso(Object dto, Object poso) throws ExpectedException;

   public Object loadAndMergePoso(Object dto) throws ExpectedException;

   public boolean isAuthorityForPosoClass(Class<?> clazz);

   public boolean isAuthorityForDtoClass(Class<?> clazz);

   public boolean isAuthorityForPoso(Object object);

   public boolean isAuthorityForDto(Object object);

   public Class<?> getPosoFromDtoMapper(Dto2PosoMapper mapper);

   public String[] createFto(Object poso, DtoView here, DtoView referenced);

   String[] createFto(Object poso);

   String[] createListFto(Object poso);

   String[] createFtoFullAccess(Object poso);

   String[] dto2Fto(Dto dto);
}
