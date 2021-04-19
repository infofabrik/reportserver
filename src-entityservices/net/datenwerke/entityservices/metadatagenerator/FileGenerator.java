package net.datenwerke.entityservices.metadatagenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.entityservices.metadatagenerator.interfaces.EntityMetadataProvider;

public class FileGenerator extends SourceFileGeneratorImpl {

	private EntityMetadataProcessor processor;
	private Element element;

	public FileGenerator(EntityMetadataProcessor processor, Element element){
		super(processor);
		this.processor = processor;
		this.element = element;
	}
	
	@Override
	public String getPackageName() {
		Elements utils = processor.getProcessingEnvironment().getElementUtils();
		return utils.getPackageOf(element).toString(); 
	}

	@Override
	public String getClassName() {
		return element.getSimpleName().toString() + "__";
	}

	@Override
	protected void addClassBody(StringBuilder sourceBuilder) {
		List<VariableElement> fieldsIn = new ArrayList<VariableElement>(ElementFilter.fieldsIn(element.getEnclosedElements()));
		Collections.sort(fieldsIn, new Comparator<VariableElement>() {
			@Override
			public int compare(VariableElement o1, VariableElement o2) {
				return o1.getSimpleName().toString().compareTo(o2.getSimpleName().toString());
			}
		});
		
		for(VariableElement field : fieldsIn){
			if(null == field.getAnnotation(Transient.class))
				sourceBuilder.append("\tpublic static final String ").append(field.getSimpleName().toString()).append(" = \"").append(field.getSimpleName().toString()).append("\";\n");
		}
	}

	@Override
	protected String getExtendedClass() {
		Element superclass = ((DeclaredType)((TypeElement)element).getSuperclass()).asElement();
		if(null == superclass.getAnnotation(Entity.class) && null == superclass.getAnnotation(MappedSuperclass.class))
			return super.getExtendedClass();

		DeclaredType typeDecl = ((DeclaredType)((TypeElement)element).getSuperclass());
		String name = typeDecl.toString();

		/* do we have type parameters */
		if(typeDecl.getTypeArguments().isEmpty())
			return name + "__";
		
		return name.substring(0, name.indexOf("<")) + "__";
	}
	
	@Override
	protected Collection<String> getImplementedInterfaces() {
		Collection<String> ifaces = super.getImplementedInterfaces();
		ifaces.add(EntityMetadataProvider.class.getName());
		return ifaces;
	}
}
