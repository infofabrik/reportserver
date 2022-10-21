package net.datenwerke.rs.tsreportarea.client.tsreportarea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utils.valueprovider.DisplayTitleValueProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class TsDiskUIServiceImpl implements TsDiskUIService {

   private FormatUiHelper formatUiHelper;
   private HookHandlerService hookHandler;

   @Inject
   public TsDiskUIServiceImpl(FormatUiHelper formatUiHelper, HookHandlerService hookHandler) {
      this.formatUiHelper = formatUiHelper;
      this.hookHandler = hookHandler;
   }

   @Override
   public ImageResource getIconFor(AbstractTsDiskNodeDto node, boolean largeIcons) {
      if (node instanceof TsDiskFolderDto || node instanceof TsDiskRootDto)
         return (largeIcons) ? BaseIcon.FOLDER_O.toImageResource(1) : BaseIcon.FOLDER_O.toImageResource();
      if (node instanceof TsDiskReportReferenceDto) {
         ReportDto report = ((TsDiskReportReferenceDto) node).getReport();
         if (null != report) {
            Boolean hardlink = ((TsDiskReportReferenceDto) node).isHardlink();
            for (ReportTypeConfigHook iconProvider : hookHandler.getHookers(ReportTypeConfigHook.class)) {
               if (iconProvider.consumes(report)) {
                  return hardlink ? iconProvider.getReportIcon() : iconProvider.getReportLinkIcon();
               }
            }
         }
      }

      return null != node ? node.toIcon().toImageResource() : null;
   }

   @Override
   public IconProvider<AbstractNodeDto> getIconProvider() {
      return new IconProvider<AbstractNodeDto>() {
         @Override
         public ImageResource getIcon(AbstractNodeDto model) {
            if (!(model instanceof AbstractTsDiskNodeDto))
               return BaseIcon.FILE.toImageResource();
            ImageResource icon = getIconFor((AbstractTsDiskNodeDto) model, false);
            if (null == icon)
               return BaseIcon.FILE.toImageResource();
            return icon;
         }
      };
   }

   @Override
   public <A extends AbstractTsDiskNodeDto> List<ColumnConfig<A, ?>> createGridColumnConfig(Class<A> type) {
      return createGridColumnConfig(type, true);
   }

   @Override
   public <A extends AbstractTsDiskNodeDto> List<ColumnConfig<A, ?>> createGridColumnConfig(Class<A> type,
         boolean addIdColumn) {
      /* create column config */
      List<ColumnConfig<A, ?>> columns = new ArrayList<ColumnConfig<A, ?>>();

      /* icon */
      ColumnConfig<A, A> iconColumn = new ColumnConfig<A, A>(new IdentityValueProvider<A>("__icon"), 30);
      iconColumn.setSortable(false);
      iconColumn.setMenuDisabled(true);
      iconColumn.setCell(new AbstractCell<A>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, A model, SafeHtmlBuilder sb) {
            ImageResource icon = getIconFor(model, false);
            if (icon instanceof CssIconImageResource)
               sb.append(((CssIconImageResource) icon).getCssIcon());
            else
               sb.append(AbstractImagePrototype.create(icon).getSafeHtml());
         }
      });
      columns.add(iconColumn);

      if (addIdColumn) {
         ColumnConfig<A, A> idColumn = new ColumnConfig<A, A>(new IdentityValueProvider<A>("__id"), 90,
               TsFavoriteMessages.INSTANCE.idLabel());
         idColumn.setMenuDisabled(true);
         idColumn.setCell(new AbstractCell<A>() {
            @Override
            public void render(com.google.gwt.cell.client.Cell.Context context, A model, SafeHtmlBuilder sb) {
               if (model instanceof TsDiskReportReferenceDto) {
                  ReportDto report = ((TsDiskReportReferenceDto) model).getReport();
                  Long id = null != report ? report.getId() : null;
                  if (null != id)
                     sb.append(id);
               }
            }
         });
         columns.add(idColumn);

         idColumn.setComparator(new Comparator<A>() {
            @Override
            public int compare(A m1, A m2) {
               /* folders first */
               if (m1 instanceof TsDiskFolderDto && m2 instanceof TsDiskFolderDto)
                  return 0;
               if (m1 instanceof TsDiskFolderDto)
                  return -1;
               if (m2 instanceof TsDiskFolderDto)
                  return 1;

               Long id1 = m1.getId();
               Long id2 = m2.getId();

               if (m1 instanceof TsDiskReportReferenceDto)
                  id1 = ((TsDiskReportReferenceDto) m1).getReport().getId();
               if (m2 instanceof TsDiskReportReferenceDto)
                  id2 = ((TsDiskReportReferenceDto) m2).getReport().getId();

               return id1.compareTo(id2);
            }
         });
      }

      /* name */
      ColumnConfig<A, String> nameColumn = new ColumnConfig<A, String>(new DisplayTitleValueProvider<A>(), 200,
            BaseMessages.INSTANCE.name());
      nameColumn.setMenuDisabled(true);

      columns.add(nameColumn);

      ColumnConfig<A, A> ccLastModified = new ColumnConfig<A, A>(new IdentityValueProvider<A>("__last"), 150,
            TsFavoriteMessages.INSTANCE.lastModified());
      ccLastModified.setMenuDisabled(true);
      ccLastModified.setCell(new AbstractCell<A>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, A model, SafeHtmlBuilder sb) {
            Date lastUpdated = getModelLastUpdated(model);
            if (null == lastUpdated) {
               sb.appendEscaped(BaseMessages.INSTANCE.unknown());
            } else {
               try {
                  sb.appendEscaped(formatUiHelper.getShortDateTimeFormat().format(lastUpdated));
               } catch (IllegalArgumentException e) {
                  sb.appendEscaped(BaseMessages.INSTANCE.unknown());
               }
            }
         }
      });
      columns.add(ccLastModified);

      ccLastModified.setComparator(new Comparator<A>() {
         @Override
         public int compare(A m1, A m2) {
            Date lu1 = getModelLastUpdated(m1);
            Date lu2 = getModelLastUpdated(m2);

            if (null == lu1 && null == lu2)
               return 0;
            if (null == lu1)
               return lu2.compareTo(lu1);
            return lu1.compareTo(lu2);
         }
      });

      return columns;
   }

   private Date getModelLastUpdated(AbstractTsDiskNodeDto model) {
      if (model instanceof TsDiskGeneralReferenceDto) {
         Date refLastUpdated = ((TsDiskGeneralReferenceDtoDec) model).getReferenceLastUpdated();
         if (null == refLastUpdated
               || (null != model.getLastUpdated() && refLastUpdated.before(model.getLastUpdated())))
            refLastUpdated = model.getLastUpdated();

         return refLastUpdated;
      } else {
         return model.getLastUpdated();
      }
   }

}
