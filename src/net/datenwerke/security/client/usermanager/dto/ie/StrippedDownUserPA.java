package net.datenwerke.security.client.usermanager.dto.ie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StrippedDownUserPA extends PropertyAccess<StrippedDownUser> {

   public static final StrippedDownUserPA INSTANCE = GWT.create(StrippedDownUserPA.class);

   @Path("id")
   public ModelKeyProvider<StrippedDownUser> dtoId();

   public ValueProvider<StrippedDownUser, Long> id();

   public ValueProvider<StrippedDownUser, String> firstname();

   public ValueProvider<StrippedDownUser, String> lastname();

   public ValueProvider<StrippedDownUser, String> parentOu();
}
