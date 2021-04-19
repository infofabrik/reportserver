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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.SelectionListEditorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.SelectionListEditorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.SelectionListEditor;

/**
 * Dto for {@link SelectionListEditor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class SelectionListEditorDto extends EditorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean forceSelection;
	private  boolean forceSelection_m;
	public static final String PROPERTY_FORCE_SELECTION = "dpi-selectionlisteditor-forceselection";

	private transient static PropertyAccessor<SelectionListEditorDto, Boolean> forceSelection_pa = new PropertyAccessor<SelectionListEditorDto, Boolean>() {
		@Override
		public void setValue(SelectionListEditorDto container, Boolean object) {
			container.setForceSelection(object);
		}

		@Override
		public Boolean getValue(SelectionListEditorDto container) {
			return container.isForceSelection();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "forceSelection";
		}

		@Override
		public void setModified(SelectionListEditorDto container, boolean modified) {
			container.forceSelection_m = modified;
		}

		@Override
		public boolean isModified(SelectionListEditorDto container) {
			return container.isForceSelectionModified();
		}
	};


	public SelectionListEditorDto() {
		super();
	}

	public boolean isForceSelection()  {
		if(! isDtoProxy()){
			return this.forceSelection;
		}

		if(isForceSelectionModified())
			return this.forceSelection;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().forceSelection());

		return _value;
	}


	public void setForceSelection(boolean forceSelection)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isForceSelection();

		/* set new value */
		this.forceSelection = forceSelection;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(forceSelection_pa, oldValue, forceSelection, this.forceSelection_m));

		/* set indicator */
		this.forceSelection_m = true;

		this.fireObjectChangedEvent(SelectionListEditorDtoPA.INSTANCE.forceSelection(), oldValue);
	}


	public boolean isForceSelectionModified()  {
		return forceSelection_m;
	}


	public static PropertyAccessor<SelectionListEditorDto, Boolean> getForceSelectionPropertyAccessor()  {
		return forceSelection_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SelectionListEditorDto2PosoMap();
	}

	public SelectionListEditorDtoPA instantiatePropertyAccess()  {
		return GWT.create(SelectionListEditorDtoPA.class);
	}

	public void clearModified()  {
		this.forceSelection = false;
		this.forceSelection_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(forceSelection_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(forceSelection_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(forceSelection_m)
			list.add(forceSelection_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(forceSelection_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
