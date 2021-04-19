package net.datenwerke.rs.uservariables.server.uservariables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.uservariables.rpc.UserVariablesRpcService;
import net.datenwerke.rs.uservariables.service.genrights.UserVariableAdminViewSecurityTarget;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

/**
 * 
 *
 */
@Singleton
public class UserVariablesRpcServiceImpl extends SecuredRemoteServiceServlet
		implements UserVariablesRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5257479459664968787L;

	private final UserVariableService userVariableService;
	private final DtoService dtoService;
	
	@Inject
	public UserVariablesRpcServiceImpl(
		UserVariableService userVariableService,
		DtoService dtoService	
		){
		
		this.userVariableService = userVariableService;
		this.dtoService = dtoService;
	}

	@SecurityChecked(
		genericTargetVerification = { 
			@GenericTargetVerification(
				target = UserVariableAdminViewSecurityTarget.class, 
				verify = @RightsVerification(rights = Write.class)) 
		})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public UserVariableDefinitionDto addUserVariableDefinition(UserVariableDefinitionDto definitionDto) throws ServerCallFailedException {
		/* create entity */
		UserVariableDefinition definition = (UserVariableDefinition) dtoService.instantiatePoso(definitionDto.getClass());
		
		/* store variable in database */
		userVariableService.persist(definition);
		
		/* return resulting dto */
		return (UserVariableDefinitionDto) dtoService.createDto(definition);
	}

	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = UserVariableAdminViewSecurityTarget.class, 
					verify = @RightsVerification(rights = Write.class)) 
			})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public void removeUserVariableDefinitions(Collection<UserVariableDefinitionDto> definitionDtos, boolean force)
			throws ServerCallFailedException {
		/* load real definitions */
		Collection<UserVariableDefinition> realDefinitions = new HashSet<UserVariableDefinition>();
		for(UserVariableDefinitionDto definitionDto : definitionDtos){
			UserVariableDefinition definition = userVariableService.getUserVariableDefinitionById(definitionDto.getId());
			if(null == definition)
				throw new IllegalStateException("Could not load user variable with id: " + definitionDto.getId()); //$NON-NLS-1$
			
			realDefinitions.add(definition);
		}

		/* remove variable */
		if(! force)
			for(UserVariableDefinition definition : realDefinitions)
				userVariableService.remove(definition);
		else
			for(UserVariableDefinition definition : realDefinitions)
				userVariableService.forceRemove(definition);
	}

	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = UserVariableAdminViewSecurityTarget.class, 
					verify = @RightsVerification(rights = Write.class)) 
			})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public UserVariableDefinitionDto updateUserVariableDefinition(UserVariableDefinitionDto definitionDto)
			throws ServerCallFailedException {
		/* load real definition */
		UserVariableDefinition definition = userVariableService.getUserVariableDefinitionById(definitionDto.getId());
		if(null == definition)
			throw new IllegalStateException("Could not load user variable with id: " + definitionDto.getId()); //$NON-NLS-1$

		/* copy parameters */
		dtoService.mergePoso(definitionDto, definition);
		
		/* merge definition */
		userVariableService.merge(definition);
		
		/* return resulting dto */
		return (UserVariableDefinitionDto) dtoService.createDto(definition);
	}

	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = UserVariableAdminViewSecurityTarget.class, 
					verify = @RightsVerification(rights = Read.class)) 
			})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public ListLoadResult<UserVariableDefinitionDto> getDefinedUserVariableDefinitions()
			throws ServerCallFailedException {
		Collection<UserVariableDefinition> variables = userVariableService.getDefinedVariableDefinitions();
		
		List<UserVariableDefinitionDto> variableDtos = new ArrayList<UserVariableDefinitionDto>();
		for(UserVariableDefinition definition : variables)
			variableDtos.add((UserVariableDefinitionDto) dtoService.createDto(definition));
		
		return new ListLoadResultBean<UserVariableDefinitionDto>(variableDtos);
	}

	@SecurityChecked(
			argumentVerification = {
				@ArgumentVerification(
					name="node", 
					isDto=true,
					verify = @RightsVerification(rights=Read.class))	
			},
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = UserVariableAdminViewSecurityTarget.class, 
					verify = @RightsVerification(rights = Read.class)) 
			})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<UserVariableInstanceDto> getDefinedUserVariableInstances(@Named("node") AbstractUserManagerNodeDto nodeDto) throws ServerCallFailedException {
		/* load real node */
		AbstractUserManagerNode node = (AbstractUserManagerNode) dtoService.loadPoso(nodeDto);
		
		Collection<UserVariableInstance> variables = userVariableService.getDefinedInstancesFor(node);
		
		List<UserVariableInstanceDto> variableDtos = new ArrayList<UserVariableInstanceDto>();
		for(UserVariableInstance definition : variables)
			variableDtos.add((UserVariableInstanceDto) dtoService.createDto(definition));
		
		return variableDtos;	
	}

	@SecurityChecked(
		argumentVerification = {
			@ArgumentVerification(
					name="node", 
					isDto=true,
					verify = @RightsVerification(rights=Read.class))	
		},
		genericTargetVerification = { 
			@GenericTargetVerification(
				target = UserVariableAdminViewSecurityTarget.class, 
				verify = @RightsVerification(rights = Read.class)) 
		})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<UserVariableInstanceDto> getInheritedUserVariableInstances(@Named("node") AbstractUserManagerNodeDto nodeDto) throws ServerCallFailedException {
		/* load real node */
		AbstractUserManagerNode node = (AbstractUserManagerNode) dtoService.loadPoso(nodeDto);
		
		List<UserVariableInstance> variables = userVariableService.getInheritedInstancesFor(node);
		
		List<UserVariableInstanceDto> variableDtos = new ArrayList<UserVariableInstanceDto>();
		for(UserVariableInstance definition : variables)
			variableDtos.add((UserVariableInstanceDto) dtoService.createDto(definition));
		
		return variableDtos;	
	}

	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = UserVariableAdminViewSecurityTarget.class, 
					verify = @RightsVerification(rights = Write.class)) 
			})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<UserVariableInstanceDto> addUserVariableInstances(List<UserVariableDefinitionDto> definitionDtos, AbstractUserManagerNodeDto nodeDto)
			throws ServerCallFailedException {
		List<UserVariableInstanceDto> result = new ArrayList<UserVariableInstanceDto>();
		
		/* load real node and definition */
		for(UserVariableDefinitionDto definitionDto : definitionDtos){
			AbstractUserManagerNode node = (AbstractUserManagerNode) dtoService.loadPoso(nodeDto);
			UserVariableDefinition definition = userVariableService.getUserVariableDefinitionById(definitionDto.getId());
			if(null == definition)
				throw new IllegalStateException("Could not load user variable with id: " + definitionDto.getId()); //$NON-NLS-1$
	
			/* test that instance has not already been created */
			if(userVariableService.hasVariableInstance(node, definition))
				throw new ExpectedException("An instance for this variable has already been assigned."); //$NON-NLS-1$
			
			/* create instance and configure it */
			UserVariableInstance instance = definition.createVariableInstance();
			instance.setFolk(node);
			
			/* persist instance */
			userVariableService.persist(instance);
			
			/* create dto and add it to list*/
			result.add((UserVariableInstanceDto) dtoService.createDto(instance));
		}
		
		return result;
	}

	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = UserVariableAdminViewSecurityTarget.class, 
					verify = @RightsVerification(rights = Write.class)) 
			})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public UserVariableInstanceDto updateUserVariableInstance(UserVariableInstanceDto instanceDto)
			throws ServerCallFailedException {
		/* load real instance */
		UserVariableInstance instance = userVariableService.getUserVariableInstanceById(instanceDto.getId());
		if(null == instance)
			throw new IllegalArgumentException("Could not load instance with id: " + instanceDto.getId()); //$NON-NLS-1$
		
		/* copy data */
		dtoService.mergePoso(instanceDto, instance);
		
		/* merge object */
		userVariableService.merge(instance);
		
		/* return new dto */
		return (UserVariableInstanceDto) dtoService.createDto(instance);
	}

	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = UserVariableAdminViewSecurityTarget.class, 
					verify = @RightsVerification(rights = Write.class)) 
			})
	@Override
	@Transactional(rollbackOn={Exception.class})
	public void removeUserVariableInstances(Collection<UserVariableInstanceDto> instanceDtos)
			throws ServerCallFailedException {
		/* load real instances */
		Collection<UserVariableInstance> realInstances = new HashSet<UserVariableInstance>();
		for(UserVariableInstanceDto instanceDto : instanceDtos){
			UserVariableInstance definition = userVariableService.getUserVariableInstanceById(instanceDto.getId());
			if(null == definition)
				throw new IllegalStateException("Could not load user variable with id: " + instanceDto.getId()); //$NON-NLS-1$
			
			realInstances.add(definition);
		}

		/* remove variable */
		for(UserVariableInstance instance : realInstances)
			userVariableService.remove(instance);
	}
}
