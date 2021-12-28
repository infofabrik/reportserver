package net.datenwerke.rs.terminal.service.terminal.basecommands;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Transient;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.TableModelHelper;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.service.security.rights.Read;

public class DescCommand implements TerminalCommandHook {

	public static final String BASE_COMMAND = "desc";
	
	private final TableModelHelper tableModelHelper;
	private final EntityUtils entityUtils;
	private final Provider<EntityManagerFactory> entityManagerFactoryProvider;
	private final ReflectionService reflectionService;
	
	@Inject
	public DescCommand(
		TableModelHelper tableModelHelper,
		EntityUtils entityUtils,
		Provider<EntityManagerFactory> entityManagerFactoryProvider,
		ReflectionService reflectionService
		){
		
		/* store objects */
		this.tableModelHelper = tableModelHelper;
		this.entityUtils = entityUtils;
		this.entityManagerFactoryProvider = entityManagerFactoryProvider;
		this.reflectionService = reflectionService;
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	@CliHelpMessage(
		messageClass = TerminalMessages.class,
		name = BASE_COMMAND,
		description = "commandDesc_description",
		args = {
			@Argument(flag="w", description="commandDesc_descFlag")
		},
		nonOptArgs = {
			@NonOptArgument(name="type", description="commandDesc_typeArgument", mandatory=true),
			@NonOptArgument(name="type", description="commandDesc_objectsArgument", varArgs=true)
		}
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		List<String> arguments = parser.getNonOptionArguments();
		if(arguments.isEmpty())
			return new CommandResult("no argument given");
		
		/* try get class */
		try{
			String type = arguments.remove(0);
			
			Class<?> entityType = entityUtils.getEntityBySimpleName(type);
			
			if(null == entityType)
				return new CommandResult("Could not find entity called " + type);
			
			if(! entityUtils.isEntity(entityType))
				return new CommandResult(type + " is no entity");
			
			if(arguments.isEmpty())
				return getDescriptionFor(entityType);
			
			Collection<Object> objectList = session.getObjectResolver().getObjects(arguments, Read.class);
			RSTableModel desc = new RSTableModel();
			desc.setTableDefinition(tableModelHelper.tableDefinitionFromEntityType(entityType));
			for(Object object : objectList){
				if(entityType.isAssignableFrom(object.getClass())){
					desc.addDataRow(getDescriptionForObject(object, entityType));
				}
			}
				
			CommandResult result = new CommandResult(desc);
			if(parser.hasOption("w"))
				result.setDisplayMode(DisplayMode.WINDOW);
			
			return result;
		} catch(Exception e){
			CommandResult result = new CommandResult("Could not find description: " + e.getMessage());
			result.setCommitTransaction(false);
			return result;
		}
	}

	

	private RSStringTableRow getDescriptionForObject(Object object, Class<?> type) {
		List<String> row = new ArrayList<String>();
		
		for(Field f : entityUtils.getPersistantFields(type)){
			boolean isEntity = entityUtils.isEntity(f);
			if(reflectionService.isCollection(f))
				isEntity = entityUtils.isEntity(reflectionService.getGenericType(f));
			
			f.setAccessible(true);
			try {
				if(! isEntity)
					row.add(String.valueOf(f.get(object)));
				else {
					Object entity = f.get(object);
					if(null != entity){
						Collection entities = null;
						boolean isCollection = false;
						if(entity instanceof Collection){
							entities = (Collection) entity;
							isCollection = true;
						} else
							entities = Collections.singleton(entity);
						
						StringBuilder value = new StringBuilder();
						if(isCollection)
							value.append("[");
						
						boolean first = true;
						for(Object obj : entities){
							if(first)
								first = false;
							else
								value.append(",");
							
							Object id = entityUtils.getId(obj);
							if(null != id)
								value.append(String.valueOf(id.toString()));
							else
								value.append("null");
						}
						
						if(isCollection)
							value.append("]");
						
						row.add(value.toString());
					} else
						row.add("null");
				}
			} catch (Exception e) {
				row.add("--error--");
			}
		}
		
		return new RSStringTableRow(row);
	}

	private CommandResult getDescriptionFor(Class<?> type) {
		RSTableModel desc = new RSTableModel();
		for(Field f : entityUtils.getPersistantFields(type)){
			Class<?> fieldType = f.getType();
			String name = f.getName();
			
			boolean isStatic = Modifier.isStatic(f.getModifiers());
			boolean isTransient = f.isAnnotationPresent(Transient.class);
			boolean isEntity = entityUtils.isEntity(fieldType);
			
			String modifiers = "";
			if(isStatic || isTransient || isEntity){
				boolean addComma = false;
				if(isStatic){
					modifiers += "s";
					addComma = true;
				}
				
				if(isTransient){
					if(addComma)
						modifiers += ",";
					modifiers += "t";
					addComma = true;
				}
				
				if(isEntity){
					if(addComma)
						modifiers += ",";
					modifiers += "e";
				}
			}
			
			desc.addDataRow(new RSStringTableRow(modifiers, name, fieldType.getSimpleName()));
		}
		
		return new CommandResult(desc);
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
		
		if(consumes(autocompleteHelper.getParser(), session)){
			Metamodel model = entityManagerFactoryProvider.get().getMetamodel();
			List<String> autocompleteNames = new ArrayList<String>();
			for(ManagedType<?> type : model.getManagedTypes()){
				if(null != type.getJavaType())
					autocompleteNames.add(type.getJavaType().getSimpleName());
			}
			
			autocompleteHelper.addAutocompleteNamesForToken(2, autocompleteNames);
		}
	}

}
