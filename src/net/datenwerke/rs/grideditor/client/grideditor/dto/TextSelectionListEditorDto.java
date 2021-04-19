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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.TextSelectionListEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextSelectionListEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextSelectionListEditor;

/**
 * Dto for {@link TextSelectionListEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TextSelectionListEditorDto extends SelectionListEditorDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Map<String, String> valueMap;
	private  boolean valueMap_m;
	public static final String PROPERTY_VALUE_MAP = "dpi-textselectionlisteditor-valuemap";

	private transient static PropertyAccessor<TextSelectionListEditorDto, Map<String, String>> valueMap_pa = new PropertyAccessor<TextSelectionListEditorDto, Map<String, String>>() {
		@Override
		public void setValue(TextSelectionListEditorDto container, Map<String, String> object) {
			container.setValueMap(object);
		}

		@Override
		public Map<String, String> getValue(TextSelectionListEditorDto container) {
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
		public void setModified(TextSelectionListEditorDto container, boolean modified) {
			container.valueMap_m = modified;
		}

		@Override
		public boolean isModified(TextSelectionListEditorDto container) {
			return container.isValueMapModified();
		}
	};

	private List<String> values;
	private  boolean values_m;
	public static final String PROPERTY_VALUES = "dpi-textselectionlisteditor-values";

	private transient static PropertyAccessor<TextSelectionListEditorDto, List<String>> values_pa = new PropertyAccessor<TextSelectionListEditorDto, List<String>>() {
		@Override
		public void setValue(TextSelectionListEditorDto container, List<String> object) {
			container.setValues(object);
		}

		@Override
		public List<String> getValue(TextSelectionListEditorDto container) {
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
		public void setModified(TextSelectionListEditorDto container, boolean modified) {
			container.values_m = modified;
		}

		@Override
		public boolean isModified(TextSelectionListEditorDto container) {
			return container.isValuesModified();
		}
	};


	public TextSelectionListEditorDto() {
		super();
	}

	public Map<String, String> getValueMap()  {
		if(! isDtoProxy()){
			return this.valueMap;
		}

		if(isValueMapModified())
			return this.valueMap;

		if(! GWT.isClient())
			return null;

		Map<String, String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().valueMap());

		return _value;
	}


	public void setValueMap(Map<String, String> valueMap)  {
		/* old value */
		Map<String, String> oldValue = null;
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

		this.fireObjectChangedEvent(TextSelectionListEditorDtoPA.INSTANCE.valueMap(), oldValue);
	}


	public boolean isValueMapModified()  {
		return valueMap_m;
	}


	public static PropertyAccessor<TextSelectionListEditorDto, Map<String, String>> getValueMapPropertyAccessor()  {
		return valueMap_pa;
	}


	public List<String> getValues()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.values;
			if(null == _currentValue)
				this.values = new ArrayList<String>();

			return this.values;
		}

		if(isValuesModified())
			return this.values;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().values());

		return _value;
	}


	public void setValues(List<String> values)  {
		/* old value */
		List<String> oldValue = null;
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

		this.fireObjectChangedEvent(TextSelectionListEditorDtoPA.INSTANCE.values(), oldValue);
	}


	public boolean isValuesModified()  {
		return values_m;
	}


	public static PropertyAccessor<TextSelectionListEditorDto, List<String>> getValuesPropertyAccessor()  {
		return values_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TextSelectionListEditorDto2PosoMap();
	}

	public TextSelectionListEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(TextSelectionListEditorDtoPA.class);
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
