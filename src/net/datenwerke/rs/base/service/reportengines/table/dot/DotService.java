package net.datenwerke.rs.base.service.reportengines.table.dot;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.security.service.usermanager.entities.User;

public interface DotService {

   String createDotFile(User user, TableReportDto reportDto, String executeToken);

}
