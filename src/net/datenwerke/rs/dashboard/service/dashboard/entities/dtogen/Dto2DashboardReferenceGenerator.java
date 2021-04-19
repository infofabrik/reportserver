package net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;
import net.datenwerke.rs.dashboard.service.dashboard.entities.LayoutType;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardReferenceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DashboardReference
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DashboardReferenceGenerator implements Dto2PosoGenerator<DashboardReferenceDto,DashboardReference> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DashboardReferenceGenerator(
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

	public DashboardReference loadPoso(DashboardReferenceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DashboardReference poso = entityManager.find(DashboardReference.class, id);
		return poso;
	}

	public DashboardReference instantiatePoso()  {
		DashboardReference poso = new DashboardReference();
		return poso;
	}

	public DashboardReference createPoso(DashboardReferenceDto dto)  throws ExpectedException {
		DashboardReference poso = new DashboardReference();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DashboardReference createUnmanagedPoso(DashboardReferenceDto dto)  throws ExpectedException {
		DashboardReference poso = new DashboardReference();

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

	public void mergePoso(DashboardReferenceDto dto, final DashboardReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DashboardReferenceDto dto, final DashboardReference poso)  throws ExpectedException {
		/*  set dadgets */
		final List<Dadget> col_dadgets = new ArrayList<Dadget>();
		/* load new data from dto */
		Collection<DadgetDto> tmpCol_dadgets = dto.getDadgets();

		/* load current data from poso */
		if(null != poso.getDadgets())
			col_dadgets.addAll(poso.getDadgets());

		/* remove non existing data */
		List<Dadget> remDto_dadgets = new ArrayList<Dadget>();
		for(Dadget ref : col_dadgets){
			boolean found = false;
			for(DadgetDto refDto : tmpCol_dadgets){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_dadgets.add(ref);
		}
		for(Dadget ref : remDto_dadgets)
			col_dadgets.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_dadgets, "dadgets");

		/* merge or add data */
		List<Dadget> new_col_dadgets = new ArrayList<Dadget>();
		for(DadgetDto refDto : tmpCol_dadgets){
			boolean found = false;
			for(Dadget ref : col_dadgets){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_dadgets.add((Dadget) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_dadgets.add((Dadget) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(dadgets) ");
		}
		poso.setDadgets(new_col_dadgets);

		/*  set dashboardNode */
		DashboardNodeDto tmpDto_dashboardNode = dto.getDashboardNode();
		if(null != tmpDto_dashboardNode && null != tmpDto_dashboardNode.getId()){
			if(null != poso.getDashboardNode() && null != poso.getDashboardNode().getId() && ! poso.getDashboardNode().getId().equals(tmpDto_dashboardNode.getId())){
				DashboardNode newPropertyValue = (DashboardNode)dtoServiceProvider.get().loadPoso(tmpDto_dashboardNode);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDashboardNode(), newPropertyValue, "dashboardNode");
				poso.setDashboardNode(newPropertyValue);
			} else if(null == poso.getDashboardNode()){
				poso.setDashboardNode((DashboardNode)dtoServiceProvider.get().loadPoso(tmpDto_dashboardNode));
			}
		} else if(null != tmpDto_dashboardNode && null == tmpDto_dashboardNode.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dashboardNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (dashboardNode)");
					poso.setDashboardNode((DashboardNode)refPoso);
				}
			});
		} else if(null == tmpDto_dashboardNode){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDashboardNode(), null, "dashboardNode");
			poso.setDashboardNode(null);
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set layout */
		LayoutTypeDto tmpDto_layout = dto.getLayout();
		poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set primary */
		try{
			poso.setPrimary(dto.isPrimary() );
		} catch(NullPointerException e){
		}

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

		/*  set singlePage */
		try{
			poso.setSinglePage(dto.isSinglePage() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(DashboardReferenceDto dto, final DashboardReference poso)  throws ExpectedException {
		/*  set dadgets */
		if(dto.isDadgetsModified()){
			final List<Dadget> col_dadgets = new ArrayList<Dadget>();
			/* load new data from dto */
			Collection<DadgetDto> tmpCol_dadgets = null;
			tmpCol_dadgets = dto.getDadgets();

			/* load current data from poso */
			if(null != poso.getDadgets())
				col_dadgets.addAll(poso.getDadgets());

			/* remove non existing data */
			List<Dadget> remDto_dadgets = new ArrayList<Dadget>();
			for(Dadget ref : col_dadgets){
				boolean found = false;
				for(DadgetDto refDto : tmpCol_dadgets){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_dadgets.add(ref);
			}
			for(Dadget ref : remDto_dadgets)
				col_dadgets.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_dadgets, "dadgets");

			/* merge or add data */
			List<Dadget> new_col_dadgets = new ArrayList<Dadget>();
			for(DadgetDto refDto : tmpCol_dadgets){
				boolean found = false;
				for(Dadget ref : col_dadgets){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_dadgets.add((Dadget) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_dadgets.add((Dadget) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(dadgets) ");
			}
			poso.setDadgets(new_col_dadgets);
		}

		/*  set dashboardNode */
		if(dto.isDashboardNodeModified()){
			DashboardNodeDto tmpDto_dashboardNode = dto.getDashboardNode();
			if(null != tmpDto_dashboardNode && null != tmpDto_dashboardNode.getId()){
				if(null != poso.getDashboardNode() && null != poso.getDashboardNode().getId() && ! poso.getDashboardNode().getId().equals(tmpDto_dashboardNode.getId())){
					DashboardNode newPropertyValue = (DashboardNode)dtoServiceProvider.get().loadPoso(tmpDto_dashboardNode);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDashboardNode(), newPropertyValue, "dashboardNode");
					poso.setDashboardNode(newPropertyValue);
				} else if(null == poso.getDashboardNode()){
					poso.setDashboardNode((DashboardNode)dtoServiceProvider.get().loadPoso(tmpDto_dashboardNode));
				}
			} else if(null != tmpDto_dashboardNode && null == tmpDto_dashboardNode.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dashboardNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (dashboardNode)");
						poso.setDashboardNode((DashboardNode)refPoso);
					}
			});
			} else if(null == tmpDto_dashboardNode){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDashboardNode(), null, "dashboardNode");
				poso.setDashboardNode(null);
			}
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set layout */
		if(dto.isLayoutModified()){
			LayoutTypeDto tmpDto_layout = dto.getLayout();
			poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set primary */
		if(dto.isPrimaryModified()){
			try{
				poso.setPrimary(dto.isPrimary() );
			} catch(NullPointerException e){
			}
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
		}

		/*  set singlePage */
		if(dto.isSinglePageModified()){
			try{
				poso.setSinglePage(dto.isSinglePage() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(DashboardReferenceDto dto, final DashboardReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DashboardReferenceDto dto, final DashboardReference poso)  throws ExpectedException {
		/*  set dadgets */
		final List<Dadget> col_dadgets = new ArrayList<Dadget>();
		/* load new data from dto */
		Collection<DadgetDto> tmpCol_dadgets = dto.getDadgets();

		/* merge or add data */
		for(DadgetDto refDto : tmpCol_dadgets){
			col_dadgets.add((Dadget) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setDadgets(col_dadgets);

		/*  set dashboardNode */
		DashboardNodeDto tmpDto_dashboardNode = dto.getDashboardNode();
		if(null != tmpDto_dashboardNode && null != tmpDto_dashboardNode.getId()){
			DashboardNode newPropertyValue = (DashboardNode)dtoServiceProvider.get().loadPoso(tmpDto_dashboardNode);
			poso.setDashboardNode(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dashboardNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDashboardNode((DashboardNode)refPoso);
				}
			});
		} else if(null != tmpDto_dashboardNode && null == tmpDto_dashboardNode.getId()){
			final DashboardNodeDto tmpDto_dashboardNode_final = tmpDto_dashboardNode;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dashboardNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDashboardNode((DashboardNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dashboardNode_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDashboardNode((DashboardNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_dashboardNode){
			poso.setDashboardNode(null);
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set layout */
		LayoutTypeDto tmpDto_layout = dto.getLayout();
		poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set primary */
		try{
			poso.setPrimary(dto.isPrimary() );
		} catch(NullPointerException e){
		}

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

		/*  set singlePage */
		try{
			poso.setSinglePage(dto.isSinglePage() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(DashboardReferenceDto dto, final DashboardReference poso)  throws ExpectedException {
		/*  set dadgets */
		if(dto.isDadgetsModified()){
			final List<Dadget> col_dadgets = new ArrayList<Dadget>();
			/* load new data from dto */
			Collection<DadgetDto> tmpCol_dadgets = null;
			tmpCol_dadgets = dto.getDadgets();

			/* merge or add data */
			for(DadgetDto refDto : tmpCol_dadgets){
				col_dadgets.add((Dadget) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setDadgets(col_dadgets);
		}

		/*  set dashboardNode */
		if(dto.isDashboardNodeModified()){
			DashboardNodeDto tmpDto_dashboardNode = dto.getDashboardNode();
			if(null != tmpDto_dashboardNode && null != tmpDto_dashboardNode.getId()){
				DashboardNode newPropertyValue = (DashboardNode)dtoServiceProvider.get().loadPoso(tmpDto_dashboardNode);
				poso.setDashboardNode(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dashboardNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDashboardNode((DashboardNode)refPoso);
					}
			});
			} else if(null != tmpDto_dashboardNode && null == tmpDto_dashboardNode.getId()){
				final DashboardNodeDto tmpDto_dashboardNode_final = tmpDto_dashboardNode;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_dashboardNode, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDashboardNode((DashboardNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dashboardNode_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDashboardNode((DashboardNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_dashboardNode){
				poso.setDashboardNode(null);
			}
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set layout */
		if(dto.isLayoutModified()){
			LayoutTypeDto tmpDto_layout = dto.getLayout();
			poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set primary */
		if(dto.isPrimaryModified()){
			try{
				poso.setPrimary(dto.isPrimary() );
			} catch(NullPointerException e){
			}
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
		}

		/*  set singlePage */
		if(dto.isSinglePageModified()){
			try{
				poso.setSinglePage(dto.isSinglePage() );
			} catch(NullPointerException e){
			}
		}

	}

	public DashboardReference loadAndMergePoso(DashboardReferenceDto dto)  throws ExpectedException {
		DashboardReference poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DashboardReferenceDto dto, DashboardReference poso)  {
	}


	public void postProcessCreateUnmanaged(DashboardReferenceDto dto, DashboardReference poso)  {
	}


	public void postProcessLoad(DashboardReferenceDto dto, DashboardReference poso)  {
	}


	public void postProcessMerge(DashboardReferenceDto dto, DashboardReference poso)  {
	}


	public void postProcessInstantiate(DashboardReference poso)  {
	}



}
