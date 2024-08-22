package net.datenwerke.rs.tsreportarea.service.tsreportarea.hookers;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;

import com.google.inject.Inject;

import net.datenwerke.rs.search.service.search.hooks.AdditionalFieldsIndexerHook;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.jpa.EntityUtils;

public class TeamSpaceAdditionalFieldsHooker implements AdditionalFieldsIndexerHook {

   private final EntityUtils entityUtils;

   @Inject
   public TeamSpaceAdditionalFieldsHooker(EntityUtils entityUtils) {
      this.entityUtils = entityUtils;
   }

   @Override
   public void addToIndex(Object toIndex, StringBuilder catchall, Document doc) {
      if (toIndex instanceof TsDiskReportReference) {
         TsDiskReportReference ref = (TsDiskReportReference) toIndex;

         String sval = "reportId:"
               + String.valueOf(entityUtils.getId(entityUtils.simpleHibernateUnproxy(ref.getReport())));
         catchall.append(sval).append(" ");
         doc.add(new StringField("reportId", sval, Store.YES));
      }
   }

}
