package net.datenwerke.rs.core.client.easteregg;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class RsPacmanObjectInfo implements ObjectPreviewTabProviderHook {

   @Override
   public boolean consumes(Object object) {
      return object instanceof ReportVariantDto && object instanceof ReportDto && null != ((ReportDto) object).getName()
            && ((ReportDto) object).getName().endsWith("LET_ME_PLAY_PACMAN");
   }

   @Override
   public List<PreviewComponent> getInfoComponents(Object object) {
      List<ObjectPreviewTabProviderHook.PreviewComponent> list = new ArrayList<ObjectPreviewTabProviderHook.PreviewComponent>();
      list.add(new PreviewComponent() {

         @Override
         public String getInfoName() {
            return "Pacman";
         }

         @Override
         public Component getInfoComponent(Object object) {
            DwContentPanel panel = new DwContentPanel();
            panel.setHeaderVisible(false);

            panel.setUrl("resources/ee/pm/index.html");

            return panel;
         }
      });

      return list;

   }

}
