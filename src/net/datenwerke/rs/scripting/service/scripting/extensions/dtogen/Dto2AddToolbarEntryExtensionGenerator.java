package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
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
import net.datenwerke.rs.scripting.client.scripting.dto.AddToolbarEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuEntryExtension;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddToolbarEntryExtension;
import net.datenwerke.rs.scripting.service.scripting.extensions.DisplayCondition;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddToolbarEntryExtensionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for AddToolbarEntryExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AddToolbarEntryExtensionGenerator implements Dto2PosoGenerator<AddToolbarEntryExtensionDto,AddToolbarEntryExtension> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2AddToolbarEntryExtensionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public AddToolbarEntryExtension loadPoso(AddToolbarEntryExtensionDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public AddToolbarEntryExtension instantiatePoso()  {
		AddToolbarEntryExtension poso = new AddToolbarEntryExtension();
		return poso;
	}

	public AddToolbarEntryExtension createPoso(AddToolbarEntryExtensionDto dto)  throws ExpectedException {
		AddToolbarEntryExtension poso = new AddToolbarEntryExtension();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public AddToolbarEntryExtension createUnmanagedPoso(AddToolbarEntryExtensionDto dto)  throws ExpectedException {
		AddToolbarEntryExtension poso = new AddToolbarEntryExtension();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(AddToolbarEntryExtensionDto dto, final AddToolbarEntryExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(AddToolbarEntryExtensionDto dto, final AddToolbarEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set columns */
		try{
			poso.setColumns(dto.getColumns() );
		} catch(NullPointerException e){
		}

		/*  set displayConditions */
		final List<DisplayCondition> col_displayConditions = new ArrayList<DisplayCondition>();
		/* load new data from dto */
		Collection<DisplayConditionDto> tmpCol_displayConditions = dto.getDisplayConditions();

		for(DisplayConditionDto refDto : tmpCol_displayConditions){
			col_displayConditions.add((DisplayCondition) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setDisplayConditions(col_displayConditions);

		/*  set groupEntries */
		final List<AddToolbarEntryExtension> col_groupEntries = new ArrayList<AddToolbarEntryExtension>();
		/* load new data from dto */
		Collection<AddToolbarEntryExtensionDto> tmpCol_groupEntries = dto.getGroupEntries();

		for(AddToolbarEntryExtensionDto refDto : tmpCol_groupEntries){
			col_groupEntries.add((AddToolbarEntryExtension) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setGroupEntries(col_groupEntries);

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set javaScript */
		poso.setJavaScript(dto.getJavaScript() );

		/*  set label */
		poso.setLabel(dto.getLabel() );

		/*  set large */
		try{
			poso.setLarge(dto.isLarge() );
		} catch(NullPointerException e){
		}

		/*  set left */
		try{
			poso.setLeft(dto.isLeft() );
		} catch(NullPointerException e){
		}

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

		/*  set toolbarName */
		poso.setToolbarName(dto.getToolbarName() );

	}

	protected void mergeProxy2Poso(AddToolbarEntryExtensionDto dto, final AddToolbarEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set columns */
		if(dto.isColumnsModified()){
			try{
				poso.setColumns(dto.getColumns() );
			} catch(NullPointerException e){
			}
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

		/*  set groupEntries */
		if(dto.isGroupEntriesModified()){
			final List<AddToolbarEntryExtension> col_groupEntries = new ArrayList<AddToolbarEntryExtension>();
			/* load new data from dto */
			Collection<AddToolbarEntryExtensionDto> tmpCol_groupEntries = null;
			tmpCol_groupEntries = dto.getGroupEntries();

			for(AddToolbarEntryExtensionDto refDto : tmpCol_groupEntries){
				col_groupEntries.add((AddToolbarEntryExtension) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setGroupEntries(col_groupEntries);
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

		/*  set large */
		if(dto.isLargeModified()){
			try{
				poso.setLarge(dto.isLarge() );
			} catch(NullPointerException e){
			}
		}

		/*  set left */
		if(dto.isLeftModified()){
			try{
				poso.setLeft(dto.isLeft() );
			} catch(NullPointerException e){
			}
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

		/*  set toolbarName */
		if(dto.isToolbarNameModified()){
			poso.setToolbarName(dto.getToolbarName() );
		}

	}

	public void mergeUnmanagedPoso(AddToolbarEntryExtensionDto dto, final AddToolbarEntryExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(AddToolbarEntryExtensionDto dto, final AddToolbarEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set columns */
		try{
			poso.setColumns(dto.getColumns() );
		} catch(NullPointerException e){
		}

		/*  set displayConditions */
		final List<DisplayCondition> col_displayConditions = new ArrayList<DisplayCondition>();
		/* load new data from dto */
		Collection<DisplayConditionDto> tmpCol_displayConditions = dto.getDisplayConditions();

		for(DisplayConditionDto refDto : tmpCol_displayConditions){
			col_displayConditions.add((DisplayCondition) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setDisplayConditions(col_displayConditions);

		/*  set groupEntries */
		final List<AddToolbarEntryExtension> col_groupEntries = new ArrayList<AddToolbarEntryExtension>();
		/* load new data from dto */
		Collection<AddToolbarEntryExtensionDto> tmpCol_groupEntries = dto.getGroupEntries();

		for(AddToolbarEntryExtensionDto refDto : tmpCol_groupEntries){
			col_groupEntries.add((AddToolbarEntryExtension) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setGroupEntries(col_groupEntries);

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set javaScript */
		poso.setJavaScript(dto.getJavaScript() );

		/*  set label */
		poso.setLabel(dto.getLabel() );

		/*  set large */
		try{
			poso.setLarge(dto.isLarge() );
		} catch(NullPointerException e){
		}

		/*  set left */
		try{
			poso.setLeft(dto.isLeft() );
		} catch(NullPointerException e){
		}

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

		/*  set toolbarName */
		poso.setToolbarName(dto.getToolbarName() );

	}

	protected void mergeProxy2UnmanagedPoso(AddToolbarEntryExtensionDto dto, final AddToolbarEntryExtension poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set columns */
		if(dto.isColumnsModified()){
			try{
				poso.setColumns(dto.getColumns() );
			} catch(NullPointerException e){
			}
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

		/*  set groupEntries */
		if(dto.isGroupEntriesModified()){
			final List<AddToolbarEntryExtension> col_groupEntries = new ArrayList<AddToolbarEntryExtension>();
			/* load new data from dto */
			Collection<AddToolbarEntryExtensionDto> tmpCol_groupEntries = null;
			tmpCol_groupEntries = dto.getGroupEntries();

			for(AddToolbarEntryExtensionDto refDto : tmpCol_groupEntries){
				col_groupEntries.add((AddToolbarEntryExtension) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setGroupEntries(col_groupEntries);
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

		/*  set large */
		if(dto.isLargeModified()){
			try{
				poso.setLarge(dto.isLarge() );
			} catch(NullPointerException e){
			}
		}

		/*  set left */
		if(dto.isLeftModified()){
			try{
				poso.setLeft(dto.isLeft() );
			} catch(NullPointerException e){
			}
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

		/*  set toolbarName */
		if(dto.isToolbarNameModified()){
			poso.setToolbarName(dto.getToolbarName() );
		}

	}

	public AddToolbarEntryExtension loadAndMergePoso(AddToolbarEntryExtensionDto dto)  throws ExpectedException {
		AddToolbarEntryExtension poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(AddToolbarEntryExtensionDto dto, AddToolbarEntryExtension poso)  {
	}


	public void postProcessCreateUnmanaged(AddToolbarEntryExtensionDto dto, AddToolbarEntryExtension poso)  {
	}


	public void postProcessLoad(AddToolbarEntryExtensionDto dto, AddToolbarEntryExtension poso)  {
	}


	public void postProcessMerge(AddToolbarEntryExtensionDto dto, AddToolbarEntryExtension poso)  {
	}


	public void postProcessInstantiate(AddToolbarEntryExtension poso)  {
	}



}
