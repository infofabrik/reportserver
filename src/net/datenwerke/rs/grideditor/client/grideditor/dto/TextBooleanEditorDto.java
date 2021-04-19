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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.TextBooleanEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.TextBooleanEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextBooleanEditor;

/**
 * Dto for {@link TextBooleanEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TextBooleanEditorDto extends EditorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String falseText;
	private  boolean falseText_m;
	public static final String PROPERTY_FALSE_TEXT = "dpi-textbooleaneditor-falsetext";

	private transient static PropertyAccessor<TextBooleanEditorDto, String> falseText_pa = new PropertyAccessor<TextBooleanEditorDto, String>() {
		@Override
		public void setValue(TextBooleanEditorDto container, String object) {
			container.setFalseText(object);
		}

		@Override
		public String getValue(TextBooleanEditorDto container) {
			return container.getFalseText();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "falseText";
		}

		@Override
		public void setModified(TextBooleanEditorDto container, boolean modified) {
			container.falseText_m = modified;
		}

		@Override
		public boolean isModified(TextBooleanEditorDto container) {
			return container.isFalseTextModified();
		}
	};

	private String trueText;
	private  boolean trueText_m;
	public static final String PROPERTY_TRUE_TEXT = "dpi-textbooleaneditor-truetext";

	private transient static PropertyAccessor<TextBooleanEditorDto, String> trueText_pa = new PropertyAccessor<TextBooleanEditorDto, String>() {
		@Override
		public void setValue(TextBooleanEditorDto container, String object) {
			container.setTrueText(object);
		}

		@Override
		public String getValue(TextBooleanEditorDto container) {
			return container.getTrueText();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "trueText";
		}

		@Override
		public void setModified(TextBooleanEditorDto container, boolean modified) {
			container.trueText_m = modified;
		}

		@Override
		public boolean isModified(TextBooleanEditorDto container) {
			return container.isTrueTextModified();
		}
	};


	public TextBooleanEditorDto() {
		super();
	}

	public String getFalseText()  {
		if(! isDtoProxy()){
			return this.falseText;
		}

		if(isFalseTextModified())
			return this.falseText;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().falseText());

		return _value;
	}


	public void setFalseText(String falseText)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFalseText();

		/* set new value */
		this.falseText = falseText;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(falseText_pa, oldValue, falseText, this.falseText_m));

		/* set indicator */
		this.falseText_m = true;

		this.fireObjectChangedEvent(TextBooleanEditorDtoPA.INSTANCE.falseText(), oldValue);
	}


	public boolean isFalseTextModified()  {
		return falseText_m;
	}


	public static PropertyAccessor<TextBooleanEditorDto, String> getFalseTextPropertyAccessor()  {
		return falseText_pa;
	}


	public String getTrueText()  {
		if(! isDtoProxy()){
			return this.trueText;
		}

		if(isTrueTextModified())
			return this.trueText;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().trueText());

		return _value;
	}


	public void setTrueText(String trueText)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTrueText();

		/* set new value */
		this.trueText = trueText;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(trueText_pa, oldValue, trueText, this.trueText_m));

		/* set indicator */
		this.trueText_m = true;

		this.fireObjectChangedEvent(TextBooleanEditorDtoPA.INSTANCE.trueText(), oldValue);
	}


	public boolean isTrueTextModified()  {
		return trueText_m;
	}


	public static PropertyAccessor<TextBooleanEditorDto, String> getTrueTextPropertyAccessor()  {
		return trueText_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TextBooleanEditorDto2PosoMap();
	}

	public TextBooleanEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(TextBooleanEditorDtoPA.class);
	}

	public void clearModified()  {
		this.falseText = null;
		this.falseText_m = false;
		this.trueText = null;
		this.trueText_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(falseText_m)
			return true;
		if(trueText_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(falseText_pa);
		list.add(trueText_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(falseText_m)
			list.add(falseText_pa);
		if(trueText_m)
			list.add(trueText_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(falseText_pa);
			list.add(trueText_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
