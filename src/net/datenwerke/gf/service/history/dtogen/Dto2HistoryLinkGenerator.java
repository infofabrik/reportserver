package net.datenwerke.gf.service.history.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.dtogen.Dto2HistoryLinkGenerator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for HistoryLink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2HistoryLinkGenerator implements Dto2PosoGenerator<HistoryLinkDto,HistoryLink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2HistoryLinkGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public HistoryLink loadPoso(HistoryLinkDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public HistoryLink instantiatePoso()  {
		HistoryLink poso = new HistoryLink();
		return poso;
	}

	public HistoryLink createPoso(HistoryLinkDto dto)  throws ExpectedException {
		HistoryLink poso = new HistoryLink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public HistoryLink createUnmanagedPoso(HistoryLinkDto dto)  throws ExpectedException {
		HistoryLink poso = new HistoryLink();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(HistoryLinkDto dto, final HistoryLink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(HistoryLinkDto dto, final HistoryLink poso)  throws ExpectedException {
		/*  set historyLinkBuilderIcon */
		poso.setHistoryLinkBuilderIcon(dto.getHistoryLinkBuilderIcon() );

		/*  set historyLinkBuilderId */
		poso.setHistoryLinkBuilderId(dto.getHistoryLinkBuilderId() );

		/*  set historyLinkBuilderName */
		poso.setHistoryLinkBuilderName(dto.getHistoryLinkBuilderName() );

		/*  set historyToken */
		poso.setHistoryToken(dto.getHistoryToken() );

		/*  set objectCaption */
		poso.setObjectCaption(dto.getObjectCaption() );

	}

	protected void mergeProxy2Poso(HistoryLinkDto dto, final HistoryLink poso)  throws ExpectedException {
		/*  set historyLinkBuilderIcon */
		if(dto.isHistoryLinkBuilderIconModified()){
			poso.setHistoryLinkBuilderIcon(dto.getHistoryLinkBuilderIcon() );
		}

		/*  set historyLinkBuilderId */
		if(dto.isHistoryLinkBuilderIdModified()){
			poso.setHistoryLinkBuilderId(dto.getHistoryLinkBuilderId() );
		}

		/*  set historyLinkBuilderName */
		if(dto.isHistoryLinkBuilderNameModified()){
			poso.setHistoryLinkBuilderName(dto.getHistoryLinkBuilderName() );
		}

		/*  set historyToken */
		if(dto.isHistoryTokenModified()){
			poso.setHistoryToken(dto.getHistoryToken() );
		}

		/*  set objectCaption */
		if(dto.isObjectCaptionModified()){
			poso.setObjectCaption(dto.getObjectCaption() );
		}

	}

	public void mergeUnmanagedPoso(HistoryLinkDto dto, final HistoryLink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(HistoryLinkDto dto, final HistoryLink poso)  throws ExpectedException {
		/*  set historyLinkBuilderIcon */
		poso.setHistoryLinkBuilderIcon(dto.getHistoryLinkBuilderIcon() );

		/*  set historyLinkBuilderId */
		poso.setHistoryLinkBuilderId(dto.getHistoryLinkBuilderId() );

		/*  set historyLinkBuilderName */
		poso.setHistoryLinkBuilderName(dto.getHistoryLinkBuilderName() );

		/*  set historyToken */
		poso.setHistoryToken(dto.getHistoryToken() );

		/*  set objectCaption */
		poso.setObjectCaption(dto.getObjectCaption() );

	}

	protected void mergeProxy2UnmanagedPoso(HistoryLinkDto dto, final HistoryLink poso)  throws ExpectedException {
		/*  set historyLinkBuilderIcon */
		if(dto.isHistoryLinkBuilderIconModified()){
			poso.setHistoryLinkBuilderIcon(dto.getHistoryLinkBuilderIcon() );
		}

		/*  set historyLinkBuilderId */
		if(dto.isHistoryLinkBuilderIdModified()){
			poso.setHistoryLinkBuilderId(dto.getHistoryLinkBuilderId() );
		}

		/*  set historyLinkBuilderName */
		if(dto.isHistoryLinkBuilderNameModified()){
			poso.setHistoryLinkBuilderName(dto.getHistoryLinkBuilderName() );
		}

		/*  set historyToken */
		if(dto.isHistoryTokenModified()){
			poso.setHistoryToken(dto.getHistoryToken() );
		}

		/*  set objectCaption */
		if(dto.isObjectCaptionModified()){
			poso.setObjectCaption(dto.getObjectCaption() );
		}

	}

	public HistoryLink loadAndMergePoso(HistoryLinkDto dto)  throws ExpectedException {
		HistoryLink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(HistoryLinkDto dto, HistoryLink poso)  {
	}


	public void postProcessCreateUnmanaged(HistoryLinkDto dto, HistoryLink poso)  {
	}


	public void postProcessLoad(HistoryLinkDto dto, HistoryLink poso)  {
	}


	public void postProcessMerge(HistoryLinkDto dto, HistoryLink poso)  {
	}


	public void postProcessInstantiate(HistoryLink poso)  {
	}



}
