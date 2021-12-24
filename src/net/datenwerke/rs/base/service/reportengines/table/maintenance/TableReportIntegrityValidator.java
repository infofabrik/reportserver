package net.datenwerke.rs.base.service.reportengines.table.maintenance;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;

public class TableReportIntegrityValidator implements MaintenanceTask {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<EntityManager> entityManagerProvider;

   @Inject
   public TableReportIntegrityValidator(Provider<EntityManager> entityManagerProvider) {

      this.entityManagerProvider = entityManagerProvider;
   }

   @Override
   public void performMaintenance() {
      try {
         /* get entity manager and flush changes so that cascades were processed */
         final EntityManager entityManager = entityManagerProvider.get();
         entityManager.flush();

         List<Column> columnOrphans = getOrphanedColumns();
         if (null != columnOrphans)
            columnOrphans.forEach(entityManager::remove);

         List<Filter> filterOrphans = getOrphanedSapFilters();
         if (null != filterOrphans)
            filterOrphans.forEach(entityManager::remove);

         List<FilterRange> rangeOrphans = getOrphanedRanges();
         if (null != rangeOrphans)
            rangeOrphans.forEach(entityManager::remove);

         List<FilterBlock> filterBlockOrphans = getOrphanedFilterBlocks();
         if (null != filterBlockOrphans)
            filterBlockOrphans.forEach(entityManager::remove);

         List<FilterSpec> filterSpecOrphans = getOrphanedFilterSpecs();
         if (null != filterSpecOrphans)
            filterSpecOrphans.forEach(entityManager::remove);

      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }
   }

   private List<FilterSpec> getOrphanedFilterSpecs() {
      HibernateEntityManager em = (HibernateEntityManager) entityManagerProvider.get();
      SQLQuery query = em.getSession()
            .createSQLQuery("SELECT A.ENTITY_ID FROM RS_FILTER_SPEC A " + "LEFT OUTER JOIN ( "
                  + "SELECT FILTERS_ID FROM RS_FILTER_BLOCK_2_FILTERS) B "
                  + "ON B.FILTERS_ID = A.ENTITY_ID WHERE B.FILTERS_ID IS NULL");
      List list = query.list();

      List<FilterSpec> specList = new ArrayList<>();
      for (Object id : list) {
         if (null != id) {
            FilterSpec spec = null;
            if (id instanceof BigInteger)
               spec = em.find(FilterSpec.class, ((BigInteger) id).longValue());
            else if (id instanceof Long)
               spec = em.find(FilterSpec.class, id);
            else
               spec = em.find(FilterSpec.class, Long.valueOf(id.toString()));

            if (null != spec)
               specList.add(spec);
         }
      }

      return specList;
   }

   protected List<FilterBlock> getOrphanedFilterBlocks() {
      HibernateEntityManager em = (HibernateEntityManager) entityManagerProvider.get();
      SQLQuery query = em.getSession().createSQLQuery("SELECT A.* FROM RS_FILTER_BLOCK A " + "LEFT OUTER JOIN ( "
            + "SELECT ROOT_BLOCK_ID AS BID FROM RS_PRE_FILTER UNION SELECT CHILD_BLOCKS_ID AS BID FROM RS_FILTER_BLOCK_2_CHILD_BL) B "
            + "ON B.BID = A.ENTITY_ID WHERE B.BID IS NULL");
      query.addEntity(FilterBlock.class);
      List list = query.list();
      return list;
   }

   public List<Column> getOrphanedColumns() {
      HibernateEntityManager em = (HibernateEntityManager) entityManagerProvider.get();
      SQLQuery query = em.getSession()
            .createSQLQuery("SELECT A.ENTITY_ID FROM RS_COLUMN A " + "LEFT OUTER JOIN ( "
                  + "SELECT COLUMNS_ID AS CID FROM RS_TABLE_REPORT_2_COLUMN UNION "
                  + "SELECT ADDITIONAL_COLUMNS_ID AS CID FROM RS_TABLE_REPORT_2_ADD_COLUMN UNION "
                  + "SELECT COLUMN_ID  AS CID FROM RS_COLUMN_FILTER UNION "
                  + "SELECT COLUMNA_ID AS CID FROM RS_BINARY_COLUMN_FILTER UNION "
                  + "SELECT COLUMNB_ID AS CID FROM RS_BINARY_COLUMN_FILTER ) B "
                  + "ON B.CID = A.ENTITY_ID WHERE B.CID IS NULL");
      List list = query.list();

      List<Column> colList = new ArrayList<Column>();
      for (Object id : list) {
         if (null != id) {
            Column column = null;
            if (id instanceof BigInteger)
               column = em.find(Column.class, ((BigInteger) id).longValue());
            else if (id instanceof Long)
               column = em.find(Column.class, id);
            else
               column = em.find(Column.class, Long.valueOf(id.toString()));

            if (null != column)
               colList.add(column);
         }
      }
      return colList;
   }

   public List<Filter> getOrphanedSapFilters() {
      HibernateEntityManager em = (HibernateEntityManager) entityManagerProvider.get();
      SQLQuery query = em.getSession()
            .createSQLQuery("SELECT A.* FROM RS_FILTER A " + "LEFT OUTER JOIN ( "
                  + "SELECT FILTER_ID FROM RS_COLUMN WHERE NOT FILTER_ID IS NULL ) B "
                  + "ON B.FILTER_ID = A.ENTITY_ID WHERE B.FILTER_ID IS NULL");
      query.addEntity(Filter.class);
      List list = query.list();
      return list;
   }

   public List<FilterRange> getOrphanedRanges() {
      HibernateEntityManager em = (HibernateEntityManager) entityManagerProvider.get();
      NativeQuery query = em.getSession().createSQLQuery("SELECT A.* FROM RS_FILTER_RANGE A " + "LEFT OUTER JOIN ( "
            + "SELECT EXCLUDE_RANGES_ID AS RANGE_ID FROM RS_FILTER_2_FILTER_RNG_EXC UNION SELECT INCLUDE_RANGES_ID AS RANGE_ID FROM RS_FILTER_2_FILTER_RNG_INC) B "
            + "ON B.RANGE_ID = A.ENTITY_ID WHERE B.RANGE_ID IS NULL");
      query.addEntity(FilterRange.class);
      List list = query.list();
      return list;
   }

}
