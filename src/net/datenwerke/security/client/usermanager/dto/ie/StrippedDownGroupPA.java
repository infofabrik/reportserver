package net.datenwerke.security.client.usermanager.dto.ie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StrippedDownGroupPA extends PropertyAccess<StrippedDownGroup> {

   public static final StrippedDownGroupPA INSTANCE = GWT.create(StrippedDownGroupPA.class);

   @Path("id")
   public ModelKeyProvider<StrippedDownGroup> dtoId();

   public ValueProvider<StrippedDownGroup, Long> id();

   public ValueProvider<StrippedDownGroup, String> name();

   public ValueProvider<StrippedDownGroup, String> parentOu();
}
