package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.grideditor.service.grideditor.locale.GridEditorMessages;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class EmptyValidator extends Validator<Object> {

   public EmptyValidator() {
   }

   public EmptyValidator(String errorMsg) {
      setErrorMsg(errorMsg);
   }

   @Override
   public String validate(Object casted) {
      if (null == casted || "".equals(String.valueOf(casted))) {
         return (null == getErrorMsg() ? GridEditorMessages.INSTANCE.validationFailedDefaultMessage() : getErrorMsg());
      }
      return null;
   }

}
