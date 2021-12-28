package net.datenwerke.gf.service.history;

import java.util.Random;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.client.history.HistoryLocation;

/**
 * 
 *
 */
@GenerateDto(dtoPackage = "net.datenwerke.gf.client.history.dto")
public class HistoryLink {

   @ExposeToClient(disableHtmlEncode = true)
   private String historyToken;

   @ExposeToClient
   private String objectCaption;

   @ExposeToClient
   private String historyLinkBuilderId;

   @ExposeToClient
   private String historyLinkBuilderName;

   @ExposeToClient
   private String historyLinkBuilderIcon;

   public HistoryLink() {
   }

   public HistoryLink(String objectCaption, String historyToken, String historyLinkBuilderId) {
      super();
      this.objectCaption = objectCaption;
      this.historyToken = historyToken;
      this.historyLinkBuilderId = historyLinkBuilderId;
      this.historyLinkBuilderName = historyLinkBuilderId;
   }

   public String getHistoryToken() {
      return historyToken;
   }

   public void setHistoryToken(String historyToken) {
      this.historyToken = historyToken;
   }

   public String getObjectCaption() {
      return objectCaption;
   }

   public void setObjectCaption(String objectCaption) {
      this.objectCaption = objectCaption;
   }

   public String getHistoryLinkBuilderId() {
      return historyLinkBuilderId;
   }

   public void setHistoryLinkBuilderId(String historyLinkBuilderId) {
      this.historyLinkBuilderId = historyLinkBuilderId;
   }

   public String getLink() {
      if (null == historyToken)
         return null;
      if (historyToken.endsWith(HistoryLocation.SEP_LOC_PARAM))
         return historyToken + "nonce" + HistoryLocation.SEP_PARAM_KEY_VALUE + new Random().nextInt();
      return historyToken + HistoryLocation.SEP_PARAMS + "nonce" + HistoryLocation.SEP_PARAM_KEY_VALUE
            + new Random().nextInt();
   }

   public void setHistoryLinkBuilderName(String historyLinkBuilderName) {
      this.historyLinkBuilderName = historyLinkBuilderName;
   }

   public String getHistoryLinkBuilderName() {
      return historyLinkBuilderName;
   }

   public void setHistoryLinkBuilderIcon(String historyLinkBuilderIcon) {
      this.historyLinkBuilderIcon = historyLinkBuilderIcon;
   }

   public String getHistoryLinkBuilderIcon() {
      return historyLinkBuilderIcon;
   }

}
