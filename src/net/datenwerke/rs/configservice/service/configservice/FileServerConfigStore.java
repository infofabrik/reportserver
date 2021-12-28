package net.datenwerke.rs.configservice.service.configservice;

import javax.inject.Inject;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.io.IOUtils;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;

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
         if (null == object || !(object instanceof FileServerFile))
            return null;

         /* get node contents */
         FileServerFile file = (FileServerFile) object;
         byte[] data = file.getData();

         if (null == data)
            return null;

         /* load config */
         BasicConfigurationBuilder<XMLConfiguration> builder = createBaseConfig();
         XMLConfiguration config = builder.getConfiguration();
         FileHandler fileHandler = new FileHandler(config);
         fileHandler.load(IOUtils.toInputStream(new String(data)));

         return config;

      } catch (ObjectResolverException e) {
         return null;
      }
   }

}
