package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuEntryExtension;
import net.datenwerke.rs.scripting.service.scripting.extensions.DisplayCondition;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddMenuEntryExtensionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for AddMenuEntryExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AddMenuEntryExtensionGenerator implements Dto2PosoGenerator<AddMenuEntryExtensionDto,AddMenuEntryExtension> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2AddMenuEntryExtensionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public AddMenuEntryExtension loadPoso(AddMenuEntryExtensionDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public AddMenuEntryExtension instantiatePoso()  {
		AddMenuEntryExtension poso = new AddMenuEntryExtension();
		return poso;
	}

	public AddMenuEntryExtension createPoso(AddMenuEntryExtensionDto dto)  throws ExpectedException {
		AddMenuEntryExtension poso = new AddMenuEntryExtension();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public AddMenuEntryExtension createUnmanagedPoso(AddMenuEntryExtensionDto dto)  throws ExpectedException {
		AddMenuEntryExtension poso = new AddMenuEntryExtension();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(AddMenuEntryExtensionDto dto, final AddMenuEntryExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(AddMenuEntryExtensionDto dto, final AddMenuEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set displayConditions */
		final List<DisplayCondition> col_displayConditions = new ArrayList<DisplayCondition>();
		/* load new data from dto */
		Collection<DisplayConditionDto> tmpCol_displayConditions = dto.getDisplayConditions();

		for(DisplayConditionDto refDto : tmpCol_displayConditions){
			col_displayConditions.add((DisplayCondition) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setDisplayConditions(col_displayConditions);

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set javaScript */
		poso.setJavaScript(dto.getJavaScript() );

		/*  set label */
		poso.setLabel(dto.getLabel() );

		/*  set menuName */
		poso.setMenuName(dto.getMenuName() );

		/*  set scriptLocation */
		poso.setScriptLocation(dto.getScriptLocation() );

		/*  set subMenuEntries */
		final List<AddMenuEntryExtension> col_subMenuEntries = new ArrayList<AddMenuEntryExtension>();
		/* load new data from dto */
		Collection<AddMenuEntryExtensionDto> tmpCol_subMenuEntries = dto.getSubMenuEntries();

		for(AddMenuEntryExtensionDto refDto : tmpCol_subMenuEntries){
			col_subMenuEntries.add((AddMenuEntryExtension) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setSubMenuEntries(col_subMenuEntries);

	}

	protected void mergeProxy2Poso(AddMenuEntryExtensionDto dto, final AddMenuEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set displayConditions */
		if(dto.isDisplayConditionsModified()){
			final List<DisplayCondition> col_displayConditions = new ArrayList<DisplayCondition>();
			/* load new data from dto */
			Collection<DisplayConditionDto> tmpCol_displayConditions = null;
			tmpCol_displayConditions = dto.getDisplayConditions();

			for(DisplayConditionDto refDto : tmpCol_displayConditions){
				col_displayConditions.add((DisplayCondition) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setDisplayConditions(col_displayConditions);
		}

		/*  set icon */
		if(dto.isIconModified()){
			poso.setIcon(dto.getIcon() );
		}

		/*  set javaScript */
		if(dto.isJavaScriptModified()){
			poso.setJavaScript(dto.getJavaScript() );
		}

		/*  set label */
		if(dto.isLabelModified()){
			poso.setLabel(dto.getLabel() );
		}

		/*  set menuName */
		if(dto.isMenuNameModified()){
			poso.setMenuName(dto.getMenuName() );
		}

		/*  set scriptLocation */
		if(dto.isScriptLocationModified()){
			poso.setScriptLocation(dto.getScriptLocation() );
		}

		/*  set subMenuEntries */
		if(dto.isSubMenuEntriesModified()){
			final List<AddMenuEntryExtension> col_subMenuEntries = new ArrayList<AddMenuEntryExtension>();
			/* load new data from dto */
			Collection<AddMenuEntryExtensionDto> tmpCol_subMenuEntries = null;
			tmpCol_subMenuEntries = dto.getSubMenuEntries();

			for(AddMenuEntryExtensionDto refDto : tmpCol_subMenuEntries){
				col_subMenuEntries.add((AddMenuEntryExtension) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setSubMenuEntries(col_subMenuEntries);
		}

	}

	public void mergeUnmanagedPoso(AddMenuEntryExtensionDto dto, final AddMenuEntryExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(AddMenuEntryExtensionDto dto, final AddMenuEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set displayConditions */
		final List<DisplayCondition> col_displayConditions = new ArrayList<DisplayCondition>();
		/* load new data from dto */
		Collection<DisplayConditionDto> tmpCol_displayConditions = dto.getDisplayConditions();

		for(DisplayConditionDto refDto : tmpCol_displayConditions){
			col_displayConditions.add((DisplayCondition) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setDisplayConditions(col_displayConditions);

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set javaScript */
		poso.setJavaScript(dto.getJavaScript() );

		/*  set label */
		poso.setLabel(dto.getLabel() );

		/*  set menuName */
		poso.setMenuName(dto.getMenuName() );

		/*  set scriptLocation */
		poso.setScriptLocation(dto.getScriptLocation() );

		/*  set subMenuEntries */
		final List<AddMenuEntryExtension> col_subMenuEntries = new ArrayList<AddMenuEntryExtension>();
		/* load new data from dto */
		Collection<AddMenuEntryExtensionDto> tmpCol_subMenuEntries = dto.getSubMenuEntries();

		for(AddMenuEntryExtensionDto refDto : tmpCol_subMenuEntries){
			col_subMenuEntries.add((AddMenuEntryExtension) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setSubMenuEntries(col_subMenuEntries);

	}

	protected void mergeProxy2UnmanagedPoso(AddMenuEntryExtensionDto dto, final AddMenuEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set displayConditions */
		if(dto.isDisplayConditionsModified()){
			final List<DisplayCondition> col_displayConditions = new ArrayList<DisplayCondition>();
			/* load new data from dto */
			Collection<DisplayConditionDto> tmpCol_displayConditions = null;
			tmpCol_displayConditions = dto.getDisplayConditions();

			for(DisplayConditionDto refDto : tmpCol_displayConditions){
				col_displayConditions.add((DisplayCondition) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setDisplayConditions(col_displayConditions);
		}

		/*  set icon */
		if(dto.isIconModified()){
			poso.setIcon(dto.getIcon() );
		}

		/*  set javaScript */
		if(dto.isJavaScriptModified()){
			poso.setJavaScript(dto.getJavaScript() );
		}

		/*  set label */
		if(dto.isLabelModified()){
			poso.setLabel(dto.getLabel() );
		}

		/*  set menuName */
		if(dto.isMenuNameModified()){
			poso.setMenuName(dto.getMenuName() );
		}

		/*  set scriptLocation */
		if(dto.isScriptLocationModified()){
			poso.setScriptLocation(dto.getScriptLocation() );
		}

		/*  set subMenuEntries */
		if(dto.isSubMenuEntriesModified()){
			final List<AddMenuEntryExtension> col_subMenuEntries = new ArrayList<AddMenuEntryExtension>();
			/* load new data from dto */
			Collection<AddMenuEntryExtensionDto> tmpCol_subMenuEntries = null;
			tmpCol_subMenuEntries = dto.getSubMenuEntries();

			for(AddMenuEntryExtensionDto refDto : tmpCol_subMenuEntries){
				col_subMenuEntries.add((AddMenuEntryExtension) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setSubMenuEntries(col_subMenuEntries);
		}

	}

	public AddMenuEntryExtension loadAndMergePoso(AddMenuEntryExtensionDto dto)  throws ExpectedException {
		AddMenuEntryExtension poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(AddMenuEntryExtensionDto dto, AddMenuEntryExtension poso)  {
	}


	public void postProcessCreateUnmanaged(AddMenuEntryExtensionDto dto, AddMenuEntryExtension poso)  {
	}


	public void postProcessLoad(AddMenuEntryExtensionDto dto, AddMenuEntryExtension poso)  {
	}


	public void postProcessMerge(AddMenuEntryExtensionDto dto, AddMenuEntryExtension poso)  {
	}


	public void postProcessInstantiate(AddMenuEntryExtension poso)  {
	}



}
