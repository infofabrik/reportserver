package net.datenwerke.treedb.ext.service.eximport;

import net.datenwerke.eximport.im.ImporterImpl;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporter;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporterFactory;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * 
 *
 */
public abstract class TreeNodeImporter extends ImporterImpl<TreeNodeImportItemConfig> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private BasicObjectImporterFactory objectImporterFactory;

	@Inject
	public void setObjectImporterFactory(BasicObjectImporterFactory objectImporterFactory){
		this.objectImporterFactory = objectImporterFactory;
	}
	
	@Override
	protected void doImportCreateMode(final TreeNodeImportItemConfig itemConfig) {
		/* create importer */
		ExportedItem exportedItem = importSupervisor.getExportedItemFor(itemConfig);
		BasicObjectImporter importer = objectImporterFactory.create(importSupervisor, exportedItem);
		
		/* configure importer */
		TreeNodeImporterConfig config = getSpecificConfig(TreeNodeImporterConfig.class);
		if(null == config || ! config.containsExImporterOption(TreeNodeExImportOptions.INCLUDE_OWNER))
			importer.addIgnoredFieldNames(TreeNodeExporter.OWNER_FIELD_NAME);
		if(null == config || ! config.containsExImporterOption(TreeNodeExImportOptions.INCLUDE_SECURITY))
			importer.addIgnoredFieldNames(TreeNodeExporter.ACL_FIELD_NAME);
		
		if(null != itemConfig.getIgnoredFields())
			importer.addIgnoredFieldNames(itemConfig.getIgnoredFields());
		
		configureObjectImporter(itemConfig, importer);
		
		/* import node */
		AbstractNode<?> node = (AbstractNode<?>) importer.importObject();
		
		/* set parent */
		if(null != itemConfig.getParent())
			addParent(node, itemConfig.getParent());
		
		finishUpImportCreateMode(itemConfig, node);
	}

	protected void configureObjectImporter(TreeNodeImportItemConfig itemConfig,
			BasicObjectImporter importer) {
		
	}

	protected void finishUpImportCreateMode(TreeNodeImportItemConfig itemConfig,
			AbstractNode<?> node) {
		
	}

	@SuppressWarnings("unchecked")
	protected void addParent(AbstractNode<?> node, AbstractNode parent) {
		if(parent instanceof HibernateProxy)
			parent = (AbstractNode<?>)((HibernateProxy)parent).getHibernateLazyInitializer().getImplementation(); 
		parent.addChild(node);
	}

	@SuppressWarnings("unchecked")
	protected void persist(AbstractNode<?> node) {
		getTreeDBManager().persist(node);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean postProcess(String id, Object object, boolean enclosed) {
		try{
			AbstractNode node = (AbstractNode) object;
			if(null == node.getId())
				persist(node);
			return true;
		} catch(Exception e){
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	@Override
	protected void doImportMergeMode(TreeNodeImportItemConfig itemConfig) {
	}

	@Override
	protected void doImportReferenceMode(TreeNodeImportItemConfig itemConfig) {
		importSupervisor.registerExternalReference(itemConfig.getId(), itemConfig.getReferenceObject());
	}

	protected abstract TreeDBManager getTreeDBManager();
}
