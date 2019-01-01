package com.paopao.newservlet.utils;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.paopao.newservlet.callback.OnResponseListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 作者：paopao on 2018/12/17 22:48
 * <p>
 * 作用:
 */
public class NetUtil {
    private static Handler handler=new Handler(Looper.getMainLooper());
    public static void asyncRequest(final String url, final String requestBody, final OnResponseListener onResponseListener){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL mURL=new URL(url);
                    HttpURLConnection connection= (HttpURLConnection) mURL.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(10*1000);
                    connection.setReadTimeout(10*1000);
                    connection.setRequestProperty("Content-Type","application/json");
                    if (requestBody != null){
                        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                        osw.write(requestBody);
                        osw.flush();
                        osw.close();
                    }
                    final StringBuffer sb = new StringBuffer();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = "";
                    while ((line = reader.readLine())!= null){
                        sb.append(line);
                    }
                    reader.close();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("result","sb.toString()="+sb.toString());
                            onResponseListener.onResponse(sb.toString());
                        }
                    });

                } catch (MalformedURLException e) {
                    Log.d("Exception",e.toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("Exception",e.toString());
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
