package net.datenwerke.dtoservices.dtogenerator.dtoservicegenerator.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dtoservicegenerator.DTOServiceGeneratorFacilitator;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.fto.FtoSupervisor;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoCreationHelper;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class ImplementationGenerator extends SourceFileGeneratorImpl {

	private static final String DTO_GENERATOR_BY_POSO_LOOKUP_NAME = "dtoGeneratorByPosoLookup";
	private static final String DTO_2_POSO_GENERATOR_LOOKUP_NAME = "dto2PosoGeneratorLookup";
	
	private static final String DTO_CLASS_SET = "dtoClassLookup";
	private static final String POSO_CLASS_SET = "posoClassLookup";
	
	private static final String SUB_MODULE_LIST = "subModules";
	
	private DtoAnnotationProcessor dtoAnnotationProcessor;
	
	private Collection<PosoAnalizer> posos;
	
	private interface MethodBuilderCallback{
		void execute(MethodBuilder mb);
	}
	
	public ImplementationGenerator(DtoAnnotationProcessor dtoAnnotationProcessor,  Collection<PosoAnalizer> posos) {
		super(dtoAnnotationProcessor);
		this.dtoAnnotationProcessor = dtoAnnotationProcessor;
		this.posos = posos;
	}

	@Override
	protected void addClassBody(StringBuilder sourceBuilder) {
		addVariableSection(sourceBuilder);
		addConstructorSection(sourceBuilder);
		
		addInstantiateDtoMethod(sourceBuilder);
		addInstantiateDtoFromClassMethod(sourceBuilder);
		addCreateDtoMethod(sourceBuilder);
		addCreateBasicDtoMethod(sourceBuilder);
		addCreateListDtoMethod(sourceBuilder);
		addCreateFullDtoMethod(sourceBuilder);
		
		addLoadPoso(sourceBuilder);
		addCreatePoso(sourceBuilder);
		addCreateUnmanagedPoso(sourceBuilder);
		addInstantiatePoso(sourceBuilder);
		addMergePoso(sourceBuilder);
		addMergeUnmanagedPoso(sourceBuilder);
		addLoadAndMergePoso(sourceBuilder);
		addIsAuthorityForPosoClassMethod(sourceBuilder);
		addIsAuthorityForDtoClassMethod(sourceBuilder);
		addIsAuthorityForPosoMethod(sourceBuilder);
		addIsAuthorityForDtoMethod(sourceBuilder);
		
		addCreateFto(sourceBuilder);
		
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			addGetCreationHelper(sourceBuilder);
			addSetCreationHelper(sourceBuilder);
			addAttachSubModuleMethod(sourceBuilder);
		}
		addGetPosoFromMapper(sourceBuilder);
	}



	private void addCreateFto(StringBuilder sourceBuilder) {
		
		MethodBuilder method;
		
//		public void createFto(Object poso, DtoView here, DtoView referenced);
		method = new MethodBuilder("createFto", "String[]", "Object poso", "DtoView here", "DtoView referenced");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.addBodyLine("Dto dto = (Dto)createDto(poso, here, referenced);");
		method.addBodyLine("return dto2Fto(dto);");
		sourceBuilder.append(method).append("\n");
		
//		public void createFto(Object poso);
		method = new MethodBuilder("createFto", "String[]", "Object poso");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.addBodyLine("Dto dto = (Dto)createDto(poso);");
		method.addBodyLine("return dto2Fto(dto);");
		sourceBuilder.append(method).append("\n");
		
//		public void createListFto(Object poso);
		method = new MethodBuilder("createListFto", "String[]", "Object poso");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.addBodyLine("Dto dto = (Dto)createListDto(poso);");
		method.addBodyLine("return dto2Fto(dto);");
		sourceBuilder.append(method).append("\n");
		
//		public void createDtoFullAccess(Object poso);
		method = new MethodBuilder("createFtoFullAccess", "String[]", "Object poso");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.addBodyLine("Dto dto = (Dto)createDtoFullAccess(poso);");
		method.addBodyLine("return dto2Fto(dto);");
		sourceBuilder.append(method).append("\n");
		
		//		private void dto2Fto(RsDto dto);
		method = new MethodBuilder("dto2Fto", "String[]", "Dto dto");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.addBodyLine("List<PropertyAccessor> propertyAccessors = dto.getPropertyAccessors();");
		method.addBodyLine("ArrayList<String> res = new ArrayList<String>();");
		method.addBodyLine("for(PropertyAccessor pa : propertyAccessors){");
		method.addBodyLine("	try {");
		method.addBodyLine("		// make sure this property can be recreated from string");
		method.addBodyLine("		if(dto instanceof FtoSupervisor && ((FtoSupervisor)dto).consumes(pa))");
		method.addBodyLine("		    res.add(((FtoSupervisor)dto).adaptFtoGeneration(pa, dto));");
		method.addBodyLine("	    else {");
		method.addBodyLine("	        Object prop = pa.getValue(dto);");
		method.addBodyLine("	    	if(null == prop)");
		method.addBodyLine("	    	    res.add(null);");
		method.addBodyLine("	    	else {");
		method.addBodyLine("	    	    if(!(prop instanceof String))");
		method.addBodyLine("		        	prop.getClass().getMethod(\"valueOf\", String.class);");
		method.addBodyLine("		        res.add(String.valueOf(prop));");
		method.addBodyLine("	        }");
		method.addBodyLine("	    }");
		method.addBodyLine("	} catch (Exception e) {");
		method.addBodyLine("		// never mind");
		method.addBodyLine("		res.add(\"_fto_ignore_this:\");");
		method.addBodyLine("	}");
		method.addBodyLine("}");
		method.addBodyLine("res.add(\"_fto_dto_type:\" + dto.getClass().getName());");
		method.addBodyLine("if(dto instanceof IdedDto)");
		method.addBodyLine("    res.add(\"_fto_dto_id:\" + String.valueOf(((IdedDto)dto).getDtoId()));");
		method.addBodyLine("return res.toArray(new String[0]);");
		
		sourceBuilder.append(method).append("\n");
	}
	

	
	
	private void addConstructorSection(StringBuilder sourceBuilder) {
		sourceBuilder.append("\t@SuppressWarnings(\"unchecked\")\n")
		            .append("\t@Inject\n")
					.append("\tpublic ").append(getClassName()).append("(\n");

		/* helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			sourceBuilder.append("\t\tProvider<").append(DtoCreationHelper.class.getSimpleName()).append("> creationHelperProvider\n\n");
		else
			sourceBuilder.append("\t" + DtoService.class.getSimpleName() + " dtoService\n\n");
		
		sourceBuilder.append("\t\t, Injector injector\n\n");
		
		sourceBuilder.append("\n\t){\n");
		
		/* set variables */
		
		/* helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			sourceBuilder.append("\t\tthis.creationHelperProvider = creationHelperProvider;\n\n");
		else
			sourceBuilder.append("\t\tthis.dtoService = dtoService;\n\n");

		/* poso 2 dto generator lookup */
		sourceBuilder.append("\t\t" + DTO_GENERATOR_BY_POSO_LOOKUP_NAME + " = new HashMap<Class<?>, " + Poso2DtoGenerator.class.getSimpleName() + ">();\n\n");
		
		for(PosoAnalizer poso : posos){
			if(! poso.getPoso2DtoInformation().generateGenerator())
				continue;
			
			sourceBuilder.append("\t\t").append(DTO_GENERATOR_BY_POSO_LOOKUP_NAME).append(".put(")
						 .append(poso.getFullyQualifiedClassName())
						 	.append(".class, injector.getInstance(")
						 .append(poso.getPoso2DtoInformation().getFullyQualifiedClassName()).append(".class)")
						 .append(");\n");
		}
		
		/* dto 2 poso generator lookup */
		sourceBuilder.append("\n\n")
					.append("\t\t" + DTO_2_POSO_GENERATOR_LOOKUP_NAME + " = new HashMap<Class<?>, " + Dto2PosoGenerator.class.getSimpleName() + ">();\n\n");
		
		for(PosoAnalizer poso : posos){
			if(! poso.getDto2PosoInformation().generateGenerator())
				continue;
			
			sourceBuilder.append("\t\t").append(DTO_2_POSO_GENERATOR_LOOKUP_NAME).append(".put(")
						 .append(poso.getDtoInformation().getFullyQualifiedClassName())
						 	.append(".class, injector.getInstance(")
					 	.append(poso.getDto2PosoInformation().getFullyQualifiedClassName()).append(".class)")
						 .append(");\n");
		}
		
		/* dto set lookup*/ 
		sourceBuilder.append("\t\t//Dto lookup\n");
		sourceBuilder.append("\n\n")
				.append("\t\t" + DTO_CLASS_SET + " = new HashSet<Class<?>>();\n\n");
		
		for(PosoAnalizer poso : posos){
			sourceBuilder.append("\t\t").append(DTO_CLASS_SET).append(".add(")
						 .append(poso.getDtoInformation().getFullyQualifiedClassName()).append(".class")
						 .append(");\n");
			if(poso.getDtoInformation().hasDecorator()){
				sourceBuilder.append("\t\t").append(DTO_CLASS_SET).append(".add(")
					 .append(poso.getDtoInformation().getFullyQualifiedClassNameForDecorator()).append(".class")
					 .append(");\n");
			}
		}
		
		/* poso set lookup*/ 
		sourceBuilder.append("\t\t//POSO lookup\n");
		sourceBuilder.append("\n\n")
				.append("\t\t" + POSO_CLASS_SET + " = new HashSet<Class<?>>();\n\n");
		
		for(PosoAnalizer poso : posos){
			sourceBuilder.append("\t\t").append(POSO_CLASS_SET).append(".add(")
						 .append(poso.getFullyQualifiedClassName()).append(".class")
						 .append(");\n");
		}
		
		/* end constructor */
		sourceBuilder.append("\t}\n\n");
	}

	private void addVariableSection(StringBuilder sourceBuilder) {
		/* initialize variable and begin static block */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			sourceBuilder
			.append("\t@SuppressWarnings(\"unchecked\")\n")
			.append("\tprivate List<").append(DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME).append("> ").append(SUB_MODULE_LIST)
			.append(" = new ArrayList<").append(DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME).append(">()")
			.append(";\n\n");
			
			sourceBuilder
			.append("\tprivate ThreadLocal<").append(DtoCreationHelper.class.getSimpleName()).append("> creationHelper = new ThreadLocal<").append(DtoCreationHelper.class.getSimpleName()).append(">();\n\n");
			sourceBuilder
			.append("\tfinal private Provider<").append(DtoCreationHelper.class.getSimpleName()).append("> creationHelperProvider;\n\n");
		} else {
			sourceBuilder.append("\tprivate final " + DtoService.class.getSimpleName() + " dtoService;\n");
		}
		
		sourceBuilder
			.append("\t@SuppressWarnings(\"unchecked\")\n")
			.append("\tprivate Map<Class<?>, " + Poso2DtoGenerator.class.getSimpleName() + "> ").append(DTO_GENERATOR_BY_POSO_LOOKUP_NAME).append(";\n\n");
		
		sourceBuilder
			.append("\t@SuppressWarnings(\"unchecked\")\n")
			.append("\tprivate Map<Class<?>, " + Dto2PosoGenerator.class.getSimpleName() + "> ").append(DTO_2_POSO_GENERATOR_LOOKUP_NAME).append(";\n\n");
		
		sourceBuilder
		.append("\t@SuppressWarnings(\"unchecked\")\n")
		.append("\tprivate Set<Class<?>> ").append(DTO_CLASS_SET).append(";\n\n");
		
		sourceBuilder
		.append("\t@SuppressWarnings(\"unchecked\")\n")
		.append("\tprivate Set<Class<?>> ").append(POSO_CLASS_SET).append(";\n\n");
	}
	
	private void addLoadAndMergePoso(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("loadAndMergePoso", "Object", "Object dto");
		method.addMethodCommentLine("Loads a poso and merges data given a Dto.");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.setThrows(ExpectedException.class.getName());

		method.addBodyLine(Dto2PosoGenerator.class.getName() + " generator = null;");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginOneLineBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		/* null check */
		method.beginOneLineBlock("if(null == dto)");
		method.addBodyLine("return null;");
		method.addBodyLine();
		
		/* call submodules ?*/
		addCallSubModuleByDto(method, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + "loadAndMergePoso" + "(dto);");
			}
		});
		
		method.addBodyComment("validate object");
		method.addBodyLine("Class<?> dtoType = dto.getClass();");
		method.beginBodyBlock("while(! dto2PosoGeneratorLookup.containsKey(dtoType))");
			method.beginOneLineBlock("if(null == dtoType)");
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			method.addBodyLine("dtoType = dtoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyComment("get generator");
		method.addBodyLine("generator = " + DTO_2_POSO_GENERATOR_LOOKUP_NAME + ".get(dtoType);");
		method.addBodyLine("Object poso = generator.loadAndMergePoso(dto);");
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			method.addBodyLine("creationHelper.get().posoLoadedFor(dto, poso, generator);");		
		else
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoLoadedFor(dto, poso, generator);");

		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			method.addBodyLine("creationHelper.get().posoMergedFor(dto, poso, generator);");		
		else
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoMergedFor(dto, poso, generator);");

		method.addBodyLine("return poso;");
		
		
		
		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}

	private void addGetPosoFromMapper(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder(InterfaceGenerator.GET_POSO_FROM_MAPPER, "Class<?>", Dto2PosoMapper.class.getSimpleName() + " mapper");
		method.addMethodCommentLine("Resolves poso from mapper");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginOneLineBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		if(! dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyComment("use main service");
			method.addBodyLine("throw new IllegalStateException(\"use main service\");");
		} else {
			/* null check */
			method.beginOneLineBlock("if(null == mapper)");
			method.addBodyLine("return null;");
			method.addBodyLine();
			method.addBodyLine("return mapper.getClass().getAnnotation(CorrespondingPoso.class).value();");
		}
		
		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}

	private void addMergePoso(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("mergePoso", "void", "Object dto", "Object poso");
		method.addMethodCommentLine("Merges a Poso given a Dto.");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.setThrows(ExpectedException.class.getName());
		
		method.addBodyLine(Dto2PosoGenerator.class.getName() + " generator = null;");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginOneLineBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		/* null check */
		method.beginOneLineBlock("if(null == dto || null == poso)");
		method.addBodyLine("return;");
		method.addBodyLine();
		
		/* call submodules ?*/
		addCallSubModuleByDto(method, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("submodule." + "mergePoso" + "(dto, poso);");
				mb.addBodyLine("return;");
			}
		});
				
		method.addBodyComment("validate object");
		method.addBodyLine("Class<?> dtoType = dto.getClass();");
		method.beginBodyBlock("while(! dto2PosoGeneratorLookup.containsKey(dtoType))");
			method.beginOneLineBlock("if(null == dtoType)");
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			method.addBodyLine("dtoType = dtoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyComment("get generator");
		method.addBodyLine("generator = " + DTO_2_POSO_GENERATOR_LOOKUP_NAME + ".get(dtoType);");
		method.addBodyLine("generator.mergePoso(dto, poso);");
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			method.addBodyLine("creationHelper.get().posoMergedFor(dto, poso, generator);");		
		else
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoMergedFor(dto, poso, generator);");

		
		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}
	
	private void addMergeUnmanagedPoso(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("mergeUnmanagedPoso", "void", "Object dto", "Object poso");
		method.addMethodCommentLine("Merges an unmanaged Poso given a Dto.");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.setThrows(ExpectedException.class.getName());
		
		method.addBodyLine(Dto2PosoGenerator.class.getName() + " generator = null;");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginBodyBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
				method.addBodyLine("creationHelper.get().setModeUnmanaged(true);");
			method.endBodyBlock();
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		/* null check */
		method.beginOneLineBlock("if(null == dto || null == poso)");
		method.addBodyLine("return;");
		method.addBodyLine();
				
		/* call submodules ?*/
		addCallSubModuleByDto(method, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("submodule." + "mergeUnmanagedPoso" + "(dto, poso);");
				mb.addBodyLine("return;");
			}
		});
		
		
		method.addBodyComment("validate object");
		method.addBodyLine("Class<?> dtoType = dto.getClass();");
		method.beginBodyBlock("while(! dto2PosoGeneratorLookup.containsKey(dtoType))");
			method.beginOneLineBlock("if(null == dtoType)");
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			method.addBodyLine("dtoType = dtoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyComment("get generator");
		method.addBodyLine("generator = " + DTO_2_POSO_GENERATOR_LOOKUP_NAME + ".get(dtoType);");
		method.addBodyLine("generator.mergeUnmanagedPoso(dto, poso);");
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			method.addBodyLine("creationHelper.get().posoMergedFor(dto, poso, generator);");		
		else
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoMergedFor(dto, poso, generator);");
		
		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}
	
	private void addInstantiatePoso(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("instantiatePoso", "Object", "Class<?> dtoType");
		method.addMethodCommentLine("Generates a Poso given a Dto's class (no properties are set).");
		method.addAnnotationString("SuppressWarnings", "unchecked");

		method.addBodyLine(Dto2PosoGenerator.class.getName() + " generator = null;");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginOneLineBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		/* null check */
		method.beginOneLineBlock("if(null == dtoType)");
		method.addBodyLine("return null;");
		method.addBodyLine();
		
		/* call submodules ?*/
		addCallSubModuleByDtoType(method, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + "instantiatePoso" + "(dtoType);");
			}
		});
		
		method.addBodyComment("validate object");
		method.addBodyLine("Class<?> tmpType = dtoType;");
		method.beginBodyBlock("while(! dto2PosoGeneratorLookup.containsKey(dtoType))");
			method.beginOneLineBlock("if(null == dtoType)");
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + tmpType.getName());");
			method.addBodyLine("dtoType = dtoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyComment("get generator");
		method.addBodyLine("generator = " + DTO_2_POSO_GENERATOR_LOOKUP_NAME + ".get(dtoType);");
		method.addBodyLine("Object poso = generator.instantiatePoso();");
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			method.addBodyLine("creationHelper.get().posoInstantiatedFor(poso, generator);");		
		else
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoInstantiatedFor(poso, generator);");
		
		method.addBodyLine("return poso;");
		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}

	private void addCreatePoso(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("createPoso", "Object", "Object dto");
		method.addMethodCommentLine("Generates a Poso given a Dto.");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.setThrows(ExpectedException.class.getName());

		method.addBodyLine(Dto2PosoGenerator.class.getName() + " generator = null;");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginOneLineBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		/* null check */
		method.beginOneLineBlock("if(null == dto)");
		method.addBodyLine("return null;");
		method.addBodyLine();
		
		/* call submodules ?*/
		addCallSubModuleByDto(method, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("Object poso = submodule." + "createPoso" + "(dto);");
				mb.addBodyLine("return poso;");
			}
		});
		
		method.addBodyComment("validate object");
		method.addBodyLine("Class<?> dtoType = dto.getClass();");
		method.beginBodyBlock("while(! dto2PosoGeneratorLookup.containsKey(dtoType))");
			method.beginOneLineBlock("if(null == dtoType)");
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			method.addBodyLine("dtoType = dtoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyComment("get generator");
		method.addBodyLine("generator = " + DTO_2_POSO_GENERATOR_LOOKUP_NAME + ".get(dtoType);");
		method.addBodyLine("Object poso = generator.createPoso(dto);");
		method.addBodyLine();

		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			method.addBodyLine("creationHelper.get().posoCreatedFor(dto, poso, generator);");		
		else
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoCreatedFor(dto, poso, generator);");
		
		method.addBodyLine();
		method.addBodyLine("return poso;");
		
		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}
	
	private void addCreateUnmanagedPoso(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("createUnmanagedPoso", "Object", "Object dto");
		method.addMethodCommentLine("Generates a Poso given a Dto.");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		method.setThrows(ExpectedException.class.getName());
		
		method.addBodyLine(Dto2PosoGenerator.class.getName() + " generator = null;");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginBodyBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
				method.addBodyLine("creationHelper.get().setModeUnmanaged(true);");
			method.endBodyBlock();
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		/* null check */
		method.beginOneLineBlock("if(null == dto)");
		method.addBodyLine("return null;");
		method.addBodyLine();
		
		/* call submodules ?*/
		addCallSubModuleByDto(method, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("Object poso = submodule." + "createUnmanagedPoso" + "(dto);");
				mb.addBodyLine("return poso;");
			}
		});
		
		method.addBodyComment("validate object");
		method.addBodyLine("Class<?> dtoType = dto.getClass();");
		method.beginBodyBlock("while(! dto2PosoGeneratorLookup.containsKey(dtoType))");
			method.beginOneLineBlock("if(null == dtoType)");
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			method.addBodyLine("dtoType = dtoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyComment("get generator");
		method.addBodyLine("generator = " + DTO_2_POSO_GENERATOR_LOOKUP_NAME + ".get(dtoType);");
		method.addBodyLine("Object poso = generator.createUnmanagedPoso(dto);");
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption()) {
			method.addBodyLine("creationHelper.get().posoCreatedFor(dto, poso, generator);");
			method.addBodyLine("creationHelper.get().unmanagedPosoCreatedFor(dto, poso, generator);");
		} else{
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoCreatedFor(dto, poso, generator);");
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().unmanagedPosoCreatedFor(dto, poso, generator);");
		}
		
		method.addBodyLine("return poso;");

		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}

	private void addHelperFinal(MethodBuilder method){
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginFinallyBlock();
			method.beginBodyBlock("if(initial)");
			method.beginTryBlock();
			method.addBodyLine("creationHelper.get().cleanup();");
			method.beginFinallyBlock();
			method.addBodyLine("creationHelper.set(null);");
			method.endBodyBlock();
			method.endBodyBlock();
			method.endBodyBlock();
		}
	}
	
	private void addLoadPoso(StringBuilder sourceBuilder) {
		MethodBuilder method = new MethodBuilder("loadPoso", "Object", "Object dto");
		method.addMethodCommentLine("Loads a Poso given a Dto.");
		method.addAnnotationString("SuppressWarnings", "unchecked");
		
		method.addBodyLine(Dto2PosoGenerator.class.getName() + " generator = null;"); 
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.addBodyLine("boolean initial = null == creationHelper.get();");
			method.beginOneLineBlock("if(initial)");
				method.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			method.addBodyLine();
			method.beginTryBlock();
		}
		
		/* null check */
		method.beginOneLineBlock("if(null == dto)");
		method.addBodyLine("return null;");
		method.addBodyLine();
				
		/* call submodules ?*/
		addCallSubModuleByDto(method, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + "loadPoso" + "(dto);");
			}
		});
		
		method.addBodyComment("validate object");
		method.addBodyLine("Class<?> dtoType = dto.getClass();");
		method.beginBodyBlock("while(! dto2PosoGeneratorLookup.containsKey(dtoType))");
			method.beginOneLineBlock("if(null == dtoType)");
				method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			method.addBodyLine("dtoType = dtoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyComment("get generator");
		method.addBodyLine("generator = " + DTO_2_POSO_GENERATOR_LOOKUP_NAME + ".get(dtoType);");
		method.addBodyLine("Object poso = generator.loadPoso(dto);");
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			method.addBodyLine("creationHelper.get().posoLoadedFor(dto, poso, generator);");		
		else
			method.addBodyLine("((DtoMainService)dtoService).getCreationHelper().posoLoadedFor(dto, poso, generator);");

		method.addBodyLine("return poso;");
		
		/* helper */
		addHelperFinal(method);
		
		sourceBuilder.append(method).append("\n");
	}
	
	private void addCreateBasicDtoMethod(StringBuilder sourceBuilder) {
		MethodBuilder createDto = new MethodBuilder(InterfaceGenerator.CREATE_BASIC_DTO_METHOD, "Object", "Object poso");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			createDto.addBodyLine("boolean initial = null == creationHelper.get();");
			createDto.beginOneLineBlock("if(initial)");
				createDto.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			createDto.addBodyLine();
			createDto.beginTryBlock();
		}
		
		createDto.addBodyLine("return " + InterfaceGenerator.CREATE_BASIC_DTO_METHOD + "(poso, DtoView.NORMAL, DtoView.MINIMAL);");
		
		/* helper */
		addHelperFinal(createDto);
		
		sourceBuilder.append(createDto).append("\n");
	}

	
	private void addCreateFullDtoMethod(StringBuilder sourceBuilder) {
		MethodBuilder createDto = new MethodBuilder(InterfaceGenerator.CREATE_FULL_DTO_METHOD, "Object", "Object poso");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			createDto.addBodyLine("boolean initial = null == creationHelper.get();");
			createDto.beginOneLineBlock("if(initial)");
				createDto.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			createDto.addBodyLine();
			createDto.beginTryBlock();
		}
		
		createDto.addBodyLine("return " + InterfaceGenerator.CREATE_BASIC_DTO_METHOD + "(poso, DtoView.ALL, DtoView.MINIMAL);");
		
		/* helper */
		addHelperFinal(createDto);
		
		sourceBuilder.append(createDto).append("\n");
	}
	
	private void addCreateListDtoMethod(StringBuilder sourceBuilder) {
		MethodBuilder createDto = new MethodBuilder(InterfaceGenerator.CREATE_LIST_DTO_METHOD, "Object", "Object poso");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			createDto.addBodyLine("boolean initial = null == creationHelper.get();");
			createDto.beginOneLineBlock("if(initial)");
				createDto.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			createDto.addBodyLine();
			createDto.beginTryBlock();
		}
		
		createDto.addBodyLine("return " + InterfaceGenerator.CREATE_BASIC_DTO_METHOD + "(poso, DtoView.LIST, DtoView.MINIMAL);");
		
		/* helper */
		addHelperFinal(createDto);
		
		sourceBuilder.append(createDto).append("\n");
	}

	private void addCreateDtoMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder createDto = new MethodBuilder(InterfaceGenerator.CREATE_BASIC_DTO_METHOD, "Object", "Object poso", "DtoView here", "DtoView referenced");
		createDto.addMethodCommentLine("Generates a DTO given a Poso.");
		createDto.addAnnotationString("SuppressWarnings", "unchecked");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			createDto.addBodyLine("boolean initial = null == creationHelper.get();");
			createDto.beginOneLineBlock("if(initial)");
				createDto.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			createDto.addBodyLine();
			
			/* add hibernate lazy loading */
			createDto.addBodyComment("unproxy hibernate object if necessary");
			createDto.beginOneLineBlock("if (poso instanceof HibernateProxy)");  
			createDto.addBodyLine("poso = ((HibernateProxy)poso).getHibernateLazyInitializer().getImplementation();");  
			
			createDto.addBodyLine();
			createDto.beginTryBlock();
		}
			
		/* null check */
		createDto.addBodyComment("return null if poso is null");
		createDto.beginOneLineBlock("if(null == poso)");
		createDto.addBodyLine("return null;");
		
		/* call submodules ?*/
		addCallSubModuleByPoso(createDto, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + InterfaceGenerator.CREATE_BASIC_DTO_METHOD + "(poso, here, referenced);");
			}
		});
		
		createDto.addBodyComment("validate object and find correct type");
		createDto.addBodyLine("Class<?> posoType = poso.getClass();");
		createDto.beginBodyBlock("while(! " + DTO_GENERATOR_BY_POSO_LOOKUP_NAME + ".containsKey(posoType))");
		createDto.beginOneLineBlock("if(null == posoType)");
		createDto.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Poso: \" + poso.getClass().getName());");
		createDto.addBodyLine("posoType = posoType.getSuperclass();");
		createDto.endBodyBlock();
		
		createDto.addBodyLine();

		createDto.addBodyComment("get generator");
		createDto.addBodyLine(Poso2DtoGenerator.class.getSimpleName() + " generator = " + DTO_GENERATOR_BY_POSO_LOOKUP_NAME + ".get(posoType);");
		createDto.addBodyLine("return generator.createDto(poso, here, referenced);");
		
		/* helper */
		addHelperFinal(createDto);
		
		sourceBuilder.append("\n").append(createDto).append("\n");
	}
	
	private void addInstantiateDtoMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder createDto = new MethodBuilder(InterfaceGenerator.INSTANTIATE_DTO_METHOD, "Object", "Object poso");
		createDto.addMethodCommentLine("Generates a Dto instance given a Poso.");
		createDto.addAnnotationString("SuppressWarnings", "unchecked");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			createDto.addBodyLine("boolean initial = null == creationHelper.get();");
			createDto.beginOneLineBlock("if(initial)");
				createDto.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			createDto.addBodyLine();
			createDto.beginTryBlock();
		}
			
		/* null check */
		createDto.addBodyComment("return null if poso is null");
		createDto.beginOneLineBlock("if(null == poso)");
		createDto.addBodyLine("return null;");
		
		/* call submodules ?*/
		addCallSubModuleByPoso(createDto, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + InterfaceGenerator.INSTANTIATE_DTO_METHOD + "(poso);");
			}
		});
		
		createDto.addBodyLine();
		createDto.addBodyComment("unproxy hibernate object if necessary");
		createDto.beginOneLineBlock("if (poso instanceof HibernateProxy)");  
		createDto.addBodyLine("poso = ((HibernateProxy)poso).getHibernateLazyInitializer().getImplementation();");  
		
		createDto.addBodyComment("validate object and find correct type");
		createDto.addBodyLine("Class<?> posoType = poso.getClass();");
		createDto.beginBodyBlock("while(! " + DTO_GENERATOR_BY_POSO_LOOKUP_NAME + ".containsKey(posoType))");
		createDto.beginOneLineBlock("if(null == posoType)");
		createDto.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Poso: \" + poso.getClass().getName());");
		createDto.addBodyLine("posoType = posoType.getSuperclass();");
		createDto.endBodyBlock();
		
		createDto.addBodyLine();

		createDto.addBodyComment("get generator");
		createDto.addBodyLine(Poso2DtoGenerator.class.getSimpleName() + " generator = " + DTO_GENERATOR_BY_POSO_LOOKUP_NAME + ".get(posoType);");
		createDto.addBodyLine("return generator.instantiateDto(poso);");
		
		/* helper */
		addHelperFinal(createDto);
		
		sourceBuilder.append("\n").append(createDto).append("\n");
	}
	
	private void addInstantiateDtoFromClassMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder createDto = new MethodBuilder(InterfaceGenerator.INSTANTIATE_DTO_FROM_CLASS_METHOD, "Object", "Class<?> posoType");
		createDto.addMethodCommentLine("Generates a Dto instance given a Poso.");
		createDto.addAnnotationString("SuppressWarnings", "unchecked");
		
		/* update helper */
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			createDto.addBodyLine("boolean initial = null == creationHelper.get();");
			createDto.beginOneLineBlock("if(initial)");
				createDto.addBodyLine("creationHelper.set(creationHelperProvider.get());");
			createDto.addBodyLine();
			createDto.beginTryBlock();
		}
			
		/* null check */
		createDto.addBodyComment("return null if poso is null");
		createDto.beginOneLineBlock("if(null == posoType)");
		createDto.addBodyLine("return null;");
		
		/* call submodules ?*/
		addCallSubModuleByPosoType(createDto, new MethodBuilderCallback(){
			@Override
			public void execute(MethodBuilder mb) {
				mb.addBodyLine("return submodule." + InterfaceGenerator.INSTANTIATE_DTO_FROM_CLASS_METHOD + "(posoType);");
			}
		});
		
		createDto.addBodyLine();
		createDto.addBodyComment("validate object and find correct type");
		createDto.addBodyLine("Class<?> orgType = posoType;");
		createDto.beginBodyBlock("while(! " + DTO_GENERATOR_BY_POSO_LOOKUP_NAME + ".containsKey(posoType))");
		createDto.beginOneLineBlock("if(null == posoType)");
		createDto.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Poso: \" + orgType.getName());");
		createDto.addBodyLine("posoType = posoType.getSuperclass();");
		createDto.endBodyBlock();
		
		createDto.addBodyLine();

		createDto.addBodyComment("get generator");
		createDto.addBodyLine(Poso2DtoGenerator.class.getSimpleName() + " generator = " + DTO_GENERATOR_BY_POSO_LOOKUP_NAME + ".get(posoType);");
		createDto.addBodyLine("return generator.instantiateDto(null);");
		
		/* helper */
		addHelperFinal(createDto);
		
		sourceBuilder.append("\n").append(createDto).append("\n");
	}
	
	private void addIsAuthorityForPosoClassMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(InterfaceGenerator.IS_AUTHORITY_FOR_POSO_CLASS_METHOD, "boolean", "Class<?> posoType");
		
		/* null check */
		method.beginOneLineBlock("if(null == posoType)");
		method.addBodyLine("return false;");
		method.addBodyLine();
		
		method.addBodyComment("validate object");
		method.beginBodyBlock("while(! " + POSO_CLASS_SET + ".contains(posoType))");
			method.beginOneLineBlock("if(null == posoType || posoType.isAnnotationPresent(" + GenerateDto.class.getName() + ".class))");
				method.addBodyLine("return false;");
			method.addBodyLine("posoType = posoType.getSuperclass();");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyLine("return true;");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	
	private void addIsAuthorityForPosoMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(InterfaceGenerator.IS_AUTHORITY_FOR_POSO_METHOD, "boolean", "Object poso");
		
		/* null check */
		method.beginOneLineBlock("if(null == poso)");
		method.addBodyLine("return false;");
		method.addBodyLine();
		
		method.addBodyLine("return " + InterfaceGenerator.IS_AUTHORITY_FOR_POSO_CLASS_METHOD + "(poso.getClass());");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	private void addIsAuthorityForDtoClassMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(InterfaceGenerator.IS_AUTHORITY_FOR_DTO_CLASS_METHOD, "boolean", "Class<?> dtoType");
		
		/* null check */
		method.beginOneLineBlock("if(null == dtoType)");
		method.addBodyLine("return false;");
		method.addBodyLine();
		
		method.addBodyComment("validate object");
		method.beginBodyBlock("if(! " + DTO_CLASS_SET + ".contains(dtoType))");
			method.addBodyLine("return false;");
		method.endBodyBlock();
		
		method.addBodyLine();

		method.addBodyLine("return true;");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	private void addIsAuthorityForDtoMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(InterfaceGenerator.IS_AUTHORITY_FOR_DTO_METHOD, "boolean", "Object dto");
		
		/* null check */
		method.beginOneLineBlock("if(null == dto)");
		method.addBodyLine("return false;");
		method.addBodyLine();
		
		method.addBodyLine("return " + InterfaceGenerator.IS_AUTHORITY_FOR_DTO_CLASS_METHOD + "(dto.getClass());");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}

	private void addAttachSubModuleMethod(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(MainInterfaceGenerator.ADD_SUB_MODULE_METHOD, "void", DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME + " subModule");
		
		method.addBodyLine(SUB_MODULE_LIST + ".add(subModule);");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	private void addGetCreationHelper(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(MainInterfaceGenerator.GET_CREATION_HELPER, DtoCreationHelper.class.getSimpleName());
		
		method.addBodyLine("return creationHelper.get();");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}
	
	private void addSetCreationHelper(StringBuilder sourceBuilder) {
		/* createDto */
		MethodBuilder method = new MethodBuilder(MainInterfaceGenerator.SET_CREATION_HELPER, "void", DtoCreationHelper.class.getSimpleName() + " creationHelper");
		
		method.addBodyLine("this.creationHelper.set(creationHelper);");
		
		sourceBuilder.append("\n").append(method).append("\n");
	}

	private void addCallSubModuleByDto(MethodBuilder method,
			MethodBuilderCallback methodBuilderCallback) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginBodyBlock("if(! " + InterfaceGenerator.IS_AUTHORITY_FOR_DTO_METHOD + "(dto))");
			
			method.beginBodyBlock("for(" + DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + InterfaceGenerator.IS_AUTHORITY_FOR_DTO_METHOD + "(dto))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			
			method.addBodyComment("handle anonymous enums");
			method.beginBodyBlock("if(dto instanceof java.lang.Enum && ! java.lang.Enum.class.equals(dto.getClass().getSuperclass()))");
			method.addBodyLine("Class<?> superEnum = dto.getClass().getSuperclass();");
			
			method.beginBodyBlock("if(! " + InterfaceGenerator.IS_AUTHORITY_FOR_DTO_CLASS_METHOD + "(superEnum))");
			
			method.beginBodyBlock("for(" + DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + InterfaceGenerator.IS_AUTHORITY_FOR_DTO_CLASS_METHOD + "(superEnum))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			
			method.endBodyBlock();
			
			method.endBodyBlock();
			method.beginOneLineBlock("else if(dto instanceof java.lang.Enum && java.lang.Enum.class.equals(dto.getClass().getSuperclass()) && ! isAuthorityForDtoClass(dto.getClass().getSuperclass()))");
			method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dto.getClass().getName());");
			
			method.endBodyBlock();
			
			method.addBodyLine();
		}
	}
	
	private void addCallSubModuleByDtoType(MethodBuilder method,
			MethodBuilderCallback methodBuilderCallback) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginBodyBlock("if(! " + InterfaceGenerator.IS_AUTHORITY_FOR_DTO_CLASS_METHOD + "(dtoType))");
			
			method.beginBodyBlock("for(" + DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + InterfaceGenerator.IS_AUTHORITY_FOR_DTO_CLASS_METHOD + "(dtoType))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + dtoType.getName());");
			
			method.endBodyBlock();
		}
	}
	
	private void addCallSubModuleByPoso(MethodBuilder method,
			MethodBuilderCallback methodBuilderCallback) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginBodyBlock("if(! " + InterfaceGenerator.IS_AUTHORITY_FOR_POSO_METHOD + "(poso))");
			
			method.beginBodyBlock("for(" + DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + InterfaceGenerator.IS_AUTHORITY_FOR_POSO_METHOD + "(poso))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + poso.getClass().getName());");
			
			method.endBodyBlock();
		}
	}
	
	private void addCallSubModuleByPosoType(MethodBuilder method,
			MethodBuilderCallback methodBuilderCallback) {
		if(dtoAnnotationProcessor.isDtoMainserviceOption()){
			method.beginBodyBlock("if(! " + InterfaceGenerator.IS_AUTHORITY_FOR_POSO_CLASS_METHOD + "(posoType))");
			
			method.beginBodyBlock("for(" + DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME + " submodule : " + SUB_MODULE_LIST + ")");
			
			method.beginBodyBlock("if(submodule." + InterfaceGenerator.IS_AUTHORITY_FOR_POSO_CLASS_METHOD + "(posoType))");
			methodBuilderCallback.execute(method);
			method.endBodyBlock();
			
			method.endBodyBlock();
			method.addBodyLine("throw new IllegalArgumentException(\"Unrecognized Dto: \" + posoType.getName());");
			
			method.endBodyBlock();
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
			col.add(DTOServiceGeneratorFacilitator.SERVICE_MAIN_INTERFACE_NAME);
			col.add(DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME);
			return col;
		}
		return Collections.singleton(DtoService.class.getName());
	}

	@Override
	protected Collection<String> getReferencedClasses() {
		Collection<String> references = super.getReferencedClasses();
		
		references.add(Injector.class.getName());
		references.add(MAP_LOCATION);
		references.add(SET_LOCATION);
		references.add(HASH_SET_LOCATION);
		references.add(HASH_MAP_LOCATION);
		references.add(TreeSet.class.getName());
		references.add(LIST_LOCATION);
		references.add(ARRAY_LIST_LOCATION);
		
		references.add(DtoMainService.class.getName());
		references.add(ThreadLocal.class.getName());
		references.add(DtoCreationHelper.class.getName());
		references.add(Provider.class.getName());
		references.add(PropertyAccessor.class.getName());
		references.add(IdedDto.class.getName());
		
		references.add(DtoView.class.getName());
		references.add(Dto.class.getName());
		references.add(FtoSupervisor.class.getName());
		
		references.add(Poso2DtoGenerator.class.getName());
		
		references.add(Dto2PosoGenerator.class.getName());
		
		references.add(HibernateProxy.class.getName());
		
		references.add(Inject.class.getName());
		
		references.add(ExpectedException.class.getName());
		
		references.add(Dto2PosoMapper.class.getName());
		references.add(CorrespondingPoso.class.getName());
		references.add(IllegalStateException.class.getName());
		
		references.add(DtoService.class.getName());
		
		return references;
	}

	@Override
	protected boolean isAbstract() {
		return false;
	}

	public String getClassName() {
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			return DTOServiceGeneratorFacilitator.SERVICE_IMPLEMENTATION_NAME;
		return dtoAnnotationProcessor.getDtoServiceBaseName() + DTOServiceGeneratorFacilitator.SERVICE_IMPLEMENTATION_NAME;
	}

	public String getPackageName() {
		return dtoAnnotationProcessor.getOptionDtoServicePackage();
	}

}
