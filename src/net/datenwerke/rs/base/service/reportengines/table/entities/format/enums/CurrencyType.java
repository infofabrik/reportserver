package net.datenwerke.rs.base.service.reportengines.table.entities.format.enums;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;

@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.reportengines.table.dto")
public enum CurrencyType {

   @EnumLabel(msg = EnumMessages.class, key = "currencyEuro")
   EURO("currencyEuro"),

   @EnumLabel(msg = EnumMessages.class, key = "currencyDollar")
   DOLLAR("currencyDollar"),

   @EnumLabel(msg = EnumMessages.class, key = "currencyPound")
   BRITTISH_POUND("currencyPound"),

   @EnumLabel(msg = EnumMessages.class, key = "AED")
   AED("AED"),

   @EnumLabel(msg = EnumMessages.class, key = "AFN")
   AFN("AFN"),

   @EnumLabel(msg = EnumMessages.class, key = "ALL")
   ALL("ALL"),

   @EnumLabel(msg = EnumMessages.class, key = "AMD")
   AMD("AMD"),

   @EnumLabel(msg = EnumMessages.class, key = "ANG")
   ANG("ANG"),

   @EnumLabel(msg = EnumMessages.class, key = "AOA")
   AOA("AOA"),

   @EnumLabel(msg = EnumMessages.class, key = "ARS")
   ARS("ARS"),

   @EnumLabel(msg = EnumMessages.class, key = "AUD")
   AUD("AUD"),

   @EnumLabel(msg = EnumMessages.class, key = "AWG")
   AWG("AWG"),

   @EnumLabel(msg = EnumMessages.class, key = "AZN")
   AZN("AZN"),

   @EnumLabel(msg = EnumMessages.class, key = "BAM")
   BAM("BAM"),

   @EnumLabel(msg = EnumMessages.class, key = "BBD")
   BBD("BBD"),

   @EnumLabel(msg = EnumMessages.class, key = "BDT")
   BDT("BDT"),

   @EnumLabel(msg = EnumMessages.class, key = "BGN")
   BGN("BGN"),

   @EnumLabel(msg = EnumMessages.class, key = "BHD")
   BHD("BHD"),

   @EnumLabel(msg = EnumMessages.class, key = "BIF")
   BIF("BIF"),

   @EnumLabel(msg = EnumMessages.class, key = "BMD")
   BMD("BMD"),

   @EnumLabel(msg = EnumMessages.class, key = "BND")
   BND("BND"),

   @EnumLabel(msg = EnumMessages.class, key = "BOB")
   BOB("BOB"),

   @EnumLabel(msg = EnumMessages.class, key = "BRL")
   BRL("BRL"),

   @EnumLabel(msg = EnumMessages.class, key = "BSD")
   BSD("BSD"),

   @EnumLabel(msg = EnumMessages.class, key = "BTN")
   BTN("BTN"),

   @EnumLabel(msg = EnumMessages.class, key = "BWP")
   BWP("BWP"),

   @EnumLabel(msg = EnumMessages.class, key = "BYR")
   BYR("BYR"),

   @EnumLabel(msg = EnumMessages.class, key = "BZD")
   BZD("BZD"),

   @EnumLabel(msg = EnumMessages.class, key = "CAD")
   CAD("CAD"),

   @EnumLabel(msg = EnumMessages.class, key = "CDF")
   CDF("CDF"),

   @EnumLabel(msg = EnumMessages.class, key = "CHF")
   CHF("CHF"),

   @EnumLabel(msg = EnumMessages.class, key = "CLP")
   CLP("CLP"),

   @EnumLabel(msg = EnumMessages.class, key = "CNY")
   CNY("CNY"),

   @EnumLabel(msg = EnumMessages.class, key = "COP")
   COP("COP"),

   @EnumLabel(msg = EnumMessages.class, key = "CRC")
   CRC("CRC"),

   @EnumLabel(msg = EnumMessages.class, key = "CUC")
   CUC("CUC"),

   @EnumLabel(msg = EnumMessages.class, key = "CUP")
   CUP("CUP"),

   @EnumLabel(msg = EnumMessages.class, key = "CVE")
   CVE("CVE"),

   @EnumLabel(msg = EnumMessages.class, key = "CZK")
   CZK("CZK"),

   @EnumLabel(msg = EnumMessages.class, key = "DJF")
   DJF("DJF"),

   @EnumLabel(msg = EnumMessages.class, key = "DKK")
   DKK("DKK"),

   @EnumLabel(msg = EnumMessages.class, key = "DOP")
   DOP("DOP"),

   @EnumLabel(msg = EnumMessages.class, key = "DZD")
   DZD("DZD"),

   @EnumLabel(msg = EnumMessages.class, key = "EGP")
   EGP("EGP"),

   @EnumLabel(msg = EnumMessages.class, key = "ERN")
   ERN("ERN"),

   @EnumLabel(msg = EnumMessages.class, key = "ETB")
   ETB("ETB"),

   @EnumLabel(msg = EnumMessages.class, key = "EUR")
   EUR("EUR"),

   @EnumLabel(msg = EnumMessages.class, key = "FJD")
   FJD("FJD"),

   @EnumLabel(msg = EnumMessages.class, key = "FKP")
   FKP("FKP"),

   @EnumLabel(msg = EnumMessages.class, key = "GBP")
   GBP("GBP"),

   @EnumLabel(msg = EnumMessages.class, key = "GEL")
   GEL("GEL"),

   @EnumLabel(msg = EnumMessages.class, key = "GGP")
   GGP("GGP"),

   @EnumLabel(msg = EnumMessages.class, key = "GHS")
   GHS("GHS"),

   @EnumLabel(msg = EnumMessages.class, key = "GIP")
   GIP("GIP"),

   @EnumLabel(msg = EnumMessages.class, key = "GMD")
   GMD("GMD"),

   @EnumLabel(msg = EnumMessages.class, key = "GNF")
   GNF("GNF"),

   @EnumLabel(msg = EnumMessages.class, key = "GTQ")
   GTQ("GTQ"),

   @EnumLabel(msg = EnumMessages.class, key = "GYD")
   GYD("GYD"),

   @EnumLabel(msg = EnumMessages.class, key = "HKD")
   HKD("HKD"),

   @EnumLabel(msg = EnumMessages.class, key = "HNL")
   HNL("HNL"),

   @EnumLabel(msg = EnumMessages.class, key = "HRK")
   HRK("HRK"),

   @EnumLabel(msg = EnumMessages.class, key = "HTG")
   HTG("HTG"),

   @EnumLabel(msg = EnumMessages.class, key = "HUF")
   HUF("HUF"),

   @EnumLabel(msg = EnumMessages.class, key = "IDR")
   IDR("IDR"),

   @EnumLabel(msg = EnumMessages.class, key = "ILS")
   ILS("ILS"),

   @EnumLabel(msg = EnumMessages.class, key = "IMP")
   IMP("IMP"),

   @EnumLabel(msg = EnumMessages.class, key = "INR")
   INR("INR"),

   @EnumLabel(msg = EnumMessages.class, key = "IQD")
   IQD("IQD"),

   @EnumLabel(msg = EnumMessages.class, key = "IRR")
   IRR("IRR"),

   @EnumLabel(msg = EnumMessages.class, key = "ISK")
   ISK("ISK"),

   @EnumLabel(msg = EnumMessages.class, key = "JEP")
   JEP("JEP"),

   @EnumLabel(msg = EnumMessages.class, key = "JMD")
   JMD("JMD"),

   @EnumLabel(msg = EnumMessages.class, key = "JOD")
   JOD("JOD"),

   @EnumLabel(msg = EnumMessages.class, key = "JPY")
   JPY("JPY"),

   @EnumLabel(msg = EnumMessages.class, key = "KES")
   KES("KES"),

   @EnumLabel(msg = EnumMessages.class, key = "KGS")
   KGS("KGS"),

   @EnumLabel(msg = EnumMessages.class, key = "KHR")
   KHR("KHR"),

   @EnumLabel(msg = EnumMessages.class, key = "KMF")
   KMF("KMF"),

   @EnumLabel(msg = EnumMessages.class, key = "KPW")
   KPW("KPW"),

   @EnumLabel(msg = EnumMessages.class, key = "KRW")
   KRW("KRW"),

   @EnumLabel(msg = EnumMessages.class, key = "KWD")
   KWD("KWD"),

   @EnumLabel(msg = EnumMessages.class, key = "KYD")
   KYD("KYD"),

   @EnumLabel(msg = EnumMessages.class, key = "KZT")
   KZT("KZT"),

   @EnumLabel(msg = EnumMessages.class, key = "LAK")
   LAK("LAK"),

   @EnumLabel(msg = EnumMessages.class, key = "LBP")
   LBP("LBP"),

   @EnumLabel(msg = EnumMessages.class, key = "LKR")
   LKR("LKR"),

   @EnumLabel(msg = EnumMessages.class, key = "LRD")
   LRD("LRD"),

   @EnumLabel(msg = EnumMessages.class, key = "LSL")
   LSL("LSL"),

   @EnumLabel(msg = EnumMessages.class, key = "LYD")
   LYD("LYD"),

   @EnumLabel(msg = EnumMessages.class, key = "MAD")
   MAD("MAD"),

   @EnumLabel(msg = EnumMessages.class, key = "MDL")
   MDL("MDL"),

   @EnumLabel(msg = EnumMessages.class, key = "MGA")
   MGA("MGA"),

   @EnumLabel(msg = EnumMessages.class, key = "MKD")
   MKD("MKD"),

   @EnumLabel(msg = EnumMessages.class, key = "MMK")
   MMK("MMK"),

   @EnumLabel(msg = EnumMessages.class, key = "MNT")
   MNT("MNT"),

   @EnumLabel(msg = EnumMessages.class, key = "MOP")
   MOP("MOP"),

   @EnumLabel(msg = EnumMessages.class, key = "MRU")
   MRU("MRU"),

   @EnumLabel(msg = EnumMessages.class, key = "MUR")
   MUR("MUR"),

   @EnumLabel(msg = EnumMessages.class, key = "MVR")
   MVR("MVR"),

   @EnumLabel(msg = EnumMessages.class, key = "MWK")
   MWK("MWK"),

   @EnumLabel(msg = EnumMessages.class, key = "MXN")
   MXN("MXN"),

   @EnumLabel(msg = EnumMessages.class, key = "MYR")
   MYR("MYR"),

   @EnumLabel(msg = EnumMessages.class, key = "MZN")
   MZN("MZN"),

   @EnumLabel(msg = EnumMessages.class, key = "NAD")
   NAD("NAD"),

   @EnumLabel(msg = EnumMessages.class, key = "NGN")
   NGN("NGN"),

   @EnumLabel(msg = EnumMessages.class, key = "NIO")
   NIO("NIO"),

   @EnumLabel(msg = EnumMessages.class, key = "NOK")
   NOK("NOK"),

   @EnumLabel(msg = EnumMessages.class, key = "NPR")
   NPR("NPR"),

   @EnumLabel(msg = EnumMessages.class, key = "NZD")
   NZD("NZD"),

   @EnumLabel(msg = EnumMessages.class, key = "OMR")
   OMR("OMR"),

   @EnumLabel(msg = EnumMessages.class, key = "PAB")
   PAB("PAB"),

   @EnumLabel(msg = EnumMessages.class, key = "PEN")
   PEN("PEN"),

   @EnumLabel(msg = EnumMessages.class, key = "PGK")
   PGK("PGK"),

   @EnumLabel(msg = EnumMessages.class, key = "PHP")
   PHP("PHP"),

   @EnumLabel(msg = EnumMessages.class, key = "PKR")
   PKR("PKR"),

   @EnumLabel(msg = EnumMessages.class, key = "PLN")
   PLN("PLN"),

   @EnumLabel(msg = EnumMessages.class, key = "PYG")
   PYG("PYG"),

   @EnumLabel(msg = EnumMessages.class, key = "QAR")
   QAR("QAR"),

   @EnumLabel(msg = EnumMessages.class, key = "RON")
   RON("RON"),

   @EnumLabel(msg = EnumMessages.class, key = "RSD")
   RSD("RSD"),

   @EnumLabel(msg = EnumMessages.class, key = "RUB")
   RUB("RUB"),

   @EnumLabel(msg = EnumMessages.class, key = "RWF")
   RWF("RWF"),

   @EnumLabel(msg = EnumMessages.class, key = "SAR")
   SAR("SAR"),

   @EnumLabel(msg = EnumMessages.class, key = "SBD")
   SBD("SBD"),

   @EnumLabel(msg = EnumMessages.class, key = "SCR")
   SCR("SCR"),

   @EnumLabel(msg = EnumMessages.class, key = "SDG")
   SDG("SDG"),

   @EnumLabel(msg = EnumMessages.class, key = "SEK")
   SEK("SEK"),

   @EnumLabel(msg = EnumMessages.class, key = "SGD")
   SGD("SGD"),

   @EnumLabel(msg = EnumMessages.class, key = "SHP")
   SHP("SHP"),

   @EnumLabel(msg = EnumMessages.class, key = "SLL")
   SLL("SLL"),

   @EnumLabel(msg = EnumMessages.class, key = "SOS")
   SOS("SOS"),

   @EnumLabel(msg = EnumMessages.class, key = "SRD")
   SRD("SRD"),

   @EnumLabel(msg = EnumMessages.class, key = "SSP")
   SSP("SSP"),

   @EnumLabel(msg = EnumMessages.class, key = "STN")
   STN("STN"),

   @EnumLabel(msg = EnumMessages.class, key = "SVC")
   SVC("SVC"),

   @EnumLabel(msg = EnumMessages.class, key = "SYP")
   SYP("SYP"),

   @EnumLabel(msg = EnumMessages.class, key = "SZL")
   SZL("SZL"),

   @EnumLabel(msg = EnumMessages.class, key = "THB")
   THB("THB"),

   @EnumLabel(msg = EnumMessages.class, key = "TJS")
   TJS("TJS"),

   @EnumLabel(msg = EnumMessages.class, key = "TMT")
   TMT("TMT"),

   @EnumLabel(msg = EnumMessages.class, key = "TND")
   TND("TND"),

   @EnumLabel(msg = EnumMessages.class, key = "TOP")
   TOP("TOP"),

   @EnumLabel(msg = EnumMessages.class, key = "TRY")
   TRY("TRY"),

   @EnumLabel(msg = EnumMessages.class, key = "TTD")
   TTD("TTD"),

   @EnumLabel(msg = EnumMessages.class, key = "TVD")
   TVD("TVD"),

   @EnumLabel(msg = EnumMessages.class, key = "TWD")
   TWD("TWD"),

   @EnumLabel(msg = EnumMessages.class, key = "TZS")
   TZS("TZS"),

   @EnumLabel(msg = EnumMessages.class, key = "UAH")
   UAH("UAH"),

   @EnumLabel(msg = EnumMessages.class, key = "UGX")
   UGX("UGX"),

   @EnumLabel(msg = EnumMessages.class, key = "USD")
   USD("USD"),

   @EnumLabel(msg = EnumMessages.class, key = "UYU")
   UYU("UYU"),

   @EnumLabel(msg = EnumMessages.class, key = "UZS")
   UZS("UZS"),

   @EnumLabel(msg = EnumMessages.class, key = "VEF")
   VEF("VEF"),

   @EnumLabel(msg = EnumMessages.class, key = "VND")
   VND("VND"),

   @EnumLabel(msg = EnumMessages.class, key = "VUV")
   VUV("VUV"),

   @EnumLabel(msg = EnumMessages.class, key = "WST")
   WST("WST"),

   @EnumLabel(msg = EnumMessages.class, key = "XAF")
   XAF("XAF"),

   @EnumLabel(msg = EnumMessages.class, key = "XCD")
   XCD("XCD"),

   @EnumLabel(msg = EnumMessages.class, key = "XOF")
   XOF("XOF"),

   @EnumLabel(msg = EnumMessages.class, key = "XPF")
   XPF("XPF"),

   @EnumLabel(msg = EnumMessages.class, key = "YER")
   YER("YER"),

   @EnumLabel(msg = EnumMessages.class, key = "ZAR")
   ZAR("ZAR"),

   @EnumLabel(msg = EnumMessages.class, key = "ZMW")
   ZMW("ZMW"),

   @EnumLabel(msg = EnumMessages.class, key = "ZWL")
   ZWL("ZWL");

   private static Map<String, String> defaultLanguages = new HashMap<String, String>() {

      /****/
      private static final long serialVersionUID = 1L;

      {
         put("currencyEuro", "de");
         put("currencyDollar", "en");
         put("currencyPound", "en");
         put("AED", "ar");
         put("AFN", "ps");
         put("ALL", "sq");
         put("AMD", "hy");
         put("ANG", "pap");
         put("AOA", "pt");
         put("ARS", "es");
         put("AUD", "en");
         put("AWG", "nl");
         put("AZN", "az");
         put("BAM", "bs");
         put("BBD", "en");
         put("BDT", "bn");
         put("BGN", "bg");
         put("BHD", "ar");
         put("BIF", "en");
         put("BMD", "en");
         put("BND", "ms");
         put("BOB", "es");
         put("BRL", "pt");
         put("BSD", "en");
         put("BTN", "dz");
         put("BWP", "en");
         put("BYR", "be");
         put("BZD", "en");
         put("CAD", "en");
         put("CDF", "ln");
         put("CHF", "de");
         put("CLP", "es");
         put("CNY", "zh");
         put("COP", "es");
         put("CRC", "es");
         put("CUC", "es");
         put("CUP", "es");
         put("CVE", "pt");
         put("CZK", "cs");
         put("DJF", "aa");
         put("DKK", "da");
         put("DOP", "es");
         put("DZD", "ar");
         put("EGP", "ar");
         put("ERN", "ti");
         put("ETB", "ti");
         put("EUR", "de");
         put("FJD", "hif");
         put("FKP", "en");
         put("GBP", "en");
         put("GEL", "ka");
         put("GGP", "en");
         put("GHS", "ak");
         put("GIP", "en");
         put("GMD", "en");
         put("GNF", "fr");
         put("GTQ", "es");
         put("GYD", "en");
         put("HKD", "en");
         put("HNL", "es");
         put("HRK", "hr");
         put("HTG", "ht");
         put("HUF", "hu");
         put("IDR", "id");
         put("ILS", "he");
         put("IMP", "en");
         put("INR", "hi");
         put("IQD", "ar");
         put("IRR", "fa");
         put("ISK", "is");
         put("JEP", "en");
         put("JMD", "en");
         put("JOD", "ar");
         put("JPY", "ja");
         put("KES", "om");
         put("KGS", "ky");
         put("KHR", "km");
         put("KMF", "fr");
         put("KPW", "ko");
         put("KRW", "ko");
         put("KWD", "ar");
         put("KYD", "en");
         put("KZT", "kk");
         put("LAK", "lo");
         put("LBP", "ar");
         put("LKR", "si");
         put("LRD", "en");
         put("LSL", "en");
         put("LYD", "ar");
         put("MAD", "ar");
         put("MDL", "ru");
         put("MGA", "mg");
         put("MKD", "mk");
         put("MMK", "my");
         put("MNT", "mn");
         put("MOP", "en");
         put("MRU", "ar");
         put("MUR", "mfe");
         put("MVR", "dv");
         put("MWK", "en");
         put("MXN", "es");
         put("MYR", "ms");
         put("MZN", "pt");
         put("NAD", "en");
         put("NGN", "en");
         put("NIO", "es");
         put("NOK", "nn");
         put("NPR", "ne");
         put("NZD", "en");
         put("OMR", "ar");
         put("PAB", "es");
         put("PEN", "es");
         put("PGK", "tpi");
         put("PHP", "fil");
         put("PKR", "pa");
         put("PLN", "pl");
         put("PYG", "es");
         put("QAR", "ar");
         put("RON", "ro");
         put("RSD", "sr");
         put("RUB", "ru");
         put("RWF", "rw");
         put("SAR", "ar");
         put("SBD", "en");
         put("SCR", "en");
         put("SDG", "ar");
         put("SEK", "sv");
         put("SGD", "en");
         put("SHP", "en");
         put("SLL", "en");
         put("SOS", "so");
         put("SRD", "nl");
         put("SSP", "en");
         put("STN", "pt");
         put("SVC", "es");
         put("SYP", "ar");
         put("SZL", "en");
         put("THB", "th");
         put("TJS", "tg");
         put("TMT", "tk");
         put("TND", "ar");
         put("TOP", "to");
         put("TRY", "tr");
         put("TTD", "en");
         put("TVD", "en");
         put("TWD", "zh");
         put("TZS", "sw");
         put("UAH", "uk");
         put("UGX", "lg");
         put("USD", "en");
         put("UYU", "es");
         put("UZS", "uz");
         put("VEF", "es");
         put("VND", "vi");
         put("VUV", "bi");
         put("WST", "sm");
         put("XAF", "fr");
         put("XCD", "en");
         put("XOF", "fr");
         put("XPF", "fr");
         put("YER", "ar");
         put("ZAR", "en");
         put("ZMW", "en");
         put("ZWL", "en");
      }
   };

   private static Map<String, String> defaultRegions = new HashMap<String, String>() {
      /****/
      private static final long serialVersionUID = 1L;

      {
         put("currencyEuro", "DE");
         put("currencyDollar", "US");
         put("currencyPound", "GB");
         put("AED", "AE");
         put("AFN", "AF");
         put("ALL", "AL");
         put("AMD", "AM");
         put("ANG", "CW");
         put("AOA", "AO");
         put("ARS", "AR");
         put("AUD", "AU");
         put("AWG", "AW");
         put("AZN", "AZ");
         put("BAM", "BA");
         put("BBD", "BB");
         put("BDT", "BD");
         put("BGN", "BG");
         put("BHD", "BH");
         put("BIF", "BI");
         put("BMD", "BM");
         put("BND", "BN");
         put("BOB", "BO");
         put("BRL", "BR");
         put("BSD", "BS");
         put("BTN", "BT");
         put("BWP", "BW");
         put("BYR", "BY");
         put("BZD", "BZ");
         put("CAD", "CA");
         put("CDF", "CD");
         put("CHF", "CH");
         put("CLP", "CL");
         put("CNY", "CN");
         put("COP", "CO");
         put("CRC", "CR");
         put("CUC", "CU");
         put("CUP", "CU");
         put("CVE", "CV");
         put("CZK", "CZ");
         put("DJF", "DJ");
         put("DKK", "DK");
         put("DOP", "DO");
         put("DZD", "DZ");
         put("EGP", "EG");
         put("ERN", "ER");
         put("ETB", "ET");
         put("EUR", "DE");
         put("FJD", "FJ");
         put("FKP", "FK");
         put("GBP", "GB");
         put("GEL", "GE");
         put("GGP", "GG");
         put("GHS", "GH");
         put("GIP", "GI");
         put("GMD", "GM");
         put("GNF", "GN");
         put("GTQ", "GT");
         put("GYD", "GY");
         put("HKD", "HK");
         put("HNL", "HN");
         put("HRK", "HR");
         put("HTG", "HT");
         put("HUF", "HU");
         put("IDR", "ID");
         put("ILS", "IL");
         put("IMP", "IM");
         put("INR", "IN");
         put("IQD", "IQ");
         put("IRR", "IR");
         put("ISK", "IS");
         put("JEP", "JE");
         put("JMD", "JM");
         put("JOD", "JO");
         put("JPY", "JP");
         put("KES", "KE");
         put("KGS", "KG");
         put("KHR", "KH");
         put("KMF", "KM");
         put("KPW", "KP");
         put("KRW", "KR");
         put("KWD", "KW");
         put("KYD", "KY");
         put("KZT", "KZ");
         put("LAK", "LA");
         put("LBP", "LB");
         put("LKR", "LK");
         put("LRD", "LR");
         put("LSL", "LS");
         put("LYD", "LY");
         put("MAD", "MA");
         put("MDL", "MD");
         put("MGA", "MG");
         put("MKD", "MK");
         put("MMK", "MM");
         put("MNT", "MN");
         put("MOP", "MO");
         put("MRU", "MR");
         put("MUR", "MU");
         put("MVR", "MV");
         put("MWK", "MW");
         put("MXN", "MX");
         put("MYR", "MY");
         put("MZN", "MZ");
         put("NAD", "NA");
         put("NGN", "NG");
         put("NIO", "NI");
         put("NOK", "NO");
         put("NPR", "NP");
         put("NZD", "NZ");
         put("OMR", "OM");
         put("PAB", "PA");
         put("PEN", "PE");
         put("PGK", "PG");
         put("PHP", "PH");
         put("PKR", "PK");
         put("PLN", "PL");
         put("PYG", "PY");
         put("QAR", "QA");
         put("RON", "RO");
         put("RSD", "RS");
         put("RUB", "RU");
         put("RWF", "RW");
         put("SAR", "SA");
         put("SBD", "SB");
         put("SCR", "SC");
         put("SDG", "SD");
         put("SEK", "SE");
         put("SGD", "SG");
         put("SHP", "SH");
         put("SLL", "SL");
         put("SOS", "SO");
         put("SRD", "SR");
         put("SSP", "SS");
         put("STN", "ST");
         put("SVC", "SV");
         put("SYP", "SY");
         put("SZL", "SZ");
         put("THB", "TH");
         put("TJS", "TJ");
         put("TMT", "TM");
         put("TND", "TN");
         put("TOP", "TO");
         put("TRY", "TR");
         put("TTD", "TT");
         put("TVD", "TV");
         put("TWD", "TW");
         put("TZS", "TZ");
         put("UAH", "UA");
         put("UGX", "UG");
         put("USD", "US");
         put("UYU", "UY");
         put("UZS", "UZ");
         put("VEF", "VE");
         put("VND", "VN");
         put("VUV", "VU");
         put("WST", "WS");
         put("XAF", "CM");
         put("XCD", "LC");
         put("XOF", "BJ");
         put("XPF", "PF");
         put("YER", "YE");
         put("ZAR", "ZA");
         put("ZMW", "ZM");
         put("ZWL", "ZW");
      }
   };

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());
   private String key;
   private String language;
   private String region;

   @Inject
   private static Provider<ConfigService> configServiceProvider;
   
   public static final String CONFIG_FILE = "main/localization.cf";
   public static final String CURRENCY_LIST = "localization.currencies.currency";

   private CurrencyType(String key) {
      this.key = key;
   }

   public Currency getCurrency() {
      switch (this) {
      case EURO:
         return Currency.getInstance(Locale.GERMANY);
      case DOLLAR:
         return Currency.getInstance(Locale.US);
      case BRITTISH_POUND:
         return Currency.getInstance(Locale.UK);
      default:
         try {
            return Currency.getInstance(name());
         } catch (Exception e) {
            return Currency.getInstance(Locale.US);
         }
      }
   }

   public Locale getLocale() {

      HierarchicalConfiguration config = null;
      if (null == language || null == region) {
         try {
            config = (HierarchicalConfiguration) ((ConfigService) configServiceProvider.get()).getConfig(CONFIG_FILE);
            if (null == config)
               return new Locale("en", "US");
         } catch (Exception e) {
            return new Locale("en", "US");
         }

         /* get the list of all currency and the attributes language and region */
         List<Object> currencyList = config.getList(CURRENCY_LIST);
         if (null == currencyList) {
            language = defaultLanguages.get(key);
            region = defaultRegions.get(key);
         } else {
            for (Object o : currencyList) {
               if ((o instanceof String) && key.equals(o)) {
                  language = config.getString(
                        "localization.currencies.currency(" + currencyList.indexOf(o) + ")[@language]",
                        defaultLanguages.get(key));
                  region = config.getString(
                        "localization.currencies.currency(" + currencyList.indexOf(o) + ")[@region]",
                        defaultRegions.get(key));
                  break;
               }
            }
         }

         if (null == language)
            language = defaultLanguages.get(key);
         if (null == region)
            region = defaultRegions.get(key);
      }
      try {
         return new Locale(language, region);
      } catch (Exception e) {
         return new Locale("en", "US");
      }
   }

}
