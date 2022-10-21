package net.datenwerke.rs.theme.client.field;

import com.sencha.gxt.cell.core.client.form.RadioCell;

public class RsRadioAppearance extends RsCheckboxAppearance implements RadioCell.RadioAppearance {

   public RsRadioAppearance() {
      super();
      type = "radio";
   }

}
