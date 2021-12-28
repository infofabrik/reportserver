package net.datenwerke.eximport.ex.objectexporters;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Transient;
import javax.xml.stream.XMLStreamException;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.ExImportHelperService;
import net.datenwerke.eximport.ex.ExportItemConfig;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.annotations.ExImportConfig;
import net.datenwerke.eximport.ex.annotations.ExportableField;
import net.datenwerke.eximport.exceptions.ExportException;
import net.datenwerke.eximport.hooks.BasicObjectExImporterHelperHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.interfaces.Callback;
import net.datenwerke.rs.utils.interfaces.Callback2;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 *
 *
 */
public class BasicObjectExporter {

	private static Map<Class<?>, Set<Field>> exportableFieldCache = new HashMap<Class<?>, Set<Field>>();
	
	public interface ObjectExporterAdjuster{
		public void configureBase() throws XMLStreamException;
		public void preProcess(BasicObjectExporter basicObjectExporter) throws XMLStreamException;
		public void postProcessField(BasicObjectExporter basicObjectExporter, Field exportableField, Object value);
		public void postProcess(BasicObjectExporter basicObjectExporter) throws XMLStreamException;
	}
	
	protected final ExImportHelperService eiHelper;
	protected final HookHandlerService hookHandler;
	protected final ReflectionService reflectionServices;
	
	protected final ExportSupervisor exportSupervisor;
	
	protected final String toExportId;
	protected final Object toExport;
	
	protected ObjectExporterAdjuster adjuster = new ObjectExporterAdjuster(){

		@Override
		public void preProcess(BasicObjectExporter basicObjectExporter) {}

		@Override
		public void postProcessField(BasicObjectExporter basicObjectExporter,
				Field exportableField, Object value) {}

		@Override
		public void configureBase() {
		}

		@Override
		public void postProcess(BasicObjectExporter basicObjectExporter) {
		}
	};
	
	protected Set<String> ignoredFieldNames = new HashSet<String>();
	
	@Inject
	public BasicObjectExporter(
		ExImportHelperService eiHelper,
		HookHandlerService hookHandler,
		ReflectionService reflectionServices,
		@Assisted ExportSupervisor exportSupervisor,
		@Assisted String toExportId,
		@Assisted Object toExport
		){
		
		/* store objects */
		this.hookHandler = hookHandler;
		this.eiHelper = eiHelper;
		this.reflectionServices = reflectionServices;
		this.exportSupervisor = exportSupervisor;
		this.toExportId = toExportId;
		
		if(toExport instanceof HibernateProxy)
			toExport = ((HibernateProxy)toExport).getHibernateLazyInitializer().getImplementation();
		this.toExport = toExport;
	}
	
	
	public Set<String> getIgnoredFieldNames() {
		return ignoredFieldNames;
	}

	public void setIgnoredFieldNames(Set<String> ignoredFieldNames) {
		this.ignoredFieldNames = ignoredFieldNames;
	}

	public void addIgnoredFieldName(String name){
		this.ignoredFieldNames.add(name);
	}
	

	public void export() throws XMLStreamException{
		beginBaseElement();
		
		eiHelper.setTypeAttribute(exportSupervisor.getXmlStream(), toExport.getClass());
				
		exportWithoutBaseElementCreation();
		
		exportSupervisor.endElement();
	}
	

	public void exportWithoutBaseElementCreation() throws XMLStreamException {
		preProcess();
		
		/* add Id */
		exportSupervisor.addIdAttributeToBaseElement(toExportId);

		adjuster.configureBase();
		
		loopOverExportableFieldsThatAreNotNull(new Callback2<Field, Object>() {
			@Override
			public void execute(Field exportableField, Object value) throws Exception {
				if(reflectionServices.isCollection(exportableField))
					exportCollection(exportableField, (Collection<?>) value);
				else
					exportSingleValue(exportableField, value);
				
				postProcessField(exportableField, value);
			}
		});
		
		postProcess();
	}
	
	protected void preProcess() throws XMLStreamException {
		adjuster.preProcess(this);
	}


	protected void postProcessField(Field exportableField, Object value) {
		adjuster.postProcessField(this, exportableField, value);
	}


	protected void exportCollection(Field field, Collection<?> collection) throws IllegalArgumentException, IllegalAccessException, XMLStreamException {
		Class<?> collectionType = reflectionServices.getGenericType(field);
		
		if(reflectionServices.isSimpleType(collectionType)){
			exportSupervisor.beginPropertyCollectionElement(field);
			exportSimpleTypedCollectionField((Collection<?>) collection);
			exportSupervisor.endElement();
		} else {
			BasicObjectExImporterHelperHook helper = getExporterHelper(collectionType);
			if(null != helper){
				exportSupervisor.beginPropertyCollectionElement(field);
				exportCollectionFieldWithHelper((Collection<?>) collection, helper);
				exportSupervisor.endElement();
			} else 
				processComplexCollection(field, collection, collectionType);
		}
	}


	protected void exportCollectionFieldWithHelper(Collection<?> collection, BasicObjectExImporterHelperHook helper) throws XMLStreamException {
		for(Object value : collection){
			exportSupervisor.beginSimpleCollectionValueElement(value);
			if(null != value)
				helper.export(exportSupervisor, value);
			exportSupervisor.endElement();
		}
	}


	protected void exportSimpleTypedCollectionField(Collection<?> collection) throws XMLStreamException {
		for(Object value : collection)
			exportSupervisor.createSimpleCollectionValueElement(value);
	}

	protected void exportSingleValue(Field exportableField, Object value) throws IllegalArgumentException, IllegalAccessException, XMLStreamException {
		if(reflectionServices.isSimpleField(exportableField))
			exportSupervisor.createPropertyElement(exportableField, value);
		else {
			BasicObjectExImporterHelperHook helper = getExporterHelper(exportableField);
			
			if(null != helper)
				exportFieldWithHelper(exportableField, helper, value);
			else
				processComplexField(exportableField, value);
		}
	}


	protected void exportFieldWithHelper(Field field, BasicObjectExImporterHelperHook helper, Object value) throws IllegalArgumentException, IllegalAccessException, XMLStreamException {
		exportSupervisor.beginPropertyElement(field, value);
		helper.export(exportSupervisor, value);
		exportSupervisor.endElement();
	}

	protected BasicObjectExImporterHelperHook getExporterHelper(Field exportableField) {
		return getExporterHelper(exportableField.getType());
	}

	protected BasicObjectExImporterHelperHook getExporterHelper(Class<?> type) {
		for(BasicObjectExImporterHelperHook helper : hookHandler.getHookers(BasicObjectExImporterHelperHook.class))
			if(helper.consumes(type))
				return helper;
		return null;
	}

	protected Set<Field> gatherExportableFields(Class<?> targetClass) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if(exportableFieldCache.containsKey(targetClass))
			return exportableFieldCache.get(targetClass);
		
		Set<Field> fields = new HashSet<Field>();
		
		while(null != targetClass){
			for(Field field : targetClass.getDeclaredFields()){
				if( ! Modifier.isFinal(field.getModifiers()) && ! Modifier.isTransient(field.getModifiers()) && ! Modifier.isStatic(field.getModifiers()))
					if(isFieldExportable(field))
						fields.add(field);
			}
			targetClass = targetClass.getSuperclass();
		}
		
		/* put in cache */
		exportableFieldCache.put(targetClass, fields);
		
		return fields;
	}

	protected boolean isFieldExportable(Field field) {
		return true;
	}

	protected void beginBaseElement() throws XMLStreamException {
		exportSupervisor.beginElement(getBaseElementName());
	}
	
	protected String getBaseElementName(){
		/* use class name */
		return ExImportHelperService.EXPORTED_ITEM_ELEMENT_NAME;
	}
	
	
	protected ExportableField getExportableFieldAnno(Field field){
		ExportableField anno = field.getAnnotation(ExportableField.class);
		return anno;
	}

	/**
	 * Template method that can be overridden to control which fields are to be exported.
	 * @param exportableField
	 */
	protected boolean allowExportField(Field field) {
		if(ignoredFieldNames.contains(field.getName()))
			return false;
		ExportableField anno =  getExportableFieldAnno(field);
		if(null != anno && ! anno.exportField())
			return false;
		
		Transient annotation = field.getAnnotation(Transient.class);
		if(null != annotation && null == anno)
			return false;
		
		ExImportConfig exImportConfig = getExportConfigAnno();
		if(null != exImportConfig){
			for(String excluded : exImportConfig.excludeFields())
				if(excluded.contains(field.getName()))
					return false;
		}
		
		return true;
	}
	
	
	protected ExImportConfig getExportConfigAnno() {
		return toExport.getClass().getAnnotation(ExImportConfig.class);
	}


	/**
	 * Can be overridden in order to process more complex fields.
	 * 
	 * @param exportableField
	 * @param value 
	 * @throws XMLStreamException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	protected void processComplexField(Field exportableField, Object value) throws XMLStreamException {
	}
	
	/**
	 * Can be overridden in order to process more complex fields.
	 * 
	 * @param exportableField
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws XMLStreamException 
	 */
	protected void processComplexCollection(Field exportableField, Collection<?> collection, Class<?> collectionType) throws IllegalArgumentException, IllegalAccessException, XMLStreamException {
	}
	

	/**
	 * Can be overriden to adjust the export.
	 * 
	 * <p>Method is called before fields are exported</p>
	 * @throws XMLStreamException 
	 */
	protected void postProcess() throws XMLStreamException {
		adjuster.postProcess(this);
	}


	/**
	 * Can be overriden to adjust the export.
	 * 
	 * <p>Method is called after fields have been exported</p>
	 * @param base
	 */


	protected void loopOverExportableFields(Callback<Field> callback) {
		try{
			for(Field exportableField : gatherExportableFields(toExport.getClass())){
				/* shall we allow the export */
				if(! allowExportField(exportableField))
					continue;
				
				/* make field accessible */
				exportableField.setAccessible(true);
				
				callback.execute(exportableField);
			}
		} catch(Exception e){
			throw new ExportException(e);
		}
	}
	
	protected void loopOverExportableFieldsThatAreNotNull(final Callback2<Field, Object> callback) {
		loopOverExportableFields(new Callback<Field>() {
			@Override
			public void execute(Field exportableField) throws Exception {
				Object value = exportableField.get(toExport);
				if(null == value)
					return;
				
				callback.execute(exportableField, value);
			}
		});
	}
	
	public Collection<ExportItemConfig<?>> getReferences(){
		return Collections.emptySet();
	}
	
	public Collection<EnclosedObjectConfig> getEnclosed(){
		return Collections.emptySet();
	}
	
	public ObjectExporterAdjuster getAdjuster() {
		return adjuster;
	}
	
	public void setAdjuster(ObjectExporterAdjuster adjuster) {
		this.adjuster = adjuster;
	}
	
	public Object getToExport() {
		return toExport;
	}
	
	public String getToExportId() {
		return toExportId;
	}


}
