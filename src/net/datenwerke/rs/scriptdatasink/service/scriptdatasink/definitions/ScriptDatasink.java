package net.datenwerke.rs.scriptdatasink.service.scriptdatasink.definitions;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.ScriptDatasinkService;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.locale.ScriptDatasinkMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.misc.DateUtils;

/**
 * Used to define script datasinks that can be used in ReportServer
 * to send reports to a given datasink defined by a script.
 */
@Entity
@Table(name = "SCRIPT_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto", 
      icon = "server"
      )
@InstanceDescription(
      msgLocation = ScriptDatasinkMessages.class, 
      objNameKey = "scriptDatasinkTypeName", 
      icon = "file-code-o"
      )
@Indexed
public class ScriptDatasink extends DatasinkDefinition {
   /**
    * 
    */
   private static final long serialVersionUID = -2439404920332697760L;
   
   @Inject
   protected static Provider<ScriptDatasinkService> basicDatasinkService;

   @ExposeToClient
   @ManyToOne
   private FileServerFile script;
   
   public FileServerFile getScript() {
      return script;
   }
   
   public void setScript(FileServerFile script) {
      this.script = script;
   }

   @Override
   public BasicDatasinkService getDatasinkService() {
      return basicDatasinkService.get();
   }

   @Override
   public DatasinkConfiguration getDefaultConfiguration(String fileEnding) {
      return new DatasinkFilenameConfig() {
         @Override
         public String getFilename() {
            return DEFAULT_EXPORT_FILENAME + DateUtils.formatCurrentDate() + fileEnding;
         }
      };
   }

}