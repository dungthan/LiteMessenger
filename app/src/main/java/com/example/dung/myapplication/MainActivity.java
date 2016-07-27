package com.example.dung.myapplication;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    public static final String FB_MESSAGE_URL = "https://m.facebook.com/messages";
    public static final String tAG = "WEBVIEW";
    public static final String U_A_CH = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    public static final String U_A_C_O = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);

        if(Build.VERSION.SDK_INT >= 19)
            webView.getSettings().setUserAgentString(U_A_CH);
        else {
            webView.getSettings().setUserAgentString(U_A_C_O);
        }
        webView.setWebChromeClient(new WebChromeClient(){
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


        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

               if (url != null && url.contains("m.facebook.com/messages")) {
                    if (Build.VERSION.SDK_INT >= 19)
                        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
                    else{
                        webView.getSettings().setUserAgentString(U_A_C_O);
                    }


                } else {
                    if (Build.VERSION.SDK_INT > 19) {
                        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");}
                    else {
                        webView.getSettings().setUserAgentString(U_A_C_O);
                    }
                }


                if (url.startsWith("https://m.facebook.com") || url.contains("http://m.facebook.com")
                        || (url.startsWith("https://mobile.facebook.com")) || (url.startsWith("http://h.facebook.com"))
                        || url.contains("messenger.com") || url.startsWith("https://free.facebook.com")
                        || url.startsWith("https://0.facebook.com")) {
                    return false;
                }
                if (url.startsWith("https://www.facebook.com/") ||  url.startsWith("http://www.facebook.com/")) {
                    url = url.replace("www.facebook.com", "m.facebook.com");
                    webView.loadUrl(url);
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }


            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.e("onLoadResource", url);
            }



            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("onPageStarted", url);
                super.onPageStarted(view, url, favicon);


            }


            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("onPageFinished", url);
                super.onPageFinished(view, url);

                String userhref = "dungk54cd";
                StringBuilder sb = new StringBuilder();
                sb.append("var parent = document.getElementsByTagName('head').item(0);" +
                                "var style = document.createElement('style');" +
                                "style.type = 'text/css';" +
                                "style.innerHTML = '';" +
                                //Rounded avatar
                                "style.innerHTML += 'textarea {resize:none;} #messageGroup i.img.profpic, #messageGroup .ib>a>img {border-radius:50%} '; " +
                                //Background image
                                //"style.innerHTML += 'div[role=\"main\"] {background-size:cover;background-position: center center;background-repeat: no-repeat;background-image:url(http://eskipaper.com/images/summer-green-landscape-1.jpg);}';" +
                                "style.innerHTML += '#root {max-width: 100%; min-width: 100%;}';" +
                                "style.innerHTML += '#messageGroup {background-color: #FAFAFA;} '; " +
                                "style.innerHTML += '#messageGroup>div, div#messageGroup {background: transparent;border:0} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt {background-color:transparent;border-top:0} #messageGroup .chatHighlight, #messageGroup .voice.acw.abt {-webkit-animation: none !important;-moz-animation: none !important;-o-animation: none !important;-ms-animation: none !important;animation: none !important;} '; " +
                                //Bubble chat
                                "style.innerHTML += '#messageGroup a.actor-link, #messageGroup a.actor-link>br {display:none;}'; " +
                                "style.innerHTML += '#messageGroup br {display:none;} #messageGroup a[href=\"/" + userhref +"\"] {position: absolute;right: 0;} #messageGroup a[href=\"/" + userhref + "\"] + div.c {text-align:right;margin-right:50px;} ';" +
                                "style.innerHTML += '#messageGroup br {display:none;} #messageGroup a[href^=\"/" + userhref +"?\"] {position: absolute;right: 0;} #messageGroup a[href^=\"/" + userhref + "?\"] + div.c {text-align:right;margin-right:50px;} ';" +
                                "style.innerHTML += '#messageGroup br {display:none;} #messageGroup a[href=\"#!/" + userhref +"\"] {position: absolute;right: 0;} #messageGroup a[href=\"#!/" + userhref + "\"] + div.c {text-align:right;margin-right:50px;} ';" +
                                "style.innerHTML += '#messageGroup br {display:none;} #messageGroup a[href^=\"#!/" + userhref +"?\"] {position: absolute;right: 0;} #messageGroup a[href^=\"#!/" + userhref + "?\"] + div.c {text-align:right;margin-right:50px;} ';" +
                                // Own messages
                                "style.innerHTML += '#messageGroup a[href=\"/" + userhref + "\"] + div.c .msg div:first-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-top-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup a[href=\"/" + userhref +"\"] + div.c .msg div:last-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-bottom-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup div.c .msg div{margin-bottom: 3px;} #messageGroup a[href=\"/" + userhref +"\"] + div.c .msg div>span {text-align: left;width:auto;display: inline-block;border-radius:2px;border:1px solid #0c81e8;padding:5px;background-color:#0781f8;color:white;border-top-left-radius:12px;border-bottom-left-radius:12px;} ';" +

                                "style.innerHTML += '#messageGroup a[href^=\"/" + userhref + "?\"] + div.c .msg div:first-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-top-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup a[href^=\"/" + userhref +"?\"] + div.c .msg div:last-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-bottom-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup div.c .msg div{margin-bottom: 3px;} #messageGroup a[href^=\"/" + userhref +"?\"] + div.c .msg div>span {text-align: left;width:auto;display: inline-block;border-radius:2px;border:1px solid #0c81e8;padding:5px;background-color:#0781f8;color:white;border-top-left-radius:12px;border-bottom-left-radius:12px;} ';" +

                                "style.innerHTML += '#messageGroup a[href=\"#!/" + userhref + "\"] + div.c .msg div:first-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-top-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup a[href=\"#!/" + userhref +"\"] + div.c .msg div:last-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-bottom-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup div.c .msg div{margin-bottom: 3px;} #messageGroup a[href=\"#!/" + userhref +"\"] + div.c .msg div>span {text-align: left;width:auto;display: inline-block;border-radius:2px;border:1px solid #0c81e8;padding:5px;background-color:#0781f8;color:white;border-top-left-radius:12px;border-bottom-left-radius:12px;} ';" +

                                "style.innerHTML += '#messageGroup a[href^=\"#!/" + userhref + "?\"] + div.c .msg div:first-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-top-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup a[href^=\"#!/" + userhref +"?\"] + div.c .msg div:last-of-type>span {text-align: left;width:auto;display: inline-block;padding:5px;background-color:#0781f8;color:white;border: 1px solid #0c81e8;border-radius:2px; border-top-left-radius:12px; border-bottom-left-radius:12px; border-bottom-right-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup div.c .msg div{margin-bottom: 3px;} #messageGroup a[href^=\"#!/" + userhref +"?\"] + div.c .msg div>span {text-align: left;width:auto;display: inline-block;border-radius:2px;border:1px solid #0c81e8;padding:5px;background-color:#0781f8;color:white;border-top-left-radius:12px;border-bottom-left-radius:12px;} ';" +
                                //Owner messages with attachments
                                "style.innerHTML += '#messageGroup div.voice.acw.abt .msg {display:inline-block;} #messageGroup div.voice.acw.abt #messageGroup .msg span.fcg>a {color:black;} #messageGroup a[href=\"/" + userhref + "\"] + div.c .msg span.fcg>a {color:white;} #messageGroup .msg span.fcg>a>span {display:initial !important;} #messageGroup .msg span:not(.emoticon):empty {display:none !important;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href=\"/" + userhref + "\"] + div.c .ib.attachment {text-align:right;display:inline-block;padding-bottom: 0;width: auto;padding: 5px;background-color: #0781f8;color: white;border: 1px solid #0c81e8;border-radius: 2px;border-top-left-radius: 12px;border-bottom-left-radius: 12px;border-bottom-right-radius: 12px;} #messageGroup div.voice.acw.abt .ib.attachment .desc.c.mfss {display:inline-block;flex:none;text-align:right;margin-bottom:0;margin-top:-5px;margin-left:-5px;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href=\"" + userhref + "\"] + div.c .ib.attachment i {display:inline-block} ';" +

                                "style.innerHTML += '#messageGroup div.voice.acw.abt .msg {display:inline-block;} #messageGroup div.voice.acw.abt #messageGroup .msg span.fcg>a {color:black;} #messageGroup a[href^=\"/" + userhref + "?\"] + div.c .msg span.fcg>a {color:white;} #messageGroup .msg span.fcg>a>span {display:initial !important;} #messageGroup .msg span:not(.emoticon):empty {display:none !important;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href^=\"/" + userhref + "?\"] + div.c .ib.attachment {text-align:right;display:inline-block;padding-bottom: 0;width: auto;padding: 5px;background-color: #0781f8;color: white;border: 1px solid #0c81e8;border-radius: 2px;border-top-left-radius: 12px;border-bottom-left-radius: 12px;border-bottom-right-radius: 12px;} #messageGroup div.voice.acw.abt .ib.attachment .desc.c.mfss {display:inline-block;flex:none;text-align:right;margin-bottom:0;margin-top:-5px;margin-left:-5px;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href^=\"" + userhref + "?\"] + div.c .ib.attachment i {display:inline-block} ';" +

                                "style.innerHTML += '#messageGroup div.voice.acw.abt .msg {display:inline-block;} #messageGroup div.voice.acw.abt #messageGroup .msg span.fcg>a {color:black;} #messageGroup a[href=\"#!/" + userhref + "\"] + div.c .msg span.fcg>a {color:white;} #messageGroup .msg span.fcg>a>span {display:initial !important;} #messageGroup .msg span:not(.emoticon):empty {display:none !important;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href=\"#!/" + userhref + "\"] + div.c .ib.attachment {text-align:right;display:inline-block;padding-bottom: 0;width: auto;padding: 5px;background-color: #0781f8;color: white;border: 1px solid #0c81e8;border-radius: 2px;border-top-left-radius: 12px;border-bottom-left-radius: 12px;border-bottom-right-radius: 12px;} #messageGroup div.voice.acw.abt .ib.attachment .desc.c.mfss {display:inline-block;flex:none;text-align:right;margin-bottom:0;margin-top:-5px;margin-left:-5px;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href=\"#!" + userhref + "\"] + div.c .ib.attachment i {display:inline-block} ';" +

                                "style.innerHTML += '#messageGroup div.voice.acw.abt .msg {display:inline-block;} #messageGroup div.voice.acw.abt #messageGroup .msg span.fcg>a {color:black;} #messageGroup a[href^=\"/" + userhref + "?\"] + div.c .msg span.fcg>a {color:white;} #messageGroup .msg span.fcg>a>span {display:initial !important;} #messageGroup .msg span:not(.emoticon):empty {display:none !important;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href^=\"#!/" + userhref + "?\"] + div.c .ib.attachment {text-align:right;display:inline-block;padding-bottom: 0;width: auto;padding: 5px;background-color: #0781f8;color: white;border: 1px solid #0c81e8;border-radius: 2px;border-top-left-radius: 12px;border-bottom-left-radius: 12px;border-bottom-right-radius: 12px;} #messageGroup div.voice.acw.abt .ib.attachment .desc.c.mfss {display:inline-block;flex:none;text-align:right;margin-bottom:0;margin-top:-5px;margin-left:-5px;} ';" +
                                "style.innerHTML += '#messageGroup div.voice.acw.abt a[href^=\"#!" + userhref + "?\"] + div.c .ib.attachment i {display:inline-block} ';" +
                                //Other messages with attachments
                                "style.innerHTML += '#messageGroup div.voice.acw.abt .ib.attachment {padding-bottom: 0;width: auto;padding:5px;background-color:#e7e7e7;color:black;border: 1px solid #e7e7e7;border-radius:2px; border-top-right-radius:12px; border-bottom-right-radius:12px; border-top-left-radius: 12px;display: inline-block;}';" +
                                // Other messages
                                "style.innerHTML += '#messageGroup div.c .msg div:first-of-type>span {display: inline-block;padding:5px;background-color:#eaeaea;color:black;border: 1px solid #eaeaea;border-radius:2px; border-top-right-radius:12px; border-bottom-right-radius:12px; border-top-left-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup div.c .msg div:last-of-type>span {display: inline-block;padding:5px;background-color:#eaeaea;color:black;border: 1px solid #eaeaea;border-radius:2px; border-top-right-radius:12px; border-bottom-right-radius:12px; border-bottom-left-radius: 12px;} ';" +
                                "style.innerHTML += '#messageGroup div.c .msg div{margin-bottom: 3px;} #messageGroup div.c .msg div>span {display: inline-block;border-radius:2px;border:1px solid #eaeaea;padding:5px;background-color:#eaeaea;color:black;border-top-right-radius:12px;border-bottom-right-radius:12px;} ';" +
                                // Input area
                                "style.innerHTML += '#message-reply-composer ._5s61._5eco button i {background-image: url(https://i.imgsafe.org/eae891ef3d.png); background-position: 0 !important; width: 32px; height: 32px;}';" +
                                "style.innerHTML += '#message-reply-composer ._5s61._5eco button {padding: 0px !important; height: 31px;}';" +
                                "style.innerHTML += '#message-reply-composer ._5s61._5eco a[role=\"button\"] div._5aqx {background-image: url(https://i.imgsafe.org/eb5eeb22d7.png); background-position: 0 !important; width: 32px; height: 32px;}';" +
                                "style.innerHTML += '#message-reply-composer ._5s61._5eco a[role=\"button\"] {padding: 0px;} '; " +
                                "style.innerHTML += '#message-reply-composer {border-top: 1px solid #BDBDBD;} '; " +
                                "style.innerHTML += '#message-reply-composer {padding-bottom: 0; border-bottom:1px solid #BDBDBD;} '; " +
                                "style.innerHTML += '#message-reply-composer ._5s61._5eco button {padding-top:0px;background:0;border:0} #message-reply-composer ._5s61._5eco a {padding-top:8px;background:0;border:0} #message-reply-composer ._5aqv.acw {padding-left:4px;padding-right:4px;} #message-reply-composer ._4g34._52cq textarea {border: none; box-shadow:none; height: 33px; line-height: 24px;} '; " +
                                "style.innerHTML += '#message-reply-composer ._5s61 button[name=\"like\"] {padding: 0px; height: 32px; background: transparent; border-color: transparent; box-shadow: none;}';" +
                                "style.innerHTML += '#message-reply-composer ._5s61 button[name=\"like\"] i {background-image: url(https://i.imgsafe.org/f9b1fd9ce3.png); height: 32px; width: 32px; background-position: 0 !important; margin: 0px !important;}';" +
                                "style.innerHTML += '#message-reply-composer ._5s61 button[name=\"send\"] {padding: 0px; height: 32px; background: transparent; border-color: transparent; box-shadow: none; color: #2E5EB6; font-size: 20px; line-height: 25px;}';" +
                                "style.innerHTML += 'input:focus, select:focus, textarea:focus, button:focus { outline: none; }';" +
                                // etc
                                "style.innerHTML += '#messageGroup a[href=\"/" + userhref + "\"] + div.c .msg {max-width:85%; float:right;} #messageGroup .msg {max-width:85%; float:left;} #messageGroup .msg span {word-break:break-word;} #messageGroup .actions.mfss.fcg {clear:both;}'; " +
                                "style.innerHTML += '#messageGroup ._4prr::after {box-shadow:none !important; border:0 !important;} #messageGroup a[href=\"/" + userhref + "\"] + div.c .messageAttachments {width: 100%;text-align: right;clear: both;float:right;} #messageGroup .messageAttachments {width: 100%;text-align: left;clear: both;float:left;} ';" +
                                "style.innerHTML += '#messageGroup a[href=\"/" + userhref +"\"] + div.c div.actions.mfss.fcg>span:first-of-type, a[href=\"/" + userhref + "\"] + div.c div.actions.mfss.fcg>span:first-of-type>span {display:none}'; " +

                                "style.innerHTML += '#messageGroup a[href^=\"/" + userhref + "?\"] + div.c .msg {max-width:85%; float:right;} #messageGroup .msg {max-width:85%; float:left;} #messageGroup .msg span {word-break:break-word;} #messageGroup .actions.mfss.fcg {clear:both;}'; " +
                                "style.innerHTML += '#messageGroup ._4prr::after {box-shadow:none !important; border:0 !important;} #messageGroup a[href^=\"/" + userhref + "?\"] + div.c .messageAttachments {width: 100%;text-align: right;clear: both;float:right;} #messageGroup .messageAttachments {width: 100%;text-align: left;clear: both;float:left;} ';" +
                                "style.innerHTML += '#messageGroup a[href^=\"/" + userhref +"?\"] + div.c div.actions.mfss.fcg>span:first-of-type, a[href^=\"/" + userhref + "?\"] + div.c div.actions.mfss.fcg>span:first-of-type>span {display:none}'; " +

                                "style.innerHTML += '#messageGroup a[href=\"#!/" + userhref + "\"] + div.c .msg {max-width:85%; float:right;} #messageGroup .msg {max-width:85%; float:left;} #messageGroup .msg span {word-break:break-word;} #messageGroup .actions.mfss.fcg {clear:both;}'; " +
                                "style.innerHTML += '#messageGroup ._4prr::after {box-shadow:none !important; border:0 !important;} #messageGroup a[href=\"#!/" + userhref + "\"] + div.c .messageAttachments {width: 100%;text-align: right;clear: both;float:right;} #messageGroup .messageAttachments {width: 100%;text-align: left;clear: both;float:left;} ';" +
                                "style.innerHTML += '#messageGroup a[href=\"#!/" + userhref +"\"] + div.c div.actions.mfss.fcg>span:first-of-type, a[href=\"#!/" + userhref + "\"] + div.c div.actions.mfss.fcg>span:first-of-type>span {display:none}'; " +

                                "style.innerHTML += '#messageGroup a[href^=\"#!/" + userhref + "?\"] + div.c .msg {max-width:85%; float:right;} #messageGroup .msg {max-width:85%; float:left;} #messageGroup .msg span {word-break:break-word;} #messageGroup .actions.mfss.fcg {clear:both;}'; " +
                                "style.innerHTML += '#messageGroup ._4prr::after {box-shadow:none !important; border:0 !important;} #messageGroup a[href^=\"#!/" + userhref + "?\"] + div.c .messageAttachments {width: 100%;text-align: right;clear: both;float:right;} #messageGroup .messageAttachments {width: 100%;text-align: left;clear: both;float:left;} ';" +
                                "style.innerHTML += '#messageGroup a[href^=\"#!/" + userhref +"?\"] + div.c div.actions.mfss.fcg>span:first-of-type, a[href^=\"#!/" + userhref + "?\"] + div.c div.actions.mfss.fcg>span:first-of-type>span {display:none}'; " +

                                "style.innerHTML += '#messageGroup a[href=\"/" + userhref + "\"] + div.c .msg a, #messageGroup a[href=\"/" + userhref + "\"] + div.c .messageAttachments a {color: white !important;}'; " +
                                "style.innerHTML += '#messageGroup a[href^=\"/" + userhref + "?\"] + div.c .msg a, #messageGroup a[href^=\"/" + userhref + "?\"] + div.c .messageAttachments a {color: white !important;}'; " +
                                "style.innerHTML += '#messageGroup a[href=\"#!/" + userhref + "\"] + div.c .msg a, #messageGroup a[href=\"#!/" + userhref + "\"] + div.c .messageAttachments a {color: white !important;}'; " +
                                "style.innerHTML += '#messageGroup a[href^=\"#!/" + userhref + "?\"] + div.c .msg a, #messageGroup a[href^=\"#!/" + userhref + "?\"] + div.c .messageAttachments a {color: white !important;}'; " +
                                "style.innerHTML += '#messageGroup a.avt-messager i:after {content: \"\"; width: 43px; height: 43px; display: block; background-repeat: no-repeat; background-position: bottom right; background-image:url(https://i.imgsafe.org/547cbce84e.png)}';" +
                                "style.innerHTML += '#messageGroup .chatHighlight a.avt-messager:after {content: \"\"; width: 43px; height: 43px; display: inline-block; background-repeat: no-repeat; background-position: bottom right; background-image:url(https://i.imgsafe.org/547cbce84e.png)}';" +
                                "style.innerHTML += '#messageGroup .chatHighlight a.avt-messager img {position: absolute; z-index: -1;}';" +
                                //remove Online status bar
                                "style.innerHTML +='#root.maxwidth.acw[role=\"main\"]>div>._55wr._4g33._52we._44qk {display:none;} ';" +

                                "parent.appendChild(style);" +

                                //Load more on scroll top
                                "var last_known_scroll_position = 0;\n" +
                                "var ticking = false;\n" +
                                "\n" +
                                "function doSomething(scroll_pos) {\n" +
                                "var event = new MouseEvent('click', {\n" +
                                "'view': window,\n" +
                                "'bubbles': true,\n" +
                                "});\n" +
                                "if(scroll_pos == 0) {var target = document.querySelector('#see_older a'); target.dispatchEvent(event) }" +
                                "}\n" +
                                "\n" +
                                "function loadOldMessages() {var target = document.querySelector('#see_older a'); target.dispatchEvent(event); } \n" +

                                "window.addEventListener('scroll', function(e) {\n" +
                                " last_known_scroll_position = window.scrollY;\n" +
                                " if (!ticking) {\n" +
                                " window.requestAnimationFrame(function() {\n" +
                                " doSomething(last_known_scroll_position);\n" +
                                " ticking = false;\n" +
                                " });\n" +
                                " }\n" +
                                " ticking = true;\n" +
                                "});" +

                                "document.addEventListener('DOMNodeInserted', function(event) { " +
                                "var element = event.target; " +
                                "if (element.tagName == 'DIV') {" +

                                    "if (element.id == 'root') {" +
                                        "var nodeVoice = element.querySelectorAll('.voice.acw.abt');" +
                                        "for (var j = 0; j < nodeVoice.length; j++) {" +
                                            "var actionsMessage = nodeVoice[j].querySelectorAll('.actions.mfss.fcg');" +
                                            "for (var i = 0; i < actionsMessage.length; i++) {" +
                                                "var childNodesList = actionsMessage[0].childNodes;" +
                                                "if (childNodesList.length == 3 && childNodesList[2].innerText.indexOf('Messenger') > 0) {" +
                                                    "var sourceAvt = nodeVoice[j].querySelectorAll('.darkTouch.l');" +
                                                    "var sourceAvtClass = sourceAvt[0].className;" +
                                                    "sourceAvt[0].className = sourceAvtClass + ' avt-messager';" +
                                                "}" +

                                            "}" +
                                        "}" +
                                    "}" +
                                    "if (element.className == '' && element.querySelectorAll('.voice.acw.abt').length > 0) {" +
                                        "var nodeVoice = element.querySelectorAll('.voice.acw.abt');" +
                                        "for (var j = 0; j < nodeVoice.length; j++) {" +
                                            "var actionsMessage = nodeVoice[j].querySelectorAll('.actions.mfss.fcg');" +
                                            "for (var i = 0; i < actionsMessage.length; i++) {" +
                                                "var childNodesList = actionsMessage[0].childNodes;" +
                                                "if (childNodesList.length == 3 && childNodesList[2].innerText.indexOf('Messenger') > 0) {" +
                                                    "var sourceAvt = nodeVoice[j].querySelectorAll('.darkTouch.l');" +
                                                    "var sourceAvtClass = sourceAvt[0].className;" +
                                                    "sourceAvt[0].className = sourceAvtClass + ' avt-messager';" +
                                                "}" +
                                            "}" +
                                        "}" +
                                    "}" +
                                    "if (element.className == 'voice acw abt') {" +
                                        "var parents = element.childNodes;" +
                                        "for(var i = 0; i < parents.length; i++) {" +
                                            "var sourceMessage = parents[i].querySelectorAll('.source.mfss.fcg');" +
                                            "if (sourceMessage[0].innerText.indexOf('Messenger') > 0) { " +
                                                "var sourceAvt = parents[i].querySelectorAll('.darkTouch.l');" +
                                                "var sourceAvtClass = sourceAvt[0].className;" +
                                                "sourceAvt[0].className = sourceAvtClass + ' avt-messager';" +
                                            "}" +
                                        "}"+
                                    "}" +
                                "} }, false);"
                );
                webView.loadUrl("javascript:(function(){" + sb.toString() + "})();");

                webView.loadUrl("javascript:(function(){" +
                       // "var imgs = document.querySelectorAll('div#messageGroup');" +
                        "})();");

            }





        });





        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(FB_MESSAGE_URL);
    }

    @Override
    public void onBackPressed() {
        if(webView != null && webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
