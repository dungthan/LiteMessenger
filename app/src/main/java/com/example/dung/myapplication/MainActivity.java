package com.example.dung.myapplication;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private int cssMessageFileCount = 0;
    private boolean isShouldReplaceMessageCss = false;
    private int cssOnlineListCount = 0;
    private boolean isShouldReplaceOnlineCss = false;

    private int cssAllFacebookCount = 0;
    private boolean isShouldReplaceAllFacebookCss = false;

    public static final String FB_MESSAGE_URL = "https://m.facebook.com/messages";
    public static final String ONLINE_URL = "https://m.facebook.com/buddylist.php";
    public static final String FB_NEWSFEED_URL = "https://m.facebook.com/home.php?_rdr";
    public static final String tAG = "WEBVIEW";
    public static final String U_A_CH = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    public static final String U_A_C_O = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);

        if (Build.VERSION.SDK_INT >= 19)
            webView.getSettings().setUserAgentString(U_A_CH);
        else {
            webView.getSettings().setUserAgentString(U_A_C_O);
        }
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e("WebviewLog", consoleMessage.message());
                return true;
            }
        });


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url != null && url.contains("m.facebook.com/messages")) {
                    if (Build.VERSION.SDK_INT >= 19)
                        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
                    else {
                        webView.getSettings().setUserAgentString(U_A_C_O);
                    }


                } else {
                    if (Build.VERSION.SDK_INT > 19) {
                        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
                    } else {
                        webView.getSettings().setUserAgentString(U_A_C_O);
                    }
                }


                if (url.startsWith("https://m.facebook.com") || url.contains("http://m.facebook.com")
                        || (url.startsWith("https://mobile.facebook.com")) || (url.startsWith("http://h.facebook.com"))
                        || url.contains("messenger.com") || url.startsWith("https://free.facebook.com")
                        || url.startsWith("https://0.facebook.com")) {
                    return false;
                }
                if (url.startsWith("https://www.facebook.com/") || url.startsWith("http://www.facebook.com/")) {
                    url = url.replace("www.facebook.com", "m.facebook.com");
                    webView.loadUrl(url);
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }


            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, final WebResourceRequest request) {
                if (urlShouldBeHandledByWebView(request.getUrl().toString()))
                    return handleRequestViaOkHttp(request.getUrl().toString());
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            @SuppressWarnings("deprecation")
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (urlShouldBeHandledByWebView(url))
                    return handleRequestViaOkHttp(url);
                return super.shouldInterceptRequest(view, url);
            }


            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //Log.e("onLoadResource", url);
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("onPageStarted", url);
                super.onPageStarted(view, url, favicon);

                if (url != null && url.contains("facebook.com/messages")) {
                    cssMessageFileCount = 0;
                    isShouldReplaceMessageCss = true;
                }else{
                    isShouldReplaceMessageCss = false;
                }

                if (url != null && url.contains("facebook.com/buddylist")) {
                    cssOnlineListCount = 0;
                    isShouldReplaceOnlineCss = true;
                }else {
                    isShouldReplaceOnlineCss = false;
                }

            }


            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("onPageFinished", url);
                super.onPageFinished(view, url);
            }


        });


        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(ONLINE_URL);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }



    public boolean urlShouldBeHandledByWebView(String url) {
        if(url != null && url.contains(".css")/* && url.contains("rsrc.php")*/) {
            Log.e("urlShouldBeHandled", url);
            if( ++cssMessageFileCount == 5 && isShouldReplaceMessageCss) return true;
            if( ++cssOnlineListCount == 5 && isShouldReplaceOnlineCss) return true;

        }

        return false;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    private WebResourceResponse handleRequestViaOkHttp(@NonNull String url) {
        Log.e("ViaOkHttp", url);
        if (url.contains(".css")) {
            if (isShouldReplaceMessageCss)
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    final okhttp3.Call call = okHttpClient.newCall(new Request.Builder()
                            .url(url)
                            .build()
                    );

                    final Response response = call.execute();
                    String userhref;
                    userhref = "dungk54cd";
                    String customeCss = StringUtils.readRawFile(R.raw.message_style, this);

                    customeCss = customeCss.replace("user_name", userhref);
                    String css = convertStreamToString(response.body().byteStream());
                    css += "\n" + customeCss;


                    return new WebResourceResponse(
                            response.header("content-type", "text/plain"), // You can set something other as default content-type
                            response.header("content-encoding", "utf-8"),  // Again, you can set another encoding as default
                            new ByteArrayInputStream(css.getBytes("UTF-8"))
                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    isShouldReplaceMessageCss = false;
                }

            /***
             **  Append our css style in bottom of Facebook css file
             * */
            if (isShouldReplaceOnlineCss)
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    final okhttp3.Call call = okHttpClient.newCall(new Request.Builder()
                            .url(url)
                            .build()
                    );

                    final Response response = call.execute();
                    String userhref;
                    userhref = "dungk54cd";
                    String customeCss = StringUtils.readRawFile(R.raw.online_list_style, this);
                    //replace username if necessary
                    customeCss = customeCss.replace("user_name", userhref);
                    String css = convertStreamToString(response.body().byteStream());
                    css += "\n" + customeCss;


                    return new WebResourceResponse(
                            response.header("content-type", "text/plain"), // You can set something other as default content-type
                            response.header("content-encoding", "utf-8"),  // Again, you can set another encoding as default
                            new ByteArrayInputStream(css.getBytes("UTF-8"))
                    );
                }catch (Exception e) {e.printStackTrace();}
                finally {
                    isShouldReplaceOnlineCss = false;
                }
        }

        return null;
    }

}
