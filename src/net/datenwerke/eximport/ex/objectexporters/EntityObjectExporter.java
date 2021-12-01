package net.datenwerke.eximport.ex.objectexporters;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.ExImportHelperService;
import net.datenwerke.eximport.ex.ExportItemConfig;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.interfaces.Callback2;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.rs.utils.reflection.ReflectionService;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Exporter to export entities (database entities) honoring fields marked as enclosed or referenced.
 *
 */
public class EntityObjectExporter extends BasicObjectExporter {

	private final EntityUtils jpaServices;

	private boolean exportVersionAttribute = false;
	
	@Inject
	public EntityObjectExporter(
		ExImportHelperService eiHelper,
		HookHandlerService hookHandler,
		ReflectionService reflectionServices,
		EntityUtils jpaServices,
		@Assisted ExportSupervisor exportSupervisor,
		@Assisted String toExportId,
		@Assisted Object toExport	
		) {
		super(eiHelper, hookHandler, reflectionServices, exportSupervisor, toExportId, toExport);
		
		if(toExport instanceof HibernateProxy)
			toExport = ((HibernateProxy)toExport).getHibernateLazyInitializer().getImplementation();
		
		/* store objects */
		this.jpaServices = jpaServices;
	}
	
	/**
	 * do not export id fields
	 */
	@Override
	protected boolean isFieldExportable(Field field) {
		return ! field.isAnnotationPresent(Id.class) && ! field.isAnnotationPresent(Version.class);
	}
	
	@Override
	protected void processComplexField(Field exportableField, Object value) throws XMLStreamException{
		/* dereference hibernate objects */
		if(value instanceof HibernateProxy)
			value = ((HibernateProxy)value).getHibernateLazyInitializer().getImplementation();
		
		if(! jpaServices.isEntity(exportableField.getType())){
			super.processComplexField(exportableField, value);
			return;
		}
		
		if(isEnclosed(exportableField))
			exportEnclosedProperty(exportableField, value);
		else
			exportReferencedProperty(exportableField, value);
	}
	
	@Override
	protected void processComplexCollection(Field exportableField, Collection<?> collection, Class<?> collectionType) throws IllegalArgumentException, IllegalAccessException, XMLStreamException {
		if(! jpaServices.isEntity(collectionType)){
			super.processComplexCollection(exportableField, collection, collectionType);
			return;
		}
		
		if(isEnclosed(exportableField))
			exportEnclosedPropertyCollection(exportableField, collection);
		else
			exportReferencedPropertyCollection(exportableField, collection);
	}
	
	protected void exportReferencedPropertyCollection(Field exportableField, Collection<?> collection) throws XMLStreamException {
		exportSupervisor.beginPropertyCollectionElement(exportableField);
		
		for(Object object : collection){
			/* dereference hibernate objects */
			if(object instanceof HibernateProxy)
				object = ((HibernateProxy)object).getHibernateLazyInitializer().getImplementation();
			
			exportSupervisor.beginSimpleCollectionValueElement(object);
			exportSupervisor.addReferenceTo(object);
			exportSupervisor.endElement();
		}
		
		exportSupervisor.endElement();
	}

	protected void exportEnclosedPropertyCollection(Field exportableField, Collection<?> collection) throws XMLStreamException {
		exportSupervisor.beginPropertyCollectionElement(exportableField);
		
		for(Object enclosed : collection){
			/* dereference hibernate objects */
			if(enclosed instanceof HibernateProxy)
				enclosed = ((HibernateProxy)enclosed).getHibernateLazyInitializer().getImplementation();
			
			exportSupervisor.beginSimpleCollectionValueElement(enclosed);
			exportSupervisor.exportEnclosedObject(enclosed);
			exportSupervisor.endElement();
		}
		
		exportSupervisor.endElement();
	}

	protected boolean isEnclosed(Field exportableField) {
		return null != exportableField.getAnnotation(EnclosedEntity.class);
	}

	protected void exportReferencedProperty(Field field, Object value) throws XMLStreamException {
		exportSupervisor.beginPropertyElement(field, value);
		exportSupervisor.addReferenceTo(value);
		exportSupervisor.endElement();
	}

	protected void exportEnclosedProperty(Field field, Object value) throws XMLStreamException {
		exportSupervisor.beginPropertyElement(field, value);
		exportSupervisor.exportEnclosedObject(value);
		exportSupervisor.endElement();
	}
	
	@Override
	protected boolean allowExportField(Field field) {
		if(null == field )
			return false;
		
		if(field.isAnnotationPresent(Version.class) && ! exportVersionAttribute)
			return false;
		
		return super.allowExportField(field);
	}

	@Override
	public Collection<ExportItemConfig<?>> getReferences() {
		final Set<ExportItemConfig<?>> references = new HashSet<ExportItemConfig<?>>();
		
		loopOverExportableFieldsThatAreNotNull(new Callback2<Field, Object>() {
			@Override
			public void execute(Field exportableField, Object value) throws Exception {
				if(isEnclosed(exportableField))
					return;
				
				if(reflectionServices.isCollection(exportableField)){
					Class<?> collectionType = reflectionServices.getGenericType(exportableField);
					for(Object v : (Collection<?>) value){
						if(! jpaServices.isEntity(collectionType))
							return;
						
						ExportItemConfig<?> config = exportSupervisor.getNewExportConfigFor(v);
						if(null != config)
							references.add(config);
					}
				} else {
					if(! jpaServices.isEntity(exportableField.getType()))
						return;
					
					ExportItemConfig<?> config = exportSupervisor.getNewExportConfigFor(value);
					if(null != config)
						references.add(config);
				}
			}
		});
		
		return references;
	}

	@Override
	public Collection<EnclosedObjectConfig> getEnclosed() {
		final Set<EnclosedObjectConfig> enclosedObjects = new HashSet<EnclosedObjectConfig>();
		
		loopOverExportableFieldsThatAreNotNull(new Callback2<Field, Object>() {
			@Override
			public void execute(Field exportableField, Object value) throws Exception {
				if(! isEnclosed(exportableField))
					return;
				
				if(reflectionServices.isCollection(exportableField)){
					for(Object v : (Collection<?>) value)
						enclosedObjects.add(exportSupervisor.getNewEnclosedConfigFor(v));
				} else {
					if(! jpaServices.isEntity(exportableField.getType()))
						return;
					
					enclosedObjects.add(exportSupervisor.getNewEnclosedConfigFor(value));
				}
			}
		});
		
		return enclosedObjects;
	}

	public void setExportVersionAttribute(boolean exportVersionAttribute) {
		this.exportVersionAttribute = exportVersionAttribute;
	}

	public boolean isExportVersionAttribute() {
		return exportVersionAttribute;
	}

}
