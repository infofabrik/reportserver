package net.datenwerke.dtoservices.dtogenerator.analizer;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.IgnoreMergeBackDto;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

/**
 * 
 *
 */
public class PosoFieldDescriptor {

	private DtoAnnotationProcessor dtoAnnotationProcessor;
	private VariableElement element;
	private PosoAnalizer referencedInPoso;
	private PropertyValidatorInformation propertyValidator;
	
	public PosoFieldDescriptor(DtoAnnotationProcessor dtoAnnotationProcessor, VariableElement element, PosoAnalizer referencedInPoso){
		this.dtoAnnotationProcessor = dtoAnnotationProcessor;
		this.element = element;
		this.referencedInPoso = referencedInPoso;
	}
	
	public boolean isExposedToClient(){
		return null != element.getAnnotation(ExposeToClient.class);
	}
	
	public ExposeToClient getExposeToClientAnno(){
		if(! isExposedToClient())
			throw new IllegalStateException("Field not exposed to client");
		
		ExposeToClient etcAnno = element.getAnnotation(ExposeToClient.class);
		return etcAnno;
	}
	
	public AnnotationMirror getExposeToClientAnnoMirror(){
		for(AnnotationMirror mirror : element.getAnnotationMirrors()){
			if(mirror.getAnnotationType().toString().equals(ExposeToClient.class.getName()))
				return mirror;
		}
		return null;
	}
	
	public DtoView getDtoView(){
		if(! isExposedToClient())
			throw new IllegalStateException("Field not exposed to client");
		
		ExposeToClient etcAnno = getExposeToClientAnno();
		
		/* ids are green */
		if(etcAnno.id())
			return DtoView.MINIMAL;
		
		/* titles are also green */
		if(etcAnno.displayTitle())
			return DtoView.MINIMAL;
			
		return etcAnno.view();
	}
	
	public String getVisibility(){
		switch(getExposeToClientAnno().visibility()){
		case PACKAGE_PRIVATE: return "";
		case PROTECTED: return "protected";
		case PUBLIC: return "public";
		}
		return "private";
	}
	
	public boolean isIdField() {
		if(! isExposedToClient())
			return false;
		
		ExposeToClient etcAnno = getExposeToClientAnno();
		return etcAnno.id();
	}

	public boolean isKeyField() {
		if(! isExposedToClient())
			return false;
		
		ExposeToClient etcAnno = getExposeToClientAnno();
		return etcAnno.key();
	}
	
	public boolean isDisplayTitleField() {
		if(! isExposedToClient())
			return false;
		
		ExposeToClient etcAnno = getExposeToClientAnno();
		return etcAnno.displayTitle();
	}
	
	public String getName(){
		return element.getSimpleName().toString();
	}
	
	public String getFieldModifiedIndicator(){
		return getName() + DtoAnnotationProcessor.FIELD_MODIFIED_INDICTATOR_POSTFIX;
	}

	public String getFieldPropertyAccessor() {
		return getName() + DtoAnnotationProcessor.FIELD_PROPERTY_ACCESSOR_POSTFIX;
	}
	

	public String getConstantNameForClientKey(){
		return DtoAnnotationProcessor.DTO_PROPERTY_PREFIX + SourceFileGenerationUtils.camelCaseToUnderscoreUpperCase(getName()); 
	}

	public String getTypesSimpleName(){
		TypeMirror type = element.asType();
		if(! (type instanceof DeclaredType))
			return type.toString();
		
		return ((DeclaredType)type).asElement().getSimpleName().toString();
	}

	public TypeMirror getType() {
		return element.asType();
	}
	
	public boolean referencesPoso(){
		TypeMirror type = getType();
		if(type instanceof DeclaredType)
			return SourceFileGenerationUtils.isPoso((DeclaredType) type);

		return false;
	}
	
	public boolean isString() {
		TypeMirror type = getType();
		if(type instanceof DeclaredType)
			return "java.lang.String".equals(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) type));

		return false;
	}
	
	public boolean referencesPosoClass(){
		TypeMirror type = getType();
		if(type instanceof DeclaredType)
			return SourceFileGenerationUtils.isPosoClass((DeclaredType) type);

		return false;	
	}
	
	public boolean referencesPosoEnum(){
		TypeMirror type = getType();
		if(type instanceof DeclaredType)
			return SourceFileGenerationUtils.isPosoEnum((DeclaredType) type);

		return false;	
	}

	
	public PosoAnalizer getPoso(){
		if(! referencesPoso())
			throw new IllegalStateException("Field " + getName() + " does not reference a poso"); 
		
		return SourceFileGenerationUtils.getPoso((DeclaredType)getType());
	}
	
	public boolean referencesPosoCollection(){
		if(! referencesCollection())
			return false;
		
		/* get collection type */
		DeclaredType type = (DeclaredType) getType();
		
		return SourceFileGenerationUtils.isPosoCollection((DeclaredType) type);
	}
	
	public PosoAnalizer getPosoReferencedInCollection(){
		if(! referencesPosoCollection())
			throw new IllegalStateException("This field does not reference a collection of posos");
		
		/* get collection type */
		DeclaredType type = (DeclaredType) getType();

		return SourceFileGenerationUtils.getPosoInCollection(type);
	}

	public boolean referencesCollection() {
		TypeMirror type = getType();
		if(type instanceof DeclaredType){
			return null != SourceFileGenerationUtils.isCollection((DeclaredType) type);
		}
		
		return false;
	}
	
	public boolean referencesMap() {
		TypeMirror type = getType();
		if(type instanceof DeclaredType){
			return null != SourceFileGenerationUtils.isMap((DeclaredType) type);
		}
		
		return false;
	}
	
	public String getSetMethodForDto(){
		String name = getSetMethod();
		return name;
	}
	
	public String getIsPropertyModifiedMethodForDto(){
		return "is" + getName().substring(0,1).toUpperCase() + getName().substring(1) + "Modified";
	}
	
	public String getPropertyAccessorMethodForDto(){
		return "get" + getName().substring(0,1).toUpperCase() + getName().substring(1) + "PropertyAccessor";
	}
	

	public String getGetMethodForDto(){
		String name = getGetMethod();
		return name;
	}
	

	public String getGetMethod(){
		return SourceFileGenerationUtils.getGetMethodForField(getName(), getType());
	}
	
	public boolean hasIgnoredMergeBackSetMethod(){
		ExecutableElement method = referencedInPoso.getMethod(getSetMethod());
		if(null == method){
			dtoAnnotationProcessor.debug("Could not find method " + getSetMethod() + " in Poso " + referencedInPoso);
			return false;
		}
			
		if(null != method.getAnnotation(IgnoreMergeBackDto.class))
			return true;
		
		return false;
	}
	
	public String getSetMethod(){
		return SourceFileGenerationUtils.getSetMethodForField(getName());
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public boolean isExposeValueToClient() {
		ExposeToClient etc = getExposeToClientAnno();
		return etc.exposeValueToClient();
	}

	public boolean isMergeDtoValueBack() {
		ExposeToClient etc = getExposeToClientAnno();
		return etc.mergeDtoValueBack();
	}

	public boolean referencesEnclosedPoso() {
		return null != element.getAnnotation(EnclosedEntity.class);
	}
	
	public EnclosedEntity getEnclosedEntityAnnotation(){
		if(! referencesEnclosedPoso())
			return null;
		return element.getAnnotation(EnclosedEntity.class);
	}

	public boolean isValidatorPresent(){
		return ! getPropertValidator().isBypass();
	}

	public PropertyValidatorInformation getPropertValidator(){
		if(null == this.propertyValidator){
			this.propertyValidator = new PropertyValidatorInformation(this, dtoAnnotationProcessor);
		}
		return propertyValidator;
	}

	public boolean isSet() {
		TypeMirror type = getType();
		if(type instanceof DeclaredType){
			return SourceFileGenerationUtils.isSet((DeclaredType) type);
		}
		
		return false;
	}

	public boolean isList() {
		TypeMirror type = getType();
		if(type instanceof DeclaredType){
			return SourceFileGenerationUtils.isList((DeclaredType) type);
		}
		
		return false;
	}

	public TypeAnalizer getTypeAnalizer() {
		return new TypeAnalizer(dtoAnnotationProcessor, getType());
	}




}
