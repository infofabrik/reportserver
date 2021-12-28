package net.datenwerke.treedb.ext.client.eximport.im.dto.pa;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface ImportTreeModelPA extends PropertyAccess<ImportTreeModel> {

	public static ImportTreeModelPA INSTANCE = GWT.create(ImportTreeModelPA.class);
	
	@Path("id")
	public ModelKeyProvider<ImportTreeModel> dtoId();

	/* Properties */
	public ValueProvider<ImportTreeModel,String> id();
	public ValueProvider<ImportTreeModel,String> name();
	public ValueProvider<ImportTreeModel,String> parentId();
	public ValueProvider<ImportTreeModel,String> type();
	
	public ValueProvider<ImportTreeModel,List<ImportTreeModel>> children();
}
