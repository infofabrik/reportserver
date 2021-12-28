package net.datenwerke.rs.base.client.parameters.string;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.form.Validator;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorBigDecimal;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorDouble;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorFloat;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorInteger;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorLong;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.string.dto.pa.TextParameterDefinitionDtoPA;
import net.datenwerke.rs.base.client.parameters.string.dto.pa.TextParameterInstanceDtoPA;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
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
public class TextParameterConfigurator
      extends ParameterConfiguratorImpl<TextParameterDefinitionDto, TextParameterInstanceDto> {

   @Override
   public Widget getEditComponentForDefinition(TextParameterDefinitionDto definition, ReportDto report) {
      final SimpleForm form = SimpleForm.getInlineInstance();

      form.beginRow();
      final String widthKey = form.addField(Integer.class, TextParameterDefinitionDtoPA.INSTANCE.width(),
            BaseMessages.INSTANCE.width());
      final String heightKey = form.addField(Integer.class, TextParameterDefinitionDtoPA.INSTANCE.height(),
            BaseMessages.INSTANCE.height());
      form.endRow();

      /* add type */
      final String returnTypeKey = form.addField(List.class, TextParameterDefinitionDtoPA.INSTANCE.returnType(),
            RsMessages.INSTANCE.returnType(), // $NON-NLS-1$ //$NON-NLS-2$
            new SFFCStaticDropdownList<DatatypeDto>() {
               public Map<String, DatatypeDto> getValues() {
                  Map<String, DatatypeDto> map = new TreeMap<String, DatatypeDto>();

                  map.put("java.lang.String", DatatypeDto.String);
                  map.put("java.lang.Integer", DatatypeDto.Integer);
                  map.put("java.lang.Long", DatatypeDto.Long);
                  map.put("java.lang.Double", DatatypeDto.Double);
                  map.put("java.lang.Float", DatatypeDto.Float);
                  map.put("java.lang.Boolean", DatatypeDto.Boolean);
                  map.put("java.math.BigDecimal", DatatypeDto.BigDecimal);
                  map.put("java.util.Date", DatatypeDto.Date);

                  return map;
               }
            });

      final String blankKey = form.addField(Boolean.class, TextParameterDefinitionDtoPA.INSTANCE.returnNullWhenEmpty(),
            new SFFCBoolean() {
               @Override
               public String getBoxLabel() {
                  return RsMessages.INSTANCE.returnNullWhenBlank();
               }
            });
      form.addCondition(returnTypeKey, new FieldEquals(DatatypeDto.String), new ShowHideFieldAction(blankKey));

      final String regexKey = form.addField(String.class, TextParameterDefinitionDtoPA.INSTANCE.validatorRegex(),
            RsMessages.INSTANCE.validatorRegexLabel());

      SFFCStringValidator validator = new SFFCStringValidator() {

         @Override
         public void setAllowBlank(boolean allowBlank) {
            throw new RuntimeException("method not implemented: setAllowBlank");
         }

         @Override
         public Validator<String> getValidator() {
            Validator<String> validator = TextParameterConfigurator.this
                  .getValidator((DatatypeDto) form.getValue(returnTypeKey), (String) form.getValue(regexKey), false)
                  .getValidator();
            return validator;
         }
      };

      String defaultValueKey = form.addField(String.class, TextParameterDefinitionDtoPA.INSTANCE.defaultValue(),
            ReportmanagerMessages.INSTANCE.defaultValue(), new SFFCTextArea() {
               public int getHeight() {
                  Object value = form.getValue(heightKey);
                  if (null == value || !(value instanceof Integer))
                     return 1;
                  return (Integer) value;
               }

               public int getWidth() {
                  Object value = form.getValue(widthKey);
                  if (null == value || !(value instanceof Integer))
                     return 60;
                  return (Integer) value;
               }

            }, validator);

      form.addDependency(defaultValueKey, regexKey);
      form.addDependency(defaultValueKey, returnTypeKey);
      form.addDependency(defaultValueKey, widthKey);
      form.addDependency(defaultValueKey, heightKey);

      /* bind definition */
      form.bind(definition);

      return form;
   }

   private SFFCStringValidator getValidator(DatatypeDto datatype, String regex, boolean mandatory) {
      SFFCStringValidator validator = new SFFCStringValidator() {
         @Override
         public void setAllowBlank(boolean allowBlank) {
         }

         @Override
         public Validator<String> getValidator() {
            return new Validator<String>() {
               @Override
               public List<EditorError> validate(Editor<String> editor, String value) {
                  // TODO Auto-generated method stub
                  return null;
               }
            };
         }
      };

      if (null != regex && !"".equals(regex.trim())) {
         try {
            validator = new SFFCStringValidatorRegex(regex);
         } catch (RuntimeException e) {
         }
      } else if (DatatypeDto.Integer.equals(datatype))
         validator = new SFFCStringValidatorInteger();
      else if (DatatypeDto.Long.equals(datatype))
         validator = new SFFCStringValidatorLong();
      else if (DatatypeDto.BigDecimal.equals(datatype))
         validator = new SFFCStringValidatorBigDecimal();
      else if (DatatypeDto.Float.equals(datatype))
         validator = new SFFCStringValidatorFloat();
      else if (DatatypeDto.Double.equals(datatype))
         validator = new SFFCStringValidatorDouble();
      else if (DatatypeDto.Boolean.equals(datatype))
         validator = new SFFCStringValidatorBoolean();

      /* allow empty value for optional parameters */
      if (null != validator) {
         validator.setAllowBlank(!mandatory);
      }

      return validator;
   }

   @Override
   public Widget doGetEditComponentForInstance(final TextParameterInstanceDto instance,
         Collection<ParameterInstanceDto> relevantInstances, final TextParameterDefinitionDto definition,
         boolean initial, int labelWidth, String executeReportToken, ReportDto report) {
      SimpleForm form = SimpleForm.getInlineLabelessInstance();
      form.setFocusOnShow(false);

      /* prepare validator */
      SFFCStringValidator validator = getValidator(definition.getReturnType(), definition.getValidatorRegex(),
            definition.isMandatory());

      /* display text field */
      form.addField(String.class, TextParameterInstanceDtoPA.INSTANCE.value(), definition.getKey(), validator,
            new SFFCTextArea() { // $NON-NLS-1$
               public int getWidth() {
                  return definition.getWidth();
               }

               public int getHeight() {
                  return definition.getHeight();
               }
            }, new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return !definition.isMandatory();
               }

            });

      /* default value */
      if (instance.isStillDefault())
         setDefaultValueInInstance(instance, definition);

      /* bind instance */
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
   protected void doSetDefaultValueInInstance(TextParameterInstanceDto instance,
         TextParameterDefinitionDto definition) {
      instance.setValue(definition.getDefaultValue());
   }

   public String getName() {
      return ReportmanagerMessages.INSTANCE.stringParameterName();
   }

   @Override
   protected TextParameterDefinitionDto doGetNewDto() {
      return new TextParameterDefinitionDto();
   }

   @Override
   public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
      return TextParameterDefinitionDto.class.equals(type);
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.PARAGRAPH.toImageResource();
   }

   @Override
   public boolean canHandle(ParameterProposalDto proposal) {
      String type = proposal.getType();
      if (String.class.getName().equals(type))
         return true;
      if (Long.class.getName().equals(type))
         return true;
      if (Double.class.getName().equals(type))
         return true;
      if (Integer.class.getName().equals(type))
         return true;
      if (Float.class.getName().equals(type))
         return true;
      if (BigDecimal.class.getName().equals(type))
         return true;

      return false;
   }

   @Override
   public ParameterDefinitionDto getNewDto(ParameterProposalDto proposal, ReportDto report) {
      TextParameterDefinitionDto definition = (TextParameterDefinitionDto) getNewDto(report);

      String type = proposal.getType();
      if (String.class.getName().equals(type))
         definition.setReturnType(DatatypeDto.String);
      if (Long.class.getName().equals(type))
         definition.setReturnType(DatatypeDto.Long);
      if (Double.class.getName().equals(type))
         definition.setReturnType(DatatypeDto.Double);
      if (Integer.class.getName().equals(type))
         definition.setReturnType(DatatypeDto.Integer);
      if (Float.class.getName().equals(type))
         definition.setReturnType(DatatypeDto.Float);
      if (BigDecimal.class.getName().equals(type))
         definition.setReturnType(DatatypeDto.BigDecimal);

      return definition;
   }

   @Override
   public List<String> validateParameter(TextParameterDefinitionDto definition, TextParameterInstanceDto instance,
         Widget widget) {
      List<String> errList = super.validateParameter(definition, instance, widget);
      if (!((SimpleForm) ((ParameterFieldWrapperForFrontend) widget).getComponent()).isValid())
         errList.add(RsMessages.INSTANCE.invalidParameter(definition.getName()));

      return errList;
   }

}
