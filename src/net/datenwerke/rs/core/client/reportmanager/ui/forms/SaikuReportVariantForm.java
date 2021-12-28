package net.datenwerke.rs.core.client.reportmanager.ui.forms;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleMultiForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class SaikuReportVariantForm extends SimpleFormView {

   private final ReportManagerTreeManagerDao dao;

   // original write protect and configuration protect values
   private boolean currentWpValue;
   private boolean currentCpValue;
   private boolean currentMdxValue;

   @Inject
   public SaikuReportVariantForm(ReportManagerTreeManagerDao dao) {
      this.dao = dao;
   }

   @Override
   public void configureSimpleForm(SimpleForm form) {
      form.setHeading(ReportmanagerMessages.INSTANCE.editReportVariant()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginRow();
      form.addField(String.class, ReportDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      form.addField(String.class, ReportDtoPA.INSTANCE.key(), ReportmanagerMessages.INSTANCE.key());
      form.endRow();

      form.addField(String.class, ReportDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(),
            new SFFCTextAreaImpl());
   }

   @Override
   protected void callbackAfterBinding(SimpleMultiForm form, AbstractNodeDto selectedNode) {
      final SimpleForm xform = SimpleForm.getNewInstance();
      xform.getButtonBar().clear();
      final String wpKey = xform.addField(Boolean.class, ReportmanagerMessages.INSTANCE.writeProtect());
      final String cpKey = xform.addField(Boolean.class, ReportmanagerMessages.INSTANCE.configurationProtect());
      final String mdxKey = xform.addField(Boolean.class, ReportmanagerMessages.INSTANCE.mdxEditor());

      xform.setValue(cpKey, ((SaikuReportVariantDtoDec) selectedNode).isConfigurationProtected());

      // set and disable write protection field when config protection is set
      this.addCondition(xform, true, wpKey, cpKey, true, true, false);

      // unset and disable mdx editor field when config protection is set
      this.addCondition(xform, true, mdxKey, cpKey, true, false, false);

      // enable write protection field when config protection is unset
      this.addCondition(xform, false, wpKey, cpKey, false, false, true);

      // enable mdx editor field when config protection is unset
      this.addCondition(xform, false, mdxKey, cpKey, false, false, true);

      xform.setValue(mdxKey, ((SaikuReportVariantDtoDec) selectedNode).isAllowMdx());
      xform.setValue(wpKey, ((SaikuReportVariantDtoDec) selectedNode).isWriteProtected());

      xform.loadFields();

      currentWpValue = ((Boolean) xform.getValue(wpKey));
      currentCpValue = ((Boolean) xform.getValue(cpKey));
      currentMdxValue = ((Boolean) xform.getValue(mdxKey));

      xform.addSubmissionCallback(new SimpleFormSubmissionCallback(form) {
         @SuppressWarnings("unchecked")
         @Override
         public void formSubmitted() {

            final boolean newWpValue = ((Boolean) xform.getValue(wpKey));
            final boolean newCpValue = ((Boolean) xform.getValue(cpKey));
            final boolean newMdxValue = ((Boolean) xform.getValue(mdxKey));

            long flagToSet = 0;
            long flagToUnset = 0;

            if (currentWpValue != newWpValue) {
               if (newWpValue) {
                  flagToSet |= SaikuReportVariantDtoDec.FLAG_WRITE_PROTECT;
               } else {
                  flagToUnset |= SaikuReportVariantDtoDec.FLAG_WRITE_PROTECT;
               }
            }

            if (currentCpValue != newCpValue) {
               if (newCpValue) {
                  flagToSet |= SaikuReportVariantDtoDec.FLAG_CONFIGURATION_PROTECT;
               } else {
                  flagToUnset |= SaikuReportVariantDtoDec.FLAG_CONFIGURATION_PROTECT;
               }
            }

            if (currentMdxValue != newMdxValue) {
               ((SaikuReportVariantDtoDec) getSelectedNode()).setAllowMdx(newMdxValue);
            }

            dao.setFlag(getSelectedNode(), flagToSet, flagToUnset, true, new RsAsyncCallback<AbstractNodeDto>());
            currentWpValue = ((Boolean) xform.getValue(wpKey));
            currentCpValue = ((Boolean) xform.getValue(cpKey));
            currentMdxValue = ((Boolean) xform.getValue(mdxKey));
         }
      });
      form.addSubForm(xform);
   }

   private void addCondition(final SimpleForm xform, final boolean fieldCondition, final String key,
         final String modifiedKey, final boolean setValue, final boolean value, final boolean setEnable) {
      xform.addCondition(modifiedKey, new FieldEquals(fieldCondition), new SimpleFormAction() {

         @Override
         public void onSuccess(SimpleForm form) {
            Widget field = form.getDisplayedField(key);
            if (null == field)
               return;
            if (field instanceof Component) {
               if (setValue) {
                  form.setValue(key, value);
               }
               if (setEnable) {
                  ((Component) field).enable();
               } else {
                  ((Component) field).disable();
               }
            }

            form.updateFormLayout();
         }

         @Override
         public void onFailure(SimpleForm form) {
         }
      });
   }

}