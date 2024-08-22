package net.datenwerke.rs.theme.client.icon;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public enum BaseIcon {

   NO_ICON(""),

   ACCEPT("nf-fa-check_circle_o"), ADD("nf-fa-plus_circle"), AGGREGATION("nf-fa-compress"), EXECUTE("nf-fa-play_circle_o"),
   EXPAND_ALL("nf-fa-plus_square_o"), COLLAPSE("nf-fa-minus_square_o"), COLLAPSE_ALL("nf-fa-minus_square_o"), DADGET("nf-fa-tachometer"),
   DELETE("nf-fa-minus_circle"), DISCONNECT("nf-fa-chain_broken"), ERROR("nf-fa-exclamation_triangle nf-fa-col_red"), EXPORT("nf-fa-upload"),
   IMPORT("nf-fa-download"), FILTER_ADD("nf-fa-filter nf-fa-add_action"), FORMAT("nf-fa-italic"), GROUP_ADD("nf-fa-users nf-fa-add_action"),
   GROUP_EDIT("nf-fa-users nf-fa-edit_action"), GROUP_PROPERTIES("nf-fa-users"), INDEX("nf-fa-bookmark"), LOADING("nf-fa-spinner nf-fa-spin"),
   LOGICAL_AND("nf-fa-angle_up"), LOGICAL_OR("nf-fa-angle_down"), ITEMS_DETAIL("nf-fa-th_list"), ITEMS_LARGE("nf-fa-th_large"),
   NETWORK("nf-fa-sitemap"), NULL_EXCLUDE("nf-fa-minus_circle"), NULL_INCLUDE("nf-fa-plus_circle"), SCRIPT("nf-fa-file_code_o"),
   SCRIPT_LINK("nf-fa-file_code_o nf-fa-link_action"), SUBTOTALS("nf-fa-bars"), TILE_VERTICAL("nf-fa-columns"),
   TILE_HORIZONTAL("nf-fa-columns", BaseIconRotate.ROTATE_90), TEMPLATE("nf-fa-newspaper_o"), USER_PROFILE("nf-fa-user"),
   USER_VARIABLE("nf-fa-gamepad"), WEBPAGE("nf-fa-globe"),

   FILE_AUDIO("nf-fa-file_audio_o"), FILE_EXCEL("nf-fa-file_excel_o"), FILE_PDF("nf-fa-file_pdf_o"), FILE_CODE("nf-fa-file_code_o"),
   FILE_IMAGE("nf-fa-file_image_o"), FILE_WORD("nf-fa-file_word_o"), FILE_VIDEO("nf-fa-file_video_o"), FILE_PPT("nf-fa-file_powerpoint_o"),
   FILE_ZIP("nf-fa-file_archive-o"),

   FILTER_EDIT("nf-fa-filter nf-fa-edit_action"),

   REPORT("nf-fa-file"), REPORT_ADD("nf-fa-file nf-fa-add_action"), REPORT_DELETE("nf-fa-file nf-fa-remove_action"),
   REPORT_LINK("nf-fa-file nf-fa-link_action"), REPORT_DISK("nf-fa-file"), REPORT_USER("nf-fa-file"), REPORT_MAGNIFY("nf-fa-file"),
   REPORT_PICTURE("nf-fa-pie_chart"),

   REPORT_BIRT("nf-fa-file_image_o"), REPORT_BIRT_LINK("nf-fa-file_image_o nf-fa-link_action"),

   REPORT_SAIKU("nf-fa-cube"), REPORT_SAIKU_LINK("nf-fa-cube nf-fa-link_action"),

   REPORT_DL("nf-fa-table"), REPORT_DL_LINK("nf-fa-table nf-fa-link_action"),

   REPORT_JXLS("nf-fa-file_excel_o"), REPORT_JXLS_LINK("nf-fa-file_excel_o nf-fa-link_action"),

   REPORT_GE("nf-fa-table nf-fa-edit_action"), REPORT_GE_LINK("nf-fa-table nf-fa-link_action"),

   REPORT_CRYSTAL("nf-fa-diamond"), REPORT_CRYSTAL_LINK("nf-fa-diamond nf-fa-link_action"), REPORT_JASPER("nf-fa-newspaper_o"),
   REPORT_JASPER_LINK("nf-fa-newspaper_o nf-fa-link_action"),

   ARROWS_IN("nf-fa-compress"), ARROWS_OUT("nf-fa-arrows_alt"), ARROWS_IN_OUT("nf-fa-exchange"),

   CHART_AREA("nf-fa-chart_area"), CHART_BAR("nf-fa-bar_chart"), CHART_LINE("nf-fa-line_chart"), CHART_PIE("nf-fa-pie_chart"),

   COG_ADD("nf-fa-cog nf-fa-add_action"), COG_DELETE("nf-fa-cog nf-fa-remove_action"), COG_EDIT("nf-fa-cog nf-fa-edit_action"),

   CLOCK_EDIT("nf-fa-clock_o nf-fa-edit_action"),

   EDIT_NOT("nf-fa-ban"),

   FOLDER_ADD("nf-fa-folder_o nf-fa-add_action"), FOLDER_USER("nf-fa-folder_o"),

   KEY_ADD("nf-fa-key nf-fa-add_action"),

   LIGHTBULB("nf-fa-lightbulb"), LOCK_ADD("nf-fa-lock nf-fa-add_action"),

   NEWSPAPER_LINK("nf-fa-newspaper_o nf-fa-link_action"),

   ROTATE_RIGHT("nf-fa-rotate_right"),

   TABLE_ADD("nf-fa-table nf-fa-add_action"), TABLE_EDIT("nf-fa-table nf-fa-edit_action"),

   USER_ADD("nf-fa-user nf-fa-add_action"), USER_BLOCKED("nf-fa-user nf-fa-col_light"), USER_COMMENT("nf-fa-user"),
   USER_EDIT("nf-fa-user nf-fa-edit_action"), USER_MALE("nf-fa-male"), USER_FEMALE("nf-fa-female"),
   
   GOOGLE_DRIVE("nf-dev-google_drive"), BOX("nf-md-box"),
   
   /* nerd icon - font awesome */
   GLASS("nf-fa-glass"), MUSIC("nf-fa-music"), SEARCH("nf-fa-search"), ENVELOPE_O("nf-fa-envelope_o"), HEART("nf-fa-heart"), STAR("nf-fa-star"),
   STAR_O("nf-fa-star_o"), USER("nf-fa-user"), FILM("nf-fa-film"), TH_LARGE("nf-fa-th_large"), TH("nf-fa-th"), TH_LIST("nf-fa-th_list"), CHECK("nf-fa-check"),
   REMOVE("nf-fa-remove"), CLOSE("nf-fa-close"), TIMES("nf-fa-times"), SEARCH_PLUS("nf-fa-search_plus"), SEARCH_MINUS("nf-fa-search_minus"),
   POWER_OFF("nf-fa-power_off"), SIGNAL("nf-fa-signal"), GEAR("nf-fa-gear"), COG("nf-fa-cog"), TRASH_O("nf-fa-trash_o"), HOME("nf-fa-home"),
   FILE_O("nf-fa-file_o"), CLOCK_O("nf-fa-clock_o"), ROAD("nf-fa-road"), DOWNLOAD("nf-fa-download"), ARROW_CIRCLE_O_DOWN("nf-fa-arrow_circle_o_down"),
   ARROW_CIRCLE_O_UP("nf-fa-arrow_circle_o_up"), INBOX("nf-fa-inbox"), PLAY_CIRCLE_O("nf-fa-play_circle_o"), REPEAT("nf-fa-repeat"),
   REFRESH("nf-fa-refresh"), LIST_ALT("nf-fa-list_alt"), LOCK("nf-fa-lock"), FLAG("nf-fa-flag"), HEADPHONES("nf-fa-headphones"),
   VOLUME_OFF("nf-fa-volume_off"), VOLUME_DOWN("nf-fa-volume_down"), VOLUME_UP("nf-fa-volume_up"), QRCODE("nf-fa-qrcode"), BARCODE("nf-fa-barcode"),
   TAG("nf-fa-tag"), TAGS("nf-fa-tags"), BOOK("nf-fa-book"), BOOKMARK("nf-fa-bookmark"), PRINT("nf-fa-print"), CAMERA("nf-fa-camera"), FONT("nf-fa-font"),
   BOLD("nf-fa-bold"), ITALIC("nf-fa-italic"), TEXT_HEIGHT("nf-fa-text_height"), TEXT_WIDTH("nf-fa-text_width"), ALIGN_LEFT("nf-fa-align_left"),
   ALIGN_CENTER("nf-fa-align_center"), ALIGN_RIGHT("nf-fa-align_right"), ALIGN_JUSTIFY("nf-fa-align_justify"), LIST("nf-fa-list"),
   DEDENT("nf-fa-dedent"), OUTDENT("nf-fa-outdent"), INDENT("nf-fa-indent"), VIDEO_CAMERA("nf-fa-video_camera"), PHOTO("nf-fa-photo"), IMAGE("nf-fa-image"),
   PICTURE_O("nf-fa-picture_o"), PENCIL("nf-fa-pencil"), MAP_MARKER("nf-fa-map_marker"), ADJUST("nf-fa-adjust"), TINT("nf-fa-tint"), EDIT("nf-fa-edit"),
   PENCIL_SQUARE_O("nf-fa-pencil_square_o"), SHARE_SQUARE_O("nf-fa-share_square_o"), CHECK_SQUARE_O("nf-fa-check_square_o"),
   ARROWS("nf-fa-arrows"), STEP_BACKWARD("nf-fa-step_backward"), FAST_BACKWARD("nf-fa-fast_backward"), BACKWARD("nf-fa-backward"), PLAY("nf-fa-play"),
   PAUSE("nf-fa-pause"), STOP("nf-fa-stop"), FORWARD("nf-fa-forward"), FAST_FORWARD("nf-fa-fast_forward"), STEP_FORWARD("nf-fa-step_forward"),
   EJECT("nf-fa-eject"), CHEVRON_LEFT("nf-fa-chevron_left"), CHEVRON_RIGHT("nf-fa-chevron_right"), PLUS_CIRCLE("nf-fa-plus_circle"),
   MINUS_CIRCLE("nf-fa-minus_circle"), TIMES_CIRCLE("nf-fa-times_circle"), CHECK_CIRCLE("nf-fa-check_circle"),
   QUESTION_CIRCLE("nf-fa-question_circle"), INFO_CIRCLE("nf-fa-info_circle"), CROSSHAIRS("nf-fa-crosshairs"),
   TIMES_CIRCLE_O("nf-fa-times_circle_o"), CHECK_CIRCLE_O("nf-fa-check_circle_o"), BAN("nf-fa-ban"), ARROW_LEFT("nf-fa-arrow_left"),
   ARROW_RIGHT("nf-fa-arrow_right"), ARROW_UP("nf-fa-arrow_up"), ARROW_DOWN("nf-fa-arrow_down"), MAIL_FORWARD("nf-fa-mail_forward"),
   SHARE("nf-fa-share"), EXPAND("nf-fa-expand"), COMPRESS("nf-fa-compress"), PLUS("nf-fa-plus"), MINUS("nf-fa-minus"), ASTERISK("nf-fa-asterisk"),
   EXCLAMATION_CIRCLE("nf-fa-exclamation_circle"), GIFT("nf-fa-gift"), LEAF("nf-fa-leaf"), FIRE("nf-fa-fire"), EYE("nf-fa-eye"),
   EYE_SLASH("nf-fa-eye_slash"), WARNING("nf-fa-warning"), EXCLAMATION_TRIANGLE("nf-fa-exclamation_triangle"), PLANE("nf-fa-plane"),
   CALENDAR("nf-fa-calendar"), RANDOM("nf-fa-random"), COMMENT("nf-fa-comment"), MAGNET("nf-fa-magnet"), CHEVRON_UP("nf-fa-chevron_up"),
   CHEVRON_DOWN("nf-fa-chevron_down"), RETWEET("nf-fa-retweet"), SHOPPING_CART("nf-fa-shopping_cart"), FOLDER("nf-fa-folder"),
   FOLDER_OPEN("nf-fa-folder_open"), ARROWS_V("nf-fa-arrows_v"), ARROWS_H("nf-fa-arrows_h"), BAR_CHART_O("nf-fa-bar_chart_o"),
   BAR_CHART("nf-fa-bar_chart"), TWITTER_SQUARE("nf-fa-twitter_square"), FACEBOOK_SQUARE("nf-fa-facebook_square"),
   CAMERA_RETRO("nf-fa-camera_retro"), KEY("nf-fa-key"), GEARS("nf-fa-gears"), COGS("nf-fa-cogs"), COMMENTS("nf-fa-comments"),
   THUMBS_O_UP("nf-fa-thumbs_o_up"), THUMBS_O_DOWN("nf-fa-thumbs_o_down"), STAR_HALF("nf-fa-star_half"), HEART_O("nf-fa-heart_o"),
   SIGN_OUT("nf-fa-sign_out"), LINKEDIN_SQUARE("nf-fa-linkedin_square"), THUMB_TACK("nf-fa-thumb_tack"), EXTERNAL_LINK("nf-fa-external_link"),
   SIGN_IN("nf-fa-sign_in"), TROPHY("nf-fa-trophy"), GITHUB_SQUARE("nf-fa-github_square"), UPLOAD("nf-fa-upload"), LEMON_O("nf-fa-lemon_o"),
   PHONE("nf-fa-phone"), SQUARE_O("nf-fa-square_o"), BOOKMARK_O("nf-fa-bookmark_o"), PHONE_SQUARE("nf-fa-phone_square"), TWITTER("nf-fa-twitter"),
   FACEBOOK_F("nf-fa-facebook_f"), FACEBOOK("nf-fa-facebook"), GITHUB("nf-fa-github"), UNLOCK("nf-fa-unlock"), CREDIT_CARD("nf-fa-credit_card"),
   FEED("nf-fa-feed"), RSS("nf-fa-rss"), HDD_O("nf-fa-hdd_o"), BULLHORN("nf-fa-bullhorn"), BELL("nf-fa-bell"), CERTIFICATE("nf-fa-certificate"),
   HAND_O_RIGHT("nf-fa-hand_o_right"), HAND_O_LEFT("nf-fa-hand_o_left"), HAND_O_UP("nf-fa-hand_o_up"), HAND_O_DOWN("nf-fa-hand_o_down"),
   ARROW_CIRCLE_LEFT("nf-fa-arrow_circle_left"), ARROW_CIRCLE_RIGHT("nf-fa-arrow_circle_right"), ARROW_CIRCLE_UP("nf-fa-arrow_circle_up"),
   ARROW_CIRCLE_DOWN("nf-fa-arrow_circle_down"), GLOBE("nf-fa-globe"), WRENCH("nf-fa-wrench"), TASKS("nf-fa-tasks"), FILTER("nf-fa-filter"),
   BRIEFCASE("nf-fa-briefcase"), ARROWS_ALT("nf-fa-arrows_alt"), GROUP("nf-fa-group"), USERS("nf-fa-users"), CHAIN("nf-fa-chain"), LINK("nf-fa-link"),
   CLOUD("nf-fa-cloud"), FLASK("nf-fa-flask"), CUT("nf-fa-cut"), SCISSORS("nf-fa-scissors"), COPY("nf-fa-copy"), FILES_O("nf-fa-files_o"),
   PAPERCLIP("nf-fa-paperclip"), SAVE("nf-fa-save"), FLOPPY_O("nf-fa-floppy_o"), SQUARE("nf-fa-square"), NAVICON("nf-fa-navicon"), REORDER("nf-fa-reorder"),
   BARS("nf-fa-bars"), LIST_UL("nf-fa-list_ul"), LIST_OL("nf-fa-list_ol"), STRIKETHROUGH("nf-fa-strikethrough"), UNDERLINE("nf-fa-underline"),
   TABLE("nf-fa-table"), MAGIC("nf-fa-magic"), TRUCK("nf-fa-truck"), PINTEREST("nf-fa-pinterest"), PINTEREST_SQUARE("nf-fa-pinterest_square"),
   GOOGLE_PLUS_SQUARE("nf-fa-google_plus_square"), GOOGLE_PLUS("nf-fa-google_plus"), MONEY("nf-fa-money"), CARET_DOWN("nf-fa-caret_down"),
   CARET_UP("nf-fa-caret_up"), CARET_LEFT("nf-fa-caret_left"), CARET_RIGHT("nf-fa-caret_right"), COLUMNS("nf-fa-columns"), UNSORTED("nf-fa-unsorted"),
   SORT("nf-fa-sort"), SORT_DOWN("nf-fa-sort_down"), SORT_DESC("nf-fa-sort_desc"), SORT_UP("nf-fa-sort_up"), SORT_ASC("nf-fa-sort_asc"),
   ENVELOPE("nf-fa-envelope"), LINKEDIN("nf-fa-linkedin"), ROTATE_LEFT("nf-fa-rotate_left"), UNDO("nf-fa-undo"), LEGAL("nf-fa-legal"), GAVEL("nf-fa-gavel"),
   DASHBOARD("nf-fa-dashboard"), TACHOMETER("nf-fa-tachometer"), COMMENT_O("nf-fa-comment_o"), COMMENTS_O("nf-fa-comments_o"), FLASH("nf-fa-flash"),
   BOLT("nf-fa-bolt"), SITEMAP("nf-fa-sitemap"), UMBRELLA("nf-fa-umbrella"), PASTE("nf-fa-paste"), CLIPBOARD("nf-fa-clipboard"),
   LIGHTBULB_O("nf-fa-lightbulb_o"), EXCHANGE("nf-fa-exchange"), CLOUD_DOWNLOAD("nf-fa-cloud_download"), CLOUD_UPLOAD("nf-fa-cloud_upload"),
   USER_MD("nf-fa-user_md"), STETHOSCOPE("nf-fa-stethoscope"), SUITCASE("nf-fa-suitcase"), BELL_O("nf-fa-bell_o"), COFFEE("nf-fa-coffee"),
   CUTLERY("nf-fa-cutlery"), FILE_TEXT_O("nf-fa-file_text_o"), BUILDING_O("nf-fa-building_o"), HOSPITAL_O("nf-fa-hospital_o"),
   AMBULANCE("nf-fa-ambulance"), MEDKIT("nf-fa-medkit"), FIGHTER_JET("nf-fa-fighter_jet"), BEER("nf-fa-beer"), H_SQUARE("nf-fa-h_square"),
   PLUS_SQUARE("nf-fa-plus_square"), ANGLE_DOUBLE_LEFT("nf-fa-angle_double_left"), ANGLE_DOUBLE_RIGHT("nf-fa-angle_double_right"),
   ANGLE_DOUBLE_UP("nf-fa-angle_double_up"), ANGLE_DOUBLE_DOWN("nf-fa-angle_double_down"), ANGLE_LEFT("nf-fa-angle_left"),
   ANGLE_RIGHT("nf-fa-angle_right"), ANGLE_UP("nf-fa-angle_up"), ANGLE_DOWN("nf-fa-angle_down"), DESKTOP("nf-fa-desktop"), LAPTOP("nf-fa-laptop"),
   TABLET("nf-fa-tablet"), MOBILE_PHONE("nf-fa-mobile_phone"), MOBILE("nf-fa-mobile"), CIRCLE_O("nf-fa-circle_o"), QUOTE_LEFT("nf-fa-quote_left"),
   QUOTE_RIGHT("nf-fa-quote_right"), SPINNER("nf-fa-spinner"), CIRCLE("nf-fa-circle"), MAIL_REPLY("nf-fa-mail_reply"), REPLY("nf-fa-reply"),
   GITHUB_ALT("nf-fa-github_alt"), FOLDER_O("nf-fa-folder_o"), FOLDER_OPEN_O("nf-fa-folder_open_o"), SMILE_O("nf-fa-smile_o"),
   FROWN_O("nf-fa-frown_o"), MEH_O("nf-fa-meh_o"), GAMEPAD("nf-fa-gamepad"), KEYBOARD_O("nf-fa-keyboard_o"), FLAG_O("nf-fa-flag_o"),
   FLAG_CHECKERED("nf-fa-flag_checkered"), TERMINAL("nf-fa-terminal"), CODE("nf-fa-code"), MAIL_REPLY_ALL("nf-fa-mail_reply_all"),
   REPLY_ALL("nf-fa-reply_all"), STAR_HALF_EMPTY("nf-fa-star_half_empty"), STAR_HALF_FULL("nf-fa-star_half_full"),
   STAR_HALF_O("nf-fa-star_half_o"), LOCATION_ARROW("nf-fa-location_arrow"), CROP("nf-fa-crop"), CODE_FORK("nf-fa-code_fork"), UNLINK("nf-fa-unlink"),
   CHAIN_BROKEN("nf-fa-chain_broken"), QUESTION("nf-fa-question"), INFO("nf-fa-info"), EXCLAMATION("nf-fa-exclamation"),
   SUPERSCRIPT("nf-fa-superscript"), SUBSCRIPT("nf-fa-subscript"), ERASER("nf-fa-eraser"), PUZZLE_PIECE("nf-fa-puzzle_piece"),
   MICROPHONE("nf-fa-microphone"), MICROPHONE_SLASH("nf-fa-microphone_slash"), SHIELD("nf-fa-shield"), CALENDAR_O("nf-fa-calendar_o"),
   FIRE_EXTINGUISHER("nf-fa-fire_extinguisher"), ROCKET("nf-fa-rocket"), MAXCDN("nf-fa-maxcdn"),
   CHEVRON_CIRCLE_LEFT("nf-fa-chevron_circle_left"), CHEVRON_CIRCLE_RIGHT("nf-fa-chevron_circle_right"),
   CHEVRON_CIRCLE_UP("nf-fa-chevron_circle_up"), CHEVRON_CIRCLE_DOWN("nf-fa-chevron_circle_down"), HTML5("nf-fa-html5"), CSS3("nf-fa-css3"),
   ANCHOR("nf-fa-anchor"), UNLOCK_ALT("nf-fa-unlock_alt"), BULLSEYE("nf-fa-bullseye"), ELLIPSIS_H("nf-fa-ellipsis_h"), ELLIPSIS_V("nf-fa-ellipsis_v"),
   RSS_SQUARE("nf-fa-rss_square"), PLAY_CIRCLE("nf-fa-play_circle"), TICKET("nf-fa-ticket"), MINUS_SQUARE("nf-fa-minus_square"),
   MINUS_SQUARE_O("nf-fa-minus_square_o"), LEVEL_UP("nf-fa-level_up"), LEVEL_DOWN("nf-fa-level_down"), CHECK_SQUARE("nf-fa-check_square"),
   PENCIL_SQUARE("nf-fa-pencil_square"), EXTERNAL_LINK_SQUARE("nf-fa-external_link_square"), SHARE_SQUARE("nf-fa-share_square"),
   COMPASS("nf-fa-compass"), TOGGLE_DOWN("nf-fa-toggle_down"), CARET_SQUARE_O_DOWN("nf-fa-caret_square_o_down"), TOGGLE_UP("nf-fa-toggle_up"),
   CARET_SQUARE_O_UP("nf-fa-caret_square_o_up"), TOGGLE_RIGHT("nf-fa-toggle_right"), CARET_SQUARE_O_RIGHT("nf-fa-caret_square_o_right"),
   EURO("nf-fa-euro"), EUR("nf-fa-eur"), GBP("nf-fa-gbp"), DOLLAR("nf-fa-dollar"), USD("nf-fa-usd"), RUPEE("nf-fa-rupee"), INR("nf-fa-inr"), CNY("nf-fa-cny"),
   RMB("nf-fa-rmb"), YEN("nf-fa-yen"), JPY("nf-fa-jpy"), RUBLE("nf-fa-ruble"), ROUBLE("nf-fa-rouble"), RUB("nf-fa-rub"), WON("nf-fa-won"), KRW("nf-fa-krw"),
   BITCOIN("nf-fa-bitcoin"), BTC("nf-fa-btc"), FILE("nf-fa-file"), FILE_TEXT("nf-fa-file_text"), SORT_ALPHA_ASC("nf-fa-sort_alpha_asc"),
   SORT_ALPHA_DESC("nf-fa-sort_alpha_desc"), SORT_AMOUNT_ASC("nf-fa-sort_amount_asc"), SORT_AMOUNT_DESC("nf-fa-sort_amount_desc"),
   SORT_NUMERIC_ASC("nf-fa-sort_numeric_asc"), SORT_NUMERIC_DESC("nf-fa-sort_numeric_desc"), THUMBS_UP("nf-fa-thumbs_up"),
   THUMBS_DOWN("nf-fa-thumbs_down"), YOUTUBE_SQUARE("nf-fa-youtube_square"), YOUTUBE("nf-fa-youtube"), XING("nf-fa-xing"),
   XING_SQUARE("nf-fa-xing_square"), YOUTUBE_PLAY("nf-fa-youtube_play"), DROPBOX("nf-fa-dropbox"), STACK_OVERFLOW("nf-fa-stack_overflow"),
   INSTAGRAM("nf-fa-instagram"), FLICKR("nf-fa-flickr"), ADN("nf-fa-adn"), BITBUCKET("nf-fa-bitbucket"), BITBUCKET_SQUARE("nf-fa-bitbucket_square"),
   TUMBLR("nf-fa-tumblr"), TUMBLR_SQUARE("nf-fa-tumblr_square"), LONG_ARROW_DOWN("nf-fa-long_arrow_down"), LONG_ARROW_UP("nf-fa-long_arrow_up"),
   LONG_ARROW_LEFT("nf-fa-long_arrow_left"), LONG_ARROW_RIGHT("nf-fa-long_arrow_right"), APPLE("nf-fa-apple"), WINDOWS("nf-fa-windows"),
   ANDROID("nf-fa-android"), LINUX("nf-fa-linux"), DRIBBBLE("nf-fa-dribbble"), SKYPE("nf-fa-skype"), FOURSQUARE("nf-fa-foursquare"), TRELLO("nf-fa-trello"),
   FEMALE("nf-fa-female"), MALE("nf-fa-male"), GITTIP("nf-fa-gittip"), GRATIPAY("nf-fa-gratipay"), SUN_O("nf-fa-sun_o"), MOON_O("nf-fa-moon_o"),
   ARCHIVE("nf-fa-archive"), BUG("nf-fa-bug"), VK("nf-fa-vk"), WEIBO("nf-fa-weibo"), RENREN("nf-fa-renren"), PAGELINES("nf-fa-pagelines"),
   STACK_EXCHANGE("nf-fa-stack_exchange"), ARROW_CIRCLE_O_RIGHT("nf-fa-arrow_circle_o_right"),
   ARROW_CIRCLE_O_LEFT("nf-fa-arrow_circle_o_left"), TOGGLE_LEFT("nf-fa-toggle_left"), CARET_SQUARE_O_LEFT("nf-fa-caret_square_o_left"),
   DOT_CIRCLE_O("nf-fa-dot_circle_o"), WHEELCHAIR("nf-fa-wheelchair"), VIMEO_SQUARE("nf-fa-vimeo_square"), TURKISH_LIRA("nf-fa-turkish_lira"),
   TRY("nf-fa-try"), PLUS_SQUARE_O("nf-fa-plus_square_o"), SPACE_SHUTTLE("nf-fa-space_shuttle"), SLACK("nf-fa-slack"),
   ENVELOPE_SQUARE("nf-fa-envelope_square"), WORDPRESS("nf-fa-wordpress"), OPENID("nf-fa-openid"), INSTITUTION("nf-fa-institution"),
   BANK("nf-fa-bank"), UNIVERSITY("nf-fa-university"), MORTAR_BOARD("nf-fa-mortar_board"), GRADUATION_CAP("nf-fa-graduation_cap"),
   YAHOO("nf-fa-yahoo"), GOOGLE("nf-fa-google"), REDDIT("nf-fa-reddit"), REDDIT_SQUARE("nf-fa-reddit_square"),
   STUMBLEUPON_CIRCLE("nf-fa-stumbleupon_circle"), STUMBLEUPON("nf-fa-stumbleupon"), DELICIOUS("nf-fa-delicious"), DIGG("nf-fa-digg"),
   PIED_PIPER("nf-fa-pied_piper"), PIED_PIPER_ALT("nf-fa-pied_piper_alt"), DRUPAL("nf-fa-drupal"), JOOMLA("nf-fa-joomla"), LANGUAGE("nf-fa-language"),
   FAX("nf-fa-fax"), BUILDING("nf-fa-building"), CHILD("nf-fa-child"), PAW("nf-fa-paw"), SPOON("nf-fa-spoon"), CUBE("nf-fa-cube"), CUBES("nf-fa-cubes"),
   BEHANCE("nf-fa-behance"), BEHANCE_SQUARE("nf-fa-behance_square"), STEAM("nf-fa-steam"), STEAM_SQUARE("nf-fa-steam_square"),
   RECYCLE("nf-fa-recycle"), AUTOMOBILE("nf-fa-automobile"), CAR("nf-fa-car"), CAB("nf-fa-cab"), TAXI("nf-fa-taxi"), TREE("nf-fa-tree"), SPOTIFY("nf-fa-spotify"),
   DEVIANTART("nf-fa-deviantart"), SOUNDCLOUD("nf-fa-soundcloud"), DATABASE("nf-fa-database"), FILE_PDF_O("nf-fa-file_pdf_o"),
   FILE_WORD_O("nf-fa-file_word_o"), FILE_EXCEL_O("nf-fa-file_excel_o"), FILE_POWERPOINT_O("nf-fa-file_powerpoint_o"),
   FILE_PHOTO_O("nf-fa-file_photo_o"), FILE_PICTURE_O("nf-fa-file_picture_o"), FILE_IMAGE_O("nf-fa-file_image_o"),
   FILE_ZIP_O("nf-fa-file_zip_o"), FILE_ARCHIVE_O("nf-fa-file_archive_o"), FILE_SOUND_O("nf-fa-file_sound_o"),
   FILE_AUDIO_O("nf-fa-file_audio_o"), FILE_MOVIE_O("nf-fa-file_movie_o"), FILE_VIDEO_O("nf-fa-file_video_o"), FILE_CODE_O("nf-fa-file_code_o"),
   VINE("nf-fa-vine"), CODEPEN("nf-fa-codepen"), JSFIDDLE("nf-fa-jsfiddle"), LIFE_BOUY("nf-fa-life_bouy"), LIFE_BUOY("nf-fa-life_buoy"),
   LIFE_SAVER("nf-fa-life_saver"), SUPPORT("nf-fa-support"), LIFE_RING("nf-fa-life_ring"), CIRCLE_O_NOTCH("nf-fa-circle_o_notch"), RA("nf-fa-ra"),
   REBEL("nf-fa-rebel"), GE("nf-fa-ge"), EMPIRE("nf-fa-empire"), GIT_SQUARE("nf-fa-git_square"), GIT("nf-fa-git"),
   Y_COMBINATOR_SQUARE("nf-fa-y_combinator_square"), YC_SQUARE("nf-fa-yc_square"), HACKER_NEWS("nf-fa-hacker_news"),
   TENCENT_WEIBO("nf-fa-tencent_weibo"), QQ("nf-fa-qq"), WECHAT("nf-fa-wechat"), WEIXIN("nf-fa-weixin"), SEND("nf-fa-send"),
   PAPER_PLANE("nf-fa-paper_plane"), SEND_O("nf-fa-send_o"), PAPER_PLANE_O("nf-fa-paper_plane_o"), HISTORY("nf-fa-history"),
   CIRCLE_THIN("nf-fa-circle_thin"), HEADER("nf-fa-header"), PARAGRAPH("nf-fa-paragraph"), SLIDERS("nf-fa-sliders"), SHARE_ALT("nf-fa-share_alt"),
   SHARE_ALT_SQUARE("nf-fa-share_alt_square"), BOMB("nf-fa-bomb"), SOCCER_BALL_O("nf-fa-soccer_ball_o"), FUTBOL_O("nf-fa-futbol_o"), TTY("nf-fa-tty"),
   BINOCULARS("nf-fa-binoculars"), PLUG("nf-fa-plug"), SLIDESHARE("nf-fa-slideshare"), TWITCH("nf-fa-twitch"), YELP("nf-fa-yelp"),
   NEWSPAPER_O("nf-fa-newspaper_o"), WIFI("nf-fa-wifi"), CALCULATOR("nf-fa-calculator"), PAYPAL("nf-fa-paypal"), GOOGLE_WALLET("nf-fa-google_wallet"),
   CC_VISA("nf-fa-cc_visa"), CC_MASTERCARD("nf-fa-cc_mastercard"), CC_DISCOVER("nf-fa-cc_discover"), CC_AMEX("nf-fa-cc_amex"),
   CC_PAYPAL("nf-fa-cc_paypal"), CC_STRIPE("nf-fa-cc_stripe"), BELL_SLASH("nf-fa-bell_slash"), BELL_SLASH_O("nf-fa-bell_slash_o"),
   TRASH("nf-fa-trash"), COPYRIGHT("nf-fa-copyright"), AT("nf-fa-at"), EYEDROPPER("nf-fa-eyedropper"), PAINT_BRUSH("nf-fa-paint_brush"),
   BIRTHDAY_CAKE("nf-fa-birthday_cake"), AREA_CHART("nf-fa-area_chart"), PIE_CHART("nf-fa-pie_chart"), LINE_CHART("nf-fa-line_chart"),
   LASTFM("nf-fa-lastfm"), LASTFM_SQUARE("nf-fa-lastfm_square"), TOGGLE_OFF("nf-fa-toggle_off"), TOGGLE_ON("nf-fa-toggle_on"),
   BICYCLE("nf-fa-bicycle"), BUS("nf-fa-bus"), IOXHOST("nf-fa-ioxhost"), ANGELLIST("nf-fa-angellist"), CC("nf-fa-cc"), SHEKEL("nf-fa-shekel"),
   SHEQEL("nf-fa-sheqel"), ILS("nf-fa-ils"), MEANPATH("nf-fa-meanpath"), BUYSELLADS("nf-fa-buysellads"), CONNECTDEVELOP("nf-fa-connectdevelop"),
   DASHCUBE("nf-fa-dashcube"), FORUMBEE("nf-fa-forumbee"), LEANPUB("nf-fa-leanpub"), SELLSY("nf-fa-sellsy"), SHIRTSINBULK("nf-fa-shirtsinbulk"),
   SIMPLYBUILT("nf-fa-simplybuilt"), SKYATLAS("nf-fa-skyatlas"), CART_PLUS("nf-fa-cart_plus"), CART_ARROW_DOWN("nf-fa-cart_arrow_down"),
   DIAMOND("nf-fa-diamond"), SHIP("nf-fa-ship"), USER_SECRET("nf-fa-user_secret"), MOTORCYCLE("nf-fa-motorcycle"), STREET_VIEW("nf-fa-street_view"),
   HEARTBEAT("nf-fa-heartbeat"), VENUS("nf-fa-venus"), MARS("nf-fa-mars"), MERCURY("nf-fa-mercury"), INTERSEX("nf-fa-intersex"),
   TRANSGENDER("nf-fa-transgender"), TRANSGENDER_ALT("nf-fa-transgender_alt"), VENUS_DOUBLE("nf-fa-venus_double"),
   MARS_DOUBLE("nf-fa-mars_double"), VENUS_MARS("nf-fa-venus_mars"), MARS_STROKE("nf-fa-mars_stroke"), MARS_STROKE_V("nf-fa-mars_stroke_v"),
   MARS_STROKE_H("nf-fa-mars_stroke_h"), NEUTER("nf-fa-neuter"), GENDERLESS("nf-fa-genderless"), FACEBOOK_OFFICIAL("nf-fa-facebook_official"),
   PINTEREST_P("nf-fa-pinterest_p"), WHATSAPP("nf-fa-whatsapp"), SERVER("nf-fa-server"), USER_PLUS("nf-fa-user_plus"), USER_TIMES("nf-fa-user_times"),
   HOTEL("nf-fa-hotel"), BED("nf-fa-bed"), VIACOIN("nf-fa-viacoin"), TRAIN("nf-fa-train"), SUBWAY("nf-fa-subway"), MEDIUM("nf-fa-medium"), YC("nf-fa-yc"),
   Y_COMBINATOR("nf-fa-y_combinator"), OPTIN_MONSTER("nf-fa-optin_monster"), OPENCART("nf-fa-opencart"), EXPEDITEDSSL("nf-fa-expeditedssl"),
   BATTERY_4("nf-fa-battery_4"), BATTERY_FULL("nf-fa-battery_full"), BATTERY_3("nf-fa-battery_3"),
   BATTERY_THREE_QUARTERS("nf-fa-battery_three_quarters"), BATTERY_2("nf-fa-battery_2"), BATTERY_HALF("nf-fa-battery_half"),
   BATTERY_1("nf-fa-battery_1"), BATTERY_QUARTER("nf-fa-battery_quarter"), BATTERY_0("nf-fa-battery_0"), BATTERY_EMPTY("nf-fa-battery_empty"),
   MOUSE_POINTER("nf-fa-mouse_pointer"), I_CURSOR("nf-fa-i_cursor"), OBJECT_GROUP("nf-fa-object_group"), OBJECT_UNGROUP("nf-fa-object_ungroup"),
   STICKY_NOTE("nf-fa-sticky_note"), STICKY_NOTE_O("nf-fa-sticky_note_o"), CC_JCB("nf-fa-cc_jcb"), CC_DINERS_CLUB("nf-fa-cc_diners_club"),
   CLONE("nf-fa-clone"), BALANCE_SCALE("nf-fa-balance_scale"), HOURGLASS_O("nf-fa-hourglass_o"), HOURGLASS_1("nf-fa-hourglass_1"),
   HOURGLASS_START("nf-fa-hourglass_start"), HOURGLASS_2("nf-fa-hourglass_2"), HOURGLASS_HALF("nf-fa-hourglass_half"),
   HOURGLASS_3("nf-fa-hourglass_3"), HOURGLASS_END("nf-fa-hourglass_end"), HOURGLASS("nf-fa-hourglass"), HAND_GRAB_O("nf-fa-hand_grab_o"),
   HAND_ROCK_O("nf-fa-hand_rock_o"), HAND_STOP_O("nf-fa-hand_stop_o"), HAND_PAPER_O("nf-fa-hand_paper_o"),
   HAND_SCISSORS_O("nf-fa-hand_scissors_o"), HAND_LIZARD_O("nf-fa-hand_lizard_o"), HAND_SPOCK_O("nf-fa-hand_spock_o"),
   HAND_POINTER_O("nf-fa-hand_pointer_o"), HAND_PEACE_O("nf-fa-hand_peace_o"), TRADEMARK("nf-fa-trademark"), REGISTERED("nf-fa-registered"),
   CREATIVE_COMMONS("nf-fa-creative_commons"), GG("nf-fa-gg"), GG_CIRCLE("nf-fa-gg_circle"), TRIPADVISOR("nf-fa-tripadvisor"),
   ODNOKLASSNIKI("nf-fa-odnoklassniki"), ODNOKLASSNIKI_SQUARE("nf-fa-odnoklassniki_square"), GET_POCKET("nf-fa-get_pocket"),
   WIKIPEDIA_W("nf-fa-wikipedia_w"), SAFARI("nf-fa-safari"), CHROME("nf-fa-chrome"), FIREFOX("nf-fa-firefox"), OPERA("nf-fa-opera"),
   INTERNET_EXPLORER("nf-fa-internet_explorer"), TV("nf-fa-tv"), TELEVISION("nf-fa-television"), CONTAO("nf-fa-contao"), AMAZON("nf-fa-amazon"),
   CALENDAR_PLUS_O("nf-fa-calendar_plus_o"), CALENDAR_MINUS_O("nf-fa-calendar_minus_o"), CALENDAR_TIMES_O("nf-fa-calendar_times_o"),
   CALENDAR_CHECK_O("nf-fa-calendar_check_o"), INDUSTRY("nf-fa-industry"), MAP_PIN("nf-fa-map_pin"), MAP_SIGNS("nf-fa-map_signs"),
   MAP_O("nf-fa-map_o"), MAP("nf-fa-map"), COMMENTING("nf-fa-commenting"), COMMENTING_O("nf-fa-commenting_o"), HOUZZ("nf-fa-houzz"), VIMEO("nf-fa-vimeo"),
   BLACK_TIE("nf-fa-black_tie"), FONTICONS("nf-fa-fonticons");

   static final Map<String, BaseIcon> iconMap = new HashMap<String, BaseIcon>();

   static {
      iconMap.put("glass", GLASS);
      iconMap.put("music", MUSIC);
      iconMap.put("search", SEARCH);
      iconMap.put("envelope-o", ENVELOPE_O);
      iconMap.put("heart", HEART);
      iconMap.put("star", STAR);
      iconMap.put("star-o", STAR_O);
      iconMap.put("user", USER);
      iconMap.put("film", FILM);
      iconMap.put("th-large", TH_LARGE);
      iconMap.put("th", TH);
      iconMap.put("th-list", TH_LIST);
      iconMap.put("check", CHECK);
      iconMap.put("remove", REMOVE);
      iconMap.put("close", CLOSE);
      iconMap.put("times", TIMES);
      iconMap.put("search-plus", SEARCH_PLUS);
      iconMap.put("search-minus", SEARCH_MINUS);
      iconMap.put("power-off", POWER_OFF);
      iconMap.put("signal", SIGNAL);
      iconMap.put("gear", GEAR);
      iconMap.put("cog", COG);
      iconMap.put("trash-o", TRASH_O);
      iconMap.put("home", HOME);
      iconMap.put("file-o", FILE_O);
      iconMap.put("clock-o", CLOCK_O);
      iconMap.put("road", ROAD);
      iconMap.put("download", DOWNLOAD);
      iconMap.put("arrow-circle-o-down", ARROW_CIRCLE_O_DOWN);
      iconMap.put("arrow-circle-o-up", ARROW_CIRCLE_O_UP);
      iconMap.put("inbox", INBOX);
      iconMap.put("play-circle-o", PLAY_CIRCLE_O);
      iconMap.put("repeat", REPEAT);
      iconMap.put("refresh", REFRESH);
      iconMap.put("list-alt", LIST_ALT);
      iconMap.put("lock", LOCK);
      iconMap.put("flag", FLAG);
      iconMap.put("headphones", HEADPHONES);
      iconMap.put("volume-off", VOLUME_OFF);
      iconMap.put("volume-down", VOLUME_DOWN);
      iconMap.put("volume-up", VOLUME_UP);
      iconMap.put("qrcode", QRCODE);
      iconMap.put("barcode", BARCODE);
      iconMap.put("tag", TAG);
      iconMap.put("tags", TAGS);
      iconMap.put("book", BOOK);
      iconMap.put("bookmark", BOOKMARK);
      iconMap.put("print", PRINT);
      iconMap.put("camera", CAMERA);
      iconMap.put("font", FONT);
      iconMap.put("bold", BOLD);
      iconMap.put("italic", ITALIC);
      iconMap.put("text-height", TEXT_HEIGHT);
      iconMap.put("text-width", TEXT_WIDTH);
      iconMap.put("align-left", ALIGN_LEFT);
      iconMap.put("align-center", ALIGN_CENTER);
      iconMap.put("align-right", ALIGN_RIGHT);
      iconMap.put("align-justify", ALIGN_JUSTIFY);
      iconMap.put("list", LIST);
      iconMap.put("dedent", DEDENT);
      iconMap.put("outdent", OUTDENT);
      iconMap.put("indent", INDENT);
      iconMap.put("video-camera", VIDEO_CAMERA);
      iconMap.put("photo", PHOTO);
      iconMap.put("image", IMAGE);
      iconMap.put("picture-o", PICTURE_O);
      iconMap.put("pencil", PENCIL);
      iconMap.put("map-marker", MAP_MARKER);
      iconMap.put("adjust", ADJUST);
      iconMap.put("tint", TINT);
      iconMap.put("edit", EDIT);
      iconMap.put("pencil-square-o", PENCIL_SQUARE_O);
      iconMap.put("share-square-o", SHARE_SQUARE_O);
      iconMap.put("check-square-o", CHECK_SQUARE_O);
      iconMap.put("arrows", ARROWS);
      iconMap.put("step-backward", STEP_BACKWARD);
      iconMap.put("fast-backward", FAST_BACKWARD);
      iconMap.put("backward", BACKWARD);
      iconMap.put("play", PLAY);
      iconMap.put("pause", PAUSE);
      iconMap.put("stop", STOP);
      iconMap.put("forward", FORWARD);
      iconMap.put("fast-forward", FAST_FORWARD);
      iconMap.put("step-forward", STEP_FORWARD);
      iconMap.put("eject", EJECT);
      iconMap.put("chevron-left", CHEVRON_LEFT);
      iconMap.put("chevron-right", CHEVRON_RIGHT);
      iconMap.put("plus-circle", PLUS_CIRCLE);
      iconMap.put("minus-circle", MINUS_CIRCLE);
      iconMap.put("times-circle", TIMES_CIRCLE);
      iconMap.put("check-circle", CHECK_CIRCLE);
      iconMap.put("question-circle", QUESTION_CIRCLE);
      iconMap.put("info-circle", INFO_CIRCLE);
      iconMap.put("crosshairs", CROSSHAIRS);
      iconMap.put("times-circle-o", TIMES_CIRCLE_O);
      iconMap.put("check-circle-o", CHECK_CIRCLE_O);
      iconMap.put("ban", BAN);
      iconMap.put("arrow-left", ARROW_LEFT);
      iconMap.put("arrow-right", ARROW_RIGHT);
      iconMap.put("arrow-up", ARROW_UP);
      iconMap.put("arrow-down", ARROW_DOWN);
      iconMap.put("mail-forward", MAIL_FORWARD);
      iconMap.put("share", SHARE);
      iconMap.put("expand", EXPAND);
      iconMap.put("compress", COMPRESS);
      iconMap.put("plus", PLUS);
      iconMap.put("minus", MINUS);
      iconMap.put("asterisk", ASTERISK);
      iconMap.put("exclamation-circle", EXCLAMATION_CIRCLE);
      iconMap.put("gift", GIFT);
      iconMap.put("leaf", LEAF);
      iconMap.put("fire", FIRE);
      iconMap.put("eye", EYE);
      iconMap.put("eye-slash", EYE_SLASH);
      iconMap.put("warning", WARNING);
      iconMap.put("exclamation-triangle", EXCLAMATION_TRIANGLE);
      iconMap.put("plane", PLANE);
      iconMap.put("calendar", CALENDAR);
      iconMap.put("random", RANDOM);
      iconMap.put("comment", COMMENT);
      iconMap.put("magnet", MAGNET);
      iconMap.put("chevron-up", CHEVRON_UP);
      iconMap.put("chevron-down", CHEVRON_DOWN);
      iconMap.put("retweet", RETWEET);
      iconMap.put("shopping-cart", SHOPPING_CART);
      iconMap.put("folder", FOLDER);
      iconMap.put("folder-open", FOLDER_OPEN);
      iconMap.put("arrows-v", ARROWS_V);
      iconMap.put("arrows-h", ARROWS_H);
      iconMap.put("bar-chart-o", BAR_CHART_O);
      iconMap.put("bar-chart", BAR_CHART);
      iconMap.put("twitter-square", TWITTER_SQUARE);
      iconMap.put("facebook-square", FACEBOOK_SQUARE);
      iconMap.put("camera-retro", CAMERA_RETRO);
      iconMap.put("key", KEY);
      iconMap.put("gears", GEARS);
      iconMap.put("cogs", COGS);
      iconMap.put("comments", COMMENTS);
      iconMap.put("thumbs-o-up", THUMBS_O_UP);
      iconMap.put("thumbs-o-down", THUMBS_O_DOWN);
      iconMap.put("star-half", STAR_HALF);
      iconMap.put("heart-o", HEART_O);
      iconMap.put("sign-out", SIGN_OUT);
      iconMap.put("linkedin-square", LINKEDIN_SQUARE);
      iconMap.put("thumb-tack", THUMB_TACK);
      iconMap.put("external-link", EXTERNAL_LINK);
      iconMap.put("sign-in", SIGN_IN);
      iconMap.put("trophy", TROPHY);
      iconMap.put("github-square", GITHUB_SQUARE);
      iconMap.put("upload", UPLOAD);
      iconMap.put("lemon-o", LEMON_O);
      iconMap.put("phone", PHONE);
      iconMap.put("square-o", SQUARE_O);
      iconMap.put("bookmark-o", BOOKMARK_O);
      iconMap.put("phone-square", PHONE_SQUARE);
      iconMap.put("twitter", TWITTER);
      iconMap.put("facebook-f", FACEBOOK_F);
      iconMap.put("facebook", FACEBOOK);
      iconMap.put("github", GITHUB);
      iconMap.put("unlock", UNLOCK);
      iconMap.put("credit-card", CREDIT_CARD);
      iconMap.put("feed", FEED);
      iconMap.put("rss", RSS);
      iconMap.put("hdd-o", HDD_O);
      iconMap.put("bullhorn", BULLHORN);
      iconMap.put("bell", BELL);
      iconMap.put("certificate", CERTIFICATE);
      iconMap.put("hand-o-right", HAND_O_RIGHT);
      iconMap.put("hand-o-left", HAND_O_LEFT);
      iconMap.put("hand-o-up", HAND_O_UP);
      iconMap.put("hand-o-down", HAND_O_DOWN);
      iconMap.put("arrow-circle-left", ARROW_CIRCLE_LEFT);
      iconMap.put("arrow-circle-right", ARROW_CIRCLE_RIGHT);
      iconMap.put("arrow-circle-up", ARROW_CIRCLE_UP);
      iconMap.put("arrow-circle-down", ARROW_CIRCLE_DOWN);
      iconMap.put("globe", GLOBE);
      iconMap.put("wrench", WRENCH);
      iconMap.put("tasks", TASKS);
      iconMap.put("filter", FILTER);
      iconMap.put("briefcase", BRIEFCASE);
      iconMap.put("arrows-alt", ARROWS_ALT);
      iconMap.put("group", GROUP);
      iconMap.put("users", USERS);
      iconMap.put("chain", CHAIN);
      iconMap.put("link", LINK);
      iconMap.put("cloud", CLOUD);
      iconMap.put("flask", FLASK);
      iconMap.put("cut", CUT);
      iconMap.put("scissors", SCISSORS);
      iconMap.put("copy", COPY);
      iconMap.put("files-o", FILES_O);
      iconMap.put("paperclip", PAPERCLIP);
      iconMap.put("save", SAVE);
      iconMap.put("floppy-o", FLOPPY_O);
      iconMap.put("square", SQUARE);
      iconMap.put("navicon", NAVICON);
      iconMap.put("reorder", REORDER);
      iconMap.put("bars", BARS);
      iconMap.put("list-ul", LIST_UL);
      iconMap.put("list-ol", LIST_OL);
      iconMap.put("strikethrough", STRIKETHROUGH);
      iconMap.put("underline", UNDERLINE);
      iconMap.put("table", TABLE);
      iconMap.put("magic", MAGIC);
      iconMap.put("truck", TRUCK);
      iconMap.put("pinterest", PINTEREST);
      iconMap.put("pinterest-square", PINTEREST_SQUARE);
      iconMap.put("google-plus-square", GOOGLE_PLUS_SQUARE);
      iconMap.put("google-plus", GOOGLE_PLUS);
      iconMap.put("money", MONEY);
      iconMap.put("caret-down", CARET_DOWN);
      iconMap.put("caret-up", CARET_UP);
      iconMap.put("caret-left", CARET_LEFT);
      iconMap.put("caret-right", CARET_RIGHT);
      iconMap.put("columns", COLUMNS);
      iconMap.put("unsorted", UNSORTED);
      iconMap.put("sort", SORT);
      iconMap.put("sort-down", SORT_DOWN);
      iconMap.put("sort-desc", SORT_DESC);
      iconMap.put("sort-up", SORT_UP);
      iconMap.put("sort-asc", SORT_ASC);
      iconMap.put("envelope", ENVELOPE);
      iconMap.put("linkedin", LINKEDIN);
      iconMap.put("rotate-left", ROTATE_LEFT);
      iconMap.put("undo", UNDO);
      iconMap.put("legal", LEGAL);
      iconMap.put("gavel", GAVEL);
      iconMap.put("dashboard", DASHBOARD);
      iconMap.put("tachometer", TACHOMETER);
      iconMap.put("comment-o", COMMENT_O);
      iconMap.put("comments-o", COMMENTS_O);
      iconMap.put("flash", FLASH);
      iconMap.put("bolt", BOLT);
      iconMap.put("sitemap", SITEMAP);
      iconMap.put("umbrella", UMBRELLA);
      iconMap.put("paste", PASTE);
      iconMap.put("clipboard", CLIPBOARD);
      iconMap.put("lightbulb-o", LIGHTBULB_O);
      iconMap.put("exchange", EXCHANGE);
      iconMap.put("cloud-download", CLOUD_DOWNLOAD);
      iconMap.put("cloud-upload", CLOUD_UPLOAD);
      iconMap.put("user-md", USER_MD);
      iconMap.put("stethoscope", STETHOSCOPE);
      iconMap.put("suitcase", SUITCASE);
      iconMap.put("bell-o", BELL_O);
      iconMap.put("coffee", COFFEE);
      iconMap.put("cutlery", CUTLERY);
      iconMap.put("file-text-o", FILE_TEXT_O);
      iconMap.put("building-o", BUILDING_O);
      iconMap.put("hospital-o", HOSPITAL_O);
      iconMap.put("ambulance", AMBULANCE);
      iconMap.put("medkit", MEDKIT);
      iconMap.put("fighter-jet", FIGHTER_JET);
      iconMap.put("beer", BEER);
      iconMap.put("h-square", H_SQUARE);
      iconMap.put("plus-square", PLUS_SQUARE);
      iconMap.put("angle-double-left", ANGLE_DOUBLE_LEFT);
      iconMap.put("angle-double-right", ANGLE_DOUBLE_RIGHT);
      iconMap.put("angle-double-up", ANGLE_DOUBLE_UP);
      iconMap.put("angle-double-down", ANGLE_DOUBLE_DOWN);
      iconMap.put("angle-left", ANGLE_LEFT);
      iconMap.put("angle-right", ANGLE_RIGHT);
      iconMap.put("angle-up", ANGLE_UP);
      iconMap.put("angle-down", ANGLE_DOWN);
      iconMap.put("desktop", DESKTOP);
      iconMap.put("laptop", LAPTOP);
      iconMap.put("tablet", TABLET);
      iconMap.put("mobile-phone", MOBILE_PHONE);
      iconMap.put("mobile", MOBILE);
      iconMap.put("circle-o", CIRCLE_O);
      iconMap.put("quote-left", QUOTE_LEFT);
      iconMap.put("quote-right", QUOTE_RIGHT);
      iconMap.put("spinner", SPINNER);
      iconMap.put("circle", CIRCLE);
      iconMap.put("mail-reply", MAIL_REPLY);
      iconMap.put("reply", REPLY);
      iconMap.put("github-alt", GITHUB_ALT);
      iconMap.put("folder-o", FOLDER_O);
      iconMap.put("folder-open-o", FOLDER_OPEN_O);
      iconMap.put("smile-o", SMILE_O);
      iconMap.put("frown-o", FROWN_O);
      iconMap.put("meh-o", MEH_O);
      iconMap.put("gamepad", GAMEPAD);
      iconMap.put("keyboard-o", KEYBOARD_O);
      iconMap.put("flag-o", FLAG_O);
      iconMap.put("flag-checkered", FLAG_CHECKERED);
      iconMap.put("terminal", TERMINAL);
      iconMap.put("code", CODE);
      iconMap.put("mail-reply-all", MAIL_REPLY_ALL);
      iconMap.put("reply-all", REPLY_ALL);
      iconMap.put("star-half-empty", STAR_HALF_EMPTY);
      iconMap.put("star-half-full", STAR_HALF_FULL);
      iconMap.put("star-half-o", STAR_HALF_O);
      iconMap.put("location-arrow", LOCATION_ARROW);
      iconMap.put("crop", CROP);
      iconMap.put("code-fork", CODE_FORK);
      iconMap.put("unlink", UNLINK);
      iconMap.put("chain-broken", CHAIN_BROKEN);
      iconMap.put("question", QUESTION);
      iconMap.put("info", INFO);
      iconMap.put("exclamation", EXCLAMATION);
      iconMap.put("superscript", SUPERSCRIPT);
      iconMap.put("subscript", SUBSCRIPT);
      iconMap.put("eraser", ERASER);
      iconMap.put("puzzle-piece", PUZZLE_PIECE);
      iconMap.put("microphone", MICROPHONE);
      iconMap.put("microphone-slash", MICROPHONE_SLASH);
      iconMap.put("shield", SHIELD);
      iconMap.put("calendar-o", CALENDAR_O);
      iconMap.put("fire-extinguisher", FIRE_EXTINGUISHER);
      iconMap.put("rocket", ROCKET);
      iconMap.put("maxcdn", MAXCDN);
      iconMap.put("chevron-circle-left", CHEVRON_CIRCLE_LEFT);
      iconMap.put("chevron-circle-right", CHEVRON_CIRCLE_RIGHT);
      iconMap.put("chevron-circle-up", CHEVRON_CIRCLE_UP);
      iconMap.put("chevron-circle-down", CHEVRON_CIRCLE_DOWN);
      iconMap.put("html5", HTML5);
      iconMap.put("css3", CSS3);
      iconMap.put("anchor", ANCHOR);
      iconMap.put("unlock-alt", UNLOCK_ALT);
      iconMap.put("bullseye", BULLSEYE);
      iconMap.put("ellipsis-h", ELLIPSIS_H);
      iconMap.put("ellipsis-v", ELLIPSIS_V);
      iconMap.put("rss-square", RSS_SQUARE);
      iconMap.put("play-circle", PLAY_CIRCLE);
      iconMap.put("ticket", TICKET);
      iconMap.put("minus-square", MINUS_SQUARE);
      iconMap.put("minus-square-o", MINUS_SQUARE_O);
      iconMap.put("level-up", LEVEL_UP);
      iconMap.put("level-down", LEVEL_DOWN);
      iconMap.put("check-square", CHECK_SQUARE);
      iconMap.put("pencil-square", PENCIL_SQUARE);
      iconMap.put("external-link-square", EXTERNAL_LINK_SQUARE);
      iconMap.put("share-square", SHARE_SQUARE);
      iconMap.put("compass", COMPASS);
      iconMap.put("toggle-down", TOGGLE_DOWN);
      iconMap.put("caret-square-o-down", CARET_SQUARE_O_DOWN);
      iconMap.put("toggle-up", TOGGLE_UP);
      iconMap.put("caret-square-o-up", CARET_SQUARE_O_UP);
      iconMap.put("toggle-right", TOGGLE_RIGHT);
      iconMap.put("caret-square-o-right", CARET_SQUARE_O_RIGHT);
      iconMap.put("euro", EURO);
      iconMap.put("eur", EUR);
      iconMap.put("gbp", GBP);
      iconMap.put("dollar", DOLLAR);
      iconMap.put("usd", USD);
      iconMap.put("rupee", RUPEE);
      iconMap.put("inr", INR);
      iconMap.put("cny", CNY);
      iconMap.put("rmb", RMB);
      iconMap.put("yen", YEN);
      iconMap.put("jpy", JPY);
      iconMap.put("ruble", RUBLE);
      iconMap.put("rouble", ROUBLE);
      iconMap.put("rub", RUB);
      iconMap.put("won", WON);
      iconMap.put("krw", KRW);
      iconMap.put("bitcoin", BITCOIN);
      iconMap.put("btc", BTC);
      iconMap.put("file", FILE);
      iconMap.put("file-text", FILE_TEXT);
      iconMap.put("sort-alpha-asc", SORT_ALPHA_ASC);
      iconMap.put("sort-alpha-desc", SORT_ALPHA_DESC);
      iconMap.put("sort-amount-asc", SORT_AMOUNT_ASC);
      iconMap.put("sort-amount-desc", SORT_AMOUNT_DESC);
      iconMap.put("sort-numeric-asc", SORT_NUMERIC_ASC);
      iconMap.put("sort-numeric-desc", SORT_NUMERIC_DESC);
      iconMap.put("thumbs-up", THUMBS_UP);
      iconMap.put("thumbs-down", THUMBS_DOWN);
      iconMap.put("youtube-square", YOUTUBE_SQUARE);
      iconMap.put("youtube", YOUTUBE);
      iconMap.put("xing", XING);
      iconMap.put("xing-square", XING_SQUARE);
      iconMap.put("youtube-play", YOUTUBE_PLAY);
      iconMap.put("dropbox", DROPBOX);
      iconMap.put("stack-overflow", STACK_OVERFLOW);
      iconMap.put("instagram", INSTAGRAM);
      iconMap.put("flickr", FLICKR);
      iconMap.put("adn", ADN);
      iconMap.put("bitbucket", BITBUCKET);
      iconMap.put("bitbucket-square", BITBUCKET_SQUARE);
      iconMap.put("tumblr", TUMBLR);
      iconMap.put("tumblr-square", TUMBLR_SQUARE);
      iconMap.put("long-arrow-down", LONG_ARROW_DOWN);
      iconMap.put("long-arrow-up", LONG_ARROW_UP);
      iconMap.put("long-arrow-left", LONG_ARROW_LEFT);
      iconMap.put("long-arrow-right", LONG_ARROW_RIGHT);
      iconMap.put("apple", APPLE);
      iconMap.put("windows", WINDOWS);
      iconMap.put("android", ANDROID);
      iconMap.put("linux", LINUX);
      iconMap.put("dribbble", DRIBBBLE);
      iconMap.put("skype", SKYPE);
      iconMap.put("foursquare", FOURSQUARE);
      iconMap.put("trello", TRELLO);
      iconMap.put("female", FEMALE);
      iconMap.put("male", MALE);
      iconMap.put("gittip", GITTIP);
      iconMap.put("gratipay", GRATIPAY);
      iconMap.put("sun-o", SUN_O);
      iconMap.put("moon-o", MOON_O);
      iconMap.put("archive", ARCHIVE);
      iconMap.put("bug", BUG);
      iconMap.put("vk", VK);
      iconMap.put("weibo", WEIBO);
      iconMap.put("renren", RENREN);
      iconMap.put("pagelines", PAGELINES);
      iconMap.put("stack-exchange", STACK_EXCHANGE);
      iconMap.put("arrow-circle-o-right", ARROW_CIRCLE_O_RIGHT);
      iconMap.put("arrow-circle-o-left", ARROW_CIRCLE_O_LEFT);
      iconMap.put("toggle-left", TOGGLE_LEFT);
      iconMap.put("caret-square-o-left", CARET_SQUARE_O_LEFT);
      iconMap.put("dot-circle-o", DOT_CIRCLE_O);
      iconMap.put("wheelchair", WHEELCHAIR);
      iconMap.put("vimeo-square", VIMEO_SQUARE);
      iconMap.put("turkish-lira", TURKISH_LIRA);
      iconMap.put("try", TRY);
      iconMap.put("plus-square-o", PLUS_SQUARE_O);
      iconMap.put("space-shuttle", SPACE_SHUTTLE);
      iconMap.put("slack", SLACK);
      iconMap.put("envelope-square", ENVELOPE_SQUARE);
      iconMap.put("wordpress", WORDPRESS);
      iconMap.put("openid", OPENID);
      iconMap.put("institution", INSTITUTION);
      iconMap.put("bank", BANK);
      iconMap.put("university", UNIVERSITY);
      iconMap.put("mortar-board", MORTAR_BOARD);
      iconMap.put("graduation-cap", GRADUATION_CAP);
      iconMap.put("yahoo", YAHOO);
      iconMap.put("google", GOOGLE);
      iconMap.put("reddit", REDDIT);
      iconMap.put("reddit-square", REDDIT_SQUARE);
      iconMap.put("stumbleupon-circle", STUMBLEUPON_CIRCLE);
      iconMap.put("stumbleupon", STUMBLEUPON);
      iconMap.put("delicious", DELICIOUS);
      iconMap.put("digg", DIGG);
      iconMap.put("pied-piper", PIED_PIPER);
      iconMap.put("pied-piper-alt", PIED_PIPER_ALT);
      iconMap.put("drupal", DRUPAL);
      iconMap.put("joomla", JOOMLA);
      iconMap.put("language", LANGUAGE);
      iconMap.put("fax", FAX);
      iconMap.put("building", BUILDING);
      iconMap.put("child", CHILD);
      iconMap.put("paw", PAW);
      iconMap.put("spoon", SPOON);
      iconMap.put("cube", CUBE);
      iconMap.put("cubes", CUBES);
      iconMap.put("behance", BEHANCE);
      iconMap.put("behance-square", BEHANCE_SQUARE);
      iconMap.put("steam", STEAM);
      iconMap.put("steam-square", STEAM_SQUARE);
      iconMap.put("recycle", RECYCLE);
      iconMap.put("automobile", AUTOMOBILE);
      iconMap.put("car", CAR);
      iconMap.put("cab", CAB);
      iconMap.put("taxi", TAXI);
      iconMap.put("tree", TREE);
      iconMap.put("spotify", SPOTIFY);
      iconMap.put("deviantart", DEVIANTART);
      iconMap.put("soundcloud", SOUNDCLOUD);
      iconMap.put("database", DATABASE);
      iconMap.put("file-pdf-o", FILE_PDF_O);
      iconMap.put("file-word-o", FILE_WORD_O);
      iconMap.put("file-excel-o", FILE_EXCEL_O);
      iconMap.put("file-powerpoint-o", FILE_POWERPOINT_O);
      iconMap.put("file-photo-o", FILE_PHOTO_O);
      iconMap.put("file-picture-o", FILE_PICTURE_O);
      iconMap.put("file-image-o", FILE_IMAGE_O);
      iconMap.put("file-zip-o", FILE_ZIP_O);
      iconMap.put("file-archive-o", FILE_ARCHIVE_O);
      iconMap.put("file-sound-o", FILE_SOUND_O);
      iconMap.put("file-audio-o", FILE_AUDIO_O);
      iconMap.put("file-movie-o", FILE_MOVIE_O);
      iconMap.put("file-video-o", FILE_VIDEO_O);
      iconMap.put("file-code-o", FILE_CODE_O);
      iconMap.put("vine", VINE);
      iconMap.put("codepen", CODEPEN);
      iconMap.put("jsfiddle", JSFIDDLE);
      iconMap.put("life-bouy", LIFE_BOUY);
      iconMap.put("life-buoy", LIFE_BUOY);
      iconMap.put("life-saver", LIFE_SAVER);
      iconMap.put("support", SUPPORT);
      iconMap.put("life-ring", LIFE_RING);
      iconMap.put("circle-o-notch", CIRCLE_O_NOTCH);
      iconMap.put("ra", RA);
      iconMap.put("rebel", REBEL);
      iconMap.put("ge", GE);
      iconMap.put("empire", EMPIRE);
      iconMap.put("git-square", GIT_SQUARE);
      iconMap.put("git", GIT);
      iconMap.put("y-combinator-square", Y_COMBINATOR_SQUARE);
      iconMap.put("yc-square", YC_SQUARE);
      iconMap.put("hacker-news", HACKER_NEWS);
      iconMap.put("tencent-weibo", TENCENT_WEIBO);
      iconMap.put("qq", QQ);
      iconMap.put("wechat", WECHAT);
      iconMap.put("weixin", WEIXIN);
      iconMap.put("send", SEND);
      iconMap.put("paper-plane", PAPER_PLANE);
      iconMap.put("send-o", SEND_O);
      iconMap.put("paper-plane-o", PAPER_PLANE_O);
      iconMap.put("history", HISTORY);
      iconMap.put("circle-thin", CIRCLE_THIN);
      iconMap.put("header", HEADER);
      iconMap.put("paragraph", PARAGRAPH);
      iconMap.put("sliders", SLIDERS);
      iconMap.put("share-alt", SHARE_ALT);
      iconMap.put("share-alt-square", SHARE_ALT_SQUARE);
      iconMap.put("bomb", BOMB);
      iconMap.put("soccer-ball-o", SOCCER_BALL_O);
      iconMap.put("futbol-o", FUTBOL_O);
      iconMap.put("tty", TTY);
      iconMap.put("binoculars", BINOCULARS);
      iconMap.put("plug", PLUG);
      iconMap.put("slideshare", SLIDESHARE);
      iconMap.put("twitch", TWITCH);
      iconMap.put("yelp", YELP);
      iconMap.put("newspaper-o", NEWSPAPER_O);
      iconMap.put("wifi", WIFI);
      iconMap.put("calculator", CALCULATOR);
      iconMap.put("paypal", PAYPAL);
      iconMap.put("google-wallet", GOOGLE_WALLET);
      iconMap.put("cc-visa", CC_VISA);
      iconMap.put("cc-mastercard", CC_MASTERCARD);
      iconMap.put("cc-discover", CC_DISCOVER);
      iconMap.put("cc-amex", CC_AMEX);
      iconMap.put("cc-paypal", CC_PAYPAL);
      iconMap.put("cc-stripe", CC_STRIPE);
      iconMap.put("bell-slash", BELL_SLASH);
      iconMap.put("bell-slash-o", BELL_SLASH_O);
      iconMap.put("trash", TRASH);
      iconMap.put("copyright", COPYRIGHT);
      iconMap.put("at", AT);
      iconMap.put("eyedropper", EYEDROPPER);
      iconMap.put("paint-brush", PAINT_BRUSH);
      iconMap.put("birthday-cake", BIRTHDAY_CAKE);
      iconMap.put("area-chart", AREA_CHART);
      iconMap.put("pie-chart", PIE_CHART);
      iconMap.put("line-chart", LINE_CHART);
      iconMap.put("lastfm", LASTFM);
      iconMap.put("lastfm-square", LASTFM_SQUARE);
      iconMap.put("toggle-off", TOGGLE_OFF);
      iconMap.put("toggle-on", TOGGLE_ON);
      iconMap.put("bicycle", BICYCLE);
      iconMap.put("bus", BUS);
      iconMap.put("ioxhost", IOXHOST);
      iconMap.put("angellist", ANGELLIST);
      iconMap.put("cc", CC);
      iconMap.put("shekel", SHEKEL);
      iconMap.put("sheqel", SHEQEL);
      iconMap.put("ils", ILS);
      iconMap.put("meanpath", MEANPATH);
      iconMap.put("buysellads", BUYSELLADS);
      iconMap.put("connectdevelop", CONNECTDEVELOP);
      iconMap.put("dashcube", DASHCUBE);
      iconMap.put("forumbee", FORUMBEE);
      iconMap.put("leanpub", LEANPUB);
      iconMap.put("sellsy", SELLSY);
      iconMap.put("shirtsinbulk", SHIRTSINBULK);
      iconMap.put("simplybuilt", SIMPLYBUILT);
      iconMap.put("skyatlas", SKYATLAS);
      iconMap.put("cart-plus", CART_PLUS);
      iconMap.put("cart-arrow-down", CART_ARROW_DOWN);
      iconMap.put("diamond", DIAMOND);
      iconMap.put("ship", SHIP);
      iconMap.put("user-secret", USER_SECRET);
      iconMap.put("motorcycle", MOTORCYCLE);
      iconMap.put("street-view", STREET_VIEW);
      iconMap.put("heartbeat", HEARTBEAT);
      iconMap.put("venus", VENUS);
      iconMap.put("mars", MARS);
      iconMap.put("mercury", MERCURY);
      iconMap.put("intersex", INTERSEX);
      iconMap.put("transgender", TRANSGENDER);
      iconMap.put("transgender-alt", TRANSGENDER_ALT);
      iconMap.put("venus-double", VENUS_DOUBLE);
      iconMap.put("mars-double", MARS_DOUBLE);
      iconMap.put("venus-mars", VENUS_MARS);
      iconMap.put("mars-stroke", MARS_STROKE);
      iconMap.put("mars-stroke-v", MARS_STROKE_V);
      iconMap.put("mars-stroke-h", MARS_STROKE_H);
      iconMap.put("neuter", NEUTER);
      iconMap.put("genderless", GENDERLESS);
      iconMap.put("facebook-official", FACEBOOK_OFFICIAL);
      iconMap.put("pinterest-p", PINTEREST_P);
      iconMap.put("whatsapp", WHATSAPP);
      iconMap.put("server", SERVER);
      iconMap.put("user-plus", USER_PLUS);
      iconMap.put("user-times", USER_TIMES);
      iconMap.put("hotel", HOTEL);
      iconMap.put("bed", BED);
      iconMap.put("viacoin", VIACOIN);
      iconMap.put("train", TRAIN);
      iconMap.put("subway", SUBWAY);
      iconMap.put("medium", MEDIUM);
      iconMap.put("yc", YC);
      iconMap.put("y-combinator", Y_COMBINATOR);
      iconMap.put("optin-monster", OPTIN_MONSTER);
      iconMap.put("opencart", OPENCART);
      iconMap.put("expeditedssl", EXPEDITEDSSL);
      iconMap.put("battery-4", BATTERY_4);
      iconMap.put("battery-full", BATTERY_FULL);
      iconMap.put("battery-3", BATTERY_3);
      iconMap.put("battery-three-quarters", BATTERY_THREE_QUARTERS);
      iconMap.put("battery-2", BATTERY_2);
      iconMap.put("battery-half", BATTERY_HALF);
      iconMap.put("battery-1", BATTERY_1);
      iconMap.put("battery-quarter", BATTERY_QUARTER);
      iconMap.put("battery-0", BATTERY_0);
      iconMap.put("battery-empty", BATTERY_EMPTY);
      iconMap.put("mouse-pointer", MOUSE_POINTER);
      iconMap.put("i-cursor", I_CURSOR);
      iconMap.put("object-group", OBJECT_GROUP);
      iconMap.put("object-ungroup", OBJECT_UNGROUP);
      iconMap.put("sticky-note", STICKY_NOTE);
      iconMap.put("sticky-note-o", STICKY_NOTE_O);
      iconMap.put("cc-jcb", CC_JCB);
      iconMap.put("cc-diners-club", CC_DINERS_CLUB);
      iconMap.put("clone", CLONE);
      iconMap.put("balance-scale", BALANCE_SCALE);
      iconMap.put("hourglass-o", HOURGLASS_O);
      iconMap.put("hourglass-1", HOURGLASS_1);
      iconMap.put("hourglass-start", HOURGLASS_START);
      iconMap.put("hourglass-2", HOURGLASS_2);
      iconMap.put("hourglass-half", HOURGLASS_HALF);
      iconMap.put("hourglass-3", HOURGLASS_3);
      iconMap.put("hourglass-end", HOURGLASS_END);
      iconMap.put("hourglass", HOURGLASS);
      iconMap.put("hand-grab-o", HAND_GRAB_O);
      iconMap.put("hand-rock-o", HAND_ROCK_O);
      iconMap.put("hand-stop-o", HAND_STOP_O);
      iconMap.put("hand-paper-o", HAND_PAPER_O);
      iconMap.put("hand-scissors-o", HAND_SCISSORS_O);
      iconMap.put("hand-lizard-o", HAND_LIZARD_O);
      iconMap.put("hand-spock-o", HAND_SPOCK_O);
      iconMap.put("hand-pointer-o", HAND_POINTER_O);
      iconMap.put("hand-peace-o", HAND_PEACE_O);
      iconMap.put("trademark", TRADEMARK);
      iconMap.put("registered", REGISTERED);
      iconMap.put("creative-commons", CREATIVE_COMMONS);
      iconMap.put("gg", GG);
      iconMap.put("gg-circle", GG_CIRCLE);
      iconMap.put("tripadvisor", TRIPADVISOR);
      iconMap.put("odnoklassniki", ODNOKLASSNIKI);
      iconMap.put("odnoklassniki-square", ODNOKLASSNIKI_SQUARE);
      iconMap.put("get-pocket", GET_POCKET);
      iconMap.put("wikipedia-w", WIKIPEDIA_W);
      iconMap.put("safari", SAFARI);
      iconMap.put("chrome", CHROME);
      iconMap.put("firefox", FIREFOX);
      iconMap.put("opera", OPERA);
      iconMap.put("internet-explorer", INTERNET_EXPLORER);
      iconMap.put("tv", TV);
      iconMap.put("television", TELEVISION);
      iconMap.put("contao", CONTAO);
      iconMap.put("amazon", AMAZON);
      iconMap.put("calendar-plus-o", CALENDAR_PLUS_O);
      iconMap.put("calendar-minus-o", CALENDAR_MINUS_O);
      iconMap.put("calendar-times-o", CALENDAR_TIMES_O);
      iconMap.put("calendar-check-o", CALENDAR_CHECK_O);
      iconMap.put("industry", INDUSTRY);
      iconMap.put("map-pin", MAP_PIN);
      iconMap.put("map-signs", MAP_SIGNS);
      iconMap.put("map-o", MAP_O);
      iconMap.put("map", MAP);
      iconMap.put("commenting", COMMENTING);
      iconMap.put("commenting-o", COMMENTING_O);
      iconMap.put("houzz", HOUZZ);
      iconMap.put("vimeo", VIMEO);
      iconMap.put("black-tie", BLACK_TIE);
      iconMap.put("fonticons", FONTICONS);
      iconMap.put("google_drive", GOOGLE_DRIVE);
      iconMap.put("box", BOX);
   }

   private String cssName;
   private String rotate;

   BaseIcon(String cssName) {
      this.cssName = cssName;
   }

   BaseIcon(String cssName, BaseIconRotate rotate) {
      this.cssName = cssName;
      this.rotate = rotate.toCssClass();
   }

   public static BaseIcon fromFileExtension(String extension) {
      if ("xls".equals(extension) || "xlsx".equals(extension))
         return FILE_EXCEL;
      if ("doc".equals(extension) || "docx".equals(extension))
         return FILE_WORD;
      if ("pdf".equals(extension))
         return FILE_PDF;
      if ("csv".equals(extension))
         return FILE_TEXT_O;
      if ("html".equals(extension))
         return GLOBE;
      if ("ppt".equals(extension) || "pptx".equals(extension))
         return FILE_PPT;
      if ("jpg".equals(extension) || "jpeg".equals(extension) || "png".equals(extension) || "tiff".equals(extension))
         return FILE_IMAGE;
      if ("zip".equals(extension) || "tgz".equals(extension))
         return FILE_ZIP;
      return FILE_O;
   }

   public static BaseIcon from(String icon) {
      if (null == icon)
         return null;

      if ("script".equals(icon))
         return SCRIPT;
      if (iconMap.containsKey(icon))
         return iconMap.get(icon);

      return fromFileExtension(icon);
   }

   public CssIconImageResource toImageResource(int size) {
      return new CssIconImageResource(this, size);
   }

   public CssIconImageResource toImageResource() {
      return new CssIconImageResource(this);
   }

   public SafeHtml toSafeHtml() {
      return new SafeHtmlBuilder().appendHtmlConstant(getHtml(0)).toSafeHtml();
   }

   public Element toElement() {
      return toElement(Document.get());
   }

   public Element toElement(Document document) {
      final Element element = document.createElement("i");
      element.addClassName("nf");
      element.addClassName(cssName);
      if (null != rotate)
         element.addClassName(rotate);
      return element;
   }

   public SafeHtml toSafeHtml(int size) {
      return new SafeHtmlBuilder().appendHtmlConstant(getHtml(size)).toSafeHtml();
   }

   private String getHtml(int size) {
      String sizeClass = null;
      if (size == 1)
         sizeClass = "nf-fa-lg";
      else if (size > 1)
         sizeClass = "nf-fa-" + size + "x";

      StringBuilder htmlBuilder = new StringBuilder("<i class=\"nf ").append(cssName);

      if (null != sizeClass)
         htmlBuilder.append(" " + sizeClass);
      if (null != rotate)
         htmlBuilder.append(" " + rotate);

      htmlBuilder.append("\"></i>");

      return htmlBuilder.toString();
   }

   public String getCssName() {
      return "nf " + cssName;
   }

   public String toString() {
      return cssName;
   }

   public BaseIconComponent toComponent() {
      return toComponent(0);
   }

   public BaseIconComponent toComponent(int size) {
      return new BaseIconComponent(toSafeHtml(size));
   }

   public void addClassName(String name) {
      cssName += " " + name;
   }

}
