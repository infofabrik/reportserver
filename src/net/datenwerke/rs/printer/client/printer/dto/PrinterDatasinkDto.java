package net.datenwerke.rs.printer.client.printer.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.printer.client.printer.dto.pa.PrinterDatasinkDtoPA;
import net.datenwerke.rs.printer.client.printer.dto.posomap.PrinterDatasinkDto2PosoMap;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link PrinterDatasink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class PrinterDatasinkDto extends DatasinkDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String printerName;
	private  boolean printerName_m;
	public static final String PROPERTY_PRINTER_NAME = "dpi-printerdatasink-printername";

	private transient static PropertyAccessor<PrinterDatasinkDto, String> printerName_pa = new PropertyAccessor<PrinterDatasinkDto, String>() {
		@Override
		public void setValue(PrinterDatasinkDto container, String object) {
			container.setPrinterName(object);
		}

		@Override
		public String getValue(PrinterDatasinkDto container) {
			return container.getPrinterName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "printerName";
		}

		@Override
		public void setModified(PrinterDatasinkDto container, boolean modified) {
			container.printerName_m = modified;
		}

		@Override
		public boolean isModified(PrinterDatasinkDto container) {
			return container.isPrinterNameModified();
		}
	};


	public PrinterDatasinkDto() {
		super();
	}

	public String getPrinterName()  {
		if(! isDtoProxy()){
			return this.printerName;
		}

		if(isPrinterNameModified())
			return this.printerName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().printerName());

		return _value;
	}


	public void setPrinterName(String printerName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPrinterName();

		/* set new value */
		this.printerName = printerName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(printerName_pa, oldValue, printerName, this.printerName_m));

		/* set indicator */
		this.printerName_m = true;

		this.fireObjectChangedEvent(PrinterDatasinkDtoPA.INSTANCE.printerName(), oldValue);
	}


	public boolean isPrinterNameModified()  {
		return printerName_m;
	}


	public static PropertyAccessor<PrinterDatasinkDto, String> getPrinterNamePropertyAccessor()  {
		return printerName_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			if(null == getName())
				return BaseMessages.INSTANCE.unnamed();
			return getName().toString();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("upload");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof PrinterDatasinkDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((PrinterDatasinkDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new PrinterDatasinkDto2PosoMap();
	}

	public PrinterDatasinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(PrinterDatasinkDtoPA.class);
	}

	public void clearModified()  {
		this.printerName = null;
		this.printerName_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(printerName_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(printerName_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(printerName_m)
			list.add(printerName_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(printerName_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
