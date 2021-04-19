package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.TextAreaEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextAreaEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextAreaEditor;

/**
 * Dto for {@link TextAreaEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TextAreaEditorDto extends EditorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-textareaeditor-height";

	private transient static PropertyAccessor<TextAreaEditorDto, Integer> height_pa = new PropertyAccessor<TextAreaEditorDto, Integer>() {
		@Override
		public void setValue(TextAreaEditorDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(TextAreaEditorDto container) {
			return container.getHeight();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "height";
		}

		@Override
		public void setModified(TextAreaEditorDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(TextAreaEditorDto container) {
			return container.isHeightModified();
		}
	};

	private int width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-textareaeditor-width";

	private transient static PropertyAccessor<TextAreaEditorDto, Integer> width_pa = new PropertyAccessor<TextAreaEditorDto, Integer>() {
		@Override
		public void setValue(TextAreaEditorDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(TextAreaEditorDto container) {
			return container.getWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "width";
		}

		@Override
		public void setModified(TextAreaEditorDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(TextAreaEditorDto container) {
			return container.isWidthModified();
		}
	};


	public TextAreaEditorDto() {
		super();
	}

	public int getHeight()  {
		if(! isDtoProxy()){
			return this.height;
		}

		if(isHeightModified())
			return this.height;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().height());

		return _value;
	}


	public void setHeight(int height)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getHeight();

		/* set new value */
		this.height = height;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(height_pa, oldValue, height, this.height_m));

		/* set indicator */
		this.height_m = true;

		this.fireObjectChangedEvent(TextAreaEditorDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<TextAreaEditorDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
	}


	public int getWidth()  {
		if(! isDtoProxy()){
			return this.width;
		}

		if(isWidthModified())
			return this.width;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().width());

		return _value;
	}


	public void setWidth(int width)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getWidth();

		/* set new value */
		this.width = width;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(width_pa, oldValue, width, this.width_m));

		/* set indicator */
		this.width_m = true;

		this.fireObjectChangedEvent(TextAreaEditorDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<TextAreaEditorDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TextAreaEditorDto2PosoMap();
	}

	public TextAreaEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(TextAreaEditorDtoPA.class);
	}

	public void clearModified()  {
		this.height = 0;
		this.height_m = false;
		this.width = 0;
		this.width_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(height_m)
			return true;
		if(width_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(height_pa);
		list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(height_m)
			list.add(height_pa);
		if(width_m)
			list.add(width_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(height_pa);
			list.add(width_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
