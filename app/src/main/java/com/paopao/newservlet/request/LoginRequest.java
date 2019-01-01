package com.paopao.newservlet.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：paopao on 2018/12/19 21:42
 * <p>
 * 作用:
 */
public class LoginRequest extends BaseRequest{
    private String user_account;
    private String user_key;
    private static LoginRequest request;

    public static BaseRequest getInstance(){
        if (request == null){
            request = new LoginRequest();
        }
        return request;
    }

    @Override
    public BaseRequest setParams(String[] params) {
        this.user_account=params[0];
        this.user_key=params[1];
        return request;
    }

    @Override
    public String getActionName() {
        return "UserLoginApi";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("user_account",this.user_account);
            body.put("user_key",this.user_key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    @Override
    public Object onParseResponse(String response) {

        try {
            String reason = new JSONObject(response).optString("reason");
            return reason;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
