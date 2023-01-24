package net.datenwerke.rs.adminutils.service.systemconsole.admin;

import javax.inject.Inject;
import net.datenwerke.rs.adminutils.client.systemconsole.admin.dto.AdminInfoDto;

public class AdminConsoleServiceImpl implements AdminConsoleService {

   @Inject
   public AdminConsoleServiceImpl() {
   }
   
   @Override
   public AdminInfoDto getAdminInfo() {
      AdminInfoDto adminInfo = new AdminInfoDto();
      return adminInfo;
   }

}
