package net.datenwerke.security.client.usermanager.dto.ie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StrippedDownOrganisationalUnitPA extends PropertyAccess<StrippedDownOrganisationalUnit> {

   public static final StrippedDownOrganisationalUnitPA INSTANCE = GWT.create(StrippedDownOrganisationalUnitPA.class);

   @Path("id")
   public ModelKeyProvider<StrippedDownOrganisationalUnit> dtoId();

   public ValueProvider<StrippedDownOrganisationalUnit, Long> id();

   public ValueProvider<StrippedDownOrganisationalUnit, String> name();

   public ValueProvider<StrippedDownOrganisationalUnit, String> parentOu();
}
