package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.RuntimeException;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2OrderGenerator;

/**
 * Dto2PosoGenerator for Order
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2OrderGenerator implements Dto2PosoGenerator<OrderDto,Order> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2OrderGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public Order loadPoso(OrderDto dto)  {
		return createPoso(dto);
	}

	public Order instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public Order createPoso(OrderDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case ASC:
				return Order.ASC;
			case DESC:
				return Order.DESC;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public Order createUnmanagedPoso(OrderDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(OrderDto dto, Order poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(OrderDto dto, Order poso)  {
		/* no merging for enums */
	}

	public Order loadAndMergePoso(OrderDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(OrderDto dto, Order poso)  {
	}


	public void postProcessCreateUnmanaged(OrderDto dto, Order poso)  {
	}


	public void postProcessLoad(OrderDto dto, Order poso)  {
	}


	public void postProcessMerge(OrderDto dto, Order poso)  {
	}


	public void postProcessInstantiate(Order poso)  {
	}



}
