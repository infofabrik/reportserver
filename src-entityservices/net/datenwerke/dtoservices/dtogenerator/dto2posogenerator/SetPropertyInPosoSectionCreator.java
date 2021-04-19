package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator;

import java.util.Collection;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoFieldDescriptor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PropertyValidatorInformation;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;
import net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

/**
 * 
 *
 */
public class SetPropertyInPosoSectionCreator {

	private PosoAnalizer posoAnalizer;
	
	private boolean ignoreSection = false;
	private String name;
	private PosoAnalizer referencedPoso;
	private DeclaredType referencedPosoCollectionType;
	private EnclosedEntity referncedEnclosedPoso = null;
	private String getMethodForDto;
	private String setMethod;
	private String getMethod;
	private boolean returnsPrimitiveType;
	private boolean useProxy;
	private boolean allowForeignPosoForEnclosed;
	private String isModifiedMethod;

	private PropertyValidatorInformation propertyValidator;
	
	private boolean merge2Unmanaged;

	
	public SetPropertyInPosoSectionCreator(PosoAnalizer posoAnalizer, PosoFieldDescriptor field){
		if(! field.isMergeDtoValueBack() || field.isIdField()){
			setIgnoreSection(true);
			return;
		}
		
		this.posoAnalizer = posoAnalizer;
		
		setName(field.getName());
		setGetMethodForDto(field.getGetMethodForDto());
		setSetMethod(field.getSetMethod());
		setGetMethod(field.getGetMethod());
		setEnclosedReferencedPoso(field.getEnclosedEntityAnnotation());
		setReturnsPrimitiveType(! TypeKind.DECLARED.equals(field.getType().getKind()));
		setIsModifiedMethod(field.getIsPropertyModifiedMethodForDto());
		
		if(field.isValidatorPresent())
			setPropertyValidator(field.getPropertValidator());
		
		if(field.referencesPosoCollection()){
			setReferencedPoso(field.getPosoReferencedInCollection());
			setReferencedPosoCollectionType((DeclaredType)field.getType());
		} else if(field.referencesPoso()) {
			setReferencedPoso(field.getPoso());
		} 
		
		setAllowForeignPosoForEnclosed(field.getExposeToClientAnno().allowForeignPosoForEnclosed());
	}
	
	public void addSetSection(Collection<String> referenceAccu, MethodBuilder method){
		if(isIgnoreSection())
			return;
		
		method.addBodyComment(" set " + getName());

		if(useProxy)
			method.beginBodyBlock("if(dto." + isModifiedMethod + "())" );

		/* if there is a validator wrap everything in an extra block */
		if(null != propertyValidator){
			method.beginBodyBlock("if(" + propertyValidator.getValidatorMethodName() + "(dto, poso))");
		}
		
		if(returnsPrimitiveType)
			method.beginTryBlock();
		
		/* set field */
		if(referencesPosoCollection()){
			/* get poso and add reference */
			referenceAccu.add(referencedPoso.getFullyQualifiedClassName());
			referenceAccu.add(referencedPoso.getDtoInformation().getFullyQualifiedClassName());
			
			/* build collection interface name */
			Class<?> collectionInterface = SourceFileGenerationUtils.isCollection(getReferencedPosoCollectionType());
			referenceAccu.add(collectionInterface.getName());
			String collectionInterfaceName = collectionInterface.getSimpleName();
			collectionInterfaceName += "<" + referencedPoso.getSimpleName() + ">";
			
			/* build collection class name */
			Collection collection = SourceFileGenerationUtils.instantiateCollection(SourceFileGenerationUtils.isCollection(getReferencedPosoCollectionType()));
			referenceAccu.add(collection.getClass().getName());
			String collectionClassName = collection.getClass().getSimpleName() + "<" + referencedPoso.getSimpleName() + ">";
			
			String tmpCollectionVar = "tmpCol_" + getName();

			/* instantiate variable to store new data */
			String collectionVar = "col_" + getName();
			method.addBodyLine("final " + collectionInterfaceName + " " + collectionVar + " = new " + collectionClassName + "();");

			/* write method part */
			
			
			/* load data from dto */
			method.addBodyComment("load new data from dto");
			if(useProxy && referencedPoso.isPosoClass()){
				method.addBodyLine("Collection<" + referencedPoso.getDtoInformation().getClassName() + "> " + tmpCollectionVar + " = null;");
				method.addBodyLine(tmpCollectionVar + " = dto." + getGetMethodForDto() + "();");
			} else 
				method.addBodyLine("Collection<" + referencedPoso.getDtoInformation().getClassName() + "> " + tmpCollectionVar + " = dto." + getGetMethodForDto() + "();");
			
			method.addBodyLine();
			
			/* write set part */
			if(isEnclosedReferencedPoso()){
				
				/* if poso does have an id field */
				if(null != referencedPoso.getIdField()){
					if(merge2Unmanaged){
						/* add or merge data from dto */
						method.addBodyComment("merge or add data");
						method.beginBodyBlock("for(" + referencedPoso.getDtoInformation().getClassName() + " refDto : " + tmpCollectionVar + ")");
						
						method.addBodyLine(collectionVar + ".add((" + referencedPoso.getSimpleName() + ") dtoServiceProvider.get().createUnmanagedPoso(refDto));");
						
						method.endBodyBlock();
						
						/* set the property */
						method.addBodyLine("poso." + getSetMethod() + "(" + collectionVar + ");");
					} else {
						/* load current data */
						method.addBodyComment("load current data from poso");
						method.beginOneLineBlock("if(null != poso." + getGetMethod() + "())");
						method.addBodyLine(collectionVar + ".addAll(poso." + getGetMethod() + "());");
						method.addBodyLine();
						
						/* remove non existing data */
						method.addBodyComment("remove non existing data");
	
						String remCollectionVar = "remDto_" + getName();
						method.addBodyLine(collectionInterfaceName + " " + remCollectionVar + " = new " + collectionClassName + "();");
						method.beginBodyBlock("for(" + referencedPoso.getSimpleName() + " ref : " + collectionVar + ")");
							method.addBodyLine("boolean found = false;");
							method.beginBodyBlock("for(" + referencedPoso.getDtoInformation().getClassName() + " refDto : " + tmpCollectionVar + ")");
								method.beginBodyBlock("if(null != refDto && null != refDto." + referencedPoso.getIdField().getGetMethodForDto() + "() && refDto." + referencedPoso.getIdField().getGetMethodForDto() + "().equals(ref." + referencedPoso.getIdField().getGetMethod() + "()))");
									method.addBodyLine("found = true;");
									method.addBodyLine("break;");
								method.endBodyBlock();
							method.endBodyBlock();
							method.beginOneLineBlock("if(! found)");
								method.addBodyLine(remCollectionVar + ".add(ref);");
						method.endBodyBlock();
						method.beginOneLineBlock("for(" + referencedPoso.getSimpleName() + " ref : " + remCollectionVar + ")");
						method.addBodyLine(collectionVar + ".remove(ref);");
						method.addBodyLine(Dto2PosoSourceFileGenerator.DTO2POSO_SUPERVISOR_NAME + ".enclosedObjectsRemovedFromCollection(dto, poso, " + remCollectionVar + ", \"" + getName() + "\");");
						method.addBodyLine();
						
						/* add or merge data from dto */
						method.addBodyComment("merge or add data");
						
						String newCollectionVar = "new_col_" + getName();
						method.addBodyLine(collectionInterfaceName + " " + newCollectionVar + " = new " + collectionClassName + "();");
						method.beginBodyBlock("for(" + referencedPoso.getDtoInformation().getClassName() + " refDto : " + tmpCollectionVar + ")");
						
						method.addBodyLine("boolean found = false;");
	
						method.beginBodyBlock("for(" + referencedPoso.getSimpleName() + " ref : " + collectionVar + ")");
	
						method.beginBodyBlock("if(null != refDto && null != refDto." + referencedPoso.getIdField().getGetMethodForDto() + "() && refDto." + referencedPoso.getIdField().getGetMethodForDto() + "().equals(ref." + referencedPoso.getIdField().getGetMethod() + "()))");
	
						method.addBodyLine(newCollectionVar + ".add((" + referencedPoso.getSimpleName() + ") dtoServiceProvider.get().loadAndMergePoso(refDto));");
						
						method.addBodyLine("found = true;");
						method.addBodyLine("break;");
	
						method.endBodyBlock();
						method.endBodyBlock();

						method.beginOneLineBlock("if(! found && null != refDto && null == refDto." + referencedPoso.getIdField().getGetMethodForDto() + "() )");
						method.addBodyLine(newCollectionVar + ".add((" + referencedPoso.getSimpleName() + ") dtoServiceProvider.get().createPoso(refDto));");
						method.beginOneLineBlock("else if(! found && null != refDto && null != refDto." + referencedPoso.getIdField().getGetMethodForDto() + "() )");
						if(! isAllowForeignPosoForEnclosed()){ // default
							method.addBodyLine("throw new IllegalArgumentException(\"dto should not have an id. property(" + getName() + ") \");");
							referenceAccu.add(IllegalArgumentException.class.getName());
						} else
							method.addBodyLine(newCollectionVar + ".add((" + referencedPoso.getSimpleName() + ") dtoServiceProvider.get().loadAndMergePoso(refDto));");
						
						method.endBodyBlock();
						
						/* set the property */
						method.addBodyLine("poso." + getSetMethod() + "(" + newCollectionVar + ");");
					}
				} else {
					method.beginBodyBlock("for(" + referencedPoso.getDtoInformation().getClassName() + " refDto : " + tmpCollectionVar + ")");
					
					method.addBodyLine(collectionVar + ".add((" + referencedPoso.getSimpleName() + ") dtoServiceProvider.get().createPoso(refDto));");
					
					method.endBodyBlock();
					
					/* set the property */
					method.addBodyLine("poso." + getSetMethod() + "(" + collectionVar + ");");
				}
			} else {
				method.beginBodyBlock("for(" + referencedPoso.getDtoInformation().getClassName() + " refDto : " + tmpCollectionVar + ")");
				
				if(null != referencedPoso.getIdField()){
					method.beginOneLineBlock("if(null != refDto && null != refDto." + referencedPoso.getIdField().getGetMethodForDto() + "())");
						method.addBodyLine(collectionVar + ".add((" + referencedPoso.getSimpleName() + ") dtoServiceProvider.get().loadPoso(refDto));");
					
					method.beginBodyBlock("else if(null != refDto && null == refDto." + referencedPoso.getIdField().getGetMethodForDto() + "())");
					{	
						method.addCustomLine("\t\t\t\t\t((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new " + CallbackOnPosoCreation.class.getName() + "(){\n");
						method.incrementIndent();
						method.beginBodyBlock("public void callback(Object poso)");
						
						method.beginOneLineBlock("if(null == poso)");
							method.addBodyLine("throw new IllegalArgumentException(\"referenced dto should have an id (" + getName() + ")\");");

						method.addBodyLine(collectionVar + ".add((" + referencedPoso.getSimpleName() + ") poso);");
							
						method.endBodyBlock();
						method.decrementIndent();
						method.addCustomLine("\t\t\t\t\t});\n");
					}
					method.endBodyBlock();
				} else { 
					method.beginOneLineBlock("if(null != refDto )");
					method.addBodyLine(collectionVar + ".add((" + referencedPoso.getSimpleName() + ") dtoServiceProvider.get().createPoso(refDto));");
				}
				
				method.endBodyBlock();
				
				/* set the property */
				method.addBodyLine("poso." + getSetMethod() + "(" + collectionVar + ");");
			}
			
			
		} else if(referencesPoso()) {
			/* add poso to references */
			referenceAccu.add(referencedPoso.getFullyQualifiedClassName());
			referenceAccu.add(referencedPoso.getDtoInformation().getFullyQualifiedClassName());
			
			String tmpDtoVar = "tmpDto_" + getName();
			
			if(useProxy && referencedPoso.isPosoClass())
				method.addBodyLine(referencedPoso.getDtoInformation().getClassName() + " " + tmpDtoVar + " = dto." + getGetMethodForDto() + "();");
			else
				method.addBodyLine(referencedPoso.getDtoInformation().getClassName() + " " + tmpDtoVar + " = dto." + getGetMethodForDto() + "();");
			
			if(null != referencedPoso.getIdField() ){
				PosoFieldDescriptor idField = referencedPoso.getIdField();
	
				if(isEnclosedReferencedPoso()){
					if(merge2Unmanaged){
						method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().createUnmanagedPoso(" + tmpDtoVar + "));");
					} else {
						method.beginBodyBlock("if(null != " + tmpDtoVar +  " && null != " + tmpDtoVar + "." + idField.getGetMethodForDto() + "())");

						method.beginOneLineBlock("if(null != poso." + getGetMethod() + "() && null != poso." + getGetMethod() + "()." + idField.getGetMethod() + "() && poso." + getGetMethod() + "()." + idField.getGetMethod() +  "().equals(" + tmpDtoVar + "." + idField.getGetMethodForDto() + "()))");
						method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().loadAndMergePoso(" + tmpDtoVar + "));");
						method.addOneLineElse();
						if(! isAllowForeignPosoForEnclosed())
							method.addBodyLine("throw new IllegalArgumentException(\"enclosed dto should not have non matching id (" + getName() + ")\");");
						else
							method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().loadAndMergePoso(" + tmpDtoVar + "));");
						
						method.addElseIfBlock("null != poso." + getGetMethod() + "()");
							method.addBodyLine(referencedPoso.getSimpleName() + " newPropertyValue = (" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().createPoso(" + tmpDtoVar + ");");
							method.addBodyLine(Dto2PosoSourceFileGenerator.DTO2POSO_SUPERVISOR_NAME + ".enclosedObjectRemoved(dto, poso, poso." + getGetMethod() + "(), newPropertyValue, \"" + getName() + "\");");
							method.addBodyLine("poso." + getSetMethod() + "(newPropertyValue);");
						
						method.addElseBlock();
							method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().createPoso(" + tmpDtoVar + "));");
						method.endBodyBlock();
					}
				} else {
					if(merge2Unmanaged){
						method.beginBodyBlock("if(null != " + tmpDtoVar +  " && null != " + tmpDtoVar + "." + idField.getGetMethodForDto() + "())");
						{
							method.addBodyLine(referencedPoso.getSimpleName() + " newPropertyValue = (" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().loadPoso(" + tmpDtoVar + ");"); 
							method.addBodyLine("poso." + getSetMethod() + "(newPropertyValue);");

							method.addCustomLine("\t\t\t((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(" + tmpDtoVar + ", new " + CallbackOnPosoCreation.class.getName() + "(){\n");
							method.incrementIndent();
							method.beginBodyBlock("public void callback(Object refPoso)");
							
							method.beginOneLineBlock("if(null != refPoso)");
								method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")refPoso);");
							method.endBodyBlock();
							method.decrementIndent();
							method.addCustomLine("\t\t\t});\n");
						}
						method.addElseIfBlock("null != " + tmpDtoVar +  " && null == " + tmpDtoVar + "." + idField.getGetMethodForDto() + "()");
						{
							String finalTmpDtoVar = tmpDtoVar + "_final";
							method.addBodyLine("final " + referencedPoso.getDtoInformation().getClassName() + " " + finalTmpDtoVar + " = " + tmpDtoVar + ";");
							method.addCustomLine("\t\t\t((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(" + tmpDtoVar + ", new " + CallbackOnPosoCreation.class.getName() + "(){\n");
							method.incrementIndent();
							method.beginBodyBlock("public void callback(Object refPoso)");
							
							method.beginBodyBlock("if(null == refPoso)");
							{
								method.beginTryBlock();
								method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().createUnmanagedPoso(" + finalTmpDtoVar + "));");
								method.beginCatchBlock("ExpectedException e");
								method.addBodyLine("throw new RuntimeException(e);");
								method.endBodyBlock();
							}
							method.addElseBlock();
								method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")refPoso);");
							method.endBodyBlock();
								
							method.endBodyBlock();
							method.decrementIndent();
							method.addCustomLine("\t\t\t});\n");
						}
	
						method.addElseIfBlock("null == " + tmpDtoVar);
							method.addBodyLine("poso." + getSetMethod() + "(null);");
						
						method.endBodyBlock();
					} else {
						method.beginBodyBlock("if(null != " + tmpDtoVar +  " && null != " + tmpDtoVar + "." + idField.getGetMethodForDto() + "())");
						
						method.beginBodyBlock("if(null != poso." + getGetMethod() + "() && null != poso." + getGetMethod() + "()." + idField.getGetMethod() + "() && ! poso." + getGetMethod() + "()." + idField.getGetMethod() +  "().equals(" + tmpDtoVar + "." + idField.getGetMethodForDto() + "()))");
							method.addBodyLine(referencedPoso.getSimpleName() + " newPropertyValue = (" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().loadPoso(" + tmpDtoVar + ");"); 
							method.addBodyLine(Dto2PosoSourceFileGenerator.DTO2POSO_SUPERVISOR_NAME + ".referencedObjectRemoved(dto, poso, poso." + getGetMethod() + "(), newPropertyValue, \"" + getName() + "\");");
							method.addBodyLine("poso." + getSetMethod() + "(newPropertyValue);");
						method.addElseIfBlock("null == poso." + getGetMethod() + "()");
						method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().loadPoso(" + tmpDtoVar + "));");
						
						method.endBodyBlock();
						
						method.addElseIfBlock("null != " + tmpDtoVar +  " && null == " + tmpDtoVar + "." + idField.getGetMethodForDto() + "()");
						{
							method.addCustomLine("\t\t\t((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(" + tmpDtoVar + ", new " + CallbackOnPosoCreation.class.getName() + "(){\n");
							method.incrementIndent();
							method.beginBodyBlock("public void callback(Object refPoso)");
							
							method.beginOneLineBlock("if(null == refPoso)");
								method.addBodyLine("throw new IllegalArgumentException(\"referenced dto should have an id (" + getName() + ")\");");

								method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")refPoso);");
								
							method.endBodyBlock();
							method.decrementIndent();
							method.addCustomLine("\t\t\t});\n");							
						}
						
						
	
						method.addElseIfBlock("null == " + tmpDtoVar);
							method.addBodyLine(Dto2PosoSourceFileGenerator.DTO2POSO_SUPERVISOR_NAME + ".referencedObjectRemoved(dto, poso, poso." + getGetMethod() + "(), null, \"" + getName() + "\");");
							method.addBodyLine("poso." + getSetMethod() + "(null);");
						
						method.endBodyBlock();
					}
				}
			} else 
				method.addBodyLine("poso." + getSetMethod() + "((" + referencedPoso.getSimpleName() + ")dtoServiceProvider.get().createPoso(" + tmpDtoVar + "));");
		} else {
			/* no poso no nothing */
			method.addBodyLine("poso." + getSetMethod() + "(dto." + getGetMethodForDto() + "() );");
		}
		
		if(returnsPrimitiveType){
			referenceAccu.add(NullPointerException.class.getName());
			method.beginCatchBlock("NullPointerException e");
			method.endBodyBlock();
		}
		
		if(null != propertyValidator)
			method.endBodyBlock();
		
		if(useProxy)
			method.endBodyBlock();
		
		
		method.addBodyLine();
	}

	public void setIgnoreSection(boolean ignoreSection) {
		this.ignoreSection = ignoreSection;
	}

	public boolean isIgnoreSection() {
		return ignoreSection;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setReferencedPoso(PosoAnalizer referencedPoso) {
		this.referencedPoso = referencedPoso;
	}

	public PosoAnalizer getReferencedPoso() {
		return referencedPoso;
	}

	public void setReferencedPosoCollectionType(
			DeclaredType referencedPosoCollectionType) {
		this.referencedPosoCollectionType = referencedPosoCollectionType;
	}

	public DeclaredType getReferencedPosoCollectionType() {
		return referencedPosoCollectionType;
	}

	public void setEnclosedReferencedPoso(EnclosedEntity enclosedPoso) {
		this.referncedEnclosedPoso = enclosedPoso;
	}

	public boolean isEnclosedReferencedPoso() {
		return null != referncedEnclosedPoso;
	}
	
	public void setGetMethodForDto(String getMethodForDto) {
		this.getMethodForDto = getMethodForDto;
	}

	public String getGetMethodForDto() {
		return getMethodForDto;
	}

	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	public String getSetMethod() {
		return setMethod;
	}
	
	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}

	public String getGetMethod() {
		return getMethod;
	}
	
	private boolean referencesPosoCollection() {
		return null != referencedPoso && null != referencedPosoCollectionType;
	}

	private boolean referencesPoso() {
		return null != referencedPoso && null == referencedPosoCollectionType;
	}

	public void setReturnsPrimitiveType(boolean returnsPrimitiveType) {
		this.returnsPrimitiveType = returnsPrimitiveType;
	}

	public boolean isReturnsPrimitiveType() {
		return returnsPrimitiveType;
	}

	public void setUseProxy() {
		this.useProxy = true;
	}

	public void setMerge2Unmanaged(boolean unmanaged) {
		this.merge2Unmanaged = unmanaged;
	}

	
	public void setPropertyValidator(PropertyValidatorInformation propertyValidator) {
		this.propertyValidator = propertyValidator;
	}

	public PropertyValidatorInformation getPropertyValidator() {
		return propertyValidator;
	}

	public boolean isAllowForeignPosoForEnclosed() {
		return allowForeignPosoForEnclosed;
	}

	public void setAllowForeignPosoForEnclosed(boolean allowForeignPosoForEnclosed) {
		this.allowForeignPosoForEnclosed = allowForeignPosoForEnclosed;
	}

	public void setIsModifiedMethod(String isModifiedMethod) {
		this.isModifiedMethod = isModifiedMethod;
	}

	public String getIsModifiedMethod() {
		return isModifiedMethod;
	}
	
	

}
