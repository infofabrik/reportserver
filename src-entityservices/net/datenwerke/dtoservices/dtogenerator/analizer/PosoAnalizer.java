package net.datenwerke.dtoservices.dtogenerator.analizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.persistence.Entity;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.comparators.ExposedClientMethodComparator;
import net.datenwerke.dtoservices.dtogenerator.analizer.comparators.PosoFieldDescriptorComparator;
import net.datenwerke.dtoservices.dtogenerator.analizer.comparators.TypeElementComparator;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;

/**
 * 
 *
 */
public class PosoAnalizer {

	private DtoAnnotationProcessor dtoAnnotationProcessor;
	
	private Collection<PosoFieldDescriptor> fields = new ArrayList<PosoFieldDescriptor>();
	
	/**
	 * contains fields that were declared here and inherited
	 */
	private Collection<PosoFieldDescriptor> allFields = new ArrayList<PosoFieldDescriptor>();

	private TypeElement poso;
	private TypeElement posoParent;
	
	private TreeSet<TypeElement> implementedPosos = new TreeSet<TypeElement>(new TypeElementComparator());
	private TreeSet<TypeElement> posoHeritage = new TreeSet<TypeElement>(new TypeElementComparator());
	
	private List<EnumAnalizer> enumConstants = new ArrayList<EnumAnalizer>();
	
	private TreeSet<ExposedClientMethod> exposedInterfaceMethods = new TreeSet<ExposedClientMethod>(new ExposedClientMethodComparator());
	private TreeSet<ExposedClientMethod> allExposedInterfaceMethods = new TreeSet<ExposedClientMethod>(new ExposedClientMethodComparator());
	
	private TreeSet<ExposedClientMethod> exposedGetMethods = new TreeSet<ExposedClientMethod>(new ExposedClientMethodComparator());
	private TreeSet<ExposedClientMethod> exposedSetMethods = new TreeSet<ExposedClientMethod>(new ExposedClientMethodComparator());
	
	private TreeSet<ExposedClientMethod> allExposedGetMethods = new TreeSet<ExposedClientMethod>(new ExposedClientMethodComparator());
	private TreeSet<ExposedClientMethod> allExposedSetMethods = new TreeSet<ExposedClientMethod>(new ExposedClientMethodComparator());
	
	
	/* information objects */
	DtoInformation dtoInfo;
	Poso2DtoInformation poso2DtoInfo;
	Dto2PosoInformation dto2PosoInfo;
	
	public PosoAnalizer(DtoAnnotationProcessor dtoAnnotationProcessor, Element poso) {
		super();
		this.dtoAnnotationProcessor = dtoAnnotationProcessor;
		this.poso = (TypeElement) poso;
		
		/* analize and store information objects */
		analizePoso();
		
		dtoInfo = new DtoInformation(this, dtoAnnotationProcessor);
		poso2DtoInfo = new Poso2DtoInformation(this, dtoAnnotationProcessor);
		dto2PosoInfo = new Dto2PosoInformation(this, dtoAnnotationProcessor);
	}
	
	public void analizePoso() {
		/* process declaration */
		processClassDefinition();
		
		Elements elementUtils = dtoAnnotationProcessor.getProcessingEnvironment().getElementUtils();
		
		/* find fields */
		if(isPosoClass()){
			/* loop over fields declared here */
			for(VariableElement field : ElementFilter.fieldsIn(poso.getEnclosedElements())){
				PosoFieldDescriptor fieldDescriptor = new PosoFieldDescriptor(dtoAnnotationProcessor, field, this);
				fields.add(fieldDescriptor);
				allFields.add(fieldDescriptor);
			}
			
			/* loop over inherited fields */
			for(TypeElement parent : posoHeritage){
				for(VariableElement field : ElementFilter.fieldsIn(elementUtils.getAllMembers(parent))){
					PosoFieldDescriptor fieldDescriptor = new PosoFieldDescriptor(dtoAnnotationProcessor, field, this);
					allFields.add(fieldDescriptor);
				}
			}
			
			/* loop over methods */
			for(ExecutableElement method : ElementFilter.methodsIn(poso.getEnclosedElements())){
				if(ExposedClientMethod.isExposedClientMethod(method)){
					ExposedClientMethod exposedMethod = new ExposedClientMethod(dtoAnnotationProcessor, method);
					
					if(exposedMethod.isGetMethod()){
						exposedGetMethods.add(exposedMethod);
						allExposedGetMethods.add(exposedMethod);
					} else if(exposedMethod.isSetMethod()){
						exposedSetMethods.add(exposedMethod);
						allExposedSetMethods.add(exposedMethod);
					}
				}
			}
			
			/* loop over inherited methods */
			if(! posoHeritage.isEmpty()){
				for(ExecutableElement method : ElementFilter.methodsIn(elementUtils.getAllMembers(posoHeritage.first()))){
					if(ExposedClientMethod.isExposedClientMethod(method)){
						ExposedClientMethod exposedMethod = new ExposedClientMethod(dtoAnnotationProcessor, method);
						
						if(exposedMethod.isGetMethod())
							allExposedGetMethods.add(exposedMethod);
						else if(exposedMethod.isSetMethod())
							allExposedSetMethods.add(exposedMethod);
					}
				}
			}
			
			/* validate get set methods */
			validateGetSetMethods();
		} else if(isPosoEnum()){
			/* loop over possible enum constants */
			for(Element element :poso.getEnclosedElements()){
				if(element.getKind().equals(ElementKind.ENUM_CONSTANT)){
					enumConstants.add(new EnumAnalizer(dtoAnnotationProcessor, element));
				}
			}
		} else if(isPosoInterface()){
			/* loop over methods */
			for(ExecutableElement method : ElementFilter.methodsIn(poso.getEnclosedElements())){
				if(ExposedClientMethod.isExposedClientMethod(method)){
					ExposedClientMethod exposedMethod = new ExposedClientMethod(dtoAnnotationProcessor, method);
					
					exposedInterfaceMethods.add(exposedMethod);
					allExposedInterfaceMethods.add(exposedMethod);
				}
			}
			
			/* loop over inherited methods */
			if(! posoHeritage.isEmpty()){
				for(ExecutableElement method : ElementFilter.methodsIn(elementUtils.getAllMembers(posoHeritage.first()))){
					if(ExposedClientMethod.isExposedClientMethod(method)){
						ExposedClientMethod exposedMethod = new ExposedClientMethod(dtoAnnotationProcessor, method);
						
						allExposedInterfaceMethods.add(exposedMethod);
					}
				}
			}
		}
		
		/* see if we found an id field and echo warning if not */
		if(null == getIdField() && isPosoClass())
			dtoAnnotationProcessor.warning("Poso Class " + getSimpleName() + " does not have a specified ID field");
	}
	
	private void validateGetSetMethods() {
		Set<ExposedClientMethod> invalidGetMethods = new TreeSet<ExposedClientMethod>(new ExposedClientMethodComparator());
		for(ExposedClientMethod getMethod : allExposedGetMethods){
			for(ExposedClientMethod setMethod : getAllExposedSetMethods())
				if(getMethod.getBaseName().equals(setMethod.getBaseName())){
					dtoAnnotationProcessor.warning("Poso: " + poso + " exposes both get and set methods for " + setMethod.getBaseName() + ". Get method will be discarded.");
					invalidGetMethods.add(getMethod);
				}
		}
		for(ExposedClientMethod getMethod : invalidGetMethods){
			allExposedGetMethods.remove(getMethod);
			exposedGetMethods.remove(getMethod);
		}
	}

	private void processClassDefinition() {
		searchPosoInHeritage(poso.getSuperclass());
		searchPosoInImplementedInterfaces((DeclaredType) poso.asType());
	}


	private void searchPosoInImplementedInterfaces(DeclaredType type) {
		TypeElement element = (TypeElement)type.asElement();
		for(TypeMirror iface : element.getInterfaces()){
			if(SourceFileGenerationUtils.isPosoInterface((DeclaredType) iface)){
				implementedPosos.add((TypeElement) ((DeclaredType)iface).asElement());
			}
		}
	}

	private void searchPosoInHeritage(TypeMirror superclass) {
		if(superclass.getKind().equals(TypeKind.DECLARED)){
			Element superElement = ((DeclaredType)superclass).asElement();
			if(SourceFileGenerationUtils.isPosoClass((DeclaredType)superclass)){
				/* set direct parent */
				if(null == posoParent)
					posoParent = (TypeElement) superElement;
				
				/* add to heritage */
				posoHeritage.add((TypeElement)superElement);
			}
				
			/* goto parent */
			if(superElement.getKind().equals(ElementKind.CLASS))
				searchPosoInHeritage(((TypeElement)superElement).getSuperclass());
		}
	}
	
	public boolean extendsPoso(){
		return null != posoParent;
	}

	public GenerateDto getGenerateDtoAnnotation(){
		GenerateDto geAnno = poso.getAnnotation(GenerateDto.class);
		if(null == geAnno)
			throw new IllegalArgumentException(poso.getSimpleName() + " is not annotated with " + GenerateDto.class.getSimpleName());

		return geAnno;
	}
	
	public AnnotationMirror getGenerateDtoAnnotationMirror(){
		for(AnnotationMirror mirror : poso.getAnnotationMirrors()){
			if(mirror.getAnnotationType().toString().equals(GenerateDto.class.getName()))
				return mirror;
		}
		
		throw new IllegalStateException("Could not find annotation mirror");
	}
	
	public PosoFieldDescriptor getIdField(){
		for(PosoFieldDescriptor field : allFields)
			if(field.isIdField())
				return field;
		
		return null;
	}

	public PosoFieldDescriptor getKeyField(){
		for(PosoFieldDescriptor field : allFields)
			if(field.isKeyField())
				return field;
		
		return null;
	}
	
	public PosoFieldDescriptor getDisplayTitleField() {
		for(PosoFieldDescriptor field : allFields)
			if(field.isDisplayTitleField())
				return field;
		
		return null;
	}
	
	public Collection<PosoFieldDescriptor> getFields(){
		return fields;
	}
	
	/**
	 * Includes inherited fields
	 */
	public Collection<PosoFieldDescriptor> getAllFields(){
		return allFields;
	}
	
	public List<DtoField> getDtoFieldObejcts(){
		List<DtoField> list = new ArrayList<DtoField>();
		
		HashSet<String> processed = new HashSet<String>();
		for(PosoFieldDescriptor field : getExposedFields()){
			DtoField dtoField = new DtoField(field.getType(), field.getName(), field.getDtoView(), field.getFieldModifiedIndicator(), field.getFieldPropertyAccessor(), field.getGetMethodForDto(), field.getSetMethodForDto(), field.getIsPropertyModifiedMethodForDto(), field.getPropertyAccessorMethodForDto(), field.getVisibility());
			if(field.isIdField())
				dtoField.setIsIdField(true);
			
			list.add(dtoField);
				
			processed.add(field.getName());
		}
		
		for(ExposedClientMethod method : getExposedSetMethods()){
			if(processed.contains(method.getFieldName()))
				continue;
			list.add(new DtoField(method.getReturnType(), method.getFieldName(), method.getDtoView(), method.getFieldModifiedIndicator(), method.getFieldPropertyAccessor(), method.getGetMethodForDto(), method.getSetMethodForDto(), method.getIsPropertyModifiedMethodForDto(), method.getPropertyAccessorMethodForDto(), "private"));
			processed.add(method.getFieldName());
		}
		for(ExposedClientMethod method : getExposedGetMethods()){
			if(processed.contains(method.getFieldName()))
				continue;
			list.add(new DtoField(method.getReturnType(), method.getFieldName(), method.getDtoView(),method.getFieldModifiedIndicator(), method.getFieldPropertyAccessor(), method.getGetMethodForDto(), method.getSetMethodForDto(), method.getIsPropertyModifiedMethodForDto(), method.getPropertyAccessorMethodForDto(), "private"));
			processed.add(method.getFieldName());
		}

		for(PosoAnalizer iPoso : getImplementedPosos()){
			for(ExposedClientMethod method : iPoso.getExposedInterfaceMethods()){
				if(method.isGetMethod()){
					if(processed.contains(method.getFieldName()))
						continue;
					list.add(new DtoField(method.getReturnType(), method.getFieldName(), method.getDtoView(), method.getFieldModifiedIndicator(), method.getFieldPropertyAccessor(), method.getGetMethodForDto(), method.getSetMethodForDto(), method.getIsPropertyModifiedMethodForDto(), method.getPropertyAccessorMethodForDto(), "private"));
					processed.add(method.getFieldName());
				}
			}
		}
		
		list.addAll(getAdditionalFields());
		
		return list;
	}
	
	public Collection<DtoField> getAdditionalFields(){
		Collection<DtoField> additionalFields = new ArrayList<DtoField>();
		
		AnnotationMirror gdAnno = getGenerateDtoAnnotationMirror();
		for(ExecutableElement key : gdAnno.getElementValues().keySet()){
			if(key.toString().equals("additionalFields()")){
				AnnotationValue value = gdAnno.getElementValues().get(key);
				Object postProcessors = value.getValue();
				
				if(postProcessors instanceof Collection){
					for(AnnotationValue attr : (Collection<AnnotationValue>) postProcessors){
						if(((AnnotationValue)attr.getValue()).getValue() instanceof AnnotationMirror){
							AnnotationMirror mirror = (AnnotationMirror) ((AnnotationValue)attr.getValue()).getValue();
							
							String name = null;
							DeclaredType type = null;
							String visibility = "private";
							List<DeclaredType> genericTypes = new ArrayList<DeclaredType>();
							for(ExecutableElement annoKey : mirror.getElementValues().keySet()){
								if(annoKey.toString().equals("name()")){
									AnnotationValue nameValue = mirror.getElementValues().get(annoKey);
									name = (String) nameValue.getValue();
								} else if(annoKey.toString().equals("type()")){
									AnnotationValue typeValue = mirror.getElementValues().get(annoKey);
									type = (DeclaredType) typeValue.getValue();
								} else if(annoKey.toString().equals("visibility()")){
									AnnotationValue visibilityValue = mirror.getElementValues().get(annoKey);
									if(null != visibilityValue.getValue()){
										String s = String.valueOf(visibilityValue.getValue());
												
										if("PROTECTED".equals(s)){
											visibility = "protected";
										}else if("PACKAGE_PRIVATE".equals(s)){
											visibility = "";
										}else if("PUBLIC".equals(s)){
											visibility = "public";
										}
									}
									
								} else if(annoKey.toString().equals("generics()")){
									Elements elementUtils = dtoAnnotationProcessor.getProcessingEnvironment().getElementUtils();
									
									AnnotationValue genericsValue = elementUtils.getElementValuesWithDefaults(mirror).get(annoKey);
									Object generics = genericsValue.getValue();
									
									if(generics instanceof Collection){
										for(AnnotationValue genericsAttr : (Collection<AnnotationValue>) generics){
											DeclaredType generic = (DeclaredType) genericsAttr.getValue();
											genericTypes.add(generic);
										}
									} 
								}
							}
							
							additionalFields.add(new DtoField(name, type, genericTypes,visibility));
						}
					}
				} 
			}
		}
		
		
		
		return additionalFields;
	}
	
	public Collection<PosoFieldDescriptor> getExposedFields(){
		Collection<PosoFieldDescriptor> exposedFields = new TreeSet<PosoFieldDescriptor>(new PosoFieldDescriptorComparator());
	
		for(PosoFieldDescriptor field : fields)
			if(field.isExposedToClient())
				exposedFields.add(field);
		
		return exposedFields;
	}
	
	public Collection<ExposedClientMethod> getExposedGetMethods(){
		return exposedGetMethods;
	}

	public Collection<ExposedClientMethod> getExposedSetMethods(){
		return exposedSetMethods;
	}
	
	
	public Collection<ExposedClientMethod> getExposedInterfaceMethods(){
		return exposedInterfaceMethods;
	}

	public Collection<ExposedClientMethod> getAllExposedInterfaceMethods(){
		return allExposedInterfaceMethods;
	}


	/**
	 * Includes exposed methods in superclasses.
	 */
	public Collection<ExposedClientMethod> getAllExposedGetMethods(){
		return allExposedGetMethods;
	}

	/**
	 * Includes exposed methods in superclasses.
	 */
	public Collection<ExposedClientMethod> getAllExposedSetMethods(){
		return allExposedSetMethods;
	}

	
	/**
	 * Includes inherited fields
	 */
	public Collection<PosoFieldDescriptor> getAllExposedFields(){
		Collection<PosoFieldDescriptor> exposedFields = new TreeSet<PosoFieldDescriptor>(new PosoFieldDescriptorComparator());
	
		for(PosoFieldDescriptor field : allFields)
			if(field.isExposedToClient())
				exposedFields.add(field);
		
		return exposedFields;
	}
	
	public TypeMirror getSuperclass(){
		return poso.getSuperclass();
	}

	/**
	 * Returns the poso's name.
	 */
	public String getSimpleName() {
		return poso.getSimpleName().toString();
	}

	/**
	 * Returns the poso's fully quallified name.
	 */
	public String getFullyQualifiedClassName() {
		return poso.getQualifiedName().toString();
	}
	
	public String getPackage(){
		Elements utils = dtoAnnotationProcessor.getProcessingEnvironment().getElementUtils();
		return utils.getPackageOf(poso).toString(); 
	}
	
	/**
	 * Provides access to information on a dto for this poso
	 */
	public DtoInformation getDtoInformation(){
		return dtoInfo;
	}
	
	/**
	 * Provides access to information on a dto generator for this poso
	 */
	public Poso2DtoInformation getPoso2DtoInformation(){
		return poso2DtoInfo;
	}
	
	public Dto2PosoInformation getDto2PosoInformation(){
		return dto2PosoInfo;
	}

	public Element getParentPoso() {
		return posoParent;
	}
	
	public boolean isPosoClass(){
		return poso.getKind().equals(ElementKind.CLASS);
	}
	
	public boolean isPosoInterface(){
		return poso.getKind().equals(ElementKind.INTERFACE);
	}
	
	public boolean isPosoEnum(){
		return poso.getKind().equals(ElementKind.ENUM);
	}
	
	public List<EnumAnalizer> getEnumConstants(){
		return enumConstants;
	}

	public boolean isAbstract() {
		return poso.getModifiers().contains(Modifier.ABSTRACT);
	}

	public boolean isJpaEntity() {
		return null != poso.getAnnotation(Entity.class);
	}

	public boolean hasIdFieldInHeritage() {
		for(TypeElement parent : posoHeritage)
			if(null != dtoAnnotationProcessor.getPosoAnalizerFor(parent).getIdField())
				return true;
		return false;
	}

	public boolean hasKeyFieldInHeritage() {
		for(TypeElement parent : posoHeritage)
			if(null != dtoAnnotationProcessor.getPosoAnalizerFor(parent).getKeyField())
				return true;
		return false;
	}

	public Collection<PosoAnalizer> getImplementedPosos() {
		List<PosoAnalizer> ifaces = new ArrayList<PosoAnalizer>();
		
		for(TypeElement element : implementedPosos)
			ifaces.add(dtoAnnotationProcessor.getPosoAnalizerFor(element));
		
		return ifaces;
	}

	public ExecutableElement getMethod(String methodName) {
		List<TypeElement> elements = new ArrayList<TypeElement>();
		elements.add(poso);
		elements.addAll(posoHeritage);
		for(TypeElement element : elements){
			for(ExecutableElement method : ElementFilter.methodsIn(element.getEnclosedElements())){
				if(method.getSimpleName().toString().equals(methodName))
					return method;
			}
		}
		return null;
	}

	


}
