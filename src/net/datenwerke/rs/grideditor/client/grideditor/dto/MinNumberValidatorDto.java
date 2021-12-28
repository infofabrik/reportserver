package net.datenwerke.rs.grideditor.client.grideditor.dto;

import java.util.List;

import com.google.gwt.core.client.GWT;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.MinNumberValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinNumberValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinIntegerValidator;

/**
 * Dto for {@link MinIntegerValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class MinNumberValidatorDto extends ValidatorDtoDec {

   private static final long serialVersionUID = 1;

   /* Fields */

   public MinNumberValidatorDto() {
      super();
   }

   @Override
   public String toString() {
      return super.toString();
   }

   public static Dto2PosoMapper newPosoMapper() {
      return new MinNumberValidatorDto2PosoMap();
   }

   public MinNumberValidatorDtoPA instantiatePropertyAccess() {
      return GWT.create(MinNumberValidatorDtoPA.class);
   }

   public void clearModified() {
   }

   public boolean isModified() {
      if (super.isModified())
         return true;
      return false;
   }

   public List<PropertyAccessor> getPropertyAccessors() {
      List<PropertyAccessor> list = super.getPropertyAccessors();
      return list;
   }

   public List<PropertyAccessor> getModifiedPropertyAccessors() {
      List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
      return list;
   }

   public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view) {
      List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
      return list;
   }

   public List<PropertyAccessor> getPropertyAccessorsForDtos() {
      List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
      return list;
   }

}
