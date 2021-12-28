package net.datenwerke.rs.dsbundle.client.dsbundle.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface DatasourceBundleMessages extends Messages {

   public static final DatasourceBundleMessages INSTANCE = GWT.create(DatasourceBundleMessages.class);

   String databaseBundleTypeName();

   String editDataSource();

   String loginSelectionFieldLabel();

   String keyProviderLoginLabel();

   String keyProviderParamLabel();

   String mappingProviderStaticLabel();

   String mappingProviderAutoNameLabel();

   String mappingProviderAutoIdLabel();

}