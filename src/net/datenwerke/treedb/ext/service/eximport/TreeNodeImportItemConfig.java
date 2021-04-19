package net.datenwerke.treedb.ext.service.eximport;

import java.util.HashSet;
import java.util.Set;

import net.datenwerke.eximport.im.ImportItemConfig;
import net.datenwerke.eximport.im.ImportMode;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * 
 *
 */
public class TreeNodeImportItemConfig extends ImportItemConfig {

	private AbstractNode<?> parent;
	
	private Set<String> ignoredFields = new HashSet<String>();
	
	public TreeNodeImportItemConfig(String id) {
		super(id);
	}
	
	public TreeNodeImportItemConfig(String id, ImportMode importMode){
		super(id, importMode);
	}
	
	public TreeNodeImportItemConfig(String id, ImportMode importMode, Object referenceObject) {
		super(id, importMode, referenceObject);
	}

	public void setParent(AbstractNode<?> parent){
		this.parent = parent;
	}

	public AbstractNode<?> getParent(){
		return parent;
	}

	public void setIgnoredFields(Set<String> ignoredFields) {
		this.ignoredFields = ignoredFields;
	}

	public Set<String> getIgnoredFields() {
		return ignoredFields;
	}
	
	public void addIgnoredField(String field){
		this.ignoredFields.add(field);
	}
}
