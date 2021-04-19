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
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultHtmlDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultHtmlDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHtml;

/**
 * Dto for {@link CommandResultHtml}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultHtmlDto extends CommandResultEntryDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String html;
	private  boolean html_m;
	public static final String PROPERTY_HTML = "dpi-commandresulthtml-html";

	private transient static PropertyAccessor<CommandResultHtmlDto, String> html_pa = new PropertyAccessor<CommandResultHtmlDto, String>() {
		@Override
		public void setValue(CommandResultHtmlDto container, String object) {
			container.setHtml(object);
		}

		@Override
		public String getValue(CommandResultHtmlDto container) {
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
		public void setModified(CommandResultHtmlDto container, boolean modified) {
			container.html_m = modified;
		}

		@Override
		public boolean isModified(CommandResultHtmlDto container) {
			return container.isHtmlModified();
		}
	};


	public CommandResultHtmlDto() {
		super();
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

		this.fireObjectChangedEvent(CommandResultHtmlDtoPA.INSTANCE.html(), oldValue);
	}


	public boolean isHtmlModified()  {
		return html_m;
	}


	public static PropertyAccessor<CommandResultHtmlDto, String> getHtmlPropertyAccessor()  {
		return html_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultHtmlDto2PosoMap();
	}

	public CommandResultHtmlDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultHtmlDtoPA.class);
	}

	public void clearModified()  {
		this.html = null;
		this.html_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(html_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(html_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(html_m)
			list.add(html_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(html_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
