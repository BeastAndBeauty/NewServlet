package com.paopao.newservlet.request;

import com.paopao.newservlet.callback.OnResponseListener;
import com.paopao.newservlet.utils.NetUtil;

/**
 * 作者：paopao on 2018/12/17 22:42
 * <p>
 * 作用:
 */
public abstract class BaseRequest {
    public abstract BaseRequest setParams(String[] params);
    public abstract  String getActionName();
    public abstract String getRequestBody();
    public abstract Object onParseResponse(String response);
    public void sendRequest(final OnResponseListener responseListener){
        //String url="http://120.78.4.147:8080/News/"+getActionName();
        String url="http://192.168.0.5:8080/News/"+getActionName();
        NetUtil.asyncRequest(url, getRequestBody(), new OnResponseListener() {
            @Override
            public void onResponse(Object result) {
                responseListener.onResponse(onParseResponse(result.toString()));
            }
        });
    }

}
