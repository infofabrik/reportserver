package net.datenwerke.rs.base.client.parameters.datetime;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.uiutils.date.DateFormulaContainer;
import net.datenwerke.gf.client.uiutils.date.simpleform.SFFCDateFormula;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDate;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec;
import net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec;
import net.datenwerke.rs.base.client.parameters.datetime.dto.pa.DateTimeParameterDefinitionDtoPA;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;
import net.datenwerke.rs.core.client.parameters.helper.DefaultValueSetter;
import net.datenwerke.rs.core.client.parameters.helper.ParameterFieldWrapperForFrontend;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
@Singleton
public class DateTimeConfigurator
      extends ParameterConfiguratorImpl<DateTimeParameterDefinitionDto, DateTimeParameterInstanceDto> {

   private FormatUiHelper formatUiHelper;

   @Inject
   public DateTimeConfigurator(FormatUiHelper formatUiHelper) {
      this.formatUiHelper = formatUiHelper;
   }

   public String getName() {
      return ReportmanagerMessages.INSTANCE.dateParameterName();
   }

   @Override
   protected DateTimeParameterDefinitionDto doGetNewDto() {
      DateTimeParameterDefinitionDto definition = new DateTimeParameterDefinitionDtoDec();
      definition.setUseNowAsDefault(false);
      return definition;
   }

   @Override
   public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
      return DateTimeParameterDefinitionDtoDec.class.equals(type);
   }

   public ImageResource getIcon() {
      return BaseIcon.CALENDAR.toImageResource();
   }

   @Override
   public Widget getEditComponentForDefinition(DateTimeParameterDefinitionDto definition, ReportDto report) {
      final SimpleForm form = SimpleForm.getInlineInstance();
      form.setHeight(300);

      /* add mode */
      final String modeKey = form.addField(List.class, DateTimeParameterDefinitionDtoPA.INSTANCE.mode(),
            RsMessages.INSTANCE.mode(), new SFFCStaticDropdownList<ModeDto>() {
               public Map<String, ModeDto> getValues() {
                  Map<String, ModeDto> map = new LinkedHashMap<String, ModeDto>();

                  map.put(ReportmanagerMessages.INSTANCE.date(), ModeDto.Date);
                  map.put(ReportmanagerMessages.INSTANCE.time(), ModeDto.Time);
                  map.put(ReportmanagerMessages.INSTANCE.dateAndTime(), ModeDto.DateTime);

                  return map;
               }

            });

      /* add default */
      form.beginFieldset(ReportmanagerMessages.INSTANCE.defaultValue());
      form.addField(Boolean.class, DateTimeParameterDefinitionDtoPA.INSTANCE.useNowAsDefault(),
            RsMessages.INSTANCE.useNowAsDefault());
      String defaultKey = form.addField(Date.class, DateTimeParameterDefinitionDtoPA.INSTANCE.defaultValue(),
            RsMessages.INSTANCE.defaultValue(), new SFFCDate() {
               public Mode getMode() {
                  Object mode = form.getValue(modeKey);
                  if (mode == ModeDto.DateTime)
                     return Mode.DateTime;
                  else if (mode == ModeDto.Time)
                     return Mode.Time;
                  else
                     return Mode.Date;
               }

               @Override
               public String getDatePattern() {
                  return formatUiHelper.getShortDateFormat().getPattern();
               }

               @Override
               public String getTimePattern() {
                  return formatUiHelper.getShortTimeFormat().getPattern();
               }
            });
      form.addField(String.class, DateTimeParameterDefinitionDtoPA.INSTANCE.formula(),
            RsMessages.INSTANCE.defaultFormula());
      form.endGroup();

      form.addDependency(defaultKey, modeKey);

      /* bind definition */
      form.bind(definition);

      return form;
   }

   @Override
   public Widget doGetEditComponentForInstance(final DateTimeParameterInstanceDto instance,
         Collection<ParameterInstanceDto> relevantInstances, final DateTimeParameterDefinitionDto definition,
         boolean initial, int labelWidth, String executeReportToken, ReportDto report) {
      SFFCDateFormula dateConfig = null;
      if (definition.getMode() == ModeDto.DateTime) {
         dateConfig = new SFFCDateFormula() {
            public Mode getMode() {
               return Mode.DateTime;
            }

            @Override
            public String getDatePattern() {
               return formatUiHelper.getShortDateFormat().getPattern();
            }

            @Override
            public String getTimePattern() {
               return formatUiHelper.getShortTimeFormat().getPattern();
            }
         };
      } else if (definition.getMode() == ModeDto.Time) {
         dateConfig = new SFFCDateFormula() {
            public Mode getMode() {
               return Mode.Time;
            }

            @Override
            public String getDatePattern() {
               return formatUiHelper.getShortDateFormat().getPattern();
            }

            @Override
            public String getTimePattern() {
               return formatUiHelper.getShortTimeFormat().getPattern();
            }
         };
      } else {
         dateConfig = new SFFCDateFormula() {
            public Mode getMode() {
               return Mode.Date;
            }

            @Override
            public String getDatePattern() {
               return formatUiHelper.getShortDateFormat().getPattern();
            }

            @Override
            public String getTimePattern() {
               return formatUiHelper.getShortTimeFormat().getPattern();
            }
         };
      }

      SimpleForm form = SimpleForm.getInlineLabelessInstance();
      form.setFocusOnShow(false);
      form.setWidth(200);

      form.addField(DateFormulaContainer.class, DateTimeParameterInstanceDtoDec.getDateFormulaValueProvider(),
            definition.getKey(), dateConfig, new SFFCAllowBlank() {

               @Override
               public boolean allowBlank() {
                  return !definition.isMandatory();
               }
            });

      if (instance.isStillDefault())
         setDefaultValueInInstance(instance, definition);

      form.bind(instance);

      /* disable edit form, if parameter is not editable */
      if (!definition.isEditable())
         form.disable();

      return new ParameterFieldWrapperForFrontend(definition, instance, form, labelWidth, new DefaultValueSetter() {
         @Override
         public void setDefaultValue() {
            setDefaultValueInInstance(instance, definition);
         }
      });
   }

   @Override
   protected void doSetDefaultValueInInstance(DateTimeParameterInstanceDto instance,
         DateTimeParameterDefinitionDto definition) {
      /*
       * note that it is important to set the formula first, as the event is thrown by
       * changing the value
       */
      if (null != definition.getFormula() && !"".equals(definition.getFormula().trim())) {
         instance.setFormula(definition.getFormula());
         instance.setValue(null);
      } else if (definition.isUseNowAsDefault()) {
         instance.setFormula(null);
         instance.setValue(new Date());
      } else {
         instance.setFormula(null);
         instance.setValue(definition.getDefaultValue());
      }
   }

   @Override
   public boolean canHandle(ParameterProposalDto proposal) {
      String type = proposal.getType();
      if (Date.class.getName().equals(type))
         return true;
      return false;
   }

   @Override
   public List<String> validateParameter(DateTimeParameterDefinitionDto definition,
         DateTimeParameterInstanceDto instance, Widget widget) {
      List<String> errList = super.validateParameter(definition, instance, widget);
      if (!((SimpleForm) ((ParameterFieldWrapperForFrontend) widget).getComponent()).isValid())
         errList.add(RsMessages.INSTANCE.invalidParameter(definition.getName()));

      return errList;
   }
}
