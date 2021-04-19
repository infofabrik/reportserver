package net.datenwerke.rs.terminal.client.terminal.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultListDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultListDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultList;

/**
 * Dto for {@link CommandResultList}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultListDto extends CommandResultEntryDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean denyBreakUp;
	private  boolean denyBreakUp_m;
	public static final String PROPERTY_DENY_BREAK_UP = "dpi-commandresultlist-denybreakup";

	private transient static PropertyAccessor<CommandResultListDto, Boolean> denyBreakUp_pa = new PropertyAccessor<CommandResultListDto, Boolean>() {
		@Override
		public void setValue(CommandResultListDto container, Boolean object) {
			container.setDenyBreakUp(object);
		}

		@Override
		public Boolean getValue(CommandResultListDto container) {
			return container.isDenyBreakUp();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "denyBreakUp";
		}

		@Override
		public void setModified(CommandResultListDto container, boolean modified) {
			container.denyBreakUp_m = modified;
		}

		@Override
		public boolean isModified(CommandResultListDto container) {
			return container.isDenyBreakUpModified();
		}
	};

	private List<String> list;
	private  boolean list_m;
	public static final String PROPERTY_LIST = "dpi-commandresultlist-list";

	private transient static PropertyAccessor<CommandResultListDto, List<String>> list_pa = new PropertyAccessor<CommandResultListDto, List<String>>() {
		@Override
		public void setValue(CommandResultListDto container, List<String> object) {
			container.setList(object);
		}

		@Override
		public List<String> getValue(CommandResultListDto container) {
			return container.getList();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "list";
		}

		@Override
		public void setModified(CommandResultListDto container, boolean modified) {
			container.list_m = modified;
		}

		@Override
		public boolean isModified(CommandResultListDto container) {
			return container.isListModified();
		}
	};


	public CommandResultListDto() {
		super();
	}

	public boolean isDenyBreakUp()  {
		if(! isDtoProxy()){
			return this.denyBreakUp;
		}

		if(isDenyBreakUpModified())
			return this.denyBreakUp;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().denyBreakUp());

		return _value;
	}


	public void setDenyBreakUp(boolean denyBreakUp)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isDenyBreakUp();

		/* set new value */
		this.denyBreakUp = denyBreakUp;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(denyBreakUp_pa, oldValue, denyBreakUp, this.denyBreakUp_m));

		/* set indicator */
		this.denyBreakUp_m = true;

		this.fireObjectChangedEvent(CommandResultListDtoPA.INSTANCE.denyBreakUp(), oldValue);
	}


	public boolean isDenyBreakUpModified()  {
		return denyBreakUp_m;
	}


	public static PropertyAccessor<CommandResultListDto, Boolean> getDenyBreakUpPropertyAccessor()  {
		return denyBreakUp_pa;
	}


	public List<String> getList()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.list;
			if(null == _currentValue)
				this.list = new ArrayList<String>();

			return this.list;
		}

		if(isListModified())
			return this.list;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().list());

		return _value;
	}


	public void setList(List<String> list)  {
		/* old value */
		List<String> oldValue = null;
		if(GWT.isClient())
			oldValue = getList();

		/* set new value */
		this.list = list;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(list_pa, oldValue, list, this.list_m));

		/* set indicator */
		this.list_m = true;

		this.fireObjectChangedEvent(CommandResultListDtoPA.INSTANCE.list(), oldValue);
	}


	public boolean isListModified()  {
		return list_m;
	}


	public static PropertyAccessor<CommandResultListDto, List<String>> getListPropertyAccessor()  {
		return list_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultListDto2PosoMap();
	}

	public CommandResultListDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultListDtoPA.class);
	}

	public void clearModified()  {
		this.denyBreakUp = false;
		this.denyBreakUp_m = false;
		this.list = null;
		this.list_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(denyBreakUp_m)
			return true;
		if(list_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(denyBreakUp_pa);
		list.add(list_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(denyBreakUp_m)
			list.add(denyBreakUp_pa);
		if(list_m)
			list.add(list_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(denyBreakUp_pa);
			list.add(list_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
