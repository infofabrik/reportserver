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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.FloatSelectionListEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.FloatSelectionListEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.FloatSelectionListEditor;

/**
 * Dto for {@link FloatSelectionListEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FloatSelectionListEditorDto extends SelectionListEditorDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Map<String, Float> valueMap;
	private  boolean valueMap_m;
	public static final String PROPERTY_VALUE_MAP = "dpi-floatselectionlisteditor-valuemap";

	private transient static PropertyAccessor<FloatSelectionListEditorDto, Map<String, Float>> valueMap_pa = new PropertyAccessor<FloatSelectionListEditorDto, Map<String, Float>>() {
		@Override
		public void setValue(FloatSelectionListEditorDto container, Map<String, Float> object) {
			container.setValueMap(object);
		}

		@Override
		public Map<String, Float> getValue(FloatSelectionListEditorDto container) {
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
		public void setModified(FloatSelectionListEditorDto container, boolean modified) {
			container.valueMap_m = modified;
		}

		@Override
		public boolean isModified(FloatSelectionListEditorDto container) {
			return container.isValueMapModified();
		}
	};

	private List<Float> values;
	private  boolean values_m;
	public static final String PROPERTY_VALUES = "dpi-floatselectionlisteditor-values";

	private transient static PropertyAccessor<FloatSelectionListEditorDto, List<Float>> values_pa = new PropertyAccessor<FloatSelectionListEditorDto, List<Float>>() {
		@Override
		public void setValue(FloatSelectionListEditorDto container, List<Float> object) {
			container.setValues(object);
		}

		@Override
		public List<Float> getValue(FloatSelectionListEditorDto container) {
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
		public void setModified(FloatSelectionListEditorDto container, boolean modified) {
			container.values_m = modified;
		}

		@Override
		public boolean isModified(FloatSelectionListEditorDto container) {
			return container.isValuesModified();
		}
	};


	public FloatSelectionListEditorDto() {
		super();
	}

	public Map<String, Float> getValueMap()  {
		if(! isDtoProxy()){
			return this.valueMap;
		}

		if(isValueMapModified())
			return this.valueMap;

		if(! GWT.isClient())
			return null;

		Map<String, Float> _value = dtoManager.getProperty(this, instantiatePropertyAccess().valueMap());

		return _value;
	}


	public void setValueMap(Map<String, Float> valueMap)  {
		/* old value */
		Map<String, Float> oldValue = null;
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

		this.fireObjectChangedEvent(FloatSelectionListEditorDtoPA.INSTANCE.valueMap(), oldValue);
	}


	public boolean isValueMapModified()  {
		return valueMap_m;
	}


	public static PropertyAccessor<FloatSelectionListEditorDto, Map<String, Float>> getValueMapPropertyAccessor()  {
		return valueMap_pa;
	}


	public List<Float> getValues()  {
		if(! isDtoProxy()){
			List<Float> _currentValue = this.values;
			if(null == _currentValue)
				this.values = new ArrayList<Float>();

			return this.values;
		}

		if(isValuesModified())
			return this.values;

		if(! GWT.isClient())
			return null;

		List<Float> _value = dtoManager.getProperty(this, instantiatePropertyAccess().values());

		return _value;
	}


	public void setValues(List<Float> values)  {
		/* old value */
		List<Float> oldValue = null;
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

		this.fireObjectChangedEvent(FloatSelectionListEditorDtoPA.INSTANCE.values(), oldValue);
	}


	public boolean isValuesModified()  {
		return values_m;
	}


	public static PropertyAccessor<FloatSelectionListEditorDto, List<Float>> getValuesPropertyAccessor()  {
		return values_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FloatSelectionListEditorDto2PosoMap();
	}

	public FloatSelectionListEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(FloatSelectionListEditorDtoPA.class);
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
