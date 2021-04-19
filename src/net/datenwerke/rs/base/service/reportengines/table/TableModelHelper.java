package net.datenwerke.rs.base.service.reportengines.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.utils.jpa.EntityUtils;

/**
 * Provides helper methods to work with {@link RSTableModel}.
 * 
 *
 */
public class TableModelHelper {

	protected final EntityUtils entityUtils;
	
	@Inject
	public TableModelHelper(EntityUtils entityUtils) {
		this.entityUtils = entityUtils;
	}


	public TableDefinition tableDefinitionFromEntityType(Object entity) {
		return tableDefinitionFromEntityType(entity.getClass());
	}

	public TableDefinition tableDefinitionFromEntityType(Class<?> type) {
		ArrayList<String> columnNames = new ArrayList<String>();
		List<Class<?>> columnTypes = new ArrayList<Class<?>>();
		
		for(Field f : entityUtils.getPersistantFields(type)){
			columnTypes.add(f.getType());
			columnNames.add(f.getName());
		}
		
		return new TableDefinition(columnNames, columnTypes);
	}
	
	
}
