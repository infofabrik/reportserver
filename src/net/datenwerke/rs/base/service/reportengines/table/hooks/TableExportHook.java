package net.datenwerke.rs.base.service.reportengines.table.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

public interface TableExportHook extends Hook {

   Collection<String> getFormats();

   void afterExport(Report report, User user, CompiledReport compiledReport, TableOutputGenerator outputGenerator,
         String outputFormat);

   void beforeExport(Report report, User user, TableDefinition td, TableOutputGenerator outputGenerator,
         String outputFormat);

}
