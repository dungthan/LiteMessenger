package com.example.dung.myapplication;

import android.content.Context;
import android.content.res.Resources;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Win7 on 2/24/2016.
 */
public class StringUtils {
    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
    }

    public static String readRawFile (int fileId, Context context) {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(fileId);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            return "";
        }
    }



/*
    public static String getIdFromDescription (String description) {
        //<a href="https://www.facebook.com/n/?profile.php&amp;id=100005054837568&amp;aref=1456339904698717&amp;medium=rss">Sóc Con</a> posted in <a href="https:/
        //<a href="https://www.facebook.com/n/?maibao.pham&amp;aref=1456339570959303&amp;medium=rss">Mai Bảo Phạm</a> likes your <a h
        String id = "";
        try {
            Document desDoc = Jsoup.parse(description);
            String firstLink = desDoc.select("a").first().attr("href");
            if(firstLink.contains("?profile.php&id=")){
                id = StringUtils.getQueryParams(firstLink).get("id").get(0);
            }else {
                int firstQuestionMarkIndex = firstLink.indexOf("/?");
                int firstAREFIndex = firstLink.indexOf("&aref");
                id = firstLink.substring(firstQuestionMarkIndex +2, firstAREFIndex);
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }*/
    //MReactSidenav
    /*public static Object getElementByItsChild (JSONArray array, String havingChild) {
        for (int i = 0; i < array.length(); i ++) {
            if()
        }
    }*/
}
