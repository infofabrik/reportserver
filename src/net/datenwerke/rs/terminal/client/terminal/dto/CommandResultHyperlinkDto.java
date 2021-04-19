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
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultHyperlinkDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultHyperlinkDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHyperlink;

/**
 * Dto for {@link CommandResultHyperlink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultHyperlinkDto extends CommandResultEntryDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String caption;
	private  boolean caption_m;
	public static final String PROPERTY_CAPTION = "dpi-commandresulthyperlink-caption";

	private transient static PropertyAccessor<CommandResultHyperlinkDto, String> caption_pa = new PropertyAccessor<CommandResultHyperlinkDto, String>() {
		@Override
		public void setValue(CommandResultHyperlinkDto container, String object) {
			container.setCaption(object);
		}

		@Override
		public String getValue(CommandResultHyperlinkDto container) {
			return container.getCaption();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "caption";
		}

		@Override
		public void setModified(CommandResultHyperlinkDto container, boolean modified) {
			container.caption_m = modified;
		}

		@Override
		public boolean isModified(CommandResultHyperlinkDto container) {
			return container.isCaptionModified();
		}
	};

	private String historyToken;
	private  boolean historyToken_m;
	public static final String PROPERTY_HISTORY_TOKEN = "dpi-commandresulthyperlink-historytoken";

	private transient static PropertyAccessor<CommandResultHyperlinkDto, String> historyToken_pa = new PropertyAccessor<CommandResultHyperlinkDto, String>() {
		@Override
		public void setValue(CommandResultHyperlinkDto container, String object) {
			container.setHistoryToken(object);
		}

		@Override
		public String getValue(CommandResultHyperlinkDto container) {
			return container.getHistoryToken();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "historyToken";
		}

		@Override
		public void setModified(CommandResultHyperlinkDto container, boolean modified) {
			container.historyToken_m = modified;
		}

		@Override
		public boolean isModified(CommandResultHyperlinkDto container) {
			return container.isHistoryTokenModified();
		}
	};


	public CommandResultHyperlinkDto() {
		super();
	}

	public String getCaption()  {
		if(! isDtoProxy()){
			return this.caption;
		}

		if(isCaptionModified())
			return this.caption;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().caption());

		return _value;
	}


	public void setCaption(String caption)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCaption();

		/* set new value */
		this.caption = caption;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(caption_pa, oldValue, caption, this.caption_m));

		/* set indicator */
		this.caption_m = true;

		this.fireObjectChangedEvent(CommandResultHyperlinkDtoPA.INSTANCE.caption(), oldValue);
	}


	public boolean isCaptionModified()  {
		return caption_m;
	}


	public static PropertyAccessor<CommandResultHyperlinkDto, String> getCaptionPropertyAccessor()  {
		return caption_pa;
	}


	public String getHistoryToken()  {
		if(! isDtoProxy()){
			return this.historyToken;
		}

		if(isHistoryTokenModified())
			return this.historyToken;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().historyToken());

		return _value;
	}


	public void setHistoryToken(String historyToken)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHistoryToken();

		/* set new value */
		this.historyToken = historyToken;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(historyToken_pa, oldValue, historyToken, this.historyToken_m));

		/* set indicator */
		this.historyToken_m = true;

		this.fireObjectChangedEvent(CommandResultHyperlinkDtoPA.INSTANCE.historyToken(), oldValue);
	}


	public boolean isHistoryTokenModified()  {
		return historyToken_m;
	}


	public static PropertyAccessor<CommandResultHyperlinkDto, String> getHistoryTokenPropertyAccessor()  {
		return historyToken_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultHyperlinkDto2PosoMap();
	}

	public CommandResultHyperlinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultHyperlinkDtoPA.class);
	}

	public void clearModified()  {
		this.caption = null;
		this.caption_m = false;
		this.historyToken = null;
		this.historyToken_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(caption_m)
			return true;
		if(historyToken_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(caption_pa);
		list.add(historyToken_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(caption_m)
			list.add(caption_pa);
		if(historyToken_m)
			list.add(historyToken_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(caption_pa);
			list.add(historyToken_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
