package net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
abstract public class Poso2DtoSourceFileGenerator extends SourceFileGeneratorImpl {

	public static final String CREATE_DTO_METHOD_NAME = "createDto";
	
	public static final String INSTANTIATE_DTO_METHOD_NAME = "instantiateDto";
	
	protected DtoAnnotationProcessor dtoAnnotationProcessor;
	protected PosoAnalizer posoAnalizer;
	
	/* used to accumulate references */
	protected Set<String> referenceAccu = new TreeSet<String>();
	
	public Poso2DtoSourceFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
		super(dtoAnnotationProcessor);
		this.posoAnalizer = posoAnalizer;
		this.dtoAnnotationProcessor = dtoAnnotationProcessor;
	}

	public String getPackageName() {
		return posoAnalizer.getPoso2DtoInformation().getPackage();
	}

	public String getClassName() {
		return posoAnalizer.getPoso2DtoInformation().getClassName();
	}

	@Override
	public String getFullyQualifiedClassName() {
		return posoAnalizer.getPoso2DtoInformation().getFullyQualifiedClassName();
	}
	
	
	@Override
	protected void addClassBody(StringBuilder sourceBuilder) {
		addVariableSection(sourceBuilder);
		addConstructor(sourceBuilder);
		
		addInstantiateDtoMethod(sourceBuilder);
		addCreateDtoMethod(sourceBuilder);
	}

	private void addVariableSection(StringBuilder sourceBuilder) {
		/* post processor */
		if(posoAnalizer.getPoso2DtoInformation().hasPostProcessors()){
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getPoso2DtoInformation().getPostProcessors())
				sourceBuilder.append("\tprivate final " + type.toString() + " postProcessor_" + postNr++ + ";\n");
		}

		sourceBuilder.append("\tprivate final Provider<DtoService> dtoServiceProvider;\n\n");
	}

	private void addConstructor(StringBuilder sourceBuilder) {
		sourceBuilder.append("\t@Inject\n")
					.append("\tpublic ").append(getClassName()).append("(\n")
					.append("\t\tProvider<DtoService> dtoServiceProvider");
		
		/* post processor */
		if(posoAnalizer.getPoso2DtoInformation().hasPostProcessors()){
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getPoso2DtoInformation().getPostProcessors())
				sourceBuilder.append(",\n\t\t" + type.toString() + " postProcessor_" + postNr++);
		}
	
		sourceBuilder.append("\t){\n")
					.append("\t\tthis.dtoServiceProvider = dtoServiceProvider;\n");
	
		/* post processor */
		if(posoAnalizer.getPoso2DtoInformation().hasPostProcessors()){
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getPoso2DtoInformation().getPostProcessors())
				sourceBuilder.append("\t\tthis.postProcessor_" + postNr + " = postProcessor_" + postNr++ + ";\n");
		}
	
		
		sourceBuilder.append("\t}\n\n");
	}
	
	protected abstract void addCreateDtoMethod(StringBuilder sourceBuilder);
	
	protected abstract void addInstantiateDtoMethod(StringBuilder sourceBuilder);

	@Override
	protected void addClassComment(StringBuilder sourceBuilder) {
		sourceBuilder.append("\n/**\n")
		.append(" * Poso2DtoGenerator for ").append(posoAnalizer.getSimpleName()).append("\n")
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
		
		if(posoAnalizer.getDtoInformation().hasAdditionalImports())
			for(DeclaredType type : posoAnalizer.getDtoInformation().getAdditionalImports())
				imports.add(((TypeElement)type.asElement()).getQualifiedName().toString());
		
		imports.add(Poso2DtoGenerator.class.getName());
		imports.add(posoAnalizer.getFullyQualifiedClassName());
		imports.add(posoAnalizer.getDtoInformation().getFullyQualifiedClassName());
		imports.add(posoAnalizer.getPoso2DtoInformation().getFullyQualifiedClassName());
		
		imports.add(Provider.class.getName());
		
		imports.add(Inject.class.getName());
		imports.add(DtoView.class.getName());
		
		if(dtoAnnotationProcessor.isDtoMainserviceOption())
			imports.add(DtoMainService.class.getName());
		imports.add(DtoService.class.getName());
		
		imports.addAll(referenceAccu);
		
		return imports;
	}

	@Override
	protected Collection<String> getImplementedInterfaces() {
		String dtoClass = posoAnalizer.getDtoInformation().hasDecorator() ?
					posoAnalizer.getDtoInformation().getClassNameForDecorator() :
					posoAnalizer.getDtoInformation().getClassName();
					
		return Collections.singleton(
			Poso2DtoGenerator.class.getSimpleName() + "<" + posoAnalizer.getSimpleName() + "," + dtoClass + ">"
		);
	}

	@Override
	protected boolean isAbstract() {
		return false;
	}


	protected void addPostProcessingMethod(MethodBuilder method, String methodName, String arguments){
		/* post processor */
		if(posoAnalizer.getPoso2DtoInformation().hasPostProcessors()){
			method.addBodyLine();
			method.addBodyComment("post processing");
			int postNr = 1;
			for(DeclaredType type : posoAnalizer.getPoso2DtoInformation().getPostProcessors()){
				method.addBodyLine("this.postProcessor_" + postNr++ + "." + methodName + "(" + arguments + ");");
			}
		}

	}
}
