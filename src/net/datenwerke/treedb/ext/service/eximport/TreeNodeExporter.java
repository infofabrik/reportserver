package net.datenwerke.treedb.ext.service.eximport;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.ex.ExportItemConfig;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.ExporterImpl;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter.ObjectExporterAdjuster;
import net.datenwerke.eximport.ex.objectexporters.EntityObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.EntityObjectExporterFactory;
import net.datenwerke.treedb.service.treedb.AbstractNode;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;

/**
 * 
 *
 */
public abstract class TreeNodeExporter extends ExporterImpl<TreeNodeExportItemConfig> {

	public static final String ACL_FIELD_NAME = "acl";
	public static final String OWNER_FIELD_NAME = "owner";
	
	private EntityObjectExporterFactory entityExporterFactory;
	
	@Inject
	public void setEntityExporter(EntityObjectExporterFactory entityExporterFactory){
		this.entityExporterFactory = entityExporterFactory;
	}
	
	@Override
	public boolean consumes(ExportItemConfig<?> config) {
		if(! super.consumes(config))
			return false;
		
		TreeNodeExportItemConfig treeConfig = (TreeNodeExportItemConfig) config;
		if(null == treeConfig.getNode())
			return false;
		
		return consumes(treeConfig.getNode());
	}
	
	@Override
	public boolean consumes(Object object) {
		if(null == object || ! (object instanceof AbstractNode))
			return false;
		
		Class<?> nodeType = object.getClass();
		for(Class<?> type : getExportableTypes())
			if(type.isAssignableFrom(nodeType))
				return true;
		
		return false;
	}


	@Override
	protected void doExport(final ExportSupervisor exportSupervisor, final TreeNodeExportItemConfig item) throws XMLStreamException {
		AbstractNode<?> node = item.getNode();
		
		/* get exporter and configure it */
		BasicObjectExporter exporter = entityExporterFactory.create(exportSupervisor, item.getId(), node);
		TreeNodeExporterConfig exporterConfig = getSpecificConfig(TreeNodeExporterConfig.class);
		if(null == exporterConfig || ! exporterConfig.containsExImporterOption(TreeNodeExImportOptions.INCLUDE_OWNER))
			exporter.addIgnoredFieldName(OWNER_FIELD_NAME);
		if(null == exporterConfig || ! exporterConfig.containsExImporterOption(TreeNodeExImportOptions.INCLUDE_SECURITY))
			exporter.addIgnoredFieldName(ACL_FIELD_NAME);
		
		/* export */
		exporter.setAdjuster(new ObjectExporterAdjuster() {
			
			@Override
			public void preProcess(BasicObjectExporter basicObjectExporter)
					throws XMLStreamException {
			}
			
			@Override
			public void configureBase() throws XMLStreamException {
			}

			@Override
			public void postProcessField(
					BasicObjectExporter basicObjectExporter,
					Field exportableField, Object value) {
				
			}

			@Override
			public void postProcess(BasicObjectExporter basicObjectExporter) throws XMLStreamException {
				postProcessExportedElement(exportSupervisor, item);
			}
		});
		
		
		exporter.export();
	}
	
	protected void postProcessExportedElement(ExportSupervisor exportSupervisor, TreeNodeExportItemConfig item) throws XMLStreamException {
		/* create reference for parent */
		AbstractNode<?> node = item.getNode();
		AbstractNode<?> parent = (AbstractNode<?>) node.getParent();
		if(null != parent ){
			if(parent instanceof HibernateProxy)
				parent = (AbstractNode<?>) ((HibernateProxy)parent).getHibernateLazyInitializer().getImplementation();
			
			if(exportSupervisor.canBeReferenced(parent)){
				exportSupervisor.beginPropertyElement("parent", AbstractNode.class);
				exportSupervisor.addReferenceTo(parent, true);
				exportSupervisor.endElement();
			}
		}
		
		/* create references for children */
		exportSupervisor.beginPropertyCollectionElement("children", AbstractNode.class, List.class);
		
		for(AbstractNode<?> child : (Collection<AbstractNode<?>>) node.getChildren()){
			if(child instanceof HibernateProxy)
				child = (AbstractNode<?>) ((HibernateProxy)child).getHibernateLazyInitializer().getImplementation();
			
			if(exportSupervisor.canBeReferenced(child)){
				exportSupervisor.beginSimpleCollectionValueElement(child);
				exportSupervisor.addReferenceTo(child, true);
				exportSupervisor.endElement();
			}
		}
		
		exportSupervisor.endElement();
	}

	@Override
	protected void doAddReferences(ExportSupervisor exportSupervisor,
			TreeNodeExportItemConfig item, Collection<ExportItemConfig<?>> references) {
		EntityObjectExporter exporter = entityExporterFactory.create(exportSupervisor, item.getId(), item.getNode());
		references.addAll(exporter.getReferences());
	}
	
	@Override
	protected void doAddEnclosed(ExportSupervisor exportSupervisor,
			TreeNodeExportItemConfig item,
			Collection<EnclosedObjectConfig> enclosed) {
		EntityObjectExporter exporter = entityExporterFactory.create(exportSupervisor, item.getId(), item.getNode());
		enclosed.addAll(exporter.getEnclosed());
	}

	@Override
	public ExportItemConfig<?> generateExportConfig(Object object) {
		if(! (object instanceof AbstractNode<?>))
			return null;
		
		Class<?> objectType = object.getClass();
		for(Class<?> type : getExportableTypes())
			if(type.isAssignableFrom(objectType))
				return new TreeNodeExportItemConfig((AbstractNode<?>) object);
		
		return null;
	}


	@Override
	public ExportItemConfig<?> getConfigFor(Object value){
		if(! (value instanceof AbstractNode<?>))
			return null;
		
		AbstractNode<?> node = (AbstractNode<?>) value;
		for(TreeNodeExportItemConfig config : getConfigItems())
			if(node.getId().equals(config.getNode().getId()))
				return config;
		
		return null;
	}

	
	protected abstract Class<? extends AbstractNode<?>> getTreeType();
	

	protected abstract Class<?>[] getExportableTypes();

}
