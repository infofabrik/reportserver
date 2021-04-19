package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.lfs;

import java.io.File;
import java.util.List;

import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser.CurrentArgument;

public class LfsAutoCompleteHelper {
	
	public static void configureAutoComplete(int tokenNum, AutocompleteHelper autocompleteHelper){
		try{
			CommandParser scp = autocompleteHelper.getParser().getSubCommandParser();
			List<String> noa = scp.getNonOptionArguments();

			CurrentArgument arg = autocompleteHelper.getParser().getCurrentArgument(autocompleteHelper.getCursorPosition());
			String fullcmd = arg.getArgument();
			String cmd = fullcmd;
			if(cmd.contains("/")){
				cmd = cmd.substring(cmd.lastIndexOf("/") + 1);
			}

			String basePath = "";
			if(noa.size() > 0){
				basePath = scp.getNonOptionArguments().get(noa.size() - 1);
			}
			File baseDir = new File(basePath).getCanonicalFile();
			if(!baseDir.exists() || !baseDir.isDirectory()){
				baseDir = baseDir.getParentFile();
			}

			for(File f : baseDir.listFiles()){
				if(f.isDirectory()){
					String name = f.getName();
					if(name.startsWith(cmd)){
						if(name.length() > cmd.length()){
							name = name.substring(cmd.length()	);
						}

						name = fullcmd + name;
						autocompleteHelper.addAutocompleteNamesForToken(tokenNum, name +"/");
					}
				}
			}
		}catch(Exception e){
		}
	}

}
