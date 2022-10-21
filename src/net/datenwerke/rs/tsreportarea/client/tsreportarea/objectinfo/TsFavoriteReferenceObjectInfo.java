package net.datenwerke.rs.tsreportarea.client.tsreportarea.objectinfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.datenwerke.gxtdto.client.objectinformation.ObjectPreviewTabPanel;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabKeyInfoProvider;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

/**
 * 
 *
 */
public class TsFavoriteReferenceObjectInfo implements ObjectPreviewTabKeyInfoProvider {

   @Override
   public boolean consumes(Object object) {
      return object instanceof TsDiskReportReferenceDto;
   }

   @Override
   public Collection<?> getSubtypes(Object object) {
      return null;
   }

   @Override
   public void setInfoPanel(final ObjectPreviewTabPanel infoPanel, Object object) {
      TsDiskReportReferenceDto reference = (TsDiskReportReferenceDto) object;
      infoPanel.addInfoFor(reference.getReport());
   }

   @Override
   public List<PreviewComponent> getInfoComponents(Object object) {
      return new ArrayList<ObjectPreviewTabProviderHook.PreviewComponent>();
   }

}
