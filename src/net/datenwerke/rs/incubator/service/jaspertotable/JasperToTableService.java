package net.datenwerke.rs.incubator.service.jaspertotable;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;

public interface JasperToTableService {

   TableReport transformToTableReport(JasperReport report);

}
