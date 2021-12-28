package net.datenwerke.gxtdto.client.forms.layout;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

public class FormFieldLayoutConfiguration {

   private boolean hasLabel = true;
   private int labelWidth = 180;
   private LabelAlign labelAlign = LabelAlign.TOP;
   private String labelText;
   private SafeHtml labelHtml;
   private MarginData marginData = new MarginData(0);

   private double fieldWidth = 1;
   private double fieldHeight = -1;

   public FormFieldLayoutConfiguration() {
   }

   public FormFieldLayoutConfiguration(String text) {
      this.labelText = text;
   }

   public FormFieldLayoutConfiguration(String text, int fieldWidth) {
      this.labelText = text;
      this.fieldWidth = fieldWidth;
   }

   public FormFieldLayoutConfiguration(String text, int fieldWidth, double fieldHeight) {
      this.labelText = text;
      this.fieldWidth = fieldWidth;
      this.fieldHeight = fieldHeight;
   }

   public static FormFieldLayoutConfiguration from(String text, FormFieldLayoutConfiguration config) {
      FormFieldLayoutConfiguration newConfig = config.doClone();

      newConfig.setLabelText(text);
      newConfig.setLabelHtml(null);

      return newConfig;
   }

   public int getLabelWidth() {
      return labelWidth;
   }

   public void setLabelWidth(int labelWidth) {
      this.labelWidth = labelWidth;
   }

   public LabelAlign getLabelAlign() {
      return labelAlign;
   }

   public void setLabelAlign(LabelAlign labelAlign) {
      this.labelAlign = labelAlign;
   }

   public void setFieldWidth(double fieldWidth) {
      this.fieldWidth = fieldWidth;
   }

   public double getFieldWidth() {
      return fieldWidth;
   }

   public void setLabelText(String labelText) {
      this.labelText = labelText;
   }

   public String getLabelText() {
      return labelText;
   }

   public void setLabelHtml(SafeHtml labelHtml) {
      this.labelHtml = labelHtml;
   }

   public SafeHtml getLabelHtml() {
      return labelHtml;
   }

   public void setFieldHeight(double fieldHeight) {
      this.fieldHeight = fieldHeight;
   }

   public double getFieldHeight() {
      return fieldHeight;
   }

   public void setHasLabel(boolean hasLabel) {
      this.hasLabel = hasLabel;
   }

   public boolean isHasLabel() {
      return hasLabel;
   }

   public void setMarginData(MarginData marginData) {
      this.marginData = marginData;
   }

   public MarginData getMarginData() {
      return marginData;
   }

   protected FormFieldLayoutConfiguration doClone() {
      FormFieldLayoutConfiguration config = new FormFieldLayoutConfiguration();

      config.setFieldHeight(getFieldHeight());
      config.setFieldWidth(getFieldWidth());
      config.setLabelAlign(getLabelAlign());
      config.setLabelHtml(getLabelHtml());
      config.setLabelText(getLabelText());
      config.setLabelWidth(getLabelWidth());
      config.setHasLabel(isHasLabel());
      config.setMarginData(getMarginData());

      return config;
   }

}
