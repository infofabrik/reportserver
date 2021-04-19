package net.datenwerke.gf.client;

//credit Fred Sauer. The original code comes from gwt-log 
public class LogUtil {

    public static native void setJSNIErrorHandler()
        /*-{
          if ($wnd != window) {
            window.onerror =
@net.datenwerke.gf.client.LogUtil::handleOnError(Ljava/lang/String;Ljava/lang/String;I);
          }

          var oldOnError = $wnd.onerror;
          $wnd.onerror = function(msg, url, line) {
            var result, oldResult;
            try {
              result =
@net.datenwerke.gf.client.LogUtil::handleOnError(Ljava/lang/String;Ljava/lang/String;I)(msg, url, line);
            } finally {
              oldResult = oldOnError && oldOnError(msg, url, line);
            }
            if (result != null) return result;
            if (oldResult != null) return oldResult;
          };
        }-*/;

    private static native boolean handleOnError(String msg, String url, int line)
        /*-{
          @net.datenwerke.gf.client.LogUtil::fatal(Ljava/lang/String;)("Uncaught JavaScript exception [" + msg
+ "] in " + url + ", line " + line);
          return true;
        }-*/;

    private static void fatal(String message) {
        System.err.println(message);
    }
}