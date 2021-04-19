package net.datenwerke.rs.base.client.datasources.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec;
import net.datenwerke.rs.base.client.datasources.dto.pa.ArgumentDatasourceConnectorDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.ArgumentDatasourceConnectorDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.connectors.ArgumentDatasourceConnector;

/**
 * Dto for {@link ArgumentDatasourceConnector}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ArgumentDatasourceConnectorDto extends DatasourceConnectorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */

	public ArgumentDatasourceConnectorDto() {
		super();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ArgumentDatasourceConnectorDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ArgumentDatasourceConnectorDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ArgumentDatasourceConnectorDto2PosoMap();
	}

	public ArgumentDatasourceConnectorDtoPA instantiatePropertyAccess()  {
		return GWT.create(ArgumentDatasourceConnectorDtoPA.class);
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
