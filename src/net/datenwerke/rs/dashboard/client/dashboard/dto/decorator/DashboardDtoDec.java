package net.datenwerke.rs.dashboard.client.dashboard.dto.decorator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;

/**
 * Dto Decorator for {@link DashboardDto}
 *
 */
public class DashboardDtoDec extends DashboardDto implements IdedDto {

   private static final long serialVersionUID = 1L;

   private Map<String, ParameterInstanceDto> temporaryInstanceMap;

   public DashboardDtoDec() {
      super();
      setLayout(LayoutTypeDto.TWO_COLUMN_EQUI);
   }

   public void updateDadget(DadgetDto old, DadgetDto newDadget) {
      List<DadgetDto> dadgets = getDadgets();
      Collections.replaceAll(dadgets, old, newDadget);
      setDadgets(dadgets);
   }

   public Map<String, ParameterInstanceDto> getTemporaryInstanceMap() {
      return temporaryInstanceMap;
   }

   public void setTemporaryInstanceMap(Map<String, ParameterInstanceDto> temporaryInstanceMap) {
      this.temporaryInstanceMap = temporaryInstanceMap;
   }

}
