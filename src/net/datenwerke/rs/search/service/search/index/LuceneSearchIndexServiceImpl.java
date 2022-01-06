package net.datenwerke.rs.search.service.search.index;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.search.service.search.EntityReflectionCache;
import net.datenwerke.rs.search.service.search.SearchServiceImpl;
import net.datenwerke.rs.search.service.search.hooks.AdditionalFieldsIndexerHook;
import net.datenwerke.rs.utils.jpa.EntityUtils;

@Singleton
public class LuceneSearchIndexServiceImpl implements SearchIndexService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private Directory indexDir;

   private IndexWriter writer;
   private IndexReader reader;
   private IndexSearcher searcher;

   private IndexWriterConfig iwc;
   private Analyzer analyzer;

   private final Provider<EntityManager> entityManager;
   private final EntityReflectionCache reflectCache;
   private final EntityUtils entityUtils;
   private final HookHandlerService hookHandlerService;
   
   @Inject
   public LuceneSearchIndexServiceImpl(
         EntityReflectionCache reflectionCache, 
         EntityUtils entityUtils,
         Provider<EntityManager> entityManager,
         HookHandlerService hookHandlerService,
         Provider<ServletContext> servletContextProvider
         ) {
      this.reflectCache = reflectionCache;
      this.entityUtils = entityUtils;
      this.entityManager = entityManager;
      this.hookHandlerService = hookHandlerService;
      this.analyzer = new StandardAnalyzer();

      try {
         Path luceneIndexPath = Paths.get(servletContextProvider.get().getRealPath("/WEB-INF/lucene_index"));
         indexDir = FSDirectory.open(luceneIndexPath);
         iwc = new IndexWriterConfig(analyzer);
         iwc.setOpenMode(OpenMode.CREATE);
         iwc.setRAMBufferSizeMB(256.0);
         writer = new IndexWriter(indexDir, iwc);
         reader = DirectoryReader.open(writer);
         searcher = new IndexSearcher(reader);
      } catch (IOException e) {
         logger.warn("error creating search index", e);
      }

   }

   @Override
   public void flushIndex() {
      try {
         writer.deleteAll();
         writer.commit();
      } catch (IOException e) {
         logger.warn("error flushing search index", e);
      }
   }

   @Override
   public void addToIndex(Object o) {
      if (null == o)
         return;

      final Object unproxied = entityUtils.simpleHibernateUnproxy(o);

      final Document doc = new Document();
      StringBuilder catchall = new StringBuilder();
      List<Field> fields = reflectCache.getFields(unproxied.getClass());
      for (Field f : fields) {
         try {
            String name = f.getName();
            if (null != f.getAnnotation(net.datenwerke.gf.base.service.annotations.Field.class).name()
                  && !f.getAnnotation(net.datenwerke.gf.base.service.annotations.Field.class).name().isEmpty()) {
               name = f.getAnnotation(net.datenwerke.gf.base.service.annotations.Field.class).name();
            }
            Object val = f.get(unproxied);
            if (null != val) {
               String sval = String.valueOf(f.get(unproxied));
               catchall.append(sval).append(" ");
               doc.add(new StringField(name, sval, Store.YES));
            }
         } catch (IllegalArgumentException e) {
         } catch (IllegalAccessException e) {
         }
      }
      try {
         
         hookHandlerService.getHookers(AdditionalFieldsIndexerHook.class)
            .forEach(hooker -> hooker.addToIndex(unproxied, catchall, doc));
         
         doc.add(new StringField("catchall", catchall.toString().toLowerCase(), Store.YES));
         String id = unproxied.getClass().getName() + ":" + entityUtils.getId(unproxied);
         doc.add(new StringField("id", id, Store.YES));
         writer.updateDocument(new Term("id", id), doc);
         
      } catch (IllegalArgumentException e) {
         logger.warn("error creating index", e);
      } catch (IOException e) {
      }
   }

   @Override
   public void commit() {
      try {
         writer.commit();
      } catch (IOException e) {
         logger.warn("error commiting index", e);
      }

   }

   @Override
   public List<?> locate(Class<?> clazz, String querystr, int limit) {
      DirectoryReader newreader;
      try {
         newreader = DirectoryReader.openIfChanged((DirectoryReader) reader);
         if (null != newreader) {
            reader = newreader;
            searcher = new IndexSearcher(reader);
         }
      } catch (IOException e1) {
         logger.warn("error locating class for search", e1);
      }

      List res = new ArrayList();
      try {
         QueryParser parser = new QueryParser("catchall", analyzer);
         parser.setAllowLeadingWildcard(true);

         Query query = parser.parse(querystr);
         TopScoreDocCollector collector = TopScoreDocCollector.create(SearchServiceImpl.MAX_LIMIT_RESULTS,
               SearchServiceImpl.MAX_LIMIT_RESULTS);
         searcher.search(query, collector);
         ScoreDoc[] hits = collector.topDocs().scoreDocs;

         for (int i = 0; i < hits.length; i++) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            String id = d.get("id");

            try {
               Class c = Class.forName(id.substring(0, id.indexOf(":")));
               if (clazz.isAssignableFrom(c)) {
                  Object o = entityManager.get().find(c, Long.valueOf(id.substring(id.indexOf(":") + 1)));
                  res.add(o);
               }
            } catch (ClassNotFoundException e) {
               logger.warn("error locating class for search", e);
            }
         }

      } catch (ParseException | IOException e) {
         logger.warn("error locating class for search", e);
      }

      return res;
   }
}