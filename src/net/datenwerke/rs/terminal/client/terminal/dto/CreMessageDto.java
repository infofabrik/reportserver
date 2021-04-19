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
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CreMessageDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CreMessageDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CreMessage;

/**
 * Dto for {@link CreMessage}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CreMessageDto extends CommandResultExtensionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-cremessage-height";

	private transient static PropertyAccessor<CreMessageDto, Integer> height_pa = new PropertyAccessor<CreMessageDto, Integer>() {
		@Override
		public void setValue(CreMessageDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(CreMessageDto container) {
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
		public void setModified(CreMessageDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(CreMessageDto container) {
			return container.isHeightModified();
		}
	};

	private String html;
	private  boolean html_m;
	public static final String PROPERTY_HTML = "dpi-cremessage-html";

	private transient static PropertyAccessor<CreMessageDto, String> html_pa = new PropertyAccessor<CreMessageDto, String>() {
		@Override
		public void setValue(CreMessageDto container, String object) {
			container.setHtml(object);
		}

		@Override
		public String getValue(CreMessageDto container) {
			return container.getHtml();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "html";
		}

		@Override
		public void setModified(CreMessageDto container, boolean modified) {
			container.html_m = modified;
		}

		@Override
		public boolean isModified(CreMessageDto container) {
			return container.isHtmlModified();
		}
	};

	private String text;
	private  boolean text_m;
	public static final String PROPERTY_TEXT = "dpi-cremessage-text";

	private transient static PropertyAccessor<CreMessageDto, String> text_pa = new PropertyAccessor<CreMessageDto, String>() {
		@Override
		public void setValue(CreMessageDto container, String object) {
			container.setText(object);
		}

		@Override
		public String getValue(CreMessageDto container) {
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
		public void setModified(CreMessageDto container, boolean modified) {
			container.text_m = modified;
		}

		@Override
		public boolean isModified(CreMessageDto container) {
			return container.isTextModified();
		}
	};

	private String title;
	private  boolean title_m;
	public static final String PROPERTY_TITLE = "dpi-cremessage-title";

	private transient static PropertyAccessor<CreMessageDto, String> title_pa = new PropertyAccessor<CreMessageDto, String>() {
		@Override
		public void setValue(CreMessageDto container, String object) {
			container.setTitle(object);
		}

		@Override
		public String getValue(CreMessageDto container) {
			return container.getTitle();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "title";
		}

		@Override
		public void setModified(CreMessageDto container, boolean modified) {
			container.title_m = modified;
		}

		@Override
		public boolean isModified(CreMessageDto container) {
			return container.isTitleModified();
		}
	};

	private int width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-cremessage-width";

	private transient static PropertyAccessor<CreMessageDto, Integer> width_pa = new PropertyAccessor<CreMessageDto, Integer>() {
		@Override
		public void setValue(CreMessageDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(CreMessageDto container) {
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
		public void setModified(CreMessageDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(CreMessageDto container) {
			return container.isWidthModified();
		}
	};

	private String windowTitle;
	private  boolean windowTitle_m;
	public static final String PROPERTY_WINDOW_TITLE = "dpi-cremessage-windowtitle";

	private transient static PropertyAccessor<CreMessageDto, String> windowTitle_pa = new PropertyAccessor<CreMessageDto, String>() {
		@Override
		public void setValue(CreMessageDto container, String object) {
			container.setWindowTitle(object);
		}

		@Override
		public String getValue(CreMessageDto container) {
			return container.getWindowTitle();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "windowTitle";
		}

		@Override
		public void setModified(CreMessageDto container, boolean modified) {
			container.windowTitle_m = modified;
		}

		@Override
		public boolean isModified(CreMessageDto container) {
			return container.isWindowTitleModified();
		}
	};


	public CreMessageDto() {
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

		this.fireObjectChangedEvent(CreMessageDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<CreMessageDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
	}


	public String getHtml()  {
		if(! isDtoProxy()){
			return this.html;
		}

		if(isHtmlModified())
			return this.html;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().html());

		return _value;
	}


	public void setHtml(String html)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHtml();

		/* set new value */
		this.html = html;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(html_pa, oldValue, html, this.html_m));

		/* set indicator */
		this.html_m = true;

		this.fireObjectChangedEvent(CreMessageDtoPA.INSTANCE.html(), oldValue);
	}


	public boolean isHtmlModified()  {
		return html_m;
	}


	public static PropertyAccessor<CreMessageDto, String> getHtmlPropertyAccessor()  {
		return html_pa;
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

		this.fireObjectChangedEvent(CreMessageDtoPA.INSTANCE.text(), oldValue);
	}


	public boolean isTextModified()  {
		return text_m;
	}


	public static PropertyAccessor<CreMessageDto, String> getTextPropertyAccessor()  {
		return text_pa;
	}


	public String getTitle()  {
		if(! isDtoProxy()){
			return this.title;
		}

		if(isTitleModified())
			return this.title;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().title());

		return _value;
	}


	public void setTitle(String title)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTitle();

		/* set new value */
		this.title = title;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(title_pa, oldValue, title, this.title_m));

		/* set indicator */
		this.title_m = true;

		this.fireObjectChangedEvent(CreMessageDtoPA.INSTANCE.title(), oldValue);
	}


	public boolean isTitleModified()  {
		return title_m;
	}


	public static PropertyAccessor<CreMessageDto, String> getTitlePropertyAccessor()  {
		return title_pa;
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

		this.fireObjectChangedEvent(CreMessageDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<CreMessageDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	public String getWindowTitle()  {
		if(! isDtoProxy()){
			return this.windowTitle;
		}

		if(isWindowTitleModified())
			return this.windowTitle;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().windowTitle());

		return _value;
	}


	public void setWindowTitle(String windowTitle)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getWindowTitle();

		/* set new value */
		this.windowTitle = windowTitle;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(windowTitle_pa, oldValue, windowTitle, this.windowTitle_m));

		/* set indicator */
		this.windowTitle_m = true;

		this.fireObjectChangedEvent(CreMessageDtoPA.INSTANCE.windowTitle(), oldValue);
	}


	public boolean isWindowTitleModified()  {
		return windowTitle_m;
	}


	public static PropertyAccessor<CreMessageDto, String> getWindowTitlePropertyAccessor()  {
		return windowTitle_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CreMessageDto2PosoMap();
	}

	public CreMessageDtoPA instantiatePropertyAccess()  {
		return GWT.create(CreMessageDtoPA.class);
	}

	public void clearModified()  {
		this.height = 0;
		this.height_m = false;
		this.html = null;
		this.html_m = false;
		this.text = null;
		this.text_m = false;
		this.title = null;
		this.title_m = false;
		this.width = 0;
		this.width_m = false;
		this.windowTitle = null;
		this.windowTitle_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(height_m)
			return true;
		if(html_m)
			return true;
		if(text_m)
			return true;
		if(title_m)
			return true;
		if(width_m)
			return true;
		if(windowTitle_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(height_pa);
		list.add(html_pa);
		list.add(text_pa);
		list.add(title_pa);
		list.add(width_pa);
		list.add(windowTitle_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(height_m)
			list.add(height_pa);
		if(html_m)
			list.add(html_pa);
		if(text_m)
			list.add(text_pa);
		if(title_m)
			list.add(title_pa);
		if(width_m)
			list.add(width_pa);
		if(windowTitle_m)
			list.add(windowTitle_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(height_pa);
			list.add(html_pa);
			list.add(text_pa);
			list.add(title_pa);
			list.add(width_pa);
			list.add(windowTitle_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
