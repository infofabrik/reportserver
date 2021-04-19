package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.TextDateEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextDateEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextDateEditor;

/**
 * Dto for {@link TextDateEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TextDateEditorDto extends EditorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String dateFormat;
	private  boolean dateFormat_m;
	public static final String PROPERTY_DATE_FORMAT = "dpi-textdateeditor-dateformat";

	private transient static PropertyAccessor<TextDateEditorDto, String> dateFormat_pa = new PropertyAccessor<TextDateEditorDto, String>() {
		@Override
		public void setValue(TextDateEditorDto container, String object) {
			container.setDateFormat(object);
		}

		@Override
		public String getValue(TextDateEditorDto container) {
			return container.getDateFormat();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "dateFormat";
		}

		@Override
		public void setModified(TextDateEditorDto container, boolean modified) {
			container.dateFormat_m = modified;
		}

		@Override
		public boolean isModified(TextDateEditorDto container) {
			return container.isDateFormatModified();
		}
	};


	public TextDateEditorDto() {
		super();
	}

	public String getDateFormat()  {
		if(! isDtoProxy()){
			return this.dateFormat;
		}

		if(isDateFormatModified())
			return this.dateFormat;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().dateFormat());

		return _value;
	}


	public void setDateFormat(String dateFormat)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDateFormat();

		/* set new value */
		this.dateFormat = dateFormat;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dateFormat_pa, oldValue, dateFormat, this.dateFormat_m));

		/* set indicator */
		this.dateFormat_m = true;

		this.fireObjectChangedEvent(TextDateEditorDtoPA.INSTANCE.dateFormat(), oldValue);
	}


	public boolean isDateFormatModified()  {
		return dateFormat_m;
	}


	public static PropertyAccessor<TextDateEditorDto, String> getDateFormatPropertyAccessor()  {
		return dateFormat_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TextDateEditorDto2PosoMap();
	}

	public TextDateEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(TextDateEditorDtoPA.class);
	}

	public void clearModified()  {
		this.dateFormat = null;
		this.dateFormat_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dateFormat_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dateFormat_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dateFormat_m)
			list.add(dateFormat_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dateFormat_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
