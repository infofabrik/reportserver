package net.datenwerke.rs.theme.client.icon;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public enum BaseIcon {

	NO_ICON(""),
	
	ACCEPT("check-circle-o"),
	ADD("plus-circle"),
	AGGREGATION("compress"),
	EXECUTE("play-circle-o"),
	EXPAND_ALL("plus-square-o"),
	COLLAPSE("minus-square-o"),
	COLLAPSE_ALL("minus-square-o"),
	DADGET("tachometer"),
	DELETE("minus-circle"),
	DISCONNECT("chain-broken"),
	ERROR("exclamation-triangle fa-col-red"),
	EXPORT("upload"),
	IMPORT("download"),
	FILTER_ADD("filter fa-add-action"),
	FORMAT("italic"),
	GROUP_ADD("users fa-add-action"),
	GROUP_EDIT("users fa-edit-action"),
	GROUP_PROPERTIES("users"),
	INDEX("bookmark"),
	LOADING("spinner fa-spin"),
	LOGICAL_AND("angle-up"),
	LOGICAL_OR("angle-down"),
	ITEMS_DETAIL("th-list"),
	ITEMS_LARGE("th-large"),
	NETWORK("sitemap"),
	NULL_EXCLUDE("minus-circle"),
	NULL_INCLUDE("plus-circle"),
	SCRIPT("file-code-o"),
	SCRIPT_LINK("file-code-o fa-link-action"),
	SUBTOTALS("bars"),
	TILE_VERTICAL("columns"),
	TILE_HORIZONTAL("columns", BaseIconRotate.ROTATE_90),
	TEMPLATE("newspaper-o"),
	USER_PROFILE("user"),
	USER_VARIABLE("gamepad"),
	WEBPAGE("globe"),
	
	FILE_AUDIO("file-audio-o"),
	FILE_EXCEL("file-excel-o"),
	FILE_PDF("file-pdf-o"),
	FILE_CODE("file-code-o"),
	FILE_IMAGE("file-image-o"),
	FILE_WORD("file-word-o"),
	FILE_VIDEO("file-video-o"),
	FILE_PPT("file-powerpoint-o"),
	FILE_ZIP("file-archive-o"),
	
	FILTER_EDIT	("filter fa-edit-action"),
	
	REPORT("file"),
	REPORT_ADD("file fa-add-action"),
	REPORT_DELETE("file fa-remove-action"),
	REPORT_LINK("file fa-link-action"),
	REPORT_DISK("file"),
	REPORT_USER("file"),
	REPORT_MAGNIFY("file"),
	REPORT_PICTURE("pie-chart"),
	
	REPORT_BIRT("file-image-o"),
	REPORT_BIRT_LINK("file-image-o fa-link-action"),
	
	REPORT_SAIKU("cube"),
	REPORT_SAIKU_LINK("cube fa-link-action"),
	
	REPORT_DL("table"),
	REPORT_DL_LINK("table fa-link-action"),
	
	REPORT_JXLS("file-excel-o"),
	REPORT_JXLS_LINK("file-excel-o fa-link-action"),
	
	REPORT_GE("table fa-edit-action"),
	REPORT_GE_LINK("table fa-link-action"),
	
	REPORT_CRYSTAL("diamond"),
	REPORT_CRYSTAL_LINK("diamond fa-link-action"),	
	REPORT_JASPER("newspaper-o"),
	REPORT_JASPER_LINK("newspaper-o fa-link-action"),
	
	
	ARROWS_IN("compress"),
	ARROWS_OUT("arrows-alt"),
	ARROWS_IN_OUT("exchange"),
	
	CHART_AREA("bar-area"),
	CHART_BAR("bar-chart"),
	CHART_LINE("line-chart"),
	CHART_PIE("pie-chart"),
	
	COG_ADD("cog fa-add-action"),
	COG_DELETE("cog fa-remove-action"),
	COG_EDIT("cog fa-edit-action"),
	
	CLOCK_EDIT("clock-o fa-edit-action"),

	EDIT_NOT("ban"),
	
	FOLDER_ADD("folder-o fa-add-action"),
	FOLDER_USER("folder-o"),
	
	KEY_ADD("key fa-add-action"),
	
	LIGHTBULB("lightbulb"),
	LOCK_ADD("lock fa-add-action"),
	
	NEWSPAPER_LINK("newspaper-o fa-link-action"),
	
	ROTATE_RIGHT("rotate-right"),
	
	TABLE_ADD("table fa-add-action"),
	TABLE_EDIT("table fa-edit-action"),
	
	USER_ADD("user fa-add-action"),
	USER_BLOCKED("user fa-col-light"),
	USER_COMMENT("user"),
	USER_EDIT("user fa-edit-action"),
	USER_MALE("male"),
	USER_FEMALE("female"),
	
	
	/* font-awesome 4.5 */
	GLASS	("glass"),
	MUSIC	("music"),
	SEARCH	("search"),
	ENVELOPE_O	("envelope-o"),
	HEART	("heart"),
	STAR	("star"),
	STAR_O	("star-o"),
	USER	("user"),
	FILM	("film"),
	TH_LARGE	("th-large"),
	TH	("th"),
	TH_LIST	("th-list"),
	CHECK	("check"),
	REMOVE	("remove"),
	CLOSE	("close"),
	TIMES	("times"),
	SEARCH_PLUS	("search-plus"),
	SEARCH_MINUS	("search-minus"),
	POWER_OFF	("power-off"),
	SIGNAL	("signal"),
	GEAR	("gear"),
	COG	("cog"),
	TRASH_O	("trash-o"),
	HOME	("home"),
	FILE_O	("file-o"),
	CLOCK_O	("clock-o"),
	ROAD	("road"),
	DOWNLOAD	("download"),
	ARROW_CIRCLE_O_DOWN	("arrow-circle-o-down"),
	ARROW_CIRCLE_O_UP	("arrow-circle-o-up"),
	INBOX	("inbox"),
	PLAY_CIRCLE_O	("play-circle-o"),
	REPEAT	("repeat"),
	REFRESH	("refresh"),
	LIST_ALT	("list-alt"),
	LOCK	("lock"),
	FLAG	("flag"),
	HEADPHONES	("headphones"),
	VOLUME_OFF	("volume-off"),
	VOLUME_DOWN	("volume-down"),
	VOLUME_UP	("volume-up"),
	QRCODE	("qrcode"),
	BARCODE	("barcode"),
	TAG	("tag"),
	TAGS	("tags"),
	BOOK	("book"),
	BOOKMARK	("bookmark"),
	PRINT	("print"),
	CAMERA	("camera"),
	FONT	("font"),
	BOLD	("bold"),
	ITALIC	("italic"),
	TEXT_HEIGHT	("text-height"),
	TEXT_WIDTH	("text-width"),
	ALIGN_LEFT	("align-left"),
	ALIGN_CENTER	("align-center"),
	ALIGN_RIGHT	("align-right"),
	ALIGN_JUSTIFY	("align-justify"),
	LIST	("list"),
	DEDENT	("dedent"),
	OUTDENT	("outdent"),
	INDENT	("indent"),
	VIDEO_CAMERA	("video-camera"),
	PHOTO	("photo"),
	IMAGE	("image"),
	PICTURE_O	("picture-o"),
	PENCIL	("pencil"),
	MAP_MARKER	("map-marker"),
	ADJUST	("adjust"),
	TINT	("tint"),
	EDIT	("edit"),
	PENCIL_SQUARE_O	("pencil-square-o"),
	SHARE_SQUARE_O	("share-square-o"),
	CHECK_SQUARE_O	("check-square-o"),
	ARROWS	("arrows"),
	STEP_BACKWARD	("step-backward"),
	FAST_BACKWARD	("fast-backward"),
	BACKWARD	("backward"),
	PLAY	("play"),
	PAUSE	("pause"),
	STOP	("stop"),
	FORWARD	("forward"),
	FAST_FORWARD	("fast-forward"),
	STEP_FORWARD	("step-forward"),
	EJECT	("eject"),
	CHEVRON_LEFT	("chevron-left"),
	CHEVRON_RIGHT	("chevron-right"),
	PLUS_CIRCLE	("plus-circle"),
	MINUS_CIRCLE	("minus-circle"),
	TIMES_CIRCLE	("times-circle"),
	CHECK_CIRCLE	("check-circle"),
	QUESTION_CIRCLE	("question-circle"),
	INFO_CIRCLE	("info-circle"),
	CROSSHAIRS	("crosshairs"),
	TIMES_CIRCLE_O	("times-circle-o"),
	CHECK_CIRCLE_O	("check-circle-o"),
	BAN	("ban"),
	ARROW_LEFT	("arrow-left"),
	ARROW_RIGHT	("arrow-right"),
	ARROW_UP	("arrow-up"),
	ARROW_DOWN	("arrow-down"),
	MAIL_FORWARD	("mail-forward"),
	SHARE	("share"),
	EXPAND	("expand"),
	COMPRESS	("compress"),
	PLUS	("plus"),
	MINUS	("minus"),
	ASTERISK	("asterisk"),
	EXCLAMATION_CIRCLE	("exclamation-circle"),
	GIFT	("gift"),
	LEAF	("leaf"),
	FIRE	("fire"),
	EYE	("eye"),
	EYE_SLASH	("eye-slash"),
	WARNING	("warning"),
	EXCLAMATION_TRIANGLE	("exclamation-triangle"),
	PLANE	("plane"),
	CALENDAR	("calendar"),
	RANDOM	("random"),
	COMMENT	("comment"),
	MAGNET	("magnet"),
	CHEVRON_UP	("chevron-up"),
	CHEVRON_DOWN	("chevron-down"),
	RETWEET	("retweet"),
	SHOPPING_CART	("shopping-cart"),
	FOLDER	("folder"),
	FOLDER_OPEN	("folder-open"),
	ARROWS_V	("arrows-v"),
	ARROWS_H	("arrows-h"),
	BAR_CHART_O	("bar-chart-o"),
	BAR_CHART	("bar-chart"),
	TWITTER_SQUARE	("twitter-square"),
	FACEBOOK_SQUARE	("facebook-square"),
	CAMERA_RETRO	("camera-retro"),
	KEY	("key"),
	GEARS	("gears"),
	COGS	("cogs"),
	COMMENTS	("comments"),
	THUMBS_O_UP	("thumbs-o-up"),
	THUMBS_O_DOWN	("thumbs-o-down"),
	STAR_HALF	("star-half"),
	HEART_O	("heart-o"),
	SIGN_OUT	("sign-out"),
	LINKEDIN_SQUARE	("linkedin-square"),
	THUMB_TACK	("thumb-tack"),
	EXTERNAL_LINK	("external-link"),
	SIGN_IN	("sign-in"),
	TROPHY	("trophy"),
	GITHUB_SQUARE	("github-square"),
	UPLOAD	("upload"),
	LEMON_O	("lemon-o"),
	PHONE	("phone"),
	SQUARE_O	("square-o"),
	BOOKMARK_O	("bookmark-o"),
	PHONE_SQUARE	("phone-square"),
	TWITTER	("twitter"),
	FACEBOOK_F	("facebook-f"),
	FACEBOOK	("facebook"),
	GITHUB	("github"),
	UNLOCK	("unlock"),
	CREDIT_CARD	("credit-card"),
	FEED	("feed"),
	RSS	("rss"),
	HDD_O	("hdd-o"),
	BULLHORN	("bullhorn"),
	BELL	("bell"),
	CERTIFICATE	("certificate"),
	HAND_O_RIGHT	("hand-o-right"),
	HAND_O_LEFT	("hand-o-left"),
	HAND_O_UP	("hand-o-up"),
	HAND_O_DOWN	("hand-o-down"),
	ARROW_CIRCLE_LEFT	("arrow-circle-left"),
	ARROW_CIRCLE_RIGHT	("arrow-circle-right"),
	ARROW_CIRCLE_UP	("arrow-circle-up"),
	ARROW_CIRCLE_DOWN	("arrow-circle-down"),
	GLOBE	("globe"),
	WRENCH	("wrench"),
	TASKS	("tasks"),
	FILTER	("filter"),
	BRIEFCASE	("briefcase"),
	ARROWS_ALT	("arrows-alt"),
	GROUP	("group"),
	USERS	("users"),
	CHAIN	("chain"),
	LINK	("link"),
	CLOUD	("cloud"),
	FLASK	("flask"),
	CUT	("cut"),
	SCISSORS	("scissors"),
	COPY	("copy"),
	FILES_O	("files-o"),
	PAPERCLIP	("paperclip"),
	SAVE	("save"),
	FLOPPY_O	("floppy-o"),
	SQUARE	("square"),
	NAVICON	("navicon"),
	REORDER	("reorder"),
	BARS	("bars"),
	LIST_UL	("list-ul"),
	LIST_OL	("list-ol"),
	STRIKETHROUGH	("strikethrough"),
	UNDERLINE	("underline"),
	TABLE	("table"),
	MAGIC	("magic"),
	TRUCK	("truck"),
	PINTEREST	("pinterest"),
	PINTEREST_SQUARE	("pinterest-square"),
	GOOGLE_PLUS_SQUARE	("google-plus-square"),
	GOOGLE_PLUS	("google-plus"),
	MONEY	("money"),
	CARET_DOWN	("caret-down"),
	CARET_UP	("caret-up"),
	CARET_LEFT	("caret-left"),
	CARET_RIGHT	("caret-right"),
	COLUMNS	("columns"),
	UNSORTED	("unsorted"),
	SORT	("sort"),
	SORT_DOWN	("sort-down"),
	SORT_DESC	("sort-desc"),
	SORT_UP	("sort-up"),
	SORT_ASC	("sort-asc"),
	ENVELOPE	("envelope"),
	LINKEDIN	("linkedin"),
	ROTATE_LEFT	("rotate-left"),
	UNDO	("undo"),
	LEGAL	("legal"),
	GAVEL	("gavel"),
	DASHBOARD	("dashboard"),
	TACHOMETER	("tachometer"),
	COMMENT_O	("comment-o"),
	COMMENTS_O	("comments-o"),
	FLASH	("flash"),
	BOLT	("bolt"),
	SITEMAP	("sitemap"),
	UMBRELLA	("umbrella"),
	PASTE	("paste"),
	CLIPBOARD	("clipboard"),
	LIGHTBULB_O	("lightbulb-o"),
	EXCHANGE	("exchange"),
	CLOUD_DOWNLOAD	("cloud-download"),
	CLOUD_UPLOAD	("cloud-upload"),
	USER_MD	("user-md"),
	STETHOSCOPE	("stethoscope"),
	SUITCASE	("suitcase"),
	BELL_O	("bell-o"),
	COFFEE	("coffee"),
	CUTLERY	("cutlery"),
	FILE_TEXT_O	("file-text-o"),
	BUILDING_O	("building-o"),
	HOSPITAL_O	("hospital-o"),
	AMBULANCE	("ambulance"),
	MEDKIT	("medkit"),
	FIGHTER_JET	("fighter-jet"),
	BEER	("beer"),
	H_SQUARE	("h-square"),
	PLUS_SQUARE	("plus-square"),
	ANGLE_DOUBLE_LEFT	("angle-double-left"),
	ANGLE_DOUBLE_RIGHT	("angle-double-right"),
	ANGLE_DOUBLE_UP	("angle-double-up"),
	ANGLE_DOUBLE_DOWN	("angle-double-down"),
	ANGLE_LEFT	("angle-left"),
	ANGLE_RIGHT	("angle-right"),
	ANGLE_UP	("angle-up"),
	ANGLE_DOWN	("angle-down"),
	DESKTOP	("desktop"),
	LAPTOP	("laptop"),
	TABLET	("tablet"),
	MOBILE_PHONE	("mobile-phone"),
	MOBILE	("mobile"),
	CIRCLE_O	("circle-o"),
	QUOTE_LEFT	("quote-left"),
	QUOTE_RIGHT	("quote-right"),
	SPINNER	("spinner"),
	CIRCLE	("circle"),
	MAIL_REPLY	("mail-reply"),
	REPLY	("reply"),
	GITHUB_ALT	("github-alt"),
	FOLDER_O	("folder-o"),
	FOLDER_OPEN_O	("folder-open-o"),
	SMILE_O	("smile-o"),
	FROWN_O	("frown-o"),
	MEH_O	("meh-o"),
	GAMEPAD	("gamepad"),
	KEYBOARD_O	("keyboard-o"),
	FLAG_O	("flag-o"),
	FLAG_CHECKERED	("flag-checkered"),
	TERMINAL	("terminal"),
	CODE	("code"),
	MAIL_REPLY_ALL	("mail-reply-all"),
	REPLY_ALL	("reply-all"),
	STAR_HALF_EMPTY	("star-half-empty"),
	STAR_HALF_FULL	("star-half-full"),
	STAR_HALF_O	("star-half-o"),
	LOCATION_ARROW	("location-arrow"),
	CROP	("crop"),
	CODE_FORK	("code-fork"),
	UNLINK	("unlink"),
	CHAIN_BROKEN	("chain-broken"),
	QUESTION	("question"),
	INFO	("info"),
	EXCLAMATION	("exclamation"),
	SUPERSCRIPT	("superscript"),
	SUBSCRIPT	("subscript"),
	ERASER	("eraser"),
	PUZZLE_PIECE	("puzzle-piece"),
	MICROPHONE	("microphone"),
	MICROPHONE_SLASH	("microphone-slash"),
	SHIELD	("shield"),
	CALENDAR_O	("calendar-o"),
	FIRE_EXTINGUISHER	("fire-extinguisher"),
	ROCKET	("rocket"),
	MAXCDN	("maxcdn"),
	CHEVRON_CIRCLE_LEFT	("chevron-circle-left"),
	CHEVRON_CIRCLE_RIGHT	("chevron-circle-right"),
	CHEVRON_CIRCLE_UP	("chevron-circle-up"),
	CHEVRON_CIRCLE_DOWN	("chevron-circle-down"),
	HTML5	("html5"),
	CSS3	("css3"),
	ANCHOR	("anchor"),
	UNLOCK_ALT	("unlock-alt"),
	BULLSEYE	("bullseye"),
	ELLIPSIS_H	("ellipsis-h"),
	ELLIPSIS_V	("ellipsis-v"),
	RSS_SQUARE	("rss-square"),
	PLAY_CIRCLE	("play-circle"),
	TICKET	("ticket"),
	MINUS_SQUARE	("minus-square"),
	MINUS_SQUARE_O	("minus-square-o"),
	LEVEL_UP	("level-up"),
	LEVEL_DOWN	("level-down"),
	CHECK_SQUARE	("check-square"),
	PENCIL_SQUARE	("pencil-square"),
	EXTERNAL_LINK_SQUARE	("external-link-square"),
	SHARE_SQUARE	("share-square"),
	COMPASS	("compass"),
	TOGGLE_DOWN	("toggle-down"),
	CARET_SQUARE_O_DOWN	("caret-square-o-down"),
	TOGGLE_UP	("toggle-up"),
	CARET_SQUARE_O_UP	("caret-square-o-up"),
	TOGGLE_RIGHT	("toggle-right"),
	CARET_SQUARE_O_RIGHT	("caret-square-o-right"),
	EURO	("euro"),
	EUR	("eur"),
	GBP	("gbp"),
	DOLLAR	("dollar"),
	USD	("usd"),
	RUPEE	("rupee"),
	INR	("inr"),
	CNY	("cny"),
	RMB	("rmb"),
	YEN	("yen"),
	JPY	("jpy"),
	RUBLE	("ruble"),
	ROUBLE	("rouble"),
	RUB	("rub"),
	WON	("won"),
	KRW	("krw"),
	BITCOIN	("bitcoin"),
	BTC	("btc"),
	FILE	("file"),
	FILE_TEXT	("file-text"),
	SORT_ALPHA_ASC	("sort-alpha-asc"),
	SORT_ALPHA_DESC	("sort-alpha-desc"),
	SORT_AMOUNT_ASC	("sort-amount-asc"),
	SORT_AMOUNT_DESC	("sort-amount-desc"),
	SORT_NUMERIC_ASC	("sort-numeric-asc"),
	SORT_NUMERIC_DESC	("sort-numeric-desc"),
	THUMBS_UP	("thumbs-up"),
	THUMBS_DOWN	("thumbs-down"),
	YOUTUBE_SQUARE	("youtube-square"),
	YOUTUBE	("youtube"),
	XING	("xing"),
	XING_SQUARE	("xing-square"),
	YOUTUBE_PLAY	("youtube-play"),
	DROPBOX	("dropbox"),
	STACK_OVERFLOW	("stack-overflow"),
	INSTAGRAM	("instagram"),
	FLICKR	("flickr"),
	ADN	("adn"),
	BITBUCKET	("bitbucket"),
	BITBUCKET_SQUARE	("bitbucket-square"),
	TUMBLR	("tumblr"),
	TUMBLR_SQUARE	("tumblr-square"),
	LONG_ARROW_DOWN	("long-arrow-down"),
	LONG_ARROW_UP	("long-arrow-up"),
	LONG_ARROW_LEFT	("long-arrow-left"),
	LONG_ARROW_RIGHT	("long-arrow-right"),
	APPLE	("apple"),
	WINDOWS	("windows"),
	ANDROID	("android"),
	LINUX	("linux"),
	DRIBBBLE	("dribbble"),
	SKYPE	("skype"),
	FOURSQUARE	("foursquare"),
	TRELLO	("trello"),
	FEMALE	("female"),
	MALE	("male"),
	GITTIP	("gittip"),
	GRATIPAY	("gratipay"),
	SUN_O	("sun-o"),
	MOON_O	("moon-o"),
	ARCHIVE	("archive"),
	BUG	("bug"),
	VK	("vk"),
	WEIBO	("weibo"),
	RENREN	("renren"),
	PAGELINES	("pagelines"),
	STACK_EXCHANGE	("stack-exchange"),
	ARROW_CIRCLE_O_RIGHT	("arrow-circle-o-right"),
	ARROW_CIRCLE_O_LEFT	("arrow-circle-o-left"),
	TOGGLE_LEFT	("toggle-left"),
	CARET_SQUARE_O_LEFT	("caret-square-o-left"),
	DOT_CIRCLE_O	("dot-circle-o"),
	WHEELCHAIR	("wheelchair"),
	VIMEO_SQUARE	("vimeo-square"),
	TURKISH_LIRA	("turkish-lira"),
	TRY	("try"),
	PLUS_SQUARE_O	("plus-square-o"),
	SPACE_SHUTTLE	("space-shuttle"),
	SLACK	("slack"),
	ENVELOPE_SQUARE	("envelope-square"),
	WORDPRESS	("wordpress"),
	OPENID	("openid"),
	INSTITUTION	("institution"),
	BANK	("bank"),
	UNIVERSITY	("university"),
	MORTAR_BOARD	("mortar-board"),
	GRADUATION_CAP	("graduation-cap"),
	YAHOO	("yahoo"),
	GOOGLE	("google"),
	REDDIT	("reddit"),
	REDDIT_SQUARE	("reddit-square"),
	STUMBLEUPON_CIRCLE	("stumbleupon-circle"),
	STUMBLEUPON	("stumbleupon"),
	DELICIOUS	("delicious"),
	DIGG	("digg"),
	PIED_PIPER	("pied-piper"),
	PIED_PIPER_ALT	("pied-piper-alt"),
	DRUPAL	("drupal"),
	JOOMLA	("joomla"),
	LANGUAGE	("language"),
	FAX	("fax"),
	BUILDING	("building"),
	CHILD	("child"),
	PAW	("paw"),
	SPOON	("spoon"),
	CUBE	("cube"),
	CUBES	("cubes"),
	BEHANCE	("behance"),
	BEHANCE_SQUARE	("behance-square"),
	STEAM	("steam"),
	STEAM_SQUARE	("steam-square"),
	RECYCLE	("recycle"),
	AUTOMOBILE	("automobile"),
	CAR	("car"),
	CAB	("cab"),
	TAXI	("taxi"),
	TREE	("tree"),
	SPOTIFY	("spotify"),
	DEVIANTART	("deviantart"),
	SOUNDCLOUD	("soundcloud"),
	DATABASE	("database"),
	FILE_PDF_O	("file-pdf-o"),
	FILE_WORD_O	("file-word-o"),
	FILE_EXCEL_O	("file-excel-o"),
	FILE_POWERPOINT_O	("file-powerpoint-o"),
	FILE_PHOTO_O	("file-photo-o"),
	FILE_PICTURE_O	("file-picture-o"),
	FILE_IMAGE_O	("file-image-o"),
	FILE_ZIP_O	("file-zip-o"),
	FILE_ARCHIVE_O	("file-archive-o"),
	FILE_SOUND_O	("file-sound-o"),
	FILE_AUDIO_O	("file-audio-o"),
	FILE_MOVIE_O	("file-movie-o"),
	FILE_VIDEO_O	("file-video-o"),
	FILE_CODE_O	("file-code-o"),
	VINE	("vine"),
	CODEPEN	("codepen"),
	JSFIDDLE	("jsfiddle"),
	LIFE_BOUY	("life-bouy"),
	LIFE_BUOY	("life-buoy"),
	LIFE_SAVER	("life-saver"),
	SUPPORT	("support"),
	LIFE_RING	("life-ring"),
	CIRCLE_O_NOTCH	("circle-o-notch"),
	RA	("ra"),
	REBEL	("rebel"),
	GE	("ge"),
	EMPIRE	("empire"),
	GIT_SQUARE	("git-square"),
	GIT	("git"),
	Y_COMBINATOR_SQUARE	("y-combinator-square"),
	YC_SQUARE	("yc-square"),
	HACKER_NEWS	("hacker-news"),
	TENCENT_WEIBO	("tencent-weibo"),
	QQ	("qq"),
	WECHAT	("wechat"),
	WEIXIN	("weixin"),
	SEND	("send"),
	PAPER_PLANE	("paper-plane"),
	SEND_O	("send-o"),
	PAPER_PLANE_O	("paper-plane-o"),
	HISTORY	("history"),
	CIRCLE_THIN	("circle-thin"),
	HEADER	("header"),
	PARAGRAPH	("paragraph"),
	SLIDERS	("sliders"),
	SHARE_ALT	("share-alt"),
	SHARE_ALT_SQUARE	("share-alt-square"),
	BOMB	("bomb"),
	SOCCER_BALL_O	("soccer-ball-o"),
	FUTBOL_O	("futbol-o"),
	TTY	("tty"),
	BINOCULARS	("binoculars"),
	PLUG	("plug"),
	SLIDESHARE	("slideshare"),
	TWITCH	("twitch"),
	YELP	("yelp"),
	NEWSPAPER_O	("newspaper-o"),
	WIFI	("wifi"),
	CALCULATOR	("calculator"),
	PAYPAL	("paypal"),
	GOOGLE_WALLET	("google-wallet"),
	CC_VISA	("cc-visa"),
	CC_MASTERCARD	("cc-mastercard"),
	CC_DISCOVER	("cc-discover"),
	CC_AMEX	("cc-amex"),
	CC_PAYPAL	("cc-paypal"),
	CC_STRIPE	("cc-stripe"),
	BELL_SLASH	("bell-slash"),
	BELL_SLASH_O	("bell-slash-o"),
	TRASH	("trash"),
	COPYRIGHT	("copyright"),
	AT	("at"),
	EYEDROPPER	("eyedropper"),
	PAINT_BRUSH	("paint-brush"),
	BIRTHDAY_CAKE	("birthday-cake"),
	AREA_CHART	("area-chart"),
	PIE_CHART	("pie-chart"),
	LINE_CHART	("line-chart"),
	LASTFM	("lastfm"),
	LASTFM_SQUARE	("lastfm-square"),
	TOGGLE_OFF	("toggle-off"),
	TOGGLE_ON	("toggle-on"),
	BICYCLE	("bicycle"),
	BUS	("bus"),
	IOXHOST	("ioxhost"),
	ANGELLIST	("angellist"),
	CC	("cc"),
	SHEKEL	("shekel"),
	SHEQEL	("sheqel"),
	ILS	("ils"),
	MEANPATH	("meanpath"),
	BUYSELLADS	("buysellads"),
	CONNECTDEVELOP	("connectdevelop"),
	DASHCUBE	("dashcube"),
	FORUMBEE	("forumbee"),
	LEANPUB	("leanpub"),
	SELLSY	("sellsy"),
	SHIRTSINBULK	("shirtsinbulk"),
	SIMPLYBUILT	("simplybuilt"),
	SKYATLAS	("skyatlas"),
	CART_PLUS	("cart-plus"),
	CART_ARROW_DOWN	("cart-arrow-down"),
	DIAMOND	("diamond"),
	SHIP	("ship"),
	USER_SECRET	("user-secret"),
	MOTORCYCLE	("motorcycle"),
	STREET_VIEW	("street-view"),
	HEARTBEAT	("heartbeat"),
	VENUS	("venus"),
	MARS	("mars"),
	MERCURY	("mercury"),
	INTERSEX	("intersex"),
	TRANSGENDER	("transgender"),
	TRANSGENDER_ALT	("transgender-alt"),
	VENUS_DOUBLE	("venus-double"),
	MARS_DOUBLE	("mars-double"),
	VENUS_MARS	("venus-mars"),
	MARS_STROKE	("mars-stroke"),
	MARS_STROKE_V	("mars-stroke-v"),
	MARS_STROKE_H	("mars-stroke-h"),
	NEUTER	("neuter"),
	GENDERLESS	("genderless"),
	FACEBOOK_OFFICIAL	("facebook-official"),
	PINTEREST_P	("pinterest-p"),
	WHATSAPP	("whatsapp"),
	SERVER	("server"),
	USER_PLUS	("user-plus"),
	USER_TIMES	("user-times"),
	HOTEL	("hotel"),
	BED	("bed"),
	VIACOIN	("viacoin"),
	TRAIN	("train"),
	SUBWAY	("subway"),
	MEDIUM	("medium"),
	YC	("yc"),
	Y_COMBINATOR	("y-combinator"),
	OPTIN_MONSTER	("optin-monster"),
	OPENCART	("opencart"),
	EXPEDITEDSSL	("expeditedssl"),
	BATTERY_4	("battery-4"),
	BATTERY_FULL	("battery-full"),
	BATTERY_3	("battery-3"),
	BATTERY_THREE_QUARTERS	("battery-three-quarters"),
	BATTERY_2	("battery-2"),
	BATTERY_HALF	("battery-half"),
	BATTERY_1	("battery-1"),
	BATTERY_QUARTER	("battery-quarter"),
	BATTERY_0	("battery-0"),
	BATTERY_EMPTY	("battery-empty"),
	MOUSE_POINTER	("mouse-pointer"),
	I_CURSOR	("i-cursor"),
	OBJECT_GROUP	("object-group"),
	OBJECT_UNGROUP	("object-ungroup"),
	STICKY_NOTE	("sticky-note"),
	STICKY_NOTE_O	("sticky-note-o"),
	CC_JCB	("cc-jcb"),
	CC_DINERS_CLUB	("cc-diners-club"),
	CLONE	("clone"),
	BALANCE_SCALE	("balance-scale"),
	HOURGLASS_O	("hourglass-o"),
	HOURGLASS_1	("hourglass-1"),
	HOURGLASS_START	("hourglass-start"),
	HOURGLASS_2	("hourglass-2"),
	HOURGLASS_HALF	("hourglass-half"),
	HOURGLASS_3	("hourglass-3"),
	HOURGLASS_END	("hourglass-end"),
	HOURGLASS	("hourglass"),
	HAND_GRAB_O	("hand-grab-o"),
	HAND_ROCK_O	("hand-rock-o"),
	HAND_STOP_O	("hand-stop-o"),
	HAND_PAPER_O	("hand-paper-o"),
	HAND_SCISSORS_O	("hand-scissors-o"),
	HAND_LIZARD_O	("hand-lizard-o"),
	HAND_SPOCK_O	("hand-spock-o"),
	HAND_POINTER_O	("hand-pointer-o"),
	HAND_PEACE_O	("hand-peace-o"),
	TRADEMARK	("trademark"),
	REGISTERED	("registered"),
	CREATIVE_COMMONS	("creative-commons"),
	GG	("gg"),
	GG_CIRCLE	("gg-circle"),
	TRIPADVISOR	("tripadvisor"),
	ODNOKLASSNIKI	("odnoklassniki"),
	ODNOKLASSNIKI_SQUARE	("odnoklassniki-square"),
	GET_POCKET	("get-pocket"),
	WIKIPEDIA_W	("wikipedia-w"),
	SAFARI	("safari"),
	CHROME	("chrome"),
	FIREFOX	("firefox"),
	OPERA	("opera"),
	INTERNET_EXPLORER	("internet-explorer"),
	TV	("tv"),
	TELEVISION	("television"),
	CONTAO	("contao"),
	AMAZON	("amazon"),
	CALENDAR_PLUS_O	("calendar-plus-o"),
	CALENDAR_MINUS_O	("calendar-minus-o"),
	CALENDAR_TIMES_O	("calendar-times-o"),
	CALENDAR_CHECK_O	("calendar-check-o"),
	INDUSTRY	("industry"),
	MAP_PIN	("map-pin"),
	MAP_SIGNS	("map-signs"),
	MAP_O	("map-o"),
	MAP	("map"),
	COMMENTING	("commenting"),
	COMMENTING_O	("commenting-o"),
	HOUZZ	("houzz"),
	VIMEO	("vimeo"),
	BLACK_TIE	("black-tie"),
	FONTICONS	("fonticons")
	;
	
	static final Map<String,BaseIcon> iconMap = new HashMap<String,BaseIcon>();
	
	static {
		iconMap.put("glass", 	GLASS	);
		iconMap.put("music", 	MUSIC	);
		iconMap.put("search", 	SEARCH	);
		iconMap.put("envelope-o", 	ENVELOPE_O	);
		iconMap.put("heart", 	HEART	);
		iconMap.put("star", 	STAR	);
		iconMap.put("star-o", 	STAR_O	);
		iconMap.put("user", 	USER	);
		iconMap.put("film", 	FILM	);
		iconMap.put("th-large", 	TH_LARGE	);
		iconMap.put("th", 	TH	);
		iconMap.put("th-list", 	TH_LIST	);
		iconMap.put("check", 	CHECK	);
		iconMap.put("remove", 	REMOVE	);
		iconMap.put("close", 	CLOSE	);
		iconMap.put("times", 	TIMES	);
		iconMap.put("search-plus", 	SEARCH_PLUS	);
		iconMap.put("search-minus", 	SEARCH_MINUS	);
		iconMap.put("power-off", 	POWER_OFF	);
		iconMap.put("signal", 	SIGNAL	);
		iconMap.put("gear", 	GEAR	);
		iconMap.put("cog", 	COG	);
		iconMap.put("trash-o", 	TRASH_O	);
		iconMap.put("home", 	HOME	);
		iconMap.put("file-o", 	FILE_O	);
		iconMap.put("clock-o", 	CLOCK_O	);
		iconMap.put("road", 	ROAD	);
		iconMap.put("download", 	DOWNLOAD	);
		iconMap.put("arrow-circle-o-down", 	ARROW_CIRCLE_O_DOWN	);
		iconMap.put("arrow-circle-o-up", 	ARROW_CIRCLE_O_UP	);
		iconMap.put("inbox", 	INBOX	);
		iconMap.put("play-circle-o", 	PLAY_CIRCLE_O	);
		iconMap.put("repeat", 	REPEAT	);
		iconMap.put("refresh", 	REFRESH	);
		iconMap.put("list-alt", 	LIST_ALT	);
		iconMap.put("lock", 	LOCK	);
		iconMap.put("flag", 	FLAG	);
		iconMap.put("headphones", 	HEADPHONES	);
		iconMap.put("volume-off", 	VOLUME_OFF	);
		iconMap.put("volume-down", 	VOLUME_DOWN	);
		iconMap.put("volume-up", 	VOLUME_UP	);
		iconMap.put("qrcode", 	QRCODE	);
		iconMap.put("barcode", 	BARCODE	);
		iconMap.put("tag", 	TAG	);
		iconMap.put("tags", 	TAGS	);
		iconMap.put("book", 	BOOK	);
		iconMap.put("bookmark", 	BOOKMARK	);
		iconMap.put("print", 	PRINT	);
		iconMap.put("camera", 	CAMERA	);
		iconMap.put("font", 	FONT	);
		iconMap.put("bold", 	BOLD	);
		iconMap.put("italic", 	ITALIC	);
		iconMap.put("text-height", 	TEXT_HEIGHT	);
		iconMap.put("text-width", 	TEXT_WIDTH	);
		iconMap.put("align-left", 	ALIGN_LEFT	);
		iconMap.put("align-center", 	ALIGN_CENTER	);
		iconMap.put("align-right", 	ALIGN_RIGHT	);
		iconMap.put("align-justify", 	ALIGN_JUSTIFY	);
		iconMap.put("list", 	LIST	);
		iconMap.put("dedent", 	DEDENT	);
		iconMap.put("outdent", 	OUTDENT	);
		iconMap.put("indent", 	INDENT	);
		iconMap.put("video-camera", 	VIDEO_CAMERA	);
		iconMap.put("photo", 	PHOTO	);
		iconMap.put("image", 	IMAGE	);
		iconMap.put("picture-o", 	PICTURE_O	);
		iconMap.put("pencil", 	PENCIL	);
		iconMap.put("map-marker", 	MAP_MARKER	);
		iconMap.put("adjust", 	ADJUST	);
		iconMap.put("tint", 	TINT	);
		iconMap.put("edit", 	EDIT	);
		iconMap.put("pencil-square-o", 	PENCIL_SQUARE_O	);
		iconMap.put("share-square-o", 	SHARE_SQUARE_O	);
		iconMap.put("check-square-o", 	CHECK_SQUARE_O	);
		iconMap.put("arrows", 	ARROWS	);
		iconMap.put("step-backward", 	STEP_BACKWARD	);
		iconMap.put("fast-backward", 	FAST_BACKWARD	);
		iconMap.put("backward", 	BACKWARD	);
		iconMap.put("play", 	PLAY	);
		iconMap.put("pause", 	PAUSE	);
		iconMap.put("stop", 	STOP	);
		iconMap.put("forward", 	FORWARD	);
		iconMap.put("fast-forward", 	FAST_FORWARD	);
		iconMap.put("step-forward", 	STEP_FORWARD	);
		iconMap.put("eject", 	EJECT	);
		iconMap.put("chevron-left", 	CHEVRON_LEFT	);
		iconMap.put("chevron-right", 	CHEVRON_RIGHT	);
		iconMap.put("plus-circle", 	PLUS_CIRCLE	);
		iconMap.put("minus-circle", 	MINUS_CIRCLE	);
		iconMap.put("times-circle", 	TIMES_CIRCLE	);
		iconMap.put("check-circle", 	CHECK_CIRCLE	);
		iconMap.put("question-circle", 	QUESTION_CIRCLE	);
		iconMap.put("info-circle", 	INFO_CIRCLE	);
		iconMap.put("crosshairs", 	CROSSHAIRS	);
		iconMap.put("times-circle-o", 	TIMES_CIRCLE_O	);
		iconMap.put("check-circle-o", 	CHECK_CIRCLE_O	);
		iconMap.put("ban", 	BAN	);
		iconMap.put("arrow-left", 	ARROW_LEFT	);
		iconMap.put("arrow-right", 	ARROW_RIGHT	);
		iconMap.put("arrow-up", 	ARROW_UP	);
		iconMap.put("arrow-down", 	ARROW_DOWN	);
		iconMap.put("mail-forward", 	MAIL_FORWARD	);
		iconMap.put("share", 	SHARE	);
		iconMap.put("expand", 	EXPAND	);
		iconMap.put("compress", 	COMPRESS	);
		iconMap.put("plus", 	PLUS	);
		iconMap.put("minus", 	MINUS	);
		iconMap.put("asterisk", 	ASTERISK	);
		iconMap.put("exclamation-circle", 	EXCLAMATION_CIRCLE	);
		iconMap.put("gift", 	GIFT	);
		iconMap.put("leaf", 	LEAF	);
		iconMap.put("fire", 	FIRE	);
		iconMap.put("eye", 	EYE	);
		iconMap.put("eye-slash", 	EYE_SLASH	);
		iconMap.put("warning", 	WARNING	);
		iconMap.put("exclamation-triangle", 	EXCLAMATION_TRIANGLE	);
		iconMap.put("plane", 	PLANE	);
		iconMap.put("calendar", 	CALENDAR	);
		iconMap.put("random", 	RANDOM	);
		iconMap.put("comment", 	COMMENT	);
		iconMap.put("magnet", 	MAGNET	);
		iconMap.put("chevron-up", 	CHEVRON_UP	);
		iconMap.put("chevron-down", 	CHEVRON_DOWN	);
		iconMap.put("retweet", 	RETWEET	);
		iconMap.put("shopping-cart", 	SHOPPING_CART	);
		iconMap.put("folder", 	FOLDER	);
		iconMap.put("folder-open", 	FOLDER_OPEN	);
		iconMap.put("arrows-v", 	ARROWS_V	);
		iconMap.put("arrows-h", 	ARROWS_H	);
		iconMap.put("bar-chart-o", 	BAR_CHART_O	);
		iconMap.put("bar-chart", 	BAR_CHART	);
		iconMap.put("twitter-square", 	TWITTER_SQUARE	);
		iconMap.put("facebook-square", 	FACEBOOK_SQUARE	);
		iconMap.put("camera-retro", 	CAMERA_RETRO	);
		iconMap.put("key", 	KEY	);
		iconMap.put("gears", 	GEARS	);
		iconMap.put("cogs", 	COGS	);
		iconMap.put("comments", 	COMMENTS	);
		iconMap.put("thumbs-o-up", 	THUMBS_O_UP	);
		iconMap.put("thumbs-o-down", 	THUMBS_O_DOWN	);
		iconMap.put("star-half", 	STAR_HALF	);
		iconMap.put("heart-o", 	HEART_O	);
		iconMap.put("sign-out", 	SIGN_OUT	);
		iconMap.put("linkedin-square", 	LINKEDIN_SQUARE	);
		iconMap.put("thumb-tack", 	THUMB_TACK	);
		iconMap.put("external-link", 	EXTERNAL_LINK	);
		iconMap.put("sign-in", 	SIGN_IN	);
		iconMap.put("trophy", 	TROPHY	);
		iconMap.put("github-square", 	GITHUB_SQUARE	);
		iconMap.put("upload", 	UPLOAD	);
		iconMap.put("lemon-o", 	LEMON_O	);
		iconMap.put("phone", 	PHONE	);
		iconMap.put("square-o", 	SQUARE_O	);
		iconMap.put("bookmark-o", 	BOOKMARK_O	);
		iconMap.put("phone-square", 	PHONE_SQUARE	);
		iconMap.put("twitter", 	TWITTER	);
		iconMap.put("facebook-f", 	FACEBOOK_F	);
		iconMap.put("facebook", 	FACEBOOK	);
		iconMap.put("github", 	GITHUB	);
		iconMap.put("unlock", 	UNLOCK	);
		iconMap.put("credit-card", 	CREDIT_CARD	);
		iconMap.put("feed", 	FEED	);
		iconMap.put("rss", 	RSS	);
		iconMap.put("hdd-o", 	HDD_O	);
		iconMap.put("bullhorn", 	BULLHORN	);
		iconMap.put("bell", 	BELL	);
		iconMap.put("certificate", 	CERTIFICATE	);
		iconMap.put("hand-o-right", 	HAND_O_RIGHT	);
		iconMap.put("hand-o-left", 	HAND_O_LEFT	);
		iconMap.put("hand-o-up", 	HAND_O_UP	);
		iconMap.put("hand-o-down", 	HAND_O_DOWN	);
		iconMap.put("arrow-circle-left", 	ARROW_CIRCLE_LEFT	);
		iconMap.put("arrow-circle-right", 	ARROW_CIRCLE_RIGHT	);
		iconMap.put("arrow-circle-up", 	ARROW_CIRCLE_UP	);
		iconMap.put("arrow-circle-down", 	ARROW_CIRCLE_DOWN	);
		iconMap.put("globe", 	GLOBE	);
		iconMap.put("wrench", 	WRENCH	);
		iconMap.put("tasks", 	TASKS	);
		iconMap.put("filter", 	FILTER	);
		iconMap.put("briefcase", 	BRIEFCASE	);
		iconMap.put("arrows-alt", 	ARROWS_ALT	);
		iconMap.put("group", 	GROUP	);
		iconMap.put("users", 	USERS	);
		iconMap.put("chain", 	CHAIN	);
		iconMap.put("link", 	LINK	);
		iconMap.put("cloud", 	CLOUD	);
		iconMap.put("flask", 	FLASK	);
		iconMap.put("cut", 	CUT	);
		iconMap.put("scissors", 	SCISSORS	);
		iconMap.put("copy", 	COPY	);
		iconMap.put("files-o", 	FILES_O	);
		iconMap.put("paperclip", 	PAPERCLIP	);
		iconMap.put("save", 	SAVE	);
		iconMap.put("floppy-o", 	FLOPPY_O	);
		iconMap.put("square", 	SQUARE	);
		iconMap.put("navicon", 	NAVICON	);
		iconMap.put("reorder", 	REORDER	);
		iconMap.put("bars", 	BARS	);
		iconMap.put("list-ul", 	LIST_UL	);
		iconMap.put("list-ol", 	LIST_OL	);
		iconMap.put("strikethrough", 	STRIKETHROUGH	);
		iconMap.put("underline", 	UNDERLINE	);
		iconMap.put("table", 	TABLE	);
		iconMap.put("magic", 	MAGIC	);
		iconMap.put("truck", 	TRUCK	);
		iconMap.put("pinterest", 	PINTEREST	);
		iconMap.put("pinterest-square", 	PINTEREST_SQUARE	);
		iconMap.put("google-plus-square", 	GOOGLE_PLUS_SQUARE	);
		iconMap.put("google-plus", 	GOOGLE_PLUS	);
		iconMap.put("money", 	MONEY	);
		iconMap.put("caret-down", 	CARET_DOWN	);
		iconMap.put("caret-up", 	CARET_UP	);
		iconMap.put("caret-left", 	CARET_LEFT	);
		iconMap.put("caret-right", 	CARET_RIGHT	);
		iconMap.put("columns", 	COLUMNS	);
		iconMap.put("unsorted", 	UNSORTED	);
		iconMap.put("sort", 	SORT	);
		iconMap.put("sort-down", 	SORT_DOWN	);
		iconMap.put("sort-desc", 	SORT_DESC	);
		iconMap.put("sort-up", 	SORT_UP	);
		iconMap.put("sort-asc", 	SORT_ASC	);
		iconMap.put("envelope", 	ENVELOPE	);
		iconMap.put("linkedin", 	LINKEDIN	);
		iconMap.put("rotate-left", 	ROTATE_LEFT	);
		iconMap.put("undo", 	UNDO	);
		iconMap.put("legal", 	LEGAL	);
		iconMap.put("gavel", 	GAVEL	);
		iconMap.put("dashboard", 	DASHBOARD	);
		iconMap.put("tachometer", 	TACHOMETER	);
		iconMap.put("comment-o", 	COMMENT_O	);
		iconMap.put("comments-o", 	COMMENTS_O	);
		iconMap.put("flash", 	FLASH	);
		iconMap.put("bolt", 	BOLT	);
		iconMap.put("sitemap", 	SITEMAP	);
		iconMap.put("umbrella", 	UMBRELLA	);
		iconMap.put("paste", 	PASTE	);
		iconMap.put("clipboard", 	CLIPBOARD	);
		iconMap.put("lightbulb-o", 	LIGHTBULB_O	);
		iconMap.put("exchange", 	EXCHANGE	);
		iconMap.put("cloud-download", 	CLOUD_DOWNLOAD	);
		iconMap.put("cloud-upload", 	CLOUD_UPLOAD	);
		iconMap.put("user-md", 	USER_MD	);
		iconMap.put("stethoscope", 	STETHOSCOPE	);
		iconMap.put("suitcase", 	SUITCASE	);
		iconMap.put("bell-o", 	BELL_O	);
		iconMap.put("coffee", 	COFFEE	);
		iconMap.put("cutlery", 	CUTLERY	);
		iconMap.put("file-text-o", 	FILE_TEXT_O	);
		iconMap.put("building-o", 	BUILDING_O	);
		iconMap.put("hospital-o", 	HOSPITAL_O	);
		iconMap.put("ambulance", 	AMBULANCE	);
		iconMap.put("medkit", 	MEDKIT	);
		iconMap.put("fighter-jet", 	FIGHTER_JET	);
		iconMap.put("beer", 	BEER	);
		iconMap.put("h-square", 	H_SQUARE	);
		iconMap.put("plus-square", 	PLUS_SQUARE	);
		iconMap.put("angle-double-left", 	ANGLE_DOUBLE_LEFT	);
		iconMap.put("angle-double-right", 	ANGLE_DOUBLE_RIGHT	);
		iconMap.put("angle-double-up", 	ANGLE_DOUBLE_UP	);
		iconMap.put("angle-double-down", 	ANGLE_DOUBLE_DOWN	);
		iconMap.put("angle-left", 	ANGLE_LEFT	);
		iconMap.put("angle-right", 	ANGLE_RIGHT	);
		iconMap.put("angle-up", 	ANGLE_UP	);
		iconMap.put("angle-down", 	ANGLE_DOWN	);
		iconMap.put("desktop", 	DESKTOP	);
		iconMap.put("laptop", 	LAPTOP	);
		iconMap.put("tablet", 	TABLET	);
		iconMap.put("mobile-phone", 	MOBILE_PHONE	);
		iconMap.put("mobile", 	MOBILE	);
		iconMap.put("circle-o", 	CIRCLE_O	);
		iconMap.put("quote-left", 	QUOTE_LEFT	);
		iconMap.put("quote-right", 	QUOTE_RIGHT	);
		iconMap.put("spinner", 	SPINNER	);
		iconMap.put("circle", 	CIRCLE	);
		iconMap.put("mail-reply", 	MAIL_REPLY	);
		iconMap.put("reply", 	REPLY	);
		iconMap.put("github-alt", 	GITHUB_ALT	);
		iconMap.put("folder-o", 	FOLDER_O	);
		iconMap.put("folder-open-o", 	FOLDER_OPEN_O	);
		iconMap.put("smile-o", 	SMILE_O	);
		iconMap.put("frown-o", 	FROWN_O	);
		iconMap.put("meh-o", 	MEH_O	);
		iconMap.put("gamepad", 	GAMEPAD	);
		iconMap.put("keyboard-o", 	KEYBOARD_O	);
		iconMap.put("flag-o", 	FLAG_O	);
		iconMap.put("flag-checkered", 	FLAG_CHECKERED	);
		iconMap.put("terminal", 	TERMINAL	);
		iconMap.put("code", 	CODE	);
		iconMap.put("mail-reply-all", 	MAIL_REPLY_ALL	);
		iconMap.put("reply-all", 	REPLY_ALL	);
		iconMap.put("star-half-empty", 	STAR_HALF_EMPTY	);
		iconMap.put("star-half-full", 	STAR_HALF_FULL	);
		iconMap.put("star-half-o", 	STAR_HALF_O	);
		iconMap.put("location-arrow", 	LOCATION_ARROW	);
		iconMap.put("crop", 	CROP	);
		iconMap.put("code-fork", 	CODE_FORK	);
		iconMap.put("unlink", 	UNLINK	);
		iconMap.put("chain-broken", 	CHAIN_BROKEN	);
		iconMap.put("question", 	QUESTION	);
		iconMap.put("info", 	INFO	);
		iconMap.put("exclamation", 	EXCLAMATION	);
		iconMap.put("superscript", 	SUPERSCRIPT	);
		iconMap.put("subscript", 	SUBSCRIPT	);
		iconMap.put("eraser", 	ERASER	);
		iconMap.put("puzzle-piece", 	PUZZLE_PIECE	);
		iconMap.put("microphone", 	MICROPHONE	);
		iconMap.put("microphone-slash", 	MICROPHONE_SLASH	);
		iconMap.put("shield", 	SHIELD	);
		iconMap.put("calendar-o", 	CALENDAR_O	);
		iconMap.put("fire-extinguisher", 	FIRE_EXTINGUISHER	);
		iconMap.put("rocket", 	ROCKET	);
		iconMap.put("maxcdn", 	MAXCDN	);
		iconMap.put("chevron-circle-left", 	CHEVRON_CIRCLE_LEFT	);
		iconMap.put("chevron-circle-right", 	CHEVRON_CIRCLE_RIGHT	);
		iconMap.put("chevron-circle-up", 	CHEVRON_CIRCLE_UP	);
		iconMap.put("chevron-circle-down", 	CHEVRON_CIRCLE_DOWN	);
		iconMap.put("html5", 	HTML5	);
		iconMap.put("css3", 	CSS3	);
		iconMap.put("anchor", 	ANCHOR	);
		iconMap.put("unlock-alt", 	UNLOCK_ALT	);
		iconMap.put("bullseye", 	BULLSEYE	);
		iconMap.put("ellipsis-h", 	ELLIPSIS_H	);
		iconMap.put("ellipsis-v", 	ELLIPSIS_V	);
		iconMap.put("rss-square", 	RSS_SQUARE	);
		iconMap.put("play-circle", 	PLAY_CIRCLE	);
		iconMap.put("ticket", 	TICKET	);
		iconMap.put("minus-square", 	MINUS_SQUARE	);
		iconMap.put("minus-square-o", 	MINUS_SQUARE_O	);
		iconMap.put("level-up", 	LEVEL_UP	);
		iconMap.put("level-down", 	LEVEL_DOWN	);
		iconMap.put("check-square", 	CHECK_SQUARE	);
		iconMap.put("pencil-square", 	PENCIL_SQUARE	);
		iconMap.put("external-link-square", 	EXTERNAL_LINK_SQUARE	);
		iconMap.put("share-square", 	SHARE_SQUARE	);
		iconMap.put("compass", 	COMPASS	);
		iconMap.put("toggle-down", 	TOGGLE_DOWN	);
		iconMap.put("caret-square-o-down", 	CARET_SQUARE_O_DOWN	);
		iconMap.put("toggle-up", 	TOGGLE_UP	);
		iconMap.put("caret-square-o-up", 	CARET_SQUARE_O_UP	);
		iconMap.put("toggle-right", 	TOGGLE_RIGHT	);
		iconMap.put("caret-square-o-right", 	CARET_SQUARE_O_RIGHT	);
		iconMap.put("euro", 	EURO	);
		iconMap.put("eur", 	EUR	);
		iconMap.put("gbp", 	GBP	);
		iconMap.put("dollar", 	DOLLAR	);
		iconMap.put("usd", 	USD	);
		iconMap.put("rupee", 	RUPEE	);
		iconMap.put("inr", 	INR	);
		iconMap.put("cny", 	CNY	);
		iconMap.put("rmb", 	RMB	);
		iconMap.put("yen", 	YEN	);
		iconMap.put("jpy", 	JPY	);
		iconMap.put("ruble", 	RUBLE	);
		iconMap.put("rouble", 	ROUBLE	);
		iconMap.put("rub", 	RUB	);
		iconMap.put("won", 	WON	);
		iconMap.put("krw", 	KRW	);
		iconMap.put("bitcoin", 	BITCOIN	);
		iconMap.put("btc", 	BTC	);
		iconMap.put("file", 	FILE	);
		iconMap.put("file-text", 	FILE_TEXT	);
		iconMap.put("sort-alpha-asc", 	SORT_ALPHA_ASC	);
		iconMap.put("sort-alpha-desc", 	SORT_ALPHA_DESC	);
		iconMap.put("sort-amount-asc", 	SORT_AMOUNT_ASC	);
		iconMap.put("sort-amount-desc", 	SORT_AMOUNT_DESC	);
		iconMap.put("sort-numeric-asc", 	SORT_NUMERIC_ASC	);
		iconMap.put("sort-numeric-desc", 	SORT_NUMERIC_DESC	);
		iconMap.put("thumbs-up", 	THUMBS_UP	);
		iconMap.put("thumbs-down", 	THUMBS_DOWN	);
		iconMap.put("youtube-square", 	YOUTUBE_SQUARE	);
		iconMap.put("youtube", 	YOUTUBE	);
		iconMap.put("xing", 	XING	);
		iconMap.put("xing-square", 	XING_SQUARE	);
		iconMap.put("youtube-play", 	YOUTUBE_PLAY	);
		iconMap.put("dropbox", 	DROPBOX	);
		iconMap.put("stack-overflow", 	STACK_OVERFLOW	);
		iconMap.put("instagram", 	INSTAGRAM	);
		iconMap.put("flickr", 	FLICKR	);
		iconMap.put("adn", 	ADN	);
		iconMap.put("bitbucket", 	BITBUCKET	);
		iconMap.put("bitbucket-square", 	BITBUCKET_SQUARE	);
		iconMap.put("tumblr", 	TUMBLR	);
		iconMap.put("tumblr-square", 	TUMBLR_SQUARE	);
		iconMap.put("long-arrow-down", 	LONG_ARROW_DOWN	);
		iconMap.put("long-arrow-up", 	LONG_ARROW_UP	);
		iconMap.put("long-arrow-left", 	LONG_ARROW_LEFT	);
		iconMap.put("long-arrow-right", 	LONG_ARROW_RIGHT	);
		iconMap.put("apple", 	APPLE	);
		iconMap.put("windows", 	WINDOWS	);
		iconMap.put("android", 	ANDROID	);
		iconMap.put("linux", 	LINUX	);
		iconMap.put("dribbble", 	DRIBBBLE	);
		iconMap.put("skype", 	SKYPE	);
		iconMap.put("foursquare", 	FOURSQUARE	);
		iconMap.put("trello", 	TRELLO	);
		iconMap.put("female", 	FEMALE	);
		iconMap.put("male", 	MALE	);
		iconMap.put("gittip", 	GITTIP	);
		iconMap.put("gratipay", 	GRATIPAY	);
		iconMap.put("sun-o", 	SUN_O	);
		iconMap.put("moon-o", 	MOON_O	);
		iconMap.put("archive", 	ARCHIVE	);
		iconMap.put("bug", 	BUG	);
		iconMap.put("vk", 	VK	);
		iconMap.put("weibo", 	WEIBO	);
		iconMap.put("renren", 	RENREN	);
		iconMap.put("pagelines", 	PAGELINES	);
		iconMap.put("stack-exchange", 	STACK_EXCHANGE	);
		iconMap.put("arrow-circle-o-right", 	ARROW_CIRCLE_O_RIGHT	);
		iconMap.put("arrow-circle-o-left", 	ARROW_CIRCLE_O_LEFT	);
		iconMap.put("toggle-left", 	TOGGLE_LEFT	);
		iconMap.put("caret-square-o-left", 	CARET_SQUARE_O_LEFT	);
		iconMap.put("dot-circle-o", 	DOT_CIRCLE_O	);
		iconMap.put("wheelchair", 	WHEELCHAIR	);
		iconMap.put("vimeo-square", 	VIMEO_SQUARE	);
		iconMap.put("turkish-lira", 	TURKISH_LIRA	);
		iconMap.put("try", 	TRY	);
		iconMap.put("plus-square-o", 	PLUS_SQUARE_O	);
		iconMap.put("space-shuttle", 	SPACE_SHUTTLE	);
		iconMap.put("slack", 	SLACK	);
		iconMap.put("envelope-square", 	ENVELOPE_SQUARE	);
		iconMap.put("wordpress", 	WORDPRESS	);
		iconMap.put("openid", 	OPENID	);
		iconMap.put("institution", 	INSTITUTION	);
		iconMap.put("bank", 	BANK	);
		iconMap.put("university", 	UNIVERSITY	);
		iconMap.put("mortar-board", 	MORTAR_BOARD	);
		iconMap.put("graduation-cap", 	GRADUATION_CAP	);
		iconMap.put("yahoo", 	YAHOO	);
		iconMap.put("google", 	GOOGLE	);
		iconMap.put("reddit", 	REDDIT	);
		iconMap.put("reddit-square", 	REDDIT_SQUARE	);
		iconMap.put("stumbleupon-circle", 	STUMBLEUPON_CIRCLE	);
		iconMap.put("stumbleupon", 	STUMBLEUPON	);
		iconMap.put("delicious", 	DELICIOUS	);
		iconMap.put("digg", 	DIGG	);
		iconMap.put("pied-piper", 	PIED_PIPER	);
		iconMap.put("pied-piper-alt", 	PIED_PIPER_ALT	);
		iconMap.put("drupal", 	DRUPAL	);
		iconMap.put("joomla", 	JOOMLA	);
		iconMap.put("language", 	LANGUAGE	);
		iconMap.put("fax", 	FAX	);
		iconMap.put("building", 	BUILDING	);
		iconMap.put("child", 	CHILD	);
		iconMap.put("paw", 	PAW	);
		iconMap.put("spoon", 	SPOON	);
		iconMap.put("cube", 	CUBE	);
		iconMap.put("cubes", 	CUBES	);
		iconMap.put("behance", 	BEHANCE	);
		iconMap.put("behance-square", 	BEHANCE_SQUARE	);
		iconMap.put("steam", 	STEAM	);
		iconMap.put("steam-square", 	STEAM_SQUARE	);
		iconMap.put("recycle", 	RECYCLE	);
		iconMap.put("automobile", 	AUTOMOBILE	);
		iconMap.put("car", 	CAR	);
		iconMap.put("cab", 	CAB	);
		iconMap.put("taxi", 	TAXI	);
		iconMap.put("tree", 	TREE	);
		iconMap.put("spotify", 	SPOTIFY	);
		iconMap.put("deviantart", 	DEVIANTART	);
		iconMap.put("soundcloud", 	SOUNDCLOUD	);
		iconMap.put("database", 	DATABASE	);
		iconMap.put("file-pdf-o", 	FILE_PDF_O	);
		iconMap.put("file-word-o", 	FILE_WORD_O	);
		iconMap.put("file-excel-o", 	FILE_EXCEL_O	);
		iconMap.put("file-powerpoint-o", 	FILE_POWERPOINT_O	);
		iconMap.put("file-photo-o", 	FILE_PHOTO_O	);
		iconMap.put("file-picture-o", 	FILE_PICTURE_O	);
		iconMap.put("file-image-o", 	FILE_IMAGE_O	);
		iconMap.put("file-zip-o", 	FILE_ZIP_O	);
		iconMap.put("file-archive-o", 	FILE_ARCHIVE_O	);
		iconMap.put("file-sound-o", 	FILE_SOUND_O	);
		iconMap.put("file-audio-o", 	FILE_AUDIO_O	);
		iconMap.put("file-movie-o", 	FILE_MOVIE_O	);
		iconMap.put("file-video-o", 	FILE_VIDEO_O	);
		iconMap.put("file-code-o", 	FILE_CODE_O	);
		iconMap.put("vine", 	VINE	);
		iconMap.put("codepen", 	CODEPEN	);
		iconMap.put("jsfiddle", 	JSFIDDLE	);
		iconMap.put("life-bouy", 	LIFE_BOUY	);
		iconMap.put("life-buoy", 	LIFE_BUOY	);
		iconMap.put("life-saver", 	LIFE_SAVER	);
		iconMap.put("support", 	SUPPORT	);
		iconMap.put("life-ring", 	LIFE_RING	);
		iconMap.put("circle-o-notch", 	CIRCLE_O_NOTCH	);
		iconMap.put("ra", 	RA	);
		iconMap.put("rebel", 	REBEL	);
		iconMap.put("ge", 	GE	);
		iconMap.put("empire", 	EMPIRE	);
		iconMap.put("git-square", 	GIT_SQUARE	);
		iconMap.put("git", 	GIT	);
		iconMap.put("y-combinator-square", 	Y_COMBINATOR_SQUARE	);
		iconMap.put("yc-square", 	YC_SQUARE	);
		iconMap.put("hacker-news", 	HACKER_NEWS	);
		iconMap.put("tencent-weibo", 	TENCENT_WEIBO	);
		iconMap.put("qq", 	QQ	);
		iconMap.put("wechat", 	WECHAT	);
		iconMap.put("weixin", 	WEIXIN	);
		iconMap.put("send", 	SEND	);
		iconMap.put("paper-plane", 	PAPER_PLANE	);
		iconMap.put("send-o", 	SEND_O	);
		iconMap.put("paper-plane-o", 	PAPER_PLANE_O	);
		iconMap.put("history", 	HISTORY	);
		iconMap.put("circle-thin", 	CIRCLE_THIN	);
		iconMap.put("header", 	HEADER	);
		iconMap.put("paragraph", 	PARAGRAPH	);
		iconMap.put("sliders", 	SLIDERS	);
		iconMap.put("share-alt", 	SHARE_ALT	);
		iconMap.put("share-alt-square", 	SHARE_ALT_SQUARE	);
		iconMap.put("bomb", 	BOMB	);
		iconMap.put("soccer-ball-o", 	SOCCER_BALL_O	);
		iconMap.put("futbol-o", 	FUTBOL_O	);
		iconMap.put("tty", 	TTY	);
		iconMap.put("binoculars", 	BINOCULARS	);
		iconMap.put("plug", 	PLUG	);
		iconMap.put("slideshare", 	SLIDESHARE	);
		iconMap.put("twitch", 	TWITCH	);
		iconMap.put("yelp", 	YELP	);
		iconMap.put("newspaper-o", 	NEWSPAPER_O	);
		iconMap.put("wifi", 	WIFI	);
		iconMap.put("calculator", 	CALCULATOR	);
		iconMap.put("paypal", 	PAYPAL	);
		iconMap.put("google-wallet", 	GOOGLE_WALLET	);
		iconMap.put("cc-visa", 	CC_VISA	);
		iconMap.put("cc-mastercard", 	CC_MASTERCARD	);
		iconMap.put("cc-discover", 	CC_DISCOVER	);
		iconMap.put("cc-amex", 	CC_AMEX	);
		iconMap.put("cc-paypal", 	CC_PAYPAL	);
		iconMap.put("cc-stripe", 	CC_STRIPE	);
		iconMap.put("bell-slash", 	BELL_SLASH	);
		iconMap.put("bell-slash-o", 	BELL_SLASH_O	);
		iconMap.put("trash", 	TRASH	);
		iconMap.put("copyright", 	COPYRIGHT	);
		iconMap.put("at", 	AT	);
		iconMap.put("eyedropper", 	EYEDROPPER	);
		iconMap.put("paint-brush", 	PAINT_BRUSH	);
		iconMap.put("birthday-cake", 	BIRTHDAY_CAKE	);
		iconMap.put("area-chart", 	AREA_CHART	);
		iconMap.put("pie-chart", 	PIE_CHART	);
		iconMap.put("line-chart", 	LINE_CHART	);
		iconMap.put("lastfm", 	LASTFM	);
		iconMap.put("lastfm-square", 	LASTFM_SQUARE	);
		iconMap.put("toggle-off", 	TOGGLE_OFF	);
		iconMap.put("toggle-on", 	TOGGLE_ON	);
		iconMap.put("bicycle", 	BICYCLE	);
		iconMap.put("bus", 	BUS	);
		iconMap.put("ioxhost", 	IOXHOST	);
		iconMap.put("angellist", 	ANGELLIST	);
		iconMap.put("cc", 	CC	);
		iconMap.put("shekel", 	SHEKEL	);
		iconMap.put("sheqel", 	SHEQEL	);
		iconMap.put("ils", 	ILS	);
		iconMap.put("meanpath", 	MEANPATH	);
		iconMap.put("buysellads", 	BUYSELLADS	);
		iconMap.put("connectdevelop", 	CONNECTDEVELOP	);
		iconMap.put("dashcube", 	DASHCUBE	);
		iconMap.put("forumbee", 	FORUMBEE	);
		iconMap.put("leanpub", 	LEANPUB	);
		iconMap.put("sellsy", 	SELLSY	);
		iconMap.put("shirtsinbulk", 	SHIRTSINBULK	);
		iconMap.put("simplybuilt", 	SIMPLYBUILT	);
		iconMap.put("skyatlas", 	SKYATLAS	);
		iconMap.put("cart-plus", 	CART_PLUS	);
		iconMap.put("cart-arrow-down", 	CART_ARROW_DOWN	);
		iconMap.put("diamond", 	DIAMOND	);
		iconMap.put("ship", 	SHIP	);
		iconMap.put("user-secret", 	USER_SECRET	);
		iconMap.put("motorcycle", 	MOTORCYCLE	);
		iconMap.put("street-view", 	STREET_VIEW	);
		iconMap.put("heartbeat", 	HEARTBEAT	);
		iconMap.put("venus", 	VENUS	);
		iconMap.put("mars", 	MARS	);
		iconMap.put("mercury", 	MERCURY	);
		iconMap.put("intersex", 	INTERSEX	);
		iconMap.put("transgender", 	TRANSGENDER	);
		iconMap.put("transgender-alt", 	TRANSGENDER_ALT	);
		iconMap.put("venus-double", 	VENUS_DOUBLE	);
		iconMap.put("mars-double", 	MARS_DOUBLE	);
		iconMap.put("venus-mars", 	VENUS_MARS	);
		iconMap.put("mars-stroke", 	MARS_STROKE	);
		iconMap.put("mars-stroke-v", 	MARS_STROKE_V	);
		iconMap.put("mars-stroke-h", 	MARS_STROKE_H	);
		iconMap.put("neuter", 	NEUTER	);
		iconMap.put("genderless", 	GENDERLESS	);
		iconMap.put("facebook-official", 	FACEBOOK_OFFICIAL	);
		iconMap.put("pinterest-p", 	PINTEREST_P	);
		iconMap.put("whatsapp", 	WHATSAPP	);
		iconMap.put("server", 	SERVER	);
		iconMap.put("user-plus", 	USER_PLUS	);
		iconMap.put("user-times", 	USER_TIMES	);
		iconMap.put("hotel", 	HOTEL	);
		iconMap.put("bed", 	BED	);
		iconMap.put("viacoin", 	VIACOIN	);
		iconMap.put("train", 	TRAIN	);
		iconMap.put("subway", 	SUBWAY	);
		iconMap.put("medium", 	MEDIUM	);
		iconMap.put("yc", 	YC	);
		iconMap.put("y-combinator", 	Y_COMBINATOR	);
		iconMap.put("optin-monster", 	OPTIN_MONSTER	);
		iconMap.put("opencart", 	OPENCART	);
		iconMap.put("expeditedssl", 	EXPEDITEDSSL	);
		iconMap.put("battery-4", 	BATTERY_4	);
		iconMap.put("battery-full", 	BATTERY_FULL	);
		iconMap.put("battery-3", 	BATTERY_3	);
		iconMap.put("battery-three-quarters", 	BATTERY_THREE_QUARTERS	);
		iconMap.put("battery-2", 	BATTERY_2	);
		iconMap.put("battery-half", 	BATTERY_HALF	);
		iconMap.put("battery-1", 	BATTERY_1	);
		iconMap.put("battery-quarter", 	BATTERY_QUARTER	);
		iconMap.put("battery-0", 	BATTERY_0	);
		iconMap.put("battery-empty", 	BATTERY_EMPTY	);
		iconMap.put("mouse-pointer", 	MOUSE_POINTER	);
		iconMap.put("i-cursor", 	I_CURSOR	);
		iconMap.put("object-group", 	OBJECT_GROUP	);
		iconMap.put("object-ungroup", 	OBJECT_UNGROUP	);
		iconMap.put("sticky-note", 	STICKY_NOTE	);
		iconMap.put("sticky-note-o", 	STICKY_NOTE_O	);
		iconMap.put("cc-jcb", 	CC_JCB	);
		iconMap.put("cc-diners-club", 	CC_DINERS_CLUB	);
		iconMap.put("clone", 	CLONE	);
		iconMap.put("balance-scale", 	BALANCE_SCALE	);
		iconMap.put("hourglass-o", 	HOURGLASS_O	);
		iconMap.put("hourglass-1", 	HOURGLASS_1	);
		iconMap.put("hourglass-start", 	HOURGLASS_START	);
		iconMap.put("hourglass-2", 	HOURGLASS_2	);
		iconMap.put("hourglass-half", 	HOURGLASS_HALF	);
		iconMap.put("hourglass-3", 	HOURGLASS_3	);
		iconMap.put("hourglass-end", 	HOURGLASS_END	);
		iconMap.put("hourglass", 	HOURGLASS	);
		iconMap.put("hand-grab-o", 	HAND_GRAB_O	);
		iconMap.put("hand-rock-o", 	HAND_ROCK_O	);
		iconMap.put("hand-stop-o", 	HAND_STOP_O	);
		iconMap.put("hand-paper-o", 	HAND_PAPER_O	);
		iconMap.put("hand-scissors-o", 	HAND_SCISSORS_O	);
		iconMap.put("hand-lizard-o", 	HAND_LIZARD_O	);
		iconMap.put("hand-spock-o", 	HAND_SPOCK_O	);
		iconMap.put("hand-pointer-o", 	HAND_POINTER_O	);
		iconMap.put("hand-peace-o", 	HAND_PEACE_O	);
		iconMap.put("trademark", 	TRADEMARK	);
		iconMap.put("registered", 	REGISTERED	);
		iconMap.put("creative-commons", 	CREATIVE_COMMONS	);
		iconMap.put("gg", 	GG	);
		iconMap.put("gg-circle", 	GG_CIRCLE	);
		iconMap.put("tripadvisor", 	TRIPADVISOR	);
		iconMap.put("odnoklassniki", 	ODNOKLASSNIKI	);
		iconMap.put("odnoklassniki-square", 	ODNOKLASSNIKI_SQUARE	);
		iconMap.put("get-pocket", 	GET_POCKET	);
		iconMap.put("wikipedia-w", 	WIKIPEDIA_W	);
		iconMap.put("safari", 	SAFARI	);
		iconMap.put("chrome", 	CHROME	);
		iconMap.put("firefox", 	FIREFOX	);
		iconMap.put("opera", 	OPERA	);
		iconMap.put("internet-explorer", 	INTERNET_EXPLORER	);
		iconMap.put("tv", 	TV	);
		iconMap.put("television", 	TELEVISION	);
		iconMap.put("contao", 	CONTAO	);
		iconMap.put("amazon", 	AMAZON	);
		iconMap.put("calendar-plus-o", 	CALENDAR_PLUS_O	);
		iconMap.put("calendar-minus-o", 	CALENDAR_MINUS_O	);
		iconMap.put("calendar-times-o", 	CALENDAR_TIMES_O	);
		iconMap.put("calendar-check-o", 	CALENDAR_CHECK_O	);
		iconMap.put("industry", 	INDUSTRY	);
		iconMap.put("map-pin", 	MAP_PIN	);
		iconMap.put("map-signs", 	MAP_SIGNS	);
		iconMap.put("map-o", 	MAP_O	);
		iconMap.put("map", 	MAP	);
		iconMap.put("commenting", 	COMMENTING	);
		iconMap.put("commenting-o", 	COMMENTING_O	);
		iconMap.put("houzz", 	HOUZZ	);
		iconMap.put("vimeo", 	VIMEO	);
		iconMap.put("black-tie", 	BLACK_TIE	);
		iconMap.put("fonticons", 	FONTICONS	);
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
	
	public static BaseIcon fromFileExtension(String extension){
		if("xls".equals(extension) || "xlsx".equals(extension))
			return FILE_EXCEL;
		if("doc".equals(extension) || "docx".equals(extension))
			return FILE_WORD;
		if("pdf".equals(extension))
			return FILE_PDF;
		if("csv".equals(extension))
			return FILE_TEXT_O;
		if("html".equals(extension))
			return GLOBE;
		if("ppt".equals(extension) || "pptx".equals(extension))
			return FILE_PPT;
		if("jpg".equals(extension) || "jpeg".equals(extension) || "png".equals(extension) || "tiff".equals(extension))
			return FILE_IMAGE;
		if("zip".equals(extension) || "tgz".equals(extension))
			return FILE_ZIP;
		return FILE_O;
	}
	
	public static BaseIcon from(String icon) {
		if(null == icon)
			return null;
		
		if("script".equals(icon))
			return SCRIPT;
		if(iconMap.containsKey(icon))
			return iconMap.get(icon);
					
		return fromFileExtension(icon);
	}

	public CssIconImageResource toImageResource(int size){
		return new CssIconImageResource(this, size);
	}
	
	public CssIconImageResource toImageResource(){
		return new CssIconImageResource(this);
	}
	
	public SafeHtml toSafeHtml() {
		return new SafeHtmlBuilder()
				.appendHtmlConstant(getHtml(0))
				.toSafeHtml();
	}

	public Element toElement(){
		return toElement(Document.get());
	}
	
	public Element toElement(Document document) {
		final Element element = document.createElement("i");
		element.addClassName("fa");
		element.addClassName("fa-" + cssName);
		if(null != rotate)
			element.addClassName(rotate);
		return element;
	}

	public SafeHtml toSafeHtml(int size) {
		return new SafeHtmlBuilder()
		.appendHtmlConstant(getHtml(size))
		.toSafeHtml();
	}
	
	private String getHtml(int size) {
		String sizeClass = null;
		if(size == 1)
			sizeClass = "fa-lg";
		else if(size > 1)
			sizeClass = "fa-" + size + "x";
		
		StringBuilder htmlBuilder = new StringBuilder("<i class=\"fa fa-").append(cssName);
		
		if(null != sizeClass)
			htmlBuilder.append(" " + sizeClass);
		if(null != rotate)
			htmlBuilder.append(" " + rotate);
		
		htmlBuilder.append("\"></i>");
		
		return htmlBuilder.toString();
	}

	public String getCssName() {
		return "fa fa-" + cssName;
	}
	
	public String toString(){
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
