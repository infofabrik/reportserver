package net.datenwerke.rs.terminal.client.terminal.dto;

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
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultLineDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultLineDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultLine;

/**
 * Dto for {@link CommandResultLine}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultLineDto extends CommandResultEntryDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String line;
	private  boolean line_m;
	public static final String PROPERTY_LINE = "dpi-commandresultline-line";

	private transient static PropertyAccessor<CommandResultLineDto, String> line_pa = new PropertyAccessor<CommandResultLineDto, String>() {
		@Override
		public void setValue(CommandResultLineDto container, String object) {
			container.setLine(object);
		}

		@Override
		public String getValue(CommandResultLineDto container) {
			return container.getLine();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "line";
		}

		@Override
		public void setModified(CommandResultLineDto container, boolean modified) {
			container.line_m = modified;
		}

		@Override
		public boolean isModified(CommandResultLineDto container) {
			return container.isLineModified();
		}
	};


	public CommandResultLineDto() {
		super();
	}

	public String getLine()  {
		if(! isDtoProxy()){
			return this.line;
		}

		if(isLineModified())
			return this.line;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().line());

		return _value;
	}


	public void setLine(String line)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLine();

		/* set new value */
		this.line = line;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(line_pa, oldValue, line, this.line_m));

		/* set indicator */
		this.line_m = true;

		this.fireObjectChangedEvent(CommandResultLineDtoPA.INSTANCE.line(), oldValue);
	}


	public boolean isLineModified()  {
		return line_m;
	}


	public static PropertyAccessor<CommandResultLineDto, String> getLinePropertyAccessor()  {
		return line_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultLineDto2PosoMap();
	}

	public CommandResultLineDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultLineDtoPA.class);
	}

	public void clearModified()  {
		this.line = null;
		this.line_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(line_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(line_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(line_m)
			list.add(line_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(line_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
