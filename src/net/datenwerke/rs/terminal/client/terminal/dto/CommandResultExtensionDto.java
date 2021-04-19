package net.datenwerke.rs.terminal.client.terminal.dto;

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
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;
import net.datenwerke.rs.terminal.client.terminal.dto.posomap.CommandResultExtensionDto2PosoMap;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;

/**
 * Dto for {@link CommandResultExtension}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class CommandResultExtensionDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */

	public CommandResultExtensionDto() {
		super();
	}

	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new CommandResultExtensionDto2PosoMap();
	}

	public CommandResultExtensionDtoPA instantiatePropertyAccess()  {
		return GWT.create(CommandResultExtensionDtoPA.class);
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
