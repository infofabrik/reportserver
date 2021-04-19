package net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2LibraryDadgetGenerator;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetContainer;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for LibraryDadget
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2LibraryDadgetGenerator implements Dto2PosoGenerator<LibraryDadgetDto,LibraryDadget> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2LibraryDadgetGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public LibraryDadget loadPoso(LibraryDadgetDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		LibraryDadget poso = entityManager.find(LibraryDadget.class, id);
		return poso;
	}

	public LibraryDadget instantiatePoso()  {
		LibraryDadget poso = new LibraryDadget();
		return poso;
	}

	public LibraryDadget createPoso(LibraryDadgetDto dto)  throws ExpectedException {
		LibraryDadget poso = new LibraryDadget();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public LibraryDadget createUnmanagedPoso(LibraryDadgetDto dto)  throws ExpectedException {
		LibraryDadget poso = new LibraryDadget();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(LibraryDadgetDto dto, final LibraryDadget poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(LibraryDadgetDto dto, final LibraryDadget poso)  throws ExpectedException {
		/*  set col */
		try{
			poso.setCol(dto.getCol() );
		} catch(NullPointerException e){
		}

		/*  set container */
		DadgetContainerDto tmpDto_container = dto.getContainer();
		poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));

		/*  set dadgetNode */
		DadgetNodeDto tmpDto_dadgetNode = dto.getDadgetNode();
		if(null != tmpDto_dadgetNode && null != tmpDto_dadgetNode.getId()){
			if(null != poso.getDadgetNode() && null != poso.getDadgetNode().getId() && ! poso.getDadgetNode().getId().equals(tmpDto_dadgetNode.getId())){
				DadgetNode newPropertyValue = (DadgetNode)dtoServiceProvider.get().loadPoso(tmpDto_dadgetNode);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDadgetNode(), newPropertyValue, "dadgetNode");
				poso.setDadgetNode(newPropertyValue);
			} else if(null == poso.getDadgetNode()){
				poso.setDadgetNode((DadgetNode)dtoServiceProvider.get().loadPoso(tmpDto_dadgetNode));
			}
		} else if(null != tmpDto_dadgetNode && null == tmpDto_dadgetNode.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dadgetNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (dadgetNode)");
					poso.setDadgetNode((DadgetNode)refPoso);
				}
			});
		} else if(null == tmpDto_dadgetNode){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDadgetNode(), null, "dadgetNode");
			poso.setDadgetNode(null);
		}

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set parameterInstances */
		final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
		/* load new data from dto */
		Collection<ParameterInstanceDto> tmpCol_parameterInstances = dto.getParameterInstances();

		/* load current data from poso */
		if(null != poso.getParameterInstances())
			col_parameterInstances.addAll(poso.getParameterInstances());

		/* remove non existing data */
		Set<ParameterInstance> remDto_parameterInstances = new HashSet<ParameterInstance>();
		for(ParameterInstance ref : col_parameterInstances){
			boolean found = false;
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_parameterInstances.add(ref);
		}
		for(ParameterInstance ref : remDto_parameterInstances)
			col_parameterInstances.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_parameterInstances, "parameterInstances");

		/* merge or add data */
		Set<ParameterInstance> new_col_parameterInstances = new HashSet<ParameterInstance>();
		for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
			boolean found = false;
			for(ParameterInstance ref : col_parameterInstances){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(parameterInstances) ");
		}
		poso.setParameterInstances(new_col_parameterInstances);

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(LibraryDadgetDto dto, final LibraryDadget poso)  throws ExpectedException {
		/*  set col */
		if(dto.isColModified()){
			try{
				poso.setCol(dto.getCol() );
			} catch(NullPointerException e){
			}
		}

		/*  set container */
		if(dto.isContainerModified()){
			DadgetContainerDto tmpDto_container = dto.getContainer();
			poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));
		}

		/*  set dadgetNode */
		if(dto.isDadgetNodeModified()){
			DadgetNodeDto tmpDto_dadgetNode = dto.getDadgetNode();
			if(null != tmpDto_dadgetNode && null != tmpDto_dadgetNode.getId()){
				if(null != poso.getDadgetNode() && null != poso.getDadgetNode().getId() && ! poso.getDadgetNode().getId().equals(tmpDto_dadgetNode.getId())){
					DadgetNode newPropertyValue = (DadgetNode)dtoServiceProvider.get().loadPoso(tmpDto_dadgetNode);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDadgetNode(), newPropertyValue, "dadgetNode");
					poso.setDadgetNode(newPropertyValue);
				} else if(null == poso.getDadgetNode()){
					poso.setDadgetNode((DadgetNode)dtoServiceProvider.get().loadPoso(tmpDto_dadgetNode));
				}
			} else if(null != tmpDto_dadgetNode && null == tmpDto_dadgetNode.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dadgetNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (dadgetNode)");
						poso.setDadgetNode((DadgetNode)refPoso);
					}
			});
			} else if(null == tmpDto_dadgetNode){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDadgetNode(), null, "dadgetNode");
				poso.setDadgetNode(null);
			}
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set parameterInstances */
		if(dto.isParameterInstancesModified()){
			final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
			/* load new data from dto */
			Collection<ParameterInstanceDto> tmpCol_parameterInstances = null;
			tmpCol_parameterInstances = dto.getParameterInstances();

			/* load current data from poso */
			if(null != poso.getParameterInstances())
				col_parameterInstances.addAll(poso.getParameterInstances());

			/* remove non existing data */
			Set<ParameterInstance> remDto_parameterInstances = new HashSet<ParameterInstance>();
			for(ParameterInstance ref : col_parameterInstances){
				boolean found = false;
				for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_parameterInstances.add(ref);
			}
			for(ParameterInstance ref : remDto_parameterInstances)
				col_parameterInstances.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_parameterInstances, "parameterInstances");

			/* merge or add data */
			Set<ParameterInstance> new_col_parameterInstances = new HashSet<ParameterInstance>();
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				boolean found = false;
				for(ParameterInstance ref : col_parameterInstances){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(parameterInstances) ");
			}
			poso.setParameterInstances(new_col_parameterInstances);
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(LibraryDadgetDto dto, final LibraryDadget poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(LibraryDadgetDto dto, final LibraryDadget poso)  throws ExpectedException {
		/*  set col */
		try{
			poso.setCol(dto.getCol() );
		} catch(NullPointerException e){
		}

		/*  set container */
		DadgetContainerDto tmpDto_container = dto.getContainer();
		poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));

		/*  set dadgetNode */
		DadgetNodeDto tmpDto_dadgetNode = dto.getDadgetNode();
		if(null != tmpDto_dadgetNode && null != tmpDto_dadgetNode.getId()){
			DadgetNode newPropertyValue = (DadgetNode)dtoServiceProvider.get().loadPoso(tmpDto_dadgetNode);
			poso.setDadgetNode(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dadgetNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDadgetNode((DadgetNode)refPoso);
				}
			});
		} else if(null != tmpDto_dadgetNode && null == tmpDto_dadgetNode.getId()){
			final DadgetNodeDto tmpDto_dadgetNode_final = tmpDto_dadgetNode;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dadgetNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDadgetNode((DadgetNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dadgetNode_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDadgetNode((DadgetNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_dadgetNode){
			poso.setDadgetNode(null);
		}

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set parameterInstances */
		final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
		/* load new data from dto */
		Collection<ParameterInstanceDto> tmpCol_parameterInstances = dto.getParameterInstances();

		/* merge or add data */
		for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
			col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setParameterInstances(col_parameterInstances);

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(LibraryDadgetDto dto, final LibraryDadget poso)  throws ExpectedException {
		/*  set col */
		if(dto.isColModified()){
			try{
				poso.setCol(dto.getCol() );
			} catch(NullPointerException e){
			}
		}

		/*  set container */
		if(dto.isContainerModified()){
			DadgetContainerDto tmpDto_container = dto.getContainer();
			poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));
		}

		/*  set dadgetNode */
		if(dto.isDadgetNodeModified()){
			DadgetNodeDto tmpDto_dadgetNode = dto.getDadgetNode();
			if(null != tmpDto_dadgetNode && null != tmpDto_dadgetNode.getId()){
				DadgetNode newPropertyValue = (DadgetNode)dtoServiceProvider.get().loadPoso(tmpDto_dadgetNode);
				poso.setDadgetNode(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dadgetNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDadgetNode((DadgetNode)refPoso);
					}
			});
			} else if(null != tmpDto_dadgetNode && null == tmpDto_dadgetNode.getId()){
				final DadgetNodeDto tmpDto_dadgetNode_final = tmpDto_dadgetNode;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dadgetNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDadgetNode((DadgetNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dadgetNode_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDadgetNode((DadgetNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_dadgetNode){
				poso.setDadgetNode(null);
			}
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set parameterInstances */
		if(dto.isParameterInstancesModified()){
			final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
			/* load new data from dto */
			Collection<ParameterInstanceDto> tmpCol_parameterInstances = null;
			tmpCol_parameterInstances = dto.getParameterInstances();

			/* merge or add data */
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setParameterInstances(col_parameterInstances);
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
		}

	}

	public LibraryDadget loadAndMergePoso(LibraryDadgetDto dto)  throws ExpectedException {
		LibraryDadget poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(LibraryDadgetDto dto, LibraryDadget poso)  {
	}


	public void postProcessCreateUnmanaged(LibraryDadgetDto dto, LibraryDadget poso)  {
	}


	public void postProcessLoad(LibraryDadgetDto dto, LibraryDadget poso)  {
	}


	public void postProcessMerge(LibraryDadgetDto dto, LibraryDadget poso)  {
	}


	public void postProcessInstantiate(LibraryDadget poso)  {
	}



}
