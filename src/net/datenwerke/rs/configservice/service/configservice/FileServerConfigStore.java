package net.datenwerke.rs.configservice.service.configservice;

import javax.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.io.IOUtils;

public class FileServerConfigStore extends AbstractConfigStore {


	private TerminalService terminalService;

	@Inject
	public FileServerConfigStore(TerminalService terminalService) {
		this.terminalService = terminalService;
	}

	@Override
	public HierarchicalConfiguration loadConfig(String identifier) throws ConfigurationException {
		try {
			/* get fileserver node */
			Object object = terminalService.getObjectByLocation("/fileserver/etc/" + identifier, false);
			if(null == object || ! (object instanceof FileServerFile))
				return null;

			/* get node contents */
			FileServerFile file = (FileServerFile) object;
			byte[] data = file.getData();
			
			if(null == data)
				return null;
			
			/* load config */
			HierarchicalConfiguration config = createBaseConfig();
			((FileConfiguration)config).load(IOUtils.toInputStream(new String(data)));

			return config;
			
		} catch (ObjectResolverException e) {
			return null;
		}
	}

}
