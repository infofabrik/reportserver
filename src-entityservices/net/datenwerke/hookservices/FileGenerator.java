package net.datenwerke.hookservices;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.persistence.Transient;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;

public class FileGenerator extends SourceFileGeneratorImpl {

	private HookAdapterProcessor processor;
	private Element element;
	private Collection<String> referenceAccu = new HashSet<String>();

	public FileGenerator(HookAdapterProcessor processor, Element element){
		super(processor);
		this.processor = processor;
		this.element = element;
	}
	
	@Override
	public String getPackageName() {
		Elements utils = processor.getProcessingEnvironment().getElementUtils();
		return utils.getPackageOf(element).toString() + ".adapter"; 
	}

	@Override
	public String getClassName() {
		return element.getSimpleName().toString() + "Adapter";
	}

	@Override
	protected void addClassBody(StringBuilder sourceBuilder) {
		for(ExecutableElement method : ElementFilter.methodsIn(element.getEnclosedElements())){
			if(null == method.getAnnotation(Transient.class)){
				String name = method.getSimpleName().toString();
				String typeName = "";
				TypeMirror type = method.getReturnType();
				if(! (type instanceof DeclaredType))
					typeName =  type.toString();
				else {
					referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) type));
					addArgumentsToReference(type);
					
					typeName = SourceFileGenerationUtils.getSimpleTypeName((DeclaredType)type);
				}
				
				List<? extends VariableElement> parameters = method.getParameters();
				String[] arguments = new String[parameters.size()];
				int i = 0;
				for(VariableElement element : parameters){
					String aName = element.getSimpleName().toString();
					String aTypeName = "";
					TypeMirror aType = element.asType();
					if(! (aType instanceof DeclaredType))
						aTypeName =  aType.toString();
					else  {
						aTypeName = SourceFileGenerationUtils.getSimpleTypeName((DeclaredType)aType);
						
						referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) aType));
						addArgumentsToReference(aType);
					}

					arguments[i++] = aTypeName + " " + aName;
				}
				
				MethodBuilder builder = new MethodBuilder(name, typeName, arguments);
				builder.addOverride();
				builder.setPublicModifier();
				
				if(! "void".equals(typeName)){
					if(! (type instanceof DeclaredType)){
						if(SourceFileGenerationUtils.isBooleanType(type))
							builder.addBodyLine("return false;");
						else
							builder.addBodyLine("return 0;");
					}
					else if(null != SourceFileGenerationUtils.isCollection((DeclaredType) type)){
						Collection col = SourceFileGenerationUtils.instantiateCollection((DeclaredType) type);

						builder.addBodyLine("return new " + col.getClass().getSimpleName() + "();");
						
						referenceAccu.add(col.getClass().getName());
					} else {
						builder.addBodyLine("return null;");
					}
					
					addArgumentsToReference(type);
				}
				
				sourceBuilder.append(builder).append("\n\n");
//				sourceBuilder.append("\tpublic ").append(^).append(method.getSimpleName().toString()).append(" = \"").append(method.getSimpleName().toString()).append("\";\n");
			}
		}
	}

	
	private void addArgumentsToReference(TypeMirror type) {
		if(! (type instanceof DeclaredType))
			return;
		
		for(TypeMirror arg: ((DeclaredType)type).getTypeArguments()){
			if(arg instanceof DeclaredType){
				referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) arg));
				addArgumentsToReference(arg);
			}
		}
		
	}

	@Override
	protected Collection<String> getImplementedInterfaces() {
		Collection<String> ifaces = super.getImplementedInterfaces();
		ifaces.add(element.getSimpleName().toString());
		return ifaces;
	}
	
	@Override
	protected Collection<String> getReferencedClasses() {
		Collection<String> references = super.getReferencedClasses();
		
		Elements utils = processor.getProcessingEnvironment().getElementUtils();
		references.add(utils.getPackageOf(element).toString() + "." + element.getSimpleName());
		references.addAll(referenceAccu);
		
		return references;
	}
}
