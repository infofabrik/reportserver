package net.datenwerke.gf.client.fileselection.dto;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface SelectedFileWrapperPA extends PropertyAccess<SelectedFileWrapper> {

   public static final SelectedFileWrapperPA INSTANCE = GWT.create(SelectedFileWrapperPA.class);

   public ValueProvider<SelectedFileWrapper, String> name();
}
