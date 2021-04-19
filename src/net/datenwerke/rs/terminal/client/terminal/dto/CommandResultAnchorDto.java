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
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultAnchorDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultAnchorDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultAnchor;

/**
 * Dto for {@link CommandResultAnchor}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultAnchorDto extends CommandResultEntryDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String target;
	private  boolean target_m;
	public static final String PROPERTY_TARGET = "dpi-commandresultanchor-target";

	private transient static PropertyAccessor<CommandResultAnchorDto, String> target_pa = new PropertyAccessor<CommandResultAnchorDto, String>() {
		@Override
		public void setValue(CommandResultAnchorDto container, String object) {
			container.setTarget(object);
		}

		@Override
		public String getValue(CommandResultAnchorDto container) {
			return container.getTarget();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "target";
		}

		@Override
		public void setModified(CommandResultAnchorDto container, boolean modified) {
			container.target_m = modified;
		}

		@Override
		public boolean isModified(CommandResultAnchorDto container) {
			return container.isTargetModified();
		}
	};

	private String text;
	private  boolean text_m;
	public static final String PROPERTY_TEXT = "dpi-commandresultanchor-text";

	private transient static PropertyAccessor<CommandResultAnchorDto, String> text_pa = new PropertyAccessor<CommandResultAnchorDto, String>() {
		@Override
		public void setValue(CommandResultAnchorDto container, String object) {
			container.setText(object);
		}

		@Override
		public String getValue(CommandResultAnchorDto container) {
			return container.getText();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "text";
		}

		@Override
		public void setModified(CommandResultAnchorDto container, boolean modified) {
			container.text_m = modified;
		}

		@Override
		public boolean isModified(CommandResultAnchorDto container) {
			return container.isTextModified();
		}
	};

	private String url;
	private  boolean url_m;
	public static final String PROPERTY_URL = "dpi-commandresultanchor-url";

	private transient static PropertyAccessor<CommandResultAnchorDto, String> url_pa = new PropertyAccessor<CommandResultAnchorDto, String>() {
		@Override
		public void setValue(CommandResultAnchorDto container, String object) {
			container.setUrl(object);
		}

		@Override
		public String getValue(CommandResultAnchorDto container) {
			return container.getUrl();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "url";
		}

		@Override
		public void setModified(CommandResultAnchorDto container, boolean modified) {
			container.url_m = modified;
		}

		@Override
		public boolean isModified(CommandResultAnchorDto container) {
			return container.isUrlModified();
		}
	};


	public CommandResultAnchorDto() {
		super();
	}

	public String getTarget()  {
		if(! isDtoProxy()){
			return this.target;
		}

		if(isTargetModified())
			return this.target;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().target());

		return _value;
	}


	public void setTarget(String target)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTarget();

		/* set new value */
		this.target = target;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(target_pa, oldValue, target, this.target_m));

		/* set indicator */
		this.target_m = true;

		this.fireObjectChangedEvent(CommandResultAnchorDtoPA.INSTANCE.target(), oldValue);
	}


	public boolean isTargetModified()  {
		return target_m;
	}


	public static PropertyAccessor<CommandResultAnchorDto, String> getTargetPropertyAccessor()  {
		return target_pa;
	}


	public String getText()  {
		if(! isDtoProxy()){
			return this.text;
		}

		if(isTextModified())
			return this.text;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().text());

		return _value;
	}


	public void setText(String text)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getText();

		/* set new value */
		this.text = text;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(text_pa, oldValue, text, this.text_m));

		/* set indicator */
		this.text_m = true;

		this.fireObjectChangedEvent(CommandResultAnchorDtoPA.INSTANCE.text(), oldValue);
	}


	public boolean isTextModified()  {
		return text_m;
	}


	public static PropertyAccessor<CommandResultAnchorDto, String> getTextPropertyAccessor()  {
		return text_pa;
	}


	public String getUrl()  {
		if(! isDtoProxy()){
			return this.url;
		}

		if(isUrlModified())
			return this.url;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().url());

		return _value;
	}


	public void setUrl(String url)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getUrl();

		/* set new value */
		this.url = url;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(url_pa, oldValue, url, this.url_m));

		/* set indicator */
		this.url_m = true;

		this.fireObjectChangedEvent(CommandResultAnchorDtoPA.INSTANCE.url(), oldValue);
	}


	public boolean isUrlModified()  {
		return url_m;
	}


	public static PropertyAccessor<CommandResultAnchorDto, String> getUrlPropertyAccessor()  {
		return url_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultAnchorDto2PosoMap();
	}

	public CommandResultAnchorDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultAnchorDtoPA.class);
	}

	public void clearModified()  {
		this.target = null;
		this.target_m = false;
		this.text = null;
		this.text_m = false;
		this.url = null;
		this.url_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(target_m)
			return true;
		if(text_m)
			return true;
		if(url_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(target_pa);
		list.add(text_pa);
		list.add(url_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(target_m)
			list.add(target_pa);
		if(text_m)
			list.add(text_pa);
		if(url_m)
			list.add(url_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(target_pa);
			list.add(text_pa);
			list.add(url_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
