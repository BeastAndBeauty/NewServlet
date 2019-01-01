package com.paopao.newservlet.request;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：paopao on 2018/12/18 21:35
 * <p>
 * 作用:
 */
public class RegisterRequest extends BaseRequest{
    private String user_account;
    private String user_key;
    private static RegisterRequest request;

    public static BaseRequest getInstance(){
        if (request == null){
            request = new RegisterRequest();
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
        return "UserRegisterApi";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("user_account",this.user_account);
            body.put("user_key",this.user_key);
           // Log.d("result","busStationId="+busStationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("result","body.toString()="+body.toString());
        return body.toString();
    }

    @Override
    public Object onParseResponse(String response) {

        try {
            String reason = new JSONObject(response).optString("reason");
            Log.d("result","reason="+reason);
            return reason;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
