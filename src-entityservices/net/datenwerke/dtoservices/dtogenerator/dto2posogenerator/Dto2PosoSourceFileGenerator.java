package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.lang.model.type.DeclaredType;
import javax.persistence.EntityManager;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
abstract public class Dto2PosoSourceFileGenerator extends SourceFileGeneratorImpl {

	public static final String DTO_SERVICE_NAME = "dtoServiceProvider";
	public static final String ENTITY_MANAGER_PROVIDER_NAME = "entityManagerProvider";
	public static final String DTO2POSO_SUPERVISOR_NAME = "dto2PosoSupervisor";
	
	protected DtoAnnotationProcessor dtoAnnotationProcessor;
	protected PosoAnalizer posoAnalizer;
	
	/* used to accumulate references */
	protected Set<String> referenceAccu = new TreeSet<String>();
	
	public Dto2PosoSourceFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
		super(dtoAnnotationProcessor);
		this.posoAnalizer = posoAnalizer;
		this.dtoAnnotationProcessor = dtoAnnotationProcessor;
	}

	public String getPackageName() {
		return posoAnalizer.getDto2PosoInformation().getPackage();
	}

	public String getClassName() {
		return posoAnalizer.getDto2PosoInformation().getClassName();
	}

	@Override
	public String getFullyQualifiedClassName() {
		return posoAnalizer.getDto2PosoInformation().getFullyQualifiedClassName();
	}
	
	@Override
	protected void addClassBody(StringBuilder sourceBuilder) {
		addMemberVariableSection(sourceBuilder);
		addConstructor(sourceBuilder);
		
		addLoadPoso(sourceBuilder);
		addInstantiatePoso(sourceBuilder);
		addCreatePoso(sourceBuilder);
		addCreateUnmanagedPoso(sourceBuilder);
		addMergePoso(sourceBuilder);
		addMergeUnmanagedPoso(sourceBuilder);
		addloadAndMergePoso(sourceBuilder);
		
		addPostProcessingMethods(sourceBuilder);
		
		addCustomMethods(sourceBuilder);
	}
	
	protected void addCustomMethods(StringBuilder sourceBuilder) {
		// to be overriden
	}

	protected void addConstructor(StringBuilder sourceBuilder) {
		/* prepare arguments */
		Map<String, String> arguments = new TreeMap<String, String>();
		arguments.put(DTO_SERVICE_NAME, "Provider<DtoService>");
		if(posoAnalizer.isJpaEntity()){
			referenceAccu.add(Provider.class.getName());
			referenceAccu.add(EntityManager.class.getName());
			arguments.put(ENTITY_MANAGER_PROVIDER_NAME, "Provider<EntityManager>");
		}

		/* post processor */
		if(posoAnalizer.getDto2PosoInformation().hasPostProcessors()){
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getDto2PosoInformation().getPostProcessors()){
				arguments.put("postProcessor_" + postNr++, type.toString());
			}
		}
		
		/* dto2pososupervisor */
		DeclaredType type = posoAnalizer.getDto2PosoInformation().getDto2PosoSupervisor();
		arguments.put(DTO2POSO_SUPERVISOR_NAME, type.toString());
		
		/* any other arguments? */
		addConstructorArguments(arguments);
		
		/* begin method */
		sourceBuilder.append("\t@Inject\n")
					.append("\tpublic ").append(getClassName()).append("(\n");
		
		/* arguments */
		Iterator<Entry<String,String>> argIt = arguments.entrySet().iterator();
		while(argIt.hasNext()){
			Entry<String,String> arg = argIt.next();
			sourceBuilder.append("\t\t").append(arg.getValue()).append(" ").append(arg.getKey()).append(argIt.hasNext() ? ",\n" : "\n");
		}

		sourceBuilder.append("\t){\n");
					
		/* constructor body */
		for(String argName : arguments.keySet())
			sourceBuilder.append("\t\tthis.").append(argName).append(" = ").append(argName).append(";\n");
					
		/* constructor end */
		sourceBuilder.append("\t}\n\n");
	}
	
	protected void addConstructorArguments(Map<String, String> arguments) {
		// can be overridden
	}

	protected void addMemberVariableSection(StringBuilder sourceBuilder) {
		sourceBuilder.append("\tprivate final Provider<DtoService> " + DTO_SERVICE_NAME + ";\n\n");
		if(posoAnalizer.isJpaEntity())
			sourceBuilder.append("\tprivate final Provider<EntityManager> " + ENTITY_MANAGER_PROVIDER_NAME + ";\n\n");
		
		/* post processor */
		if(posoAnalizer.getDto2PosoInformation().hasPostProcessors()){
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getDto2PosoInformation().getPostProcessors())
				sourceBuilder.append("\tprivate final " + type.toString() + " postProcessor_" + postNr++ + ";\n\n");
		}

		DeclaredType type = posoAnalizer.getDto2PosoInformation().getDto2PosoSupervisor();
		sourceBuilder.append("\tprivate final " + type.toString() + " " + DTO2POSO_SUPERVISOR_NAME + ";\n\n");
		
		addAdditionalMemberVariables(sourceBuilder);
	}

	protected void addAdditionalMemberVariables(StringBuilder sourceBuilder) {
		// To be overriden
	}

	protected String getDtoType() {
		return posoAnalizer.getDtoInformation().getClassName();
	}

	protected String getPosoType() {
		return posoAnalizer.getSimpleName();
	}
	
	abstract protected void addMergePoso(StringBuilder sourceBuilder);
	
	abstract protected void addMergeUnmanagedPoso(StringBuilder sourceBuilder);

	abstract protected void addloadAndMergePoso(StringBuilder sourceBuilder);
	
	abstract protected void addLoadPoso(StringBuilder sourceBuilder);

	abstract protected void addInstantiatePoso(StringBuilder sourceBuilder);
	
	abstract protected void addCreatePoso(StringBuilder sourceBuilder);
	
	abstract protected void addCreateUnmanagedPoso(StringBuilder sourceBuilder);


	@Override
	protected void addClassComment(StringBuilder sourceBuilder) {
		sourceBuilder.append("\n/**\n")
		.append(" * Dto2PosoGenerator for ").append(posoAnalizer.getSimpleName()).append("\n")
		.append(" *\n")
		.append(" * This file was automatically created by ")
			.append(DtoAnnotationProcessor.name)
			.append(", version ")
			.append(DtoAnnotationProcessor.version)
			.append("\n")
		.append(" */\n");
	}

	@Override
	protected String getExtendedClass() {
		return null;
	}

	@Override
	protected Collection<String> getReferencedClasses() {
		Collection<String> imports = super.getReferencedClasses();
		
		imports.add(Collection.class.getName());
		
		imports.add(Dto2PosoGenerator.class.getName());
		imports.add(posoAnalizer.getFullyQualifiedClassName());
		imports.add(posoAnalizer.getDtoInformation().getFullyQualifiedClassName());
		imports.add(posoAnalizer.getDto2PosoInformation().getFullyQualifiedClassName());
		imports.add(Provider.class.getName());
		
		imports.add(Inject.class.getName());
		
		imports.add(ExpectedException.class.getName());
		imports.add(RuntimeException.class.getName());
		imports.add(DtoService.class.getName());
		
		imports.addAll(referenceAccu);
		
		return imports;
	}

	@Override
	protected Collection<String> getImplementedInterfaces() {
		return Collections.singleton(
			Dto2PosoGenerator.class.getSimpleName() + "<" + posoAnalizer.getDtoInformation().getClassName() + "," +  posoAnalizer.getSimpleName() + ">"
		);
	}

	@Override
	protected boolean isAbstract() {
		return false;
	}

	protected void addPostProcessingMethods(StringBuilder sourceBuilder){
MethodBuilder method = new MethodBuilder("postProcessCreate", "void", getDtoType() + " dto", getPosoType() + " poso");
		
		if(posoAnalizer.getDto2PosoInformation().hasPostProcessors()){
			method.addBodyLine();
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getDto2PosoInformation().getPostProcessors()){
				method.addBodyLine("this.postProcessor_" + postNr++ + ".posoCreated(dto, poso);");
			}
		}
		
		sourceBuilder.append(method).append("\n\n");
		
		method = new MethodBuilder("postProcessCreateUnmanaged", "void", getDtoType() + " dto", getPosoType() + " poso");
		
		if(posoAnalizer.getDto2PosoInformation().hasPostProcessors()){
			method.addBodyLine();
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getDto2PosoInformation().getPostProcessors()){
				method.addBodyLine("this.postProcessor_" + postNr++ + ".posoCreatedUnmanaged(dto, poso);");
			}
		}
		
		sourceBuilder.append(method).append("\n\n");
		
		method = new MethodBuilder("postProcessLoad", "void", getDtoType() + " dto", getPosoType() + " poso");
		
		if(posoAnalizer.getDto2PosoInformation().hasPostProcessors()){
			method.addBodyLine();
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getDto2PosoInformation().getPostProcessors()){
				method.addBodyLine("this.postProcessor_" + postNr++ + ".posoLoaded(dto, poso);");
			}
		}
		
		sourceBuilder.append(method).append("\n\n");
		
		method = new MethodBuilder("postProcessMerge", "void", getDtoType() + " dto", getPosoType() + " poso");
		
		if(posoAnalizer.getDto2PosoInformation().hasPostProcessors()){
			method.addBodyLine();
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getDto2PosoInformation().getPostProcessors()){
				method.addBodyLine("this.postProcessor_" + postNr++ + ".posoMerged(dto, poso);");
			}
		}
		
		sourceBuilder.append(method).append("\n\n");
		
		method = new MethodBuilder("postProcessInstantiate", "void", getPosoType() + " poso");
		
		if(posoAnalizer.getDto2PosoInformation().hasPostProcessors()){
			method.addBodyLine();
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getDto2PosoInformation().getPostProcessors()){
				method.addBodyLine("this.postProcessor_" + postNr++ + ".posoInstantiated(poso);");
			}
		}
		
		sourceBuilder.append(method).append("\n\n");
	}
}
