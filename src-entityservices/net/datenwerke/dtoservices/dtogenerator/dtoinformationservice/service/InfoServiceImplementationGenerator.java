package net.datenwerke.dtoservices.dtogenerator.dtoinformationservice.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

import com.google.inject.Inject;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoFieldDescriptor;
import net.datenwerke.dtoservices.dtogenerator.dtoinformationservice.DtoClientInfoServiceGeneratorFacilitator;
import net.datenwerke.dtoservices.dtogenerator.dtoservicegenerator.service.MainInterfaceGenerator;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtoinfo.DtoInformationService;
import net.datenwerke.gxtdto.client.dtoinfo.DtoMainInformationService;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

public class InfoServiceImplementationGenerator extends SourceFileGeneratorImpl{
	
	private static final String SUB_MODULE_LIST = "subModules";
	
	private DtoAnnotationProcessor dtoAnnotationProcessor;
	
	private Collection<PosoAnalizer> posos;
	private Collection<String> referenceAccu = new TreeSet<String>();
	
	private interface MethodBuilderCallback{
		void execute(MethodBuilder mb);
	}
	
	public InfoServiceImplementationGenerator(DtoAnnotationProcessor dtoAnnotationProcessor,  Collection<PosoAnalizer> posos) {
		super(dtoAnnotationProcessor);
		this.dtoAnnotationProcessor = dtoAnnotationProcessor;
		this.posos = posos;
	}

	@Override
	protected void addClassBody(StringBuilder sourceBuilder) {
		addVariableSection(sourceBuilder);
		addConstructorSection(sourceBuilder);
		
		addGetDtoId(sourceBuilder);
		addCreateInstance(sourceBuilder);
		addLookupPosoMapper(sourceBuilder);
		
		addIsProxyable(sourceBuilder);
		addIsAuthorityForDtoClassMethod(sourceBuilder);
		addIsAuthorityForDtoMethod(sourceBuilder);
		addIsAuthorityForDtoClassNameMethod(sourceBuilder);
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			addAttachSubModuleMethod(sourceBuilder);
	}

	private void addIsProxyable(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("isProxyableDto","boolean","Dto dto");

		/* call submodules ?*/
		addCallSubModuleByDto(method, false, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + "isProxyableDto" + "(dto);");
			}
		});
		
		for(PosoAnalizer poso : posos){
			if(poso.getDtoInformation().isProxyable()){
				method.beginOneLineBlock("if(dto instanceof " + poso.getDtoInformation().getFullyQualifiedClassName() + ")");
				method.addBodyLine("return true;");
			}
		}
		
		method.addBodyLine();
		method.addBodyLine("return false;");
		
		sourceBuilder.append(method).append("\n\n");
	}

	private void addAttachSubModuleMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(MainInterfaceGenerator.ADD_SUB_MODULE_METHOD, "void", DtoInformationService.class.getName() + " subModule");
		
		method.addBodyLine(SUB_MODULE_LIST + ".add(subModule);");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}


	private void addIsAuthorityForDtoClassMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder("isAuthorityForClass", "boolean", "Class<?> dtoType");
		
		/* null check */
		method.beginOneLineBlock("if(null == dtoType)");
		method.addBodyLine("return false;");
		method.addBodyLine();
		
		
		for(PosoAnalizer poso : posos){
			method.beginOneLineBlock("if(" + poso.getDtoInformation().getFullyQualifiedClassName() + ".class.equals(dtoType))");
			method.addBodyLine("return true;");
			if(poso.getDtoInformation().hasDecorator()){
				method.beginOneLineBlock("if(" + poso.getDtoInformation().getFullyQualifiedClassNameForDecorator() + ".class.equals(dtoType))");
				method.addBodyLine("return true;");
			}
		}

		method.addBodyLine("return false;");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	private void addIsAuthorityForDtoClassNameMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder("isAuthorityForClassName", "boolean", "String dtoClassName");
		
		/* null check */
		method.beginOneLineBlock("if(null == dtoClassName)");
		method.addBodyLine("return false;");
		method.addBodyLine();
		
		
		for(PosoAnalizer poso : posos){
			method.beginOneLineBlock("if(\"" + poso.getDtoInformation().getFullyQualifiedClassName() + "\".equals(dtoClassName))");
			method.addBodyLine("return true;");
			if(poso.getDtoInformation().hasDecorator()){
				method.beginOneLineBlock("if(\"" + poso.getDtoInformation().getFullyQualifiedClassNameForDecorator() + "\".equals(dtoClassName))");
				method.addBodyLine("return true;");
			}
		}

		method.addBodyLine("return false;");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	private void addIsAuthorityForDtoMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder("isAuthorityFor", "boolean", "Object dto");
		
		/* null check */
		method.beginOneLineBlock("if(null == dto)");
		method.addBodyLine("return false;");
		method.addBodyLine();
		
		method.addBodyLine("return " + "isAuthorityForClass" + "(dto.getClass());");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	private void addLookupPosoMapper(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("lookupPosoMapper", "Class<? extends Dto2PosoMapper>","Class<? extends RsDto> dtoClass");
		method.addOverride();
		
		referenceAccu.add(Dto2PosoMapper.class.getCanonicalName());
		referenceAccu.add(RsDto.class.getCanonicalName());
		
		for(PosoAnalizer poso : posos){
				
			method.beginOneLineBlock("if(" + poso.getDtoInformation().getFullyQualifiedClassName() + ".class.equals(dtoClass))");
			method.addBodyLine("return " + poso.getDtoInformation().getFullyQualifiedDto2PosoMapClassName() + ".class;");
				
			if(poso.getDtoInformation().hasDecorator()){
				method.beginOneLineBlock("if(" + poso.getDtoInformation().getFullyQualifiedClassNameForDecorator() + ".class.equals(dtoClass))");
				method.addBodyLine("return " + poso.getDtoInformation().getFullyQualifiedDto2PosoMapClassName() + ".class;");
			}
			
		}
		
		method.addBodyLine("throw new IllegalArgumentException(\"unrecognized dto: \" + dtoClass);");
		
		sourceBuilder.append(method).append("\n\n");
	}
	
	private void addCreateInstance(StringBuilder sourceBuilder) {
		MethodBuilder fromClassMethod = new MethodBuilder("createInstance","<X extends Dto> X","Class<X> dtoClass");
		MethodBuilder fromNameMethod = new MethodBuilder("createInstance","<X extends Dto> X","String dtoClassName");

		/* call submodules ?*/
		addCallSubModuleByDtoClass(fromClassMethod,true, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + "createInstance" + "(dtoClass);");
			}
		});
		
		addCallSubModuleByDtoClassName(fromNameMethod, true, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + "createInstance" + "(dtoClassName);");
			}
		});
		
		
		for(PosoAnalizer poso : posos){
			if(poso.getDtoInformation().isAbstractDto())
				continue;
			if(! poso.isPosoClass())
				continue;
			
			referenceAccu.add(poso.getDtoInformation().getFullyQualifiedClassName());
			
			if(poso.getDtoInformation().hasDecorator()){
				fromClassMethod.beginOneLineBlock("if(" + poso.getDtoInformation().getFullyQualifiedClassNameForDecorator() + ".class.equals(dtoClass))");
				fromClassMethod.addBodyLine("return (X) new " + poso.getDtoInformation().getFullyQualifiedClassNameForDecorator() + "();");
				
				fromNameMethod.beginOneLineBlock("if(\"" + poso.getDtoInformation().getFullyQualifiedClassNameForDecorator() + "\".equals(dtoClassName))");
				fromNameMethod.addBodyLine("return (X) new " + poso.getDtoInformation().getFullyQualifiedClassNameForDecorator() + "();");
			} else {
				fromClassMethod.beginOneLineBlock("if(" + poso.getDtoInformation().getFullyQualifiedClassName() + ".class.equals(dtoClass))");
				fromClassMethod.addBodyLine("return (X) new " + poso.getDtoInformation().getFullyQualifiedClassName() + "();");
				
				fromNameMethod.beginOneLineBlock("if(\"" + poso.getDtoInformation().getFullyQualifiedClassName() + "\".equals(dtoClassName))");
				fromNameMethod.addBodyLine("return (X) new " + poso.getDtoInformation().getFullyQualifiedClassName() + "();");
			}
		}
		
		referenceAccu.add(IllegalArgumentException.class.getName());
		fromClassMethod.addBodyLine("throw new IllegalArgumentException(\"unrecognized dto: \" + dtoClass);");
		fromNameMethod.addBodyLine("throw new IllegalArgumentException(\"unrecognized dto: \" + dtoClassName);");
		
		sourceBuilder.append(fromClassMethod).append("\n\n");
		sourceBuilder.append(fromNameMethod).append("\n\n");
	}

	private void addGetDtoId(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("getDtoId","Object","Dto dto");

		/* call submodules ?*/
		addCallSubModuleByDto(method,true, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + "getDtoId" + "(dto);");
			}
		});
		
		
		for(PosoAnalizer poso : posos){
			PosoFieldDescriptor idField = poso.getIdField();

			if(null == idField)
				continue;

			referenceAccu.add(poso.getDtoInformation().getFullyQualifiedClassName());
			
			method.beginOneLineBlock("if(dto instanceof " + poso.getDtoInformation().getFullyQualifiedClassName() + ")");
			method.addBodyLine("return ((" + poso.getDtoInformation().getFullyQualifiedClassName() + ") dto)." + idField.getGetMethodForDto() + "();");
		}
		
		referenceAccu.add(IllegalArgumentException.class.getName());
		method.addBodyLine("throw new IllegalArgumentException(\"unrecognized dto: \" + dto);");
		
		sourceBuilder.append(method).append("\n\n");
	}


	private void addConstructorSection(StringBuilder sourceBuilder) {
		sourceBuilder.append("\t@SuppressWarnings(\"unchecked\")\n")
		            .append("\t@Inject\n")
					.append("\tpublic " + getClassName() + "(\n");

		sourceBuilder.append("\t){\n");
		
		/* end constructor */
		sourceBuilder.append("\t}\n\n");
	}

	private void addVariableSection(StringBuilder sourceBuilder) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			sourceBuilder
			.append("\t@SuppressWarnings(\"unchecked\")\n")
			.append("\tprivate List<").append(DtoInformationService.class.getName()).append("> ").append(SUB_MODULE_LIST)
			.append(" = new ArrayList<").append(DtoInformationService.class.getName()).append(">()")
			.append(";\n\n")
			.append("\tprivate Map<Class<? extends ").append(RsDto.class.getCanonicalName()).append(">, Class<? extends ")
			.append(Dto2PosoMapper.class.getCanonicalName()).append(">> dto2PosoMapperByDtoLookup = new HashMap<>()")
			.append(";\n\n");
		}
	}
	
	private void addCallSubModuleByDto(MethodBuilder method,
			boolean throwException, MethodBuilderCallback methodBuilderCallback) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginBodyBlock("if(! " + "isAuthorityFor" + "(dto))");
			
			method.beginBodyBlock("for(" + DtoInformationService.class.getSimpleName() + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + "isAuthorityFor" + "(dto))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			if(throwException)
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			else
				method.addBodyLine("return false;");
			
			method.endBodyBlock();
			method.addBodyLine();
		}
	}
	
	
	private void addCallSubModuleByDtoClass(MethodBuilder method,
			boolean throwException, MethodBuilderCallback methodBuilderCallback) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginBodyBlock("if(! " + "isAuthorityForClass" + "(dtoClass))");
			
			method.beginBodyBlock("for(" + DtoInformationService.class.getSimpleName() + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + "isAuthorityForClass" + "(dtoClass))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			if(throwException)
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dtoClass.getName());");
			else
				method.addBodyLine("return false;");
			
			method.endBodyBlock();
			method.addBodyLine();
		}
	}
	
	private void addCallSubModuleByDtoClassName(MethodBuilder method,
			boolean throwException, MethodBuilderCallback methodBuilderCallback) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginBodyBlock("if(! " + "isAuthorityForClassName" + "(dtoClassName))");
			
			method.beginBodyBlock("for(" + DtoInformationService.class.getSimpleName() + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + "isAuthorityForClassName" + "(dtoClassName))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			if(throwException)
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dtoClassName);");
			else
				method.addBodyLine("return false;");
			
			method.endBodyBlock();
			method.addBodyLine();
		}
	}

	@Override
	protected String getExtendedClass() {
		return null;
	}

	@Override
	protected Collection<String> getImplementedInterfaces() {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			HashSet<String> col = new HashSet<String>();
			col.add(DtoInformationService.class.getSimpleName());
			col.add(DtoMainInformationService.class.getSimpleName());
			return col;
		}
			
		return Collections.singleton(DtoInformationService.class.getSimpleName());
	}

	@Override
	protected Collection<String> getReferencedClasses() {
		Collection<String> references = super.getReferencedClasses();
		
		references.add(Collection.class.getName());
		references.add(HASH_SET_LOCATION);
		references.add(ARRAY_LIST_LOCATION);
		
		references.add(MAP_LOCATION);
		references.add(HASH_MAP_LOCATION);
		
		references.add(LIST_LOCATION);
		references.add(ARRAY_LIST_LOCATION);
		
		references.add(DtoView.class.getName());
		
		references.add(Dto.class.getName());
		references.add(DtoInformationService.class.getName());
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			references.add(DtoMainInformationService.class.getName());
		
		references.add(Inject.class.getName());
		
		references.addAll(referenceAccu);
		
		return references;
	}

	@Override
	protected boolean isAbstract() {
		return false;
	}

	public String getClassName() {
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			return DtoClientInfoServiceGeneratorFacilitator.SERVICE_IMPLEMENTATION_NAME;
		return dtoAnnotationProcessor.getDtoServiceBaseName() + DtoClientInfoServiceGeneratorFacilitator.SERVICE_IMPLEMENTATION_NAME;
	}

	public String getPackageName() {
		return dtoAnnotationProcessor.getOptionDtoInfoServicePackage();
	}
}
