package net.datenwerke.dtoservices.dtogenerator.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

public class SourceFileGenerationUtils {

	private static DtoAnnotationProcessor dtoAnnotationProcessor;
	
	
	public static void setAnnotationProcessor(DtoAnnotationProcessor dtoAnnotationProcessor){
		SourceFileGenerationUtils.dtoAnnotationProcessor = dtoAnnotationProcessor;
	}
	
	
	public static String camelCaseToUnderscoreUpperCase(String camel){
		return new StringBuilder(camel.substring(0,1))
			 	.append(camel.substring(1).replaceAll("\\B([A-Z])", "_$1"))
			 	.toString().toUpperCase();
	}
	
	public static String getGetMethodForField(String fieldName, TypeMirror type){
		if(TypeKind.BOOLEAN.equals(type.getKind()))
			return getXMethodForField(fieldName, "is");
		if(TypeKind.DECLARED.equals(type.getKind()) && Boolean.class.getName().equals(type.toString()))
			return getXMethodForField(fieldName, "is");
		
		return getXMethodForField(fieldName, "get");
	}
	
	public static String getSetMethodForField(String fieldName){
		return getXMethodForField(fieldName, "set");
	}
	
	public static String getIsModifiedMethodForField(String fieldName){
		return getXMethodForField(fieldName, "is") + "Modified";
	}
	
	public static String getPropertyAccessorMethodForField(String fieldName){
		return getXMethodForField(fieldName, "get") + "PropertyAccessor";
	}
	

	private static String getXMethodForField(String fieldName, String prefix){
		return new StringBuilder(prefix)
				.append(fieldName.substring(0,1).toUpperCase())
				.append(fieldName.substring(1))
				.toString();
	}
	
	/**
	 * Instantiates a new collection.
	 * 
	 * @param decl
	 */
	public static Class<?> isCollection(DeclaredType decl){
		Types typeUtils = dtoAnnotationProcessor.getProcessingEnvironment().getTypeUtils();
		
		boolean collectionFound = false;
		
		Collection<TypeMirror> typesToCheck = new HashSet<TypeMirror>();
		typesToCheck.add(decl);
		typesToCheck.addAll(typeUtils.directSupertypes(decl));
		
		/* search for specific type */
		for(TypeMirror superType : typesToCheck){
			if(! (superType instanceof DeclaredType))
				continue;
			
			TypeElement element = (TypeElement) ((DeclaredType)superType).asElement();
			if(List.class.getName().equals(element.getQualifiedName().toString()))
				return List.class;
			if(Set.class.getName().equals(element.getQualifiedName().toString()))
				return Set.class;
			if(Collection.class.getName().equals(element.getQualifiedName().toString()))
				collectionFound = true;
		}

		if(collectionFound)
			return Collection.class;
		
		return null;
	}
	
	public static Class<?> isMap(DeclaredType decl){
		Types typeUtils = dtoAnnotationProcessor.getProcessingEnvironment().getTypeUtils();
		
		Collection<TypeMirror> typesToCheck = new HashSet<TypeMirror>();
		typesToCheck.add(decl);
		typesToCheck.addAll(typeUtils.directSupertypes(decl));
		
		/* search for specific type */
		for(TypeMirror superType : typesToCheck){
			if(! (superType instanceof DeclaredType))
				continue;
			
			TypeElement element = (TypeElement) ((DeclaredType)superType).asElement();
			
			if(HashMap.class.getName().equals(element.getQualifiedName().toString()))
				return HashMap.class;
			if(Map.class.getName().equals(element.getQualifiedName().toString()))
				return Map.class;
		}

		return null;
	}
	
	public static boolean isSet(DeclaredType decl) {
		return Set.class.equals(isCollection(decl)); 
	}
	
	public static boolean isList(DeclaredType decl) {
		return List.class.equals(isCollection(decl)); 
	}
	
	@SuppressWarnings("unchecked")
	public static Collection instantiateCollection(DeclaredType decl){
		return instantiateCollection(isCollection(decl));
	}

	@SuppressWarnings("unchecked")
	public static Collection instantiateCollection(Class<?> colClass) {
		if(List.class.equals(colClass))
			return new ArrayList();
		if(Set.class.equals(colClass))
			return new HashSet();
		if(Collection.class.equals(colClass))
			return new HashSet();
		
		return null;
	}

	public static boolean isPoso(DeclaredType typeDecl){
		return null != typeDecl.asElement().getAnnotation(GenerateDto.class);
	}
	
	/**
	 * A Poso has the {@link GenerateDto} annotation and is a class.
	 *  
	 * @param typeDecl
	 */
	public static boolean isPosoClass(DeclaredType typeDecl){
		return null != typeDecl.asElement().getAnnotation(GenerateDto.class) && typeDecl.asElement().getKind().equals(ElementKind.CLASS);
	}
	
	/**
	 * A Poso has the {@link GenerateDto} annotation and is an interface.
	 *  
	 * @param typeDecl
	 */
	public static boolean isPosoInterface(DeclaredType typeDecl){
		return null != typeDecl.asElement().getAnnotation(GenerateDto.class) && typeDecl.asElement().getKind().equals(ElementKind.INTERFACE);
	}
	
	/**
	 * A PosoEnum has the {@link GenerateDto} annotation and is an enum.
	 *  
	 * @param typeDecl
	 */
	public static boolean isPosoEnum(DeclaredType typeDecl){
		return null != typeDecl.asElement().getAnnotation(GenerateDto.class) && typeDecl.asElement().getKind().equals(ElementKind.ENUM);
	}

	/**
	 * Returns the name of a type including parameters such as Set&lt;String&gt; 
	 */
	public static String getSimpleTypeName(DeclaredType typeDecl){
		String name = typeDecl.toString();

		/* do we have type parameters */
		if(typeDecl.getTypeArguments().isEmpty())
			return name.substring(name.lastIndexOf(".") + 1);
		
		/* name until type parameters */
		String nameUntilTypeParameters = name.substring(0, name.indexOf("<"));
		nameUntilTypeParameters = nameUntilTypeParameters.substring(nameUntilTypeParameters.lastIndexOf(".") + 1);
		
		return nameUntilTypeParameters + "<" + getTypeArguments(typeDecl) + ">";
	}
	
	public static String getTypeArguments(DeclaredType typeDecl){
		/* do we have type parameters */
		if(typeDecl.getTypeArguments().isEmpty())
			return "";
		
		
		/* get name of type parameters */
		StringBuilder nameOfTypeParameters = new StringBuilder();
		
		boolean first = true;
		for(TypeMirror arg : typeDecl.getTypeArguments()){
			if(first)
				first = false;
			else
				nameOfTypeParameters.append(", ");
			
			if(arg instanceof DeclaredType)
				nameOfTypeParameters.append(getSimpleTypeName((DeclaredType) arg));
			else
				nameOfTypeParameters.append(arg.toString());
		}
		
		return nameOfTypeParameters.toString();
	}

	public static String getQualifiedNameWithoutTypeArguments(
			DeclaredType typeDecl) {
			String name = typeDecl.toString();

			/* do we have type parameters */
			if(typeDecl.getTypeArguments().isEmpty())
				return name;
			
			return name.substring(0, name.indexOf("<"));
	}
	
	public static String getQualifiedNameWithoutTypeArguments(String name) {
			/* do we have type parameters */
			if(! name.contains("<"))
				return name;
			
			return name.substring(0, name.indexOf("<"));
	}

	public static String getSimpleNameWithoutTypeArguments(
			DeclaredType typeDecl) {
		return typeDecl.asElement().getSimpleName().toString();
	}


	public static boolean isPosoCollection(DeclaredType type) {
		/* assert that we have exactly one type argument */
		if(1 != type.getTypeArguments().size())
			return false;
		
		TypeMirror arg = type.getTypeArguments().get(0);
		if(arg instanceof DeclaredType && SourceFileGenerationUtils.isPoso((DeclaredType) arg))
			return true;
			
		return false;
	}


	public static PosoAnalizer getPosoInCollection(DeclaredType type) {
		DeclaredType arg = (DeclaredType) type.getTypeArguments().get(0);

		return dtoAnnotationProcessor.getPosoAnalizerFor(arg.asElement());
	}


	public static PosoAnalizer getPoso(DeclaredType type) {
		return dtoAnnotationProcessor.getPosoAnalizerFor(type.asElement());
	}
	
	public static String unproxySimpleType(String type){
		if("int".equals(type))
			return "Integer";
		if("boolean".equals(type))
			return "Boolean";
		if("long".equals(type))
			return "Long";
		if("double".equals(type))
			return "Double";
		if("byte".equals(type))
			return "Byte";
		
		return type.toString();
	}


	public static boolean isBooleanType(TypeMirror type) {
		if(TypeKind.BOOLEAN.equals(type.getKind()))
			return true;
		if(TypeKind.DECLARED.equals(type.getKind()) && Boolean.class.getName().equals(type.toString()))
			return true;
		
		return false;
	}


	public static String getNullValueFor(String type) {
		if("int".equals(type))
			return "0";
		if("boolean".equals(type))
			return "false";
		if("long".equals(type))
			return "0";
		if("double".equals(type))
			return "0";
		if("byte".equals(type))
			return "'0'";
		
		return null;
	}




}