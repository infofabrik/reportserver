package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.TableDefinitionDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.TableDefinitionDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;

/**
 * Dto for {@link TableDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TableDefinitionDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private HashMap<String, Integer> columnIndex;
	private  boolean columnIndex_m;
	public static final String PROPERTY_COLUMN_INDEX = "dpi-tabledefinition-columnindex";

	private transient static PropertyAccessor<TableDefinitionDto, HashMap<String, Integer>> columnIndex_pa = new PropertyAccessor<TableDefinitionDto, HashMap<String, Integer>>() {
		@Override
		public void setValue(TableDefinitionDto container, HashMap<String, Integer> object) {
			container.setColumnIndex(object);
		}

		@Override
		public HashMap<String, Integer> getValue(TableDefinitionDto container) {
			return container.getColumnIndex();
		}

		@Override
		public Class<?> getType() {
			return HashMap.class;
		}

		@Override
		public String getPath() {
			return "columnIndex";
		}

		@Override
		public void setModified(TableDefinitionDto container, boolean modified) {
			container.columnIndex_m = modified;
		}

		@Override
		public boolean isModified(TableDefinitionDto container) {
			return container.isColumnIndexModified();
		}
	};

	private List<String> columnNames;
	private  boolean columnNames_m;
	public static final String PROPERTY_COLUMN_NAMES = "dpi-tabledefinition-columnnames";

	private transient static PropertyAccessor<TableDefinitionDto, List<String>> columnNames_pa = new PropertyAccessor<TableDefinitionDto, List<String>>() {
		@Override
		public void setValue(TableDefinitionDto container, List<String> object) {
			container.setColumnNames(object);
		}

		@Override
		public List<String> getValue(TableDefinitionDto container) {
			return container.getColumnNames();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "columnNames";
		}

		@Override
		public void setModified(TableDefinitionDto container, boolean modified) {
			container.columnNames_m = modified;
		}

		@Override
		public boolean isModified(TableDefinitionDto container) {
			return container.isColumnNamesModified();
		}
	};

	private List<Integer> displaySizes;
	private  boolean displaySizes_m;
	public static final String PROPERTY_DISPLAY_SIZES = "dpi-tabledefinition-displaysizes";

	private transient static PropertyAccessor<TableDefinitionDto, List<Integer>> displaySizes_pa = new PropertyAccessor<TableDefinitionDto, List<Integer>>() {
		@Override
		public void setValue(TableDefinitionDto container, List<Integer> object) {
			container.setDisplaySizes(object);
		}

		@Override
		public List<Integer> getValue(TableDefinitionDto container) {
			return container.getDisplaySizes();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "displaySizes";
		}

		@Override
		public void setModified(TableDefinitionDto container, boolean modified) {
			container.displaySizes_m = modified;
		}

		@Override
		public boolean isModified(TableDefinitionDto container) {
			return container.isDisplaySizesModified();
		}
	};

	private List<Integer> sqlColumnTypes;
	private  boolean sqlColumnTypes_m;
	public static final String PROPERTY_SQL_COLUMN_TYPES = "dpi-tabledefinition-sqlcolumntypes";

	private transient static PropertyAccessor<TableDefinitionDto, List<Integer>> sqlColumnTypes_pa = new PropertyAccessor<TableDefinitionDto, List<Integer>>() {
		@Override
		public void setValue(TableDefinitionDto container, List<Integer> object) {
			container.setSqlColumnTypes(object);
		}

		@Override
		public List<Integer> getValue(TableDefinitionDto container) {
			return container.getSqlColumnTypes();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "sqlColumnTypes";
		}

		@Override
		public void setModified(TableDefinitionDto container, boolean modified) {
			container.sqlColumnTypes_m = modified;
		}

		@Override
		public boolean isModified(TableDefinitionDto container) {
			return container.isSqlColumnTypesModified();
		}
	};


	public TableDefinitionDto() {
		super();
	}

	public HashMap<String, Integer> getColumnIndex()  {
		if(! isDtoProxy()){
			return this.columnIndex;
		}

		if(isColumnIndexModified())
			return this.columnIndex;

		if(! GWT.isClient())
			return null;

		HashMap<String, Integer> _value = dtoManager.getProperty(this, instantiatePropertyAccess().columnIndex());

		return _value;
	}


	public void setColumnIndex(HashMap<String, Integer> columnIndex)  {
		/* old value */
		HashMap<String, Integer> oldValue = null;
		if(GWT.isClient())
			oldValue = getColumnIndex();

		/* set new value */
		this.columnIndex = columnIndex;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(columnIndex_pa, oldValue, columnIndex, this.columnIndex_m));

		/* set indicator */
		this.columnIndex_m = true;

		this.fireObjectChangedEvent(TableDefinitionDtoPA.INSTANCE.columnIndex(), oldValue);
	}


	public boolean isColumnIndexModified()  {
		return columnIndex_m;
	}


	public static PropertyAccessor<TableDefinitionDto, HashMap<String, Integer>> getColumnIndexPropertyAccessor()  {
		return columnIndex_pa;
	}


	public List<String> getColumnNames()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.columnNames;
			if(null == _currentValue)
				this.columnNames = new ArrayList<String>();

			return this.columnNames;
		}

		if(isColumnNamesModified())
			return this.columnNames;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().columnNames());

		return _value;
	}


	public void setColumnNames(List<String> columnNames)  {
		/* old value */
		List<String> oldValue = null;
		if(GWT.isClient())
			oldValue = getColumnNames();

		/* set new value */
		this.columnNames = columnNames;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(columnNames_pa, oldValue, columnNames, this.columnNames_m));

		/* set indicator */
		this.columnNames_m = true;

		this.fireObjectChangedEvent(TableDefinitionDtoPA.INSTANCE.columnNames(), oldValue);
	}


	public boolean isColumnNamesModified()  {
		return columnNames_m;
	}


	public static PropertyAccessor<TableDefinitionDto, List<String>> getColumnNamesPropertyAccessor()  {
		return columnNames_pa;
	}


	public List<Integer> getDisplaySizes()  {
		if(! isDtoProxy()){
			List<Integer> _currentValue = this.displaySizes;
			if(null == _currentValue)
				this.displaySizes = new ArrayList<Integer>();

			return this.displaySizes;
		}

		if(isDisplaySizesModified())
			return this.displaySizes;

		if(! GWT.isClient())
			return null;

		List<Integer> _value = dtoManager.getProperty(this, instantiatePropertyAccess().displaySizes());

		return _value;
	}


	public void setDisplaySizes(List<Integer> displaySizes)  {
		/* old value */
		List<Integer> oldValue = null;
		if(GWT.isClient())
			oldValue = getDisplaySizes();

		/* set new value */
		this.displaySizes = displaySizes;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(displaySizes_pa, oldValue, displaySizes, this.displaySizes_m));

		/* set indicator */
		this.displaySizes_m = true;

		this.fireObjectChangedEvent(TableDefinitionDtoPA.INSTANCE.displaySizes(), oldValue);
	}


	public boolean isDisplaySizesModified()  {
		return displaySizes_m;
	}


	public static PropertyAccessor<TableDefinitionDto, List<Integer>> getDisplaySizesPropertyAccessor()  {
		return displaySizes_pa;
	}


	public List<Integer> getSqlColumnTypes()  {
		if(! isDtoProxy()){
			List<Integer> _currentValue = this.sqlColumnTypes;
			if(null == _currentValue)
				this.sqlColumnTypes = new ArrayList<Integer>();

			return this.sqlColumnTypes;
		}

		if(isSqlColumnTypesModified())
			return this.sqlColumnTypes;

		if(! GWT.isClient())
			return null;

		List<Integer> _value = dtoManager.getProperty(this, instantiatePropertyAccess().sqlColumnTypes());

		return _value;
	}


	public void setSqlColumnTypes(List<Integer> sqlColumnTypes)  {
		/* old value */
		List<Integer> oldValue = null;
		if(GWT.isClient())
			oldValue = getSqlColumnTypes();

		/* set new value */
		this.sqlColumnTypes = sqlColumnTypes;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(sqlColumnTypes_pa, oldValue, sqlColumnTypes, this.sqlColumnTypes_m));

		/* set indicator */
		this.sqlColumnTypes_m = true;

		this.fireObjectChangedEvent(TableDefinitionDtoPA.INSTANCE.sqlColumnTypes(), oldValue);
	}


	public boolean isSqlColumnTypesModified()  {
		return sqlColumnTypes_m;
	}


	public static PropertyAccessor<TableDefinitionDto, List<Integer>> getSqlColumnTypesPropertyAccessor()  {
		return sqlColumnTypes_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TableDefinitionDto2PosoMap();
	}

	public TableDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(TableDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.columnIndex = null;
		this.columnIndex_m = false;
		this.columnNames = null;
		this.columnNames_m = false;
		this.displaySizes = null;
		this.displaySizes_m = false;
		this.sqlColumnTypes = null;
		this.sqlColumnTypes_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(columnIndex_m)
			return true;
		if(columnNames_m)
			return true;
		if(displaySizes_m)
			return true;
		if(sqlColumnTypes_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(columnIndex_pa);
		list.add(columnNames_pa);
		list.add(displaySizes_pa);
		list.add(sqlColumnTypes_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(columnIndex_m)
			list.add(columnIndex_pa);
		if(columnNames_m)
			list.add(columnNames_pa);
		if(displaySizes_m)
			list.add(displaySizes_pa);
		if(sqlColumnTypes_m)
			list.add(sqlColumnTypes_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(columnIndex_pa);
			list.add(columnNames_pa);
			list.add(displaySizes_pa);
			list.add(sqlColumnTypes_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
