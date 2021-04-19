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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.DoubleSelectionListEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.DoubleSelectionListEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DoubleSelectionListEditor;

/**
 * Dto for {@link DoubleSelectionListEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DoubleSelectionListEditorDto extends SelectionListEditorDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Map<String, Double> valueMap;
	private  boolean valueMap_m;
	public static final String PROPERTY_VALUE_MAP = "dpi-doubleselectionlisteditor-valuemap";

	private transient static PropertyAccessor<DoubleSelectionListEditorDto, Map<String, Double>> valueMap_pa = new PropertyAccessor<DoubleSelectionListEditorDto, Map<String, Double>>() {
		@Override
		public void setValue(DoubleSelectionListEditorDto container, Map<String, Double> object) {
			container.setValueMap(object);
		}

		@Override
		public Map<String, Double> getValue(DoubleSelectionListEditorDto container) {
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
		public void setModified(DoubleSelectionListEditorDto container, boolean modified) {
			container.valueMap_m = modified;
		}

		@Override
		public boolean isModified(DoubleSelectionListEditorDto container) {
			return container.isValueMapModified();
		}
	};

	private List<Double> values;
	private  boolean values_m;
	public static final String PROPERTY_VALUES = "dpi-doubleselectionlisteditor-values";

	private transient static PropertyAccessor<DoubleSelectionListEditorDto, List<Double>> values_pa = new PropertyAccessor<DoubleSelectionListEditorDto, List<Double>>() {
		@Override
		public void setValue(DoubleSelectionListEditorDto container, List<Double> object) {
			container.setValues(object);
		}

		@Override
		public List<Double> getValue(DoubleSelectionListEditorDto container) {
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
		public void setModified(DoubleSelectionListEditorDto container, boolean modified) {
			container.values_m = modified;
		}

		@Override
		public boolean isModified(DoubleSelectionListEditorDto container) {
			return container.isValuesModified();
		}
	};


	public DoubleSelectionListEditorDto() {
		super();
	}

	public Map<String, Double> getValueMap()  {
		if(! isDtoProxy()){
			return this.valueMap;
		}

		if(isValueMapModified())
			return this.valueMap;

		if(! GWT.isClient())
			return null;

		Map<String, Double> _value = dtoManager.getProperty(this, instantiatePropertyAccess().valueMap());

		return _value;
	}


	public void setValueMap(Map<String, Double> valueMap)  {
		/* old value */
		Map<String, Double> oldValue = null;
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

		this.fireObjectChangedEvent(DoubleSelectionListEditorDtoPA.INSTANCE.valueMap(), oldValue);
	}


	public boolean isValueMapModified()  {
		return valueMap_m;
	}


	public static PropertyAccessor<DoubleSelectionListEditorDto, Map<String, Double>> getValueMapPropertyAccessor()  {
		return valueMap_pa;
	}


	public List<Double> getValues()  {
		if(! isDtoProxy()){
			List<Double> _currentValue = this.values;
			if(null == _currentValue)
				this.values = new ArrayList<Double>();

			return this.values;
		}

		if(isValuesModified())
			return this.values;

		if(! GWT.isClient())
			return null;

		List<Double> _value = dtoManager.getProperty(this, instantiatePropertyAccess().values());

		return _value;
	}


	public void setValues(List<Double> values)  {
		/* old value */
		List<Double> oldValue = null;
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

		this.fireObjectChangedEvent(DoubleSelectionListEditorDtoPA.INSTANCE.values(), oldValue);
	}


	public boolean isValuesModified()  {
		return values_m;
	}


	public static PropertyAccessor<DoubleSelectionListEditorDto, List<Double>> getValuesPropertyAccessor()  {
		return values_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DoubleSelectionListEditorDto2PosoMap();
	}

	public DoubleSelectionListEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(DoubleSelectionListEditorDtoPA.class);
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
