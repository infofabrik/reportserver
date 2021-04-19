package net.datenwerke.rs.incubator.service.versioning.terminal.commands;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.incubator.service.versioning.VersioningService;
import net.datenwerke.rs.incubator.service.versioning.entities.Revision;
import net.datenwerke.rs.incubator.service.versioning.hooks.RevSubCommandHook;
import net.datenwerke.rs.incubator.service.versioning.locale.VersioningMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.rs.utils.entitydiff.EntityDiffService;
import net.datenwerke.rs.utils.entitydiff.result.EntityDiffResult;
import net.datenwerke.rs.utils.entitydiff.result.FieldDiffResult;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class ListSubCommand implements RevSubCommandHook{
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public static final String BASE_COMMAND = "list";
	
	private final VersioningService versioningService;
	private final EntityDiffService entityDiffService;
	private final EntityUtils entityUtils;
	
	@Inject
	public ListSubCommand(
			VersioningService versioningService, 
			EntityDiffService entityDiffService, 
			EntityUtils entityUtils
	) {
		this.versioningService = versioningService;
		this.entityDiffService = entityDiffService;
		this.entityUtils = entityUtils;
	}
	
	@Override
	public String getBaseCommand() {
		return BASE_COMMAND;
	}

	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	@CliHelpMessage(
			messageClass = VersioningMessages.class,
			name = BASE_COMMAND,
			description = "commandRev_sub_list_description",
			args = {
				@Argument(flag="n", hasValue=true, valueName="number of revisions", description="commandRev_sub_list_flagN", mandatory=false),
				@Argument(flag="a", hasValue=false, valueName="all revs", description="commandRev_sub_list_flagA", mandatory=false)
			},
			nonOptArgs = {
				@NonOptArgument(name="entity", description="commandRev_sub_list_arg1", mandatory = true)
			}
		)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		String argStr = "n:a";
		
		/* arguments */
		int numberOfRevisions = 5;
		if(parser.hasOption("a", argStr))
			numberOfRevisions = Integer.MAX_VALUE;
		else if(parser.hasOption("n", argStr))
			numberOfRevisions= Integer.parseInt(String.valueOf(parser.parse(argStr).valueOf("n")));
		
		List<String> arguments = parser.getNonOptionArguments();
		if(1 > arguments.size())
			throw new IllegalArgumentException();
		
		/* locate entity */
		String entityLocator = arguments.get(0);
		Collection<Object> entityCandidates = new HashSet<Object>();
		try{
			ObjectResolverDeamon objectResolver = session.getObjectResolver();
			entityCandidates = objectResolver.getObjects(entityLocator, Read.class);
		}catch(Exception e){
			logger.warn( e.getMessage(), e);
		}

		Object locatedObject;
		Set<Revision> revisions;

		try{
			if(entityCandidates.size() != 1){
				/* deleted entity? */
				String locationStr = arguments.get(0);
				String[] parts = locationStr.split(":");
				String entityName = parts[1 - (parts.length == 2 ? 1 : 0)];
				String id = parts[2  - (parts.length == 2 ? 1 : 0)];
				
				Class<?> type = entityUtils.getEntityBySimpleName(entityName);
				
				TreeSet<Number> revisionNumbers = new TreeSet<Number>(versioningService.getRevisionNumbers(type, Long.valueOf(id)));
				
				if(revisionNumbers.isEmpty())
					throw new IllegalArgumentException("Failed to locate entities");
				
				locatedObject = versioningService.getAtRevision(type, Long.parseLong(id), revisionNumbers.first());
				revisions = versioningService.getRevisions(revisionNumbers);
			}else{
				locatedObject = entityCandidates.iterator().next();
				revisions = versioningService.getRevisions(locatedObject);
			}
		} catch(Exception e){
			throw new IllegalArgumentException(e);
		}
		
		RSTableModel table = new RSTableModel();
		TableDefinition td = new TableDefinition(Arrays.asList("Revision", "Date", "User", "Change"), Arrays.asList(Object.class, String.class, Object.class, String.class));
		table.setTableDefinition(td);

		if(! revisions.isEmpty()){
			Iterator<Revision> iterator = revisions.iterator();
			
			Object last = versioningService.getAtRevision(locatedObject.getClass(), locatedObject, iterator.next().getId());
			while (iterator.hasNext()) {
				Revision rev = iterator.next();
	
				/* make sure that we only load number of revision many revisions */
				if(numberOfRevisions <= 0)
					break;
				else
					numberOfRevisions--;
				
				/* load revision */
				Object current = versioningService.getAtRevision(locatedObject.getClass(), locatedObject, rev.getId());
				User u = versioningService.getAuditReader().find(User.class, rev.getUserId(), rev.getId());
				
				String diffStr = "";
				
				if(null == current)
					diffStr += "[deleted]";
				else {
					EntityDiffResult diff = entityDiffService.diff(current, last);
					for(FieldDiffResult fr: diff.getFieldResults()){
						if(!fr.isEqual()){
							if(diffStr.length() > 0)
								diffStr += ", ";
							diffStr += fr.getField().getName();
						}
					}
				}
				
				last = current;
				
				table.addDataRow(new RSStringTableRow(String.valueOf(rev.getId()), SimpleDateFormat.getDateTimeInstance().format(rev.getRevisionDate()), String.valueOf(u), diffStr));	
			}
		}
		
		
		CommandResult cr = new CommandResult();
		cr.addResultTable(table);
		return cr;
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}

}
