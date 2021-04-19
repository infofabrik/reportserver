package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.SelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.IntSelectionListEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.IntSelectionListEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntSelectionListEditor;

/**
 * Dto for {@link IntSelectionListEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class IntSelectionListEditorDto extends SelectionListEditorDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Map<String, Integer> valueMap;
	private  boolean valueMap_m;
	public static final String PROPERTY_VALUE_MAP = "dpi-intselectionlisteditor-valuemap";

	private transient static PropertyAccessor<IntSelectionListEditorDto, Map<String, Integer>> valueMap_pa = new PropertyAccessor<IntSelectionListEditorDto, Map<String, Integer>>() {
		@Override
		public void setValue(IntSelectionListEditorDto container, Map<String, Integer> object) {
			container.setValueMap(object);
		}

		@Override
		public Map<String, Integer> getValue(IntSelectionListEditorDto container) {
			return container.getValueMap();
		}

		@Override
		public Class<?> getType() {
			return Map.class;
		}

		@Override
		public String getPath() {
			return "valueMap";
		}

		@Override
		public void setModified(IntSelectionListEditorDto container, boolean modified) {
			container.valueMap_m = modified;
		}

		@Override
		public boolean isModified(IntSelectionListEditorDto container) {
			return container.isValueMapModified();
		}
	};

	private List<Integer> values;
	private  boolean values_m;
	public static final String PROPERTY_VALUES = "dpi-intselectionlisteditor-values";

	private transient static PropertyAccessor<IntSelectionListEditorDto, List<Integer>> values_pa = new PropertyAccessor<IntSelectionListEditorDto, List<Integer>>() {
		@Override
		public void setValue(IntSelectionListEditorDto container, List<Integer> object) {
			container.setValues(object);
		}

		@Override
		public List<Integer> getValue(IntSelectionListEditorDto container) {
			return container.getValues();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "values";
		}

		@Override
		public void setModified(IntSelectionListEditorDto container, boolean modified) {
			container.values_m = modified;
		}

		@Override
		public boolean isModified(IntSelectionListEditorDto container) {
			return container.isValuesModified();
		}
	};


	public IntSelectionListEditorDto() {
		super();
	}

	public Map<String, Integer> getValueMap()  {
		if(! isDtoProxy()){
			return this.valueMap;
		}

		if(isValueMapModified())
			return this.valueMap;

		if(! GWT.isClient())
			return null;

		Map<String, Integer> _value = dtoManager.getProperty(this, instantiatePropertyAccess().valueMap());

		return _value;
	}


	public void setValueMap(Map<String, Integer> valueMap)  {
		/* old value */
		Map<String, Integer> oldValue = null;
		if(GWT.isClient())
			oldValue = getValueMap();

		/* set new value */
		this.valueMap = valueMap;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(valueMap_pa, oldValue, valueMap, this.valueMap_m));

		/* set indicator */
		this.valueMap_m = true;

		this.fireObjectChangedEvent(IntSelectionListEditorDtoPA.INSTANCE.valueMap(), oldValue);
	}


	public boolean isValueMapModified()  {
		return valueMap_m;
	}


	public static PropertyAccessor<IntSelectionListEditorDto, Map<String, Integer>> getValueMapPropertyAccessor()  {
		return valueMap_pa;
	}


	public List<Integer> getValues()  {
		if(! isDtoProxy()){
			List<Integer> _currentValue = this.values;
			if(null == _currentValue)
				this.values = new ArrayList<Integer>();

			return this.values;
		}

		if(isValuesModified())
			return this.values;

		if(! GWT.isClient())
			return null;

		List<Integer> _value = dtoManager.getProperty(this, instantiatePropertyAccess().values());

		return _value;
	}


	public void setValues(List<Integer> values)  {
		/* old value */
		List<Integer> oldValue = null;
		if(GWT.isClient())
			oldValue = getValues();

		/* set new value */
		this.values = values;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(values_pa, oldValue, values, this.values_m));

		/* set indicator */
		this.values_m = true;

		this.fireObjectChangedEvent(IntSelectionListEditorDtoPA.INSTANCE.values(), oldValue);
	}


	public boolean isValuesModified()  {
		return values_m;
	}


	public static PropertyAccessor<IntSelectionListEditorDto, List<Integer>> getValuesPropertyAccessor()  {
		return values_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new IntSelectionListEditorDto2PosoMap();
	}

	public IntSelectionListEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(IntSelectionListEditorDtoPA.class);
	}

	public void clearModified()  {
		this.valueMap = null;
		this.valueMap_m = false;
		this.values = null;
		this.values_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(valueMap_m)
			return true;
		if(values_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(valueMap_pa);
		list.add(values_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(valueMap_m)
			list.add(valueMap_pa);
		if(values_m)
			list.add(values_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(valueMap_pa);
			list.add(values_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
