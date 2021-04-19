package net.datenwerke.rs.birt.service.utils.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;
import net.datenwerke.rs.birt.service.utils.BirtParameterProposal;
import net.datenwerke.rs.birt.service.utils.dtogen.Dto2BirtParameterProposalGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for BirtParameterProposal
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BirtParameterProposalGenerator implements Dto2PosoGenerator<BirtParameterProposalDto,BirtParameterProposal> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2BirtParameterProposalGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public BirtParameterProposal loadPoso(BirtParameterProposalDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public BirtParameterProposal instantiatePoso()  {
		BirtParameterProposal poso = new BirtParameterProposal();
		return poso;
	}

	public BirtParameterProposal createPoso(BirtParameterProposalDto dto)  throws ExpectedException {
		BirtParameterProposal poso = new BirtParameterProposal();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public BirtParameterProposal createUnmanagedPoso(BirtParameterProposalDto dto)  throws ExpectedException {
		BirtParameterProposal poso = new BirtParameterProposal();

		/* store old id */
		if(null != dto.getKey()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getKey());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(BirtParameterProposalDto dto, final BirtParameterProposal poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(BirtParameterProposalDto dto, final BirtParameterProposal poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set parameterProposal */
		ParameterDefinitionDto tmpDto_parameterProposal = dto.getParameterProposal();
		if(null != tmpDto_parameterProposal && null != tmpDto_parameterProposal.getId()){
			if(null != poso.getParameterProposal() && null != poso.getParameterProposal().getId() && poso.getParameterProposal().getId().equals(tmpDto_parameterProposal.getId()))
				poso.setParameterProposal((ParameterDefinition)dtoServiceProvider.get().loadAndMergePoso(tmpDto_parameterProposal));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (parameterProposal)");
		} else if(null != poso.getParameterProposal()){
			ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().createPoso(tmpDto_parameterProposal);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getParameterProposal(), newPropertyValue, "parameterProposal");
			poso.setParameterProposal(newPropertyValue);
		} else {
			poso.setParameterProposal((ParameterDefinition)dtoServiceProvider.get().createPoso(tmpDto_parameterProposal));
		}

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2Poso(BirtParameterProposalDto dto, final BirtParameterProposal poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set parameterProposal */
		if(dto.isParameterProposalModified()){
			ParameterDefinitionDto tmpDto_parameterProposal = dto.getParameterProposal();
			if(null != tmpDto_parameterProposal && null != tmpDto_parameterProposal.getId()){
				if(null != poso.getParameterProposal() && null != poso.getParameterProposal().getId() && poso.getParameterProposal().getId().equals(tmpDto_parameterProposal.getId()))
					poso.setParameterProposal((ParameterDefinition)dtoServiceProvider.get().loadAndMergePoso(tmpDto_parameterProposal));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (parameterProposal)");
			} else if(null != poso.getParameterProposal()){
				ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().createPoso(tmpDto_parameterProposal);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getParameterProposal(), newPropertyValue, "parameterProposal");
				poso.setParameterProposal(newPropertyValue);
			} else {
				poso.setParameterProposal((ParameterDefinition)dtoServiceProvider.get().createPoso(tmpDto_parameterProposal));
			}
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public void mergeUnmanagedPoso(BirtParameterProposalDto dto, final BirtParameterProposal poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(BirtParameterProposalDto dto, final BirtParameterProposal poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set parameterProposal */
		ParameterDefinitionDto tmpDto_parameterProposal = dto.getParameterProposal();
		poso.setParameterProposal((ParameterDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_parameterProposal));

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2UnmanagedPoso(BirtParameterProposalDto dto, final BirtParameterProposal poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set parameterProposal */
		if(dto.isParameterProposalModified()){
			ParameterDefinitionDto tmpDto_parameterProposal = dto.getParameterProposal();
			poso.setParameterProposal((ParameterDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_parameterProposal));
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public BirtParameterProposal loadAndMergePoso(BirtParameterProposalDto dto)  throws ExpectedException {
		BirtParameterProposal poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(BirtParameterProposalDto dto, BirtParameterProposal poso)  {
	}


	public void postProcessCreateUnmanaged(BirtParameterProposalDto dto, BirtParameterProposal poso)  {
	}


	public void postProcessLoad(BirtParameterProposalDto dto, BirtParameterProposal poso)  {
	}


	public void postProcessMerge(BirtParameterProposalDto dto, BirtParameterProposal poso)  {
	}


	public void postProcessInstantiate(BirtParameterProposal poso)  {
	}



}
