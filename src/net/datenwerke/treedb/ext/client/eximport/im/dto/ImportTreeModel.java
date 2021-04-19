package net.datenwerke.treedb.ext.client.eximport.im.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.data.shared.TreeStore.TreeNode;


public class ImportTreeModel implements Serializable, TreeNode<ImportTreeModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -455692463561631529L;

	private String id;
	private String name;
	private String parentId;
	private String type;

	private List<ImportTreeModel> children = new ArrayList<ImportTreeModel>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setChildren(List<ImportTreeModel> children) {
		if(null == children)
			children = new ArrayList<ImportTreeModel>();
		this.children = children;
	}
	@Override
	public List<ImportTreeModel> getChildren() {
		return children;
	}
	public void addChild(ImportTreeModel model) {
		children.add(model);
	}
	@Override
	public ImportTreeModel getData() {
		return this;
	}
	
	
}
