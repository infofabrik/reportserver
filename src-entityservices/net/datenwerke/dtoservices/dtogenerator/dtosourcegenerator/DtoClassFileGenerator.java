package net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.DtoField;
import net.datenwerke.dtoservices.dtogenerator.analizer.ExposedClientMethod;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoFieldDescriptor;
import net.datenwerke.dtoservices.dtogenerator.analizer.TypeAnalizer;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

import com.google.gwt.core.client.GWT;

/**
 * 
 *
 */
public class DtoClassFileGenerator extends DtoCreator {

	private static final String CLEAR_MODIFIED_METHOD = "clearModified";
	private static final String IS_MODIFIED_METHOD = "isModified";
	private static final String GET_PROPERTY_ACCESSORS = "getPropertyAccessors";
	private static final String GET_PROPERTY_ACCESSORS_BY_VIEW = "getPropertyAccessorsByView";
	private static final String GET_PROPERTY_ACCESSORS_FOR_DTOS = "getPropertyAccessorsForDtos";
	private static final String GET_MODIFIED_PROPERTY_ACCESSORS = "getModifiedPropertyAccessors";
	
	

	public DtoClassFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
		super(posoAnalizer, dtoAnnotationProcessor);
	}
	
	protected void addSerialVersionUID(StringBuilder sourceBuilder) {
		sourceBuilder.append("\n\tprivate static final long serialVersionUID = " + posoAnalizer.getDtoInformation().getSerialVersionUID() + ";\n\n");
	}

	protected void addMethods(StringBuilder sourceBuilder) {
		/* add methods for fields */
		for(PosoFieldDescriptor field : posoAnalizer.getExposedFields()){
			if(field.isIdField() && posoAnalizer.getDtoInformation().isProxyable()){
				addGetIdMethod(sourceBuilder, field);
				addSetIdMethod(sourceBuilder, field);
			} else {
				addGetMethod(sourceBuilder, field);
				addSetMethod(sourceBuilder, field);
			}
			addIsModifiedMethod(sourceBuilder, field);
			addGetPropertyAccessorMethod(sourceBuilder, field);
		}
		
		/* additional setMethods */
		for(PosoAnalizer iPoso : posoAnalizer.getImplementedPosos()){
			for(ExposedClientMethod method : iPoso.getExposedInterfaceMethods()){
				if(method.isGetMethod()){
					boolean found = false;
					for(PosoFieldDescriptor field : posoAnalizer.getExposedFields()){
						if(field.getGetMethod().equals(method.getGetMethod())){
							found = true;
							break;
						}
					}
					if(found)
						continue;
					
					addAdditionalSetMethod(sourceBuilder, method);
					addAdditionalGetMethod(sourceBuilder, method);
					addIsModifiedMethod(sourceBuilder, method);
					addGetPropertyAccessorMethod(sourceBuilder, method);
				}
			}
		}
		
		
		/* additional setMethods */
		for(ExposedClientMethod setMethod : posoAnalizer.getExposedSetMethods()){
			addAdditionalSetMethod(sourceBuilder, setMethod);
			addAdditionalGetMethod(sourceBuilder, setMethod);
			addIsModifiedMethod(sourceBuilder, setMethod);
			addGetPropertyAccessorMethod(sourceBuilder, setMethod);
		}
		
		/* additional setMethods */
		for(ExposedClientMethod getMethod : posoAnalizer.getExposedGetMethods()){
			addAdditionalGetMethod(sourceBuilder, getMethod);
			addAdditionalSetMethod(sourceBuilder, getMethod);
			addIsModifiedMethod(sourceBuilder, getMethod);
			addGetPropertyAccessorMethod(sourceBuilder, getMethod);
		}
		
		for(DtoField field : posoAnalizer.getAdditionalFields()){
			addGetMethod(sourceBuilder, field);
			addSetMethod(sourceBuilder, field);
			addIsModifiedMethod(sourceBuilder, field);
			addGetPropertyAccessorMethod(sourceBuilder, field);
		}
		
		addSetDtoId(sourceBuilder);
		addGetDtoId(sourceBuilder);
		addDisplayTitle(sourceBuilder);
		addTypeDescription(sourceBuilder);
		addIcon(sourceBuilder);
		addEqualsMethod(sourceBuilder);
		addToString(sourceBuilder);
		addDto2PosoMapper(sourceBuilder);
		addCreatePropertyAccess(sourceBuilder);
		addClearModified(sourceBuilder);
		addIsDtoModified(sourceBuilder);
		addGetPAs(sourceBuilder);
		addGetModifiedPAs(sourceBuilder);
		addGetPAsByView(sourceBuilder);
		addGetPAsByForDtos(sourceBuilder);
		
		addWhitelist(sourceBuilder);
	}


	private void addWhitelist(StringBuilder sourceBuilder) {
		Set<String> whitelist = new HashSet<String>();
		
		for(PosoFieldDescriptor field : posoAnalizer.getExposedFields()){
			if(field.referencesPoso())
				addToWhiteList(field.getPoso(), whitelist);
			if(field.referencesPosoCollection())
				addToWhiteList(field.getPosoReferencedInCollection(), whitelist);
		}
		
		for(PosoAnalizer iPoso : posoAnalizer.getImplementedPosos()){
			for(ExposedClientMethod method : iPoso.getExposedInterfaceMethods()){
				if(method.returnsPoso()){
					addToWhiteList(method.getPoso(), whitelist);
					
				}if(method.returnsPosoCollection())
					addToWhiteList(method.getPosoReferencedInReturnedCollection(), whitelist);
			}
		}
		
		for(ExposedClientMethod method : posoAnalizer.getExposedGetMethods()){
			if(method.returnsPoso())
				addToWhiteList(method.getPoso(), whitelist);
			if(method.returnsPosoCollection())
				addToWhiteList(method.getPosoReferencedInReturnedCollection(), whitelist);
		}
		
		for(ExposedClientMethod method : posoAnalizer.getExposedSetMethods()){
			if(method.returnsPoso())
				addToWhiteList(method.getPoso(), whitelist);
			if(method.returnsPosoCollection())
				addToWhiteList(method.getPosoReferencedInReturnedCollection(), whitelist);
		}
		
		if(posoAnalizer.getDtoInformation().hasWhitelist())
			for(DeclaredType type : posoAnalizer.getDtoInformation().getWhitelist())
				whitelist.add(type.toString());
		
		int i = 0;
		sourceBuilder.append("\n");
		for(String wl : whitelist)
			sourceBuilder.append("\t").append(wl).append(" wl_").append(i++).append(";\n");
	}

	private void addToWhiteList(PosoAnalizer poso, Set<String> whitelist) {
		whitelist.add(poso.getDtoInformation().getFullyQualifiedClassName());
	}

	private void addDto2PosoMapper(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("newPosoMapper", Dto2PosoMapper.class.getSimpleName());
		method.setStatic();
		
		method.addBodyLine("return new " + posoAnalizer.getDtoInformation().getDto2PosoMapClassName() + "();");
		referenceAccu.add(posoAnalizer.getDtoInformation().getDto2PosoMapPackageName() + "." + posoAnalizer.getDtoInformation().getDto2PosoMapClassName());
		referenceAccu.add(Dto2PosoMapper.class.getName());
		
		sourceBuilder.append(method).append("\n");
	}
	
	private void addToString(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("toString", "String");
		method.addOverride();
		
		PosoFieldDescriptor idField = posoAnalizer.getIdField();
		if(null != idField)
			method.addBodyLine("return getClass().getName() + \": \" + " + idField.getGetMethodForDto() + "();");
		else
			method.addBodyLine("return super.toString();");
		
		sourceBuilder.append(method).append("\n");
	}

	private void addSetIdMethod(StringBuilder sourceBuilder,
			PosoFieldDescriptor field) {
		/* preprocess type */
		String type = field.getTypeAnalizer().getKnownDtoType(referenceAccu);

		/* build method */
		MethodBuilder method = new MethodBuilder(field.getSetMethodForDto(), MethodBuilder.VOID, type + " " + field.getName() );
		method.setFinal();
		
		method.beginOneLineBlock("if (null != " + ID_FIELD_NAME + ")");
		method.addBodyLine("throw new IllegalStateException(\"Id already set!\");");
		referenceAccu.add(IllegalStateException.class.getName());
		
		method.addBodyLine("this." + ID_FIELD_NAME + " = " + field.getName() + ";");
		
		sourceBuilder.append(method).append("\n");
	}

	private void addGetIdMethod(StringBuilder sourceBuilder,
			PosoFieldDescriptor field) {
		/* preprocess type */
		String type = field.getTypeAnalizer().getKnownDtoType(referenceAccu);

		/* build method */
		MethodBuilder method = new MethodBuilder(field.getGetMethodForDto(), type);
		method.setFinal();
		
		method.addBodyLine("return " + ID_FIELD_NAME + ";");
		
		sourceBuilder.append(method).append("\n");
	}

	protected void addSetDtoId(StringBuilder sourceBuilder) {
		PosoFieldDescriptor idField = posoAnalizer.getIdField();
		
		if(null == idField || posoAnalizer.hasIdFieldInHeritage())
			return;
		
		MethodBuilder method = new MethodBuilder("setDtoId", "void", "Object id");
		method.addOverride();
		
		method.addBodyLine(idField.getSetMethodForDto() + "((" + idField.getTypesSimpleName() + ") id);");
		
		sourceBuilder.append(method).append("\n");
	}
	
	protected void addGetDtoId(StringBuilder sourceBuilder) {
		PosoFieldDescriptor idField = posoAnalizer.getIdField();
		
		if(null == idField || posoAnalizer.hasIdFieldInHeritage())
			return;
		
		MethodBuilder method = new MethodBuilder("getDtoId", "Object");
		method.addOverride();
		
		method.addBodyLine("return " + idField.getGetMethod() + "();");
		
		sourceBuilder.append(method).append("\n");
	}
	
	protected void addDisplayTitle(StringBuilder sourceBuilder) {
		if(! posoAnalizer.getDtoInformation().hasSpecializedDisplayTitle())
			return ;
		
		MethodBuilder method = new MethodBuilder("toDisplayTitle", "String");
		method.addAnnotation("Override");
		
		PosoFieldDescriptor displayField = posoAnalizer.getDisplayTitleField();
		
		method.beginTryBlock();
		if(null != displayField){
			method.beginOneLineBlock("if(null == " + displayField.getGetMethodForDto() + "())");
			method.addBodyLine("return BaseMessages.INSTANCE.unnamed();");
			method.addBodyLine("return " + displayField.getGetMethodForDto() + "().toString();");
		} else {
			method.addBodyLine("return " + posoAnalizer.getGenerateDtoAnnotation().displayTitle() + ";");
		}

		
		referenceAccu.add(NullPointerException.class.getName());
		method.beginCatchBlock("NullPointerException e");
		
		method.addBodyLine("return BaseMessages.INSTANCE.unnamed();");
		method.endBodyBlock();
		
		referenceAccu.add(BaseMessages.class.getName());
		
		sourceBuilder.append(method).append("\n");
	}
	
	protected void addTypeDescription(StringBuilder sourceBuilder) {
		DeclaredType type = posoAnalizer.getDtoInformation().getTypeDescriptionMsgInterface();
		if(null == type)
			return;
		
		MethodBuilder method = new MethodBuilder("toTypeDescription", "String");
		method.addAnnotation("Override");

		String iFace = SourceFileGenerationUtils.getSimpleTypeName(type);
		referenceAccu.add(type.toString());
		
		method.addBodyLine("return " + iFace + ".INSTANCE." + posoAnalizer.getGenerateDtoAnnotation().typeDescriptionKey() + "();");
				
		sourceBuilder.append(method).append("\n");
	}
	
	protected void addIcon(StringBuilder sourceBuilder) {
		AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
		
		boolean cont = false;
		for(ExecutableElement key : gdAnno.getElementValues().keySet()){
			if(key.toString().equals("icon()")){
				cont = true;
			}
		}
		
		if(! cont)
			return;

		String icon = posoAnalizer.getGenerateDtoAnnotation().icon();
		
		MethodBuilder method = new MethodBuilder("toIcon", "BaseIcon");
		method.addAnnotation("Override");

		referenceAccu.add("net.datenwerke.rs.theme.client.icon.BaseIcon");
		
		method.addBodyLine("return BaseIcon.from(\"" + icon + "\");");
				
		sourceBuilder.append(method).append("\n");
	}

	/**
	 * 
	 * @param sourceBuilder
	 * @param exposedMethod Either a get or set method
	 */
	protected void addAdditionalGetMethod(StringBuilder sourceBuilder, ExposedClientMethod exposedMethod) {
		/* prepare return type */
		String returnType;
		if(exposedMethod.isGetMethod())
			returnType = exposedMethod.getReturnTypeAnalizer().getKnownDtoType(referenceAccu);
		else {
			VariableElement parameter = exposedMethod.getParameters().get(0);
			returnType = new TypeAnalizer(dtoAnnotationProcessor, parameter.asType()).getCorrectParameterType(referenceAccu);
		}
		
		addGetMethod(sourceBuilder, 
				exposedMethod.getGetMethodForDto(),
				returnType,
				exposedMethod.getFieldName(),
				exposedMethod.getSetMethodForDto(),
				exposedMethod.getIsPropertyModifiedMethodForDto(),
				exposedMethod.getReturnType(),
				exposedMethod.returnsCollection(),
				exposedMethod.returnsPosoCollection(),
				exposedMethod.returnsPosoCollection() ? exposedMethod.getPosoReferencedInReturnedCollection().getDtoInformation().getClassName() : null,
				exposedMethod.returnsPosoClass(),
				exposedMethod.returnsSet(),
				exposedMethod.returnsList());
		
	}


	/**
	 * 
	 * @param sourceBuilder
	 * @param exposedMethod Either a get or set method.
	 */
	protected void addAdditionalSetMethod(StringBuilder sourceBuilder, ExposedClientMethod exposedMethod) {
		/* prepare argument */
		String type = null;
		if(exposedMethod.isSetMethod()){
			VariableElement parameter = exposedMethod.getParameters().get(0);
			type = new TypeAnalizer(dtoAnnotationProcessor, parameter.asType()).getCorrectParameterType(referenceAccu);
		} else {
			type = exposedMethod.getReturnTypeAnalizer().getKnownDtoType(referenceAccu);
		}
			
		
		addSetMethod(sourceBuilder, exposedMethod.getSetMethodForDto(), exposedMethod.getGetMethodForDto(), type, exposedMethod.getFieldName(), exposedMethod.getFieldModifiedIndicator(), exposedMethod.getFieldPropertyAccessor());
	}
	
	protected void addCreatePropertyAccess(StringBuilder sourceBuilder) {
		/* create method */
		MethodBuilder method = new MethodBuilder(PROPERTY_ACCESS_METHOD, posoAnalizer.getDtoInformation().getDto2PropertyAccessClassName());
		referenceAccu.add(posoAnalizer.getDtoInformation().getFullyQualifiedPropertyAccesClassName());
		
		method.addBodyLine("return GWT.create(" + posoAnalizer.getDtoInformation().getDto2PropertyAccessClassName() + ".class);");
		referenceAccu.add(GWT.class.getName());
		
		sourceBuilder.append(method).append("\n");
	}

	protected void addEqualsMethod(StringBuilder sourceBuilder) {
		PosoFieldDescriptor idField = posoAnalizer.getIdField();
		
		if(null == idField)
			return;
		
		String getIdMethod = idField.getGetMethodForDto();
		
		/* hashcode */
		MethodBuilder hashCodeBuilder = new MethodBuilder("hashCode", "int");
		hashCodeBuilder.addAnnotation("Override");
		
		
		if(idField.getType() instanceof DeclaredType){
			/* test for null */
			hashCodeBuilder.beginOneLineBlock("if(null == " + getIdMethod + "())");
			hashCodeBuilder.addBodyLine("return super.hashCode();");
			
			hashCodeBuilder.addBodyLine("return " + getIdMethod + "().hashCode();");
		} else {
			/* test for null */
			hashCodeBuilder.beginOneLineBlock("if(0 == " + getIdMethod + "())");
			hashCodeBuilder.addBodyLine("return super.hashCode();");
			
			if(idField.getTypesSimpleName().equals("int"))
				hashCodeBuilder.addBodyLine("return " + getIdMethod + "();");
			else if( idField.getTypesSimpleName().equals("long"))
				hashCodeBuilder.addBodyLine("return (int)" + getIdMethod + "();");
			else
				dtoAnnotationProcessor.error("Id field has unsupported primitive type: " + idField.getType());
		}
			
		
		sourceBuilder.append(hashCodeBuilder).append("\n");
		
		/* equals */
		MethodBuilder equalsBuilder = new MethodBuilder("equals", "boolean", "Object obj");
		equalsBuilder.addAnnotation("Override");
		
		equalsBuilder.beginOneLineBlock("if(! (obj instanceof " + posoAnalizer.getDtoInformation().getClassName() + "))");
		equalsBuilder.addBodyLine("return false;");
		equalsBuilder.addBodyLine();

		
		/* primitive type ?*/
		if(idField.getType() instanceof DeclaredType){
			/* test for null */
			equalsBuilder.beginOneLineBlock("if(null == " + getIdMethod + "())");
			equalsBuilder.addBodyLine("return super.equals(obj);");
			
			/* add return statement */
			equalsBuilder.addBodyLine("return " + getIdMethod + "().equals(((" + posoAnalizer.getDtoInformation().getClassName() + ")obj)." + getIdMethod + "());");
		} else {
			/* test for null */
			equalsBuilder.beginOneLineBlock("if(0 == " + getIdMethod + "())");
			equalsBuilder.addBodyLine("return super.equals(obj);");
			
			/* add return statement */
			equalsBuilder.addBodyLine("return ((" + posoAnalizer.getDtoInformation().getClassName() + ")obj)." + getIdMethod + "() == " + getIdMethod + "();");
		}
			
			
			
		sourceBuilder.append(equalsBuilder).append("\n");
	}

	protected void addGetMethod(StringBuilder sourceBuilder, PosoFieldDescriptor field) {
		/* preprocess type */
		String type = field.getTypeAnalizer().getKnownDtoType(referenceAccu);

		addGetMethod(sourceBuilder, 
					field.getGetMethodForDto(), 
					type, 
					field.getName(), 
					field.getSetMethodForDto(), 
					field.getIsPropertyModifiedMethodForDto(),
					field.getType(),
					field.referencesCollection(),
					field.referencesPosoCollection(),
					field.referencesPosoCollection() ? field.getPosoReferencedInCollection().getDtoInformation().getClassName() : null,
					field.referencesPosoClass(),
					field.isSet(),
					field.isList());
	}
	

	protected void addGetMethod(StringBuilder sourceBuilder, DtoField field) {
		addGetMethod(sourceBuilder, 
					field.getGetMethod(), 
					field.getKnownDtoType(dtoAnnotationProcessor, referenceAccu), 
					field.getName(), 
					field.getSetMethod(), 
					field.getIsModifiedMethod(),
					field.getType(),
					field.isCollection(dtoAnnotationProcessor),
					field.isPosoCollection(dtoAnnotationProcessor),
					field.isPosoCollection(dtoAnnotationProcessor) ? field.getPosoReferencedInCollection(dtoAnnotationProcessor).getDtoInformation().getClassName() : null,
					field.isPosoClass(dtoAnnotationProcessor),
					field.isSet(dtoAnnotationProcessor),
					field.isList(dtoAnnotationProcessor),
					field.getGenericTypeString());
	}
		
	protected void addGetMethod(StringBuilder sourceBuilder, String methodName, String type, String fieldName, String setMethod, String modifiedMethod, TypeMirror fieldType, boolean referencesCollection, boolean referencesPosoCollection, String dtoClassInPosoCollection, boolean referencesPosoClass, boolean isSet, boolean isList) {
		addGetMethod(sourceBuilder, methodName, type, fieldName, setMethod, modifiedMethod, fieldType, referencesCollection, referencesPosoCollection, dtoClassInPosoCollection, referencesPosoClass, isSet, isList, null);
	}
	
	protected void addGetMethod(StringBuilder sourceBuilder, String methodName, String type, String fieldName, String setMethod, String modifiedMethod, TypeMirror fieldType, boolean referencesCollection, boolean referencesPosoCollection, String dtoClassInPosoCollection, boolean referencesPosoClass, boolean isSet, boolean isList, String overrideTypeArguments) {
		/* build method */
		MethodBuilder method = new MethodBuilder(methodName, type);

		method.beginBodyBlock("if(! isDtoProxy())");
		
			/* if a collection is null return an empty collection */
			if(referencesCollection){
				method.addBodyLine(type + " _currentValue = this." + fieldName + ";");
				method.beginOneLineBlock("if(null == _currentValue)");
				
				Collection instantiatedCollection = SourceFileGenerationUtils.instantiateCollection((DeclaredType) fieldType);
				referenceAccu.add(instantiatedCollection.getClass().getName());
	
				String typeArgument;
				if(null != overrideTypeArguments)
					typeArgument = overrideTypeArguments;
				else {
					if(referencesPosoCollection)
						typeArgument = dtoClassInPosoCollection;
					else
						typeArgument = SourceFileGenerationUtils.getTypeArguments((DeclaredType) fieldType);
				}
				
				method.addBodyLine("this." + fieldName + " = new " + instantiatedCollection.getClass().getSimpleName() + "<" + typeArgument +  ">();");
				method.addBodyLine();
				method.addBodyLine("return this." + fieldName + ";");
			} else 
				method.addBodyLine("return this." + fieldName + ";");
		
		method.endBodyBlock();
		method.addBodyLine();
		
		/* check modified */
		method.beginOneLineBlock("if(" + modifiedMethod + "())");
		method.addBodyLine("return this." + fieldName + ";");
		method.addBodyLine();
		
		/* check server */
		method.beginOneLineBlock("if(! GWT.isClient())");
		method.addBodyLine("return " + SourceFileGenerationUtils.getNullValueFor(type) +";");
		method.addBodyLine();
		referenceAccu.add(GWT.class.getName());
		
		/* get value from manager */
		method.addBodyLine(type + " _value = dtoManager.getProperty(this, " + PROPERTY_ACCESS_METHOD + "()." + fieldName + "());" );
		method.addBodyLine();
		
		if(referencesPosoCollection){
			if(isSet){
				method.addBodyLine("_value = new " + ChangeMonitoredSet.class.getSimpleName() + "<" + dtoClassInPosoCollection + ">(_value);" );
				referenceAccu.add(ChangeMonitoredSet.class.getName());
			} else if(isList){
				method.addBodyLine("_value = new " + ChangeMonitoredList.class.getSimpleName() + "<" + dtoClassInPosoCollection + ">(_value);" );
				referenceAccu.add(ChangeMonitoredList.class.getName());
			} else
				throw new IllegalArgumentException("Unknown Collection Type");
		}
		
		if(referencesPosoClass || referencesPosoCollection){
			referenceAccu.add(HasObjectChangedEventHandler.class.getName());
			referenceAccu.add(ObjectChangedEvent.class.getName());
			referenceAccu.add(ObjectChangedEventHandler.class.getName());
			referenceAccu.add(Dto.class.getName());
			
			method.beginBodyBlock("if(_value instanceof HasObjectChangedEventHandler)");
				method.beginBodyBlock("((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new " + ObjectChangedEventHandler.class.getName() + "()");
					method.addBodyLine("@Override");
					method.beginBodyBlock("public void onObjectChangedEvent(" + ObjectChangedEvent.class.getName() + " event)");
					method.beginOneLineBlock("if(! " + modifiedMethod + "())");
					if(referencesPosoCollection){
						method.addBodyLine(setMethod + "((" + type + ") ((" + MonitoredCollection.class.getSimpleName() + ") event.getObject()).getUnderlyingCollection());");
						referenceAccu.add(MonitoredCollection.class.getName());
					}else
						method.addBodyLine(setMethod + "((" + type + ") event.getObject());");
					method.endBodyBlock();
				method.endBodyBlock();
				method.addBodyLine(");");
			method.endBodyBlock();
		}
		
		
		method.addBodyLine("return _value;");
		
		/* add method to class */
		sourceBuilder.append(method).append("\n\n");;
	}
	

	protected void addSetMethod(StringBuilder sourceBuilder, PosoFieldDescriptor field) {
		/* preprocess type */
		String type = field.getTypeAnalizer().getKnownDtoType(referenceAccu);

		addSetMethod(sourceBuilder, field.getSetMethodForDto(), field.getGetMethodForDto(), type, field.getName(), field.getFieldModifiedIndicator(), field.getFieldPropertyAccessor());
	}
	
	protected void addSetMethod(StringBuilder sourceBuilder, DtoField field) {
		addSetMethod(sourceBuilder, field.getSetMethod(), field.getGetMethod(),  field.getKnownDtoType(dtoAnnotationProcessor, referenceAccu), field.getName(), field.getModIndicator(), field.getPropertyAccessor());
	}
	
	protected void addSetMethod(StringBuilder sourceBuilder, String setMethodName, String getMethodName, String type, String fieldName, String modifiedIndicator, String propertyAccessor) {
		/* build method */
		MethodBuilder method = new MethodBuilder(setMethodName, MethodBuilder.VOID, type + " " + fieldName );
		
		method.addBodyComment("old value");
		method.addBodyLine(type + " oldValue = " +  String.valueOf(SourceFileGenerationUtils.getNullValueFor(type)) + ";");
		method.beginOneLineBlock("if(GWT.isClient())");
		method.addBodyLine("oldValue = " + getMethodName + "();");
		method.addBodyLine();
		
		method.addBodyComment("set new value");
		method.addBodyLine("this." + fieldName + " = " + fieldName + ";");
		method.addBodyLine();
		
		method.beginOneLineBlock("if(! GWT.isClient())");
			method.addBodyLine("return;");
		method.addBodyLine();
		
		method.beginOneLineBlock("if(isTrackChanges())");
		method.addBodyLine("addChange(new " + ChangeTracker.class.getSimpleName() + "(" + propertyAccessor + ", oldValue, " + fieldName + ", this." + modifiedIndicator + "));");
		
		method.addBodyLine();
		
		method.addBodyComment("set indicator");
		method.addBodyLine("this." + modifiedIndicator + " = true;");
		method.addBodyLine();
		method.addBodyLine("this.fireObjectChangedEvent(" + posoAnalizer.getDtoInformation().getDto2PropertyAccessClassName() + ".INSTANCE." + fieldName + "(), oldValue);");

		/* add method to class */
		sourceBuilder.append(method).append("\n\n");
	}
	
	protected void addIsModifiedMethod(StringBuilder sourceBuilder, PosoFieldDescriptor field) {
		addIsModifiedMethod(sourceBuilder, field.getIsPropertyModifiedMethodForDto(), field.getFieldModifiedIndicator());
	}
	
	protected void addIsModifiedMethod(StringBuilder sourceBuilder, ExposedClientMethod method) {
		addIsModifiedMethod(sourceBuilder, method.getIsPropertyModifiedMethodForDto(), method.getFieldModifiedIndicator());
	}
	

	protected void addIsModifiedMethod(StringBuilder sourceBuilder, DtoField field) {
		addIsModifiedMethod(sourceBuilder, field.getIsModifiedMethod(), field.getModIndicator());
	}
	
	protected void addIsModifiedMethod(StringBuilder sourceBuilder, String name, String field) {
		/* build method */
		MethodBuilder method = new MethodBuilder(name, "boolean");
		
		method.addBodyLine("return " + field + ";");
		
		/* add method to class */
		sourceBuilder.append(method).append("\n\n");
	}
	
	protected void addGetPropertyAccessorMethod(StringBuilder sourceBuilder, PosoFieldDescriptor field) {
		/* preprocess type */
		String type = field.getTypeAnalizer().getKnownDtoType(referenceAccu);
		
		addGetPropertyAccessorMethod(sourceBuilder, type, field.getPropertyAccessorMethodForDto(), field.getFieldPropertyAccessor());
	}
	
	
	protected void addGetPropertyAccessorMethod(StringBuilder sourceBuilder, ExposedClientMethod method) {
		/* preprocess type */
		String type = method.getReturnTypeAnalizer().getKnownDtoType(referenceAccu);
		
		addGetPropertyAccessorMethod(sourceBuilder, type, method.getPropertyAccessorMethodForDto(), method.getFieldPropertyAccessor());
	}
	
	protected void addGetPropertyAccessorMethod(StringBuilder sourceBuilder,
			DtoField field) {
		addGetPropertyAccessorMethod(sourceBuilder, field.getKnownDtoType(dtoAnnotationProcessor, referenceAccu), field.getGetPropertyAccessorMethod(), field.getPropertyAccessor());
	}

	
	protected void addGetPropertyAccessorMethod(StringBuilder sourceBuilder, String type, String methodName, String field) {
		/* build method */
		MethodBuilder method = new MethodBuilder(methodName, PropertyAccessor.class.getSimpleName() + "<" + posoAnalizer.getDtoInformation().getClassName() + ", " + SourceFileGenerationUtils.unproxySimpleType(type) + ">");
		method.setStatic();
		referenceAccu.add(PropertyAccessor.class.getName());
		
		method.addBodyLine("return " + field + ";");
		
		/* add method to class */
		sourceBuilder.append(method).append("\n\n");
	}
	
	protected void addClearModified(StringBuilder sourceBuilder) {
		/* build method */
		MethodBuilder methodBuilder = new MethodBuilder(CLEAR_MODIFIED_METHOD, MethodBuilder.VOID);

		/* prepare map of constants */
		for(DtoField field : posoAnalizer.getDtoFieldObejcts()){
			methodBuilder.addBodyLine("this." + field.getName() + " = " + SourceFileGenerationUtils.getNullValueFor(field.getKnownDtoType(dtoAnnotationProcessor, referenceAccu)) + ";");
			methodBuilder.addBodyLine("this." + field.getModIndicator() + " = false;");
		}
		
		/* add method to class */
		sourceBuilder.append(methodBuilder).append("\n\n");
	}
	
	protected void addIsDtoModified(StringBuilder sourceBuilder) {
		/* build method */
		MethodBuilder methodBuilder = new MethodBuilder(IS_MODIFIED_METHOD, "boolean");

		methodBuilder.beginOneLineBlock("if(super." + IS_MODIFIED_METHOD + "())");
		methodBuilder.addBodyLine("return true;");
		
		/* prepare map of constants */
		for(DtoField field : posoAnalizer.getDtoFieldObejcts()){
			methodBuilder.beginOneLineBlock("if(" + field.getModIndicator() + ")");
			methodBuilder.addBodyLine("return true;");
		}
		
		methodBuilder.addBodyLine("return false;");
		
		/* add method to class */
		sourceBuilder.append(methodBuilder).append("\n\n");
	}
	
	protected void addGetPAs(StringBuilder sourceBuilder) {
		/* build method */
		MethodBuilder methodBuilder = new MethodBuilder(GET_PROPERTY_ACCESSORS, "List<" + PropertyAccessor.class.getSimpleName() + ">");
		referenceAccu.add(LIST_LOCATION);
		referenceAccu.add(ArrayList.class.getName());
		
		methodBuilder.addBodyLine("List<" + PropertyAccessor.class.getSimpleName() + "> list = super." + GET_PROPERTY_ACCESSORS + "();");
		
		/* prepare map of constants */
		for(DtoField field : posoAnalizer.getDtoFieldObejcts())
			methodBuilder.addBodyLine("list.add(" + field.getPropertyAccessor() + ");");
		
		methodBuilder.addBodyLine("return list;");
		
		/* add method to class */
		sourceBuilder.append(methodBuilder).append("\n\n");
	}
	
	
	protected void addGetPAsByView(StringBuilder sourceBuilder) {
		/* build method */
		MethodBuilder methodBuilder = new MethodBuilder(GET_PROPERTY_ACCESSORS_BY_VIEW, "List<" + PropertyAccessor.class.getSimpleName() + ">", DtoView.class.getName() + " view");
		referenceAccu.add(LIST_LOCATION);
		referenceAccu.add(ArrayList.class.getName());
		referenceAccu.add(DtoView.class.getName());
		
		methodBuilder.addBodyLine("List<" + PropertyAccessor.class.getSimpleName() + "> list = super." + GET_PROPERTY_ACCESSORS_BY_VIEW + "(view);");
		
		/* prepare map of constants */
		for(DtoView view : DtoView.values()){
			boolean first = true;
			for(DtoField field : posoAnalizer.getDtoFieldObejcts()){
				if(view == field.getDtoView()){
					if(first){
						methodBuilder.beginBodyBlock("if(view.compareTo(" + view.getClass().getSimpleName() + "." + view.name() + ") >= 0)");
						first = false;
					}
					methodBuilder.addBodyLine("list.add(" + field.getPropertyAccessor() + ");");
				}
			}
			if(! first)
				methodBuilder.endBodyBlock();
		}
		
		
		methodBuilder.addBodyLine("return list;");
		
		/* add method to class */
		sourceBuilder.append(methodBuilder).append("\n\n");
	}
	
	protected void addGetPAsByForDtos(StringBuilder sourceBuilder) {
		/* build method */
		MethodBuilder methodBuilder = new MethodBuilder(GET_PROPERTY_ACCESSORS_FOR_DTOS, "List<" + PropertyAccessor.class.getSimpleName() + ">");
		referenceAccu.add(LIST_LOCATION);
		referenceAccu.add(ArrayList.class.getName());
		referenceAccu.add(DtoView.class.getName());
		
		methodBuilder.addBodyLine("List<" + PropertyAccessor.class.getSimpleName() + "> list = super." + GET_PROPERTY_ACCESSORS_FOR_DTOS + "();");
		
		/* prepare map of constants */
		for(DtoField field : posoAnalizer.getDtoFieldObejcts())
			if(field.isPosoClass(dtoAnnotationProcessor) || field.isPosoCollection(dtoAnnotationProcessor))
				methodBuilder.addBodyLine("list.add(" + field.getPropertyAccessor() + ");");	
		
		methodBuilder.addBodyLine("return list;");
		
		/* add method to class */
		sourceBuilder.append(methodBuilder).append("\n\n");
	}
	
	protected void addGetModifiedPAs(StringBuilder sourceBuilder) {
		/* build method */
		MethodBuilder methodBuilder = new MethodBuilder(GET_MODIFIED_PROPERTY_ACCESSORS, "List<" + PropertyAccessor.class.getSimpleName() + ">");
		referenceAccu.add(LIST_LOCATION);
		referenceAccu.add(ArrayList.class.getName());
		
		methodBuilder.addBodyLine("List<" + PropertyAccessor.class.getSimpleName() + "> list = super." + GET_MODIFIED_PROPERTY_ACCESSORS + "();");
		
		/* prepare map of constants */
		for(DtoField field : posoAnalizer.getDtoFieldObejcts()){
			methodBuilder.beginOneLineBlock("if(" + field.getModIndicator() + ")");
			methodBuilder.addBodyLine("list.add(" + field.getPropertyAccessor() + ");");
		}
		
		methodBuilder.addBodyLine("return list;");
		
		/* add method to class */
		sourceBuilder.append(methodBuilder).append("\n\n");
	}
	
	protected void addConstructors(StringBuilder sourceBuilder) {
		/* constructor declaration */
		sourceBuilder.append("\n\tpublic ")
				.append(getClassName())
				.append("() {\n");
		
		/* constructor declaration */
		sourceBuilder.append("\t\tsuper();\n");
			
		/* constructor end */
		sourceBuilder.append("\t}\n\n");
		
	}

	@Override
	protected void addClassComment(StringBuilder sourceBuilder) {
		referenceAccu.add(posoAnalizer.getFullyQualifiedClassName());
		
		sourceBuilder.append("\n/**\n")
					.append(" * Dto for {@link ").append(posoAnalizer.getSimpleName()).append("}\n")
					.append(" *\n")
					.append(" * This file was automatically created by ")
						.append(DtoAnnotationProcessor.name)
						.append(", version ")
						.append(DtoAnnotationProcessor.version)
						.append("\n")
					.append(" */\n");
	}

	protected void addFields(StringBuilder sourceBuilder) {
		sourceBuilder.append("\n");
		sourceBuilder.append("\t/* Fields */");

		for(DtoField field : posoAnalizer.getDtoFieldObejcts()){
			String type = field.getKnownDtoType(dtoAnnotationProcessor, referenceAccu);
			
			sourceBuilder.append("\n\t" + field.getVisibility() + " " + type + " " + field.getName() + ";\n");
			sourceBuilder.append("\t" + field.getVisibility() + "  boolean " + field.getModIndicator() + ";\n");
			sourceBuilder.append("\tpublic static final String " + field.getPropertyIdName() + " = \"dpi-" + posoAnalizer.getSimpleName().toLowerCase() + "-" + field.getName().toLowerCase() + "\";\n\n");
			sourceBuilder.append("\tprivate transient static PropertyAccessor<" + posoAnalizer.getDtoInformation().getClassName() + ", " + SourceFileGenerationUtils.unproxySimpleType(type) + "> " + field.getPropertyAccessor() + " = new PropertyAccessor<" + posoAnalizer.getDtoInformation().getClassName() + ", " +  SourceFileGenerationUtils.unproxySimpleType(type) + ">() {\n");
			
			sourceBuilder.append("\t\t@Override\n");
			sourceBuilder.append("\t\tpublic void setValue(" + posoAnalizer.getDtoInformation().getClassName() + " container, " + SourceFileGenerationUtils.unproxySimpleType(type) + " object) {\n");
			if(field.isIdField())
				sourceBuilder.append("\t\t\t// id field\n");
			else
				sourceBuilder.append("\t\t\tcontainer." + field.getSetMethod() + "(object);\n");
			sourceBuilder.append("\t\t}\n\n");

			sourceBuilder.append("\t\t@Override\n");
			sourceBuilder.append("\t\tpublic " + SourceFileGenerationUtils.unproxySimpleType(type) + " getValue(" + posoAnalizer.getDtoInformation().getClassName() + " container) {\n");
			sourceBuilder.append("\t\t\treturn container." + field.getGetMethod() + "();\n");
			sourceBuilder.append("\t\t}\n\n");
			
			sourceBuilder.append("\t\t@Override\n");
			sourceBuilder.append("\t\tpublic Class<?> getType() {\n");
			sourceBuilder.append("\t\t\treturn " + SourceFileGenerationUtils.unproxySimpleType(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(type)) + ".class;\n");
			sourceBuilder.append("\t\t}\n\n");

			sourceBuilder.append("\t\t@Override\n");
			sourceBuilder.append("\t\tpublic String getPath() {\n");
			sourceBuilder.append("\t\t\treturn \"" + field.getName() +"\";\n");
			sourceBuilder.append("\t\t}\n\n");
			
			sourceBuilder.append("\t\t@Override\n");
			sourceBuilder.append("\t\tpublic void setModified(" +  posoAnalizer.getDtoInformation().getClassName() + " container, boolean modified) {\n");
			sourceBuilder.append("\t\t\tcontainer." + field.getModIndicator()  + " = modified;\n");
			sourceBuilder.append("\t\t}\n\n");
			
			sourceBuilder.append("\t\t@Override\n");
			sourceBuilder.append("\t\tpublic boolean isModified(" + posoAnalizer.getDtoInformation().getClassName() + " container) {\n");
			sourceBuilder.append("\t\t\treturn container." + field.getIsModifiedMethod() + "();\n");
			sourceBuilder.append("\t\t}\n");
			sourceBuilder.append("\t};\n");
		}
		
		sourceBuilder.append("\n");
	}
	
	@Deprecated
	protected void addFieldConstants(StringBuilder sourceBuilder) {
		sourceBuilder.append("\n");
		sourceBuilder.append("\t/* Field Constants */\n");

		/* prepare map of constants */
		Map<String, String> constants = new TreeMap<String, String>();
		for(PosoFieldDescriptor field : posoAnalizer.getExposedFields())
			constants.put(field.getConstantNameForClientKey(), field.getName().toUpperCase());
		for(ExposedClientMethod method : posoAnalizer.getExposedGetMethods())
			constants.put(method.getConstantFieldName(), method.getSimpleName().toString().substring(3).toUpperCase());
		for(ExposedClientMethod method : posoAnalizer.getExposedSetMethods())
			constants.put(method.getConstantFieldName(), method.getSimpleName().toString().substring(3).toUpperCase());
		for(PosoAnalizer iPoso : posoAnalizer.getImplementedPosos()){
			for(ExposedClientMethod method : iPoso.getExposedInterfaceMethods()){
				if(method.isGetMethod())
					if(SourceFileGenerationUtils.isBooleanType(method.getReturnType()))
						constants.put(method.getConstantFieldName(), method.getSimpleName().toString().substring(2).toUpperCase());
					else
						constants.put(method.getConstantFieldName(), method.getSimpleName().toString().substring(3).toUpperCase());
			}
		}
		
		/* add constants to class */
		for(Entry<String, String> entry : constants.entrySet())
			sourceBuilder.append("\tpublic static final String ")
						.append(entry.getKey())
						.append(" = \"")
						.append(SourceFileGenerationUtils.camelCaseToUnderscoreUpperCase(posoAnalizer.getSimpleName()) + "_" + entry.getValue())
						.append("\"; //$NON-NLS-1$\n");
		
		
		sourceBuilder.append("\n");
	}


	@Override
	public String getPackageName() {
		return posoAnalizer.getDtoInformation().getPackageName();
	}

	@Override
	public String getClassName() {
		return posoAnalizer.getDtoInformation().getClassName();
	}

	@Override
	public String getFullyQualifiedClassName() {
		return posoAnalizer.getDtoInformation().getFullyQualifiedClassName();
	}
	
	@Override
	protected void addClassBody(StringBuilder sourceBuilder) {
		addSerialVersionUID(sourceBuilder);
		if(posoAnalizer.getDtoInformation().isProxyable())
			addIdField(sourceBuilder);
//		addFieldConstants(sourceBuilder);
		
		addFields(sourceBuilder);
		
		addConstructors(sourceBuilder);
		
		addMethods(sourceBuilder);
	}
	

	protected void addIdField(StringBuilder sourceBuilder) {
		PosoFieldDescriptor idField = posoAnalizer.getIdField();
		if(posoAnalizer.hasIdFieldInHeritage() || null == idField)
			return;
		
		sourceBuilder.append("\tprivate " + idField.getTypeAnalizer().getKnownDtoType(referenceAccu) + " " + ID_FIELD_NAME + ";\n\n");
	}

	@Override
	protected String getExtendedClass() {
		/* test for specific extend */
		AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
		for(ExecutableElement key : gdAnno.getElementValues().keySet()){
			if(key.toString().equals("dtoExtends()")){
				AnnotationValue value = gdAnno.getElementValues().get(key);
				Object toExtend = value.getValue();
				if(! (toExtend instanceof DeclaredType))
					throw new IllegalStateException(toExtend + " is not of type DeclaredType but a " + toExtend.getClass() );
				
				DeclaredType type = (DeclaredType) toExtend;
				
				referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(type));
				return SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(type);
			}
		}
		
		/* test for default */
		if(! posoAnalizer.extendsPoso()){
			/* specialized class ? */
			if(null == dtoAnnotationProcessor.getOptionDtoSuperClass()){
				referenceAccu.add(Dto.class.getName());
				return Dto.class.getSimpleName();
			} else {
				String superClass = dtoAnnotationProcessor.getOptionDtoSuperClass();
				referenceAccu.add(superClass);
				return superClass.substring(superClass.lastIndexOf(".") + 1);
			}
		}
		
		/* use poso */
		PosoAnalizer parentAnalizer = dtoAnnotationProcessor.getPosoAnalizerFor(posoAnalizer.getParentPoso());
	
		/* decorator */
		if(parentAnalizer.getDtoInformation().hasDecorator()){
			referenceAccu.add(parentAnalizer.getDtoInformation().getFullyQualifiedClassNameForDecorator());
			return parentAnalizer.getDtoInformation().getClassNameForDecorator();
		}
		
		/* plain old server object */
		referenceAccu.add(parentAnalizer.getDtoInformation().getFullyQualifiedClassName());
		return parentAnalizer.getDtoInformation().getClassName();
	
	}

	@Override
	protected Collection<String> getReferencedClasses() {
		/* references */
		Collection<String> imports = super.getReferencedClasses();
		
		imports.add(SET_LOCATION);
		imports.add(PropertyAccessor.class.getName());
		imports.add(ChangeTracker.class.getName());
		
		if(posoAnalizer.getDtoInformation().hasAdditionalImports())
			for(DeclaredType type : posoAnalizer.getDtoInformation().getAdditionalImports())
				imports.add(((TypeElement)type.asElement()).getQualifiedName().toString());
		
		imports.addAll(referenceAccu);
		
		return imports;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Collection<String> getImplementedInterfaces() {
		Collection<String> interfaces = new TreeSet<String>();
		
		for(PosoAnalizer ifacePoso : posoAnalizer.getImplementedPosos()){
			referenceAccu.add(ifacePoso.getDtoInformation().getFullyQualifiedClassName());
			interfaces.add(ifacePoso.getDtoInformation().getClassName());
		}
		
		/* proxyable dto */
		if(null != posoAnalizer.getIdField() && ! posoAnalizer.hasIdFieldInHeritage()){
			referenceAccu.add(IdedDto.class.getName());
			interfaces.add(IdedDto.class.getSimpleName());
		}
		
		/* additional interfaces */
		AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
		for(ExecutableElement key : gdAnno.getElementValues().keySet()){
			if(key.toString().equals("dtoImplementInterfaces()")){
				AnnotationValue value = gdAnno.getElementValues().get(key);
				Object toImplement = value.getValue();
				
				if(toImplement instanceof Collection){
					for(AnnotationValue attr : (Collection<AnnotationValue>) toImplement){
						dtoAnnotationProcessor.debug("Interface: " + attr.getValue());
						if(! (attr.getValue() instanceof DeclaredType))
							continue;
						DeclaredType type = (DeclaredType) attr.getValue();
						
						referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(type));
						interfaces.add(SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(type));
					}
				} else
					throw new IllegalStateException("I do not recognize the interface: " + toImplement + " - " + toImplement.getClass().getName());
				 
				
				break;
			}
		}
		
		
		
		return interfaces;
	}

	@Override
	protected boolean isAbstract() {
		/* if we have to implement a non get/set method */
		for(PosoAnalizer iPoso : posoAnalizer.getImplementedPosos())
			for(ExposedClientMethod method : iPoso.getExposedInterfaceMethods())
				if(! method.isGetSetMethod())
					return true;
					
		return posoAnalizer.getDtoInformation().isAbstractDto() || posoAnalizer.getDtoInformation().hasDecorator();
	}

	
}
