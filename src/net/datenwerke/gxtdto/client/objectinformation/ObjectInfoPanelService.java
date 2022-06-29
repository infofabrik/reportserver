package net.datenwerke.gxtdto.client.objectinformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.XTemplates.FormatterFactoryMethod;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gf.client.history.HistoryDao;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.objectinformation.locale.ObjectInfoMessages;
import net.datenwerke.gxtdto.client.ui.helper.info.InfoWindow;
import net.datenwerke.gxtdto.client.xtemplates.NullSafeFormatter;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class ObjectInfoPanelService {

   class TemplateModel {
      private ImageResource icon;
      private String name;
      private String type;

      public SafeHtml getIcon() {
         if (icon instanceof CssIconImageResource)
            return ((CssIconImageResource) icon).getCssIcon();
         return SafeHtmlUtils.EMPTY_SAFE_HTML;
      }

      public void setIcon(ImageResource icon) {
         this.icon = icon;
      }

      public void setIcon(SafeHtml icon) {

      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public String getType() {
         return type;
      }

      public void setType(String type) {
         this.type = type;
      }
   }

   @FormatterFactories(@FormatterFactory(factory = NullSafeFormatter.class, methods = @FormatterFactoryMethod(name = "nullsafe")))
   interface ObjectInfoPanelServiceTemplates extends XTemplates {
      @XTemplate("<div class=\"rs-objectinfo-infoblock\">" + "<div class=\"icon\">{icon}</div>"
            + "<div class=\"name\">{name:nullsafe}</div>" + "<div class=\"type\">{type:nullsafe}</div>" + "</div>")
      public SafeHtml render(TemplateModel model);
   }

   private final HookHandlerService hookHandler;
   private final Provider<InfoWindow> infoWindowProvider;
   private final Provider<HistoryDao> historyDao;

   @Inject
   public ObjectInfoPanelService(
         HookHandlerService hookHandler, 
         Provider<InfoWindow> infoWindowProvider,
         Provider<HistoryDao> historyDao
         ) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.infoWindowProvider = infoWindowProvider;
      this.historyDao = historyDao;
   }

   public void displayInformationOn(final Object object) {
      final InfoWindow window = infoWindowProvider.get();

      ObjectInfoPanelServiceTemplates template = GWT.create(ObjectInfoPanelServiceTemplates.class);

      ObjectInfoKeyInfoProvider keyProvider = null;
      for (ObjectInfoKeyInfoProvider keyInfoProvider : hookHandler.getHookers(ObjectInfoKeyInfoProvider.class)) {
         if (keyInfoProvider.consumes(object)) {
            TemplateModel model = new TemplateModel();
            model.setIcon(keyInfoProvider.getIconSmall(object));
            model.setName(keyInfoProvider.getName(object));
            model.setType(keyInfoProvider.getType(object));

            window.setInfoData(new HTML(template.render(model)));

            keyProvider = keyInfoProvider;
            break;
         }
      }

      if (null == keyProvider)
         return;

      window.setHeading(ObjectInfoMessages.INSTANCE.infoOn());
      window.getHeader().setIcon(keyProvider.getIconSmall(object));

      final Map<String, String> data = new HashMap<String, String>();
      data.put(BaseMessages.INSTANCE.name(), keyProvider.getName(object));
      data.put(BaseMessages.INSTANCE.description(), keyProvider.getDescription(object));
      if (object instanceof AbstractNodeDto) {
         Object id = ((AbstractNodeDto) object).getDtoId();
         data.put(BaseMessages.INSTANCE.id(), null == id ? "null" : id.toString());
      }
      data.put(BaseMessages.INSTANCE.createdOn(), null == keyProvider.getCreatedOn(object) ? ""
            : DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(keyProvider.getCreatedOn(object)));
      data.put(BaseMessages.INSTANCE.changedOn(), null == keyProvider.getLastUpdatedOn(object) ? ""
            : DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(keyProvider.getLastUpdatedOn(object)));
      final DwFlowContainer fieldWrapper = window.addDelayedSimpleDataInfoPanel(ObjectInfoMessages.INSTANCE.general());
      
      historyDao.get().getLinksFor((Dto)object, new RsAsyncCallback<List<HistoryLinkDto>>() {
         @Override
         public void onSuccess(List<HistoryLinkDto> result) {
            if (null != result && !result.isEmpty()) {
               data.put(BaseMessages.INSTANCE.path(),
                     result.get(0).getObjectCaption() + " (" + result.get(0).getHistoryLinkBuilderId() + ")");
            }
            
            data.forEach((key, val) -> {
               FieldLabel field = new FieldLabel(new Label(val), key);
               fieldWrapper.add(field);
            });

         }
      });
      
      hookHandler.getHookers(ObjectInfoAdditionalInfoProvider.class)
         .stream()
         .filter(infoProvider -> infoProvider.consumes(object))
         .forEach(infoProvider -> infoProvider.addInfoFor(object, window));
      
      window.show();
   }

}