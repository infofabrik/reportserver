package net.datenwerke.rs.amazons3.service.amazons3.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface AmazonS3DatasinkMessages extends Messages {

   public final static AmazonS3DatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(AmazonS3DatasinkMessages.class);

   String amazonS3DatasinkTypeName();

   String amazonS3Datasinks();

}
