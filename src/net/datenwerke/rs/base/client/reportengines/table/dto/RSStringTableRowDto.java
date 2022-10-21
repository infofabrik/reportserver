package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.RSStringTableRowDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.RSStringTableRowDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;

/**
 * Dto for {@link RSStringTableRow}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RSStringTableRowDto extends RSTableRowDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<String> stringRow;
	private  boolean stringRow_m;
	public static final String PROPERTY_STRING_ROW = "dpi-rsstringtablerow-stringrow";

	private transient static PropertyAccessor<RSStringTableRowDto, List<String>> stringRow_pa = new PropertyAccessor<RSStringTableRowDto, List<String>>() {
		@Override
		public void setValue(RSStringTableRowDto container, List<String> object) {
			container.setStringRow(object);
		}

		@Override
		public List<String> getValue(RSStringTableRowDto container) {
			return container.getStringRow();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "stringRow";
		}

		@Override
		public void setModified(RSStringTableRowDto container, boolean modified) {
			container.stringRow_m = modified;
		}

		@Override
		public boolean isModified(RSStringTableRowDto container) {
			return container.isStringRowModified();
		}
	};


	public RSStringTableRowDto() {
		super();
	}

	public List<String> getStringRow()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.stringRow;
			if(null == _currentValue)
				this.stringRow = new ArrayList<String>();

			return this.stringRow;
		}

		if(isStringRowModified())
			return this.stringRow;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().stringRow());

		return _value;
	}


	public void setStringRow(List<String> stringRow)  {
		/* old value */
		List<String> oldValue = null;
		if(GWT.isClient())
			oldValue = getStringRow();

		/* set new value */
		this.stringRow = stringRow;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(stringRow_pa, oldValue, stringRow, this.stringRow_m));

		/* set indicator */
		this.stringRow_m = true;

		this.fireObjectChangedEvent(RSStringTableRowDtoPA.INSTANCE.stringRow(), oldValue);
	}


	public boolean isStringRowModified()  {
		return stringRow_m;
	}


	public static PropertyAccessor<RSStringTableRowDto, List<String>> getStringRowPropertyAccessor()  {
		return stringRow_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RSStringTableRowDto2PosoMap();
	}

	public RSStringTableRowDtoPA instantiatePropertyAccess()  {
		return GWT.create(RSStringTableRowDtoPA.class);
	}

	public void clearModified()  {
		this.stringRow = null;
		this.stringRow_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(stringRow_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(stringRow_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(stringRow_m)
			list.add(stringRow_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(stringRow_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
