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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.IntBooleanEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.IntBooleanEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntBooleanEditor;

/**
 * Dto for {@link IntBooleanEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class IntBooleanEditorDto extends EditorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int falseInt;
	private  boolean falseInt_m;
	public static final String PROPERTY_FALSE_INT = "dpi-intbooleaneditor-falseint";

	private transient static PropertyAccessor<IntBooleanEditorDto, Integer> falseInt_pa = new PropertyAccessor<IntBooleanEditorDto, Integer>() {
		@Override
		public void setValue(IntBooleanEditorDto container, Integer object) {
			container.setFalseInt(object);
		}

		@Override
		public Integer getValue(IntBooleanEditorDto container) {
			return container.getFalseInt();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "falseInt";
		}

		@Override
		public void setModified(IntBooleanEditorDto container, boolean modified) {
			container.falseInt_m = modified;
		}

		@Override
		public boolean isModified(IntBooleanEditorDto container) {
			return container.isFalseIntModified();
		}
	};

	private int trueInt;
	private  boolean trueInt_m;
	public static final String PROPERTY_TRUE_INT = "dpi-intbooleaneditor-trueint";

	private transient static PropertyAccessor<IntBooleanEditorDto, Integer> trueInt_pa = new PropertyAccessor<IntBooleanEditorDto, Integer>() {
		@Override
		public void setValue(IntBooleanEditorDto container, Integer object) {
			container.setTrueInt(object);
		}

		@Override
		public Integer getValue(IntBooleanEditorDto container) {
			return container.getTrueInt();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "trueInt";
		}

		@Override
		public void setModified(IntBooleanEditorDto container, boolean modified) {
			container.trueInt_m = modified;
		}

		@Override
		public boolean isModified(IntBooleanEditorDto container) {
			return container.isTrueIntModified();
		}
	};


	public IntBooleanEditorDto() {
		super();
	}

	public int getFalseInt()  {
		if(! isDtoProxy()){
			return this.falseInt;
		}

		if(isFalseIntModified())
			return this.falseInt;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().falseInt());

		return _value;
	}


	public void setFalseInt(int falseInt)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getFalseInt();

		/* set new value */
		this.falseInt = falseInt;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(falseInt_pa, oldValue, falseInt, this.falseInt_m));

		/* set indicator */
		this.falseInt_m = true;

		this.fireObjectChangedEvent(IntBooleanEditorDtoPA.INSTANCE.falseInt(), oldValue);
	}


	public boolean isFalseIntModified()  {
		return falseInt_m;
	}


	public static PropertyAccessor<IntBooleanEditorDto, Integer> getFalseIntPropertyAccessor()  {
		return falseInt_pa;
	}


	public int getTrueInt()  {
		if(! isDtoProxy()){
			return this.trueInt;
		}

		if(isTrueIntModified())
			return this.trueInt;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().trueInt());

		return _value;
	}


	public void setTrueInt(int trueInt)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getTrueInt();

		/* set new value */
		this.trueInt = trueInt;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(trueInt_pa, oldValue, trueInt, this.trueInt_m));

		/* set indicator */
		this.trueInt_m = true;

		this.fireObjectChangedEvent(IntBooleanEditorDtoPA.INSTANCE.trueInt(), oldValue);
	}


	public boolean isTrueIntModified()  {
		return trueInt_m;
	}


	public static PropertyAccessor<IntBooleanEditorDto, Integer> getTrueIntPropertyAccessor()  {
		return trueInt_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new IntBooleanEditorDto2PosoMap();
	}

	public IntBooleanEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(IntBooleanEditorDtoPA.class);
	}

	public void clearModified()  {
		this.falseInt = 0;
		this.falseInt_m = false;
		this.trueInt = 0;
		this.trueInt_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(falseInt_m)
			return true;
		if(trueInt_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(falseInt_pa);
		list.add(trueInt_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(falseInt_m)
			list.add(falseInt_pa);
		if(trueInt_m)
			list.add(trueInt_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(falseInt_pa);
			list.add(trueInt_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
