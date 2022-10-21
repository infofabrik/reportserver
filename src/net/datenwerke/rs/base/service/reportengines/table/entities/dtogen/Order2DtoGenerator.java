package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Order2DtoGenerator;

/**
 * Poso2DtoGenerator for Order
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Order2DtoGenerator implements Poso2DtoGenerator<Order,OrderDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Order2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public OrderDto instantiateDto(Order poso)  {
		/* Simply return the first enum! */
		OrderDto dto = OrderDto.ASC;
		return dto;
	}

	public OrderDto createDto(Order poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case ASC:
				return OrderDto.ASC;
			case DESC:
				return OrderDto.DESC;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
