package net.datenwerke.gf.service.localization.terminal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.inject.Inject;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.gf.service.localization.locale.LocalizationMessages;
import net.datenwerke.gf.service.localization.terminal.hooks.LocalizationSubCommandHook;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.vfs.locale.VfsMessages;

public class LocalizationImportMessagesSubCommand implements LocalizationSubCommandHook {

public static final String BASE_COMMAND = "importMessages";
	
	private final RemoteMessageService remoteMessageService;
	
	@Inject	
	public LocalizationImportMessagesSubCommand(RemoteMessageService remoteMessageService) {
		super();
		this.remoteMessageService = remoteMessageService;
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
		messageClass = LocalizationMessages.class,
		name = BASE_COMMAND,
		description = "commandLocalization_sub_importMessages_description",
		nonOptArgs = {
			@NonOptArgument(name="identifier", description="commandLocalization_sub_importMessages_arg1", mandatory=false)
		}
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		List<String> arguments = parser.getNonOptionArguments();
		if( arguments.size() > 1)
			throw new IllegalArgumentException();
		
		try{
			if(arguments.size() == 1)
				return loadFromFileServer(arguments.get(0), session);
			else
				return loadFromSystem();
		} catch(InvalidFormatException | IOException e){
			throw new IllegalArgumentException(e);
		}
	}

	private CommandResult loadFromSystem() throws InvalidFormatException, IOException {
		String relativePath = "/resources/translation-template.xlsx";
		
		File wbFile = new File("." + relativePath);
		if(! wbFile.exists()){
			wbFile = new File("./webapps/reportserver" + relativePath);
			if(! wbFile.exists() && System.getProperties().containsKey("catalina.base")){
				File base = new File(System.getProperty("catalina.base"));
			  	wbFile = new File(base, "/webapps/ROOT/" + relativePath);
				
				if(!wbFile.exists()){
					wbFile = new File(base, "/webapps/reportserver/" + relativePath);
				}
			}
			if(!wbFile.exists()){
				wbFile = new File("c:/work/tomcat/");
			}
		}
		
		if(! wbFile.exists())
			return new CommandResult("Could not find translation template");

		Workbook workbook = WorkbookFactory.create(wbFile);
		loadFrom(workbook);
		
		return new CommandResult("messages imported");
	}
	

	private CommandResult loadFromFileServer(String path, TerminalSession session) throws ObjectResolverException, InvalidFormatException, IOException {
		Object node = session.getObjectResolver().getObjects(path).iterator().next();
		
		if(null == node)
			return new CommandResult(VfsMessages.INSTANCE.fileNotFound());
		
		if(! (node instanceof FileServerFile))
			return new CommandResult(VfsMessages.INSTANCE.notSupported());
		
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(((FileServerFile)node).getData()));
		loadFrom(workbook);
		
		return new CommandResult("messages imported");
	}

	private void loadFrom(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);

		int lastRowNum = sheet.getLastRowNum();

		/* get header row */
		Row firstRow = sheet.getRow(0);
		int lastCellNum = firstRow.getLastCellNum();
		String[] head = new String[lastCellNum];
		for(int i = 0; i < lastCellNum; i++){
			head[i] = firstRow.getCell(i).getStringCellValue();
		}

		for(int i = 1; i <= lastRowNum; i++){
			Row row = sheet.getRow(i);
			for(int j = 3; j < head.length; j++){
				try{
					String msgClass = row.getCell(0).getStringCellValue();
					msgClass = msgClass.replaceFirst("^.*/net/datenwerke", "net.datenwerke");
					msgClass = msgClass.replace("/", ".");
					msgClass = msgClass.replace(".java", "");
					
					String key = row.getCell(1).getStringCellValue();
					String lang = head[j];
					String value = row.getCell(j).getStringCellValue();
					
					remoteMessageService.addTranslation(lang, msgClass, key, value, true);
				}catch(Exception e){}
			}
			
		}
	}


	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}

}
