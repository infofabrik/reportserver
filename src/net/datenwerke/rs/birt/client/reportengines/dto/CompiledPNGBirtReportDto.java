package net.datenwerke.rs.birt.client.reportengines.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.birt.client.reportengines.dto.pa.CompiledPNGBirtReportDtoPA;
import net.datenwerke.rs.birt.client.reportengines.dto.posomap.CompiledPNGBirtReportDto2PosoMap;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledPNGBirtReport;

/**
 * Dto for {@link CompiledPNGBirtReport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CompiledPNGBirtReportDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */

	public CompiledPNGBirtReportDto() {
		super();
	}

	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CompiledPNGBirtReportDto2PosoMap();
	}

	public CompiledPNGBirtReportDtoPA instantiatePropertyAccess()  {
		return GWT.create(CompiledPNGBirtReportDtoPA.class);
	}

	public void clearModified()  {
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
